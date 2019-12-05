package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CadastroCliente.ClienteDTO;
import CadastroUsuario.Usuario;
import acchpoo.Conexao;

public class UsuarioDAO {
	public int salvar(String usuario, String senha, String tipo) throws SQLException {
		Conexao connection = new Conexao();
				
		int result = connection.executeSQL("INSERT INTO usuario(usuario, senha, tipo) VALUES('"+usuario+"', '"+senha+"', '"+tipo+"')");
		
		return result;
	}
	
	public Usuario get(int idUsuario) throws SQLException {
		Conexao connection = new Conexao();
    	Usuario usuario = new Usuario();
				
		ResultSet rs = connection.executeBusca("SELECT * FROM usuario WHERE id = " + idUsuario);
		
		if (rs.next()) {
        	usuario.setIdUsuario(rs.getInt("id"));
        	usuario.setUsuario(rs.getString("usuario"));
        	usuario.setSenha(rs.getString("senha"));
        	usuario.setTipo(rs.getString("tipo"));
        }
		
		return usuario;
	}
	
	public List<Usuario> get() throws SQLException {
		Conexao connection = new Conexao();
		List<Usuario> usuarios = new ArrayList<Usuario>();
		ResultSet rs = connection.executeBusca("SELECT * FROM usuario");
		
		while (rs.next()) {
	    	Usuario usuario = new Usuario();
	    	
        	usuario.setIdUsuario(rs.getInt("id"));
        	usuario.setUsuario(rs.getString("usuario"));
        	usuario.setSenha(rs.getString("senha"));
        	usuario.setTipo(rs.getString("tipo"));
        	
        	usuarios.add(usuario);
        }
		
		return usuarios;
	}
}
