package dados;

public class Contato {

    private String nome;
    private int telefone;

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTelefone() {
        return this.telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String toString() {
        return this.nome + " - " + this.telefone;
    }

    @Override
    public boolean equals(Object object) {
        Contato c = (Contato) object;
        return c.getTelefone() == this.telefone;
    }

}