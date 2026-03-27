package com.carlostorresdevjava.challenge.ceiba.funds.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "funds")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fund {
    @Id
    private String id;
    private String nombre;
    private Double montoMinimo;
    private String categoria;
}