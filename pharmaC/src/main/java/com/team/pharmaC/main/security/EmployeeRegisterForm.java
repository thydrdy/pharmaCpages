package com.team.pharmaC.main.security;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;

@Data
public class EmployeeRegisterForm {
	@NotBlank
	@Digits(integer=10,message="Please Input A Valid Medical Licese Id", fraction = 0)
	private   String license_id;
	@NotBlank
	@Size(min=5,message=" Username Should Be At  Least 5 character long")
	private   String username;
	@NotBlank
	@Size(min=8,message="Password Should be At least 8 character long")
	private   String password;
	@NotBlank
	@Size(min=5,message=" First name Should Be At  Least 5 character long")
	private   String firstname;
	@NotBlank
	@Size(min=5,message=" Last name Should Be At  Least 5 character long")
	private   String lastname;
	
	public MainEmployee toUser(PasswordEncoder passwordEncoder) {
		return new MainEmployee(license_id,username, passwordEncoder.encode(password),firstname,lastname);
	}
}
