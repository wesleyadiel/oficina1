package acchpoo;

public class ContaPoupanca extends ContaBancaria implements Impressao {

    private double limite;
    private Conexao c = new Conexao();
    private Banco b = new Banco(c);

    /*    public ContaPoupanca(double limite) {
        this.limite = limite;
    }
     */
    public ContaPoupanca() {
        this.numero = 0;
        this.saldo = 0;
        this.limite = 20;
    }

    @Override
    public void mostrarDados() {
        System.out.println("\nNumero da conta poupança : " + numero + "\nSaldo da conta poupança : " + saldo + "\nTaxa de operação da conta corrente : " + limite);
    }

    @Override
    public void sacar(double saque) {
        double saldoAntigo = saldo;
        double limiteAntigo = limite;
        if (saldo >= saque) {
            this.saldo = saldo - saque;
        } else if (saque > saldo) {
            double aux;
            aux = saque - saldo; //recebe a diferença entre saque e saldo
            this.limite = limite - aux; // desconta ela no limite
            this.saldo = saldo - saque; // atualiza o valor do saldo

            if (saldo < 0 && limite >= 0) {
                System.out.println("Atenção seu saldo está negativo : " + saldo + "Limite : " + limite);
            } else {
                this.saldo = saldoAntigo;
                this.limite = limiteAntigo;
                System.out.println("Não foi possível sacar! O valor passou do limite!");
            }
        }
    }

    public Conexao getC() {
        return c;
    }

    public void setC(Conexao c) {
        this.c = c;
    }

    public Banco getB() {
        return b;
    }

    public void setB(Banco b) {
        this.b = b;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    @Override
    public void depositar(double valor) {
        this.saldo = saldo + valor;
    }

    @Override
    public void depositar(ContaBancaria contaBancaria, double valor) {
        contaBancaria.saldo = contaBancaria.saldo + valor;
    }

    @Override
    public void transferir(double valor, ContaBancaria contaBancaria) {
        sacar(valor);
        depositar(contaBancaria, valor);

    }

}
