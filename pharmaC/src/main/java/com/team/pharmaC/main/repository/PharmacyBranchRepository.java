package com.team.pharmaC.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.team.pharmaC.main.domains.PharmacBranch;

public interface PharmacyBranchRepository  extends CrudRepository<PharmacBranch,Long>{
	@Query("SELECT DISTINCT pharmabr FROM PharmacBranch pharmabr where pharmabr.city like  ?1% or pharmabr.subcity like  ?1%  or pharmabr.kebele like  ?1% ")
	List<PharmacBranch> searchPharmacyByLocation(String location);
}
