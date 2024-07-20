package com.sistema.apicr7imports.controller.interfaces;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


import com.sistema.apicr7imports.domain.Brand;
import com.sistema.apicr7imports.domain.VO.BrandVO;

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
	public ResponseEntity<List<BrandVO>> findAll();
	
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
	public ResponseEntity<Brand> findById(@ApiParam(value = "ID de Cadastro no Banco", required = true, example = "1") @PathVariable Integer id);

	@ApiOperation(value = "Trazer tipos de marcas cadastradas por nome")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Marcas de roupas."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 404, message = "Marca não encontrada."),
		    @ApiResponse(code = 500, message = "Erro geral da Aplicação.")
		})
	public ResponseEntity<List<BrandVO>> findByBrand(@ApiParam(value = "Nome da Marca.", required = true,example = "teste") @RequestParam(value = "marca") String brand);
	
	@ApiOperation(value = "Insere uma marca")
	@ApiResponses(value = {
		    @ApiResponse(code = 201, message = "Inserção de Marca."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 500, message = "Erro geral da Aplicação.")
		})
	public ResponseEntity<Brand> insert(@RequestBody Brand brand);
	
	@ApiOperation(value = "Atualiza uma Marca")
	@ApiResponses(value = {
		    @ApiResponse(code = 204, message = "Atualização de Categoria de roupa."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 404, message = "Marca não encontrada."),
		    @ApiResponse(code = 500, message = "Erro geral da Aplicação.")
		})
	public ResponseEntity<Brand> update(@RequestBody Brand brand);
	
	@ApiOperation(value = "Deleta uma marca")
	@ApiResponses(value = {
		    @ApiResponse(code = 204, message = "Deleção de marca de roupa."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 404, message = "Marca não encontrada."),
		    @ApiResponse(code = 500, message = "Erro geral da Aplicação.")
		})
	public ResponseEntity<Void> delete(@ApiParam(value = "ID de Cadastro no Banco", required = true, example = "1") @PathVariable Integer id);
}
