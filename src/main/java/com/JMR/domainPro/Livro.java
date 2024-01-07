package com.JMR.domainPro;

import com.JMR.domain.AbstractEntity;
import com.JMR.domain.Categoria;
import com.JMR.domain.Formato;
import com.JMR.domain.Fornecedor;
import com.JMR.domain.PctgDesconto;
import com.JMR.domain.Porclik;
import com.JMR.domain.QtdParcela;
import com.JMR.domain.ValorParcela;
import com.JMR.domain.ValorProduto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
/*
* Referencia os BYTES que suporta no @Lob
* 
* columnDefinition="TINYBLOB"
* 
* TINYBLOB : maximum length of 255 bytes BLOB : maximum length of 65,535 bytes
* MEDIUMBLOB : maximum length of 16,777,215 bytes LONGBLOB : maximum length of
* 4,294,967,295 bytes
*/

@SuppressWarnings("serial")
@Entity
@Table(name = "livros")
public class Livro extends AbstractEntity<Long> {

	@Column(name = "nome", nullable = false, length = 2000)
	private String nome;

	@Lob
	@Column(name = "imagem", columnDefinition = "MEDIUMBLOB")
	private byte[] imagem;

	@NotNull(message = "Link deve ser preenchido")
	@Column(name = "link", nullable = false, unique = true, length = 2000)
	private String link;

	@NotNull(message = "Check out deve ser preenchido")
	@Column(name = "checkout", nullable = false, unique = true, length = 2000)
	private String checkout;

	// ------------------- Muitos para Um Informações ------------------------------
	@ManyToOne
	@NotNull(message = "Categoria deve ser selecionada")
	private Categoria categoria;

	@ManyToOne
	@NotNull(message = "Formato deve ser selecionada")
	private Formato formato;

	@ManyToOne
	@NotNull(message = "Fornecedor deve ser selecionada")
	private Fornecedor fornecedor;
	
	@ManyToOne
	@NotNull(message = "Clik deve ser selecionada")
	private Porclik porclik;

	// ------------------- Muitos para Um Valores------------------------------
	@ManyToOne
	@NotNull(message = "ValorProduto deve ser selecionada")
	private ValorProduto valorProduto;

	@ManyToOne
	@NotNull(message = "ValorParcela deve ser selecionada")
	private ValorParcela valorParcela;

	@ManyToOne
	@NotNull(message = "QtdParcela deve ser selecionada")
	private QtdParcela qtdParcela;

	@ManyToOne
	@NotNull(message = "PctgDesconto deve ser selecionada")
	private PctgDesconto pctgDesconto;

	// ------------------- Muitos para Um SQL Get e Set ---------------------

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Formato getFormato() {
		return formato;
	}

	public void setFormato(Formato formato) {
		this.formato = formato;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	// ------------------- Muitos para Um SQL Get e Set ---------------------
	public ValorProduto getValorProduto() {
		return valorProduto;
	}

	public void setValorProduto(ValorProduto valorProduto) {
		this.valorProduto = valorProduto;
	}

	public ValorParcela getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(ValorParcela valorParcela) {
		this.valorParcela = valorParcela;
	}

	public QtdParcela getQtdParcela() {
		return qtdParcela;
	}

	public void setQtdParcela(QtdParcela qtdParcela) {
		this.qtdParcela = qtdParcela;
	}

	public PctgDesconto getPctgDesconto() {
		return pctgDesconto;
	}

	public void setPctgDesconto(PctgDesconto pctgDesconto) {
		this.pctgDesconto = pctgDesconto;
	}

	// ------------------- Produtos Get e Set ------------------------------
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getCheckout() {
		return checkout;
	}

	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}
	
	public Porclik getPorclik() {
		return porclik;
	}

	public void setPorclik(Porclik porclik) {
		this.porclik = porclik;
	}
	

}