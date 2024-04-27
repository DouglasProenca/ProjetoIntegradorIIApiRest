package com.sistema.apicr7imports.domain.response;

public class CpfResponse {

	private boolean valido;

	/**
	 * @param valido
	 */
	public CpfResponse(boolean valido) {
		super();
		this.valido = valido;
	}

	/**
	 * @return the valido
	 */
	public boolean isValido() {
		return valido;
	}

	/**
	 * @param valido the valido to set
	 */
	public void setValido(boolean valido) {
		this.valido = valido;
	}
		
}
