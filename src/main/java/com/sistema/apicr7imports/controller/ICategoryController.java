package com.sistema.apicr7imports.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.sistema.apicr7imports.data.dto.CategoryDTO;
import com.sistema.apicr7imports.data.dto.request.CategoryRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Categoria") 
public interface ICategoryController {

	@Operation(description = "Trazer todos os tipos de roupas cadastrados")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "Todos os tipos de roupas cadastradas."),
		    @ApiResponse(responseCode = "403", description = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(responseCode = "500", description = "Erro geral da Aplicação.")
		})
	ResponseEntity<List<CategoryDTO>> findAll();
	
	@Operation(description = "Todos os tipos de roupas cadastrados por categoria de forma paginada")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "Todos os tipos de roupas cadastradas por categoria."),
		    @ApiResponse(responseCode = "403", description = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(responseCode = "500", description = "Erro de Servidor interno")
		})
	ResponseEntity<Page<CategoryDTO>> findAllPage(@Parameter(description = "Página.", required = false, example = "1")
			                                      @RequestParam(defaultValue = "0") Integer page,
			                                      @Parameter(description = "limite.", required = false, example = "10")
			                                      @RequestParam(defaultValue = "10") Integer limit);
	
	@Operation(description = "Trazer tipos de roupas cadastrados por id")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "Categoria cadastrada por id."),
		    @ApiResponse(responseCode = "403", description = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(responseCode = "404", description = "Categoria não encontrada."),
		    @ApiResponse(responseCode = "500", description = "Erro geral da Aplicação.")
		})
	ResponseEntity<CategoryDTO> findById(@Parameter(description = "ID de Cadastro no Banco.", required = true, example = "1") @PathVariable Integer id);
	
	@Operation(description = "Todos os tipos de roupas cadastradas por categoria de forma paginada")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "Todos os tipos de roupas cadastradas por categoria."),
		    @ApiResponse(responseCode = "403", description = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(responseCode = "500", description = "Erro de Servidor interno")
		})
	ResponseEntity<Page<CategoryDTO>> findByCategoryPage(@Parameter(description = "Nome da categoria.", required = true, example = "teste") 
														 @RequestParam String category,
														 @Parameter(description = "Página.", required = false, example = "1")
			                                             @RequestParam(defaultValue = "0") Integer page,
			                                             @Parameter(description = "limite.", required = false, example = "10")
			                                             @RequestParam(defaultValue = "10") Integer limit);
	
	@Operation(description = "Trazer tipos de roupas cadastrado por Categoria")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "Categorias de roupas."),
		    @ApiResponse(responseCode = "403", description = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(responseCode = "404", description = "Categoria não encontrada."),
		    @ApiResponse(responseCode = "500", description = "Erro geral da Aplicação.")
		})
	ResponseEntity<List<CategoryDTO>> findByCategoria(@Parameter(description = "Nome da Categoria.", required = true, example = "teste") @RequestParam String categoria);
	
	@Operation(description = "Gera Excel das categorias de roupas")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "Excel de Categoria de roupa."),
		    @ApiResponse(responseCode = "403", description = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(responseCode = "500", description = "Erro geral da Aplicação.")
		})
	ResponseEntity<byte[]> getExcel () throws IOException;
	
	@Operation(description = "Insere uma Categoria")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "201", description = "Inserção de Categoria de roupa."),
		    @ApiResponse(responseCode = "403", description = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(responseCode = "500", description = "Erro geral da Aplicação.")
		})
	ResponseEntity<CategoryDTO> save(@RequestBody CategoryRequest categoryRequest);
	
	@Operation(description = "Atualiza uma Categoria")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "204", description = "Atualização de Categoria de roupa."),
		    @ApiResponse(responseCode = "403", description = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(responseCode = "404", description = "Categoria não encontrada."),
		    @ApiResponse(responseCode = "500", description = "Erro geral da Aplicação.")
		})
	ResponseEntity<CategoryDTO> update(@Parameter(description = "ID de Cadastro no Banco.", required = true, example = "1") @PathVariable Integer id,
			                           @RequestBody CategoryRequest category);
	
	@Operation(description = "Deleta uma Categoria")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "204", description = "Deleção de Categoria de roupa."),
		    @ApiResponse(responseCode = "403", description = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(responseCode = "404", description = "Categoria não encontrada."),
		    @ApiResponse(responseCode = "409", description = "Conflito - Relação com outros registros."),
		    @ApiResponse(responseCode = "500", description = "Erro geral da Aplicação.")
		})
	ResponseEntity<Void> delete(@Parameter(description = "ID de Cadastro no Banco.", required = true, example = "1") @PathVariable Integer id);

}
