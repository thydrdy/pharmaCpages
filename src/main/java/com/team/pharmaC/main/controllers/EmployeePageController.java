package com.team.pharmaC.main.controllers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.team.pharmaC.main.domains.Comments;
import com.team.pharmaC.main.domains.Drugs;
import com.team.pharmaC.main.domains.PharmacBranch;
import com.team.pharmaC.main.domains.Pharmacy;
import com.team.pharmaC.main.domains.Rating;
import com.team.pharmaC.main.domains.SearchInfo;
import com.team.pharmaC.main.security.MainEmployee;
import com.team.pharmaC.main.service.CommentsServiceImpl;
import com.team.pharmaC.main.service.DrugsServiceImpl;
import com.team.pharmaC.main.service.PharmacyBranchServiceImpl;
import com.team.pharmaC.main.service.PharmacyServiceImpl;
import com.team.pharmaC.main.service.RatingServiceImpl;

@Controller 
@RequestMapping("employee_page")
@SessionAttributes("MainEmployee")
public class EmployeePageController {
	
	private DrugsServiceImpl drugrep;
	private PharmacyBranchServiceImpl branchService;
	private PharmacyServiceImpl repo;
	private CommentsServiceImpl commentRepo;
	private RatingServiceImpl ratingRepo;
	
	private Pharmacy phObj;
	private Long id;
	private Long Br_id;
	private Long Dr_id;
	
	@Autowired
	public EmployeePageController(DrugsServiceImpl drugrep,PharmacyBranchServiceImpl branchService,PharmacyServiceImpl repo,CommentsServiceImpl commentRepo,RatingServiceImpl ratingRepo) {
		this.drugrep=drugrep;
		this.branchService=branchService;
		this.repo=repo;
		this.commentRepo=commentRepo;
		this.ratingRepo=ratingRepo;
	}
	// ----------
	@ModelAttribute(name="MainEmployee")
	public MainEmployee loginUser(@AuthenticationPrincipal MainEmployee  ee) {
		return ee;
	}
	@ModelAttribute(name="searchObj")
	public SearchInfo searchInfo() {
		return new SearchInfo();
	}
	// ----------
	
	@GetMapping("/EmpAccess")
	public String getEmpPharmacy(@RequestParam("id") Long id,Model model) {
		phObj=repo.findById(id).get();
		this.id=id;
		model.addAttribute("pharmaC",phObj);
		List<Comments> comments=commentRepo.findCommentsByPhID(this.id);
		model.addAttribute("pharmacyComments",comments);
		Rating rate=ratingRepo.findRatingById(this.id);
		if(rate==null) 
			rate=new Rating();
		phObj.setRatingObject(rate);
		return "emp_details";
	}
	// Edit Branch
	@GetMapping("/EmpAccessBrEdit")
	public String editEmpPharmacyBranch(@RequestParam("id") Long id,Model model) {
		PharmacBranch br=this.branchService.findById(id).get();
		model.addAttribute("brObj",br);
		this.Br_id=id;
		System.out.println(id);
		return "empBranch";
	}
	@PostMapping("/EmpAccessBrEdit")
	public String proccessBr(@Valid @ModelAttribute("brObj") PharmacBranch pharmacybr,  Errors errors,Model model) {
		if(errors.hasErrors()) {
			return "empBranch";
		}
		//---------------
		System.out.println(" "+this.Br_id);
		this.branchService.updateProfile(pharmacybr.getBranchName(),pharmacybr.getCity(),
				pharmacybr.getSubcity(),pharmacybr.getKebele(),pharmacybr.getBranch_office_phone_no(),this.Br_id);
		
		return "redirect:/employee_page/EmpAccess?id="+this.id;//"redirect:/employee_page/EmpAccess?id="+this.id;
	}
	
	@GetMapping("/EmpAccessBrDel")
	public String delEmpPharmacyBranch(@RequestParam("id") Long br_id) {
		for(PharmacBranch br:phObj.getPharmacBranches()) {
			if(br.getBranch_no()==br_id) {
				phObj.getPharmacBranches().remove(br);
				System.out.println("----------Made it here---------");}
		}
		repo.save(phObj);
		PharmacBranch branch=branchService.findById(br_id).get();
		branchService.delete(branch);
		return  "redirect:/employee_page/EmpAccess?id="+this.id;
	}
	
