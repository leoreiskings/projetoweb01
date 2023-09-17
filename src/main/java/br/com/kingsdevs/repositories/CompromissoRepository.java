package br.com.kingsdevs.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

import br.com.kingsdevs.entities.Compromisso;
import br.com.kingsdevs.factories.ConnectionFactory;

public class CompromissoRepository {

	//método para inserir um compromisso no banco de dados
	public void inserir(Compromisso compromisso) throws Exception {

	//abrindo conexão com o banco de dados
	Connection connection = ConnectionFactory.getConnection();

	//executar uma query SQL para gravar
	//o compromisso no banco de dados
	PreparedStatement statement = connection.prepareStatement("insert into compromisso(nome, data, hora, descricao, prioridade, idusuario) "
					+ "values(?, ?, ?, ?, ?, ?)");
	
	statement.setString(1, compromisso.getNome());
	statement.setString(2, new SimpleDateFormat("yyyy-MM-dd").format(compromisso.getData()));
	statement.setString(3, compromisso.getHora());
	statement.setString(4, compromisso.getDescricao());
	statement.setInt(5, compromisso.getPrioridade());
	statement.setInt(6, compromisso.getUsuario().getIdUsuario());
	
	statement.execute();	
	connection.close();
	
	}	
}
