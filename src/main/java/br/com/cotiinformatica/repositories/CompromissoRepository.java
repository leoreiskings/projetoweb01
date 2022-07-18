package br.com.cotiinformatica.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.cotiinformatica.entities.Compromisso;
import br.com.cotiinformatica.factories.ConnectionFactory;

public class CompromissoRepository {

	public void inserir(Compromisso compromisso) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		// executar uma query SQL para gravar
		// o compromisso no banco de dados
		PreparedStatement statement = connection.prepareStatement(
				"insert into compromisso(nome, data, hora,descricao, prioridade, idusuario)values(?, ?, ?, ?, ?, ?)");

		statement.setString(1, compromisso.getNome());
		statement.setString(2, new SimpleDateFormat("yyyy-MM-dd").format(compromisso.getData()));
		statement.setString(3, compromisso.getHora());
		statement.setString(4, compromisso.getDescricao());
		statement.setInt(5, compromisso.getPrioridade());
		statement.setInt(6, compromisso.getUsuario().getIdUsuario());

		statement.execute();
		// fechando a conexão
		connection.close();
	}

	public void atualizar(Compromisso compromisso) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection
				.prepareStatement("update compromisso set nome=?, data=?, hora=?, descricao=?, prioridade=? "
						+ "where idcompromisso=? and idusuario=?");

		statement.setString(1, compromisso.getNome());
		statement.setString(2, new SimpleDateFormat("yyyy-MM-dd").format(compromisso.getData()));
		statement.setString(3, compromisso.getHora());
		statement.setString(4, compromisso.getDescricao());
		statement.setInt(5, compromisso.getPrioridade());
		statement.setInt(6, compromisso.getIdCompromisso());
		statement.setInt(7, compromisso.getUsuario().getIdUsuario());

		statement.execute();
		connection.close();
	}

	// método para excluir um compromisso no banco de dados
	public void excluir(Compromisso compromisso) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection
				.prepareStatement("delete from compromisso	where idcompromisso=? and idusuario=?");

		statement.setInt(1, compromisso.getIdCompromisso());
		statement.setInt(2, compromisso.getUsuario().getIdUsuario());
		statement.execute(); // comando de execução de uma query mas sem dar retorno.
		connection.close();
	}

	// método para consultar todos os compromissos cadastrados no banco de dados
	public List<Compromisso> obterTodos(Integer idUsuario) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection
				.prepareStatement("select * from compromisso where idusuario=? order by data desc, hora desc");

		statement.setInt(1, idUsuario);

		ResultSet resultSet = statement.executeQuery(); // executa a query com um resultado de retorno. sempre
														// armazenando em um ResultSet

		List<Compromisso> lista = new ArrayList<Compromisso>();

		while (resultSet.next()) {
			
			Compromisso compromisso = new Compromisso();

			compromisso.setIdCompromisso(resultSet.getInt("idcompromisso"));
			compromisso.setNome(resultSet.getString("nome"));
			compromisso.setData(new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("data")));
			compromisso.setHora(resultSet.getString("hora"));
			compromisso.setDescricao(resultSet.getString("descricao"));
			compromisso.setPrioridade(resultSet.getInt("prioridade"));

			lista.add(compromisso); // adicionar na lista
		}

		connection.close();
		return lista;

	}

	public List<Compromisso> obterTodos(Integer idUsuario, Date dataMin, Date dataMax) throws Exception {
			
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement("select * from compromisso "
				+ "where idusuario=? and data between ? and ? order by data desc, hora desc ");
		statement.setInt(1, idUsuario);
		statement.setString(2, new SimpleDateFormat("yyyy-MM-dd").format(dataMin)); // Data no java sempre será convertida para SimpleDateFormat
		statement.setString(3, new SimpleDateFormat("yyyy-MM-dd").format(dataMax));
		
		ResultSet resultSet = statement.executeQuery();
		
		List<Compromisso> lista = new ArrayList<Compromisso>();
		
		while (resultSet.next()) {
			
			Compromisso compromisso = new Compromisso();
		
			compromisso.setIdCompromisso(resultSet.getInt("idcompromisso"));
			compromisso.setNome(resultSet.getString("nome"));
			compromisso.setData(new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("data")));
			compromisso.setHora(resultSet.getString("hora"));
			compromisso.setDescricao(resultSet.getString("descricao"));
			compromisso.setPrioridade(resultSet.getInt("prioridade"));
			
			lista.add(compromisso); // adicionar na lista
		}
		
		connection.close();		
		return lista;
		
		}

	public Compromisso obterPorId(Integer idCompromisso, Integer idUsuario) throws Exception{
		
		//abrindo conexão com o banco de dados
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement("select * from compromisso where idcompromisso = ? and idusuario = ?");
		
		statement.setInt(1, idCompromisso);		
		statement.setInt(2, idUsuario);		
		ResultSet resultSet = statement.executeQuery();
		
		Compromisso compromisso = null; // criando um objeto nulo que será populado pelo resultado da query e returned para a pagina
		
		if(resultSet.next()) {
			
			compromisso = new Compromisso();
			
			compromisso.setIdCompromisso(resultSet.getInt("idcompromisso"));
			compromisso.setNome(resultSet.getString("nome"));
			compromisso.setData(new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("data")));
			compromisso.setHora(resultSet.getString("hora"));
			compromisso.setDescricao(resultSet.getString("descricao"));
			compromisso.setPrioridade(resultSet.getInt("prioridade"));			
		}
		
		connection.close(); //fechando a conexão		
		return compromisso; 
	} 
	
	
	
	
	
	
	
}
