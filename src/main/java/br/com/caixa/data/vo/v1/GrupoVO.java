package br.com.caixa.data.vo.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;

import java.util.Objects;

public class GrupoVO {

    @Mapping("id")
    @JsonProperty("id")
    private Integer key;
    private String name;

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrupoVO grupoVO = (GrupoVO) o;
        return Objects.equals(key, grupoVO.key) && Objects.equals(name, grupoVO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, name);
    }
}
