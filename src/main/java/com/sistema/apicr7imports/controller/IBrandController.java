package com.sistema.apicr7imports.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.sistema.apicr7imports.data.dto.BrandDTO;
import com.sistema.apicr7imports.data.dto.request.BrandRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Marca")
public interface IBrandController {
	
	@Operation(description = "Trazer todos os tipos de marcas cadastradas")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "Todos os tipos de marcas cadastradas."),
		    @ApiResponse(responseCode = "403", description = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(responseCode = "500", description = "Erro geral da Aplicação.")
		})
	ResponseEntity<List<BrandDTO>> findAll();
	
	@Operation(description = "Todas as marcas cadastradas de forma paginada")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "Todos as marcas cadastradas."),
		    @ApiResponse(responseCode = "403", description = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(responseCode = "500", description = "Erro de Servidor interno")
		})
	ResponseEntity<Page<BrandDTO>> findAllPage(@Parameter(description = "Página.", required = false ,example = "1")
			                                   @RequestParam(value = "page", defaultValue = "0") Integer page,
			                                   @Parameter(description = "limite.", required = false, example = "10")
			                                   @RequestParam(value = "limit", defaultValue = "10") Integer limit);
	
	@Operation(description = "Gera Excel das Marcas")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "Excel de marcas de roupa."),
		    @ApiResponse(responseCode = "403", description = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(responseCode = "500", description = "Erro geral da Aplicação.")
		})
	ResponseEntity<byte[]> getExcel () throws IOException;
	
	@Operation(description = "Trazer marca cadastrada por id")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "Marca cadastrada por id."),
		    @ApiResponse(responseCode = "403", description = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(responseCode = "404", description = "Marca não encontrada."),
		    @ApiResponse(responseCode = "500", description = "Erro geral da Aplicação.")
		})
	ResponseEntity<BrandDTO> findById(@Parameter(description = "ID de Cadastro no Banco", required = true, example = "1") 
	                                  @PathVariable Integer id);

	@Operation(description = "Todos os produtos cadastrados por nome de forma paginada")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "Todos os produtos cadastrados por nome."),
		    @ApiResponse(responseCode = "403", description = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(responseCode = "500", description = "Erro de Servidor interno")
		})
	ResponseEntity<Page<BrandDTO>> findByBrandPage(@Parameter(description = "Nome da marca.", required = true, example = "teste") 
												   @RequestParam(value = "brand") String brand,
												   @Parameter(description = "Página.", required = false, example = "1")
			                                       @RequestParam(value = "page", defaultValue = "0") Integer page,
			                                       @Parameter(description = "limite.", required = false, example = "10")
			                                       @RequestParam(value = "limit", defaultValue = "10") Integer limit);
	
	@Operation(description = "Trazer tipos de marcas cadastradas por nome")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "Marcas de roupas."),
		    @ApiResponse(responseCode = "403", description = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(responseCode = "404", description = "Marca não encontrada."),
		    @ApiResponse(responseCode = "500", description = "Erro geral da Aplicação.")
		})
	ResponseEntity<List<BrandDTO>> findByBrand(@Parameter(description = "Nome da Marca.", required = true, example = "Nike") String brand);
	
	@Operation(description = "Insere uma marca")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "201", description = "Inserção de Marca."),
		    @ApiResponse(responseCode = "403", description = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(responseCode = "500", description = "Erro geral da Aplicação.")
		})
	ResponseEntity<BrandDTO> save(@RequestBody BrandRequest brandRequest);
	
	@Operation(description = "Atualiza uma Marca")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "204", description = "Atualização de Categoria de roupa."),
		    @ApiResponse(responseCode = "403", description = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(responseCode = "404", description = "Marca não encontrada."),
		    @ApiResponse(responseCode = "500", description = "Erro geral da Aplicação.")
		})
	ResponseEntity<BrandDTO> update(@Parameter(description = "ID de Cadastro no Banco", required = true, example = "1") @PathVariable Integer id
			                       ,@RequestBody BrandRequest brand);
	
	@Operation(description = "Deleta uma marca")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "204", description = "Deleção de marca de roupa."),
		    @ApiResponse(responseCode = "403", description = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(responseCode = "404", description = "Marca não encontrada."),
		    @ApiResponse(responseCode = "409", description = "Conflito - Relação com outros registros."),
		    @ApiResponse(responseCode = "500", description = "Erro geral da Aplicação.")
		})
	ResponseEntity<Void> delete(@Parameter(description = "ID de Cadastro no Banco", required = true, example = "1") @PathVariable Integer id);
}
