package acchpoo;

public abstract class ContaBancaria {

    protected int numero;
    protected double saldo;
    
    /*public ContaBancaria(int numero, double saldo) {
        this.numero = numero;
        this.saldo = saldo;
    }
    public ContaBancaria() {
    }
     */
  

    public abstract void sacar(double valor);

    public abstract void depositar(double valor);

    public abstract void depositar(ContaBancaria contaBancaria, double valor);

    public abstract void transferir(double valor, ContaBancaria contaBancaria);

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

}
