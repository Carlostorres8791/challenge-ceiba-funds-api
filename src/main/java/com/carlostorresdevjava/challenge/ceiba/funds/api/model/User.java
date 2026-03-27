package com.carlostorresdevjava.challenge.ceiba.funds.api.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    private String id;

    private String nombre;
    private String email;
    private String telefono;

    private String password; // <--- agregar esto
    private Double saldo;

    private NotificationPreference notificacionPreferida;

    public enum NotificationPreference {
        EMAIL,
        SMS
    }
}