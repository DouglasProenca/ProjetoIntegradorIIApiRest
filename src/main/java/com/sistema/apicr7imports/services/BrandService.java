package com.sistema.apicr7imports.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	public List<BrandVO> findbyBrand(String text) {
		List<Brand> brandList = brandRepository.findByMarca(text);
		if (brandList.isEmpty()) {
			throw new ObjectNotFoundException("Marca não encontrada!");
		}
		return DozerMapper.parseListObject(brandList, BrandVO.class);
	}

	public void delete(Integer id) {
		findbyId(id);
		brandRepository.deleteById(id);
	}

	public Brand insert(Brand brand) {
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
}
