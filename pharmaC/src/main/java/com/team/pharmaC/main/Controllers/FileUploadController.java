package com.team.pharmaC.main.Controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.team.pharmaC.Pharmacy;

@Controller
@RequestMapping("/upload")
@SessionAttributes("pharmacy")
public class FileUploadController {
	
	
    @GetMapping("/image")
    public String index() {
        return "upload";
    }

    @PostMapping("/image") 
    public String singleFileUpload(@RequestParam("file") MultipartFile file,RedirectAttributes redirectAttributes, Pharmacy pharmacy) {
    	String UPLOADED_FOLDER = "C:\\Users\\Maroc\\Documents\\workspace-sts\\pharmacy_sql_db\\uploads\\";
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

        return "upload";
    }

}
