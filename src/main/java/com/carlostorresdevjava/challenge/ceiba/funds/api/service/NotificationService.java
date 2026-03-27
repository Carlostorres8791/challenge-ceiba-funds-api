package com.carlostorresdevjava.challenge.ceiba.funds.api.service;

import com.carlostorresdevjava.challenge.ceiba.funds.api.model.User;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void notify(User user, String message) {
        if (user.getNotificacionPreferida() == User.NotificationPreference.EMAIL) {
            System.out.println("Email a " + user.getEmail() + ": " + message);
        } else {
            System.out.println("SMS a " + user.getTelefono() + ": " + message);
        }
    }
}