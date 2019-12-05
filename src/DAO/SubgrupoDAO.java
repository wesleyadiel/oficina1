package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CadastrarGrupo.Grupo;
import CadastrarSubGrupo.Subgrupo;
import CadastrarSubGrupo.SubgrupoDTO;
import acchpoo.Conexao;
public class SubgrupoDAO {
	public List<Subgrupo> get() throws SQLException {
		Conexao conn = new Conexao();
		List<Subgrupo> subgrupos = new ArrayList<Subgrupo>();
		
		ResultSet rs = conn.executeBusca("SELECT id, descricao, idgrupo FROM subgrupo ORDER BY id");
		
        while (rs.next()) {
        	Subgrupo subgrupo = new Subgrupo();
        	
        	subgrupo.setIdSubgrupo(rs.getInt("id"));
        	subgrupo.setDescricao(rs.getString("descricao"));
        	subgrupo.setIdGrupo(rs.getInt("idgrupo"));
        	
        	subgrupos.add(subgrupo);
        }
        
		return subgrupos;
	}
	
	public List<SubgrupoDTO> getDTO() throws SQLException {
		Conexao conn = new Conexao();
		List<SubgrupoDTO> subgrupos = new ArrayList<SubgrupoDTO>();
		
		ResultSet rs = conn.executeBusca("SELECT 0 AS id, 'Nenhum' AS descricao, 'Nenhum' AS descricaogrupo UNION ALL SELECT s.id, s.descricao, g.descricao AS descricaogrupo FROM subgrupo s JOIN grupo g ON (s.idgrupo = g.id) ORDER BY id");
		
        while (rs.next()) {
        	SubgrupoDTO subgrupo = new SubgrupoDTO();
        	
        	subgrupo.setIdSubgrupo(rs.getInt("id"));
        	subgrupo.setDescricao(rs.getString("descricao"));
        	subgrupo.setGrupo(rs.getString("descricaogrupo"));
        	
        	subgrupos.add(subgrupo);
        }
        
		return subgrupos;
	}
	
	public SubgrupoDTO getDTO(int idsubgrupo) throws SQLException {
		if(idsubgrupo == 0) {
			SubgrupoDTO sgEmpty = new SubgrupoDTO();
			sgEmpty.setIdSubgrupo(0);
			sgEmpty.setDescricao("Nenhum");
			sgEmpty.setGrupo("Nenhum");
			
			return sgEmpty;
		}
		
		Conexao conn = new Conexao();
    	SubgrupoDTO subgrupo = new SubgrupoDTO();
		
		ResultSet rs = conn.executeBusca("SELECT s.id, s.descricao, g.descricao AS descricaogrupo FROM subgrupo s JOIN grupo g ON (s.idgrupo = g.id) WHERE s.id = "+ idsubgrupo +" ORDER BY id");
		
        if (rs.next()) {
        	subgrupo.setIdSubgrupo(rs.getInt("id"));
        	subgrupo.setDescricao(rs.getString("descricao"));
        	subgrupo.setGrupo(rs.getString("descricaogrupo"));
        }
        
		return subgrupo;
	}
	
	public int salvar(Subgrupo subgrupo) {
		Conexao conn = new Conexao();
		
		if(subgrupo.getIdSubgrupo() == 0) {
			return conn.executeSQL("INSERT INTO subgrupo(descricao, idgrupo) VALUES('"+ subgrupo.getDescricao() +"', "+ subgrupo.getIdGrupo() +");");
		} else {
			return conn.executeSQL("UPDATE subgrupo SET descricao = "+ subgrupo.getDescricao() + ", idgrupo = "+subgrupo.getIdGrupo()+" WHERE id = " + subgrupo.getIdSubgrupo());
		}
	}
}
