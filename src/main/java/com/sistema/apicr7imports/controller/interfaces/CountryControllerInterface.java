package com.sistema.apicr7imports.controller.interfaces;

import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.sistema.apicr7imports.domain.Country;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Paises")
public interface CountryControllerInterface {
	
	@ApiOperation(value = "Todos os paises cadastrados")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Todos os paises cadastrados."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 500, message = "Erro geral da Aplicação.")
		})
	public ResponseEntity<List<Country>> findAll();

	@ApiOperation(value = "Todos os paises cadastrados de forma paginada")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Todos os paises cadastrados."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 500, message = "Erro geral da Aplicação.")
		})
	public ResponseEntity<PagedModel<EntityModel<Country>>> findAllPage(@RequestParam(value = "page",defaultValue = "0") Integer page
                                                                       ,@RequestParam(value = "limit",defaultValue = "10") Integer limit
                                                                       ,@RequestParam(value = "direction",defaultValue = "asc") String direction);



	@ApiOperation(value = "Pais cadastrado por id")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Pais cadastrado por id."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 404, message = "Pais não encontrado."),
		    @ApiResponse(code = 500, message = "Erro geral da Aplicação.")
		})
	public ResponseEntity<Country> findById(@ApiParam(value = "ID de Cadastro no Banco.", required = true,example = "1") @PathVariable Integer id);

}
