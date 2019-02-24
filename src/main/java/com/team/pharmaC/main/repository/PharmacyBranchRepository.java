package com.team.pharmaC.main.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.team.pharmaC.main.domains.PharmacBranch;

public interface PharmacyBranchRepository  extends CrudRepository<PharmacBranch,Long>{
	@Query("SELECT DISTINCT pharmabr FROM PharmacBranch pharmabr where pharmabr.city like  ?1% or pharmabr.subcity like  ?1%  or pharmabr.kebele like  ?1% ")
	List<PharmacBranch> searchPharmacyByLocation(String location);
	
	@Modifying
	@Transactional
	@Query("update PharmacBranch M Set M.BranchName=?1,M.city=?2,M.subcity=?3,M.kebele=?4, M.branch_office_phone_no=?5    where M.branch_no = ?6")
	void updateProfile(String BranchName,String city,String subcity,String kebele,String branch_office_phone_no,long branch_no);
	
	
	@Modifying
	@Transactional
	@Query("update PharmacBranch P Set P.pharmacy_license_id=?1 where P.branch_no = ?2")
	void updatePharmacyIdById(long pharmacy_id,long id);
}
