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

import com.JMR.domainPro.Produto;
import com.JMR.repositoryPro.ProdutoRepository;

@Controller
public class ProdutoVendaController{

	@Autowired
	private ProdutoRepository produtoRepository;

	// ===== LISTAR OFERTAS =====
	@Transactional(readOnly = true)
	@GetMapping("/Produtos")
	public String exibirProdutos(Model model) {
		Sort sort = Sort.by(Sort.Direction.ASC, "nome");
		PageRequest page = PageRequest.of(0, 40, sort);// Quantidades de cards a mostrar na pagina
		model.addAttribute("listaProdutos", this.produtoRepository.findAll(page));
		return "vendas/userProduto/produto-search";
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/ShowScrollTopUser/ajax")
	public String listarCards(@RequestParam(name = "page", defaultValue = "1") int page, Model model) {
		Sort sort = Sort.by(Sort.Direction.ASC, "nome");
		PageRequest pageRequest = PageRequest.of(page, 40, sort);// Quantidades de cards a mostrar na pagina

		model.addAttribute("listaProdutos", this.produtoRepository.findAll(pageRequest));

		return "vendas/userProduto/produto-list-card";
	}

	// ===== Pesquisar OFERTAS =====
	
	@Transactional(readOnly = true)
	@GetMapping("/pesquisarProdutos")
	public String pesquisarProdutos(@RequestParam("nome")String nomeProduto, ModelMap model) {

		List<Produto> resultado = this.produtoRepository
				.findByNomeContainingIgnoreCase(nomeProduto, Sort.by("nome"));
		model.addAttribute("listaProdutos", resultado);
		return "vendas/userProduto/produto-search";
	}

}