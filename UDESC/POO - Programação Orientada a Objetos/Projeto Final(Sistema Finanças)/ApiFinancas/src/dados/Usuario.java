package dados;

public class Usuario {
    private int id;
    private String nome;
    private String senha;
    private Conta conta;

    public Usuario(String nome, String senha){
        this.nome = nome;
        this.senha = senha;

    }
    public String getNome() {
        return nome;
    }
    public String getSenha() {
        return senha;
    }
    public Conta getConta() {
        return conta;
    }
    public void setConta(Conta conta) {
        this.conta = conta;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}