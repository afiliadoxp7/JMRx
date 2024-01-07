package com.JMR.domain;

import jakarta.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "categorias", indexes = {@Index(name = "idx_categoria_categoria", columnList = "categoria")})
public class Categoria extends AbstractEntity<Long> {
	
	@Column(name = "categoria", unique = true, nullable = false, length = 50)
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
