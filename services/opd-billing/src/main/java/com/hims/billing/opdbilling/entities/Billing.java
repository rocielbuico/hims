package com.hims.billing.opdbilling.entities;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@Entity
@Table(name = "billing")
@ApiModel(description="All details about the OPD Billing. ")
public class Billing {
	
	@ApiModelProperty(notes = "The database generated OPD billing ID.")
	private long id;
	
	@ApiModelProperty(notes = "The customer ID.")
	private String customerId;
	
	@ApiModelProperty(notes = "The billing total amount.")
	private Double amount;
	
	@ApiModelProperty(notes = "The date billing created.")
	private Date createdDate;
	
	@ApiModelProperty(notes = "The employee id of the person who make the transaction.")
	private String transactedBy;
	
	@ApiModelProperty(notes = "The billing status.")
	private String status;
	
	@ApiModelProperty(notes = "The billing record date updated.")
	private Date updatedDate;
	
	@ApiModelProperty(notes = "The employee id of the person who updated the record.")
	private String updatedBy;

	
	public Billing() {
		
	}
	
	public Billing(String customerId, Double amount, Date createdDate, String transactedBy) {
		this.customerId = customerId;
		this.amount = amount;
		this.createdDate = createdDate;
		this.transactedBy = transactedBy;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "customer_id", nullable = false)
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	@Column(name = "total_amount", nullable = false)
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	@Column(name = "created_dt", nullable = false)
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	@Column(name = "transacted_by", nullable = false)
	public String getTransactedBy() {
		return transactedBy;
	}
	public void setTransactedBy(String transactedBy) {
		this.transactedBy = transactedBy;
	}
	
	@Column(nullable = false)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "updated_dt", nullable = true)
	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Column(name = "updated_by", nullable = true)
	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}


	@Override
	public String toString(){

		String result = "Billing [id=" + id + ",customerId=" + customerId + ",amount=" 
		+ amount + ",createdDate=" + createdDate + ",transactedBy=" 
				+ transactedBy + ",status=" + status + ",updatedDate=" + updatedDate 
				+ ",updatedBy=" + updatedBy + "]";
		
		
		return result;
		
		
	}
	
}
