package com.sistema.apicr7imports.controller.interfaces;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.sistema.apicr7imports.domain.Product;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Produtos")
public interface ProductControllerInterface {

	@ApiOperation(value = "Trazer todos os produtos cadastrados")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Todos os Produtos."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 500, message = "Erro geral da Aplicação.")
		})
	public ResponseEntity<List<Product>> findAll();
	
	@ApiOperation(value = "Todos os produtos cadastrados de forma paginada")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Todos os produtos cadastrados."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 500, message = "Erro de Servidor interno")
		})
	public ResponseEntity<Page<Product>> findAllPage(@RequestParam(value = "page",defaultValue = "0") Integer page
			                                        ,@RequestParam(value = "limit",defaultValue = "10") Integer limit);
	
	@ApiOperation(value = "Gera Excel dos produtos")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Excel de produtos."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 500, message = "Erro geral da Aplicação.")
		})
	public ResponseEntity<byte[]> getExcel () throws IOException;
	
	@ApiOperation(value = "Trazer produto por id")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Produto cadastrado por id."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 404, message = "Produto não encontrado."),
		    @ApiResponse(code = 500, message = "Erro geral da Aplicação.")
		})
	public ResponseEntity<Product> findById(@ApiParam(value = "ID de Cadastro no Banco", required = true, example = "1") @PathVariable Integer id);

	@ApiOperation(value = "Trazer tipos de produtos cadastrados por nome")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Produtos."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 404, message = "Produto não encontrado."),
		    @ApiResponse(code = 500, message = "Erro geral da Aplicação.")
		})
	public ResponseEntity<List<Product>> findByProduct(@ApiParam(value = "Nome do Produto.", required = true,example = "teste") @RequestParam(value = "marca") String product);
	
	@ApiOperation(value = "Insere um produto")
	@ApiResponses(value = {
		    @ApiResponse(code = 201, message = "Inserção de Produto."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 500, message = "Erro geral da Aplicação.")
		})
	public ResponseEntity<Void> insert(@RequestBody Product product);
	
	@ApiOperation(value = "Atualiza um produto")
	@ApiResponses(value = {
		    @ApiResponse(code = 204, message = "Atualização de Produto."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 404, message = "Produto não encontrado."),
		    @ApiResponse(code = 500, message = "Erro geral da Aplicação.")
		})
	public ResponseEntity<Void> update(@RequestBody Product product);
	
	@ApiOperation(value = "Deleta um produto")
	@ApiResponses(value = {
		    @ApiResponse(code = 204, message = "Deleção de porduto."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 404, message = "produto não encontrado."),
		    @ApiResponse(code = 500, message = "Erro geral da Aplicação.")
		})
	public ResponseEntity<Void> delete(@ApiParam(value = "ID de Cadastro no Banco", required = true, example = "1") @PathVariable Integer id);
}
