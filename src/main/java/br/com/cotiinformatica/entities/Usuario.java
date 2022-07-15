package br.com.cotiinformatica.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter 
@Getter
@NoArgsConstructor 
@AllArgsConstructor
public class Usuario {

	/* ctrl+o mostra implicitamente os metodos getters and setters criados */
	private Integer idUsuario;
	private String nome;
	private String email;
	private String senha;
	
	//Associoacao (UM Usuario TEM MUITOS Compromissos [tem uma lista de compromissos])
	private List<Compromisso> compromissos;
	
}
  