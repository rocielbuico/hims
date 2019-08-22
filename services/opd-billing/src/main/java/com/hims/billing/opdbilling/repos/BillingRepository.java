package com.hims.billing.opdbilling.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hims.billing.opdbilling.entities.Billing;


@Repository
public interface BillingRepository extends JpaRepository<Billing, Long>{

}
