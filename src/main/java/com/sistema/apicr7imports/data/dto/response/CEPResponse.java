package com.sistema.apicr7imports.data.dto.response;

import lombok.Getter;

@Getter
public class CEPResponse {

	String cep;
	String logradouro;
    String complemento;
	String bairro;
	String localidade;
	String uf;
	Integer ibge;
	Integer gia;
	Integer ddd;
	Integer siafi;

}
