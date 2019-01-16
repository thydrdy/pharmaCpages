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
import org.springframework.ui.Model;

import com.team.pharmaC.main.controllers.pharmacyBranchController;

@RunWith(SpringRunner.class)
@WebMvcTest(pharmacyBranchController.class)
public class PharmacyBranchControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	  public void testLocationsPage(Model model) throws Exception {
	    mockMvc.perform(get("/locations"))    
	    
	      .andExpect(status().isOk())  
	      
	      .andExpect(view().name("locations")) 
	      
	      .andExpect(content().string(    
	          containsString(" ")));  
	  }
	/*
	@Test
    public void showAddBranchForm() throws Exception {
        mockMvc.perform(get("/locations"))
                .andExpect(status().isOk())
                .andExpect(view().name("locations"))
                .andExpect(forwardedUrl("/locations"))
                .andExpect(model().attribute("locations", hasProperty("branchName", nullValue())))
                .andExpect(model().attribute("locations", hasProperty("subCity", isEmptyOrNullString())))
                .andExpect(model().attribute("locations", hasProperty("kebele", isEmptyOrNullString())))
                .andExpect(model().attribute("locations", hasProperty("phoneNumber", isEmptyOrNullString())));
}
*/

}
