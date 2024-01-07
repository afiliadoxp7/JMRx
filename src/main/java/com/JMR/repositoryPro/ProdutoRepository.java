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
import com.JMR.domainPro.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	List<Produto> findByNomeContainingIgnoreCase(String nomeProduto, Sort sort);
	
	//Funções para o SELECT no salvar Informação
	Page<Produto> findByCategoriaIsNull(List<Categoria> categorias, Pageable pageable);
	
	Page<Produto> findByFormatoIsNull(List<Formato> formatos, Pageable pageable);
	
	Page<Produto> findByFornecedorIsNull(List<Fornecedor> fornecedores, Pageable pageable);
	
	Page<Produto> findByPorclikIsNull(List<Porclik> porclik, Pageable pageable);
	
	//Funções para o SELECT no salvar Valores
	Page<Produto> findByValorProdutoIsNull(List<ValorProduto> valorProdutos, Pageable pageable);
	
	Page<Produto> findByQtdParcelaIsNull(List<QtdParcela> qtdParcela, Pageable pageable);
	
	Page<Produto> findByValorParcelaIsNull(List<ValorParcela> valorParcelas, Pageable pageable);
	
	Page<Produto> findByPctgDescontoIsNull(List<PctgDesconto> pctgDesconto, Pageable pageable);
	

	
}
