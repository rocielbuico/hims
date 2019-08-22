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

import com.hims.billing.opdbilling.entities.BillingItem;
import com.hims.billing.opdbilling.exception.ResourceNotFoundException;
import com.hims.billing.opdbilling.repos.BillingItemRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1")
@Api(value="OPD Billing System")
public class BillingItemController {
	
	@Autowired
	private BillingItemRepository billingItemRepository;

	@ApiOperation(value = "View a list of available billing", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/billingItems")
	public List<BillingItem> getAllBillingItems() {
		return billingItemRepository.findAll();
	}


	@ApiOperation(value = "Get billing item by Id")
	@GetMapping("/billingItems/{id}")
	public ResponseEntity<BillingItem> getBillingItemById(
			@ApiParam(value = "BillingItem id from which billing object will retrieve", required = true)
			@PathVariable(value = "id") Long billingItemId)
			throws ResourceNotFoundException {
		BillingItem billingItem = billingItemRepository.findById(billingItemId)
				.orElseThrow(() -> new ResourceNotFoundException("BillingItem not found for this id :: " + billingItemId));
		return ResponseEntity.ok().body(billingItem);
	}

	@ApiOperation(value = "Add billing item")
	@PostMapping("/billingItems")
	public BillingItem createBillingItem(
			@ApiParam(value = "BillingItem object store in database table", required = true)
			@Valid @RequestBody BillingItem billingItem) {
		return billingItemRepository.save(billingItem);
	}

	@ApiOperation(value = "Update BillingItem")
	@PutMapping("/billingItems/{id}")
	public ResponseEntity<BillingItem> updateBillingItem(
			@ApiParam(value = "BillingItem Id to update billing item object", required = true)
			@PathVariable(value = "id") Long billingItemId,
			@ApiParam(value = "Update billing item object", required = true)
			@Valid @RequestBody BillingItem billingItemDetails) throws ResourceNotFoundException {
		BillingItem billingItem = billingItemRepository.findById(billingItemId)
				.orElseThrow(() -> new ResourceNotFoundException("BillingItem not found for this id :: " + billingItemId));

		billingItem.setBilling(billingItemDetails.getBilling());
		billingItem.setItemName(billingItemDetails.getItemName());
		billingItem.setPrice(billingItemDetails.getPrice());
		billingItem.setQty(billingItemDetails.getQty());
		

		final BillingItem updatedBillingItem = billingItemRepository.save(billingItem);
		return ResponseEntity.ok(updatedBillingItem);
	}

	@ApiOperation(value = "Delete BillingItem")
	@DeleteMapping("/billingItems/{id}")
	public Map<String, Boolean> deleteBillingItem(
			@ApiParam(value = "BillingItem Id from which billing item object will delete from database table", required = true)
			@PathVariable(value = "id") Long billingItemId)
			throws ResourceNotFoundException {
		BillingItem billingItem = billingItemRepository.findById(billingItemId)
				.orElseThrow(() -> new ResourceNotFoundException("BillingItem not found for this id :: " + billingItemId));

		billingItemRepository.delete(billingItem);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
