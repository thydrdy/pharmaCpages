package com.team.pharmaC.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team.pharmaC.main.domains.Drugs;
import com.team.pharmaC.main.repository.MainEmployeeRepository;
import com.team.pharmaC.main.security.EmployeeRegisterForm;

@Controller
@RequestMapping("/user_register")
public class EmployeeRegistrationController {
	
	private MainEmployeeRepository empRepo;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public EmployeeRegistrationController(MainEmployeeRepository userRepo, PasswordEncoder passwordEncoder) {
		this.empRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	}
	
	@ModelAttribute(name="regEmp")
	public EmployeeRegisterForm registerform(Model model) {
		return new EmployeeRegisterForm();
	}
	
	@GetMapping
	public String registerForm() {
		return "user_registration";
	}
	
	@PostMapping
	public String processRegistration(EmployeeRegisterForm form) {
		empRepo.save(form.toUser(passwordEncoder));
		return "redirect:/login";
	}
}
