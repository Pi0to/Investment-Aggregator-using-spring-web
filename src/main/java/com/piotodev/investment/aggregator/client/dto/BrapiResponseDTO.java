package com.piotodev.investment.aggregator.client.dto;

import java.util.List;

public record BrapiResponseDTO(List<StockDTO> results) {
}
