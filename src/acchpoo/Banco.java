package acchpoo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import CadMercadorias.Mercadoria;

public class Banco {

    /*    public Banco() {
    }
     */
    public Banco(Conexao conexao) {
        this.conexao = conexao;
    }

    public Conexao getConexao() {
        return conexao;
    }

    public void setConexao(Conexao conexao) {
        this.conexao = conexao;
    }

    private Conexao conexao;

    public void removerMercadoria(Mercadoria mercadoria) {
        conexao.removerMercadoria(mercadoria.getDescricao());
    }

  

    public void inserirVenda(Venda venda) {
        String sql = "INSERT INTO Venda(nomeCliente, descricaoMercadoria, "
                + "quantidade, desconto, precoVenda, precoMercadoria) VALUES('"
                + venda.getNomeCliente() + "','" + venda.getDescricaoMercadoria()
                + "','" + venda.getQuantidade() + "','" + venda.getDesconto()
                + "','" + venda.getPrecoVenda() + "','" + venda.getPrecoMercadoria() + "');";

        int res = conexao.executeSQL(sql);

        if (res > 0) {
            System.out.println("Venda cadastrada com sucesso!");
        } else {
            System.out.println("Erro!");
        }
    }
    
     public void cadastrarEstoqueMP(Estoque es) {
        String sql = "INSERT INTO Grupo(descricao, cor, tipo, quantidade, tamanho) VALUES('"
                + es.getDescricao() + es.getCor() + es.getTipo() + es.getQuantidade()
                + es.getTamanho() + "')";

        int res = conexao.executeSQL(sql);

        if (res > 0) {
            System.out.println("Estoque de matéria prima cadastrado com sucesso!");
        } else {
            System.out.println("Erro!");
        }

    }

    public void inserirEstoqueM(String descricao, int novaQuantidade) throws SQLException {
    	Conexao con = new Conexao();
    	String sql = "INSERT INTO estoqueMercadoria(descricao, quantidade) VALUES ('" + descricao +"', " + novaQuantidade + ");";
        con.executeSQL(sql);
    }

    public void atualizarEstoqueMP(String descricao, int novaQuantidade) throws SQLException {
        String sql = "UPDATE estoqueMP SET quantidade = '" + novaQuantidade + "' WHERE descricao ='" + descricao + "';";
        Statement stm = conexao.getCon().createStatement();
        stm.executeUpdate(sql);
    }

}
