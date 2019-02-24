package com.team.pharmaC.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.team.pharmaC.main.domains.*;

public interface DrugsService {
	
	Optional<Drugs> findById(Long id);
	void updateProfile(String Name,String Description,String expiration_date,long amount,long price,String production_date,long drug_id);
	
	Drugs save(Drugs entity);
	
	public Iterable<Drugs> saveAll(Iterable<Drugs> entities);


	boolean existsById(Long id);
	
	Iterable<Drugs> findAll();

	Iterable<Drugs> findAllById(Iterable<Long> ids);

	long count();
	
	void deleteById(Long id);
	
	void delete(Drugs entities);
	
	void deleteAll(Iterable<Drugs> entities);

	void deleteAll();
	
	List<Drugs> findDrugsByName( String Name);
	
	List<Drugs> findDrugsByAmount( String Name);
	
	List<Drugs> findDrugsByPrice( String Name);
	
	List<Drugs> findDrugsByEdate( String Name);
	
	void updatePharmacyIdById(long pharmacy_id,long id);
	
	Page<Drugs> findAll(Pageable pageable);
}
