package com.team.pharmaC.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.team.pharmaC.Pharmacy;
import com.team.pharmaC.SearchInfo;
import com.team.pharmaC.data.PharmacyBranchRepository;
import com.team.pharmaC.data.PharmacyRepository;


@Controller
@RequestMapping("/search")
public class SearchController {
	private PharmacyRepository pharmaRepo;
	private PharmacyBranchRepository branchRepo;	   
	@Autowired
	public SearchController(PharmacyRepository repo,PharmacyBranchRepository branchRepo) {
		this.pharmaRepo=repo;
		this.branchRepo=branchRepo;
	}
	
	@ModelAttribute(name="pharmas")
	public List<Pharmacy> drug(Model model) {
		
		return (List<Pharmacy>)pharmaRepo.findAll();
	}
	
	@ModelAttribute(name="searchObj")
	public SearchInfo searchInfo(Model model) {
		return new SearchInfo();
	}
	
	@GetMapping("/spe")
	public String searchForm(Model model) { 
		
		  return "search";    
	}
	@PostMapping("/spe")
	public String processSearch(@ModelAttribute(name="searchObj") SearchInfo info,@ModelAttribute(name="pharmas") List<Pharmacy> pharmas,Model model) {

		pharmas.clear();
		pharmas=(List<Pharmacy>)this.pharmaRepo.searchPharmacyByName(info.getQueryValue());
		//pharmas=(List<Pharmacy>)this.branchRepo.searchPharmacyByLocation(info.getQueryValue());
		System.out.println(pharmas.size()+" results found");
		model.addAttribute("pharmas", pharmas);
		return "search";
	}
	
	@GetMapping("/all")
	public String getAll(Model model) { 
		
		  return "listPharma";    
	}
	
	
}
