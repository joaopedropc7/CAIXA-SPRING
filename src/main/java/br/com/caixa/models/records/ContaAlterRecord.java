package br.com.caixa.models.records;

import br.com.caixa.models.TipoConta;

public record ContaAlterRecord(Integer id, String nomeConta, Integer idGrupo, TipoConta tipoConta) {
}
