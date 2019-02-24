package com.team.pharmaC.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.team.pharmaC.main.repository.MainEmployeeRepository;
import com.team.pharmaC.main.security.MainEmployee;

@Service
public class EmployeeRepositoryUserDetailsService implements UserDetailsService {

	private MainEmployeeRepository employeeRepo;
	
	@Autowired
	public EmployeeRepositoryUserDetailsService(MainEmployeeRepository employeeRepo) {
		this.employeeRepo = employeeRepo;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MainEmployee user = employeeRepo.findByUsername(username);
		if (user != null) {
				return user;
		}
		throw new UsernameNotFoundException("Employee with  '" + username + "'is  not found");
	}
	
}
