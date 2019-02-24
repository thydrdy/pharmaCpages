package com.team.pharmaC.main.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.team.pharmaC.main.security.MainEmployee;

public interface MainEmployeeRepository  extends CrudRepository<MainEmployee, Long>{
	MainEmployee findByUsername(String username);
	
	@Modifying
	@Transactional
	@Query("update MainEmployee M Set M.pharmacy_id=?1 where M.id = ?2")
	void updatePharmacyIdById(String pharmacy_id,long id);
	
	@Modifying
	@Transactional
	@Query("update MainEmployee M Set M.license_id=?1,M.firstname=?2,M.lastname=?3,M.profileImage=?4  where M.id = ?5")
	void updateProfile(String license_id,String firstname,String lastname,String profileImag,long id);

}
