package com.carlostorresdevjava.challenge.ceiba.funds.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FundDto {
    private String nombre;
    private Double montoMinimo;
    private String categoria;
}