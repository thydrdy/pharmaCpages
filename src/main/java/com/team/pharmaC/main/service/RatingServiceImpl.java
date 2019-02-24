package com.team.pharmaC.main.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.pharmaC.main.domains.Rating;
import com.team.pharmaC.main.repository.RatingRepository;

@Service
public class RatingServiceImpl implements RatingService{
	private RatingRepository rateRepo;
	
	@Autowired
	public RatingServiceImpl(RatingRepository rateRepo) {
		this.rateRepo=rateRepo;
	}
	
	@Override
	public Optional<Rating> findById(Long id) {
		return rateRepo.findById(id);
	}

	@Override
	public Rating save(Rating entity) {
		return rateRepo.save(entity);
	}

	@Override
	public Iterable<Rating> saveAll(Iterable<Rating> entities) {
		return rateRepo.saveAll(entities);
	}

	@Override
	public boolean existsById(Long id) {
		return rateRepo.existsById(id);
	}

	@Override
	public Iterable<Rating> findAll() {
		return rateRepo.findAll();
	}

	@Override
	public Iterable<Rating> findAllById(Iterable<Long> ids) {
		return rateRepo.findAllById(ids);
	}

	@Override
	public long count() {
		return rateRepo.count();
	}

	@Override
	public void deleteById(Long id) {
		rateRepo.deleteById(id);
	}

	@Override
	public void delete(Rating entities) {
		rateRepo.delete(entities);
	}

	@Override
	public void deleteAll(Iterable<Rating> entities) {
		rateRepo.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		rateRepo.deleteAll();
	}

	@Override
	public Optional<Rating> findRatingByPharmacyId(long id) {
		return this.rateRepo.findRatingByPharmacyId(id);
	}

	@Override
	public Rating findRatingById(long id) {
		return this.rateRepo.findRatingByPhId(id);
	}

}
