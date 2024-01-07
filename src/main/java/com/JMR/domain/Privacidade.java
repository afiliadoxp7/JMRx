package com.JMR.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "privacidades", indexes = {@Index(name = "idx_privacidade_privacidade", columnList = "privacidade")})
public class Privacidade extends AbstractEntity<Long> {

	@Column(name = "privacidade", unique = true, nullable = false, length = 50)
	private String titulo;

	@Column(name = "descricao", columnDefinition = "TEXT")
	private String descricao;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
