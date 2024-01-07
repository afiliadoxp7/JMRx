package com.JMR.service;

import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.JMR.datatables.Datatables;
import com.JMR.datatables.DatatablesColunas;
import com.JMR.domain.PctgDesconto;
import com.JMR.repository.PctgDescontoRepository;

@Service
public class PctgDescontoService {

	@Autowired
	private PctgDescontoRepository repository;
	@Autowired
	private Datatables datatables;

	@Transactional(readOnly = false)
	public void salvar(PctgDesconto pctgDesconto) {
		
		repository.save(pctgDesconto);
	}

	@Transactional(readOnly = true)
	public Map<String, Object> buscarPctgDescontos(HttpServletRequest request) {
		datatables.setRequest(request);
		datatables.setColunas(DatatablesColunas.PCTGDESCONTOS);
		Page<?> page = datatables.getSearch().isEmpty()
				? repository.findAll(datatables.getPageable())
				: repository.findAllByDesconto(datatables.getSearch(), datatables.getPageable());
		return datatables.getResponse(page);
	}

	@Transactional(readOnly = true)
	public PctgDesconto buscarPorId(Long id) {
		
		return repository.findById(id).get();
	}

	@Transactional(readOnly = false)
	public void remover(Long id) {
		
		repository.deleteById(id);
	}
	
	
}
