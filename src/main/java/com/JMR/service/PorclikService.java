package com.JMR.service;


import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.JMR.datatables.Datatables;
import com.JMR.datatables.DatatablesColunas;
import com.JMR.domain.Porclik;
import com.JMR.repository.PorclikRepository;

@Service
public class PorclikService {

	@Autowired
	private PorclikRepository repository;
	@Autowired
	private Datatables datatables;

	@Transactional(readOnly = false)
	public void salvar(Porclik porclik) {
		
		repository.save(porclik);
	}

	@Transactional(readOnly = true)
	public Map<String, Object> buscarPorcliks(HttpServletRequest request) {
		datatables.setRequest(request);
		datatables.setColunas(DatatablesColunas.PORCLIKS);
		Page<?> page = datatables.getSearch().isEmpty()
				? repository.findAll(datatables.getPageable())
				: repository.findAllByTitulo(datatables.getSearch(), datatables.getPageable());
		return datatables.getResponse(page);
	}

	@Transactional(readOnly = true)
	public Porclik buscarPorId(Long id) {		
		return repository.findById(id).get();
	}

	@Transactional(readOnly = false)
	public void remover(Long id) {
		
		repository.deleteById(id);
	}	
	
}
