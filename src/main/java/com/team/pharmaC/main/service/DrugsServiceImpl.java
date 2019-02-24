package com.team.pharmaC.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.team.pharmaC.main.domains.Drugs;
import com.team.pharmaC.main.repository.DrugsRepository;
@Service
public class DrugsServiceImpl implements DrugsService {
    public DrugsRepository drugsRepo;
    @Autowired
    public DrugsServiceImpl(DrugsRepository drugsRepo) {
        this.drugsRepo=drugsRepo;	
    }
	@Override
	public Optional<Drugs> findById(Long id) {
		return this.drugsRepo.findById(id);
	}

	@Override
	public void updateProfile(String Name, String Description, String expiration_date, long amount, long price,
			String production_date, long drug_id) {
		this.drugsRepo.updateProfile(Name, Description, expiration_date, amount, price, production_date, drug_id);

	}

	@Override
	public Drugs save(Drugs entity) {
		return this.drugsRepo.save(entity);
	}
	@Override
	public Iterable<Drugs> saveAll(Iterable<Drugs> entities) {
		return this.drugsRepo.saveAll(entities);
	}
	@Override
	public boolean existsById(Long id) {
		return this.drugsRepo.existsById(id);
	}
	@Override
	public Iterable<Drugs> findAll() {
		return this.drugsRepo.findAll();
	}
	@Override
	public Iterable<Drugs> findAllById(Iterable<Long> ids) {
		return this.drugsRepo.findAllById(ids);
	}
	@Override
	public long count() {
		return this.drugsRepo.count();
	}
	@Override
	public void deleteById(Long id) {
		this.drugsRepo.deleteById(id);
		
	}
	@Override
	public void delete(Drugs entity) {
		this.drugsRepo.delete(entity);
	}
	@Override
	public void deleteAll(Iterable<Drugs> entities) {
		this.drugsRepo.deleteAll(entities);
	}
	@Override
	public void deleteAll() {
		this.drugsRepo.deleteAll();
		
	}
	@Override
	public List<Drugs> findDrugsByName(String Name) {
		return this.drugsRepo.findDrugsByName(Name);
	}
	@Override
	public List<Drugs> findDrugsByAmount(String Name) {
		return this.drugsRepo.findDrugsByAmount(Name);
	}
	@Override
	public List<Drugs> findDrugsByPrice(String Name) {
		return this.drugsRepo.findDrugsByPrice(Name);
	}
	@Override
	public List<Drugs> findDrugsByEdate(String Name) {
		return this.drugsRepo.findDrugsByEdate(Name);
	}
	@Override
	public void updatePharmacyIdById(long pharmacy_id, long id) {
		this.drugsRepo.updatePharmacyIdById(pharmacy_id, id);
	}
	@Override
	public Page<Drugs> findAll(Pageable pageable) {
		return this.drugsRepo.findAll(pageable);
	}

}
