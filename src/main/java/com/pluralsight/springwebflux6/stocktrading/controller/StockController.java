package com.pluralsight.springwebflux6.stocktrading.controller;

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
    public Mono<Stock> getOneStock(@PathVariable String id) {
        return stocksService.getOneStock(id);
    }

    @GetMapping
    public Flux<Stock> getAllStocks() {
        return stocksService.getAllStocks();
    }

    @PostMapping
    public Mono<Stock> createStock(@RequestBody Stock stock) {
        return stocksService.createStock(stock);
    }
}
