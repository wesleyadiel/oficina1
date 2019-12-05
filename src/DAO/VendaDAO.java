package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import GerarRelatorioEstoqueMercadoria.MercadoriaEstoqueRelatorioDTO;
import GerarRelatorioVendas.MercadoriaVendaRelatorioDTO;
import GerarRelatorioVendas.TotaisDTO;
import PrevisaoDeCompras.PrevisaoCompraRelatorioDTO;
import RealizarVenda.MercadoriaVenda;
import RealizarVenda.MercadoriaVendaDTO;
import acchpoo.Conexao;
import javafx.collections.ObservableList;
import javafx.util.Callback;

public class VendaDAO {
	public void salvar(List<MercadoriaVenda> mercadorias) throws SQLException {
		Conexao conn = new Conexao();
		for (MercadoriaVenda merc : mercadorias) {
			conn.executeSQL("INSERT INTO venda(idmercadoria, idcliente, quantidade, valormercadoria, valorvenda, valordesconto) VALUES("
					+ merc.getIdMercadoria()+", "+ merc.getIdCliente()+", "+merc.getQuantidade()+", "+merc.getValorMercadoria()+", "+merc.getValorVenda()+","+merc.getValorDesconto()+")");
			
			conn = new Conexao();
			ResultSet rs = conn.executeBusca("SELECT * FROM materiaprima WHERE idmercadoria = "+ merc.getIdMercadoria());
			boolean contemMateriaPrima = false;
			while(rs.next()) {
				contemMateriaPrima = true;
				conn.executeSQL("INSERT INTO estoquemp(idmercadoria, quantidade) VALUES("+rs.getDouble("idmercadoriamateriaprima")+", "+rs.getDouble("quantidade")+") ON CONFLICT (idmercadoria) DO UPDATE SET quantidade = estoquemp.quantidade - "+rs.getDouble("quantidade")+" WHERE estoquemp.idmercadoria = "+rs.getDouble("idmercadoriamateriaprima"));
			}
			
			if(!contemMateriaPrima) {
				conn = new Conexao();
				conn.executeSQL("INSERT INTO estoquemercadoria(idmercadoria, quantidade) VALUES("+merc.getIdMercadoria()+", "+merc.getQuantidade()+") ON CONFLICT (idmercadoria) DO UPDATE SET quantidade = estoquemercadoria.quantidade - "+merc.getQuantidade()+" WHERE estoquemercadoria.idmercadoria = "+merc.getIdMercadoria());
			}
		}
		
	}

	public List<MercadoriaVendaRelatorioDTO> buscarListaRelatorio() throws SQLException {
		Conexao conn = new Conexao();
		List<MercadoriaVendaRelatorioDTO> vendas = new ArrayList<MercadoriaVendaRelatorioDTO>();
		
		ResultSet rs = conn.executeBusca("SELECT v.idmercadoria, m.descricao, v.idcliente, c.nomecompleto, SUM(v.quantidade) AS quantidade, v.valormercadoria, v.valorvenda, v.valordesconto, (SUM(v.quantidade) * v.valorvenda) AS valortotal \r\n" + 
											"FROM venda v\r\n" + 
											"JOIN mercadoria m ON(m.id = v.idmercadoria)\r\n" + 
											"LEFT JOIN cliente c ON(c.id = v.idcliente) \r\n" + 
											"GROUP BY v.idmercadoria, m.descricao, v.idcliente, c.nomecompleto, v.valormercadoria, v.valorvenda, v.valordesconto  ");
		
		while(rs.next()) {
			MercadoriaVendaRelatorioDTO mer = new MercadoriaVendaRelatorioDTO();
			mer.setDescricao(rs.getString("descricao"));
			mer.setIdCliente(rs.getInt("idcliente"));
			mer.setIdMercadoria(rs.getInt("idmercadoria"));
			mer.setNomeCompleto(rs.getString("nomecompleto"));
			mer.setQuantidade(rs.getDouble("quantidade"));
			mer.setValorDesconto(rs.getDouble("valordesconto"));
			mer.setValorMercadoria(rs.getDouble("valormercadoria"));
			mer.setValorVenda(rs.getDouble("valorvenda"));
			mer.setValorTotal(rs.getDouble("valortotal"));
			
			vendas.add(mer);
		}
				
				
		return vendas;
	}
	
	public TotaisDTO buscarTotaisRelatorio() throws SQLException {
		Conexao conn = new Conexao();
		TotaisDTO tot = new TotaisDTO();
		tot.setTotalBruto(0.00);
		tot.setTotalDesconto(0.00);
		tot.setTotalLiquido(0.00);
		
		ResultSet rs = conn.executeBusca("SELECT \r\n" + 
											"SUM(COALESCE(v.valorvenda, 0.00) * v.quantidade) as totalbruto, \r\n" + 
											"SUM(COALESCE(v.valordesconto, 0.00)) as totaldesconto, \r\n" + 
											"(SUM(COALESCE(v.valorvenda, 0.00) * v.quantidade) - SUM(COALESCE(v.valordesconto, 0.00))) as totalliquido\r\n" + 
											"FROM venda v");
		
		if(rs.next()) {
			tot.setTotalBruto(rs.getDouble("totalbruto"));
			tot.setTotalDesconto(rs.getDouble("totaldesconto"));
			tot.setTotalLiquido(rs.getDouble("totalliquido"));
		}
				
				
		return tot;
	}
	public List<PrevisaoCompraRelatorioDTO> buscarPrevisaoCompraRelatorio() throws SQLException {
		Conexao conn = new Conexao();
		List<PrevisaoCompraRelatorioDTO> vendas = new ArrayList<PrevisaoCompraRelatorioDTO>();
		
		ResultSet rs = conn.executeBusca("SELECT \r\n" + 
											"v.idmercadoria, \r\n" + 
											"m.descricao, \r\n" + 
											"SUM(COALESCE(v.quantidade, 0.00)) as quantidadeVenda, \r\n" + 
											"(CASE m.idtipo\r\n" + 
											"	WHEN 2 \r\n" + 
											"		THEN\r\n" + 
											"			COALESCE((SELECT emp.quantidade FROM estoquemp emp WHERE emp.idmercadoria = v.idmercadoria), 0.00)\r\n" + 
											"		ELSE\r\n" + 
											"			COALESCE((SELECT em.quantidade FROM estoquemercadoria em WHERE em.idmercadoria = v.idmercadoria), 0.00)\r\n" + 
											"	END) AS quantidadeEstoque\r\n" + 
											"FROM venda v\r\n" + 
											"JOIN mercadoria m ON (m.id = v.idmercadoria)\r\n" + 
											"LEFT JOIN estoquemercadoria em ON(em.idmercadoria = v.idmercadoria)\r\n" + 
											"LEFT JOIN estoquemp emp ON(emp.idmercadoria = v.idmercadoria)\r\n" + 
											"GROUP BY v.idmercadoria, m.descricao, m.idtipo");
		
		while(rs.next()) {
			PrevisaoCompraRelatorioDTO mer = new PrevisaoCompraRelatorioDTO();
			mer.setDescricao(rs.getString("descricao"));
			mer.setIdMercadoria(rs.getInt("idmercadoria"));
			mer.setQuantidadeEstoque(rs.getDouble("quantidadeestoque"));
			mer.setQuantidadeCompra(rs.getDouble("quantidadevenda"));
			
			vendas.add(mer);
		}
				
				
		return vendas;
	}
	
}
