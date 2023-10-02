package br.com.caixa.controller;

import br.com.caixa.models.Conta;
import br.com.caixa.models.RelatorioResponseGrupo;
import br.com.caixa.service.ContaService;
import br.com.caixa.service.SaldoGeralService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @Autowired
    private ContaService contaService;

    @Autowired
    private SaldoGeralService saldoGeralService;

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/home/saldo")
    public String verSaldoViem(Model model){
        model.addAttribute("contas", contaService.findAll());
        return "formSaldo";
    }

    @PostMapping("/home/contasaldo")
    public String formVerSaldo(Integer idConta, HttpSession httpSession){
        Conta conta = contaService.findById(idConta);
        httpSession.setAttribute("conta", conta);
        return "redirect:/home/saldoview";
    }

    @GetMapping("/home/saldoview")
    public String viewSaldo(Model model){
        var saldo = saldoGeralService.getSaldo();
        model.addAttribute("saldo", saldo);
        return "saldoView";
    }
}
