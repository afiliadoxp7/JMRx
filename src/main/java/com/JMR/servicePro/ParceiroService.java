package com.JMR.servicePro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.JMR.domainPro.Parceiro;
import com.JMR.repository.PlataformaRepository;
import com.JMR.repositoryPro.ParceiroRepository;

@Service
public class ParceiroService {
	
	@Autowired
	private ParceiroRepository parceiroRepository;

	@Autowired
	private PlataformaRepository plataformaRepository;

	@Transactional(readOnly = false)
	public void salvar(Parceiro parceiro) {		
		parceiroRepository.save(parceiro);
	}

	@Transactional(readOnly = true)
	public void exibir(Parceiro parceiro, Model model) {
		model.addAttribute("listaPlataformas", this.plataformaRepository.findAll(Sort.by("titulo")));
		model.addAttribute("listaPlataformas", this.plataformaRepository.findAll(Sort.by("descricao")));		
	}

	@Transactional(readOnly = true)
	public void editar(Parceiro parceiro, Long id, Model model) {
		model.addAttribute("parceiro", this.parceiroRepository.findById(id));
		exibir(parceiro, model);		
	}
	@Transactional(readOnly = false)
	public byte[] image(Long idprod) {
		Parceiro parceiro = this.parceiroRepository.getReferenceById(idprod);
		return parceiro.getImagem();
	}

}
