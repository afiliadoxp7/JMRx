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

import com.JMR.domain.Fornecedor;
import com.JMR.service.FornecedorService;



@Controller
@RequestMapping("fornecedores")
public class FornecedorController {
	
	@Autowired
	private FornecedorService service;

	@GetMapping({"", "/"})
	public String abrir(Fornecedor fornecedor) {

		return "fornecedor/fornecedorCad";
	}
	
	@PostMapping("/salvar")
	public String salvar(Fornecedor fornecedor, RedirectAttributes attr) {
		try {
			service.salvar(fornecedor);
			attr.addFlashAttribute("sucesso", "Operação realizada com sucesso!");
		} catch (Exception e) {
			 attr.addFlashAttribute("aviso", "ATENÇÂO! Operação não realizada! Essa FORNECEDOR já está cadastrado!");
	            return "redirect:/fornecedores";
		}
		
		return "redirect:/fornecedores";
	}
	
	@GetMapping("/datatables/server")
	public ResponseEntity<?> getFornecedores(HttpServletRequest request) {

		return ResponseEntity.ok(service.buscarFornecedores(request));
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("fornecedor", service.buscarPorId(id));
		return "fornecedor/fornecedorCad";
	}
	
	@GetMapping("/excluir/{id}")
	public String abrir(@PathVariable("id") Long id, RedirectAttributes attr) {
		service.remover(id);
		attr.addFlashAttribute("sucesso", "Operação realizada com sucesso.");
		return "redirect:/fornecedores";
	}
}