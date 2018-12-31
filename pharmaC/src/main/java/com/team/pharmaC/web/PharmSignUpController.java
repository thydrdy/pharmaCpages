package com.team.pharmaC.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team.pharmaC.Pharmacy;
//import lombok.extern.slf4j.Slf4j;
@Controller

public class PharmSignUpController {
  
  @RequestMapping("/pharmacy")
  public String greetingForm(Model model) {
	  model.addAttribute("pharmacy", new Pharmacy());  
	  return "pharmacy";    
  }
  

	@PostMapping("pharmacy")
	public String processOrder(@Valid Pharmacy pharmacy, Errors errors) {
		
		if(errors.hasErrors()) {
			return "pharmacy";
		}
		return "registeredPharmacy";
	}

}
