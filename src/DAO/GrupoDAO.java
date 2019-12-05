package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CadastrarGrupo.Grupo;
import CadastroCliente.ClienteDTO;
import acchpoo.Conexao;

public class GrupoDAO {
	
	public List<Grupo> get() throws SQLException {
		Conexao conn = new Conexao();
		List<Grupo> grupos = new ArrayList<Grupo>();
		
		ResultSet rs = conn.executeBusca("SELECT 0 AS id, 'Nenhum' AS descricao UNION ALL SELECT id, descricao FROM grupo ORDER BY id");
		
        while (rs.next()) {
        	Grupo grupo = new Grupo();
        	
        	grupo.setIdGrupo(rs.getInt("id"));
        	grupo.setDescricao(rs.getString("descricao"));
        	grupos.add(grupo);
        }
        
		return grupos;
	}
	
	public Grupo get(int idGrupo) throws SQLException {
		if(idGrupo == 0) {
			Grupo gEmpty = new Grupo();
			gEmpty.setIdGrupo(0);
			gEmpty.setDescricao("Nenhum");
			
			return gEmpty;
		}
		
		Conexao conn = new Conexao();
    	Grupo grupo = new Grupo();
		
		ResultSet rs = conn.executeBusca("SELECT id, descricao FROM grupo WHERE id = "+ idGrupo +" ORDER BY id");
		
        if (rs.next()) {
        	grupo.setIdGrupo(rs.getInt("id"));
        	grupo.setDescricao(rs.getString("descricao"));
        }
        
		return grupo;
	}
	
	public int salvar(Grupo grupo) {
		Conexao conn = new Conexao();
		
		if(grupo.getIdGrupo() == 0) {
			return conn.executeSQL("INSERT INTO grupo(descricao) VALUES('"+ grupo.getDescricao() +"');");
		} else {
			return conn.executeSQL("UPDATE grupo SET descricao = "+ grupo.getDescricao() + "WHERE id = " + grupo.getIdGrupo());
		}
	}
}
