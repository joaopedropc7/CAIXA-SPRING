package br.com.caixa.models.records;

import br.com.caixa.models.TipoConta;

public record ContaRecord(String nomeConta, Integer idGrupo, TipoConta tipoConta) {
}
