package acchpoo;

public class Relatorio {

    /*public Relatorio() {
    }*/
    public void gerarRelatorioC(ContaCorrente contaCorrente) {
        String relatorio = "\n*RELATÓRIO DA CONTA CORRENTE *"
                + "\nNumero : " + contaCorrente.getNumero()
                + "\nSaldo  após o saque : " + contaCorrente.getSaldo()
                + "\nTaxa de Operação : " + contaCorrente.getTaxaDeOperacao();
        mostrarDadosDoObjeto(relatorio);
    }

    public void gerarRelatorioP(ContaPoupanca contaPoupanca) {
        String relatorio = "\n*RELATÓRIO DA CONTA POUPANCA *"
                + "\nNumero : " + contaPoupanca.getNumero()
                + "\nSaldo após o saque : " + contaPoupanca.getSaldo()
                + "\nLimite : " + contaPoupanca.getLimite();
        mostrarDadosDoObjeto(relatorio);
    }

    public void mostrarDadosDoObjeto(String relatorio) {
        System.out.println(relatorio);
    }

}
