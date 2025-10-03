package com.sistema.apicr7imports.useful;

import org.apache.commons.codec.binary.Base64;

public class CodeStringHelper {
	
	public static String codeString(String string) {
	    return new Base64().encodeToString(string.getBytes());
	}

	public static String decodeString(String string) {
	    return new String(new Base64().decode(string));
	}
}
