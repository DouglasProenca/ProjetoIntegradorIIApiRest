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
import com.sistema.apicr7imports.domain.VO.BrandVO;
import com.sistema.apicr7imports.exception.ObjectNotFoundException;
import com.sistema.apicr7imports.mapper.DozerMapper;
import com.sistema.apicr7imports.repository.BrandRepository;


@Service
public class BrandService {

	@Autowired
	BrandRepository brandRepository;
	
	@Autowired
	Excel excel;

	public List<BrandVO> findAll() {
		return DozerMapper.parseListObject(brandRepository.findAll(), BrandVO.class);
	}

	public Brand findbyId(Integer id) {
		return brandRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Marca não encontrada!"));
	}

	public List<BrandVO> findbyBrand(String name) {
		List<Brand> list = brandRepository.findByMarca(name);
		
		if (list.isEmpty()) 
			throw new ObjectNotFoundException("Marca não encontrada!");
		
		return DozerMapper.parseListObject(list, BrandVO.class);
	}

	public void delete(Integer id) {
		findbyId(id);
		brandRepository.deleteById(id);
	}

	public Brand save(Brand brand) {
		return brandRepository.save(brand);
	}

	public Brand update(Brand brand) {
		Brand newBrand = findbyId(brand.getId());
		newBrand.setMarca(brand.getMarca());
		newBrand.setCountry(brand.getCountry());
		newBrand.setData(brand.getData());
		newBrand.setUser(brand.getUser());
		
		return brandRepository.save(newBrand);
	}
	
	public byte[] getExcel() throws IOException {
		String[] titulos = new String[]{"ID","Marca","Pais","Data","Usuário"};
		return excel.exportExcel((ArrayList<?>) findAll(), "Marcas", titulos).toByteArray();
	}
	
	public Page<Brand> findAllPage(Pageable pageable) {
		return brandRepository.findAll(pageable);
	}
	
	public Page<Brand> findbyBrandPageable(String name,Pageable pageable) {
		Page<Brand> list = brandRepository.findByMarcaPageable(name,pageable);
		
		if (list.isEmpty()) 
			throw new ObjectNotFoundException("Marca não encontrada!");

		return list;
	}
}
