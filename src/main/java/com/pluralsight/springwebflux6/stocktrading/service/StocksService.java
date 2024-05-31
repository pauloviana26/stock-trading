package com.pluralsight.springwebflux6.stocktrading.service;

import com.pluralsight.springwebflux6.stocktrading.dto.StockRequest;
import com.pluralsight.springwebflux6.stocktrading.dto.StockResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StocksService {
    Mono<StockResponse> getOneStock(String id);

    Flux<StockResponse> getAllStocks();

    Mono<StockResponse> createStock(StockRequest stock);
}
