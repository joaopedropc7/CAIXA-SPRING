package br.com.caixa.service;

import br.com.caixa.models.*;
import br.com.caixa.repositories.GrupoRepository;
import br.com.caixa.repositories.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RelatorioService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private GrupoRepository grupoRepository;

    public List<Relatorio> findByTipoReceita(TipoReceita tipoReceita){
        List<Movimentacao> movimentacaoLista = movimentacaoRepository.findByTipoReceita(tipoReceita);
        List<Relatorio> relatorios = new ArrayList<>();
        Double saldo = 0.0;
        Double totalSaidas = 0.0;
        Double totalEntradas = 0.0;

        for (Movimentacao movimentacao : movimentacaoLista){
            Relatorio relatorio = new Relatorio(movimentacao);
            if(movimentacao.getTipoReceita() == TipoReceita.RECEITA){
                relatorio.setValorEntrada(movimentacao.getValor());
                relatorio.setValorSaida(0.0);
                totalEntradas += movimentacao.getValor();
                saldo += movimentacao.getValor();
            }else if (movimentacao.getTipoReceita() == TipoReceita.DESPESA){
                relatorio.setValorSaida(movimentacao.getValor());
                relatorio.setValorEntrada(0.0);
                totalSaidas += movimentacao.getValor();
                saldo -= movimentacao.getValor();
            }
            relatorio.setSaldo(saldo);
            relatorios.add(relatorio);
        }
        return relatorios;
    }

    public RelatorioResponse findByContaIds(List<Integer> contaId){
        List<Movimentacao> movimentacaoLista = movimentacaoRepository.findByContaIdIn(contaId);
        List<Relatorio> relatorios = new ArrayList<>();
        Double saldo = 0.0;
        Double totalSaidas = 0.0;
        Double totalEntradas = 0.0;

        for(Movimentacao movimentacao : movimentacaoLista){
            Relatorio relatorio = new Relatorio(movimentacao);
            if(movimentacao.getTipoReceita() == TipoReceita.RECEITA){
                relatorio.setValorEntrada(movimentacao.getValor());
                relatorio.setValorSaida(0.0);
                totalEntradas += movimentacao.getValor();
                saldo += movimentacao.getValor();
            }else if (movimentacao.getTipoReceita() == TipoReceita.DESPESA){
                relatorio.setValorSaida(movimentacao.getValor());
                relatorio.setValorEntrada(0.0);
                totalSaidas += movimentacao.getValor();
                saldo -= movimentacao.getValor();
            }
            relatorio.setSaldo(saldo);
            relatorios.add(relatorio);
        }

        RelatorioResponse relatorioResponse = new RelatorioResponse();
        relatorioResponse.setRelatorios(relatorios);
        relatorioResponse.setTotalEntradas(totalEntradas);
        relatorioResponse.setTotalSaidas(totalSaidas);
        relatorioResponse.setSaldoTotal(relatorioResponse.getTotalEntradas() - relatorioResponse.getTotalSaidas());

        return relatorioResponse;
    }

    public RelatorioResponse findMovimentacoesEntreDatas(LocalDate dataInicio, LocalDate dataFim) {
        List<Movimentacao> movimentacaoLista = movimentacaoRepository.findByDataMovimentacaoBetween(dataInicio, dataFim);
        List<Relatorio> relatorios = new ArrayList<>();
        Double saldo = 0.0;
        Double totalSaidas = 0.0;
        Double totalEntradas = 0.0;

        for(Movimentacao movimentacao : movimentacaoLista){
            Relatorio relatorio = new Relatorio(movimentacao);
            if(movimentacao.getTipoReceita() == TipoReceita.RECEITA){
                relatorio.setValorEntrada(movimentacao.getValor());
                relatorio.setValorSaida(0.0);
                totalEntradas += movimentacao.getValor();
                saldo += movimentacao.getValor();
            }else if (movimentacao.getTipoReceita() == TipoReceita.DESPESA){
                relatorio.setValorSaida(movimentacao.getValor());
                relatorio.setValorEntrada(0.0);
                totalSaidas += movimentacao.getValor();
                saldo -= movimentacao.getValor();
            }
            relatorio.setSaldo(saldo);
            relatorios.add(relatorio);
        }

        RelatorioResponse relatorioResponse = new RelatorioResponse();
        relatorioResponse.setRelatorios(relatorios);
        relatorioResponse.setTotalEntradas(totalEntradas);
        relatorioResponse.setTotalSaidas(totalSaidas);
        relatorioResponse.setSaldoTotal(relatorioResponse.getTotalEntradas() - relatorioResponse.getTotalSaidas());

        return relatorioResponse;
    }

    public RelatorioResponseGrupo findByMovimentacoesGrupo(Integer idGrupo){
        var grupo = grupoRepository.findById(idGrupo).orElseThrow(() -> new RuntimeException(""));
        List<Movimentacao> movimentacaoLista = movimentacaoRepository.findByConta_Grupo(grupo);
        List<RelatorioGrupo> relatorios = new ArrayList<>();
        Double saldo = 0.0;
        Double totalSaidas = 0.0;
        Double totalEntradas = 0.0;

        for(Movimentacao movimentacao : movimentacaoLista){
            RelatorioGrupo relatorio = new RelatorioGrupo(movimentacao);
            if(movimentacao.getTipoReceita() == TipoReceita.RECEITA){
                relatorio.setData(movimentacao.getDataMovimentacao());
                relatorio.setValorEntrada(movimentacao.getValor());
                relatorio.setValorSaida(0.0);
                totalEntradas += movimentacao.getValor();
                saldo += movimentacao.getValor();
            }else if (movimentacao.getTipoReceita() == TipoReceita.DESPESA){
                relatorio.setData(movimentacao.getDataMovimentacao());
                relatorio.setValorSaida(movimentacao.getValor());
                relatorio.setValorEntrada(0.0);
                totalSaidas += movimentacao.getValor();
                saldo -= movimentacao.getValor();
            }
            relatorio.setSaldo(saldo);
            relatorios.add(relatorio);
        }

        RelatorioResponseGrupo relatorioResponseGrupo = new RelatorioResponseGrupo();
        relatorioResponseGrupo.setIdGrupo(idGrupo);
        relatorioResponseGrupo.setRelatorioGrupos(relatorios);
        relatorioResponseGrupo.setNomeGrupo(grupo.getName());
        relatorioResponseGrupo.setTotalEntradas(totalEntradas);
        relatorioResponseGrupo.setTotalSaidas(totalSaidas);
        relatorioResponseGrupo.setSaldoTotal(totalEntradas - totalSaidas);

        return relatorioResponseGrupo;
    }
}
