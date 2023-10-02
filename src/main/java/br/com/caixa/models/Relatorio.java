package br.com.caixa.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Relatorio {

    private Conta conta;
    private LocalDate data;
    private String historico;
    private Double valorEntrada;
    private Double valorSaida;
    private Double saldo;

    public Relatorio(Movimentacao entity) {
        this.conta = entity.getConta();
        this.data = entity.getDataMovimentacao();
        this.historico = entity.getHistorico();
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public Double getValorEntrada() {
        return valorEntrada;
    }

    public void setValorEntrada(Double valorEntrada) {
        this.valorEntrada = valorEntrada;
    }

    public Double getValorSaida() {
        return valorSaida;
    }

    public void setValorSaida(Double valorSaida) {
        this.valorSaida = valorSaida;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Relatorio relatorio = (Relatorio) o;
        return Objects.equals(conta, relatorio.conta) && Objects.equals(data, relatorio.data) && Objects.equals(historico, relatorio.historico) && Objects.equals(valorEntrada, relatorio.valorEntrada) && Objects.equals(valorSaida, relatorio.valorSaida) && Objects.equals(saldo, relatorio.saldo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(conta, data, historico, valorEntrada, valorSaida, saldo);
    }
}
