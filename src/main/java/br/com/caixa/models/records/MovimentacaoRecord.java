package br.com.caixa.models.records;

import br.com.caixa.models.TipoReceita;

import java.time.LocalDate;

public record MovimentacaoRecord(Integer idConta, Double valor, TipoReceita tipoReceita, LocalDate localDate, String historico) {
}
