package br.com.caixa.models.records;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DataRequisicaoDTO(LocalDate dataInicio, LocalDate dataFim) {
}
