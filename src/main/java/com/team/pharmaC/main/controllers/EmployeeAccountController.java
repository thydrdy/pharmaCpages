package com.team.pharmaC.main.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.team.pharmaC.main.domains.Pharmacy;
import com.team.pharmaC.main.security.MainEmployee;
import com.team.pharmaC.main.service.MainEmployeeServiceImpl;
import com.team.pharmaC.main.service.PharmacyServiceImpl;

@Controller 
@RequestMapping("employee_account")
@SessionAttributes("MainEmployee")
public class EmployeeAccountController {
	private MainEmployeeServiceImpl employeeService;
	private PharmacyServiceImpl pharmaRepo;
	
	@Autowired
	public  EmployeeAccountController(MainEmployeeServiceImpl employeeService,PharmacyServiceImpl pharmaRepo) {
		this.employeeService=employeeService;
		this.pharmaRepo=pharmaRepo; 
	}
	
	
	@ModelAttribute(name="MainEmployee")
	public MainEmployee loginUser(@AuthenticationPrincipal MainEmployee  ee) {
		return ee;
	}
	
	@GetMapping("/userProfile")
	public String getProfile() {
		return "userProfilePage";
	}
	
	@GetMapping("/editUserProfile")
	public String getEditProfile() {
		return "editProfilePage";
	}
	
    @PostMapping("/editUserProfile/image") 
	public String singleFileUpload(@RequestParam("file") MultipartFile file,@ModelAttribute("MainEmployee") MainEmployee employee,Model model) {
	    	String UPLOADED_FOLDER = "uploads\\";
	        if (file.isEmpty()) {
	            return "editProfilePage";
	        }
	        try {
	            byte[] bytes = file.getBytes();
	            Path path = Paths.get(UPLOADED_FOLDER+ file.getOriginalFilename());
	            employee.setProfileImage(file.getOriginalFilename());
	            Files.write(path, bytes);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return "editProfilePage";
	}
	    
	@PostMapping("/editUserProfile")  
	public String processRegistration(@Valid @ModelAttribute("MainEmployee") MainEmployee employee, Errors errors,Model model) {
		if(errors.hasErrors()) {
			return "editProfilePage";
		}
		this.employeeService.updateProfile(employee.getLicense_id(),employee.getFirstname(),employee.getLastname(),employee.getProfileImage(),employee.getId());
		return "userProfilePage";
	}
	
	@GetMapping("/deleteAcc")
	public String deleteAccount(@ModelAttribute("MainEmployee") MainEmployee employee) {
		employeeService.delete(employee);
		return "redirect:/";
	}
	@GetMapping("/deletePh")
	public String deletePharmacy(@ModelAttribute("MainEmployee") MainEmployee employee) {
		Pharmacy pharma=this.pharmaRepo.findById(Long.parseLong(employee.getPharmacy_id())).get();
		employee.setPharmacy_id("false");
		employeeService.save(employee);
		this.pharmaRepo.delete(pharma);
		return "redirect:/";
	}
}
