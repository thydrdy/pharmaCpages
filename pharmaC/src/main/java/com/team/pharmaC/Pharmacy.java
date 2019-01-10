package com.team.pharmaC;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
//import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


import lombok.Data;

@Data
@Entity
public class Pharmacy implements Serializable{

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private long 	 pharmacy_license_id;
 
  @NotBlank(message="*Pharmacy name is required")
  @Size(min=2, message="**Name must be at least 2 characters long")
  private String pharmacyName;
  
  @ManyToMany
  private java.util.Set<PharmacBranch> pharmacLocations= new HashSet<PharmacBranch>() ;
  
  @NotBlank(message="*Official email is required")
  @Size(min=5, message="**Official email must be at least 5 characters long")
  private String officialEmail; 
  
  @NotBlank(message="Password is required")
  @Size(min=5, message="Name must be at least 5 characters long")
  private String password;
  
  @NotBlank(message="Rewrite your password")
  @Size(min=5, message="Name must be at least 5 characters long")
  private String rePassword;
 
  public void addLocation(PharmacBranch loc) {
	  this.pharmacLocations.add(loc);
  }
}
