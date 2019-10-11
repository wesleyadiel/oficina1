package acchpoo;

public class ContaCorrente extends ContaBancaria implements Impressao {

    private double taxaDeOperacao;
    private double antigo;
    /*  public ContaCorrente(double taxaDeOperacao) {
        this.taxaDeOperacao = taxaDeOperacao;
    }
     */
    public ContaCorrente() {
        this.numero = 0;
        this.saldo = 0;
        this.taxaDeOperacao = 0;
    }
    

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getTaxaDeOperacao() {
        return taxaDeOperacao;
    }

    public void setTaxaDeOperacao(double taxaDeOperacao) {
        this.taxaDeOperacao = taxaDeOperacao;
    }

    @Override
    public void mostrarDados() {
        System.out.println("\nNumero da conta corrente : " + numero + "\nSaldo da conta corrente : " + saldo + "\nTaxa de operação da conta corrente : " + taxaDeOperacao);
    }

    @Override
    public void sacar(double valor) {
        if (saldo > 0 && saldo>valor+valor*0.1) {
            taxaDeOperacao = valor * 0.1;
            this.saldo = saldo - valor - taxaDeOperacao;
        }else{
            System.out.println("\n***Saque não realizado! Não é possivel sacar um valor que irá negativar o saldo da conta corrente!***");
        }
        
    }

    @Override
    public void depositar(double valor) {
        taxaDeOperacao = valor * 0.1;
        this.saldo = this.saldo + valor - taxaDeOperacao;
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
