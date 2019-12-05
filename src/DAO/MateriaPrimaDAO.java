package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CadastrarEstoqueMP.MateriaPrima;
import CadastrarEstoqueMP.MateriaPrimaDTO;
import acchpoo.Conexao;

public class MateriaPrimaDAO {
	
	public List<MateriaPrimaDTO> buscarByIdMercadoria(int idMercadoria) throws SQLException {
		Conexao conn = new Conexao();
		List<MateriaPrimaDTO> materias = new ArrayList<MateriaPrimaDTO>();
		
		ResultSet rs = conn.executeBusca("SELECT mp.idmercadoriamateriaprima, m.descricao, mp.quantidade FROM materiaprima mp JOIN mercadoria m ON (m.id = mp.idmercadoriamateriaprima) WHERE mp.idmercadoria = "+idMercadoria+" ORDER BY mp.idmercadoriamateriaprima");
		
		while(rs.next()) {
			MateriaPrimaDTO materia = new MateriaPrimaDTO();
			
			materia.setCodigo(rs.getInt("idmercadoriamateriaprima"));
			materia.setDescricao(rs.getString("descricao"));
			materia.setQuantidade(rs.getDouble("quantidade"));
			
			materias.add(materia);
		}
		
		return materias;
	}
	
	public int salvar(MateriaPrima mp) throws SQLException {
		Conexao conn = new Conexao();
		List<MateriaPrimaDTO> materias = new ArrayList<MateriaPrimaDTO>();
		
		return conn.executeSQL("INSERT INTO materiaprima(idmercadoria, idmercadoriamateriaprima, quantidade) VALUES("
				+mp.getIdMercadoria()+", "+mp.getIdMercadoriaMateriaPrima()+", "+mp.getQuantidade()+ ") "
						+ "ON CONFLICT (idmercadoria, idmercadoriamateriaprima) DO UPDATE SET quantidade = "+ mp.getQuantidade());
		
	}
}
