package br.com.caixa.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class RelatorioGrupo {

    private LocalDate data;
    private Double valorEntrada;
    private Double valorSaida;
    private Double saldo;


    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public RelatorioGrupo(Movimentacao movimentacao) {

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
        RelatorioGrupo that = (RelatorioGrupo) o;
        return Objects.equals(valorEntrada, that.valorEntrada) && Objects.equals(valorSaida, that.valorSaida) && Objects.equals(saldo, that.saldo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valorEntrada, valorSaida, saldo);
    }
}
