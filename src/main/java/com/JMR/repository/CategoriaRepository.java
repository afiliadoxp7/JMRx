package com.JMR.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.JMR.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	@Query("select e from Categoria e where e.titulo like :search%")
	Page<Categoria> findAllByTitulo(String search, Pageable pageable);

}
