package com.piotodev.investment.aggregator.repository;

import com.piotodev.investment.aggregator.entities.AccountStock;
import com.piotodev.investment.aggregator.entities.AccountStockId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountStockRepository extends JpaRepository<AccountStock, AccountStockId> {
}
