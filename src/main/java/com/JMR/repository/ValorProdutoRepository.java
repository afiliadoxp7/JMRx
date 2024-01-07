package com.JMR.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.JMR.domain.ValorProduto;

public interface ValorProdutoRepository extends JpaRepository<ValorProduto, Long>{
	
	@Query("select e from ValorProduto e where e.valor like :search%")
	Page<ValorProduto> findAllByValorProduto(String search, Pageable pageable);

	
}
