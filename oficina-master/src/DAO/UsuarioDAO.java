package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import acchpoo.Conexao;

public class UsuarioDAO {
	public int salvar(String usuario, String senha, String tipo) throws SQLException {
		Conexao connection = new Conexao();
				
		int result = connection.executeSQL("INSERT INTO usuario(usuario, senha, tipo) VALUES('"+usuario+"', '"+senha+"', '"+tipo+"')");
		
		return result;
	}
}
