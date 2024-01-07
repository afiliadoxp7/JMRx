package com.JMR.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.JMR.datatables.Datatables;
import com.JMR.datatables.DatatablesColunas;
import com.JMR.domain.Fornecedor;
import com.JMR.repository.FornecedorRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class FornecedorService {

	@Autowired
	private FornecedorRepository repository;
	@Autowired
	private Datatables datatables;

	@Transactional(readOnly = false)
	public void salvar(Fornecedor fornecedor) {
		
		repository.save(fornecedor);
	}

	@Transactional(readOnly = true)
	public Map<String, Object> buscarFornecedores(HttpServletRequest request) {
		datatables.setRequest(request);
		datatables.setColunas(DatatablesColunas.FORNECEDORES);
		Page<?> page = datatables.getSearch().isEmpty()
				? repository.findAll(datatables.getPageable())
				: repository.findAllByTitulo(datatables.getSearch(), datatables.getPageable());
		return datatables.getResponse(page);
	}

	@Transactional(readOnly = true)
	public Fornecedor buscarPorId(Long id) {
		
		return repository.findById(id).get();
	}

	@Transactional(readOnly = false)
	public void remover(Long id) {
		
		repository.deleteById(id);
	}
	
	
}
