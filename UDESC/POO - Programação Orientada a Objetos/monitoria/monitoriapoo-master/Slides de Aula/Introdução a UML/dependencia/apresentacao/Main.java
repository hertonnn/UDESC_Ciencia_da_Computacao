package apresentacao;

import java.util.Scanner;
import java.util.LinkedList;

import dados.Pessoa;
import negocio.Sistema;

public class Main {

    private static Sistema sistema = new Sistema();
    private static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {

        int opcao = -1;

        while (opcao != 0) {

            System.out.println("Escolha uma opcao:");
            System.out.println("0 - Sair");
            System.out.println("1 - Cadastrar Pessoa");
            System.out.println("2 - Exibir Pessoas");

            opcao = s.nextInt();

            switch (opcao) {
            case 0:
                break;
            case 1:
                sistema.cadastrarPessoa(novaPessoa());
                break;
            case 2:
                exibirPessoas();
                break;
            default:
                System.out.println("Valor incorreto!");
                break;
            }
        }
    }

    public static Pessoa novaPessoa() {

        System.out.println("Digite o nome da pessoa");
        String nome = s.nextLine();
        nome = s.nextLine();

        System.out.println("Digite a idade da pessoa");
        int idade = s.nextInt();

        return new Pessoa(nome, idade);

    }

    public static void exibirPessoas() {
        for (Pessoa pessoa : sistema.mostrarPessoas()) {
            System.out.println(pessoa.toString());
        }
    }

}