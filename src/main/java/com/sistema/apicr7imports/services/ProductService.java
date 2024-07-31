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
import com.sistema.apicr7imports.domain.Dto.ProductDTO;
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

	public Product save(Product obj) {
		return repository.save(obj);
	}

	public Product update(Product product) {
		Product newProduct = DozerMapper.parseObject(findbyId(product.getId()), Product.class);
		newProduct.setNome(product.getNome());
		newProduct.setQuantidade(product.getQuantidade());
		newProduct.setBrand(product.getBrand());
		newProduct.setCategory(product.getCategory());
		newProduct.setAtivo(product.getAtivo());
		newProduct.setValor(product.getValor());
		newProduct.setData(product.getData());
		newProduct.setUser(product.getUser());
		
		return repository.save(newProduct);
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
