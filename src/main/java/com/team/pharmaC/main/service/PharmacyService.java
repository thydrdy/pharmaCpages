package com.team.pharmaC.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.team.pharmaC.main.domains.Pharmacy;

public interface PharmacyService {
  
	Optional<Pharmacy> findById(Long id);
	  
	Pharmacy save(Pharmacy entity);
	  
	Page<Pharmacy> findAll(Pageable pageable);
	  
	public Iterable<Pharmacy> saveAll(Iterable<Pharmacy> entities);

	boolean existsById(Long id);
	
	Iterable<Pharmacy> findAll();
	
	Iterable<Pharmacy> findAllById(Iterable<Long> ids);
	
	long count();
	
	void deleteById(Long id);
	
	void delete(Pharmacy entities);
	
	void deleteAll(Iterable<Pharmacy> entities);
	
	void deleteAll();
	
	List<Pharmacy> searchPharmacyByName(String pharmacyName);
}
