package com.team.pharmaC.main.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.team.pharmaC.main.security.MainEmployee;

@Controller

public class IndexController {

	@ModelAttribute(name="MainEmployee")
	public MainEmployee loginUser(@AuthenticationPrincipal MainEmployee  ee) {
		return ee;
	}
	
	@GetMapping("/")
	public String pharmacForm() { 
	  return "index";    
	}
}
