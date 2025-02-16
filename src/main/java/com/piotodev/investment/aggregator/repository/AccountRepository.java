package com.piotodev.investment.aggregator.repository;

import com.piotodev.investment.aggregator.entities.Account;
import com.piotodev.investment.aggregator.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
}
