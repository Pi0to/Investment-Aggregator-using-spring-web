package com.piotodev.investment.aggregator.controller.dto;

import com.piotodev.investment.aggregator.entities.Stock;

public record AccountStockOutDTO(Integer quantity, Stock stock) {
}
