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
import com.team.pharmaC.data.PharmacyBranchRepository;
import com.team.pharmaC.data.PharmacyRepository;

@Controller
@RequestMapping("/location")
@SessionAttributes("pharmacy")
public class pharmacyBranchController {
	
	private PharmacyBranchRepository rep;
	@Autowired
	public pharmacyBranchController(PharmacyBranchRepository rep) {
		this.rep=rep;
	}
	
	@ModelAttribute(name="pharmacyBranch")
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
	public String proccessForm(@Valid @ModelAttribute("pharmacyBranch") PharmacBranch pharmacybr,  Errors errors, Pharmacy pharmacy) { 
		
		if(errors.hasErrors()) {
			return "locations";
		}
	    pharmacy.addLocation(pharmacybr);
		this.rep.save(pharmacybr);
		return "locations";    
	}
}
