package com.team.pharmaC;

import lombok.Data;

@Data
public class SearchInfo {
	
	private String  queryValue;
	private boolean byPharmacyName;
	private boolean byDrugName=true;
	private boolean byLocationName;
	private boolean byPrice=true;
	private boolean byEdate;
	private boolean byAmount;
	private String buff;
	public SearchInfo setAllTrue() {
		this.byPharmacyName=true;
		this.byLocationName=true;
		this.byEdate=true;
		this.byAmount=true;
		return this;
	}
}
