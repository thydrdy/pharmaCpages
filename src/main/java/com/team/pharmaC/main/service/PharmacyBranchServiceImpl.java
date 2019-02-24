package com.team.pharmaC.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.pharmaC.main.domains.PharmacBranch;
import com.team.pharmaC.main.repository.PharmacyBranchRepository;
@Service
public class PharmacyBranchServiceImpl implements PharmacyBranchService {
    public PharmacyBranchRepository pharmacyBranchRepository;
    @Autowired
    public PharmacyBranchServiceImpl(PharmacyBranchRepository pharmacyBranchRepository){
    	this.pharmacyBranchRepository=pharmacyBranchRepository;
    }
	@Override
	public Optional<PharmacBranch> findById(Long id) {
		return this.pharmacyBranchRepository.findById(id);
	}

	@Override
	public void updateProfile(String BranchName, String city, String subcity, String kebele,
			String branch_office_phone_no, long branch_no) {
		this.pharmacyBranchRepository.updateProfile(BranchName, city, subcity, kebele, branch_office_phone_no, branch_no);
	}

	@Override
	public void deleteById(Long id) {
		this.pharmacyBranchRepository.deleteById(id);

	}

	@Override
	public PharmacBranch save(PharmacBranch entity) {
		return this.pharmacyBranchRepository.save(entity);
	}
	@Override
	public Iterable<PharmacBranch> saveAll(Iterable<PharmacBranch> entities) {
		return this.pharmacyBranchRepository.saveAll(entities);
	}
	@Override
	public boolean existsById(Long id) {
		return this.pharmacyBranchRepository.existsById(id);
	}
	@Override
	public Iterable<PharmacBranch> findAll() {
		return this.pharmacyBranchRepository.findAll();
	}
	@Override
	public Iterable<PharmacBranch> findAllById(Iterable<Long> ids) {
		return this.pharmacyBranchRepository.findAllById(ids);
	}
	@Override
	public long count() {
		return this.pharmacyBranchRepository.count();
	}
	@Override
	public void delete(PharmacBranch entities) {
		this.pharmacyBranchRepository.delete(entities);
		
	}
	@Override
	public void deleteAll(Iterable<PharmacBranch> entities) {
		this.pharmacyBranchRepository.deleteAll(entities);
	}
	@Override
	public void deleteAll() {
		this.pharmacyBranchRepository.deleteAll();
	}
	@Override
	public List<PharmacBranch> searchPharmacyByLocation(String location) {
		return this.pharmacyBranchRepository.searchPharmacyByLocation(location);
	}
	@Override
	public void updatePharmacyIdById(long pharmacy_id, long id) {
		this.pharmacyBranchRepository.updatePharmacyIdById(pharmacy_id, id);
		
	}

}
