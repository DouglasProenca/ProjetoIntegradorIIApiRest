package com.sistema.apicr7imports.controller.interfaces;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.sistema.apicr7imports.data.dto.ProductDTO;
import com.sistema.apicr7imports.data.dto.request.CreateProductRequest;
import com.sistema.apicr7imports.data.dto.request.EditProductRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Produtos")
public interface ProductControllerInterface {

	@Operation(description = "Trazer todos os produtos cadastrados")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "Todos os Produtos."),
		    @ApiResponse(responseCode = "403", description = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(responseCode = "500", description = "Erro geral da Aplicação.")
		})
	public ResponseEntity<List<ProductDTO>> findAll();
	
	@Operation(description = "Todos os produtos cadastrados de forma paginada")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "Todos os produtos cadastrados."),
		    @ApiResponse(responseCode = "403", description = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(responseCode = "500", description = "Erro de Servidor interno")
		})
	public ResponseEntity<Page<ProductDTO>> findAllPage(@Parameter(description = "Página.", required = false, example = "1")
			                                            @RequestParam(value = "page", defaultValue = "0") Integer page,
			                                            @Parameter(description = "limite.", required = false, example = "10")
			                                            @RequestParam(value = "limit", defaultValue = "10") Integer limit);
	
	@Operation(description = "Gera Excel dos produtos")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "Excel de produtos."),
		    @ApiResponse(responseCode = "403", description = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(responseCode = "500", description = "Erro geral da Aplicação.")
		})
	public ResponseEntity<byte[]> getExcel () throws IOException;
	
	@Operation(description = "Trazer produto por id")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "Produto cadastrado por id."),
		    @ApiResponse(responseCode = "403", description = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(responseCode = "404", description = "Produto não encontrado."),
		    @ApiResponse(responseCode = "500", description = "Erro geral da Aplicação.")
		})
	public ResponseEntity<ProductDTO> findById(@Parameter(description = "ID de Cadastro no Banco", required = true, example = "1") @PathVariable Integer id);

	@Operation(description = "Todos os produtos cadastrados por nome de forma paginada")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "Todos os produtos cadastrados por nome."),
		    @ApiResponse(responseCode = "403", description = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(responseCode = "500", description = "Erro de Servidor interno")
		})
	public ResponseEntity<Page<ProductDTO>> findByProductPage(@Parameter(description = "Nome do Produto.", required = true, example = "teste") 
														      @RequestParam(value = "prouduct") String product,
														      @Parameter(description = "Página.", required = false, example = "1")
			                                                  @RequestParam(value = "page", defaultValue = "0") Integer page,
			                                                  @Parameter(description = "limite.", required = false, example = "10")
			                                                  @RequestParam(value = "limit", defaultValue = "10") Integer limit);
	
	@Operation(description = "Trazer tipos de produtos cadastrados por nome")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "Produtos."),
		    @ApiResponse(responseCode = "403", description = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(responseCode = "404", description = "Produto não encontrado."),
		    @ApiResponse(responseCode = "500", description = "Erro geral da Aplicação.")
		})
	public ResponseEntity<List<ProductDTO>> findByProduct(@Parameter(description = "Nome do Produto.", required = true, example = "teste") @RequestParam(value = "prouduct") String product);
	
	@Operation(description = "Insere um produto")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "201", description = "Inserção de Produto."),
		    @ApiResponse(responseCode = "403", description = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(responseCode = "500", description = "Erro geral da Aplicação.")
		})
	public ResponseEntity<ProductDTO> save(@RequestBody CreateProductRequest product);
	
	@Operation(description = "Atualiza um produto")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "204", description = "Atualização de Produto."),
		    @ApiResponse(responseCode = "403", description = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(responseCode = "404", description = "Produto não encontrado."),
		    @ApiResponse(responseCode = "500", description = "Erro geral da Aplicação.")
		})
	public ResponseEntity<ProductDTO> update(@RequestBody EditProductRequest product);
	
	@Operation(description = "Deleta um produto")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "204", description = "Deleção de porduto."),
		    @ApiResponse(responseCode = "403", description = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(responseCode = "404", description = "produto não encontrado."),
		    @ApiResponse(responseCode = "409", description = "Conflito - Relação com outros registros."),
		    @ApiResponse(responseCode = "500", description = "Erro geral da Aplicação.")
		})
	public ResponseEntity<Void> delete(@Parameter(description = "ID de Cadastro no Banco", required = true, example = "1") @PathVariable Integer id);
}
