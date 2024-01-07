package com.JMR.web.controllerPro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.JMR.domainPro.Fisico;
import com.JMR.repositoryPro.FisicoRepository;

@Controller
public class FisicoVendaController {
	
	@Autowired
	private FisicoRepository fisicoRepository;

	// ===== LISTAR OFERTAS =====
	@GetMapping("/Fisicos")
	public String exibirFisicos(Model model) {
		Sort sort = Sort.by(Sort.Direction.ASC, "nome");
		PageRequest page = PageRequest.of(0, 40, sort);// Quantidades de cards a mostrar na pagina
		model.addAttribute("listaFisicos", this.fisicoRepository.findAll(page));
		return "vendas/userFisico/fisico-search";
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/FisicoScrollTopUser/ajax")
	public String listarCards(@RequestParam(name = "page", defaultValue = "1") int page, Model model) {
		Sort sort = Sort.by(Sort.Direction.ASC, "nome");
		PageRequest pageRequest = PageRequest.of(page, 40, sort);// Quantidades de cards a mostrar na pagina

		model.addAttribute("listaFisicos", this.fisicoRepository.findAll(pageRequest));

		return "vendas/userFisico/fisico-list-card";
	}

	// ===== Pesquisar OFERTAS =====
	
	@Transactional(readOnly = true)
	@GetMapping("/pesquisarFisicos")
	public String pesquisarFisicos(@RequestParam("nome")String nomeFisico, ModelMap model) {

		List<Fisico> resultado = this.fisicoRepository
				.findByNomeContainingIgnoreCase(nomeFisico, Sort.by("nome"));
		model.addAttribute("listaFisicos", resultado);
		return "vendas/userFisico/fisico-search";
	}

}