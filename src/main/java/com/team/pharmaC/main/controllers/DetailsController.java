package com.team.pharmaC.main.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.team.pharmaC.main.domains.Pharmacy;
import com.team.pharmaC.main.domains.Rating;
import com.team.pharmaC.main.domains.SearchInfo;
import com.team.pharmaC.main.security.MainEmployee;
import com.team.pharmaC.main.service.CommentsServiceImpl;
import com.team.pharmaC.main.service.DrugsServiceImpl;
import com.team.pharmaC.main.service.PharmacyServiceImpl;
import com.team.pharmaC.main.service.RatingServiceImpl;

@Controller 
@RequestMapping("Details")
@SessionAttributes("MainEmployee")
public class DetailsController {
	private PharmacyServiceImpl repo;
	private DrugsServiceImpl drugRepo;
	private CommentsServiceImpl commentRepo;
	private RatingServiceImpl ratingRepo;
	
	private Pharmacy phObj;
	private Long id;

	@Autowired
	public DetailsController(PharmacyServiceImpl repo,DrugsServiceImpl drugRepo,CommentsServiceImpl commentRepo,RatingServiceImpl ratingRepo) {
		this.repo=repo;
		this.drugRepo=drugRepo;
		this.commentRepo=commentRepo;
		this.ratingRepo=ratingRepo;
	}

	
	// Models -------------
	@ModelAttribute(name="searchObj")
	public SearchInfo searchInfo() {
		return new SearchInfo();
	}
	@ModelAttribute(name="MainEmployee")
	public MainEmployee loginUser(@AuthenticationPrincipal MainEmployee  ee) {
		return ee;
	}
	@ModelAttribute(name="drugs")
	public List<Drugs> drugInFO() {
		return new  ArrayList<Drugs>();
	}
	@ModelAttribute(name="commentObj")
	public Comments commentObj() {
		return new  Comments();
	}
	
	// Controllers-----
	@GetMapping
	public String deatails(@RequestParam("id") Long id,Model model) {
		System.out.println("ID : "+id);
		phObj=repo.findById(id).get();
		this.id=id;
		model.addAttribute("pharmaC",phObj);
		model.addAttribute("pharmaCDrugs",phObj.getPharmacDrugs());
		List<Comments> comments=commentRepo.findCommentsByPhID(this.id);
		model.addAttribute("pharmacyComments",comments);
		Rating rate=ratingRepo.findRatingById(this.id);
		if(rate==null) 
			rate=new Rating();
		rate.incrementViewers();
		ratingRepo.save(rate);
		phObj.setRatingObject(rate);
		return "details";
	}
	
	@PostMapping("/search")
	public String findDrug(@ModelAttribute("searchObj") SearchInfo info, Model model) {
		phObj=repo.findById(id).get();
		model.addAttribute("pharmaC",phObj);
		model.addAttribute("pharmaCDrugs",findDrugs(info));
		List<Comments> comments=commentRepo.findCommentsByPhID(this.id);
		model.addAttribute("pharmacyComments",comments);
		Rating rate=ratingRepo.findRatingById(this.id);
		if(rate==null) 
			rate=new Rating();
		phObj.setRatingObject(rate);
		return "details";
	}
	
	// Search Section -----
	public  List<Drugs> findDrugs(SearchInfo info){
		List<Drugs> retResult=new ArrayList<>();
		List<Drugs> drugs=sortDrugs(info);
		for(Drugs drug: drugs) {
			if(drug.getPharmacy_license_id()==id)
				retResult.add(drug);
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
	
	// Comment Section  --------
	
	@PostMapping("/saveComment")
	public String saveComment(@Valid @ModelAttribute("commentObj") Comments comment,Errors errors,Model model) {
		model.addAttribute("pharmaC",phObj);
		model.addAttribute("pharmaCDrugs",phObj.getPharmacDrugs());
		if(errors.hasErrors()) {
			return "details";
		}
		comment.setDateCommented(new Date());
		comment.setPharmacy_license_id(this.id);
		this.commentRepo.save(comment);
		Pharmacy pharma=repo.findById(this.id).get();
		pharma.getPharmacyComment().add(comment);
		repo.save(pharma);
		List<Comments> comments=commentRepo.findCommentsByPhID(this.id);
		model.addAttribute("pharmacyComments",comments);
		Rating rate=ratingRepo.findRatingById(this.id);
		if(rate==null) 
			rate=new Rating();
		phObj.setRatingObject(rate);
		return "details";
	}
	
	// Rating Sections
	@GetMapping("/rating")
	public String ratePharmacy(@RequestParam("star") int star,Model model) {
		phObj=repo.findById(this.id).get();
		Rating rate=ratingRepo.findRatingById(id);
		if(rate==null) {
			rate=new Rating();
		}
		System.out.println("star "+star);
		rate.setPharmacy_license_id(this.id);
		rate.incrementViewers();
		rate.calculateRate(star);
		rate=ratingRepo.save(rate);
		phObj.setPharmacyRatingId(rate.getId()+"");
		repo.save(phObj);
		//----------------------------------------------//
		phObj=repo.findById(this.id).get();
		model.addAttribute("pharmaC",phObj);
		model.addAttribute("pharmaCDrugs",phObj.getPharmacDrugs());
		List<Comments> comments=commentRepo.findCommentsByPhID(this.id);
		model.addAttribute("pharmacyComments",comments);
		phObj.setRatingObject(rate);
		return "details";
	}
	
}
