package com.JMR.domain;

import jakarta.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "fornecedores", indexes = {@Index(name = "idx_fornecedor_fornecedores", columnList = "fornecedores")})
public class Fornecedor extends AbstractEntity<Long> {
	
	@Column(name = "fornecedores", unique = true, nullable = false,length = 50)
	private String nome;
	
	@Column(name = "link", unique = true, nullable = false)
	private String link;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	

}
