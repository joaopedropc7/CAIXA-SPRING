package br.com.caixa.repositories;

import br.com.caixa.models.Conta;
import br.com.caixa.models.Grupo;
import br.com.caixa.models.Movimentacao;
import br.com.caixa.models.TipoReceita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Integer> {
    List<Movimentacao> findByTipoReceita(TipoReceita tipoReceita);
    List<Movimentacao> findByContaIdIn(List<Integer> contaId);
    List<Movimentacao> findByConta(Conta conta);

    @Query("SELECT m FROM Movimentacao m WHERE m.dataMovimentacao >= :dataInicio AND m.dataMovimentacao <= :dataFim")
    List<Movimentacao> findByDataMovimentacaoBetween(@Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFim);

    List<Movimentacao> findByConta_Grupo(Grupo grupo);

    void deleteByConta(Conta conta);

}
