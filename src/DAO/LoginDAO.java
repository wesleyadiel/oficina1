package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import acchpoo.Conexao;

public class LoginDAO {
	
	public int validarLogin(String usuario, String senha) throws SQLException {
		Conexao connection = new Conexao();
				
		ResultSet rs = connection.executeBusca("SELECT id FROM empregado WHERE (cpf ~* '"+usuario+"')");
		
		if(rs.next()) {
			return 1;
		}
		
		return 0;
	}
}
