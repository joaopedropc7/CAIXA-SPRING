package br.com.caixa.controller;

import br.com.caixa.models.*;
import br.com.caixa.models.records.DataRequisicaoDTO;
import br.com.caixa.service.ContaService;
import br.com.caixa.service.GrupoService;
import br.com.caixa.service.RelatorioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/relatorio")
public class RelatorioController {

    @Autowired
    private RelatorioService service;

    @Autowired
    private ContaService contaService;

    @Autowired
    private GrupoService grupoService;

    @GetMapping("/data")
    public String viewFormDate(){
        return "formData";
    }

    @GetMapping("/contas")
    public String viewContasForRelatorio(Model model){
        model.addAttribute("contas", contaService.findAll());
        return "formContaRelatorio";
    }

    @GetMapping("/despesas")
    public String relatorioDespesas(Model model){
        TipoReceita tipoReceita = TipoReceita.DESPESA;
        List<Relatorio> relatorio = service.findByTipoReceita(tipoReceita);
        model.addAttribute("relatorio", relatorio);
        return "relatorio";
    }

    @GetMapping("/receitas")
    public String relatorioReceita(Model model){
        TipoReceita tipoReceita = TipoReceita.RECEITA;
        List<Relatorio> relatorio = service.findByTipoReceita(tipoReceita);
        model.addAttribute("relatorio", relatorio);
        System.out.println(relatorio);
        return "relatorio";
    }

    @GetMapping("/contasporid")
    public String relatorioContasPorIds(HttpSession session, Model model){
        RelatorioResponse relatorioResponse = (RelatorioResponse) session.getAttribute("relatorios");
        model.addAttribute("relatorios", relatorioResponse);
        return "relatorioResponse";
    }

    @PostMapping(value = "/conta/ids")
    public String relatorioByConta(@RequestParam("contaSelecionada") List<Integer> ids, HttpSession session){
        RelatorioResponse relatorioResponse = service.findByContaIds(ids);
        session.setAttribute("relatorios", relatorioResponse);
        return "redirect:/relatorio/contasporid";
    }

    @GetMapping("/periododata")
    public String relatorioPeriodoData(HttpSession session, Model model){
        RelatorioResponse relatorioResponse = (RelatorioResponse) session.getAttribute("relatoriosData");
        model.addAttribute("relatorios", relatorioResponse);
        return "relatorioResponse";
    }

    @PostMapping("/data")
    public String relatorioByPeriodo(DataRequisicaoDTO periodoRequest, HttpSession httpSession){

        LocalDate dataInicio = periodoRequest.dataInicio();
        LocalDate dataFim = periodoRequest.dataFim();

        // LocalDateTime inicio = dataInicio.atTime(LocalTime.MIN);
        // LocalDateTime fim = dataFim.atTime(LocalTime.MAX);
        System.out.println(dataInicio + " ---- " + dataFim);
        RelatorioResponse relatorioResponse =  service.findMovimentacoesEntreDatas(dataInicio, dataFim);
        httpSession.setAttribute("relatoriosData", relatorioResponse);
        return "redirect:/relatorio/periododata";
    }

    @GetMapping("/formgrupo")
    public String viewFormGrupo(Model model){
        model.addAttribute("grupos", grupoService.findAll());
        return "formGrupoView";
    }

    @PostMapping("/grupo")
    public String relatorioByGrupo(Integer idGrupo, HttpSession httpSession){
        RelatorioResponseGrupo relatorioResponse = service.findByMovimentacoesGrupo(idGrupo);
        httpSession.setAttribute("relatoriogrupo", relatorioResponse);
        return "redirect:/relatorio/gruporelatorio";
    }

    @GetMapping("/gruporelatorio")
    public String viewRelatorioGrupo(Model model, HttpSession httpSession){
        RelatorioResponseGrupo relatorioResponse = (RelatorioResponseGrupo) httpSession.getAttribute("relatoriogrupo");
        model.addAttribute("relatorio", relatorioResponse);
        return "relatorioResponseGrupo";
    }
}
