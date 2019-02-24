package com.team.pharmaC.main.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.team.pharmaC.main.repository.MainEmployeeRepository;
import com.team.pharmaC.main.security.EmployeeRegisterForm;
import com.team.pharmaC.main.security.MainEmployee;
import com.team.pharmaC.main.service.MainEmployeeServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user_register")
@SessionAttributes("regEmp")
public class EmployeeRegistrationController {
	
	private MainEmployeeServiceImpl empRepo;
	private PasswordEncoder passwordEncoder;
	@Autowired
	public EmployeeRegistrationController(MainEmployeeServiceImpl userRepo, PasswordEncoder passwordEncoder) {
		this.empRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	}
	
	// Models-----------------
	@ModelAttribute(name="regEmp")
	public EmployeeRegisterForm registerform(Model model) {
		EmployeeRegisterForm emp= new EmployeeRegisterForm();
		emp.setProfileImage("user.jpg");
		model.addAttribute("confirm", "");
		return emp;
	}
	
	@ModelAttribute(name="MainEmployee")
	public MainEmployee loginUser(@AuthenticationPrincipal MainEmployee  ee) {
		return ee;
	}
	// End --- of Models
	
	@GetMapping
	public String registerForm() {
		return "user_registration";
	}
	
    @PostMapping("/image") 
    public String singleFileUpload(@RequestParam("file") MultipartFile file,RedirectAttributes redirectAttributes,@ModelAttribute("regEmp") EmployeeRegisterForm form,Model model) {
    	String UPLOADED_FOLDER = "uploads\\";
        if (file.isEmpty()) {
            return "user_registration";
        }
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER+ file.getOriginalFilename());
            form.setProfileImage(file.getOriginalFilename());
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "user_registration";
    }
    
	@PostMapping  
	public String processRegistration(@Valid @ModelAttribute("regEmp") EmployeeRegisterForm form, Errors errors,Model model) {
		
		if(errors.hasErrors()) {
			return "user_registration";
		}
		else if(!form.getPassword().equals(form.getConfirm())) {
			model.addAttribute("confirm", "Inconsistent password.");
			return "user_registration";
		}
		this.empRepo.save(form.toUser(passwordEncoder));
		return "redirect:/login";
	}
}
