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

import com.JMR.domainPro.Livro;
import com.JMR.repositoryPro.LivroRepository;

@Controller
public class LivroVendaController {
	
	@Autowired
	private LivroRepository livroRepository;

	// ===== LISTAR OFERTAS =====
	@GetMapping("/Livros")
	public String exibirLivros(Model model) {
		Sort sort = Sort.by(Sort.Direction.ASC, "nome");
		PageRequest page = PageRequest.of(0, 40, sort);// Quantidades de cards a mostrar na pagina
		model.addAttribute("listaLivros", this.livroRepository.findAll(page));
		return "vendas/userLivro/livro-search";
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/LivroScrollTopUser/ajax")
	public String listarCards(@RequestParam(name = "page", defaultValue = "1") int page, Model model) {
		Sort sort = Sort.by(Sort.Direction.ASC, "nome");
		PageRequest pageRequest = PageRequest.of(page, 40, sort);// Quantidades de cards a mostrar na pagina

		model.addAttribute("listaLivros", this.livroRepository.findAll(pageRequest));

		return "vendas/userLivro/livro-list-card";
	}

	// ===== Pesquisar OFERTAS =====
	@Transactional(readOnly = true)
	@GetMapping("/pesquisarLivros")
	public String pesquisarLivros(@RequestParam("nome")String nomeLivro, ModelMap model) {

		List<Livro> resultado = this.livroRepository
				.findByNomeContainingIgnoreCase(nomeLivro, Sort.by("nome"));
		model.addAttribute("listaLivros", resultado);
		return "vendas/userLivro/livro-search";
	}

}