package br.com.caixa.repositories;

import br.com.caixa.models.Conta;
import br.com.caixa.models.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContaRepository extends JpaRepository<Conta, Integer> {
    List<Conta> findByGrupo(Grupo grupo);
    void deleteByGrupo(Grupo grupo);

}
