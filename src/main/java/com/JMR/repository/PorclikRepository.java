package com.JMR.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.JMR.domain.Porclik;

public interface PorclikRepository extends JpaRepository<Porclik, Long> {

	@Query("select e from Porclik e where e.titulo like :search%")
	Page<Porclik> findAllByTitulo(String search, Pageable pageable);

	
}
