package com.team.pharmaC.main;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.team.pharmaC.main.controllers.SearchController;

@RunWith(SpringRunner.class)
@WebMvcTest(SearchController.class)
public class SearchControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testSearchPage() throws Exception {
	    mockMvc.perform(get("/search"))    
	    
	      .andExpect(status().isOk())  
	      
	      .andExpect(view().name("search")) 
	      
	      .andExpect(content().string(    
	          containsString("Pharmacy lists")));  
	  }
	
	
}
