package com.team.pharmaC.main.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;

@Data
public class EmployeeRegisterForm {
	
	private   long license_id;
	private   String username;
	private   String password;
	private   String firstname;
	private   String lastname;
	
	public MainEmployee toUser(PasswordEncoder passwordEncoder) {
		return new MainEmployee(license_id,username, passwordEncoder.encode(password),firstname,lastname);
	}
}
