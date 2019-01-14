package com.team.pharmaC.data;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.team.pharmaC.PharmacBranch;

public interface PharmacyBranchRepository  extends CrudRepository<PharmacBranch,Long>{
	//@Query("SELECT DISTINCT pharmacy FROM PharmacBranch c where c.city like  ?1% ")
	//List<PharmacBranch> searchPharmacyByLocation(String pharmacyName);
}
