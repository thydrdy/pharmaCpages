package com.team.pharmaC.data;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.team.pharmaC.Drugs;


public interface DrugsRepository   extends CrudRepository<Drugs,Long> {
	
	@Query("SELECT DISTINCT c FROM Drugs c where c.Name like ?1% ")
	List<Drugs> findDrugsByName( String Name);
	
	@Query("SELECT DISTINCT c FROM Drugs c where c.Name like ?1% ORDER BY 'Amount' ")
	List<Drugs> findDrugsByAmount( String Name);
}
