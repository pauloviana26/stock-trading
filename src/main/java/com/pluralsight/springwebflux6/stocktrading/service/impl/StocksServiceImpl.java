package com.pluralsight.springwebflux6.stocktrading.service.impl;

import com.pluralsight.springwebflux6.stocktrading.dto.StockRequest;
import com.pluralsight.springwebflux6.stocktrading.dto.StockResponse;
import com.pluralsight.springwebflux6.stocktrading.exception.StockCreationException;
import com.pluralsight.springwebflux6.stocktrading.exception.StockNotFoundException;
import com.pluralsight.springwebflux6.stocktrading.model.repository.StocksRepository;
import com.pluralsight.springwebflux6.stocktrading.service.StocksService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
@Slf4j
@RequiredArgsConstructor
public class StocksServiceImpl implements StocksService {

    private final StocksRepository stocksRepository;

    @Override
    public Mono<StockResponse> getOneStock(String id) {
        return stocksRepository.findById(id)
                .map(StockResponse::fromModel)
                .switchIfEmpty(Mono.error(new StockNotFoundException("Stock not found with id: " + id)))
                .doFirst(() -> log.info("Retrieving stock with id: {}", id))
                .doOnNext(stock -> log.info("Stock found: {}", stock))
                .doOnError(ex -> log.error("Something went wrong while retrieving the stock with id: {}", id))
                .doOnTerminate(() -> log.info("Finalized retrieving stock"))
                .doFinally(signalType -> log.info("Finalized retrieving stock with signal type: {}", signalType));
    }

    @Override
    public Flux<StockResponse> getAllStocks(BigDecimal priceGreaterThan) {
        return stocksRepository.findAll()
                .filter(stock -> stock.getPrice().compareTo(priceGreaterThan) > 0)
                .map(StockResponse::fromModel)
                .doFirst(() -> log.info("Retrieving all stocks"))
                .doOnNext(stock -> log.info("Stock found: {}", stock))
                .doOnError(ex -> log.warn("Something went wrong while retrieving the stocks", ex))
                .doOnTerminate(() -> log.info("Finalized retrieving stocks"))
                .doFinally(signalType -> log.info("Finalized retrieving stock with signal type: {}", signalType));
    }

    @Override
    public Mono<StockResponse> createStock(StockRequest stockRequest) {
        return Mono.just(stockRequest)
                .map(StockRequest::toModel)
                .flatMap(stock -> stocksRepository.save(stockRequest.toModel()))
                .map(StockResponse::fromModel)
                // .onErrorReturn(StockResponse.builder().build())
                // .onErrorResume(ex -> Mono.just(StockResponse.builder().build()))
                .onErrorMap(ex -> new StockCreationException(ex.getMessage()))
                ;

    }
}
