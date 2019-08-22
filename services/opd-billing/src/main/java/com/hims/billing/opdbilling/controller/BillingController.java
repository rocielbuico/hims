package com.hims.billing.opdbilling.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hims.billing.opdbilling.entities.Billing;
import com.hims.billing.opdbilling.exception.ResourceNotFoundException;
import com.hims.billing.opdbilling.repos.BillingRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1")
@Api(value="OPD Billing System", description="Operations pertaining to OPD Billing Management System")
public class BillingController {
	
	@Autowired
	private BillingRepository billingRepository;

	@ApiOperation(value = "View a list of available billing", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/billing")
	public List<Billing> getAllBilling() {
		return billingRepository.findAll();
	}


	@ApiOperation(value = "Get billing by Id")
	@GetMapping("/billing/{id}")
	public ResponseEntity<Billing> getBillingById(
			@ApiParam(value = "Billing id from which billing object will retrieve", required = true)
			@PathVariable(value = "id") Long billingId)
			throws ResourceNotFoundException {
		Billing billing = billingRepository.findById(billingId)
				.orElseThrow(() -> new ResourceNotFoundException("Billing not found for this id :: " + billingId));
		return ResponseEntity.ok().body(billing);
	}

	@ApiOperation(value = "Add billing")
	@PostMapping("/billing")
	public Billing createBilling(
			@ApiParam(value = "Billing object store in database table", required = true)
			@Valid @RequestBody Billing billing) {
		return billingRepository.save(billing);
	}

	@ApiOperation(value = "Update Billing")
	@PutMapping("/billing/{id}")
	public ResponseEntity<Billing> updateBilling(
			@ApiParam(value = "Billing Id to update billing object", required = true)
			@PathVariable(value = "id") Long billingId,
			@ApiParam(value = "Update billing object", required = true)
			@Valid @RequestBody Billing billingDetails) throws ResourceNotFoundException {
		Billing billing = billingRepository.findById(billingId)
				.orElseThrow(() -> new ResourceNotFoundException("Billing not found for this id :: " + billingId));

		billing.setCustomerId(billingDetails.getCustomerId());
		billing.setAmount(billingDetails.getAmount());
		billing.setCreatedDate(billingDetails.getCreatedDate());
		billing.setTransactedBy(billingDetails.getTransactedBy());
		billing.setStatus(billingDetails.getStatus());
		billing.setUpdatedDate(billingDetails.getUpdatedDate());
		billing.setUpdatedBy(billingDetails.getUpdatedBy());
		

		final Billing updatedBilling = billingRepository.save(billing);
		return ResponseEntity.ok(updatedBilling);
	}

	@ApiOperation(value = "Delete Billing")
	@DeleteMapping("/billing/{id}")
	public Map<String, Boolean> deleteBilling(
			@ApiParam(value = "Billing Id from which billing object will delete from database table", required = true)
			@PathVariable(value = "id") Long billingId)
			throws ResourceNotFoundException {
		Billing billing = billingRepository.findById(billingId)
				.orElseThrow(() -> new ResourceNotFoundException("Billing not found for this id :: " + billingId));

		billingRepository.delete(billing);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
