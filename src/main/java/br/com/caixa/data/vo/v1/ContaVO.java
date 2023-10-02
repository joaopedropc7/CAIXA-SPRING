package br.com.caixa.data.vo.v1;

import br.com.caixa.models.Grupo;
import br.com.caixa.models.TipoConta;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Objects;

public class ContaVO {

    @Mapping("id")
    @JsonProperty("id")
    private Integer key;
    private String nomeConta;
    private Grupo grupo;
    private TipoConta tipoConta;
    private Double valorEntradas;
    private Double valorSaídas;

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getNomeConta() {
        return nomeConta;
    }

    public void setNomeConta(String nomeConta) {
        this.nomeConta = nomeConta;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    public Double getValorEntradas() {
        return valorEntradas;
    }

    public void setValorEntradas(Double valorEntradas) {
        this.valorEntradas = valorEntradas;
    }

    public Double getValorSaídas() {
        return valorSaídas;
    }

    public void setValorSaídas(Double valorSaídas) {
        this.valorSaídas = valorSaídas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContaVO contaVO = (ContaVO) o;
        return Objects.equals(key, contaVO.key) && Objects.equals(nomeConta, contaVO.nomeConta) && Objects.equals(grupo, contaVO.grupo) && tipoConta == contaVO.tipoConta && Objects.equals(valorEntradas, contaVO.valorEntradas) && Objects.equals(valorSaídas, contaVO.valorSaídas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, nomeConta, grupo, tipoConta, valorEntradas, valorSaídas);
    }
}
