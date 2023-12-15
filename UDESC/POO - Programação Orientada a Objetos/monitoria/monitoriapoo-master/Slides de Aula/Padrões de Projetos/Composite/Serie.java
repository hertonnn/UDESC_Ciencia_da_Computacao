
public class Serie {

    private String nome;
    private int ano;

    public Serie() {
    }

    public Serie(String nome, int ano) {
        this.nome = nome;
        this.ano = ano;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAno() {
        return this.ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String toString() {
        return this.nome + " - " + this.ano;
    }

}