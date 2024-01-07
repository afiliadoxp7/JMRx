package com.JMR.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.JMR.domain.PctgDesconto;

public interface PctgDescontoRepository extends JpaRepository<PctgDesconto, Long>{

	@Query("select e from PctgDesconto e where e.desconto like :search%")
	Page<PctgDesconto> findAllByDesconto(String search, Pageable pageable);

	
}
