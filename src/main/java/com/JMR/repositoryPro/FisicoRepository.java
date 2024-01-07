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
import com.JMR.domainPro.Fisico;

public interface FisicoRepository extends JpaRepository<Fisico, Long>{
	
	List<Fisico> findByNomeContainingIgnoreCase(String nomeFisico, Sort sort);
	
	//Funções para o SELECT no salvar Informação
	Page<Fisico> findByCategoriaIsNull(List<Categoria> categorias, Pageable pageable);
	
	Page<Fisico> findByFormatoIsNull(List<Formato> formatos, Pageable pageable);
	
	Page<Fisico> findByFornecedorIsNull(List<Fornecedor> fornecedores, Pageable pageable);
	
	Page<Fisico> findByPorclikIsNull(List<Porclik> porclik, Pageable pageable);
	
	//Funções para o SELECT no salvar Valores
	Page<Fisico> findByValorProdutoIsNull(List<ValorProduto> valorProdutos, Pageable pageable);
	
	Page<Fisico> findByQtdParcelaIsNull(List<QtdParcela> qtdParcela, Pageable pageable);
	
	Page<Fisico> findByValorParcelaIsNull(List<ValorParcela> valorParcelas, Pageable pageable);
	
	Page<Fisico> findByPctgDescontoIsNull(List<PctgDesconto> pctgDesconto, Pageable pageable);
	
}
