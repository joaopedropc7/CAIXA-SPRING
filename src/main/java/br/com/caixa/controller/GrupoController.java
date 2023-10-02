package br.com.caixa.controller;

import br.com.caixa.data.vo.v1.GrupoVO;
import br.com.caixa.models.Conta;
import br.com.caixa.models.Grupo;
import br.com.caixa.models.records.GrupoAlterRecord;
import br.com.caixa.models.records.GrupoRecord;
import br.com.caixa.repositories.ContaRepository;
import br.com.caixa.repositories.GrupoRepository;
import br.com.caixa.repositories.MovimentacaoRepository;
import br.com.caixa.service.ContaService;
import br.com.caixa.service.GrupoService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/grupo")
public class GrupoController {

    @Autowired
    private ContaService contaService;

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private GrupoService service;

    @GetMapping("/cadastro")
    public String viewForm(){
        return "formGrupo";
    }

    @PostMapping
    public RedirectView create(GrupoRecord grupoRecord, RedirectAttributes redirectAttributes){
        service.create(grupoRecord);
        redirectAttributes.addFlashAttribute("mensagem", "Grupo criada com sucesso!");
        return new RedirectView("/grupo", true);
    }

    @GetMapping
    public String findAll(Model model){
        model.addAttribute("grupos", service.findAll());
        return "listGrupos";
    }

    @PostMapping("/findid")
    public String findById(Integer id, HttpSession httpSession){
        var grupo = service.findById(id);
        System.out.println(grupo);
        httpSession.setAttribute("grupo", grupo);
        return "redirect:/grupo/alterar";
    }

    @PostMapping("/update")
    public RedirectView update(GrupoAlterRecord grupoAlterRecord, RedirectAttributes redirectAttributes){
        service.update(grupoAlterRecord);
        redirectAttributes.addFlashAttribute("mensagem", "Grupo alterado com sucesso!");
        return new RedirectView("/grupo", true);
    }

    @PostMapping("/id")
    @Transactional
    public String delete(Integer id) {
        var grupo = grupoRepository.findById(id).orElseThrow(() -> new RuntimeException(""));
        List<Conta> contas = contaRepository.findByGrupo(grupo);
        for(Conta conta : contas){
            contaService.ajustaSaldoMovimentacaoContasDelete(conta);
            movimentacaoRepository.deleteByConta(conta);
        }
        contaRepository.deleteByGrupo(grupo);
        service.delete(id);

            return "redirect:/grupo";

    }

    @GetMapping("/alterar")
    public String alterarGrupoView(Model model, HttpSession httpSession){
        Grupo grupo = (Grupo) httpSession.getAttribute("grupo");
        model.addAttribute("grupo", grupo);
        return "alterarGrupo";
    }
}
