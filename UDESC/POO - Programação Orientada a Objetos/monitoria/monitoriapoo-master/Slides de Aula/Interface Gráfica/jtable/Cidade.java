package jtable;

public class Cidade {

    private String nome;
    private String estado;

    public Cidade(String nome, String estado) {
        this.nome = nome;
        this.estado = estado;
    }

    public String getNome() {
        return this.nome;
    }

    public String getEstado() {
        return this.estado;
    }

}