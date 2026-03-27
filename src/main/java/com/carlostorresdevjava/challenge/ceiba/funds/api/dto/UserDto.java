package com.carlostorresdevjava.challenge.ceiba.funds.api.dto;

import com.carlostorresdevjava.challenge.ceiba.funds.api.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String nombre;
    private String email;
    private String telefono;
    private String password;
    private User.NotificationPreference notificacionPreferida;
}