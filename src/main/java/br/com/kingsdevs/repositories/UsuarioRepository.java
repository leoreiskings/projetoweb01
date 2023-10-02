package br.com.kingsdevs.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.kingsdevs.entities.Usuario;
import br.com.kingsdevs.factories.ConnectionFactory;

public class UsuarioRepository {

	// método para cadastrar um usuário no banco de dados
	public void inserir(Usuario usuario) throws Exception {

		// abrindo conexão com o banco de dados
		Connection connection = ConnectionFactory.getConnection();

		// executando um comando SQL no banco de dados
		PreparedStatement statement = connection
				.prepareStatement("insert into usuario(nome, email, senha) values(?, ?, md5(?))");

		statement.setString(1, usuario.getNome());
		statement.setString(2, usuario.getEmail());
		statement.setString(3, usuario.getSenha());

		statement.execute();

		// fechando conexão
		connection.close();

	}

	// método para cadastrar um usuário no banco de dados
		public void alterarSenha(Integer idUsuario, String novaSenha) throws Exception {

			// abrindo conexão com o banco de dados
			Connection connection = ConnectionFactory.getConnection();

			// executando um comando SQL no banco de dados
			PreparedStatement statement = connection
					.prepareStatement("update usuario set senha = md5(?) where idusuario = ?");

			statement.setString(1, novaSenha);
			statement.setInt(2, idUsuario);
 
			statement.execute();

			// fechando conexão
			connection.close();

		}
	
	
	
	
	// método para consultar 1 usuário no banco de dados através do email
	public Usuario obterPorEmail(String email) throws Exception {

		// abrindo conexão com o banco de dados
		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement("select * from usuario where email = ?");

		statement.setString(1, email);

		ResultSet resultSet = statement.executeQuery();

		Usuario usuario = null;

		if (resultSet.next()) { // verificando se algum registro foi encontrado

			usuario = new Usuario();

			usuario.setIdUsuario(resultSet.getInt("idusuario"));
			usuario.setNome(resultSet.getString("nome"));
			usuario.setEmail(resultSet.getString("email"));
			usuario.setSenha(resultSet.getString("senha"));

		}

		// fechando conexão
		connection.close();

		return usuario;
	}

	public Usuario obterPorEmailESenha(String email, String senha) throws Exception {
		// abrindo conexão com o banco de dados
		Connection connection = ConnectionFactory.getConnection();
	
		// executando o comando SQL (query)
		PreparedStatement statement = connection.prepareStatement("select * from usuario where email=? and senha=md5(?)");

		statement.setString(1, email);
		statement.setString(2, senha);
		
		ResultSet resultSet = statement.executeQuery();
		
		Usuario usuario = null; // vazio
		
		// verificando se algum registro foi encontrado
		// caso seja true, carrega os dados do usuario no objeto "usuario"
		if (resultSet.next()) {
			
			usuario = new Usuario();
			
			usuario.setIdUsuario(resultSet.getInt("idusuario"));
			usuario.setNome(resultSet.getString("nome"));
			usuario.setEmail(resultSet.getString("email"));
			usuario.setSenha(resultSet.getString("senha"));
		}
		
		// fechando a conexão
		connection.close();
		
		return usuario;
	}

}
