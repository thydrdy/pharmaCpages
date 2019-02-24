package com.team.pharmaC.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.team.pharmaC.main.domains.Comments;
import com.team.pharmaC.main.domains.PharmacBranch;

public interface CommentsService {
	Optional<Comments> findById(Long id);
	Iterable<Comments> findAll(Sort sort);
	Page<Comments> findAll(Pageable pageable);
	
	Comments save(Comments entity);
	
	public Iterable<Comments> saveAll(Iterable<Comments> entities);


	boolean existsById(Long id);
	
	Iterable<Comments> findAll();

	Iterable<Comments> findAllById(Iterable<Long> ids);

	long count();
	
	void deleteById(Long id);
	
	void delete(Comments entities);
	
	void deleteAll(Iterable<Comments> entities);

	void deleteAll();
	List<Comments> findCommentsByPhID(long  pharmacy_license_id );
}
