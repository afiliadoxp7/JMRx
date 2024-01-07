package com.JMR.domain;

import jakarta.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "valorProdutos", indexes = {@Index(name = "idx_valorProduto_valor_do_produto", columnList = "valor_do_produto")})
public class ValorProduto extends AbstractEntity<Long> {
	
	@Column(name = "valor_do_produto", unique = true, nullable = false, length = 12)
	private String valor;

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}



}
