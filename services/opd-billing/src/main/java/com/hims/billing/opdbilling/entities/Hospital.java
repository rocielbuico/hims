package com.hims.billing.opdbilling.entities;

import java.util.List;

public class Hospital {
	
	String hospitalId;
	
	List<Patient> patientList;

	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	public List<Patient> getPatientList() {
		return patientList;
	}

	public void setPatientList(List<Patient> patientList) {
		this.patientList = patientList;
	}

	
}
