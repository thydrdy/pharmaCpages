package com.team.pharmaC.main.repository;

import org.springframework.data.repository.CrudRepository;

import com.team.pharmaC.main.security.MainEmployee;

public interface MainEmployeeRepository  extends CrudRepository<MainEmployee, Long>{
	MainEmployee findByUsername(String username);
}
