package com.team.pharmaC.main.service;

import java.util.List;
import java.util.Optional;
import com.team.pharmaC.main.domains.*;

public interface PharmacyBranchService {
	Optional<PharmacBranch> findById(Long id);
	void updateProfile(String BranchName,String city,String subcity,String kebele,String branch_office_phone_no,long branch_no);
	void deleteById(Long id);
	PharmacBranch save(PharmacBranch entity);
	
	public Iterable<PharmacBranch> saveAll(Iterable<PharmacBranch> entities);

	boolean existsById(Long id);
	
	Iterable<PharmacBranch> findAll();

	Iterable<PharmacBranch> findAllById(Iterable<Long> ids);

	long count();
	
	void delete(PharmacBranch entities);
	
	void deleteAll(Iterable<PharmacBranch> entities);

	void deleteAll();
	
	List<PharmacBranch> searchPharmacyByLocation(String location);
	
	void updatePharmacyIdById(long pharmacy_id,long id);
}
