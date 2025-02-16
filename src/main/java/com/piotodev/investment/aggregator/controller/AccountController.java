package com.piotodev.investment.aggregator.controller;

import com.piotodev.investment.aggregator.entities.BillingAdress;
import com.piotodev.investment.aggregator.service.AccountService;
import com.piotodev.investment.aggregator.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users/{userID}/accounts/{accountId}")
public class AccountController {

    private UserService userService;
    private AccountService accountService;

    public AccountController(UserService userService, AccountService accountService){
        this.userService = userService;
        this.accountService = accountService;
    }

    //Billing Address
    @GetMapping("/billingadress")
    public ResponseEntity<BillingAdress> getBillingAdress(@PathVariable("accountId") String accountId){

        var billingAdress = accountService.getBillingAdress(accountId);

        return ResponseEntity.ok().body(billingAdress);
    }

}
