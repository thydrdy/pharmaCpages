package com.team.pharmaC.main.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team.pharmaC.main.domains.Drugs;
import com.team.pharmaC.main.domains.PharmacBranch;
import com.team.pharmaC.main.domains.Pharmacy;
import com.team.pharmaC.main.domains.SearchInfo;
import com.team.pharmaC.main.repository.DrugsRepository;
import com.team.pharmaC.main.repository.PharmacyBranchRepository;
import com.team.pharmaC.main.repository.PharmacyRepository;


@Controller
@RequestMapping("/search")
public class SearchController {
	private PharmacyRepository pharmaRepo;
	private PharmacyBranchRepository branchRepo;
	private DrugsRepository drugRepo;
	@Autowired
	public SearchController(PharmacyRepository repo,PharmacyBranchRepository branchRepo,DrugsRepository drugRepo) {
		this.pharmaRepo=repo;
		this.branchRepo=branchRepo;
		this.drugRepo=drugRepo;
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
		
		  return "search_2";    
	}
	@PostMapping("/spe")
	public String processSearch(@ModelAttribute(name="searchObj") SearchInfo info,@ModelAttribute(name="pharmas") List<Pharmacy> pharmas,Model model) {

		pharmas.clear();
		System.out.println(info.getQueryValue()+" isByAmount"+info.isByAmount()+" isByDrugName"+info.isByDrugName()+"isByPharmacyName "+info.isByPharmacyName()+" price"+info.isByPrice());
		pharmas=filterResult(info);
		System.out.println(pharmas.size()+" results found");
		model.addAttribute("result",pharmas.size()+" results found.");
		model.addAttribute("pharmas", pharmas);
		return "search_2";
	} 
	
	@GetMapping("/all")
	public String getAll(Model model) { 
		
		  return "listPharma";    
	}
	
	public List<Pharmacy> filterResult(SearchInfo info) {
		List<Pharmacy> result = new ArrayList<Pharmacy>();
		if(info.isByPharmacyName()) {
			result=(List<Pharmacy>)this.pharmaRepo.searchPharmacyByName(info.getQueryValue());}
		if(info.isByLocationName()){
			List<PharmacBranch> br=branchRepo.searchPharmacyByLocation(info.getQueryValue());
			List<Pharmacy> ph=(List<Pharmacy>)pharmaRepo.findAll();
			for(Pharmacy p: ph) {
				for(PharmacBranch b: p.getPharmacBranches()) {
					for(PharmacBranch bs:br) {
						if(bs.getBranch_no()==b.getBranch_no()) {
							result.add(p);
							break;
						}
					}
				}
			}
		}
		if(info.isByDrugName()) {
			List<Drugs> br=sortDrugs(info);
			List<Pharmacy> ph=(List<Pharmacy>)pharmaRepo.findAll();
			for(Pharmacy p: ph) {
				for(Drugs bs:br) {
					for(Drugs b: p.getPharmacDrugs()) {
						if(bs.getName()==b.getName()) {
							result.add(p);
							break;
						}
					}
				}
			}
		}
		
		return result;
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
