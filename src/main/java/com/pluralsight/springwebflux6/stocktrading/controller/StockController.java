package com.pluralsight.springwebflux6.stocktrading.controller;

import com.pluralsight.springwebflux6.stocktrading.dto.StockRequest;
import com.pluralsight.springwebflux6.stocktrading.dto.StockResponse;
import com.pluralsight.springwebflux6.stocktrading.model.Stock;
import com.pluralsight.springwebflux6.stocktrading.service.StocksService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StocksService stocksService;

    @GetMapping("/{id}")
    public Mono<StockResponse> getOneStock(@PathVariable String id) {
        return stocksService.getOneStock(id);
    }

    @GetMapping
    public Flux<StockResponse> getAllStocks() {
        return stocksService.getAllStocks();
    }

    @PostMapping
    public Mono<StockResponse> createStock(@RequestBody StockRequest stockRequest) {
        return stocksService.createStock(stockRequest);
    }
}
