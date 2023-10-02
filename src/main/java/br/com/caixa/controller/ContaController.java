package br.com.caixa.controller;

import br.com.caixa.data.vo.v1.ContaVO;
import br.com.caixa.data.vo.v1.GrupoVO;
import br.com.caixa.models.Conta;
import br.com.caixa.models.Grupo;
import br.com.caixa.models.TipoConta;
import br.com.caixa.models.records.ContaAlterRecord;
import br.com.caixa.models.records.ContaRecord;
import br.com.caixa.models.records.GrupoAlterRecord;
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

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;
    @Autowired
    private ContaService contaService;
    @Autowired
    private GrupoService grupoService;

    @GetMapping("/cadastro")
    public String contaForm(Model model){
        List<String> tiposDeConta = EnumSet.allOf(TipoConta.class)
                .stream()
                .map(Enum::name)
                .collect(Collectors.toList());
        model.addAttribute("grupos", grupoService.findAll()).addAttribute("tipoConta", tiposDeConta);
        return "formConta";
    }

    @PostMapping
    public RedirectView create(ContaRecord contaRecord, RedirectAttributes redirectAttributes){
        contaService.create(contaRecord);

        redirectAttributes.addFlashAttribute("mensagem", "Conta criada com sucesso!");
        return new RedirectView("/contas", true);
    }

    @GetMapping
    public String findAll(Model model){
        model.addAttribute("contas", contaService.findAll());
        return "listConta";
    }

    @GetMapping(value = "/{id}")
    public Conta findById(@PathVariable(value = "id") Integer id){
        return contaService.findById(id);
    }

    @PostMapping("/id")
    @Transactional
    public String delete(Integer id) {
        var conta = contaService.findById(id);
        contaService.ajustaSaldoMovimentacaoContasDelete(conta);
        movimentacaoRepository.deleteByConta(conta);
        contaService.delete(id);
        return "redirect:/contas";
    }

    @PostMapping("/findid")
    public String findById(Integer id, HttpSession httpSession){
        var conta = contaService.findById(id);
        httpSession.setAttribute("conta", conta);
        return "redirect:/contas/alterar";
    }

    @GetMapping("/alterar")
    public String viewAlterar(Model model, HttpSession httpSession){
        Conta conta = (Conta) httpSession.getAttribute("conta");
        List<String> tiposDeConta = EnumSet.allOf(TipoConta.class)
                .stream()
                .map(Enum::name)
                .collect(Collectors.toList());
        model.addAttribute("grupos", grupoService.findAll()).addAttribute("tipoConta", tiposDeConta).addAttribute("conta", conta);
        return "alterarConta";
    }

    @PostMapping("/update")
    public RedirectView update(ContaAlterRecord contaAlterRecord, RedirectAttributes redirectAttributes){
        contaService.update(contaAlterRecord);
        redirectAttributes.addFlashAttribute("mensagem", "Grupo alterado com sucesso!");
        return new RedirectView("/contas", true);
    }
}
