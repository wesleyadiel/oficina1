package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import CadastroCliente.Cliente;
import CadastroEmpregado.Empregado;
import CadastroEmpregado.EmpregadoDTO;
import acchpoo.Conexao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EmpregadoDAO {
	public ObservableList<EmpregadoDTO> get() throws SQLException {
		Conexao connection = new Conexao();
		ObservableList<EmpregadoDTO> empregados =  FXCollections.observableArrayList();
		
		ResultSet rs = connection.executeBusca("SELECT id, nomecompleto, telefone, cpf, rua, numero, bairro, cidade, idusuario, (CASE ativo WHEN true THEN 'Ativo' ELSE 'Inativo' END) AS ativo FROM empregado ORDER BY id");
		

        while (rs.next()) {
        	EmpregadoDTO empregado = new EmpregadoDTO();
        	
        	empregado.setIdEmpregado(rs.getInt("id"));
        	empregado.setNomeCompleto(rs.getString("nomecompleto"));
        	empregado.setTelefone(rs.getString("telefone"));
        	empregado.setCpf(rs.getString("cpf"));
        	empregado.setRua(rs.getString("rua"));
        	empregado.setNumero(rs.getString("numero"));
        	empregado.setBairro(rs.getString("bairro"));
        	empregado.setCidade(rs.getString("cidade"));
			empregado.setIdUsuario(rs.getInt("idusuario"));
        	empregado.setAtivo(rs.getString("ativo"));
        	
        	empregados.add(empregado);
        }
		
		return empregados;
	}

	public int salvar(Empregado empregado) {
			Conexao connection = new Conexao();
			
			if(empregado.getIdEmpregado() == 0) {
				int rs = connection.executeSQL("INSERT INTO empregado(nomecompleto, telefone, cpf, cidade, bairro, rua, numero, idusuario, ativo) VALUES('"
						+ empregado.getNomeCompleto()+"', '"
								+ empregado.getTelefone()+"', '"
										+empregado.getCpf()+ "', '"
												+ empregado.getCidade()+"', '"
														+ empregado.getBairro()+"', '"
																+ empregado.getRua()+"', '"
																		+ empregado.getNumero()+"', "
																				+ empregado.getIdUsuario()+",'"
																					+ empregado.isAtivo() +"');");
				
				
				return rs;
			} else {
				int rs = connection.executeSQL("UPDATE empregado SET "
						+"nomecompleto = '"+ empregado.getNomeCompleto()+"', "
								+"telefone = '"+ empregado.getTelefone()+"', "
										+"cpf = '"+empregado.getCpf()+ "', "
												+"cidade = '"+ empregado.getCidade()+"', "
														+"bairro = '"+ empregado.getBairro()+"', "
																+"rua = '"+ empregado.getRua()+"', "
																		+"numero = '"+ empregado.getNumero()+"', "
																			+"idusuario = "+ empregado.getIdUsuario()+", "
																				+"ativo = "+ empregado.isAtivo()+ " "
																					+ "WHERE id = " + empregado.getIdEmpregado());
				
				
				return rs;
			}
	}

	public Empregado getById(int idEmpregado) {
		Conexao connection = new Conexao();
    	Empregado empregado = new Empregado();
    	
		ResultSet rs = connection.executeBusca("SELECT id, nomecompleto, telefone, cpf, rua, numero, bairro, cidade, idusuario, ativo FROM empregado WHERE id = " + idEmpregado);
		

        try {
			if (rs.next()) {
				empregado.setIdEmpregado(rs.getInt("id"));
				empregado.setNomeCompleto(rs.getString("nomecompleto"));
				empregado.setTelefone(rs.getString("telefone"));
				empregado.setCpf(rs.getString("cpf"));
				empregado.setRua(rs.getString("rua"));
				empregado.setNumero(rs.getString("numero"));
				empregado.setBairro(rs.getString("bairro"));
				empregado.setCidade(rs.getString("cidade"));
				empregado.setIdUsuario(rs.getInt("idusuario"));
				empregado.setAtivo(rs.getBoolean("ativo"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return empregado;
	}
}
