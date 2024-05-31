package com.pluralsight.springwebflux6.stocktrading.model.repository;

import com.pluralsight.springwebflux6.stocktrading.model.Stock;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StocksRepository extends ReactiveMongoRepository<Stock, String> {
}
