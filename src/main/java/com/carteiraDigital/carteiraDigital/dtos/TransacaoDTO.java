package com.carteiraDigital.carteiraDigital.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransacaoDTO(BigDecimal valor, Long pagadorID, Long beneficiarioID, LocalDateTime tempo) {
}
