package com.pluralsight.springwebflux6.stocktrading.service.impl;

import com.pluralsight.springwebflux6.stocktrading.model.Stock;
import com.pluralsight.springwebflux6.stocktrading.model.repository.StocksRepository;
import com.pluralsight.springwebflux6.stocktrading.service.StocksService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StocksServiceImpl implements StocksService {

    private final StocksRepository stocksRepository;

    @Override
    public Mono<Stock> getOneStock(String id) {
        return stocksRepository.findById(id);
    }

    @Override
    public Flux<Stock> getAllStocks() {
        return stocksRepository.findAll();
    }

    @Override
    public Mono<Stock> createStock(Stock stock) {
        return stocksRepository.save(stock);
    }
}
