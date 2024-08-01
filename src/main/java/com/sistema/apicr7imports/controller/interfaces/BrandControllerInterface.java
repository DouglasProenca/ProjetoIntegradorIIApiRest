package com.sistema.apicr7imports.controller.interfaces;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.sistema.apicr7imports.domain.Dto.BrandDTO;
import com.sistema.apicr7imports.domain.Dto.request.CreateBrandRequest;
import com.sistema.apicr7imports.domain.Dto.request.EditBrandRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Tipos de Marcas")
public interface BrandControllerInterface {
	
	@ApiOperation(value = "Trazer todos os tipos de marcas cadastradas")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Todos os tipos de marcas cadastradas."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 500, message = "Erro geral da Aplicação.")
		})
	public ResponseEntity<List<BrandDTO>> findAll();
	
	@ApiOperation(value = "Todas as marcas cadastradas de forma paginada")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Todos as marcas cadastradas."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 500, message = "Erro de Servidor interno")
		})
	public ResponseEntity<Page<BrandDTO>> findAllPage(@ApiParam(value = "Página.",required= false,example = "1")
			                                         @RequestParam(value = "page",defaultValue = "0") Integer page,
			                                         @ApiParam(value = "limite.",required=false,example = "10")
			                                         @RequestParam(value = "limit",defaultValue = "10") Integer limit);
	
	@ApiOperation(value = "Gera Excel das Marcas")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Excel de marcas de roupa."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 500, message = "Erro geral da Aplicação.")
		})
	public ResponseEntity<byte[]> getExcel () throws IOException;
	
	@ApiOperation(value = "Trazer marca cadastrada por id")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Marca cadastrada por id."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 404, message = "Marca não encontrada."),
		    @ApiResponse(code = 500, message = "Erro geral da Aplicação.")
		})
	public ResponseEntity<BrandDTO> findById(@ApiParam(value = "ID de Cadastro no Banco", required = true, example = "1") 
	                                         @PathVariable Integer id);

	@ApiOperation(value = "Todos os produtos cadastrados por nome de forma paginada")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Todos os produtos cadastrados por nome."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 500, message = "Erro de Servidor interno")
		})
	public ResponseEntity<Page<BrandDTO>> findByBrandPage(@ApiParam(value = "Nome da marca.", required = true,example = "teste") 
														  @RequestParam(value = "brand") String brand,
			                                              @ApiParam(value = "Página.",required= false,example = "1")
			                                              @RequestParam(value = "page",defaultValue = "0") Integer page,
			                                              @ApiParam(value = "limite.",required=false,example = "10")
			                                              @RequestParam(value = "limit",defaultValue = "10") Integer limit);
	
	@ApiOperation(value = "Trazer tipos de marcas cadastradas por nome")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Marcas de roupas."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 404, message = "Marca não encontrada."),
		    @ApiResponse(code = 500, message = "Erro geral da Aplicação.")
		})
	public ResponseEntity<List<BrandDTO>> findByBrand(@ApiParam(value = "Nome da Marca.", required = true,example = "teste") @RequestParam(value = "marca") String brand);
	
	@ApiOperation(value = "Insere uma marca")
	@ApiResponses(value = {
		    @ApiResponse(code = 201, message = "Inserção de Marca."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 500, message = "Erro geral da Aplicação.")
		})
	public ResponseEntity<BrandDTO> save(@RequestBody CreateBrandRequest brandRequest);
	
	@ApiOperation(value = "Atualiza uma Marca")
	@ApiResponses(value = {
		    @ApiResponse(code = 204, message = "Atualização de Categoria de roupa."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 404, message = "Marca não encontrada."),
		    @ApiResponse(code = 500, message = "Erro geral da Aplicação.")
		})
	public ResponseEntity<BrandDTO> update(@RequestBody EditBrandRequest brand);
	
	@ApiOperation(value = "Deleta uma marca")
	@ApiResponses(value = {
		    @ApiResponse(code = 204, message = "Deleção de marca de roupa."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 404, message = "Marca não encontrada."),
		    @ApiResponse(code = 500, message = "Erro geral da Aplicação.")
		})
	public ResponseEntity<Void> delete(@ApiParam(value = "ID de Cadastro no Banco", required = true, example = "1") @PathVariable Integer id);
}
