package com.piotodev.investment.aggregator.repository;

import com.piotodev.investment.aggregator.entities.BillingAdress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BillingAdressRepository extends JpaRepository<BillingAdress, UUID> {
}
