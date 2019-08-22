package com.hims.billing.opdbilling.entities;

import java.util.List;

public class Patient {
	
	String patientId;
	
	List<Billing> billingItemList;

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public List<Billing> getBillingItemList() {
		return billingItemList;
	}

	public void setBillingItemList(List<Billing> billingItemList) {
		this.billingItemList = billingItemList;
	}
	
	

}
