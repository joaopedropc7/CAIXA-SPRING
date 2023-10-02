package br.com.caixa.data.vo.v1;

import br.com.caixa.models.Conta;
import br.com.caixa.models.Grupo;
import br.com.caixa.models.TipoReceita;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class MovimentacaoVO {

    @Mapping("id")
    @JsonProperty("id")
    private Integer key;
    private Conta conta;
    private Double valor;
    private TipoReceita tipoReceita;
    private String historico;
    private LocalDate dataMovimentacao;


    public MovimentacaoVO() {
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public LocalDate getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(LocalDate dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public TipoReceita getTipoReceita() {
        return tipoReceita;
    }

    public void setTipoReceita(TipoReceita tipoReceita) {
        this.tipoReceita = tipoReceita;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovimentacaoVO that = (MovimentacaoVO) o;
        return Objects.equals(key, that.key) && Objects.equals(conta, that.conta) && Objects.equals(valor, that.valor) && tipoReceita == that.tipoReceita && Objects.equals(historico, that.historico) && Objects.equals(dataMovimentacao, that.dataMovimentacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, conta, valor, tipoReceita, historico, dataMovimentacao);
    }
}
