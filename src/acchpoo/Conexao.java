package acchpoo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Conexao {

    private String url;
    private String usuario;
    private String senha;
    private Connection con;

    /*public Conexao(){
    }
        public Conexao(String url, String usuario, String senha, Connection con) {
        this.url = url;
        this.usuario = usuario;
        this.senha = senha;
        this.con = con;
    }
     */
    public Conexao() {
        url = "jdbc:postgresql://localhost:5432/gestire";
        usuario = "postgres";
        senha = "postgres";

        try {
            Class.forName("org.postgresql.Driver");
            this.con = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conectado!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public int executeSQL(String sql) {
        try {
            Statement stm = con.createStatement();
            int res = stm.executeUpdate(sql);
            con.createStatement();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public ResultSet executeBusca(String sql) {
        try {
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery(sql);
            con.close();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet ListarEstoqueMP() {
        String sql = "SELECT m.descricao, SUM(emp.quantidade) AS quantidade FROM estoquemp emp JOIN mercadoria m ON(m.id = emp.idmercadoria) GROUP BY m.descricao";
        try {
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery(sql);
            con.close();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet ListarVendas() {
        String sql = "SELECT * FROM  Venda";
        try {
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery(sql);
            con.close();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet ListarLucroBrutoVendas() {
        String sql = "SELECT sum(precoVenda) as total FROM  Venda";
        try {
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery(sql);
            con.close();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet ListarLucroLiquidoVendas() {
        String sql = "SELECT (sum(precoVenda) - sum(precoMercadoria)) as total FROM  Venda";
        try {
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery(sql);
            con.close();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet ListarEstoqueMercadoria() {
        String sql = "SELECT m.descricao, SUM(em.quantidade) AS quantidade FROM EstoqueMercadoria em JOIN mercadoria m ON(m.id = em.idmercadoria) GROUP BY m.descricao";
        try {
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery(sql);
            con.close();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void removerEncomenda(String descricao) {
        try {
            PreparedStatement stmt = con.prepareStatement("DELETE FROM Encomenda "
                    + "WHERE descricao = '" + descricao + "' ");
            stmt.execute();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removerMercadoria(String descricao) {
        try {
            PreparedStatement stmt = con.prepareStatement("DELETE FROM Mercadoria "
                    + "WHERE descricao = '" + descricao + "' ");
            stmt.execute();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*  public void removerContaCC(int numeroCC) {
        try {
            PreparedStatement stmt = con.prepareStatement("DELETE FROM ContaCorrente "
                    + "WHERE numero = '" + numeroCC + "' ");
            stmt.execute();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     */
    public Connection getConnection() {
        return con;
    }

    public void setConnection(Connection con) {
        this.con = con;
    }

}
