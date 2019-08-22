package com.hims.billing.opdbilling.entities;

import java.util.List;

import org.springframework.data.annotation.Id;

public class OpdBilling {

	@Id
	String id;
	
	List<Hospital> hospitalList;

	


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Hospital> getHospitalList() {
		return hospitalList;
	}

	public void setHospitalList(List<Hospital> hospitalList) {
		this.hospitalList = hospitalList;
	}
	
	

}

