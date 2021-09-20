package com.bna.cash.security.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends OncePerRequestFilter {
	public static final String HEADER_STRING = "Authorization";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String SECRET = "admin@esprit.tn";

	@SuppressWarnings("unchecked")
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		response.addHeader("Access-Control-Allow-Origin", "http://localhost:3030");
		response.addHeader("Access-Control-Allow-Headers",
				" Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, Authorization");
		response.addHeader("Access-Control-Expose-Headers",
				"Access-Control-Allow-Origin, Access-Control-Allow-Credentials, authorization");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");

		String jwt = request.getHeader(HEADER_STRING);
		if (request.getMethod().equals("OPTIONS")) {
			response.setStatus(HttpServletResponse.SC_OK);
		}

		else {
			if (jwt == null || !jwt.startsWith(TOKEN_PREFIX)) {
				filterChain.doFilter(request, response);
				return;
			}
			Claims claims = Jwts.parser().setSigningKey(SECRET)
					.parseClaimsJws(jwt.replace(TOKEN_PREFIX, "")).getBody();
			String username = claims.getSubject();
			Collection<GrantedAuthority> authorities = new ArrayList<>();
			ArrayList<Map<String, String>> roles = (ArrayList<Map<String, String>>) claims.get("roles");
			roles.forEach(r -> {
				authorities.add(new SimpleGrantedAuthority(r.get("authority")));
			});
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
					null, authorities);
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			filterChain.doFilter(request, response);

		}

	}
}
