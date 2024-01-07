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

import com.JMR.domainPro.Parceiro;
import com.JMR.repositoryPro.ParceiroRepository;

@Controller
public class ParceiroVendaController {
	
	@Autowired
	private ParceiroRepository parceiroRepository;

	// ===== LISTAR OFERTAS =====
	@GetMapping("/Parceiros")
	public String exibirParceiros(Model model) {
		Sort sort = Sort.by(Sort.Direction.ASC, "nome");
		PageRequest page = PageRequest.of(0, 40, sort);// Quantidades de cards a mostrar na pagina
		model.addAttribute("listaParceiros", this.parceiroRepository.findAll(page));
		return "vendas/userParceiro/parceiro-search";
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/ParceiroScrollTopUser/ajax")
	public String listarCards(@RequestParam(name = "page", defaultValue = "1") int page, Model model) {
		Sort sort = Sort.by(Sort.Direction.ASC, "nome");
		PageRequest pageRequest = PageRequest.of(page, 40, sort);// Quantidades de cards a mostrar na pagina

		model.addAttribute("listaParceiros", this.parceiroRepository.findAll(pageRequest));

		return "vendas/userParceiro/parceiro-list-card";
	}

	// ===== Pesquisar OFERTAS =====

	@Transactional(readOnly = true)
	@GetMapping("/pesquisarParceiros")
	public String pesquisarParceiros(@RequestParam("nome")String nomeParceiro, ModelMap model) {

		List<Parceiro> resultado = this.parceiroRepository
				.findByNomeContainingIgnoreCase(nomeParceiro, Sort.by("nome"));
		model.addAttribute("listaParceiros", resultado);
		return "vendas/userParceiro/parceiro-search";
	}

}