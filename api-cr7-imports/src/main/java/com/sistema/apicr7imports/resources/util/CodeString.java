package com.sistema.apicr7imports.resources.util;

import java.io.UnsupportedEncodingException;

public class CodeString {

	public static String decodeString(String string) {
		try {
		String[] corArray = string.split(",");
		byte[] bytes = new byte[corArray.length];
		for (int i = 0; i < corArray.length; i++) {
			bytes[i] = Byte.parseByte(corArray[i]);
		}
			return new String(bytes, "UTF-8");
		} catch (UnsupportedEncodingException | NullPointerException e) {
			//e.printStackTrace();
			return null;
		}
	}

	public static String codeString(String string) {
		byte[] bytes = string.getBytes();
		String aux = "";

		for (int i = 0; i < bytes.length; i++) {
			aux = aux + bytes[i]+",";
		}
		return aux;
	}
}
