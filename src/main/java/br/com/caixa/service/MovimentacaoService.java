package br.com.caixa.service;

import br.com.caixa.data.vo.v1.GrupoVO;
import br.com.caixa.data.vo.v1.MovimentacaoVO;
import br.com.caixa.mapper.DozerMapper;
import br.com.caixa.models.Movimentacao;
import br.com.caixa.models.TipoReceita;
import br.com.caixa.models.records.MovimentacaoRecord;
import br.com.caixa.repositories.ContaRepository;
import br.com.caixa.repositories.GrupoRepository;
import br.com.caixa.repositories.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository repository;

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private SaldoGeralService saldoGeralService;

    public MovimentacaoVO create(MovimentacaoRecord movimentacaoRecord){
        var conta = contaRepository.findById(movimentacaoRecord.idConta()).orElseThrow(() -> new RuntimeException(""));
        Movimentacao movimentacao = new Movimentacao(movimentacaoRecord, conta);
        if (movimentacaoRecord.tipoReceita() == TipoReceita.RECEITA){
            saldoGeralService.incrementaSaldo(movimentacao.getValor());
            var vo = DozerMapper.parseObject(repository.save(movimentacao), MovimentacaoVO.class);
            return vo;
        }else if(movimentacaoRecord.tipoReceita() == TipoReceita.DESPESA){
                saldoGeralService.diminuiSaldo(movimentacao.getValor());
                var vo = DozerMapper.parseObject(repository.save(movimentacao), MovimentacaoVO.class);
                return vo;
        }
            throw new RuntimeException("Tipo de receita inválido");
    }

    public MovimentacaoVO findById(Integer id){
        var entity = repository.findById(id).orElseThrow(() -> new RuntimeException(""));
        var vo = DozerMapper.parseObject(entity, MovimentacaoVO.class);
        return vo;
    }

    public List<MovimentacaoVO> findAll(){
        var entity = repository.findAll();
        var vo = DozerMapper.parseListObjects(entity, MovimentacaoVO.class);
        return vo;
    }

    public MovimentacaoVO update(MovimentacaoVO movimentacaoVO){
        var entity = repository.findById(movimentacaoVO.getKey()).orElseThrow(() -> new RuntimeException(""));
        entity.setValor(movimentacaoVO.getValor());
        entity.setTipoReceita(movimentacaoVO.getTipoReceita());
        repository.save(entity);
        var vo = DozerMapper.parseObject(entity, MovimentacaoVO.class);
        return vo;
    }

    public void delete(Integer id){
        repository.deleteById(id);
    }



}
