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

import com.JMR.domain.Categoria;
import com.JMR.service.CategoriaService;


@Controller
@RequestMapping("categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaService service;

	@GetMapping({"", "/"})
	public String abrir(Categoria categoria) {

		return "categoria/categoriaCad";
	}
	
	@PostMapping("/salvar")
	public String salvar(Categoria categoria, RedirectAttributes attr) {
		try {
		service.salvar(categoria);
		attr.addFlashAttribute("sucesso", "Operação realizada com sucesso!");
		} catch (Exception e) {
	            attr.addFlashAttribute("aviso", "ATENÇÂO! Operação não realizada! Essa CATEGORIA já está cadastrada!");
	            return "redirect:/categorias";
	        }
		
		return "redirect:/categorias";
	}
	
	@GetMapping("/datatables/server")
	public ResponseEntity<?> getCategorias(HttpServletRequest request) {

		return ResponseEntity.ok(service.buscarCategorias(request));
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("categoria", service.buscarPorId(id));
		return "categoria/categoriaCad";
	}
	
	@GetMapping("/excluir/{id}")
	public String abrir(@PathVariable("id") Long id, RedirectAttributes attr) {
		service.remover(id);
		attr.addFlashAttribute("sucesso", "Operação realizada com sucesso.");
		return "redirect:/categorias";
	}
}
