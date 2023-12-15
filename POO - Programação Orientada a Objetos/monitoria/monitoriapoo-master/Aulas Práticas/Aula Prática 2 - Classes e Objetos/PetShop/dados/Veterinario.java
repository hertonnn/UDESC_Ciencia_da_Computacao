package dados;

public class Veterinario {

    private String nome;
    private float salario;
    private Endereco endereco;
    private Animal[] animais = new Animal[30];
    private int quantidadeAnimais = 0;

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getSalario() {
        return this.salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public Endereco getEndereco() {
        return this.endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Animal[] getAnimais() {
        return this.animais;
    }

    public void setAnimais(Animal animal) {

        if (this.quantidadeAnimais < 30) {
            this.animais[this.quantidadeAnimais] = animal;
            this.quantidadeAnimais++;
        }

    }

    public int getQuantidadeAnimais() {
        return this.quantidadeAnimais;
    }

    public String toString() {

        String veterinario = "";

        veterinario += "Nome: " + this.nome + "\n";
        veterinario += "Salario: " + this.salario + "\n";

        if (this.endereco != null) {
            veterinario += "Endereço: " + this.endereco.toString() + "\n";
        }

        return veterinario;
    }

}