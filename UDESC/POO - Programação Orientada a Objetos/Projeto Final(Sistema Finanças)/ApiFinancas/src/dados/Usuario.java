package dados;

import java.util.ArrayList;

public class Usuario {
    private String nome;
    private String senha;
    private Conta conta;
    private ArrayList<Registro> registros;
    private ArrayList<Orcamento> orcamentos;


    public Usuario(String nome, String senha){
        this.nome = nome;
        this.senha = senha;
        registros = new ArrayList<>();
        orcamentos = new ArrayList<>();
    }
    public String getNome() {
        return nome;
    }
    public String getSenha() {
        return senha;
    }
    public void adicionaRegistro(Registro registro){
        registros.add(registro);
    }
    public void adicionaOrcamento(Orcamento Orcamento){
        orcamentos.add(Orcamento);
    }
    public void setConta(Conta conta) {
        this.conta = conta;
    }
    public ArrayList<Registro> getRegistros() {
        return registros;
    }
}