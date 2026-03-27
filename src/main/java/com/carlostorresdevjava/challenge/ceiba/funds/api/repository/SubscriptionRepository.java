package com.carlostorresdevjava.challenge.ceiba.funds.api.repository;

import com.carlostorresdevjava.challenge.ceiba.funds.api.model.Subscription;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SubscriptionRepository extends MongoRepository<Subscription, String> {
    List<Subscription> findByUserId(String userId);
}
