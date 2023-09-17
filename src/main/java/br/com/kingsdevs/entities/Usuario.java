package br.com.kingsdevs.entities;

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
	
	private Integer idUsuario;
	private String nome;
	private String email;
	private String senha;
	
	//Um Usuario pode ter N compromissos - representamos por uma lista de compromissos
	private List<Compromisso> compromissos;
	
}
