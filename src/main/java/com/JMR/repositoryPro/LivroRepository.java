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
import com.JMR.domainPro.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{
	
	List<Livro> findByNomeContainingIgnoreCase(String nomeLivro, Sort sort);
	
	//Funções para o SELECT no salvar Informação
	Page<Livro> findByCategoriaIsNull(List<Categoria> categorias, Pageable pageable);
	
	Page<Livro> findByFormatoIsNull(List<Formato> formatos, Pageable pageable);
	
	Page<Livro> findByFornecedorIsNull(List<Fornecedor> fornecedores, Pageable pageable);
	
	Page<Livro> findByPorclikIsNull(List<Porclik> porclik, Pageable pageable);
	
	//Funções para o SELECT no salvar Valores
	Page<Livro> findByValorProdutoIsNull(List<ValorProduto> valorProdutos, Pageable pageable);
	
	Page<Livro> findByQtdParcelaIsNull(List<QtdParcela> qtdParcela, Pageable pageable);
	
	Page<Livro> findByValorParcelaIsNull(List<ValorParcela> valorParcelas, Pageable pageable);
	
	Page<Livro> findByPctgDescontoIsNull(List<PctgDesconto> pctgDesconto, Pageable pageable);
}
