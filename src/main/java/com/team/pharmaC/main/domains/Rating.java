package com.team.pharmaC.main.domains;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.micrometer.core.instrument.util.MathUtils;
import lombok.Data;

@Data
@Entity
public class Rating {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private  Long id;
	
	private long  pharmacy_license_id;
	private long  no_of_viewers;
	private double   rate_no;
	
	public void calculateRate(int rateNo) {
		this.rate_no+=rateNo/(double)this.no_of_viewers;

	}
	public void incrementViewers() {
		this.no_of_viewers+=1;
	}
	public double getRate_no() {
		double r=Math.min(5.0, rate_no);
		BigDecimal bd= new BigDecimal(r);
		bd=bd.round(new MathContext(2));
		return bd.doubleValue();
	}
}
