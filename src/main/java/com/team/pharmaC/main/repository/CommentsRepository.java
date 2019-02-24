package com.team.pharmaC.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.team.pharmaC.main.domains.Comments;

public interface CommentsRepository extends PagingAndSortingRepository<Comments, Long> {
	@Query("SELECT DISTINCT c FROM Comments c where c.pharmacy_license_id = ?1 ORDER BY c.dateCommented ASC ")
	List<Comments> findCommentsByPhID(long  pharmacy_license_id );
}
