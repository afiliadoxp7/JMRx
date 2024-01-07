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

import com.JMR.domainPro.Parceiro;
import com.JMR.repositoryPro.ParceiroRepository;
import com.JMR.servicePro.ParceiroService;

import jakarta.validation.Valid;

@Controller
public class ParceiroController {

	@Autowired
	private ParceiroService serviceP;
	@Autowired
	private ParceiroRepository parceiroRepository;

	// ===== EXIBIR O FORMULARIO DE CADASTRO DE OFERTAS =====

	@GetMapping("/exibirFormParceiro")
	public String exibirForm(Parceiro parceiro, Model model) {

		serviceP.exibir(parceiro,model);
		return "parceiro/parceiro-form";
	}
	
	@PostMapping("/salvarParceiro")
	public String salvarParceiro(@Valid Parceiro parceiro, RedirectAttributes attr,
								BindingResult result, Model model,
								@RequestParam("fileParceiro") MultipartFile file) {
		if (result.hasErrors()) {
			return exibirForm(parceiro, model);
		}		
		try {
			parceiro.setImagem(file.getBytes());						
			serviceP.salvar(parceiro);			
			attr.addFlashAttribute("sucesso", "Operação realizada com sucesso!");
			return "redirect:/exibirFormParceiro";
		} catch (Exception e) {			
			attr.addFlashAttribute("aviso", "ATENÇÂO! Operação não realizada, URL do Site já cadastrado!");
            return "redirect:/exibirFormParceiro";
		} 
		
	}
	// ===== LISTAR OFERTAS =====

	@Transactional(readOnly = true)
	@GetMapping("/listarParceiros")
	public String exibirLista(Model model) {
		Sort sort = Sort.by(Sort.Direction.ASC, "nome");
		PageRequest page = PageRequest.of(0, 8, sort);// Quantidades de cards a mostrar na pagina
		model.addAttribute("listaParceiros", this.parceiroRepository.findAll(page));
		return "parceiro/parceiroListSearch";

	}

	@Transactional(readOnly = true)
	@GetMapping("/ParceiroScrollTopSyst/ajax")
	public String listarCards(@RequestParam(name = "page", defaultValue = "1") int page, Model model) {
		Sort sort = Sort.by(Sort.Direction.ASC, "nome");
		PageRequest pageRequest = PageRequest.of(page, 8, sort);// Quantidades de cards a mostrar na pagina

		model.addAttribute("listaParceiros", this.parceiroRepository.findAll(pageRequest));
		return "parceiro/parceiro-list-card";
	}

	// ===== EDITAR OFERTAS =====
	@GetMapping("/editarParceiro")
	public String editarParceiro(Parceiro parceiro, Long id, Model model) {
		serviceP.editar(parceiro,id,model);
		return "parceiro/parceiro-form";
	}

	@GetMapping("/imagemParceiro/{idprod}")
	@ResponseBody
	public byte[] exibirImagen(@PathVariable("idprod") Long idprod) {
		return serviceP.image(idprod);
		
	}

	// ===== Pesquisar OFERTAS =====
	@Transactional(readOnly = true)
	@PostMapping("/buscarParceiros")
	public String buscarParceiros(String nomeParceiro, Model model) {

		List<Parceiro> resultado = this.parceiroRepository.findByNomeContainingIgnoreCase(nomeParceiro,
				Sort.by("nome"));
		model.addAttribute("listaParceiros", resultado);
		return "parceiro/parceiroListSearch";
	}
}
