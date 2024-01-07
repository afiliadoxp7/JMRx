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

import com.JMR.domain.Privacidade;
import com.JMR.service.PrivacidadeService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("privacidades")
public class PrivacidadeController {
	
	@Autowired
	private PrivacidadeService service;

	@GetMapping({"", "/"})
	public String abrir(Privacidade privacidade) {

		return "privacidade/privacidadeCad";
	}
	
	@PostMapping("/salvar")
	public String salvar(Privacidade privacidade, RedirectAttributes attr) {
		try {
            service.salvar(privacidade);
        attr.addFlashAttribute("sucesso", "Operação realizada com sucesso!");
        } catch (Exception e) {
            attr.addFlashAttribute("aviso", "ATENÇÂO! Operação não realizada, Essa  já está cadastrada!");
            return "redirect:/privacidades";
        }
		
		return "redirect:/privacidades";
	}
	
	@GetMapping("/datatables/server")
	public ResponseEntity<?> getPrivacidades(HttpServletRequest request) {

		return ResponseEntity.ok(service.buscarPrivacidades(request));
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("privacidade", service.buscarPorId(id));
		return "privacidade/privacidadeCad";
	}
	
	@GetMapping("/excluir/{id}")
	public String abrir(@PathVariable("id") Long id, RedirectAttributes attr) {
		service.remover(id);
		attr.addFlashAttribute("sucesso", "Operação realizada com sucesso.");
		return "redirect:/privacidades";
	}
}