	// add new Branch
	@GetMapping("/EmpAccessBrAdd")
	public String addEmpPharmacyBranch(Model model) {
		model.addAttribute("brObj2",new PharmacBranch());
		return "empAddBranch";
	}
	@PostMapping("/EmpAccessBrAdd")
	public String proccessAddBr(@Valid @ModelAttribute("brObj2") PharmacBranch pharmacybr,  Errors errors,Model model) {
		if(errors.hasErrors()) {
			return "empAddBranch";
		}
		this.branchService.save(pharmacybr);
		phObj.getPharmacBranches().add(pharmacybr);
		repo.save(phObj);
		return  "redirect:/employee_page/EmpAccess?id="+this.id;
	}
	// Edit Drugs
	
	@GetMapping("/EmpAccessDrugEdit")
	public String edEmpPharmacyDrug(@RequestParam("id") Long id,Model model) {
		Drugs dr=this.drugrep.findById(id).get();
		model.addAttribute("drObj",dr);
		this.Dr_id=id;
		return "empDrug";
	}
	@PostMapping("/EmpAccessDrugEdit")
	public String proccessDr(@Valid @ModelAttribute("drObj") Drugs drs,  Errors errors) {
		if(errors.hasErrors()) {
			return "empDrug";
		}
		this.drugrep.updateProfile(drs.getName(), drs.getDescription(), drs.getExpiration_date()
				, drs.getAmount(), drs.getPrice(), drs.getProduction_date(), this.Dr_id);
		return  "redirect:/employee_page/EmpAccess?id="+this.id;
	}
	@GetMapping("/EmpAccessDrugDel")
	public String delEmpPharmacyDrug(@RequestParam("id") Long dr_id,Model model) {
		for(Drugs dr:phObj.getPharmacDrugs()) {
			if(dr.getDrug_id()==dr_id) {
				phObj.getPharmacDrugs().remove(dr);
				System.out.println("----------Made it here---------");}
		}
//		for(PharmacBranch branch:phObj.getPharmacBranches()) {
//			for(Drugs dr:branch.getPharmacDrugs()) {
//				if(dr.getDrug_id()==dr_id) {
//					branch.getPharmacDrugs().remove(dr);
//					System.out.println("----------Made it here---------");}
//			}
//		}
		repo.save(phObj);
		Drugs drug=drugrep.findById(dr_id).get();
		drugrep.delete(drug);
		return  "redirect:/employee_page/EmpAccess?id="+this.id;
	}
	//-------------
	
	// add new
	@GetMapping("/EmpAccessDrAdd")
	public String addEmpPharmacyDrug(Model model) {
		model.addAttribute("drObj2", new Drugs());
		return "empAddDrug";
	}
	@PostMapping("/EmpAccessDrAdd")
	public String proccessDrAdd(@Valid @ModelAttribute("drObj2") Drugs drs,  Errors errors) {
		if(errors.hasErrors()) {
			return "empAddDrug";
		}
		this.drugrep.save(drs);
		phObj.addDrugs(drs);
		repo.save(phObj);
		return  "redirect:/employee_page/EmpAccess?id="+this.id;
	}
	
	// employee Search
	
	@PostMapping("/search/emp")
	public String findDrug2(@ModelAttribute("searchObj") SearchInfo info, Model model) {
		phObj=repo.findById(id).get();
		phObj.setPharmacDrugs(findDrugs(info.getQueryValue()));
		model.addAttribute("pharmaC",phObj);
		List<Comments> comments=commentRepo.findCommentsByPhID(this.id);
		model.addAttribute("pharmacyComments",comments);
		Rating rate=ratingRepo.findRatingById(this.id);
		if(rate==null) 
			rate=new Rating();
		phObj.setRatingObject(rate);
		return "emp_details";
	}
	
	public  Set<Drugs> findDrugs(String dr_name){
		return phObj.getPharmacDrugs().stream().filter(i -> i.getName().toUpperCase().contains(dr_name.toUpperCase())).collect(Collectors.toSet());
		 
	}
	
	// Comment Section
	@GetMapping("/DelComment")
	public String deleteComment(@RequestParam("id") Long c_id,Model model) {
		for(Comments com:phObj.getPharmacyComment()) {
			if(com.getId()==c_id) {
				phObj.getPharmacyComment().remove(com);
				System.out.println("----------Made it here---------");}
		}
		repo.save(phObj);
		Comments comment=commentRepo.findById(c_id).get();
		commentRepo.delete(comment);
		return "redirect:/employee_page/EmpAccess?id="+this.id;
	}
}
