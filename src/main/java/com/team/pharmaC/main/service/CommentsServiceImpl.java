package com.team.pharmaC.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.team.pharmaC.main.domains.Comments;
import com.team.pharmaC.main.domains.Drugs;
import com.team.pharmaC.main.domains.PharmacBranch;
import com.team.pharmaC.main.repository.CommentsRepository;

@Service
public class CommentsServiceImpl implements CommentsService {
    public CommentsRepository commentsRepository;
    @Autowired
    public CommentsServiceImpl(CommentsRepository commentsRepository) {
    	this.commentsRepository=commentsRepository;
    }
	@Override
	public Iterable<Comments> findAll(Sort sort) {
		return this.commentsRepository.findAll(sort);
	}
	@Override
	public Page<Comments> findAll(Pageable pageable) {
		return this.commentsRepository.findAll(pageable);
	}
	@Override
	public Comments save(Comments entity) {
		return this.commentsRepository.save(entity);
	}
	@Override
	public Iterable<Comments> saveAll(Iterable<Comments> entities) {
		return this.commentsRepository.saveAll(entities);
	}
	@Override
	public boolean existsById(Long id) {
		return this.commentsRepository.existsById(id);
	}
	@Override
	public Iterable<Comments> findAll() {
		return this.commentsRepository.findAll();
	}
	@Override
	public Iterable<Comments> findAllById(Iterable<Long> ids) {
		return this.commentsRepository.findAllById(ids);
	}
	@Override
	public long count() {
		return this.commentsRepository.count();
	}
	@Override
	public void deleteById(Long id) {
		this.commentsRepository.deleteById(id);
	}
	@Override
	public void delete(Comments entities) {
		this.commentsRepository.delete(entities);
	}
	@Override
	public void deleteAll(Iterable<Comments> entities) {
		this.commentsRepository.deleteAll(entities);
	}
	@Override
	public void deleteAll() {
		this.commentsRepository.deleteAll();
	}
	@Override
	public List<Comments> findCommentsByPhID(long pharmacy_license_id) {
		return this.commentsRepository.findCommentsByPhID(pharmacy_license_id);
	}
	@Override
	public Optional<Comments> findById(Long id) {
		return this.commentsRepository.findById(id);
	}
	
}
