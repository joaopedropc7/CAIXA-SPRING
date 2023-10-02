package br.com.caixa.controller;

import br.com.caixa.data.vo.v1.GrupoVO;
import br.com.caixa.data.vo.v1.MovimentacaoVO;
import br.com.caixa.models.TipoConta;
import br.com.caixa.models.TipoReceita;
import br.com.caixa.models.records.MovimentacaoRecord;
import br.com.caixa.service.ContaService;
import br.com.caixa.service.MovimentacaoService;
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
@RequestMapping("/movimentacao")
public class MovimentacaoController {

    @Autowired
    private ContaService contaService;

    @Autowired
    private MovimentacaoService service;

    @GetMapping("/cadastro")
    public String viewForm(Model model){
        List<String> tiposDeReceita = EnumSet.allOf(TipoReceita.class)
                .stream()
                .map(Enum::name)
                .collect(Collectors.toList());
        model.addAttribute("contas", contaService.findAll()).addAttribute("tipoReceita", tiposDeReceita);
        return "formMovimentacao";
    }

    @PostMapping
    public RedirectView create(MovimentacaoRecord movimentacaoRecord, RedirectAttributes redirectAttributes){
       service.create(movimentacaoRecord);
        redirectAttributes.addFlashAttribute("mensagem", "Movimentação realizzada com sucesso!");
        return new RedirectView("/home", true);
    }

    @GetMapping
    public List<MovimentacaoVO> findAll(){
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    public MovimentacaoVO findById(@PathVariable(value = "id") Integer id){
        return service.findById(id);
    }

    @PutMapping
    public MovimentacaoVO update(@RequestBody MovimentacaoVO movimentacaoVO){
        return service.update(movimentacaoVO);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(value = "id") Integer id){
        service.delete(id);
    }
}
