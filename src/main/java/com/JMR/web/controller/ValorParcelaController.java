package com.JMR.web.controller;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.JMR.domain.ValorParcela;
import com.JMR.service.ValorParcelaService;

@Controller
@RequestMapping("valorParcelas")
public class ValorParcelaController {

    @Autowired
    private ValorParcelaService service;

    @GetMapping({"", "/"})
    public String abrir(ValorParcela valorParcela) {

        return "valorParcela/valorParcelaCad";
    }

    @PostMapping("/salvar")
    public String salvar(ValorParcela valorParcela, RedirectAttributes attr) {
        try {
            service.salvar(valorParcela);
        attr.addFlashAttribute("sucesso", "Operação realizada com sucesso!");
        } catch (Exception e) {
            attr.addFlashAttribute("aviso", "ATENÇÂO! Operação não realizada! "
            		+ "Essa VALOR DE PARCELA já está cadastrada!");
            return "redirect:/valorParcelas";
        }
        

        return "redirect:/valorParcelas";
    }

    @GetMapping("/datatables/server")
    public ResponseEntity<?> getValorParcelas(HttpServletRequest request) {

        return ResponseEntity.ok(service.buscarValorParcelas(request));
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("valorParcela", service.buscarPorId(id));
        return "valorParcela/valorParcelaCad";
    }

    @GetMapping("/excluir/{id}")
    public String abrir(@PathVariable("id") Long id, RedirectAttributes attr) {
        service.remover(id);
        attr.addFlashAttribute("sucesso", "Operação realizada com sucesso.");
        return "redirect:/valorParcelas";
    }
}