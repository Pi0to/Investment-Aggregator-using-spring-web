package com.piotodev.investment.aggregator.controller;

import com.piotodev.investment.aggregator.controller.dto.AccountStockOutDTO;
import com.piotodev.investment.aggregator.controller.dto.AssociateStockDTO;
import com.piotodev.investment.aggregator.controller.dto.CreateAccountDTO;
import com.piotodev.investment.aggregator.entities.AccountStock;
import com.piotodev.investment.aggregator.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/{accountId}")
    public ResponseEntity<Void> associateStock(@PathVariable("accountId") String accountId, @RequestBody AssociateStockDTO associateStockDTO){

        accountService.associateStock(accountId, associateStockDTO);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<List<AccountStockOutDTO>> getStocks(@PathVariable("accountId") String accountId){

        var stocks = accountService.getStocks(accountId);

        var stocksOut = stocks.stream().map(x -> new AccountStockOutDTO(x.getQuantity(), x.getStock())).toList();

        return ResponseEntity.ok().body(stocksOut);
    }
}
