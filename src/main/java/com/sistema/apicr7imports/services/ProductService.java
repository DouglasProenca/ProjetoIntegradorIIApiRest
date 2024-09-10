package com.sistema.apicr7imports.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sistema.apicr7imports.util.ExcelEngine;
import com.sistema.apicr7imports.data.dto.ProductDTO;
import com.sistema.apicr7imports.data.dto.request.ProductRequest;
import com.sistema.apicr7imports.data.model.Brand;
import com.sistema.apicr7imports.data.model.Category;
import com.sistema.apicr7imports.data.model.Product;
import com.sistema.apicr7imports.data.model.ProductImage;
import com.sistema.apicr7imports.data.model.User;
import com.sistema.apicr7imports.exception.ForeignKeyException;
import com.sistema.apicr7imports.exception.ObjectNotFoundException;
import com.sistema.apicr7imports.mapper.DozerMapper;
import com.sistema.apicr7imports.repository.ProductImageRepository;
import com.sistema.apicr7imports.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository repository;
	
	@Autowired
	BrandService brandService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired 
	ProductImageRepository imageRepository;
	
	@Autowired
	ExcelEngine excel;
	
	public List<ProductDTO> findAll() {
		return DozerMapper.parseListObject(repository.findAll(), ProductDTO.class);
	}
	
	public ProductDTO findbyId(Integer id) {
		Product product = repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado!"));
		return DozerMapper.parseObject(product, ProductDTO.class);
	}

	public List<ProductDTO> findbyProduct(String name) {
		Optional<List<Product>> list = repository.findByNome(name);
		return DozerMapper.parseListObject(list.filter(l -> !l.isEmpty()).orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado!")), ProductDTO.class);
	}

	public void delete(Integer id) {
		findbyId(id); 
		try {
			repository.deleteById(id);
		}catch (Exception e) {
			throw new ForeignKeyException("O Registro possui relação com outros registros e não pode ser excluido");
		}
	}

	public ProductDTO save(ProductRequest productRequest) {
		Product product = new Product();
		product.setProductName(productRequest.getProductName());
		product.setAmount(productRequest.getAmount());
		product.setPrice(productRequest.getPrice());
		product.setEnabled(productRequest.getEnabled());
		product.setBrand(DozerMapper.parseObject(brandService.findbyId(productRequest.getBrand()),Brand.class));
		product.setCategory(DozerMapper.parseObject(categoryService.findbyId(productRequest.getCategory()),Category.class));
		product.setDate(productRequest.getDate());
		product.setUser(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		return DozerMapper.parseObject(repository.save(product),ProductDTO.class);
	}

	public ProductDTO update(Integer id, ProductRequest productRequest) {
		Product newProduct = DozerMapper.parseObject(findbyId(id), Product.class);
		newProduct.setProductName(productRequest.getProductName());
		newProduct.setAmount(productRequest.getAmount());
		newProduct.setBrand(DozerMapper.parseObject(brandService.findbyId(productRequest.getBrand()),Brand.class));
		newProduct.setCategory(DozerMapper.parseObject(categoryService.findbyId(productRequest.getCategory()),Category.class));
		newProduct.setEnabled(productRequest.getEnabled());
		newProduct.setPrice(productRequest.getPrice());
		newProduct.setDate(productRequest.getDate());
		newProduct.setUser(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		return DozerMapper.parseObject(repository.save(newProduct),ProductDTO.class);
	}
	
	public byte[] getExcel() throws IOException {
		String[] titles = new String[]{"ID","Nome","Marca","Valor","Quantidade","Categoria","Data","Usuário"};
		return excel.generateExcel((ArrayList<?>) findAll(), "Produtos", titles);
	}
	
	public List<byte[]> getImage(Integer id) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		Product product = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado!"));
		Optional<List<byte[]>> listImages = Optional.empty(); 
		
	    for (ProductImage productImage : product.getImages()) {
	    	ImageIO.write(ImageIO.read(new ByteArrayInputStream(productImage.getImage())), "png", byteArrayOutputStream);
	    	listImages.get().add(byteArrayOutputStream.toByteArray());
		}	
	    
		return listImages.orElseThrow(() -> new ObjectNotFoundException("imagens não encontradas!"));
	}
	
	public Page<ProductDTO> findAllPage(Pageable pageable) {
		return repository.findAll(pageable).map(product -> DozerMapper.parseObject(product, ProductDTO.class));
	}
	
	public Page<ProductDTO> findbyProductPageable(String name,Pageable pageable) {
		Optional<Page<Product>> list = repository.findByNomePageable(name,pageable);
		return list.filter(l -> !l.isEmpty()).orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado!"))
		           .map(product -> DozerMapper.parseObject(product, ProductDTO.class));
	}
	
}
