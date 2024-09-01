package com.sistema.apicr7imports.controller;

import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.sistema.apicr7imports.data.model.Country;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Paises")
public interface ICountryController {
	
	@Operation(description = "Todos os paises cadastrados")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "Todos os paises cadastrados."),
		    @ApiResponse(responseCode = "403", description = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(responseCode = "500", description = "Erro geral da Aplicação.")
		})
	ResponseEntity<List<Country>> findAll();

	@Operation(description = "Todos os paises cadastrados de forma paginada")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "Todos os paises cadastrados."),
		    @ApiResponse(responseCode = "403", description = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(responseCode = "500", description = "Erro geral da Aplicação.")
		})
	ResponseEntity<PagedModel<EntityModel<Country>>> findAllPage(@RequestParam(value = "page", defaultValue = "0") Integer page
                                                                ,@RequestParam(value = "limit", defaultValue = "10") Integer limit
                                                                ,@RequestParam(value = "direction", defaultValue = "asc") String direction);



	@Operation(description = "Pais cadastrado por id")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "Pais cadastrado por id."),
		    @ApiResponse(responseCode = "403", description = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(responseCode = "404", description = "Pais não encontrado."),
		    @ApiResponse(responseCode = "500", description = "Erro geral da Aplicação.")
		})
	ResponseEntity<Country> findById(@Parameter(description = "ID de Cadastro no Banco.", required = true, example = "1") @PathVariable Integer id);

}
