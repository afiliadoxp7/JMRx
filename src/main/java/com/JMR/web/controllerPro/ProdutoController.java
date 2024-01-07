package com.JMR.web.controllerPro;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.JMR.domainPro.Produto;
import com.JMR.repositoryPro.ProdutoRepository;
import com.JMR.servicePro.ProdutoService;

@Controller
public class ProdutoController {

	@Autowired
	private ProdutoService serviceP;
	
	@Autowired
	private ProdutoRepository produtoRepository;

	// ===== EXIBIR O FORM CADASTRO DE OFERTAS =====

	@GetMapping("/exibirFormProduto")
	public String exibirForm(Produto produto, Model model) {
		serviceP.exibir(produto, model);
		return "produto/produto-form";
	}

	@PostMapping("/salvarProduto")
	public String salvarProduto(@Valid Produto produto, RedirectAttributes attr, BindingResult result, Model model,
			@RequestParam("fileProduto") MultipartFile file) {
	
		try {
			produto.setImagem(file.getBytes());			
			serviceP.salvar(produto);			
			attr.addFlashAttribute("sucesso", "Operação realizada com sucesso!");				
			return "redirect:/exibirFormProduto";
		} catch (Exception e) {
			attr.addFlashAttribute("aviso", "ATENÇÂO! Operação não realizada, Conclua outro cadastro!");
			return "redirect:/exibirFormProduto";
		}
		
	}
	
	// ===== LISTAR OFERTAS =====

	@Transactional(readOnly = true)
	@GetMapping("/listarProdutos")
	public String exibirLista(Model model) {
		Sort sort = Sort.by(Sort.Direction.ASC, "nome");
		PageRequest page = PageRequest.of(0, 40, sort);// Quantidades de cards a mostrar na pagina
		model.addAttribute("listaProdutos", this.produtoRepository.findAll(page));
		return "produto/produtoListSearch";

	}
	
	@Transactional(readOnly = true)
	@GetMapping("/ListScrollTopSyst/ajax")
	public String listarCards(@RequestParam(name = "page", defaultValue = "1") int page, Model model) {
		Sort sort = Sort.by(Sort.Direction.ASC, "nome");
		PageRequest pageRequest = PageRequest.of(page, 40, sort);// Quantidades de cards a mostrar na pagina

		model.addAttribute("listaProdutos", this.produtoRepository.findAll(pageRequest));
		return "produto/produto-list-card";
	}

	// ===== EDITAR OFERTAS =====

	@GetMapping("/editarProduto")
	public String editarProduto(Produto produto, Long id, Model model) {
		serviceP.editar(produto, id, model);
		return "produto/produto-form";
	}	

	@GetMapping("/imagem/{idprod}")
	@ResponseBody
	public byte[] exibirImagen(@PathVariable("idprod") Long idprod) {

		return serviceP.image(idprod);
	}

	/*
	 * //@Transactional(readOnly = false)
	 * 
	 * @GetMapping("/removerProduto") public String removerProduto(Long id) {
	 * this.produtoRepository.deleteById(id); return "redirect:/listarProdutos"; }
	 */

	// ===== Pesquisar OFERTAS =====

	@Transactional(readOnly = true)
	@PostMapping ("/buscarProdutos")
	public String buscarProdutos(String nomeProduto, Model model) {

		List<Produto> resultado = this.produtoRepository.findByNomeContainingIgnoreCase(nomeProduto, Sort.by("nome"));
		model.addAttribute("listaProdutos", resultado);
		return "produto/produtoListSearch";
	}

}