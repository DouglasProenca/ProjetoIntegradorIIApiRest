package com.sistema.apicr7imports.domain.Dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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
