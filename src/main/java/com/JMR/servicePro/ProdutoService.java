package com.JMR.servicePro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.JMR.domainPro.Produto;
import com.JMR.repository.CategoriaRepository;
import com.JMR.repository.FormatoRepository;
import com.JMR.repository.FornecedorRepository;
import com.JMR.repository.PctgDescontoRepository;
import com.JMR.repository.PorclikRepository;
import com.JMR.repository.QtdParcelaRepository;
import com.JMR.repository.ValorParcelaRepository;
import com.JMR.repository.ValorProdutoRepository;
import com.JMR.repositoryPro.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private FormatoRepository formatoRepository;
	@Autowired
	private FornecedorRepository fornecedorRepository;
	@Autowired
	private PorclikRepository porclikRepository;

	@Autowired
	private ValorProdutoRepository valorProdutoRepository;
	@Autowired
	private ValorParcelaRepository valorParcelaRepository;
	@Autowired
	private QtdParcelaRepository qtdParcelaRepository;
	@Autowired
	private PctgDescontoRepository pctgDescontoRepository;

	
	@Transactional(readOnly = false)
	public void salvar(Produto produto) {		
		produtoRepository.save(produto);
	}
	
	@Transactional(readOnly = true)
	public void exibir(Produto produto, Model model) {

		model.addAttribute("listaCategorias", this.categoriaRepository.findAll(Sort.by("titulo")));
		model.addAttribute("listaFormatos", this.formatoRepository.findAll(Sort.by("titulo")));
		model.addAttribute("listaFornecedores", this.fornecedorRepository.findAll(Sort.by("nome")));
		model.addAttribute("listaPorCliks", this.porclikRepository.findAll(Sort.by("titulo")));
		// Valores dos produtos
		model.addAttribute("listaValorProdutos", this.valorProdutoRepository.findAll(Sort.by("valor")));
		model.addAttribute("listaQtdParcelas", this.qtdParcelaRepository.findAll(Sort.by("parcelamento")));
		model.addAttribute("listaValorParcelas", this.valorParcelaRepository.findAll(Sort.by("valor")));
		model.addAttribute("listaPctgDescontos", this.pctgDescontoRepository.findAll(Sort.by("desconto")));
		
	}

	@Transactional(readOnly = true)
	public void editar(Produto produto, Long id, Model model) {
		model.addAttribute("produto", this.produtoRepository.findById(id));
		exibir(produto, model);		
	}

	@Transactional(readOnly = true)
	public byte[] image(Long idprod) {
		Produto produto = this.produtoRepository.getReferenceById(idprod);
		return produto.getImagem();
	}

}
