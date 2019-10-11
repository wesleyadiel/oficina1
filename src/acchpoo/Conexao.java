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
        url = "jdbc:postgresql://localhost:5432/oficina";
        usuario = "postgres";
        senha = "123";

        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url, usuario, senha);
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

    public ResultSet ListarUsuarioesUE() {
        String sql = "SELECT * FROM  Empregado";
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

    public ResultSet ListarContasCP() {
        String sql = "SELECT * FROM  ContaPoupanca";
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

    public void removerContaCC(int numeroCC) {
        try {
            PreparedStatement stmt = con.prepareStatement("DELETE FROM ContaCorrente "
                    + "WHERE numero = '" + numeroCC + "' ");
            stmt.execute();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removerContaCP(int numeroCP) {
        try {
            PreparedStatement stmt = con.prepareStatement("DELETE FROM ContaPoupanca "
                    + "WHERE numero = '" + numeroCP + "' ");
            stmt.execute();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return con;
    }

    public void setConnection(Connection con) {
        this.con = con;
    }

}
