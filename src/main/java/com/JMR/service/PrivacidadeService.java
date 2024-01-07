package com.JMR.service;

import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.JMR.datatables.Datatables;
import com.JMR.datatables.DatatablesColunas;
import com.JMR.domain.Privacidade;
import com.JMR.repository.PrivacidadeRepository;

@Service
public class PrivacidadeService {

	@Autowired
	private PrivacidadeRepository repository;
	@Autowired
	private Datatables datatables;

	@Transactional(readOnly = false)
	public void salvar(Privacidade privacidade) {
		
		repository.save(privacidade);
	}

	@Transactional(readOnly = true)
	public Map<String, Object> buscarPrivacidades(HttpServletRequest request) {
		datatables.setRequest(request);
		datatables.setColunas(DatatablesColunas.PRIVACIDADES);
		Page<?> page = datatables.getSearch().isEmpty()
				? repository.findAll(datatables.getPageable())
				: repository.findAllByTitulo(datatables.getSearch(), datatables.getPageable());
		return datatables.getResponse(page);
	}

	@Transactional(readOnly = true)
	public Privacidade buscarPorId(Long id) {
		
		return repository.findById(id).get();
	}

	@Transactional(readOnly = false)
	public void remover(Long id) {
		
		repository.deleteById(id);
	}
	
	
}
