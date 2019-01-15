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

import com.team.pharmaC.main.controllers.DrugController;

@RunWith(SpringRunner.class)
@WebMvcTest(DrugController.class)
public class DrugControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	  public void testDrugPage() throws Exception {
	    mockMvc.perform(get("/drug"))    
	    
	      .andExpect(status().isOk())  
	      
	      .andExpect(view().name("drug")) 
	      
	      .andExpect(content().string(    
	          containsString("Drug")));  
	  }
	/*
	@Test
    public void showAddDrugForm() throws Exception {
        mockMvc.perform(get("/drug"))
                .andExpect(status().isOk())
                .andExpect(view().name("drug"))
                .andExpect(forwardedUrl("/drug"))
                .andExpect(model().attribute("drug", hasProperty("name", nullValue())))
                .andExpect(model().attribute("drug", hasProperty("expireDate", isEmptyOrNullString())))
                .andExpect(model().attribute("drug", hasProperty("price", isEmptyOrNullString())));
}
*/

}
