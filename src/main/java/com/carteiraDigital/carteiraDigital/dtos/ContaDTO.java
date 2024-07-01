package com.carteiraDigital.carteiraDigital.dtos;

import com.carteiraDigital.carteiraDigital.model.conta.Tipo;

import java.math.BigDecimal;

public record ContaDTO(String nome, String documento, String email, String senha, BigDecimal saldo, Tipo tipo) {
}
