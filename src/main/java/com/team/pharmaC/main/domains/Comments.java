package com.team.pharmaC.main.domains;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Comments {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private  Long id;
	
	@Email(message="*A valid Email Address is Required")
	private String email;
	
	@NotBlank(message="* name is required")
	@Size(min=5, message="**Name must be at least 5 characters long")
	private String userName;
	
	@NotBlank(message="*comment is required")
	@Size(min=10, message="*comment should be at least 10 characters long")
	private String comment;
	
	private long  pharmacy_license_id;

	private Date dateCommented;
	
	public String formattedDate() {
		SimpleDateFormat format=new SimpleDateFormat("h:mm a - EEE,MMM d");
		return format.format(this.getDateCommented());
	}
}
