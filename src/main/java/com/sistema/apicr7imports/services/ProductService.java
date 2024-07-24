package com.sistema.apicr7imports.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sistema.apicr7imports.component.Excel;
import com.sistema.apicr7imports.domain.Product;
import com.sistema.apicr7imports.exception.ObjectNotFoundException;
import com.sistema.apicr7imports.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	Excel excel;
	
	public List<Product> findAll() {
		return productRepository.findAll();
	}
	
	public Product findbyId(Integer id) {
		return productRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado!"));
	}

	public List<Product> findbyProduct(String name) {
		List<Product> list = productRepository.findByNome(name);
		
		if (list.isEmpty()) 
			throw new ObjectNotFoundException("Produto não encontrado!");

		return list;
	}

	public void delete(Integer id) {
		findbyId(id);
		productRepository.deleteById(id);
	}

	public Product save(Product obj) {
		return productRepository.save(obj);
	}

	public Product update(Product product) {
		Product newProduct = findbyId(product.getId());
		newProduct.setNome(product.getNome());
		newProduct.setQuantidade(product.getQuantidade());
		newProduct.setBrand(product.getBrand());
		newProduct.setCategory(product.getCategory());
		newProduct.setAtivo(product.getAtivo());
		newProduct.setValor(product.getValor());
		newProduct.setData(product.getData());
		newProduct.setUser(product.getUser());
		
		return productRepository.save(newProduct);
	}
	
	public byte[] getExcel() throws IOException {
		String[] titulos = new String[]{"ID","Nome","Marca","Valor","Quantidade","Categoria","Data","Usuário"};
		return excel.exportExcel((ArrayList<?>) findAll(), "Produtos", titulos).toByteArray();
	}
	
	public Page<Product> findAllPage(Pageable pageable) {
		return productRepository.findAll(pageable);
	}
	
	public Page<Product> findbyProductPageable(String name,Pageable pageable) {
		Page<Product> list = productRepository.findByNomePageable(name,pageable);
		
		if (list.isEmpty()) 
			throw new ObjectNotFoundException("Produto não encontrado!");

		return list;
	}
}
