package com.team.pharmaC.main.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.team.pharmaC.main.domains.Drugs;


public interface DrugsRepository   extends PagingAndSortingRepository<Drugs,Long> {
	
	@Query("SELECT DISTINCT c FROM Drugs c where c.Name like ?1% ")
	List<Drugs> findDrugsByName( String Name);
	
	@Query("SELECT DISTINCT c FROM Drugs c where c.Name like ?1% ORDER BY c.amount ASC ")
	List<Drugs> findDrugsByAmount( String Name);
	
	@Query("SELECT DISTINCT c FROM Drugs c where c.Name like ?1% ORDER BY c.price ASC ")
	List<Drugs> findDrugsByPrice( String Name);
	
	@Query("SELECT DISTINCT c FROM Drugs c where c.Name like ?1% ORDER BY c.expiration_date ASC ")
	List<Drugs> findDrugsByEdate( String Name);
	
	@Modifying
	@Transactional
	@Query("update Drugs M Set M.Name=?1,M.Description=?2,M.expiration_date=?3,M.amount=?4, M.price=?5,M.production_date=?6   where M.drug_id = ?7")
	void updateProfile(String Name,String Description,String expiration_date,long amount,long price,String production_date,long drug_id);

	
	@Modifying
	@Transactional
	@Query("update Drugs D Set D.pharmacy_license_id=?1 where D.drug_id = ?2")
	void updatePharmacyIdById(long pharmacy_id,long id);
}
