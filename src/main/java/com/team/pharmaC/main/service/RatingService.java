package com.team.pharmaC.main.service;

import java.util.Optional;
import com.team.pharmaC.main.domains.Rating;

public interface RatingService {
	Optional<Rating> findById(Long id);
	  
	Rating save(Rating entity);
	
	public Iterable<Rating> saveAll(Iterable<Rating> entities);

	boolean existsById(Long id);
	
	Iterable<Rating> findAll();
	
	Iterable<Rating> findAllById(Iterable<Long> ids);
	
	long count();
	
	void deleteById(Long id);
	
	void delete(Rating entities);
	
	void deleteAll(Iterable<Rating> entities);
	
	void deleteAll();
	Optional<Rating> findRatingByPharmacyId(long id);
	
	Rating findRatingById(long id);
}
