package acchpoo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

    public void inserirMercadoria(Mercadoria mercadoria) {
        String sql = "INSERT INTO Mercadoria(descricao, marca, tipo, grupo, quantidade, preco) VALUES('"
                + mercadoria.getDescricao() + "','" + mercadoria.getMarca() + "','" + mercadoria.getTipo() + "','" + mercadoria.getGrupo() +"','" + mercadoria.getQuantidade() + "','" + mercadoria.getPreco() + "');";

        int res = conexao.executeSQL(sql);

        if (res > 0) {
            System.out.println("Mercadoria cadastrada com sucesso!");
        } else {
            System.out.println("Erro!");
        }
    }

    public void inserirEncomenda(Encomenda encomenda) {
        String sql = "INSERT INTO Encomenda(descricao, tipo , NomeCliente, quantidade, data) VALUES('"
                + encomenda.getDescricao() + "','" + encomenda.getTipo() + "','" + encomenda.getNomeCliente() + "','" + encomenda.getQuantidade() + "','" + encomenda.getData() + "')";

        int res = conexao.executeSQL(sql);

        if (res > 0) {
            System.out.println("Encomenda cadastrada com sucesso!");
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

    public void inserirSubGrupo(SubGrupo sg) {
        String sql = "INSERT INTO SubGrupo(descricao, grupo) VALUES('"
                + sg.getDescricao() + "','" + sg.getGrupo() + "')";

        int res = conexao.executeSQL(sql);

        if (res > 0) {
            System.out.println("Sub Grupo cadastrado com sucesso!");
        } else {
            System.out.println("Erro!");
        }

    }

    public void inserirGrupo(Grupo g) {
        String sql = "INSERT INTO Grupo(descricao) VALUES('"
                + g.getDescricao() + "')";

        int res = conexao.executeSQL(sql);

        if (res > 0) {
            System.out.println("Grupo cadastrado com sucesso!");
        } else {
            System.out.println("Erro!");
        }

    }

    public void removerEncomenda(Encomenda encomenda) {
        conexao.removerEncomenda(encomenda.getDescricao());
    }

    public Mercadoria consultarMercadoria(String descricao) throws SQLException {
        Mercadoria mercadoria = new Mercadoria();
        String sql = "SELECT descricao, marca, tipo, grupo, quantidade FROM  Mercadoria WHERE descricao='" + descricao + "'";

        Statement stm = conexao.getCon().createStatement();
        ResultSet rs = stm.executeQuery(sql);
        rs.next();
        mercadoria.setDescricao(rs.getString("descricao"));
        mercadoria.setMarca(rs.getString("marca"));
        mercadoria.setTipo(rs.getString("tipo"));
        mercadoria.setGrupo(rs.getString("grupo"));
        mercadoria.setQuantidade(rs.getInt("quantidade"));
        return mercadoria;
    }

    public void atualizarEstoqueM(String descricao, int novaQuantidade) throws SQLException {
        String sql = "UPDATE estoqueMercadoria SET quantidade = '" + novaQuantidade + "' WHERE descricao ='" + descricao + "';";
        Statement stm = conexao.getCon().createStatement();
        stm.executeUpdate(sql);
    }

    public void atualizarEstoqueMP(String descricao, int novaQuantidade) throws SQLException {
        String sql = "UPDATE estoqueMP SET quantidade = '" + novaQuantidade + "' WHERE descricao ='" + descricao + "';";
        Statement stm = conexao.getCon().createStatement();
        stm.executeUpdate(sql);
    }

}
