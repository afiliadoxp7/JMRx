package com.JMR.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.JMR.domain.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
	
	@Query("select e from Fornecedor e where e.nome like :search%")
	Page<Fornecedor> findAllByTitulo(String search, Pageable pageable);

}
