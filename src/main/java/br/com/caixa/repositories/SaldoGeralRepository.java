package br.com.caixa.repositories;

import br.com.caixa.models.SaldoGeral;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaldoGeralRepository extends JpaRepository<SaldoGeral, Integer> {
}
