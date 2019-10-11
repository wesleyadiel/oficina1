package acchpoo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Banco implements Impressao {

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

    public void removerUsuario(UsuarioEmpregado usuarioEmpregado) {
        conexao.removerUsuarioUE(usuarioEmpregado.numero);
    }

    public void inserirUsuario(UsuarioEmpregado usuarioEmpregado) {
        String sql = "INSERT INTO ContaCorrente(numero,saldo,taxaDeOperacao) VALUES('"
                + contaCorrente.getNumero() + "','" + contaCorrente.getSaldo() + "','" + contaCorrente.getTaxaDeOperacao() + "');";

        int res = conexao.executeSQL(sql);

        if (res > 0) {
            System.out.println("Conta Corrente cadastrada com sucesso!");
        } else {
            System.out.println("Erro!");
        }
    }

    public void inserirConta(ContaPoupanca contaPoupanca) {
        String sql = "INSERT INTO ContaPoupanca(numero,saldo,limite) VALUES('" 
                + contaPoupanca.getNumero() + "','" + contaPoupanca.getSaldo() + "','" + contaPoupanca.getLimite() + "')";

        int res = conexao.executeSQL(sql);

        if (res > 0) {
            System.out.println("Conta Poupanca cadastrada com sucesso!");
        } else {
            System.out.println("Erro!");
        }

    }

    public void removerConta(ContaPoupanca contaPoupanca) {
        conexao.removerContaCP(contaPoupanca.getNumero());
    }

    public ContaBancaria consultarContaCC(int numConta) throws SQLException {
        ContaBancaria contaBancaria = new ContaCorrente();
        String sql = "SELECT numero, saldo FROM  ContaCorrente WHERE numero='" + numConta + "'";

        Statement stm = conexao.getCon().createStatement();
        ResultSet rs = stm.executeQuery(sql);
        rs.next();
        contaBancaria.setNumero(rs.getInt("numero"));
        contaBancaria.setSaldo(rs.getDouble("saldo"));
        return contaBancaria;
    }

    public ContaBancaria consultarContaCP(int numConta) throws SQLException {
        ContaBancaria contaBancaria = new ContaPoupanca();

        String sql = "SELECT numero, saldo FROM  ContaPoupanca WHERE numero='" + numConta + "'";

        Statement stm = conexao.getCon().createStatement();
        ResultSet rs = stm.executeQuery(sql);
        rs.next();
        contaBancaria.setNumero(rs.getInt("numero"));
        contaBancaria.setSaldo(rs.getDouble("saldo"));
        return contaBancaria;
    }

    public void atualizarcc(double novosaldo, int numero) throws SQLException {
        String sql = "UPDATE ContaCorrente SET Saldo = '" + novosaldo + "' WHERE numero ='" + numero + "';";
        Statement stm = conexao.getCon().createStatement();
        stm.executeUpdate(sql);
    }

    public void atualizarcp(double novosaldo, int numero) throws SQLException {
        String sql = "UPDATE ContaPoupanca SET Saldo = '" + novosaldo + "' WHERE numero ='" + numero + "';";
        Statement stm = conexao.getCon().createStatement();
        stm.executeUpdate(sql);
    }

    @Override
    public void mostrarDados() {
        Scanner sc = new Scanner(System.in);
        int numConta = sc.nextInt();
        System.out.println("Informe o número da conta bancária da qual deseja exibir um extrato : ");
        String sql = "SELECT cb.numero, cb.saldo, cc.taxaDeOperacao, cp.limite FROM  ContaCorrente cc"
                + "INNER JOIN ContaBancaria cb  ON" + numConta + "=cc.id_contaC"
                + "INNER JOIN ContaPoupanca cp ON" + numConta + "=cp.id_contaP;";
        ResultSet rs = conexao.executeBusca(sql);
        try {
            while (rs.next()) {
                int numero = rs.getInt("cb.numero");
                double saldo = rs.getDouble("cb.saldo");
                double taxaOp = rs.getDouble("cc.taxaDeOperacao");
                double limite = rs.getDouble("cp.limite");
                System.out.println("*EXTRATO BANCÁRIO*"
                        + "\n*DADOS DA CONTA BANCÁRIA*"
                        + "\nNumero : " + numero
                        + "\nSaldo : " + saldo
                        + "\n*DADOS DA CONTA CORRENTE*"
                        + "\nTaxa de operação : " + taxaOp
                        + "\n*DADOS DA CONTA POUPANCA*"
                        + "\nLimite : " + limite);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
