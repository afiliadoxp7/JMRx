package com.JMR.web.controllerPro;

import java.util.List;

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

import com.JMR.domainPro.Livro;
import com.JMR.repositoryPro.LivroRepository;
import com.JMR.servicePro.LivroService;

import jakarta.validation.Valid;

@Controller
public class LivroController {
	
	@Autowired
	private LivroService serviceL;
	@Autowired
	private LivroRepository livroRepository;
		
	// ===== EXIBIR O FORMULARIO DE CADASTRO DE OFERTAS =====
	
	@GetMapping("/exibirFormLivro")
	public String exibirForm(Livro livro, Model model) {

		serviceL.exibir(livro, model);
		return "livro/livro-form";
	}
	
	@PostMapping("/salvarLivro")
	public String salvarLivro(@Valid Livro livro, RedirectAttributes attr,
								BindingResult result, Model model,
								@RequestParam("fileLivro") MultipartFile file) {
		if (result.hasErrors()) {
			return exibirForm(livro, model);
		}		
		try {
			livro.setImagem(file.getBytes());
			serviceL.salvar(livro);			
			attr.addFlashAttribute("sucesso", "Operação realizada com sucesso!");
			return "redirect:/exibirFormLivro";
		} catch (Exception e) {			
			attr.addFlashAttribute("aviso", "ATENÇÂO! Operação não realizada, Conclua outro cadastro!");
            return "redirect:/exibirFormLivro";
		} 
		
	}
	
	// ===== LISTAR OFERTAS =====

	@Transactional(readOnly = true)
	@GetMapping("/listarLivros")
	public String exibirLista(Model model) {
		Sort sort = Sort.by(Sort.Direction.ASC, "nome");
		PageRequest page = PageRequest.of(0, 40, sort);// Quantidades de cards a mostrar na pagina
		model.addAttribute("listaLivros", this.livroRepository.findAll(page));
		return "livro/livroListSearch";

	}

	@Transactional(readOnly = true)
	@GetMapping("/LivroScrollTopSyst/ajax")
	public String listarCards(@RequestParam(name = "page", defaultValue = "1") int page, Model model) {
		Sort sort = Sort.by(Sort.Direction.ASC, "nome");
		PageRequest pageRequest = PageRequest.of(page, 40, sort);// Quantidades de cards a mostrar na pagina

		model.addAttribute("listaLivros", this.livroRepository.findAll(pageRequest));
		return "livro/livro-list-card";
	}
	
	// ===== EDITAR OFERTAS =====

	@GetMapping("/editarLivro")
	public String editarLivro(Livro livro, Long id, Model model) {
		
		serviceL.editar(livro, id, model);	
		return "livro/livro-form";
	}
	
	@GetMapping("/imagemLivro/{idprod}")
	@ResponseBody
	public byte[] exibirImagen(@PathVariable("idprod") Long idprod) {

		return serviceL.image(idprod);
	}
	
	
	// ===== Pesquisar OFERTAS =====
	
	@Transactional(readOnly = true)
	@PostMapping("/buscarLivros")
	public String buscarLivros(String nomeLivro, Model model) {

		List<Livro> resultado = this.livroRepository
				.findByNomeContainingIgnoreCase(nomeLivro, Sort.by("nome"));
		model.addAttribute("listaLivros", resultado);
		return "livro/livroListSearch";
	}
}
