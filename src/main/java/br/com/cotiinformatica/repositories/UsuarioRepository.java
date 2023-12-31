package br.com.cotiinformatica.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.factories.ConnectionFactory;

public class UsuarioRepository {

	// m�todo para cadastrar um usu�rio no banco de dados
	public void inserir(Usuario usuario) throws Exception {

		// abrindo conex�o com o banco de dados
		Connection connection = ConnectionFactory.getConnection();

		// executando um comando SQL no banco de dados
		PreparedStatement statement = connection
				.prepareStatement("insert into usuario(nome, email, senha) values(?, ?, md5(?))");

		statement.setString(1, usuario.getNome()); // esta recuperando o nome usuario que veio passado como parametro na
													// chamada do metodo
		statement.setString(2, usuario.getEmail());
		statement.setString(3, usuario.getSenha());
		statement.execute();

		// fechando conex�o
		connection.close();
	}

	public Usuario obterPorEmail(String email) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement("select * from usuario where email = ?");

		statement.setString(1, email);
		ResultSet resultSet = statement.executeQuery();

		Usuario usuario = null;

		if (resultSet.next()) {

			usuario = new Usuario();

			usuario.setIdUsuario(resultSet.getInt("idusuario"));
			usuario.setNome(resultSet.getString("nome"));
			usuario.setEmail(resultSet.getString("email"));
			usuario.setSenha(resultSet.getString("senha"));

		}

		// fechando conex�o
		connection.close();

		return usuario;
	}

	public Usuario obterPorEmailESenha(String email, String senha) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection
				.prepareStatement("select * from usuario where email = ? and senha = md5(?)"); // montando o sql

		statement.setString(1, email);
		statement.setString(2, senha);

		// select * from usuario where senha = md5(123);
		ResultSet resultSet = statement.executeQuery();

		Usuario usuario = null;

		if (resultSet.next()) {

			usuario = new Usuario();

			usuario.setIdUsuario(resultSet.getInt("idusuario"));
			usuario.setNome(resultSet.getString("nome"));
			usuario.setEmail(resultSet.getString("email"));
			usuario.setSenha(resultSet.getString("senha"));
		}

		// fechando a conex�o
		connection.close();

		return usuario;
	}

	// m�todo para atualizar a senha do usu�rio no banco de dados
	public void alterarSenha(Integer idUsuario, String novaSenha) throws Exception {

		// abrindo conex�o com o banco de dados
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement statement = connection.prepareStatement("update usuario set senha = md5(?) where idusuario = ?");
		
		statement.setString(1, novaSenha);
		statement.setInt(2, idUsuario);
		statement.execute();
		
		// fechando a conex�o
		connection.close();

	}

}
