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

import com.JMR.domainPro.Curso;
import com.JMR.repositoryPro.CursoRepository;
import com.JMR.servicePro.CursoService;

import jakarta.validation.Valid;

@Controller
public class CursoController {
	
	@Autowired
	private CursoService serviceC;
	@Autowired
	private CursoRepository cursoRepository;	
	
	// ===== EXIBIR O FORMULARIO DE CADASTRO DE OFERTAS =====

	@GetMapping("/exibirFormCurso")
	public String exibirForm(Curso curso, Model model) {			
		serviceC.exibir(curso, model);
		return "curso/curso-form";
	}
	
	@PostMapping("/salvarCurso")
	public String salvarCurso(@Valid Curso curso, RedirectAttributes attr,
								BindingResult result, Model model,
								@RequestParam("fileCurso") MultipartFile file) {
		if (result.hasErrors()) {
			return exibirForm(curso, model);
		}		
		try {
			curso.setImagem(file.getBytes());
			serviceC.salvar(curso);			
			attr.addFlashAttribute("sucesso", "Operação realizada com sucesso!");
			return "redirect:/exibirFormCurso";
		} catch (Exception e) {			
			attr.addFlashAttribute("aviso", "ATENÇÂO! Operação não realizada, Conclua outro cadastro!");
            return "redirect:/exibirFormCurso";
		} 
		
	}
	
	// ===== LISTAR OFERTAS =====

	@Transactional(readOnly = true)
	@GetMapping("/listarCursos")
	public String exibirLista(Model model) {
		Sort sort = Sort.by(Sort.Direction.ASC, "nome");
		PageRequest page = PageRequest.of(0, 40, sort);// Quantidades de cards a mostrar na pagina
		model.addAttribute("listaCursos", this.cursoRepository.findAll(page));
		return "curso/cursoListSearch";

	}

	@Transactional(readOnly = true)
	@GetMapping("/CursoScrollTopSyst/ajax")
	public String listarCards(@RequestParam(name = "page", defaultValue = "1") int page, Model model) {
		Sort sort = Sort.by(Sort.Direction.ASC, "nome");
		PageRequest pageRequest = PageRequest.of(page, 40, sort);// Quantidades de cards a mostrar na pagina

		model.addAttribute("listaCursos", this.cursoRepository.findAll(pageRequest));
		return "curso/curso-list-card";
	}
	
	// ===== EDITAR OFERTAS =====

	@GetMapping("/editarCurso")
	public String editarCurso(Curso curso, Long id, Model model) {
		serviceC.editar(curso, id, model);
		return "curso/curso-form";
	}
	
	@GetMapping("/imagemCurso/{idprod}")
	@ResponseBody
	public byte[] exibirImagen(@PathVariable("idprod") Long idprod) {
		return serviceC.image(idprod);	 
	}	
	
	// ===== Pesquisar OFERTAS =====
	
	@Transactional(readOnly = false)
	@PostMapping("/buscarCursos")
	public String buscarCursos(String nomeCurso, Model model) {

		List<Curso> resultado = this.cursoRepository.findByNomeContainingIgnoreCase(nomeCurso, Sort.by("nome"));
		model.addAttribute("listaCursos", resultado);
		return "curso/cursoListSearch";
	}
}
