package com.team.pharmaC;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Drugs  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	  @Id
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	  private long drug_id;
	  
	  @NotBlank(message="*Drug name is required")
	  @Size(min=2, message="**Name must be at least 2 characters long")
	  private String Name;
	  
	  @NotBlank(message="*Description is required")
	  @Size(min=10, message="**Please Write some description about the drug and it's side effects ")
	  private String Description;
	  
	  @NotBlank(message="*Expiration Date is required")
	  @Size(min=4, message="**Expiration Date must be given in dd/mm/yy format")
	  private String expiration_date;
	  
	  @NotNull(message="The Amount Field can not be empty")
	  @Min(2)
	  private long amount;
	  
	  @NotNull(message="The price Field can not be empty")
	  @Min(2)	  
	  private long price;
	  
	  @NotBlank(message="*Production Date is required")
	  @Size(min=4, message="**Production Date must be given in dd/mm/yy format")
	  private String production_date;
	  
	  public void clearAll() {
		  this.amount=0;
		  this.Description="";
		  this.drug_id=0;
		  this.expiration_date="";
		  this.Name="";
		  this.price=0;
		  this.production_date="";
	  }
}
