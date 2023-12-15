
public class Episodio {

    private String nome;
    private int numero;

    public Episodio() {
    }

    public Episodio(String nome, int numero) {
        this.nome = nome;
        this.numero = numero;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumero() {
        return this.numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String toString() {
        return numero + " - " + nome;
    }

}