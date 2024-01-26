package com.sistema.apicr7imports.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.apicr7imports.domain.Brand;
import com.sistema.apicr7imports.domain.VO.BrandVO;
import com.sistema.apicr7imports.exception.ObjectNotFoundException;
import com.sistema.apicr7imports.mapper.DozerMapper;
import com.sistema.apicr7imports.repository.BrandRepository;

@Service
public class BrandService {

	@Autowired
	BrandRepository repository;
	
	public List<BrandVO> findAll() {
		return DozerMapper.parseListObject(repository.findAll(), BrandVO.class);
	}
	
	public Brand findbyId(Long id) {
		Brand brand = repository.findById(Long.valueOf(id)).orElse(null);
		if (brand == null) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
		return brand;
	}

	public List<BrandVO> findbyBrand(String text) {
		List<Brand> brandList = repository.findByMarca(text);
		if (brandList == null) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
		return DozerMapper.parseListObject(brandList, BrandVO.class);
	}

	public void delete(Long id) {
		findbyId(id);
		repository.deleteById(id);
	}
	
	public Brand insert(Brand obj) {
		repository.insert(obj);
		return obj;
	}
	
	public Brand update(Brand obj) {
		Brand newObj = findbyId(Long.valueOf(obj.getId()));
		updateData(newObj,obj);
		return repository.save(obj);
	}

	private void updateData(Brand newObj, Brand brand) {
		newObj.setMarca(brand.getMarca());
		newObj.setCountry(brand.getCountry());
		newObj.setData(brand.getData());
		newObj.setUser(brand.getUser());
	}
}
