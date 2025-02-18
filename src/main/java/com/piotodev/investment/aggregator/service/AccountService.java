package com.piotodev.investment.aggregator.service;

import com.piotodev.investment.aggregator.controller.dto.AssociateStockDTO;
import com.piotodev.investment.aggregator.entities.Account;
import com.piotodev.investment.aggregator.entities.AccountStock;
import com.piotodev.investment.aggregator.entities.AccountStockId;
import com.piotodev.investment.aggregator.repository.AccountRepository;
import com.piotodev.investment.aggregator.repository.AccountStockRepository;
import com.piotodev.investment.aggregator.repository.StockRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    private AccountRepository accountRepository;
    private StockRepository stockRepository;
    private final AccountStockRepository accountStockRepository;

    public AccountService(AccountRepository accountRepository, StockRepository stockRepository,
                          AccountStockRepository accountStockRepository) {
        this.accountRepository = accountRepository;
        this.stockRepository = stockRepository;
        this.accountStockRepository = accountStockRepository;
    }


    public void associateStock(String accountId, AssociateStockDTO associateStockDTO) {

        var account = accountRepository
                .findById(UUID.fromString(accountId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var stock = stockRepository
                .findById(associateStockDTO.stockId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var id = new AccountStockId(account.getAccountId(), stock.getStockID());

        var accountStock = new AccountStock(id, account, stock, associateStockDTO.quantity());

        accountStockRepository.save(accountStock);
    }

    public List<AccountStock> getStocks(String accountId) {

        var account = accountRepository
                .findById(UUID.fromString(accountId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var accountStocks = account.getAccountStocks();

        return accountStocks.stream().toList();
    }
} 
