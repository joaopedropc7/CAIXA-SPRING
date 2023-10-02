package br.com.caixa.service;

import br.com.caixa.data.vo.v1.ContaVO;
import br.com.caixa.data.vo.v1.GrupoVO;
import br.com.caixa.mapper.DozerMapper;
import br.com.caixa.models.Conta;
import br.com.caixa.models.Grupo;
import br.com.caixa.models.Movimentacao;
import br.com.caixa.models.TipoReceita;
import br.com.caixa.models.records.ContaAlterRecord;
import br.com.caixa.models.records.ContaRecord;
import br.com.caixa.models.records.GrupoAlterRecord;
import br.com.caixa.repositories.ContaRepository;
import br.com.caixa.repositories.GrupoRepository;
import br.com.caixa.repositories.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaService {

    @Autowired
    private SaldoGeralService saldoGeralService;

    @Autowired
    private ContaRepository repository;

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    public Conta create(ContaRecord contaRecord){
        var grupo = grupoRepository.findById(contaRecord.idGrupo()).orElseThrow(() -> new RuntimeException(""));
        Conta conta = new Conta(contaRecord, grupo);
        repository.save(conta);
        return conta;
    }

    public List<Conta> findAll(){
        var entity = repository.findAll();
        return entity;
    }

    public Conta findById(Integer id){
        var entity = repository.findById(id).orElseThrow(() -> new RuntimeException(""));
        return entity;
    }

    public Conta update(ContaAlterRecord contaAlterRecord){
        var entity = repository.findById(contaAlterRecord.id()).orElseThrow(() -> new RuntimeException(""));
        Grupo grupo = grupoRepository.findById(contaAlterRecord.id()).orElseThrow(() -> new RuntimeException(""));
        entity.setGrupo(grupo);
        entity.setNomeConta(contaAlterRecord.nomeConta());
        entity.setTipoConta(contaAlterRecord.tipoConta());
        repository.save(entity);
        return entity;
    }

    public void delete(Integer id){
        repository.deleteById(id);
    }

    public void ajustaSaldoMovimentacaoContasDelete(Conta conta){
        List<Movimentacao> movimentacoes = movimentacaoRepository.findByConta(conta);
        for (Movimentacao  movimentacao : movimentacoes) {
            if (movimentacao.getTipoReceita() == TipoReceita.RECEITA){
                saldoGeralService.diminuiSaldo(movimentacao.getValor());
            }else{
                saldoGeralService.incrementaSaldo(movimentacao.getValor());
            }
        }
    }

}
