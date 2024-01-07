package com.JMR.domain;

import jakarta.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "qtdParcelas", indexes = {@Index(name = "idx_qtdParcela_qtdParcela", columnList = "qtdParcela")})
public class QtdParcela extends AbstractEntity<Long> {
	
	@Column(name = "qtdParcela", unique = true, nullable = false, length = 2)
	private int parcelamento;
	
	public int getParcelamento() {
		return parcelamento;
	}

	public void setParcelamento(int parcelamento) {
		this.parcelamento = parcelamento;
	}	

}
