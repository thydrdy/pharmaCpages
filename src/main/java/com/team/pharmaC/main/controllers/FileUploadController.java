package com.team.pharmaC.main.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.team.pharmaC.main.domains.Pharmacy;
import com.team.pharmaC.main.security.MainEmployee;

@Controller
@RequestMapping("/upload")
@SessionAttributes("pharmacy")
public class FileUploadController {
	@ModelAttribute(name="MainEmployee")
	public MainEmployee loginUser(@AuthenticationPrincipal MainEmployee  ee) {
		return ee;
	}
	
    @GetMapping("/image")
    public String index(Model model) {
    	model.addAttribute("mssage", "Add An Image To Your Profile");
        return "upload";
    }

    @PostMapping("/image") 
    public String singleFileUpload(@RequestParam("file") MultipartFile file,RedirectAttributes redirectAttributes, Pharmacy pharmacy,Model model) {
    	String UPLOADED_FOLDER = "uploads\\";
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "upload";
        }
        try {

            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER+ file.getOriginalFilename());
            pharmacy.addimage(file.getOriginalFilename());
            System.out.println(path.toString());
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("mssage", "Add Another Image To Your Profile");
        return "upload";
    }

}
