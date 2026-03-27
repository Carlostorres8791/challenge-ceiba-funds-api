package com.carlostorresdevjava.challenge.ceiba.funds.api.service;

import com.carlostorresdevjava.challenge.ceiba.funds.api.dto.SubscriptionDto;
import com.carlostorresdevjava.challenge.ceiba.funds.api.exception.CustomException;
import com.carlostorresdevjava.challenge.ceiba.funds.api.model.Fund;
import com.carlostorresdevjava.challenge.ceiba.funds.api.model.Subscription;
import com.carlostorresdevjava.challenge.ceiba.funds.api.model.Transaction;
import com.carlostorresdevjava.challenge.ceiba.funds.api.model.User;
import com.carlostorresdevjava.challenge.ceiba.funds.api.repository.FundRepository;
import com.carlostorresdevjava.challenge.ceiba.funds.api.repository.SubscriptionRepository;
import com.carlostorresdevjava.challenge.ceiba.funds.api.repository.TransactionRepository;
import com.carlostorresdevjava.challenge.ceiba.funds.api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FundService {

    private final FundRepository fundRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final NotificationService notificationService;

    public FundService(FundRepository fundRepository,
                       SubscriptionRepository subscriptionRepository,
                       UserRepository userRepository,
                       TransactionRepository transactionRepository,
                       NotificationService notificationService) {
        this.fundRepository = fundRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
        this.notificationService = notificationService;
    }

    public List<Fund> getAllFunds() {
        return this.fundRepository.findAll();
    }

    public Fund createFund(Fund fund) {
        return this.fundRepository.save(fund);
    }

    public Subscription subscribe(SubscriptionDto dto) {
        User user = this.userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new CustomException("Usuario no encontrado"));
        Fund fund = this.fundRepository.findById(dto.getFundId())
                .orElseThrow(() -> new CustomException("Fondo no encontrado"));

        if (dto.getAmount() < fund.getMontoMinimo()) {
            throw new CustomException("Monto menor al mínimo requerido");
        }

        if (user.getSaldo() < dto.getAmount()) {
            throw new CustomException("No tiene saldo disponible para vincularse al fondo " + fund.getNombre());
        }

        // Restar saldo del usuario
        user.setSaldo(user.getSaldo() - dto.getAmount());
        this.userRepository.save(user);

        // Crear suscripción
        Subscription subscription = Subscription.builder()
                .userId(user.getId())
                .fundId(fund.getId())
                .amount(dto.getAmount())
                .build();
        this.subscriptionRepository.save(subscription);

        // Registrar transacción
        Transaction transaction = Transaction.builder()
                .userId(user.getId())
                .fundId(fund.getId())
                .amount(dto.getAmount())
                .type(Transaction.TransactionType.APERTURA) // CORRECTO
                .createdAt(LocalDateTime.now())
                .build();
        this.transactionRepository.save(transaction);

        // Notificar al usuario
        this.notificationService.notify(user, "Suscripción al fondo " + fund.getNombre() + " exitosa");

        return subscription;
    }

    public String cancelSubscription(String subscriptionId) {
        Subscription subscription = this.subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new CustomException("Suscripción no encontrada"));
        User user = this.userRepository.findById(subscription.getUserId())
                .orElseThrow(() -> new CustomException("Usuario no encontrado"));

        // Devolver saldo
        user.setSaldo(user.getSaldo() + subscription.getAmount());
        this.userRepository.save(user);

        // Eliminar suscripción
        this.subscriptionRepository.deleteById(subscriptionId);

        // Registrar transacción de cancelación
        Transaction transaction = Transaction.builder()
                .userId(user.getId())
                .fundId(subscription.getFundId())
                .amount(subscription.getAmount())
                .type(Transaction.TransactionType.CANCELACION) // CORRECTO
                .createdAt(LocalDateTime.now())
                .build();
        this.transactionRepository.save(transaction);

        // Notificar al usuario
        this.notificationService.notify(user, "Suscripción al fondo cancelada");

        return "Suscripción cancelada";
    }

    public List<Transaction> getUserTransactions(String userId) {
        return this.transactionRepository.findByUserId(userId);
    }

    public List<Subscription> getUserSubscriptions(String userId) {
        return this.subscriptionRepository.findByUserId(userId);
    }
}