package com.team.pharmaC;

//import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

//import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

@Data
public class Pharmacy {

  @NotBlank(message="*Pharmacy name is required")
  @Size(min=2, message="**Name must be at least 2 characters long")
  private String pharmacyName;
  
  @NotBlank(message="*Sub City is required")
  @Size(min=3, message="**Sub City must be at least 3 characters long")
  private String subCity;
  
  @NotBlank(message="*Kebele is required")
  //@Size(min=5, message="Name must be at least 5 characters long")
  private String kebele;
  
  @NotBlank(message="*Official email is required")
  @Size(min=5, message="**Official email must be at least 5 characters long")
  private String officialEmail;
  
  @NotBlank(message="Official phone number is required")
  //@Size(min=5, message="**official phone number must be at least 5 characters long")
  private String officialPhoneNo; 
  
  @NotBlank(message="Password is required")
  @Size(min=5, message="Name must be at least 5 characters long")
  private String password;
  
  @NotBlank(message="Rewrite your password")
  @Size(min=5, message="Name must be at least 5 characters long")
  private String rePassword;
 
}
