package com.JMR.repositoryPro;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.JMR.domain.Categoria;
import com.JMR.domain.Formato;
import com.JMR.domain.Fornecedor;
import com.JMR.domain.PctgDesconto;
import com.JMR.domain.Porclik;
import com.JMR.domain.QtdParcela;
import com.JMR.domain.ValorParcela;
import com.JMR.domain.ValorProduto;
import com.JMR.domainPro.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long>{
	
	List<Curso> findByNomeContainingIgnoreCase(String nomeCurso, Sort sort);
	
	//Funções para o SELECT no salvar Informação
	Page<Curso> findByCategoriaIsNull(List<Categoria> categorias, Pageable pageable);
	
	Page<Curso> findByFormatoIsNull(List<Formato> formatos, Pageable pageable);
	
	Page<Curso> findByFornecedorIsNull(List<Fornecedor> fornecedores, Pageable pageable);
	
	Page<Curso> findByPorclikIsNull(List<Porclik> porclik, Pageable pageable);
	
	//Funções para o SELECT no salvar Valores
	Page<Curso> findByValorProdutoIsNull(List<ValorProduto> valorProdutos, Pageable pageable);
	
	Page<Curso> findByQtdParcelaIsNull(List<QtdParcela> qtdParcela, Pageable pageable);
	
	Page<Curso> findByValorParcelaIsNull(List<ValorParcela> valorParcelas, Pageable pageable);
	
	Page<Curso> findByPctgDescontoIsNull(List<PctgDesconto> pctgDesconto, Pageable pageable);
}
