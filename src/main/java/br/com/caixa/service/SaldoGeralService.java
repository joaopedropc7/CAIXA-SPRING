package br.com.caixa.service;

import br.com.caixa.repositories.SaldoGeralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaldoGeralService {

    @Autowired
    private SaldoGeralRepository saldoGeralRepository;

    public Double getSaldo(){
        var saldoGeral = saldoGeralRepository.findById(1).orElseThrow(() -> new RuntimeException(""));
        return saldoGeral.getSaldo();
    }

    public void incrementaSaldo(Double valor){
        var saldoGeral = saldoGeralRepository.findById(1).orElseThrow(() -> new RuntimeException(""));
        saldoGeral.setSaldo(saldoGeral.getSaldo() + valor);
        saldoGeralRepository.save(saldoGeral);
    }

    public void diminuiSaldo(Double valor){
        var saldoGeral = saldoGeralRepository.findById(1).orElseThrow(() -> new RuntimeException(""));
        saldoGeral.setSaldo(saldoGeral.getSaldo() - valor);
        saldoGeralRepository.save(saldoGeral);
    }

}
