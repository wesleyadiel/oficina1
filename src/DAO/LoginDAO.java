package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import acchpoo.Conexao;

public class LoginDAO {
	
	public int validarLogin(String usuario, String senha) throws SQLException {
		Conexao connection = new Conexao();
				
		ResultSet rs = connection.executeBusca("SELECT id FROM usuario WHERE usuario = '"+usuario+"' AND senha = '"+senha+"'");
		
		if(rs.next()) {
			return rs.getInt("id");
		}
		
		return 0;
	}
}
