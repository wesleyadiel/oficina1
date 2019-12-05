package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CadMercadorias.Mercadoria;
import CadMercadorias.MercadoriaDTO;
import CadastroUsuario.Usuario;
import PesquisaMercadoria.PesquisaMercadoriaDTO;
import acchpoo.Conexao;

public class MercadoriaDAO {
	
	public MercadoriaDTO getDTO(int idMercadoria) throws SQLException {
		Conexao connection = new Conexao();
		MercadoriaDTO mercadoria = new MercadoriaDTO();
				
		ResultSet rs = connection.executeBusca("SELECT m.*, COALESCE(em.quantidade, 0.000) AS quantidade, g.descricao AS grupo, sg.descricao AS subgrupo FROM mercadoria m LEFT JOIN estoquemercadoria em ON (em.idmercadoria = m.id) LEFT JOIN grupo g ON (g.id = m.idgrupo) LEFT JOIN subgrupo sg ON(sg.id = m.idsubgrupo) WHERE m.id = " + idMercadoria);
		
		if (rs.next()) {
			mercadoria.setIdMercadoria(rs.getInt("id"));
			mercadoria.setDescricao(rs.getString("descricao"));
			mercadoria.setIdTipo(rs.getInt("idtipo"));
			mercadoria.setGrupo(rs.getString("grupo"));
			mercadoria.setIdGrupo(rs.getInt("idgrupo"));
			mercadoria.setSubgrupo(rs.getString("subgrupo"));
			mercadoria.setIdSubgrupo(rs.getInt("idsubgrupo"));
			mercadoria.setMarca(rs.getString("marca"));
			mercadoria.setPreco(rs.getDouble("preco"));
			mercadoria.setCor(rs.getString("cor"));
			mercadoria.setTamanho(rs.getString("tamanho"));
			mercadoria.setQuantidade(rs.getDouble("quantidade"));
        }
		
		return mercadoria;
	}
	
	public List<MercadoriaDTO> getDTO() throws SQLException {
		Conexao connection = new Conexao();
		List<MercadoriaDTO> mercadorias = new ArrayList<MercadoriaDTO>();
				
		ResultSet rs = connection.executeBusca("SELECT m.*, (CASE WHEN m.idtipo = 0 THEN 'Nenhum' WHEN m.idtipo = 1 THEN 'Mercadoria' WHEN m.idtipo = 2 THEN 'Materia Prima' END) AS tipo, COALESCE(em.quantidade, 0.000) AS quantidade, COALESCE(g.descricao, 'Nenhum') AS grupo, COALESCE(sg.descricao, 'Nenhum') AS subgrupo FROM mercadoria m LEFT JOIN estoquemercadoria em ON (em.idmercadoria = m.id) LEFT JOIN grupo g ON (g.id = m.idgrupo) LEFT JOIN subgrupo sg ON(sg.id = m.idsubgrupo)");
		
		while (rs.next()) {
			MercadoriaDTO mercadoria = new MercadoriaDTO();
			
			mercadoria.setIdMercadoria(rs.getInt("id"));
			mercadoria.setDescricao(rs.getString("descricao"));
			mercadoria.setTipo(rs.getString("tipo"));
			mercadoria.setIdTipo(rs.getInt("idtipo"));
			mercadoria.setGrupo(rs.getString("grupo"));
			mercadoria.setIdGrupo(rs.getInt("idgrupo"));
			mercadoria.setSubgrupo(rs.getString("subgrupo"));
			mercadoria.setIdSubgrupo(rs.getInt("idsubgrupo"));
			mercadoria.setMarca(rs.getString("marca"));
			mercadoria.setPreco(rs.getDouble("preco"));
			mercadoria.setCor(rs.getString("cor"));
			mercadoria.setTamanho(rs.getString("tamanho"));
			mercadoria.setQuantidade(rs.getDouble("quantidade"));
			
			mercadorias.add(mercadoria);
        }
		
		return mercadorias;
	}
	
	public MercadoriaDTO getDTO(String descricao) throws SQLException {
		Conexao connection = new Conexao();
		MercadoriaDTO mercadoria = new MercadoriaDTO();
				
		ResultSet rs = connection.executeBusca("SELECT m.*, COALESCE(em.quantidade, 0.000) AS quantidade, g.descricao AS grupo, sg.descricao AS subgrupo FROM mercadoria m LEFT JOIN estoquemercadoria em ON (em.idmercadoria = m.id) LEFT JOIN grupo g ON (g.id = m.idgrupo) LEFT JOIN subgrupo sg ON(sg.id = m.idsubgrupo) WHERE m.descricao ~* '" + descricao + "'");
		
		if (rs.next()) {
			mercadoria.setIdMercadoria(rs.getInt("id"));
			mercadoria.setDescricao(rs.getString("descricao"));
			mercadoria.setTipo(rs.getString("tipo"));
			mercadoria.setGrupo(rs.getString("grupo"));
			mercadoria.setSubgrupo(rs.getString("subgrupo"));
			mercadoria.setMarca(rs.getString("marca"));
			mercadoria.setPreco(rs.getDouble("preco"));
			mercadoria.setCor(rs.getString("cor"));
			mercadoria.setTamanho(rs.getString("tamanho"));
			mercadoria.setQuantidade(rs.getDouble("quantidade"));
        }
		
		return mercadoria;
	}
	
