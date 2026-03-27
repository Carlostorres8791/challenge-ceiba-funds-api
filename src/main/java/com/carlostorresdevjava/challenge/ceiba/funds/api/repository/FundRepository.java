package com.carlostorresdevjava.challenge.ceiba.funds.api.repository;

import com.carlostorresdevjava.challenge.ceiba.funds.api.model.Fund;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FundRepository extends MongoRepository<Fund, String> {
}
