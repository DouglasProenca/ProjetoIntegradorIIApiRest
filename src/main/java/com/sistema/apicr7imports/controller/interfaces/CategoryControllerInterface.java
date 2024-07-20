package com.sistema.apicr7imports.controller.interfaces;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.sistema.apicr7imports.domain.Category;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Tipos de Roupas") 
public interface CategoryControllerInterface {

	@ApiOperation(value = "Trazer todos os tipos de roupas cadastrados")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Todos os tipos de roupas cadastradas."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 500, message = "Erro geral da Aplicação.")
		})
	public ResponseEntity<List<Category>> findAll();
	
	@ApiOperation(value = "Trazer tipos de roupas cadastrados por id")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Categoria cadastrada por id."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 404, message = "Categoria não encontrada."),
		    @ApiResponse(code = 500, message = "Erro geral da Aplicação.")
		})
	public ResponseEntity<Category> findById(@ApiParam(value = "ID de Cadastro no Banco.", required = true,example = "1") @PathVariable Integer id);
	
	@ApiOperation(value = "Trazer tipos de roupas cadastrado por Categoria")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Categorias de roupas."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 404, message = "Categoria não encontrada."),
		    @ApiResponse(code = 500, message = "Erro geral da Aplicação.")
		})
	public ResponseEntity<List<Category>> findByCategoria(@ApiParam(value = "Nome da Categoria.", required = true,example = "teste") @RequestParam(value= "categoria") String categoria);
	
	@ApiOperation(value = "Gera Excel das categorias de roupas")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Excel de Categoria de roupa."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 500, message = "Erro geral da Aplicação.")
		})
	public ResponseEntity<byte[]> getExcel () throws IOException;
	
	@ApiOperation(value = "Insere uma Categoria")
	@ApiResponses(value = {
		    @ApiResponse(code = 201, message = "Inserção de Categoria de roupa."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 500, message = "Erro geral da Aplicação.")
		})
	public ResponseEntity<Void> insert(@RequestBody Category category);
	
	@ApiOperation(value = "Atualiza uma Categoria")
	@ApiResponses(value = {
		    @ApiResponse(code = 204, message = "Atualização de Categoria de roupa."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 404, message = "Categoria não encontrada."),
		    @ApiResponse(code = 500, message = "Erro geral da Aplicação.")
		})
	public ResponseEntity<Void> update(@RequestBody Category category);
	
	@ApiOperation(value = "Deleta uma Categoria")
	@ApiResponses(value = {
		    @ApiResponse(code = 204, message = "Deleção de Categoria de roupa."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 404, message = "Categoria não encontrada."),
		    @ApiResponse(code = 500, message = "Erro geral da Aplicação.")
		})
	public ResponseEntity<Void> delete(@ApiParam(value = "ID de Cadastro no Banco.", required = true,example = "1") @PathVariable Integer id);

}
