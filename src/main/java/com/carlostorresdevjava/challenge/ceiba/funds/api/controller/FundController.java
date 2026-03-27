package com.carlostorresdevjava.challenge.ceiba.funds.api.controller;

import com.carlostorresdevjava.challenge.ceiba.funds.api.dto.SubscriptionDto;
import com.carlostorresdevjava.challenge.ceiba.funds.api.model.Fund;
import com.carlostorresdevjava.challenge.ceiba.funds.api.model.Subscription;
import com.carlostorresdevjava.challenge.ceiba.funds.api.model.Transaction;
import com.carlostorresdevjava.challenge.ceiba.funds.api.repository.TransactionRepository;
import com.carlostorresdevjava.challenge.ceiba.funds.api.service.FundService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funds")
public class FundController {

    private final FundService fundService;
    private final TransactionRepository transactionRepository;

    public FundController(FundService fundService, TransactionRepository transactionRepository) {
        this.fundService = fundService;
        this.transactionRepository = transactionRepository;
    }

    // Listar todos los fondos
    @GetMapping
    public List<Fund> getFunds() {
        return fundService.getAllFunds();
    }

    // Crear un fondo (solo admin)
    @PostMapping
    public Fund createFund(@RequestBody Fund fund) {
        return fundService.createFund(fund);
    }

    // Suscribirse a un fondo
    @PostMapping("/subscribe")
    public Subscription subscribe(@RequestBody SubscriptionDto dto) {
        return fundService.subscribe(dto);
    }

    // Cancelar una suscripción
    @PostMapping("/cancel/{subscriptionId}")
    public String cancel(@PathVariable String subscriptionId) {
        return fundService.cancelSubscription(subscriptionId);
    }

    // Historial de transacciones por usuario
    @GetMapping("/subscriptions/{userId}")
    public List<Transaction> getUserTransactions(@PathVariable String userId) {
        return transactionRepository.findByUserId(userId);
    }
}