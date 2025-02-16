package com.piotodev.investment.aggregator.repository;

import com.piotodev.investment.aggregator.entities.Stock;
import com.piotodev.investment.aggregator.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {
}
