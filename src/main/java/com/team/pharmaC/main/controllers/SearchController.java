package com.team.pharmaC.main.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team.pharmaC.main.domains.Drugs;
import com.team.pharmaC.main.domains.PharmacBranch;
import com.team.pharmaC.main.domains.Pharmacy;
import com.team.pharmaC.main.domains.Rating;
import com.team.pharmaC.main.domains.SearchInfo;
import com.team.pharmaC.main.repository.DrugsRepository;
import com.team.pharmaC.main.repository.PharmacyBranchRepository;
import com.team.pharmaC.main.repository.PharmacyRepository;
import com.team.pharmaC.main.security.MainEmployee;
import com.team.pharmaC.main.service.DrugsServiceImpl;
import com.team.pharmaC.main.service.MainEmployeeServiceImpl;
import com.team.pharmaC.main.service.PharmacyBranchServiceImpl;
import com.team.pharmaC.main.service.PharmacyService;
import com.team.pharmaC.main.service.PharmacyServiceImpl;
import com.team.pharmaC.main.service.RatingServiceImpl;


@Controller
@RequestMapping("/search")
public class SearchController {
	private PharmacyServiceImpl pharmaRepo;
	private PharmacyBranchServiceImpl branchRepo;
	private DrugsServiceImpl drugRepo;
	private RatingServiceImpl ratingRepo;
	@Autowired
	public SearchController(PharmacyServiceImpl pharmaRepo,PharmacyBranchServiceImpl branchRepo,DrugsServiceImpl drugRepo,RatingServiceImpl ratingRepo) {
		this.pharmaRepo=pharmaRepo;
		this.branchRepo=branchRepo;
		this.drugRepo=drugRepo;
		this.ratingRepo=ratingRepo;
	}
	
	@ModelAttribute(name="pharmas")
	public List<Pharmacy> drug(Model model) {
		List<Pharmacy> pharmacies=(List<Pharmacy>)pharmaRepo.findAll();
		for(Pharmacy pharma:pharmacies) {
			Rating rate=ratingRepo.findRatingById(pharma.getPharmacy_license_id());
			if(rate==null) {
				rate=new Rating();
			}
			pharma.setRatingObject(rate);
		}
		return pharmacies;
	}
	
	@ModelAttribute(name="searchObj")
	public SearchInfo searchInfo(Model model) {
		return new SearchInfo();
	}
	@ModelAttribute(name="MainEmployee")
	public MainEmployee loginUser(@AuthenticationPrincipal MainEmployee  ee) {
		return ee;
	}
	
	@GetMapping("/spe")
	public String searchForm(Model model) { 
		  
		  return "search_2";    
	}
	@PostMapping("/spe")
	public String processSearch(@ModelAttribute(name="searchObj") SearchInfo info,@ModelAttribute(name="pharmas") List<Pharmacy> pharmas,Model model) {
		
		pharmas.clear();
		pharmas=filterResult(info);
		model.addAttribute("result",pharmas.size()+" results found.");
		model.addAttribute("pharmas", pharmas);
		return "search_2";
	} 
	
	@GetMapping("/all")
	public String getAll(Model model) { 
		  return "listPharma";    
	}
	

	public List<Pharmacy> filterResult(SearchInfo info) {
		List<Pharmacy> retResult=new ArrayList<>();
		if(info.isByPharmacyName()) {
			retResult=(List<Pharmacy>)this.pharmaRepo.searchPharmacyByName(info.getQueryValue());
		}
		if(info.isByLocationName()){
			List<PharmacBranch> branches=branchRepo.searchPharmacyByLocation(info.getQueryValue());
			for(PharmacBranch branch:branches) {
				retResult.add(pharmaRepo.findById(branch.getPharmacy_license_id()).get());
			}
		}
		if(info.isByDrugName()) {
			List<Drugs> drugs=sortDrugs(info);
			for(Drugs drug: drugs) {
				retResult.add(pharmaRepo.findById(drug.getPharmacy_license_id()).get());
			}
		}
		return retResult;
	}
	
	public List<Drugs> sortDrugs(SearchInfo info){
		List<Drugs> br=drugRepo.findDrugsByName(info.getQueryValue());
		if(info.isByAmount()) {
			br=drugRepo.findDrugsByAmount(info.getQueryValue());
		}else if(info.isByPrice()) {
			br=drugRepo.findDrugsByPrice(info.getQueryValue());
		}else if(info.isByEdate()) {
			br=drugRepo.findDrugsByEdate(info.getQueryValue());
		}
		return br;
	}

}
