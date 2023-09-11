package dados;

import java.util.ArrayList;

public class Conta {
    private String nome;
    private double saldo;
    private ArrayList<Registro> historico;

    public Conta(String nome, double saldo){
        this.nome = nome;
        this.saldo = saldo;
        historico = new ArrayList<>();
    }

    public void deposita(double valor) {
        this.saldo += valor;
    }
    public boolean saca(double valor) {
        if(this.saldo < valor){
            return false;
        }
        else{
            this.saldo -= valor;
            return true;
        }
    }
    public void adicionaRegistro(Registro novoRegistro){
        historico.add(novoRegistro);
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public double getSaldo() {
        return saldo;
    }
}
