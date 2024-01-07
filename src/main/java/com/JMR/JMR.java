package com.JMR;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JMR {

	public static void main(String[] args) {
		Tabelas recursos = new Tabelas();
	
		SpringApplication.run(JMR.class, args);
		
		System.out.println(recursos.runProgeto());

	}
}
