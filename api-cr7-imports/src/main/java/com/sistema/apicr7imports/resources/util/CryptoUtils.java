package com.sistema.apicr7imports.resources.util;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class CryptoUtils {

	public static String gerarhashSenha(String senhaAberta) {
		return BCrypt.withDefaults().hashToString(8, senhaAberta.toCharArray());
	}

	public static boolean verificarSenha(String senhaAberta, String senhaFechada) {
		BCrypt.Result resultado = BCrypt.verifyer().verify(senhaAberta.toCharArray(), senhaFechada);
		return resultado.verified;
	} 
}
