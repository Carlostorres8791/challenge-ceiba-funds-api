package com.carlostorresdevjava.challenge.ceiba.funds.api.repository;

import com.carlostorresdevjava.challenge.ceiba.funds.api.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
    List<Transaction> findByUserId(String userId);
}

