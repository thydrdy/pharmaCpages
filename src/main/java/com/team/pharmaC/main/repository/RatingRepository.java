package com.team.pharmaC.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.team.pharmaC.main.domains.Rating;

public interface RatingRepository  extends CrudRepository<Rating,Long> {
	@Query("SELECT DISTINCT r FROM Rating r where r.pharmacy_license_id =?1 ")
	Optional<Rating> findRatingByPharmacyId(long id);
	
	@Query("SELECT DISTINCT r FROM Rating r where r.pharmacy_license_id =?1 ")
	Rating findRatingByPhId(long id);
}
