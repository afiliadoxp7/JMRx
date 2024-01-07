package com.JMR.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.JMR.domain.Formato;


public interface FormatoRepository extends JpaRepository<Formato, Long>{

	@Query("select e from Formato e where e.titulo like :search%")
	Page<Formato> findAllByTitulo(String search, Pageable pageable);

	
}
