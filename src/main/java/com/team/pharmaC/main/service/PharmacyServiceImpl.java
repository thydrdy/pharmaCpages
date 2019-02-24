package com.team.pharmaC.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.team.pharmaC.main.domains.Pharmacy;
import com.team.pharmaC.main.repository.PharmacyRepository;
@Service
public class PharmacyServiceImpl implements PharmacyService {
    public PharmacyRepository pharmacyRepository;
    @Autowired
    public PharmacyServiceImpl(PharmacyRepository pharmacyRepository) {
    	this.pharmacyRepository=pharmacyRepository;
    }
	@Override
	public Optional<Pharmacy> findById(Long id) {
		return this.pharmacyRepository.findById(id);
	}

	@Override
	public Pharmacy save(Pharmacy entity) {
		return this.pharmacyRepository.save(entity);
	}
	@Override
	public Page<Pharmacy> findAll(Pageable pageable) {
		return pharmacyRepository.findAll(pageable);
	}
	@Override
	public Iterable<Pharmacy> saveAll(Iterable<Pharmacy> entities) {
		return pharmacyRepository.saveAll(entities);
	}
	@Override
	public boolean existsById(Long id) {
		return pharmacyRepository.existsById(id);
	}
	@Override
	public Iterable<Pharmacy> findAll() {
		return pharmacyRepository.findAll();
	}
	@Override
	public Iterable<Pharmacy> findAllById(Iterable<Long> ids) {
		return pharmacyRepository.findAllById(ids);
	}
	@Override
	public long count() {
		return pharmacyRepository.count();
	}
	@Override
	public void deleteById(Long id) {
		pharmacyRepository.deleteById(id);
	}
	@Override
	public void delete(Pharmacy entities) {
		pharmacyRepository.delete(entities);
	}
	@Override
	public void deleteAll(Iterable<Pharmacy> entities) {
		pharmacyRepository.deleteAll(entities);
	}
	@Override
	public void deleteAll() {
		pharmacyRepository.deleteAll();
	}
	@Override
	public List<Pharmacy> searchPharmacyByName(String pharmacyName) {
		return pharmacyRepository.searchPharmacyByName(pharmacyName);
	}

}
