package com.sistema.apicr7imports.services;

import java.io.IOException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sistema.apicr7imports.component.Excel;
import com.sistema.apicr7imports.domain.Brand;
import com.sistema.apicr7imports.domain.Category;
import com.sistema.apicr7imports.domain.Product;
import com.sistema.apicr7imports.domain.User;
import com.sistema.apicr7imports.domain.Dto.ProductDTO;
import com.sistema.apicr7imports.domain.Dto.request.CreateProductRequest;
import com.sistema.apicr7imports.domain.Dto.request.EditProductRequest;
import com.sistema.apicr7imports.exception.ObjectNotFoundException;
import com.sistema.apicr7imports.mapper.DozerMapper;
import com.sistema.apicr7imports.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository repository;
	
	@Autowired
	Excel excel;
	
	public List<ProductDTO> findAll() {
		return DozerMapper.parseListObject(repository.findAll(), ProductDTO.class);
	}
	
	public ProductDTO findbyId(Integer id) {
		Product product = repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado!"));
		return DozerMapper.parseObject(product, ProductDTO.class);
	}

	public List<ProductDTO> findbyProduct(String name) {
		List<Product> list = repository.findByNome(name);
		
		if (list.isEmpty()) 
			throw new ObjectNotFoundException("Produto não encontrado!");

		return DozerMapper.parseListObject(list, ProductDTO.class);
	}

	public void delete(Integer id) {
		findbyId(id);
		repository.deleteById(id);
	}

	public ProductDTO save(CreateProductRequest productRequest) {
		Product product = new Product();
		product.setNome(productRequest.getNome());
		product.setQuantidade(productRequest.getQuantidade());
		product.setValor(product.getValor());
		product.setAtivo(productRequest.getAtivo());
		product.setBrand(new Brand(productRequest.getBrand()));
		product.setCategory(new Category(productRequest.getCategory()));
		product.setData(Date.from(productRequest.getData().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		product.setUser(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		return DozerMapper.parseObject(repository.save(product),ProductDTO.class);
	}

	public ProductDTO update(EditProductRequest productRequest) {
		Product newProduct = DozerMapper.parseObject(findbyId(productRequest.getId()), Product.class);
		newProduct.setNome(productRequest.getNome());
		newProduct.setQuantidade(productRequest.getQuantidade());
		newProduct.setBrand(new Brand(productRequest.getBrand()));
		newProduct.setCategory(new Category(productRequest.getCategory()));
		newProduct.setAtivo(productRequest.getAtivo());
		newProduct.setValor(productRequest.getValor());
		newProduct.setData(Date.from(productRequest.getData().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		newProduct.setUser(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		return DozerMapper.parseObject(repository.save(newProduct),ProductDTO.class);
	}
	
	public byte[] getExcel() throws IOException {
		String[] titulos = new String[]{"ID","Nome","Marca","Valor","Quantidade","Categoria","Data","Usuário"};
		return excel.exportExcel((ArrayList<?>) findAll(), "Produtos", titulos).toByteArray();
	}
	
	public Page<ProductDTO> findAllPage(Pageable pageable) {
		return repository.findAll(pageable).map(product -> DozerMapper.parseObject(product, ProductDTO.class));
	}
	
	public Page<ProductDTO> findbyProductPageable(String name,Pageable pageable) {
		Page<Product> list = repository.findByNomePageable(name,pageable);
		
		if (list.isEmpty()) 
			throw new ObjectNotFoundException("Produto não encontrado!");

		return list.map(product -> DozerMapper.parseObject(product, ProductDTO.class));
	}
}
