package com.team.pharmaC.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.team.pharmaC.main.domains.Pharmacy;

public interface PharmacyRepository extends CrudRepository<Pharmacy,Long>{
	@Query("SELECT DISTINCT c FROM Pharmacy c where c.pharmacyName like  ?1% ")
	List<Pharmacy> searchPharmacyByName(String pharmacyName);
	
	
}
