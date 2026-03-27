package com.carlostorresdevjava.challenge.ceiba.funds.api.mapper;

import com.carlostorresdevjava.challenge.ceiba.funds.api.dto.FundDto;
import com.carlostorresdevjava.challenge.ceiba.funds.api.model.Fund;

public class FundMapper {
    public static Fund toEntity(FundDto dto) {
        return Fund.builder().nombre(dto.getNombre()).montoMinimo(dto.getMontoMinimo()).categoria(dto.getCategoria()).build();
    }

    public static FundDto toDto(Fund fund) {
        FundDto dto = new FundDto();
        dto.setNombre(fund.getNombre());
        dto.setMontoMinimo(fund.getMontoMinimo());
        dto.setCategoria(fund.getCategoria());
        return dto;
    }
}
