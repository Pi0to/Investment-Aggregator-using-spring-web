package com.piotodev.investment.aggregator.service;

import com.piotodev.investment.aggregator.entities.BillingAdress;
import com.piotodev.investment.aggregator.repository.AccountRepository;
import com.piotodev.investment.aggregator.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountService {

    AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountService(AccountRepository accountRepository,
                          UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public BillingAdress getBillingAdress(String AccountId){

        var accountExists = accountRepository
                .findById(UUID.fromString(AccountId))
                .orElseThrow(() -> new RuntimeException("Account not found"));

        return accountExists.getBillingAdress();

    }
}
