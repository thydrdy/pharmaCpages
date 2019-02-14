package com.team.pharmaC.main.security;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.team.pharmaC.main.domains.Pharmacy;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@RequiredArgsConstructor
public class MainEmployee implements UserDetails  {//extends Employee 
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private  long   id;
	@NotBlank
	@Digits(integer=10,message="Please Input A Valid Medical Licese Id", fraction = 0)
	private  final String license_id;
	@NotBlank
	@Size(min=5,message=" Username Should Be At  Least 5 character long")
	private  final String username;
	@NotBlank
	@Size(min=8,message="Password Should be At least 8 character long")
	private  final String password;
	@NotBlank
	@Size(min=5,message=" First name Should Be At  Least 5 character long")
	private  final String firstname;
	@NotBlank
	@Size(min=5,message=" Last name Should Be At  Least 5 character long")
	private  final String lastname;  //private  final String authorization_level;
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority("MainEmployee"));
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
//@ManyToMany
//private  Pharmacy main_pharma;

