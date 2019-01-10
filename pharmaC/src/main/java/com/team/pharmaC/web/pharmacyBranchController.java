package com.team.pharmaC.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.team.pharmaC.PharmacBranch;
import com.team.pharmaC.Pharmacy;
import com.team.pharmaC.data.PharmacyLocationRepository;
import com.team.pharmaC.data.PharmacyRepository;

@Controller
@RequestMapping("/location")
@SessionAttributes("pharmacy")
public class pharmacyBranchController {
	
	private PharmacyLocationRepository rep;
	private PharmacyRepository pharmaRepo;
	@Autowired
	public pharmacyBranchController(PharmacyLocationRepository rep,PharmacyRepository repo) {
		this.rep=rep;
		this.pharmaRepo=repo;
	}
	
	@ModelAttribute(name="pharmacylocation")
	public PharmacBranch pharmacyloc(Model model) {
		return new PharmacBranch();
	}
	
	@ModelAttribute(name="pharmacy")
	public Pharmacy pharmacy(Model model) {
		return new Pharmacy();
	}
	
	@GetMapping
	public String locForm(Model model) { 
		
		  return "locations";    
	}
	
	@PostMapping
	public String proccessForm(@Valid @ModelAttribute("pharmacylocation") PharmacBranch pharmacyloc,  Errors errors, Pharmacy pharmacy) { //, @ModelAttribute Pharmacy pharmacy
		
		if(errors.hasErrors()) {
			return "locations";
		}
	    pharmacy.addLocation(pharmacyloc);
		this.rep.save(pharmacyloc);
		return "pharmacy";    
	}
}
