package com.bna.cash.rest.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import exceptions.RestException;
import lombok.Getter;
import lombok.Setter;

/**
 *
 */
@ControllerAdvice
public class ExceptionHandlerController {

	/**
	 * Handles RestException
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler({ RestException.class })
	public ResponseEntity<Object> handleRestException(RestException ex, WebRequest request) {
		return new ResponseEntity<>(new DataException(ex.getStatus(), ex.getCode()), new HttpHeaders(), ex.getStatus());
	}

	/**
	 * Dto object to hold the exception code and status
	 * @author zda
	 *
	 */
	@Getter
	@Setter
	class DataException {

		private int status;
		private String code;

		public DataException(HttpStatus httpStatus, String code) {
			this.status = httpStatus.value();
			this.code = code;
		}
	}
}
