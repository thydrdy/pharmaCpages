package com.team.pharmaC.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.team.pharmaC.Pharmacy;
import com.team.pharmaC.data.PharmacyRepository;


@Controller
@RequestMapping("/search")
public class SearchController {
	private PharmacyRepository pharmaRepo;
	@Autowired
	
	public SearchController(PharmacyRepository repo) {
		this.pharmaRepo=repo;
	}
	@ModelAttribute(name="pharmas")
	public Iterable<Pharmacy> drug(Model model) {
		
		return pharmaRepo.findAll();
	}
	
	
	@GetMapping
	public String drugForm(Model model) { 
		
		  return "listPharma";    
	}
	
}
