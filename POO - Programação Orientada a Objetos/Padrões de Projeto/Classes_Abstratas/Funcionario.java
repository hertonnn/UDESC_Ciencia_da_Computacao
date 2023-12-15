//Apenas pode ser uma superclasse. Não de pode ser instanciada.
public abstract class Funcionario{
    // Trabalhando com herança, logo protected
    protected String nome;
    protected double salario;

    
    public Funcionario(String nome, double salario) {
        this.nome = nome;
        this.salario = salario;
    }
}