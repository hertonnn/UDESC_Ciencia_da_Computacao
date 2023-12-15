
public class Produto {

    private String nome;
    private double valor;

    public Produto(String nome, double valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return this.valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean equals(Object objeto) {
        Produto p = (Produto) (objeto);
        if (p.getNome().equals(nome) && p.getValor() == valor) {
            return true;
        }
        return false;
    }
}