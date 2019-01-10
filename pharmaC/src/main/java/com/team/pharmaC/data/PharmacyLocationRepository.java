package com.team.pharmaC.data;

import org.springframework.data.repository.CrudRepository;

import com.team.pharmaC.PharmacBranch;
import com.team.pharmaC.Pharmacy;

public interface PharmacyLocationRepository  extends CrudRepository<PharmacBranch,Long>{

}
