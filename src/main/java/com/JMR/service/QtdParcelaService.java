package com.JMR.service;

import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.JMR.datatables.Datatables;
import com.JMR.datatables.DatatablesColunas;
import com.JMR.domain.QtdParcela;
import com.JMR.repository.QtdParcelaRepository;

@Service
public class QtdParcelaService {

	@Autowired
	private QtdParcelaRepository repository;
	@Autowired
	private Datatables datatables;

	@Transactional(readOnly = false)
	public void salvar(QtdParcela qtdParcela) {

		repository.save(qtdParcela);
	}

	@Transactional(readOnly = true)
	public Map<String, Object> buscarQtdParcelas(HttpServletRequest request) {
		datatables.setRequest(request);
		datatables.setColunas(DatatablesColunas.QTDPARCELAS);
		Page<?> page = datatables.getSearch().isEmpty() ? repository.findAll(datatables.getPageable())
				: repository.findAllByParcelamento(datatables.getSearch(), datatables.getPageable());
		return datatables.getResponse(page);
	}

	@Transactional(readOnly = true)
	public QtdParcela buscarPorId(Long id) {

		return repository.findById(id).get();
	}

	@Transactional(readOnly = false)
	public void remover(Long id) {

		repository.deleteById(id);
	}
}
