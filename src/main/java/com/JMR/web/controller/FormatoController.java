package com.JMR.web.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.JMR.domain.Formato;
import com.JMR.service.FormatoService;

import jakarta.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("formatos")
public class FormatoController {
	
	@Autowired
	private FormatoService service;

	@GetMapping({"", "/"})
	public String abrir(Formato formato) {

		return "formato/formatoCad";
	}
	
	@PostMapping("/salvar")
	public String salvar(Formato formato, RedirectAttributes attr) {
		try {
			service.salvar(formato);
			attr.addFlashAttribute("sucesso", "Operação realizada com sucesso!");			
		} catch (Exception e) {
			  attr.addFlashAttribute("aviso", "ATENÇÂO! Operação não realizada! Essa FORMATO já está cadastrada!");
	            return "redirect:/formatos";
		}
		
		return "redirect:/formatos";
	}
	
	@GetMapping("/datatables/server")
	public ResponseEntity<?> getFormatos(HttpServletRequest request) {

		return ResponseEntity.ok(service.buscarFormatos(request));
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("formato", service.buscarPorId(id));
		return "formato/formatoCad";
	}
	
	@GetMapping("/excluir/{id}")
	public String abrir(@PathVariable("id") Long id, RedirectAttributes attr) {
		service.remover(id);
		attr.addFlashAttribute("sucesso", "Operação realizada com sucesso.");
		return "redirect:/formatos";
	}
}
