package com.team.pharmaC;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
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
  
  @Email(message="* Please Enter a Valid Email Address")
  private String office_email;
  
  @NotBlank(message="*Office Phone Number is required")
  @Size(min=10, message="**Number must be at least 10 characters long")// it should have  a valid phone
  private String office_phone_no;
  
  //private String HQ_location;
  
  @NotBlank(message="*This Field is required")
  @Size(min=10, message="**please write a short description about your pharmacy..")
  private String Description;
  
  private java.util.ArrayList<String> profileImages= new java.util.ArrayList<String>();
  
  @ManyToMany
  private java.util.Set<PharmacBranch> pharmacBranches= new HashSet<PharmacBranch>();
  
  @ManyToMany
  private java.util.Set<Drugs> pharmacDrugs= new HashSet<Drugs>();
  
 
  public void addLocation(PharmacBranch loc) {
	  this.pharmacBranches.add(loc);
  }
  
  public void addDrugs(Drugs drug) {
	  this.pharmacDrugs.add(drug);
  }
  
  public void addimage(String path) {
	  this.profileImages.add(path);
  }
  
}
