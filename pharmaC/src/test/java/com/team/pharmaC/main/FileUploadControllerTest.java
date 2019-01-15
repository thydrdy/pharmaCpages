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

import com.team.pharmaC.main.controllers.FileUploadController;

@RunWith(SpringRunner.class)
@WebMvcTest(FileUploadController.class)
public class FileUploadControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	  public void testUploadPage() throws Exception {
	    mockMvc.perform(get("/upload"))    
	    
	      .andExpect(status().isOk())  
	      
	      .andExpect(view().name("upload")) 
	      
	      .andExpect(content().string(    
	          containsString("File Upload")));  
	  }


}