	public Mercadoria get(String descricaoMercadoria) throws SQLException {
		Conexao connection = new Conexao();
    	Mercadoria mercadoria = new Mercadoria();
				
		ResultSet rs = connection.executeBusca("SELECT m.* FROM mercadoria m WHERE m.descricao ~* '" + descricaoMercadoria + "'");
		
		if (rs.next()) {
			mercadoria.setIdMercadoria(rs.getInt("id"));
			mercadoria.setDescricao(rs.getString("descricao"));
			mercadoria.setIdTipo(rs.getInt("idtipo"));
			mercadoria.setIdGrupo(rs.getInt("idgrupo"));
			mercadoria.setIdSubgrupo(rs.getInt("idsubgrupo"));
			mercadoria.setMarca(rs.getString("marca"));
			mercadoria.setPreco(rs.getDouble("preco"));
			mercadoria.setIdGrupo(rs.getInt("cor"));
			mercadoria.setCor(rs.getString("tamanho"));
        }
		
		return mercadoria;
	}
	
	public Mercadoria get(int idMercadoria) throws SQLException {
		Conexao connection = new Conexao();
    	Mercadoria mercadoria = null;
				
		ResultSet rs = connection.executeBusca("SELECT m.id, m.descricao, COALESCE(m.idtipo, 0) AS idtipo, COALESCE(m.idgrupo, 0) AS idgrupo, COALESCE(m.idsubgrupo, 0) AS idsubgrupo, m.marca, m.cor, m.tamanho, COALESCE(m.preco, 0.00) AS preco FROM mercadoria m WHERE m.id = " + idMercadoria);
		
		if (rs.next()) {
			mercadoria = new Mercadoria();
			mercadoria.setIdMercadoria(rs.getInt("id"));
			mercadoria.setDescricao(rs.getString("descricao"));
			mercadoria.setIdTipo(rs.getInt("idtipo"));
			mercadoria.setIdGrupo(rs.getInt("idgrupo"));
			mercadoria.setIdSubgrupo(rs.getInt("idsubgrupo"));
			mercadoria.setMarca(rs.getString("marca"));
			mercadoria.setPreco(rs.getDouble("preco"));
			mercadoria.setCor(rs.getString("cor"));
			mercadoria.setTamanho(rs.getString("tamanho"));
        }
		
		return mercadoria;
	}
	
	public int salvar(Mercadoria mercadoria) {
		Conexao connection = new Conexao();
		
		if(mercadoria.getIdMercadoria() == 0) {
			int rs = connection.executeSQL("INSERT INTO mercadoria(descricao, marca, cor, tamanho, preco, idtipo, idgrupo, idsubgrupo) VALUES('"
					+ mercadoria.getDescricao()+"', '"
							+ mercadoria.getMarca()+"','"
									+mercadoria.getCor()+ "','"
											+ mercadoria.getTamanho()+"', "
													+ mercadoria.getPreco()+", "
															+ mercadoria.getIdTipo()+", "
																	+ mercadoria.getIdGrupo()+", "
																			+ mercadoria.getIdSubgrupo() +");");
			
			
			return rs;
		} else {
			int rs = connection.executeSQL("UPDATE mercadoria SET "
					+"descricao = '"+ mercadoria.getDescricao()+"', "
							+"marca = '"+ mercadoria.getMarca()+"', "
									+"cor = '"+mercadoria.getCor()+ "', "
											+"tamanho = '"+ mercadoria.getTamanho()+"', "
													+"preco = "+ mercadoria.getPreco()+", "
															+"idtipo = "+ mercadoria.getIdTipo()+", "
																	+"idgrupo = "+ mercadoria.getIdGrupo()+", "
																		+"idsubgrupo = "+ mercadoria.getIdSubgrupo()+ " "
																			+ "WHERE id = " + mercadoria.getIdMercadoria());
			
			
			return rs;
		}
	}

	public void excluir(Integer idMercadoria) {
		Conexao conn = new Conexao();
		conn.executeSQL("DELETE FROM mercadoria WHERE id = "+ idMercadoria);
	}
	
	public List<PesquisaMercadoriaDTO> pesquisaMercadoria(String descricao) throws SQLException {
		Conexao conn = new Conexao();
		List<PesquisaMercadoriaDTO> mercadorias = new ArrayList<PesquisaMercadoriaDTO>();
		String where = descricao.isEmpty() ? "" : " WHERE m.descricao ~* '"+ descricao +"'";
		ResultSet rs = conn.executeBusca("SELECT m.id, m.descricao, m.preco, em.quantidade FROM mercadoria m LEFT JOIN estoquemercadoria em ON (em.idmercadoria = m.id) "+ where +" ORDER BY m.descricao");
	
		while(rs.next()) {
			PesquisaMercadoriaDTO p = new PesquisaMercadoriaDTO();
			
			p.setIdMercadoria(rs.getInt("Id"));
			p.setDescricao(rs.getString("descricao"));
			p.setQtdEstoque(rs.getDouble("quantidade"));
			p.setPreco(rs.getDouble("preco"));
		
			mercadorias.add(p);
		}
		
		return mercadorias;
	}

}
