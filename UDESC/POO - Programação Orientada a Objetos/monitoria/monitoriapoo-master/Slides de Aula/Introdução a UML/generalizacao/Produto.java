package generalizacao;

public class Produto{
    private String nome;
    private float valor;

    public String getNome(){
        return this.nome;
    }

    public float getValor(){
        return this.valor;
    }

    public void setValor(float valor){
        this.valor = valor;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
}