package com.piotodev.investment.aggregator.service;

import com.piotodev.investment.aggregator.client.BrapiClient;
import com.piotodev.investment.aggregator.controller.dto.CreateStockDTO;
import com.piotodev.investment.aggregator.entities.Stock;
import com.piotodev.investment.aggregator.repository.StockRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StockService {


    @Value("#{environment.TOKEN}")
    private String TOKEN;

    private BrapiClient brapiClient;
    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository, BrapiClient brapiClient) {
        this.stockRepository = stockRepository;
        this.brapiClient = brapiClient;
    }


    public void createStock(CreateStockDTO createStockDTO){

        var price = brapiClient.getQuote(TOKEN, createStockDTO.stockId()).results().getFirst().regularMarketPrice();

        var stock = new Stock(createStockDTO.stockId(), createStockDTO.description(), price);

        stockRepository.save(stock);

    }

}
