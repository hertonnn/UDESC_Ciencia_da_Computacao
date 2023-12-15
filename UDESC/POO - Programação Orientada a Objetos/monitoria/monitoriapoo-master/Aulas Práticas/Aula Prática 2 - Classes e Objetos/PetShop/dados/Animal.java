package dados;

public class Animal {

    private String nome;
    private Dono dono;
    private String especie;
    private String descricao;

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Dono getDono() {
        return this.dono;
    }

    public void setDono(Dono dono) {
        this.dono = dono;
    }

    public String getEspecie() {
        return this.especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String toString() {
        String animal = "";
        animal += "Nome: " + this.nome + "\n";
        animal += "Especie: " + this.especie + "\n";
        animal += "Descrição: " + this.descricao + "\n";
        return animal;
    }

}