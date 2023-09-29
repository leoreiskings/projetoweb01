package br.com.kingsdevs.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.kingsdevs.entities.Compromisso;
import br.com.kingsdevs.factories.ConnectionFactory;
//A camada repository traz os metodos que estão mais próximos do banco de dados
// por isso trabalham com SQL diretamente em seu codigo
//como regra devemos ter 1 classe Repository para cada entidade.
//ela terá todas as acoes dentro dela (inserir, deletar, alterar, consulta)
 
public class CompromissoRepository {

	// método para inserir um compromisso no banco de dados
	public void inserir(Compromisso compromisso) throws Exception {

		// abrindo conexão com o banco de dados
		Connection connection = ConnectionFactory.getConnection();

		// executar uma query SQL para gravar
		// o compromisso no banco de dados
		PreparedStatement statement = connection
				.prepareStatement("insert into compromisso(nome, data, hora, descricao, "
						+ "prioridade, idusuario) "
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

	// método para atualizar um compromisso no banco de dados
	public void atualizar(Compromisso compromisso) throws Exception {

		//abrindo conexao com o BD
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement statement = connection.
		prepareStatement("update compromisso set nome=?, data=?, hora=?, descricao=?, prioridade=? "
						+ "where idcompromisso=? and idusuario=?");
	
		//setando os valores que comporão a query
		statement.setString(1, compromisso.getNome());
		statement.setString(2, new SimpleDateFormat("yyyy-MM-dd").format(compromisso.getData()));
		statement.setString(3, compromisso.getHora());
		statement.setString(4, compromisso.getDescricao());
		statement.setInt(5, compromisso.getPrioridade());
		statement.setInt(6, compromisso.getIdCompromisso());
		statement.setInt(7, compromisso.getUsuario().getIdUsuario());
		
		//executando a query no BD
		statement.execute();
		
		//fechando conexao com o BD
		connection.close();
	
	}

	// método para excluir um compromisso no banco de dados
	public void excluir(Compromisso compromisso) throws Exception {
		
		//abrindo conexao com o BD
		Connection connection = ConnectionFactory.getConnection();
	
		PreparedStatement statement = connection.prepareStatement("delete from compromisso "
				+ "where idcompromisso=? and idusuario=?");
		statement.setInt(1, compromisso.getIdCompromisso());
		statement.setInt(2, compromisso.getUsuario().getIdUsuario());
		
		statement.execute();
		
		//fechando conexao com o BD
		connection.close();
	}

	// método para consultar todos os compromissos cadastrados no banco de dados
	public List<Compromisso> obterTodos(Integer idUsuario) throws Exception {
		
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement statement = connection
										.prepareStatement("select * from compromisso "
										+ "where idusuario=? order by data desc, hora desc ");
		statement.setInt(1, idUsuario);
		
		ResultSet resultSet = statement.executeQuery(); // executa e lê os dados retornados do BD
		
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

	// método para consultar todos os compromissos cadastrados no banco de dados
	public List<Compromisso> obterTodos(Integer idUsuario, Date dataMin, Date dataMax) throws Exception

	{
		
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement statement = connection
									  .prepareStatement("select * from compromisso "
									  		+ "where idusuario=? and data between ? and ? "
									  		+ "order by data desc, hora desc ");
		
		statement.setInt(1, idUsuario);
		statement.setString(2, new SimpleDateFormat("yyyy-MM-dd").format(dataMin));		
		statement.setString(3, new SimpleDateFormat("yyyy-MM-dd").format(dataMax));
		
		ResultSet resultSet = statement.executeQuery();// executa e lê os dados retornados do BD
		
		//criando lista de compromissos vazia
		List<Compromisso> lista = new ArrayList<Compromisso>();
		
		while (resultSet.next()) {
			
			//lendo compromisso por compromisso retornado do resultset
			//lendo do bd (resultset.getString() ) e setando no objeto (compromisso.setNome())
			Compromisso compromisso = new Compromisso();
			
			//populando os dados dos compromissos um a um. campo a campo
			compromisso.setIdCompromisso(resultSet.getInt("idcompromisso"));
			compromisso.setNome(resultSet.getString("nome"));
			compromisso.setData(new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("data")));
			compromisso.setHora(resultSet.getString("hora"));
			compromisso.setDescricao(resultSet.getString("descricao"));
			compromisso.setPrioridade(resultSet.getInt("prioridade"));
			
			lista.add(compromisso); // adicionar na lista de compromisso
		}
		
	connection.close();
	return lista;
	
	}
	
	public Compromisso obterPorId(Integer idCompromisso, Integer idUsuario) throws Exception {
		
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.
				prepareStatement("select * from compromisso where idcompromisso = ? and idusuario = ? ");
		
		statement.setInt(1, idCompromisso);
		statement.setInt(2, idUsuario);
		
		ResultSet resultSet = statement.executeQuery();
			
		Compromisso compromisso = null;
		
		//se o "if" for true, significa que o resultado do resultset retornou algum registro, 
		//entao ele entra na condicao 
		if (resultSet.next()) {
			
			compromisso = new Compromisso();
			compromisso.setIdCompromisso(resultSet.getInt("idcompromisso"));
			compromisso.setNome(resultSet.getString("nome"));
			compromisso.setData(new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("data")));
			compromisso.setHora(resultSet.getString("hora"));
			compromisso.setDescricao(resultSet.getString("descricao"));
			compromisso.setPrioridade(resultSet.getInt("prioridade"));
			
		}
		
		connection.close();
		
		return compromisso;
	}
	
}
