package com.team.pharmaC.main.configurations;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import com.team.pharmaC.main.repository.PharmacyRepository;

@Component
public class PharmacyCount implements InfoContributor {
	public PharmacyRepository repo;
	
	@Autowired
	public  PharmacyCount(PharmacyRepository repo) {
		this.repo=repo;
	}

	@Override
	public void contribute(Builder builder) {
		long count=repo.count();
		Map<String,Object> map= new HashMap<>();
		map.put("Count ", count);
		builder.withDetail("Avaliable-Pharmacies", map);
		
	}

}
