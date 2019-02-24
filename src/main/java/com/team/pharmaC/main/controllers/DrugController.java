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

import com.team.pharmaC.main.domains.Drugs;
import com.team.pharmaC.main.domains.PharmacBranch;
import com.team.pharmaC.main.domains.Pharmacy;
import com.team.pharmaC.main.repository.DrugsRepository;
import com.team.pharmaC.main.security.MainEmployee;
import com.team.pharmaC.main.service.DrugsServiceImpl;

@Controller
@RequestMapping("/drugs")
@SessionAttributes("pharmacy")
public class DrugController {
	
	private DrugsServiceImpl rep;
	@Autowired
	public DrugController(DrugsServiceImpl rep) {
		this.rep=rep;
	}
	
	@ModelAttribute(name="drugObj")
	public Drugs drug(Model model) {
		return new Drugs();
	}
	@ModelAttribute(name="MainEmployee")
	public MainEmployee loginUser(@AuthenticationPrincipal MainEmployee  ee) {
		return ee;
	}
	
	@GetMapping
	public String drugForm(Model model) { 
		model.addAttribute("mssage", "Add Your Drugs");
		  return "drug";    
	}
	
	@PostMapping
	public String proccessForm(@Valid @ModelAttribute("drugObj") Drugs drug,  Errors errors, Pharmacy pharmacy,Model model) { 
		
		if(errors.hasErrors()) {
			return "drug";
		}
		model.addAttribute("mssage", "Add Another Drug");
	    pharmacy.addDrugs(drug);
		this.rep.save(drug);
		return "drug";    
	}
}
