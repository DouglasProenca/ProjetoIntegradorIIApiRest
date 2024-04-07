package com.sistema.apicr7imports.codeString;

import java.io.UnsupportedEncodingException;

public class CodeString {

	public static String decodeString(String string) throws UnsupportedEncodingException, NullPointerException {
		String[] stringArray = string.split(",");
		byte[] bytes = new byte[stringArray.length];
		for (int i = 0; i < stringArray.length; i++) {
			bytes[i] = Byte.parseByte(stringArray[i]);
		}
		return new String(bytes, "UTF-8");
	}

	public static String codeString(String string) {
		byte[] bytes = string.getBytes();
		String aux = "";

		for (int i = 0; i < bytes.length; i++) {
			aux = aux + bytes[i] + ",";
		}
		return aux;
	}
}
