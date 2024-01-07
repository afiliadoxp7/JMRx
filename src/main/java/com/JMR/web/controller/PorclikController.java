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

import com.JMR.domain.Porclik;
import com.JMR.service.PorclikService;

@Controller
@RequestMapping("porcliks")
public class PorclikController {

	@Autowired
	private PorclikService service;

	@GetMapping({ "", "/" })
	public String abrir(Porclik porclik) {

		return "porclik/porclikCad";
	}

	@PostMapping("/salvar")
	public String salvar(Porclik porclik, RedirectAttributes attr) {
		try {
			service.salvar(porclik);
			attr.addFlashAttribute("sucesso", "Operação realizada com sucesso!");
		} catch (Exception e) {
			attr.addFlashAttribute("aviso", "ATENÇÂO! Operação não realizada! Essa Porclik já está cadastrada!");
			return "redirect:/porcliks";
		}
		return "redirect:/porcliks";
	}

	@GetMapping("/datatables/server")
	public ResponseEntity<?> getPorcliks(HttpServletRequest request) {

		return ResponseEntity.ok(service.buscarPorcliks(request));
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("porclik", service.buscarPorId(id));
		return "porclik/porclikCad";
	}

	@GetMapping("/excluir/{id}")
	public String abrir(@PathVariable("id") Long id, RedirectAttributes attr) {
		service.remover(id);
		attr.addFlashAttribute("sucesso", "Operação realizada com sucesso.");
		return "redirect:/porcliks";
	}
}