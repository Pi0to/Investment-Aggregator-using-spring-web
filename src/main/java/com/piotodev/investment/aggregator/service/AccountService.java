package com.piotodev.investment.aggregator.service;

import com.piotodev.investment.aggregator.controller.dto.BillingAdressDTO;
import com.piotodev.investment.aggregator.entities.BillingAdress;
import com.piotodev.investment.aggregator.repository.AccountRepository;
import com.piotodev.investment.aggregator.repository.BillingAdressRepository;
import com.piotodev.investment.aggregator.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountService {

    AccountRepository accountRepository;
    private BillingAdressRepository billingAdressRepository;


    public AccountService(AccountRepository accountRepository,
                          BillingAdressRepository billingAdressRepository) {
        this.accountRepository = accountRepository;
        this.billingAdressRepository = billingAdressRepository;
    }

    public BillingAdress getBillingAdress(String accountId){

        var billingAdress = billingAdressRepository.
                findById(UUID.fromString(accountId))
                .orElseThrow(() -> new RuntimeException("Account not found"));

        return billingAdress;

    }

    public void updateBillingAdress(String accountId, BillingAdressDTO billingAdressDTO){

        var billingAdress = billingAdressRepository
                .findById(UUID.fromString(accountId))
                .orElseThrow(() -> new RuntimeException("Account not found"));

        billingAdress.setStreet(billingAdressDTO.street());
        billingAdress.setNumber(billingAdressDTO.number());

        billingAdressRepository.save(billingAdress);

    }
}
