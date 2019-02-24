package com.team.pharmaC.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.pharmaC.main.repository.MainEmployeeRepository;
import com.team.pharmaC.main.security.MainEmployee;
@Service
public class MainEmployeeServiceImpl implements MainEmployeeService {
    public MainEmployeeRepository mainEmployeeRepository;
    @Autowired
    public MainEmployeeServiceImpl(MainEmployeeRepository mainEmployeeRepository) {
    	this.mainEmployeeRepository=mainEmployeeRepository;
	}
	@Override
	public void updateProfile(String license_id, String firstname, String lastname, String profileImag, long id) {
		this.mainEmployeeRepository.updateProfile(license_id, firstname, lastname, profileImag, id);

	}
	@Override
	public MainEmployee save(MainEmployee entity) {
		return this.mainEmployeeRepository.save(entity);
	}
	@Override
	public void updatePharmacyIdById(String pharmacy_id, long id) {
		this.mainEmployeeRepository.updatePharmacyIdById(pharmacy_id, id);
	}
	@Override
	public Iterable<MainEmployee> saveAll(Iterable<MainEmployee> entities) {
		return this.mainEmployeeRepository.saveAll(entities);
	}
	@Override
	public boolean existsById(Long id) {
		return this.mainEmployeeRepository.existsById(id);
	}
	@Override
	public Iterable<MainEmployee> findAll() {
		return this.mainEmployeeRepository.findAll();
	}
	@Override
	public Iterable<MainEmployee> findAllById(Iterable<Long> ids) {
		return this.mainEmployeeRepository.findAllById(ids);
	}
	@Override
	public long count() {
		return this.mainEmployeeRepository.count();
	}
	@Override
	public void deleteById(Long id) {
		this.mainEmployeeRepository.deleteById(id);
	}
	@Override
	public void delete(MainEmployee entities) {
		this.mainEmployeeRepository.delete(entities);
	}
	@Override
	public void deleteAll(Iterable<MainEmployee> entities) {
		this.mainEmployeeRepository.deleteAll(entities);
	}
	@Override
	public void deleteAll() {
		this.mainEmployeeRepository.deleteAll();
	}

}
