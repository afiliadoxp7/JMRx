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

import com.JMR.domain.PctgDesconto;
import com.JMR.service.PctgDescontoService;


@Controller
@RequestMapping("pctgDescontos")
public class PctgDescontoController {
	
	@Autowired
	private PctgDescontoService service;

	@GetMapping({"", "/"})
	public String abrir(PctgDesconto pctgDesconto) {

		return "pctgDesconto/pctgDescontoCad";
	}
	
	@PostMapping("/salvar")
	public String salvar(PctgDesconto pctgDesconto, RedirectAttributes attr) {
		try {
		service.salvar(pctgDesconto);
		attr.addFlashAttribute("sucesso", "Operação realizada com sucesso!");
		} catch (Exception e) {
	            attr.addFlashAttribute("aviso", "ATENÇÂO! Operação não realizada! Essa CATEGORIA já está cadastrada!");
	            return "redirect:/pctgDescontos";
	        }
		
		return "redirect:/pctgDescontos";
	}
	
	@GetMapping("/datatables/server")
	public ResponseEntity<?> getPctgDescontos(HttpServletRequest request) {

		return ResponseEntity.ok(service.buscarPctgDescontos(request));
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("pctgDesconto", service.buscarPorId(id));
		return "pctgDesconto/pctgDescontoCad";
	}
	
	@GetMapping("/excluir/{id}")
	public String abrir(@PathVariable("id") Long id, RedirectAttributes attr) {
		service.remover(id);
		attr.addFlashAttribute("sucesso", "Operação realizada com sucesso.");
		return "redirect:/pctgDescontos";
	}
}
