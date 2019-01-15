package com.team.pharmaC;

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

import com.team.pharmaC.main.controllers.PharmacyController;

@RunWith(SpringRunner.class)
@WebMvcTest(PharmacyController.class)
public class PharmacyControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	  public void testPharmacyPage() throws Exception {
	    mockMvc.perform(get("/pharmacy"))    
	    
	      .andExpect(status().isOk())  
	      
	      .andExpect(view().name("pharmacy")) 
	      
	      .andExpect(content().string(    
	          containsString("Pharmacy")));  
	  }
	/*
	@Test
    public void showAddBranchForm() throws Exception {
        mockMvc.perform(get("/pharmacy"))
                .andExpect(status().isOk())
                .andExpect(view().name("pharmacy"))
                .andExpect(forwardedUrl("/pharmacy"))
                .andExpect(model().attribute("pharmacy", hasProperty("pharmacyName", nullValue())))
                .andExpect(model().attribute("pharmacy", hasProperty("email", isEmptyOrNullString())))
                .andExpect(model().attribute("pharmacy", hasProperty("description", isEmptyOrNullString())))
                .andExpect(model().attribute("pharmacy", hasProperty("phoneNumber", isEmptyOrNullString())));
}
*/

}
