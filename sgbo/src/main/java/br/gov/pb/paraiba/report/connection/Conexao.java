package br.gov.pb.paraiba.report.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Conexao {
	private Connection conexao;

	public Conexao() throws ClassNotFoundException, SQLException {
		criaConexao();
	}

	public void criaConexao() throws SQLException, ClassNotFoundException {
		String endereco = "localhost";
		String porta = "3306";
		String banco = "sgbo";
		String usuario = "root";
		String senha = "root";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexao = DriverManager.getConnection("jdbc:mysql://" + endereco
					+ ":" + porta + "/" + banco + "?user=" + usuario
					+ "&password=" + senha);

		} catch (ClassNotFoundException ex) {
			throw ex;
		} catch (SQLException ex) {
			throw ex;
		}

	}

	public void fechaConexao() throws SQLException {
		conexao.close();
		conexao = null;
	}

	public boolean isFechada() {
		try {
			return conexao.isClosed();
		} catch (SQLException ex) {
			return false;
		}
	}

	public Connection getConexao() {
		return conexao;
	}
}
