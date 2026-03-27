package com.carlostorresdevjava.challenge.ceiba.funds.api.service;

import com.carlostorresdevjava.challenge.ceiba.funds.api.exception.CustomException;
import com.carlostorresdevjava.challenge.ceiba.funds.api.model.User;
import com.carlostorresdevjava.challenge.ceiba.funds.api.repository.UserRepository;
import com.carlostorresdevjava.challenge.ceiba.funds.api.security.JwtUtil;
import com.carlostorresdevjava.challenge.ceiba.funds.api.security.Role;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User createUser(User user) {
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new CustomException("La contraseña es obligatoria");
        }

        user.setSaldo(500_000.0); // saldo inicial
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User getUser(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new CustomException("Usuario no encontrado"));
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public boolean checkPassword(User user, String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }

    public String login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException("Usuario no encontrado"));

        if (!checkPassword(user, password)) {
            throw new CustomException("Contraseña incorrecta");
        }

        return jwtUtil.generateToken(user.getId(), Role.USER.name());
    }
}