import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Pessoa {
    private String nome;
    private int idade;

    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String toString() {
        return "Pessoa{" + "nome='" + nome + '\'' +", idade=" + idade +"}";
    }
}

class Sorteador {
    private List<Pessoa> pessoas;
    private Random random;

    public Sorteador(List<Pessoa> pessoas) {
        this.pessoas = new ArrayList<>(pessoas);
        this.random = new Random();
    }

    public Pessoa sortearProximo() {
        if (pessoas.isEmpty()) {
            return null;
        }

        int index = random.nextInt(pessoas.size());
        return pessoas.remove(index);
    }
}

public class Main {
    public static void main(String[] args) {
        List<Pessoa> pessoas = new ArrayList<>();
        pessoas.add(new Pessoa("João", 25));
        pessoas.add(new Pessoa("Maria", 30));
        pessoas.add(new Pessoa("Carlos", 40));

        Sorteador sorteador = new Sorteador(pessoas);

        for (int i = 0; i < 3; i++) {
            Pessoa pessoaSorteada = sorteador.sortearProximo();
            if (pessoaSorteada != null) {
                System.out.println("Pessoa sorteada: " + pessoaSorteada);
            } else {
                System.out.println("Não há mais pessoas para sortear.");
            }
        }
    }
}