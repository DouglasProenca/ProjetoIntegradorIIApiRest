package com.sistema.apicr7imports.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.apicr7imports.domain.Product;
import com.sistema.apicr7imports.exception.ObjectNotFoundException;
import com.sistema.apicr7imports.repository.ProductRepository;
import com.sistema.apicr7imports.services.excel.Excel;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	private Excel excel = new Excel();
	
	public List<Product> findAll() {
		return productRepository.findAll();
	}
	
	public Product findbyId(Long id) {
		return productRepository.findById(Long.valueOf(id))
				.orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado!"));
	}

	public List<Product> findbyBrand(String text) {
		List<Product> brandList = productRepository.findByNome(text);
		if (brandList.isEmpty()) {
			throw new ObjectNotFoundException("Produto não encontrado!");
		}
		return brandList;
	}

	public void delete(Long id) {
		this.findbyId(id);
		productRepository.deleteById(id);
	}

	public Product insert(Product obj) {
		productRepository.save(obj);
		return obj;
	}

	public Product update(Product obj) {
		Product newObj = findbyId(Long.valueOf(obj.getId()));
		updateData(newObj, obj);
		return productRepository.save(newObj);
	}

	private void updateData(Product newBrand, Product brand) {
		//newBrand.setMarca(brand.getMarca());
		//newBrand.setCountry(brand.getCountry());
		newBrand.setData(brand.getData());
		newBrand.setUser(brand.getUser());
	}
	
	public byte[] createExcel() throws IOException {
		String[] titulos = new String[]{"ID","Nome","Marca","Valor","Quantidade","Categoria","Data","Usuário"};
		return excel.exportExcel((ArrayList<?>) findAll(), "Produtos", titulos).toByteArray();
	}
}
