package com.team.pharmaC.main.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

import com.team.pharmaC.main.domains.Drugs;
import com.team.pharmaC.main.domains.PharmacBranch;
import com.team.pharmaC.main.domains.Pharmacy;
import com.team.pharmaC.main.repository.MainEmployeeRepository;
import com.team.pharmaC.main.repository.PharmacyBranchRepository;
import com.team.pharmaC.main.repository.PharmacyRepository;
import com.team.pharmaC.main.security.MainEmployee;
import com.team.pharmaC.main.service.*;

@Controller
@SessionAttributes("pharmacy")
public class PharmacyController {
	private PharmacyServiceImpl pharmaRepo;
	private PharmacyBranchServiceImpl branchRep;
	private MainEmployeeServiceImpl userRepo;
	private DrugsServiceImpl drugsRepo;
	@Autowired
	public PharmacyController(PharmacyServiceImpl repo,PharmacyBranchServiceImpl rep,MainEmployeeServiceImpl userRepo,DrugsServiceImpl drugsRepo) {
		this.pharmaRepo=repo;
		this.branchRep=rep;
		this.userRepo=userRepo;
		this.drugsRepo=drugsRepo;
	}
	
	@ModelAttribute(name="pharmacy")
	public Pharmacy pharmacy() {
		return new Pharmacy();
	}

	@ModelAttribute(name="MainEmployee")
	public MainEmployee loginUser(@AuthenticationPrincipal MainEmployee  ee) {
		return ee;
	}
	
	@GetMapping("/pharmacy")
	public String pharmacForm() { 
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
	public String savePharma(@Valid Pharmacy pharmacy, Errors errors,SessionStatus sessionStatus,@AuthenticationPrincipal MainEmployee  ee) {
		
		if(errors.hasErrors()) {
			return "pharmacy";
		}
		for(PharmacBranch br : pharmacy.getPharmacBranches()) {
			br.setPharmacDrugs(pharmacy.getPharmacDrugs());
			br.setProfileImages(pharmacy.getProfileImages());
			branchRep.save(br);
		}
		pharmaRepo.save(pharmacy);
		for(PharmacBranch br : pharmacy.getPharmacBranches()) {
			this.branchRep.updatePharmacyIdById(pharmacy.getPharmacy_license_id(), br.getBranch_no());
		}
		for(Drugs dr : pharmacy.getPharmacDrugs()) {
			this.drugsRepo.updatePharmacyIdById(pharmacy.getPharmacy_license_id(), dr.getDrug_id());
		}
		System.out.println("user id "+ ee.getId());
		ee.setPharmacy_id(pharmacy.getPharmacy_license_id()+"");
		this.userRepo.updatePharmacyIdById(pharmacy.getPharmacy_license_id()+"", ee.getId());
		
		sessionStatus.setComplete();
		return "index";
	}
	
	@GetMapping("/complete")
	public String completeForm(Model model) { 
	  return "completeReg";    
	}

}
