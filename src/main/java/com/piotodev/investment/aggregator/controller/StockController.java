package com.piotodev.investment.aggregator.controller;


import com.piotodev.investment.aggregator.controller.dto.CreateStockDTO;
import com.piotodev.investment.aggregator.service.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/stocks")
public class StockController {

    private StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping
    public ResponseEntity<Void> createStock (@RequestBody CreateStockDTO createStockDTO) {

        stockService.createStock(createStockDTO);

        return ResponseEntity.ok().build();
    }
}
