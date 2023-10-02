package br.com.caixa.models;

import java.util.List;
import java.util.Objects;

public class RelatorioResponseGrupo {

    private Integer idGrupo;
    private String nomeGrupo;
    private List<RelatorioGrupo> relatorioGrupos;
    private Double totalEntradas;
    private Double totalSaidas;
    private Double saldoTotal;

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public void setNomeGrupo(String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }

    public List<RelatorioGrupo> getRelatorioGrupos() {
        return relatorioGrupos;
    }

    public void setRelatorioGrupos(List<RelatorioGrupo> relatorioGrupos) {
        this.relatorioGrupos = relatorioGrupos;
    }

    public Double getTotalEntradas() {
        return totalEntradas;
    }

    public void setTotalEntradas(Double totalEntradas) {
        this.totalEntradas = totalEntradas;
    }

    public Double getTotalSaidas() {
        return totalSaidas;
    }

    public void setTotalSaidas(Double totalSaidas) {
        this.totalSaidas = totalSaidas;
    }

    public Double getSaldoTotal() {
        return saldoTotal;
    }

    public void setSaldoTotal(Double saldoTotal) {
        this.saldoTotal = saldoTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelatorioResponseGrupo that = (RelatorioResponseGrupo) o;
        return Objects.equals(idGrupo, that.idGrupo) && Objects.equals(nomeGrupo, that.nomeGrupo) && Objects.equals(relatorioGrupos, that.relatorioGrupos) && Objects.equals(totalEntradas, that.totalEntradas) && Objects.equals(totalSaidas, that.totalSaidas) && Objects.equals(saldoTotal, that.saldoTotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGrupo, nomeGrupo, relatorioGrupos, totalEntradas, totalSaidas, saldoTotal);
    }
}
