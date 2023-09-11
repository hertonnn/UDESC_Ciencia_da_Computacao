import java.util.ArrayList;
import java.util.List;

class Curso {
    private String nome;
    private double preco;
    private int duracaoHoras;

    public Curso(String nome, double preco, int duracaoHoras) {
        this.nome = nome;
        this.preco = preco;
        this.duracaoHoras = duracaoHoras;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getDuracaoHoras() {
        return duracaoHoras;
    }

    public void setDuracaoHoras(int duracaoHoras) {
        this.duracaoHoras = duracaoHoras;
    }


    public String toString() {
        return "Curso{" +
                "nome='" + nome + '\'' +
                ", preco=" + preco +
                ", duracaoHoras=" + duracaoHoras +
                '}';
    }
}

class Aluno {
    private String nome;
    private String email;
    private int idade;

    public Aluno(String nome, String email, int idade) {
        this.nome = nome;
        this.email = email;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }


    public String toString() {
        return "Aluno{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", idade=" + idade +
                '}';
    }
}

class Compra {
    private Aluno aluno;
    private Curso curso;
    private double valor;

    public Compra(Aluno aluno, Curso curso, double valor) {
        this.aluno = aluno;
        this.curso = curso;
        this.valor = valor;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }


    public String toString() {
        return "Compra{" +
                "aluno=" + aluno +
                ", curso=" + curso +
                ", valor=" + valor +
                '}';
    }
}

class CarrinhoCompras {
    private List<Compra> compras;

    public CarrinhoCompras() {
        compras = new ArrayList<>();
    }

    public void adicionarCompra(Compra compra) {
        compras.add(compra);
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public double calcularTotal() {
        double total = 0;
        for (Compra compra : compras) {
            total += compra.getValor();
        }
        return total;
    }


    public String toString() {
        return "CarrinhoCompras{" +
                "compras=" + compras +
                '}';
    }
}

public class Main {
    public static void main(String[] args) {
        Aluno aluno1 = new Aluno("João", "joao@example.com", 25);
        Aluno aluno2 = new Aluno("Maria", "maria@example.com", 30);

        Curso curso1 = new Curso("Java Fundamentals", 150.0, 40);
        Curso curso2 = new Curso("Web Development", 200.0, 60);

        Compra compra1 = new Compra(aluno1, curso1, 150.0);
        Compra compra2 = new Compra(aluno2, curso2, 200.0);

        CarrinhoCompras carrinho = new CarrinhoCompras();
        carrinho.adicionarCompra(compra1);
        carrinho.adicionarCompra(compra2);

        System.out.println(carrinho);
        System.out.println("Total: $" + carrinho.calcularTotal());
    }
}