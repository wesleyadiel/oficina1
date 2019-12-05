package DAO;

import Encomenda.Encomenda;
import acchpoo.Conexao;

public class EncomendaDAO {
	public void salvar(Encomenda encomenda) {
		Conexao conn = new Conexao();
		
		conn.executeSQL("INSERT INTO encomenda(idmercadoria, idcliente, quantidade, data, finalizada) VALUES("+encomenda.getIdMercadoria()+", "+encomenda.getIdCliente()+", "+ encomenda.getQuantidade() +", '"+encomenda.getData()+"', "+encomenda.getFinalizada()+")");
	}
}
