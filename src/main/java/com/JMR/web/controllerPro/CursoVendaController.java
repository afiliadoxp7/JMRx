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

import com.JMR.domainPro.Curso;
import com.JMR.repositoryPro.CursoRepository;

@Controller
public class CursoVendaController {

	@Autowired
	private CursoRepository cursoRepository;

	// ===== LISTAR OFERTAS =====
	@GetMapping("/Cursos")
	public String exibirCursos(Model model) {
		Sort sort = Sort.by(Sort.Direction.ASC, "nome");
		PageRequest page = PageRequest.of(0, 40, sort);// Quantidades de cards a mostrar na pagina
		model.addAttribute("listaCursos", this.cursoRepository.findAll(page));
		return "vendas/userCurso/curso-search";
	}

	@Transactional(readOnly = true)
	@GetMapping("/CursoScrollTopUser/ajax")
	public String listarCards(@RequestParam(name = "page", defaultValue = "1") int page, Model model) {
		Sort sort = Sort.by(Sort.Direction.ASC, "nome");
		PageRequest pageRequest = PageRequest.of(page, 40, sort);// Quantidades de cards a mostrar na pagina

		model.addAttribute("listaCursos", this.cursoRepository.findAll(pageRequest));

		return "vendas/userCurso/curso-list-card";
	}

	// ===== Pesquisar OFERTAS =====
	@Transactional(readOnly = false)
	@GetMapping("/pesquisarCursos")
	public String pesquisarCursos(@RequestParam("nome") String nomeCurso, ModelMap model) {

		List<Curso> resultado = this.cursoRepository.findByNomeContainingIgnoreCase(nomeCurso, Sort.by("nome"));
		model.addAttribute("listaCursos", resultado);
		return "vendas/userCurso/curso-search";
	}

}