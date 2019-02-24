package com.team.pharmaC.main.controllers;

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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.team.pharmaC.main.domains.PharmacBranch;
import com.team.pharmaC.main.domains.Pharmacy;
import com.team.pharmaC.main.repository.PharmacyBranchRepository;
import com.team.pharmaC.main.repository.PharmacyRepository;
import com.team.pharmaC.main.security.MainEmployee;
import com.team.pharmaC.main.service.PharmacyBranchServiceImpl;

@Controller
@RequestMapping("/location")
@SessionAttributes("pharmacy")
public class PharmacyBranchController {
	
	private PharmacyBranchServiceImpl rep;
	@Autowired
	public PharmacyBranchController(PharmacyBranchServiceImpl rep) {
		this.rep=rep;
	}
	
	@ModelAttribute(name="pharmacyBranch")
	public PharmacBranch pharmacyloc(Model model) {
		model.addAttribute("mssage", "Create Your Branch Profile");
		return new PharmacBranch();
	}
	
	@ModelAttribute(name="pharmacy")
	public Pharmacy pharmacy(Model model) {
		return new Pharmacy();
	}
	
	@ModelAttribute(name="MainEmployee")
	public MainEmployee loginUser(@AuthenticationPrincipal MainEmployee  ee) {
		return ee;
	}
	
	@GetMapping
	public String locForm(Model model) { 
		
		  return "locations";    
	}
	
	@PostMapping
	public String proccessForm(@Valid @ModelAttribute("pharmacyBranch") PharmacBranch pharmacybr,  Errors errors, Pharmacy pharmacy,Model model) { 
		
		if(errors.hasErrors()) {
			return "locations";
		}
		model.addAttribute("mssage", "Create Another  Branch Profile");
		//pharmacybr.setHQ_pharmacy(pharmacy);
	    pharmacy.addLocation(pharmacybr);
		return "locations";    
	}
}
