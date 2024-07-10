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

	public Brand findbyId(Long id) {
		return brandRepository.findById(Long.valueOf(id))
				.orElseThrow(() -> new ObjectNotFoundException("Marca não encontrada!"));
	}

	public List<BrandVO> findbyBrand(String text) {
		List<Brand> brandList = brandRepository.findByMarca(text);
		if (brandList.isEmpty()) {
			throw new ObjectNotFoundException("Marca não encontrada!");
		}
		return DozerMapper.parseListObject(brandList, BrandVO.class);
	}

	public void delete(Long id) {
		this.findbyId(id);
		brandRepository.deleteById(id);
	}

	public Brand insert(Brand obj) {
		brandRepository.save(obj);
		return obj;
	}

	public Brand update(Brand obj) {
		Brand newObj = findbyId(Long.valueOf(obj.getId()));
		updateData(newObj, obj);
		return brandRepository.save(newObj);
	}

	private void updateData(Brand newBrand, Brand brand) {
		newBrand.setMarca(brand.getMarca());
		newBrand.setCountry(brand.getCountry());
		newBrand.setData(brand.getData());
		newBrand.setUser(brand.getUser());
	}
	
	public byte[] createExcel() throws IOException {
		String[] titulos = new String[]{"ID","Marca","Pais","Data","Usuário"};
		return excel.exportExcel((ArrayList<?>) findAll(), "Marcas", titulos).toByteArray();
	}
}
