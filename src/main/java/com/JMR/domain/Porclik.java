package com.JMR.domain;

import jakarta.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "porcliks", indexes = {@Index(name = "idx_porclik_porclik", columnList = "porclik")})
public class Porclik extends AbstractEntity<Long>{
	
	@Column(name = "porclik", unique = true, nullable = false, length = 50)
	private String titulo;

			
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}	

}