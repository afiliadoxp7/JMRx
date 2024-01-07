package com.JMR.repositoryPro;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.JMR.domain.Plataforma;
import com.JMR.domainPro.Parceiro;

public interface ParceiroRepository extends JpaRepository<Parceiro, Long>{
	
	List<Parceiro> findByNomeContainingIgnoreCase(String nomeParceiro, Sort sort);
	
	//Funções para o SELECT no salvar Informação
		Page<Parceiro> findByPlataformaIsNull(List<Plataforma> plataformas, Pageable pageable);
}
