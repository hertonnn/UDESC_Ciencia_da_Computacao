package negocio;

import dados.ContaBancaria;
import dados.ContaCorrente;
import dados.ContaSalario;

public class Sistema {

    private ContaBancaria[] contaBancarias = new ContaBancaria[100];
    private int quantidade = 0;

    public void cadastrarConta(ContaBancaria conta) {

        if (quantidade < 100) {
            this.contaBancarias[quantidade] = conta;
            quantidade++;
        }

    }

    public String realizarSaque(ContaBancaria conta, float valor) {
        conta.sacar(valor);
        return this.obterExtrato(conta);
    }

    public boolean realizarDeposito(ContaCorrente conta, float valor) {

        return conta.depositar(valor);

    }

    public boolean realizarDeposito(ContaSalario conta, float valor, int cnpj) {

        return conta.depositar(valor, cnpj);

    }

    public ContaCorrente[] getContasCorrentes() {

        int max = 0;

        for (int i = 0; i < quantidade; i++) {
            if (contaBancarias[i] instanceof ContaCorrente) {
                max++;
            }
        }

        ContaCorrente[] contas = new ContaCorrente[max];

        int qnt = 0;

        for (int i = 0; i < quantidade; i++) {
            if (contaBancarias[i] instanceof ContaCorrente) {
                contas[qnt] = (ContaCorrente) (contaBancarias[i]);
                qnt++;
            }
        }

        return contas;
    }

    public ContaSalario[] getContaSalarios() {

        int max = 0;

        for (int i = 0; i < quantidade; i++) {
            if (contaBancarias[i] instanceof ContaSalario) {
                max++;
            }
        }

        ContaSalario[] contas = new ContaSalario[max];

        int qnt = 0;

        for (int i = 0; i < quantidade; i++) {
            if (contaBancarias[i] instanceof ContaSalario) {
                contas[qnt] = (ContaSalario) (contaBancarias[i]);
                qnt++;
            }
        }

        return contas;
    }

    public ContaBancaria[] getContaBancarias() {
        return contaBancarias;
    }

    public int getQuantidadeContas() {
        return this.quantidade;
    }

    public String obterExtrato(ContaBancaria conta) {

        return conta.gerarExtrato();

    }
}