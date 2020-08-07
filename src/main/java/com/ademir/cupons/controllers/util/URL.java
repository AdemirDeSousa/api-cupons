package com.ademir.cupons.controllers.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URL {
	
	public static String decodeParam(String palavra) {
		try {
			return URLDecoder.decode(palavra, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}
