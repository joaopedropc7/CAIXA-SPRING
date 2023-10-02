package br.com.caixa.models;

import java.util.List;
import java.util.Objects;

public class RelatorioResponse {

    private List<Relatorio> relatorios;
    private double totalEntradas;
    private double totalSaidas;
    private double saldoTotal;

    public List<Relatorio> getRelatorios() {
        return relatorios;
    }

    public void setRelatorios(List<Relatorio> relatorios) {
        this.relatorios = relatorios;
    }

    public double getTotalEntradas() {
        return totalEntradas;
    }

    public void setTotalEntradas(double totalEntradas) {
        this.totalEntradas = totalEntradas;
    }

    public double getTotalSaidas() {
        return totalSaidas;
    }

    public void setTotalSaidas(double totalSaidas) {
        this.totalSaidas = totalSaidas;
    }

    public double getSaldoTotal() {
        return saldoTotal;
    }

    public void setSaldoTotal(double saldoTotal) {
        this.saldoTotal = saldoTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelatorioResponse that = (RelatorioResponse) o;
        return Double.compare(totalEntradas, that.totalEntradas) == 0 && Double.compare(totalSaidas, that.totalSaidas) == 0 && Double.compare(saldoTotal, that.saldoTotal) == 0 && Objects.equals(relatorios, that.relatorios);
    }

    @Override
    public int hashCode() {
        return Objects.hash(relatorios, totalEntradas, totalSaidas, saldoTotal);
    }
}
