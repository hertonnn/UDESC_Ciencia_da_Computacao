package dados;

public class Veterinario {
	
	private String nome;
	private float salario;
	private Animal animais[];
	private int quantAnimais = 0;
	
	public  Veterinario(int tamAnimais) {
		animais = new Animal[tamAnimais]; 
	}
	
	public void cadastrarAnimal(Animal animal) {
		if (quantAnimais < animais.length) {
			animais[quantAnimais] = animal;
			quantAnimais++;
		}
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public float getSalario() {
		return salario;
	}
	
	public void setSalario(float salario) {
		this.salario = salario;
	}
	
	public Animal[] getAnimais() {
		return animais;
	}
	public int getQuantAnimais() {
		return quantAnimais;
	}
	
	public String toString() {
		return "Nome: "+nome+" Salário: "+salario;
	}
}
