package com.sistema.apicr7imports.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sistema.apicr7imports.component.Excel;
import com.sistema.apicr7imports.domain.Brand;
import com.sistema.apicr7imports.domain.Dto.BrandDTO;
import com.sistema.apicr7imports.exception.ObjectNotFoundException;
import com.sistema.apicr7imports.mapper.DozerMapper;
import com.sistema.apicr7imports.repository.BrandRepository;


@Service
public class BrandService {

	@Autowired
	BrandRepository repository;
	
	@Autowired
	Excel excel;

	public List<BrandDTO> findAll() {
		return DozerMapper.parseListObject(repository.findAll(), BrandDTO.class);
	}

	public BrandDTO findbyId(Integer id) {
		Brand brand = repository.findById(id)
				   .orElseThrow(() -> new ObjectNotFoundException("Marca não encontrada!"));
		return DozerMapper.parseObject(brand, BrandDTO.class);
	}

	public List<BrandDTO> findbyBrand(String name) {
		List<Brand> list = repository.findByMarca(name);
		
		if (list.isEmpty()) 
			throw new ObjectNotFoundException("Marca não encontrada!");
		
		return DozerMapper.parseListObject(list, BrandDTO.class);
	}

	public void delete(Integer id) {
		findbyId(id);
		repository.deleteById(id);
	}

	public Brand save(Brand brand) {
		return repository.save(brand);
	}

	public Brand update(Brand brand) {
		Brand newBrand = DozerMapper.parseObject(findbyId(brand.getId()),Brand.class);
		newBrand.setMarca(brand.getMarca());
		newBrand.setCountry(brand.getCountry());
		newBrand.setData(brand.getData());
		newBrand.setUser(brand.getUser());

		return repository.save(newBrand);
	}
	
	public byte[] getExcel() throws IOException {
		String[] titulos = new String[]{"ID","Marca","Pais","Data","Usuário"};
		return excel.exportExcel((ArrayList<?>) findAll(), "Marcas", titulos).toByteArray();
	}
	
	public Page<BrandDTO> findAllPage(Pageable pageable) {
		return repository.findAll(pageable).map(brand -> DozerMapper.parseObject(brand, BrandDTO.class));
	}
	
	public Page<BrandDTO> findbyBrandPageable(String name,Pageable pageable) {
		Page<Brand> list = repository.findByMarcaPageable(name,pageable);
		
		if (list.isEmpty()) 
			throw new ObjectNotFoundException("Marca não encontrada!");

		return list.map(brand -> DozerMapper.parseObject(brand, BrandDTO.class));
	}
}
