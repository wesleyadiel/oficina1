package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CadMercadorias.Mercadoria;
import GerarRelatorioEstoqueMercadoria.MercadoriaEstoqueRelatorioDTO;
import acchpoo.Conexao;

public class MateriaPrimaEstoqueDAO {
	public int salvar(Mercadoria mercadoria, double quantidade) throws SQLException {
		Conexao connection = new Conexao();
				
		int result = connection.executeSQL("INSERT INTO estoquemp(idmercadoria, quantidade) VALUES(" + mercadoria.getIdMercadoria() + ", " + quantidade + ")"
				+ "ON CONFLICT (idmercadoria) DO UPDATE SET quantidade = estoquemp.quantidade + ("+quantidade+");");
		
		return result;
	}
	
	public List<MercadoriaEstoqueRelatorioDTO> buscarRelatorioEstoqueMateriaPrima() throws SQLException {
		Conexao conn = new Conexao();
		List<MercadoriaEstoqueRelatorioDTO> rels = new ArrayList<MercadoriaEstoqueRelatorioDTO>();
		
		ResultSet rs = conn.executeBusca("SELECT emp.idmercadoria, m.descricao, COALESCE(emp.quantidade, 0.00) AS quantidade FROM estoquemp emp JOIN mercadoria m ON(m.id = emp.idmercadoria)");
		
		while(rs.next()) {
			MercadoriaEstoqueRelatorioDTO e = new MercadoriaEstoqueRelatorioDTO();
			e.setDescricao(rs.getString("descricao"));
			e.setIdMercadoria(rs.getInt("idmercadoria"));
			e.setQuantidade(rs.getDouble("quantidade"));
			rels.add(e);
		}
		
		return rels;
	}
}
