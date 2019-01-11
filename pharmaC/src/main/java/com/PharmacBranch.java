package com.team.pharmaC;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class PharmacBranch implements Serializable{

	private static final long serialVersionUID = 1L;

	  @Id
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	  private long branch_no;
	  
	  @NotBlank(message="*Branch name is required")
	  @Size(min=2, message="**Name must be at least 2 characters long")
	  private String BranchName;
	  
	  @NotBlank(message="*city is required")
	  @Size(min=4, message="**city must be at least 4 characters long")
	  private String city;
	  
	  @NotBlank(message="*subcity is required")
	  @Size(min=4, message="**subcity must be at least 4 characters long")
	  private String subcity;
	  
	  @NotBlank(message="*kebele is required")
	  @Size(min=4, message="**kebele must be at least 5 characters long")
	  private String kebele;
	  
	  //private String location_coordinate;
	  
	  @NotBlank(message="*Branch Office Phone Number is required")
	  @Size(min=10, message="**Branch Office Phone Number must be at least 10 characters long")// it should have  a valid phone
	  private String branch_office_phone_no;
	  
}
