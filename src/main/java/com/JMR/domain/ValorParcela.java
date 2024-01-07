package com.JMR.domain;

import jakarta.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "valorParcelas", indexes = {@Index(name = "idx_valorParcela_valor_da_parcela", columnList = "valor_da_parcela")})
public class ValorParcela extends AbstractEntity<Long> {
	
	@Column(name = "valor_da_parcela", unique = true, nullable = false, length = 12)

	private String valor;

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}



}
