package com.team.pharmaC.main.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.team.pharmaC.main.domains.Drugs;
import com.team.pharmaC.main.domains.Pharmacy;
import com.team.pharmaC.main.domains.SearchInfo;
import com.team.pharmaC.main.repository.PharmacyRepository;

@Controller 
@RequestMapping("Details")
public class DetailsController {
	@Autowired
	private PharmacyRepository repo;
	private Pharmacy phObj;
	private Long id;
	public DetailsController(PharmacyRepository repo) {
		this.repo=repo;
	}

	@ModelAttribute(name="searchObj")
	public SearchInfo searchInfo(Model model) {
		return new SearchInfo();
	}
	
	@ModelAttribute(name="drugs")
	public List<Drugs> drugInFO(Model model) {
		return new  ArrayList<Drugs>();
	}
	
	@GetMapping
	public String deatails(@RequestParam("id") Long id,Model model) {
		System.out.println("ID : "+id);
		phObj=repo.findById(id).get();
		model.addAttribute("pharmaC",phObj);
		return "details";
	}
	
	@PostMapping("/search")
	public String findDrug(@ModelAttribute("searchObj") SearchInfo info, Model model) {
		System.out.println(info.getQueryValue());
		phObj.setPharmacDrugs(findDrugs(info.getQueryValue()));
		model.addAttribute("pharmaC",phObj);
		return "details";
	}
	
	public  Set<Drugs> findDrugs(String dr_name){
		return phObj.getPharmacDrugs().stream().filter(i -> i.getName().toUpperCase().contains(dr_name.toUpperCase())).collect(Collectors.toSet());
		 
	}
}
