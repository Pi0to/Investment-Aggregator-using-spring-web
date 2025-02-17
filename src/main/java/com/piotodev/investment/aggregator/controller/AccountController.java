package com.piotodev.investment.aggregator.controller;

import com.piotodev.investment.aggregator.controller.dto.BillingAdressDTO;
import com.piotodev.investment.aggregator.entities.BillingAdress;
import com.piotodev.investment.aggregator.repository.BillingAdressRepository;
import com.piotodev.investment.aggregator.service.AccountService;
import com.piotodev.investment.aggregator.service.UserService;
import org.springframework.data.web.ReactiveOffsetScrollPositionHandlerMethodArgumentResolver;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/billingadress")
    public ResponseEntity<Void> updateBillingAdress(@PathVariable("accountId") String accountId, @RequestBody BillingAdressDTO billingAdress){

        accountService.updateBillingAdress(accountId, billingAdress);

        return ResponseEntity.ok().build();
    }



}
