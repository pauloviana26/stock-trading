package com.pluralsight.springwebflux6.stocktrading.service.impl;

import com.pluralsight.springwebflux6.stocktrading.dto.StockRequest;
import com.pluralsight.springwebflux6.stocktrading.dto.StockResponse;
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
    public Mono<StockResponse> getOneStock(String id) {
        return stocksRepository.findById(id)
                .map(StockResponse::fromModel);
    }

    @Override
    public Flux<StockResponse> getAllStocks() {
        return stocksRepository.findAll()
                .map(StockResponse::fromModel);
    }

    @Override
    public Mono<StockResponse> createStock(StockRequest stockRequest) {
        return stocksRepository.save(stockRequest.toModel())
                .map(StockResponse::fromModel);
    }
}
