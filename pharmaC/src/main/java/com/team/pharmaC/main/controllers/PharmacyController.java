package com.team.pharmaC.main.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.team.pharmaC.main.domains.PharmacBranch;
import com.team.pharmaC.main.domains.Pharmacy;
import com.team.pharmaC.main.repository.PharmacyBranchRepository;
import com.team.pharmaC.main.repository.PharmacyRepository;

@Controller
@SessionAttributes("pharmacy")
public class PharmacyController {
	private PharmacyRepository pharmaRepo;
	private PharmacyBranchRepository rep;
	@Autowired
	public PharmacyController(PharmacyRepository repo,PharmacyBranchRepository rep) {
		this.pharmaRepo=repo;
		this.rep=rep;
	}
	@ModelAttribute(name="pharmacy")
	public Pharmacy pharmacy(Model model) {
		return new Pharmacy();
	}
	@GetMapping("/pharmacy")
	public String pharmacForm(Model model) { 
	  return "pharmacy";    
	}

	@PostMapping("/pharmacy/cont")
	public String savePh(@Valid Pharmacy pharmacy, Errors errors) {
		if(errors.hasErrors()) {
			return "pharmacy";
		}
		return "redirect:/location";
	}
	
	@PostMapping("/pharmacy")
	public String savePharma(@Valid Pharmacy pharmacy, Errors errors,SessionStatus sessionStatus) {
		
		if(errors.hasErrors()) {
			return "pharmacy";
		}
		for(PharmacBranch br : pharmacy.getPharmacBranches()) {
			br.setPharmacDrugs(pharmacy.getPharmacDrugs());
			br.setProfileImages(pharmacy.getProfileImages());
			rep.save(br);
		}
		pharmaRepo.save(pharmacy);
		sessionStatus.setComplete();
		return "index";
	}
	
	@GetMapping("/complete")
	public String completeForm(Model model) { 
	  return "completeReg";    
	}

}
