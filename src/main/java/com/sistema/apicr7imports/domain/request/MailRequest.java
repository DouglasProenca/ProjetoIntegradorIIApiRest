package com.sistema.apicr7imports.domain.request;

import com.sistema.apicr7imports.domain.VO.UserVO;

public class MailRequest {

	private String destinatario;
	private String assunto;
	private String texto;
	private String anexoTitulo;
	private String anexoFile;
	private UserVO user;
	
	
	public MailRequest() {
		
	}
	
	
	/**
	 * @return the destinatario
	 */
	public String getDestinatario() {
		return destinatario;
	}
	/**
	 * @param destinatario the destinatario to set
	 */
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	/**
	 * @return the assunto
	 */
	public String getAssunto() {
		return assunto;
	}
	/**
	 * @param assunto the assunto to set
	 */
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	/**
	 * @return the texto
	 */
	public String getTexto() {
		return texto;
	}
	/**
	 * @param texto the texto to set
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}
	/**
	 * @return the user
	 */
	public UserVO getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(UserVO user) {
		this.user = user;
	}


	/**
	 * @return the anexoTitulo
	 */
	public String getAnexoTitulo() {
		return anexoTitulo;
	}


	/**
	 * @param anexoTitulo the anexoTitulo to set
	 */
	public void setAnexoTitulo(String anexoTitulo) {
		this.anexoTitulo = anexoTitulo;
	}


	/**
	 * @return the anexoFile
	 */
	public String getAnexoFile() {
		return anexoFile;
	}


	/**
	 * @param anexoFile the anexoFile to set
	 */
	public void setAnexoFile(String anexoFile) {
		this.anexoFile = anexoFile;
	}
	
	
}
