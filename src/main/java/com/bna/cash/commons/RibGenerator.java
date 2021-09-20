package com.bna.cash.commons;

import java.math.BigInteger;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

public class RibGenerator {

	private static final String CODE_BANQUE_BNA = "03";
	private static final String CODE_AGENCE = "000";

	public static String generateRib() {
		String rib = CODE_BANQUE_BNA + CODE_AGENCE + RandomStringUtils.randomNumeric(13) + "xx";
		String cleCalculee = getCleRib(rib);
		rib = rib.replace("xx", cleCalculee);
		return rib;
	}
	
	public static void main(String[] args) {
		System.out.println(generateRib());
	}

	public static boolean validateRibTunisie(String rib) {
		if (StringUtils.length(rib) == 20) {
			try {
				int posCleRib = 18;
				BigInteger n = new BigInteger(rib.substring(0, posCleRib));
				BigInteger cleRib = new BigInteger(rib.substring(posCleRib, 20));
				BigInteger x = BigInteger.valueOf(100);
				BigInteger n1 = n.multiply(x);
				BigInteger r = n1.mod(BigInteger.valueOf(97));
				BigInteger k = BigInteger.valueOf(97).subtract(r);
				return (k.equals(cleRib));
			} catch (NumberFormatException ex) {
				return false;
			}

		} else {
			return false;
		}
	}

	public static String getCleRib(String rib) {
		if (StringUtils.length(rib) == 20) {
			int posCleRib = 18;
			BigInteger n = new BigInteger(rib.substring(0, posCleRib));
			BigInteger x = BigInteger.valueOf(100);
			BigInteger n1 = n.multiply(x);
			BigInteger r = n1.mod(BigInteger.valueOf(97));
			BigInteger k = BigInteger.valueOf(97).subtract(r);
			return k.toString();

		}
		return "";
	}

}
