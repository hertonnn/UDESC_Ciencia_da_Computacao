package negocio;

import java.util.ArrayList;

import dados.ContaBancaria;
import dados.ContaCorrente;
import dados.ContaSalario;

public class Sistema{

    private int quantidade = 0;
    private ContaBancaria[] contas = new ContaBancaria[100];
    ContaSalario[] contasSalario;
    ContaCorrente[] contasCorrente;

    public void cadastrarConta(ContaBancaria conta){
        
        if(this.quantidade < contas.length){
            this.contas[this.quantidade] = conta;
            this.quantidade++;
        }
        else{
            System.out.println("Numero máximo de contas excedido.\n\n\n\n Era melhor ter usado ArrayList");
        } 
    }
    public boolean realizarSaque(ContaBancaria conta, float valor){
        if(conta.sacar(valor)){
            return true;
        }
        else{
            return false;
        }
        
    }
    public boolean realizarDeposito(ContaCorrente conta, float valor){
        if(conta.depositar(valor)){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean realizarDeposito(ContaSalario conta, float valor, int cnpj){
        if(conta.depositar(cnpj, valor)){
            return true;
        }
        else{
            return false;
        }
    }
    public ContaBancaria[] getContaBancarias() {
        return contas;
    }

    public ContaSalario[] getContasSalario() {
        
        int max = 0;

        for(var conta : this.contas){
            if(conta instanceof ContaSalario){
                max++;
            }
        }
        
        this.contasSalario = new ContaSalario[max];
        
        int i = 0;

        for(var conta : this.contas){
            if(conta instanceof ContaSalario){
                this.contasSalario[i] = (ContaSalario)(conta);
                i++;
            }
        }
        return this.contasSalario;

    }
    public ContaCorrente[] getContasCorrente() {
        
        int max = 0;

        for(var conta : this.contas){
            if(conta instanceof ContaCorrente){
                max++;
            }
        }
        
        this.contasCorrente = new ContaCorrente[max];
        
        int i = 0;

        for(var conta : this.contas){
            if(conta instanceof ContaCorrente){
                this.contasCorrente[i] = (ContaCorrente)(conta);
                i++;
            }
        }
        return this.contasCorrente;

    }
    public String obterExtrato(ContaBancaria conta) {
        return conta.gerarExtrato();
    }
    public int getQuantidadeContas() {
        return this.quantidade;
    }
}