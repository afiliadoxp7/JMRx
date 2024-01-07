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

import com.JMR.domain.QtdParcela;
import com.JMR.service.QtdParcelaService;


@Controller
@RequestMapping("qtdParcelas")
public class QtdParcelaController {
	
	@Autowired
	private QtdParcelaService service;

	@GetMapping({"", "/"})
	public String abrir(QtdParcela qtdParcela) {

		return "qtdParcela/qtdParcelaCad";
	}
	
	@PostMapping("/salvar")
	public String salvar(QtdParcela qtdParcela, RedirectAttributes attr) {
		try {
		service.salvar(qtdParcela);
		attr.addFlashAttribute("sucesso", "Operação realizada com sucesso!");
		} catch (Exception e) {
	            attr.addFlashAttribute("aviso", "ATENÇÂO! Operação não realizada! Essa CATEGORIA já está cadastrada!");
	            return "redirect:/qtdParcelas";
	        }
		
		return "redirect:/qtdParcelas";
	}
	
	@GetMapping("/datatables/server")
	public ResponseEntity<?> getQtdParcelas(HttpServletRequest request) {

		return ResponseEntity.ok(service.buscarQtdParcelas(request));
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("qtdParcela", service.buscarPorId(id));
		return "qtdParcela/qtdParcelaCad";
	}
	
	@GetMapping("/excluir/{id}")
	public String abrir(@PathVariable("id") Long id, RedirectAttributes attr) {
		service.remover(id);
		attr.addFlashAttribute("sucesso", "Operação realizada com sucesso.");
		return "redirect:/qtdParcelas";
	}
}
