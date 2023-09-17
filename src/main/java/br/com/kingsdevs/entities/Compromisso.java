package br.com.kingsdevs.entities;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Compromisso {

	private Integer idCompromisso;
	private String nome;
	private Date data;
	private String hora;
	private String descricao;
	private Integer prioridade;
	
	//Relacionamento (Associação TER-1)
	private Usuario usuario;
	
	
	
}
