package com.team.pharmaC.main.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.pharmaC.main.domains.Drugs;
import com.team.pharmaC.main.domains.Suggestion;
import com.team.pharmaC.main.service.DrugsServiceImpl;

@RestController
@RequestMapping(path="/api/suggest", produces="application/json")
public class SuggestionController {
	private DrugsServiceImpl drugService;
	
	@Autowired
	public SuggestionController(DrugsServiceImpl drugService) {
		this.drugService=drugService;
	}
	
	@PostMapping(consumes="application/json")
	public Suggestion saveTaco(@RequestBody Suggestion suggest) {
		List<Drugs> drugs= drugService.findDrugsByName(suggest.getQuery());
		List<Suggestion> suggestions= new ArrayList<Suggestion>();
		for(Drugs drug:drugs) {
			suggestions.add(new Suggestion(drug.getName()));
		}
		System.out.println("--------sent ------- "+suggestions.get(0).getAnswer());
		return suggestions.get(0);
	}
	
//	@PostMapping(consumes="application/json")
//	public List<Suggestion> saveTaco(@RequestBody Suggestion suggest) {
//		List<Drugs> drugs= drugService.findDrugsByName(suggest.getQuery());
//		List<Suggestion> suggestions= new ArrayList<Suggestion>();
//		for(Drugs drug:drugs) {
//			suggestions.add(new Suggestion(drug.getName()));
//		}
//		return suggestions;
//	}
}
