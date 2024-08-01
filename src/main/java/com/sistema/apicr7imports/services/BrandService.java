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
import com.sistema.apicr7imports.domain.Country;
import com.sistema.apicr7imports.domain.User;
import com.sistema.apicr7imports.domain.Dto.BrandDTO;
import com.sistema.apicr7imports.domain.Dto.request.CreateBrandRequest;
import com.sistema.apicr7imports.domain.Dto.request.EditBrandRequest;
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

	public BrandDTO save(CreateBrandRequest brandRequest) {
		Brand brand = new Brand();
		brand.setMarca(brandRequest.getMarca());
		brand.setCountry(new Country(brandRequest.getCountry()));
		brand.setData(Date.from(brandRequest.getData().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		brand.setUser(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		return DozerMapper.parseObject(repository.save(brand),BrandDTO.class);
	}

	public BrandDTO update(EditBrandRequest brandRequest) {
		Brand newBrand = DozerMapper.parseObject(findbyId(brandRequest.getId()),Brand.class);
		newBrand.setMarca(brandRequest.getMarca());
		newBrand.setCountry(new Country(brandRequest.getCountry()));
		newBrand.setData(Date.from(brandRequest.getData().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		newBrand.setUser(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		return DozerMapper.parseObject(repository.save(newBrand),BrandDTO.class);
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
