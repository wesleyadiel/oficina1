package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CadastroCliente.Cliente;
import CadastroCliente.ClienteDTO;
import PesquisaCliente.ClientePesquisaDTO;
import acchpoo.Conexao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;

public class ClienteDAO {
	public ObservableList<ClienteDTO> get() throws SQLException {
		Conexao connection = new Conexao();
		ObservableList<ClienteDTO> clientes =  FXCollections.observableArrayList();
		
		ResultSet rs = connection.executeBusca("SELECT id, nomecompleto, telefone, cpf, rua, numero, bairro, cidade, (CASE ativo WHEN true THEN 'Ativo' ELSE 'Inativo' END) AS ativo FROM cliente ORDER BY id");
		

        while (rs.next()) {
        	ClienteDTO cliente = new ClienteDTO();
        	
        	cliente.setIdCliente(rs.getInt("id"));
        	cliente.setNomeCompleto(rs.getString("nomecompleto"));
        	cliente.setTelefone(rs.getString("telefone"));
        	cliente.setCpf(rs.getString("cpf"));
        	cliente.setRua(rs.getString("rua"));
        	cliente.setNumero(rs.getString("numero"));
        	cliente.setBairro(rs.getString("bairro"));
        	cliente.setCidade(rs.getString("cidade"));
        	cliente.setAtivo(rs.getString("ativo"));
        	
        	clientes.add(cliente);
        }
		
		return clientes;
	}
	
	public int salvar(Cliente cliente) {
		Conexao connection = new Conexao();
		
		if(cliente.getIdCliente() == 0) {
			int rs = connection.executeSQL("INSERT INTO cliente(nomecompleto, telefone, cpf, cidade, bairro, rua, numero, ativo) VALUES('"
					+ cliente.getNomeCompleto()+"', '"
							+ cliente.getTelefone()+"','"
									+cliente.getCpf()+ "','"
											+ cliente.getCidade()+"','"
													+ cliente.getBairro()+"','"
															+ cliente.getRua()+"','"
																	+ cliente.getNumero()+"','"
																			+ cliente.isAtivo() +"');");
			
			
			return rs;
		} else {
			int rs = connection.executeSQL("UPDATE cliente SET "
					+"nomecompleto = '"+ cliente.getNomeCompleto()+"', "
							+"telefone = '"+ cliente.getTelefone()+"', "
									+"cpf = '"+cliente.getCpf()+ "', "
											+"cidade = '"+ cliente.getCidade()+"', "
													+"bairro = '"+ cliente.getBairro()+"', "
															+"rua = '"+ cliente.getRua()+"', "
																	+"numero = '"+ cliente.getNumero()+"', "
																		+"ativo = "+ cliente.isAtivo()+ " "
																			+ "WHERE id = " + cliente.getIdCliente());
			
			
			return rs;
		}
	}
	
	public Cliente get(int idCliente) throws SQLException {
		Conexao connection = new Conexao();
    	Cliente cliente = new Cliente();
		
		ResultSet rs = connection.executeBusca("SELECT id, nomecompleto, telefone, cpf, rua, numero, bairro, cidade, ativo FROM cliente WHERE id = "+ idCliente);
		

        if (rs.next()) {
        	cliente.setIdCliente(rs.getInt("id"));
        	cliente.setNomeCompleto(rs.getString("nomecompleto"));
        	cliente.setTelefone(rs.getString("telefone"));
        	cliente.setCpf(rs.getString("cpf"));
        	cliente.setRua(rs.getString("rua"));
        	cliente.setNumero(rs.getString("numero"));
        	cliente.setBairro(rs.getString("bairro"));
        	cliente.setCidade(rs.getString("cidade"));
        	cliente.setAtivo(rs.getBoolean("ativo"));
        }
		
		return cliente;
	}

	public List<ClientePesquisaDTO> get(String text) throws SQLException {
		Conexao conn = new Conexao();
		List<ClientePesquisaDTO> list = new ArrayList<ClientePesquisaDTO>();
		
		String where = text.isEmpty() ? "" : " WHERE nomecompleto ~* '"+text+"' ";
		
		ResultSet rs = conn.executeBusca("SELECT * FROM cliente "+where);
		
		while(rs.next()) {
			ClientePesquisaDTO cli = new ClientePesquisaDTO();
			cli.setIdCliente(rs.getInt("id"));
			cli.setNomeCompleto(rs.getString("nomecompleto"));
			cli.setCpf(rs.getString("cpf"));
			
			list.add(cli);
		}
		
		return list;
	}
}
