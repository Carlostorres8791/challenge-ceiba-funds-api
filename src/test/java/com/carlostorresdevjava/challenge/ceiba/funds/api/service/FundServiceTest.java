package com.carlostorresdevjava.challenge.ceiba.funds.api.service;

import com.carlostorresdevjava.challenge.ceiba.funds.api.dto.SubscriptionDto;
import com.carlostorresdevjava.challenge.ceiba.funds.api.model.*;
import com.carlostorresdevjava.challenge.ceiba.funds.api.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FundServiceTest {

    @Mock
    private FundRepository fundRepository;

    @Mock
    private SubscriptionRepository subscriptionRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private FundService fundService;

    @Test
    void shouldSubscribeUserSuccessfully() {
        // Arrange
        SubscriptionDto dto = new SubscriptionDto();
        dto.setUserId("user1");
        dto.setFundId("1");
        dto.setAmount(100000.0);

        User user = User.builder()
                .id("user1")
                .saldo(500000.0)
                .build();

        Fund fund = Fund.builder()
                .id("1")
                .nombre("FPV")
                .montoMinimo(50000.0)
                .build();

        when(userRepository.findById("user1")).thenReturn(Optional.of(user));
        when(fundRepository.findById("1")).thenReturn(Optional.of(fund));

        // Act
        Subscription result = fundService.subscribe(dto);

        // Assert
        assertNotNull(result);
        verify(userRepository).save(user);
        verify(subscriptionRepository).save(any(Subscription.class));
        verify(transactionRepository).save(any(Transaction.class));
        verify(notificationService).notify(eq(user), anyString());
    }

    @Test
    void shouldFailWhenUserHasInsufficientBalance() {
        // Arrange
        SubscriptionDto dto = new SubscriptionDto();
        dto.setUserId("user1");
        dto.setFundId("1");
        dto.setAmount(600000.0); // más que el saldo

        User user = User.builder()
                .id("user1")
                .saldo(500000.0)
                .build();

        Fund fund = Fund.builder()
                .id("1")
                .nombre("FPV")
                .montoMinimo(50000.0)
                .build();

        when(userRepository.findById("user1")).thenReturn(Optional.of(user));
        when(fundRepository.findById("1")).thenReturn(Optional.of(fund));

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            fundService.subscribe(dto);
        });

        assertTrue(exception.getMessage().contains("No tiene saldo disponible"));
    }

    @Test
    void shouldFailWhenAmountIsLessThanMinimum() {
        // Arrange
        SubscriptionDto dto = new SubscriptionDto();
        dto.setUserId("user1");
        dto.setFundId("1");
        dto.setAmount(10000.0); // menor al mínimo

        User user = User.builder()
                .id("user1")
                .saldo(500000.0)
                .build();

        Fund fund = Fund.builder()
                .id("1")
                .nombre("FPV")
                .montoMinimo(50000.0)
                .build();

        when(userRepository.findById("user1")).thenReturn(Optional.of(user));
        when(fundRepository.findById("1")).thenReturn(Optional.of(fund));

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            fundService.subscribe(dto);
        });

        assertTrue(exception.getMessage().contains("Monto menor"));
    }

    @Test
    void shouldCancelSubscriptionSuccessfully() {
        // Arrange
        Subscription subscription = Subscription.builder()
                .id("sub1")
                .userId("user1")
                .fundId("1")
                .amount(100000.0)
                .build();

        User user = User.builder()
                .id("user1")
                .saldo(400000.0)
                .build();

        when(subscriptionRepository.findById("sub1")).thenReturn(Optional.of(subscription));
        when(userRepository.findById("user1")).thenReturn(Optional.of(user));

        // Act
        String result = fundService.cancelSubscription("sub1");

        // Assert
        assertEquals("Suscripción cancelada", result);
        verify(userRepository).save(user);
        verify(subscriptionRepository).deleteById("sub1");
        verify(transactionRepository).save(any(Transaction.class));
        verify(notificationService).notify(eq(user), anyString());
    }
}