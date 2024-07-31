package com.sistema.apicr7imports.controller.interfaces;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.sistema.apicr7imports.domain.Category;
import com.sistema.apicr7imports.domain.Dto.CategoryDTO;

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
	public ResponseEntity<List<CategoryDTO>> findAll();
	
	@ApiOperation(value = "Todos os tipos de roupas cadastrados por categoria de forma paginada")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Todos os tipos de roupas cadastradas por categoria."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 500, message = "Erro de Servidor interno")
		})
	public ResponseEntity<Page<CategoryDTO>> findAllPage(@ApiParam(value = "Página.",required= false,example = "1")
			                                         @RequestParam(value = "page",defaultValue = "0") Integer page,
			                                         @ApiParam(value = "limite.",required=false,example = "10")
			                                         @RequestParam(value = "limit",defaultValue = "10") Integer limit);
	
	@ApiOperation(value = "Trazer tipos de roupas cadastrados por id")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Categoria cadastrada por id."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 404, message = "Categoria não encontrada."),
		    @ApiResponse(code = 500, message = "Erro geral da Aplicação.")
		})
	public ResponseEntity<CategoryDTO> findById(@ApiParam(value = "ID de Cadastro no Banco.", required = true,example = "1") @PathVariable Integer id);
	
	@ApiOperation(value = "Todos os tipos de roupas cadastradas por categoria de forma paginada")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Todos os tipos de roupas cadastradas por categoria."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 500, message = "Erro de Servidor interno")
		})
	public ResponseEntity<Page<CategoryDTO>> findByCategoryPage(@ApiParam(value = "Nome da categoria.", required = true,example = "teste") 
														   @RequestParam(value = "category") String category,
			                                               @ApiParam(value = "Página.",required= false,example = "1")
			                                               @RequestParam(value = "page",defaultValue = "0") Integer page,
			                                               @ApiParam(value = "limite.",required=false,example = "10")
			                                               @RequestParam(value = "limit",defaultValue = "10") Integer limit);
	
	@ApiOperation(value = "Trazer tipos de roupas cadastrado por Categoria")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Categorias de roupas."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 404, message = "Categoria não encontrada."),
		    @ApiResponse(code = 500, message = "Erro geral da Aplicação.")
		})
	public ResponseEntity<List<CategoryDTO>> findByCategoria(@ApiParam(value = "Nome da Categoria.", required = true,example = "teste") @RequestParam(value= "categoria") String categoria);
	
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
	public ResponseEntity<Category> save(@RequestBody Category category);
	
	@ApiOperation(value = "Atualiza uma Categoria")
	@ApiResponses(value = {
		    @ApiResponse(code = 204, message = "Atualização de Categoria de roupa."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 404, message = "Categoria não encontrada."),
		    @ApiResponse(code = 500, message = "Erro geral da Aplicação.")
		})
	public ResponseEntity<Category> update(@RequestBody Category category);
	
	@ApiOperation(value = "Deleta uma Categoria")
	@ApiResponses(value = {
		    @ApiResponse(code = 204, message = "Deleção de Categoria de roupa."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 404, message = "Categoria não encontrada."),
		    @ApiResponse(code = 500, message = "Erro geral da Aplicação.")
		})
	public ResponseEntity<Void> delete(@ApiParam(value = "ID de Cadastro no Banco.", required = true,example = "1") @PathVariable Integer id);

}
