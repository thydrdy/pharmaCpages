package com.team.pharmaC;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class PharmacBranch implements Serializable {

	  private static final long serialVersionUID = 1L;
	  @Id
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	  private long branch_no;
	  
	  @NotBlank(message="*Sub City is required")
	  @Size(min=3, message="**Sub City must be at least 3 characters long")
	  private String branchName;
	  
	  @NotBlank(message="*Sub City is required")
	  @Size(min=3, message="**Sub City must be at least 3 characters long")
	  private String subCity;
	  
	  @NotBlank(message="*Kebele is required")
	  @Size(min=5, message="Name must be at least 5 characters long")
	  private String kebele;
	  
	  @NotBlank(message="Official phone number is required")
	  @Size(min=5, message="**official phone number must be at least 5 characters long")
	  private String officialPhoneNo; 
}
