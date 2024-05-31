package com.pluralsight.springwebflux6.stocktrading.service;

import com.pluralsight.springwebflux6.stocktrading.model.Stock;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StocksService {
    Mono<Stock> getOneStock(String id);

    Flux<Stock> getAllStocks();

    Mono<Stock> createStock(Stock stock);
}
