package com.sistema.apicr7imports.services;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sistema.apicr7imports.util.ExcelEngine;
import com.sistema.apicr7imports.data.dto.BrandDTO;
import com.sistema.apicr7imports.data.dto.request.BrandRequest;
import com.sistema.apicr7imports.data.model.Brand;
import com.sistema.apicr7imports.data.model.User;
import com.sistema.apicr7imports.exception.ForeignKeyException;
import com.sistema.apicr7imports.exception.ObjectNotFoundException;
import com.sistema.apicr7imports.mapper.DozerMapper;
import com.sistema.apicr7imports.repository.BrandRepository;


@Service
public class BrandService {

	@Autowired
	BrandRepository repository;
	
	@Autowired
	CountryService countryService;
	
	@Autowired
	ExcelEngine excel;

	public List<BrandDTO> findAll() {
		return DozerMapper.parseListObject(repository.findAll(), BrandDTO.class);
	}

	public BrandDTO findbyId(Integer id) {
		Brand brand = repository.findById(id)
				   .orElseThrow(() -> new ObjectNotFoundException("Marca não encontrada!"));
		return DozerMapper.parseObject(brand, BrandDTO.class);
	}

	public List<BrandDTO> findbyBrand(String name) {
		Optional<List<Brand>> list = repository.findByMarca(name);
		return DozerMapper.parseListObject(list.filter(l -> !l.isEmpty()).orElseThrow(() -> new ObjectNotFoundException("Marca não encontrada!")), BrandDTO.class);
	}

	public void delete(Integer id) {
		findbyId(id);
		try {
		   repository.deleteById(id);
		} catch (Exception e) {
			throw new ForeignKeyException("O Registro possui relação com outros registros e não pode ser excluido!");
		}
	}

	public BrandDTO save(BrandRequest brandRequest) {
		Brand brand = new Brand();
		brand.setBrandName(brandRequest.getBrandName());
		brand.setCountry(countryService.findbyId(brandRequest.getCountry()));
		brand.setDate(LocalDate.now());
		brand.setUser(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		return DozerMapper.parseObject(repository.save(brand), BrandDTO.class);
	}

	public BrandDTO update(Integer id, BrandRequest brandRequest) {
		Brand newBrand = DozerMapper.parseObject(findbyId(id), Brand.class);
		newBrand.setBrandName(brandRequest.getBrandName());
		newBrand.setCountry(countryService.findbyId(brandRequest.getCountry()));
		newBrand.setDate(LocalDate.now());
		newBrand.setUser(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		return DozerMapper.parseObject(repository.save(newBrand), BrandDTO.class);
	}
	
	public byte[] getExcel() throws IOException {
		String[] titles = new String[]{"ID","Marca","Pais","Data","Usuário"};
		return excel.generateExcel((ArrayList<?>) repository.findAll(), "Marcas", titles);
	}
	
	public Page<BrandDTO> findAllPage(Pageable pageable) {
		return repository.findAll(pageable).map(brand -> DozerMapper.parseObject(brand, BrandDTO.class));
	}
	
	public Page<BrandDTO> findbyBrandPageable(String name,Pageable pageable) {
		Optional<Page<Brand>> list = repository.findByMarcaPageable(name,pageable);
		return list.filter(l -> !l.isEmpty()).orElseThrow(() -> new ObjectNotFoundException("Marca não encontrada!"))
				   .map(brand -> DozerMapper.parseObject(brand, BrandDTO.class));
	}
}
