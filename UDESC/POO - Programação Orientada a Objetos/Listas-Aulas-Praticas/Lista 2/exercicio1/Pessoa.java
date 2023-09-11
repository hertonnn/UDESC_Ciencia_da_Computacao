package exercicio1;

public class Pessoa implements Comparable<Pessoa> {
    private String nome;
    private int idade;
    private int cpf;
    private String cidade;

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public void setCpf(int cpf) {
        this.cpf = cpf;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCidade() {
        return cidade;
    }
    public int getCpf() {
        return cpf;
    }
    public int getIdade() {
        return idade;
    }
    public String getNome() {
        return nome;
    }
    public String toString(){
        return this.nome +","+ this.idade+ "," + this.cpf + "," + this.cidade;
    }
    public int compareTo(Pessoa o) {
		return nome.compareTo(o.getNome());
	}
}
