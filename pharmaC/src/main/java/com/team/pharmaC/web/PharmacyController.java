package com.team.pharmaC.web;

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

import com.team.pharmaC.PharmacBranch;
import com.team.pharmaC.Pharmacy;
import com.team.pharmaC.data.PharmacyRepository;

@Controller
@RequestMapping("/pharmacy")
@SessionAttributes("pharmacy")
public class PharmacyController {
	private PharmacyRepository pharmaRepo;
	@Autowired
	public PharmacyController(PharmacyRepository repo) {
		this.pharmaRepo=repo;
	}
	@ModelAttribute(name="pharmacy")
	public Pharmacy pharmacy(Model model) {
		return new Pharmacy();
	}
	@GetMapping
	public String greetingForm(Model model) { 
	  return "pharmacy";    
	}

	@PostMapping
	public String processOrder(@Valid Pharmacy pharmacy, Errors errors,SessionStatus sessionStatus) {
		
		if(errors.hasErrors()) {
			return "pharmacy";
		}
		pharmaRepo.save(pharmacy);
		sessionStatus.setComplete();
		return "index";
	}

}
