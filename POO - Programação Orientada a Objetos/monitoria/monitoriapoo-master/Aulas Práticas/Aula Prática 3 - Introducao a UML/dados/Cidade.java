package dados;

public class Cidade {

    private String nome;
    private String estado;

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String toString() {
        return this.nome + " - " + this.estado + "\n";
    }

}