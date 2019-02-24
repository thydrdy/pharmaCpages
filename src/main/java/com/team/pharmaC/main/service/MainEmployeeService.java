package com.team.pharmaC.main.service;

import com.team.pharmaC.main.domains.Drugs;
import com.team.pharmaC.main.security.MainEmployee;

public interface MainEmployeeService {
	
	void updateProfile(String license_id,String firstname,String lastname,String profileImag,long id);
	
	void updatePharmacyIdById(String pharmacy_id,long id);
	
	MainEmployee save(MainEmployee entity);
	
	public Iterable<MainEmployee> saveAll(Iterable<MainEmployee> entities);


	boolean existsById(Long id);
	
	Iterable<MainEmployee> findAll();

	Iterable<MainEmployee> findAllById(Iterable<Long> ids);

	long count();
	
	void deleteById(Long id);
	
	void delete(MainEmployee entities);
	
	void deleteAll(Iterable<MainEmployee> entities);

	void deleteAll();
}
