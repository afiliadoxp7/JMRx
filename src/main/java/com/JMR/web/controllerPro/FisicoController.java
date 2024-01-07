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

import com.JMR.domainPro.Fisico;
import com.JMR.repositoryPro.FisicoRepository;
import com.JMR.servicePro.FisicoService;

import jakarta.validation.Valid;

@Controller
public class FisicoController {
	
	@Autowired
	private FisicoService serviceF;	
	@Autowired
	private FisicoRepository fisicoRepository;
	
	// ===== EXIBIR O FORMULARIO DE CADASTRO DE OFERTAS =====

	@GetMapping("/exibirFormFisico")
	public String exibirForm(Fisico fisico, Model model) {

		serviceF.exibir(fisico, model);
		return "fisico/fisico-form";
	}
	
	@PostMapping("/salvarFisico")
	public String salvarFisico(@Valid Fisico fisico, RedirectAttributes attr,
								BindingResult result, Model model,
								@RequestParam("fileFisico") MultipartFile file) {
		if (result.hasErrors()) {
			return exibirForm(fisico, model);
		}		
		try {
			fisico.setImagem(file.getBytes());
			serviceF.salvar(fisico);
			attr.addFlashAttribute("sucesso", "Operação realizada com sucesso!");
			return "redirect:/exibirFormFisico";
			
		} catch (Exception e) {			
			attr.addFlashAttribute("aviso", "ATENÇÂO! Operação não realizada, Conclua outro cadastro!");
            return "redirect:/exibirFormFisico";
		} 
		
	}
	
	// ===== LISTAR OFERTAS =====

	@Transactional(readOnly = true)
	@GetMapping("/listarFisicos")
	public String exibirLista(Model model) {
		Sort sort = Sort.by(Sort.Direction.ASC, "nome");
		PageRequest page = PageRequest.of(0, 40, sort);// Quantidades de cards a mostrar na pagina
		model.addAttribute("listaFisicos", this.fisicoRepository.findAll(page));
		return "fisico/fisicoListSearch";

	}

	@Transactional(readOnly = true)
	@GetMapping("/FisicoScrollTopSyst/ajax")
	public String listarCards(@RequestParam(name = "page", defaultValue = "1") int page, Model model) {
		Sort sort = Sort.by(Sort.Direction.ASC, "nome");
		PageRequest pageRequest = PageRequest.of(page, 40, sort);// Quantidades de cards a mostrar na pagina

		model.addAttribute("listaFisicos", this.fisicoRepository.findAll(pageRequest));
		return "fisico/fisico-list-card";
	}
	
	// ===== EDITAR OFERTAS =====

	@GetMapping("/editarFisico")
	public String editarFisico(Fisico fisico, Long id, Model model) {
		serviceF.editar(fisico, id, model);
		return "fisico/fisico-form";
	}
	

	@GetMapping("/imagemFisico/{idprod}")
	@ResponseBody
	public byte[] exibirImagen(@PathVariable("idprod") Long idprod) {

		return serviceF.image(idprod);
	}
	
	
	// ===== Pesquisar OFERTAS =====
	
	@Transactional(readOnly = false)
	@PostMapping("/buscarFisicos")
	public String buscarFisicos(String nomeFisico, Model model) {

		List<Fisico> resultado = this.fisicoRepository
				.findByNomeContainingIgnoreCase(nomeFisico, Sort.by("nome"));
		model.addAttribute("listaFisicos", resultado);
		return "fisico/fisicoListSearch";
	}
}
