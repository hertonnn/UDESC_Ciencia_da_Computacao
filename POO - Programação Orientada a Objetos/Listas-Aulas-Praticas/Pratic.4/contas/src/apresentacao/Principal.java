package apresentacao;

import java.util.Scanner;

import dados.ContaBancaria;
import dados.ContaCorrente;
import dados.ContaSalario;
import negocio.Sistema;

public class Principal {

    private static Sistema sistema = new Sistema();
    private static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {

        int opcao = -1;

        while (opcao != 0) {

            imprimeMenu();
            opcao = s.nextInt();

            switch (opcao) {
                case 0:
                    break;
                case 1:
                    cadastrarConta();
                    break;
                case 2:
                    realizarSaque();
                    break;
                case 3:
                    realizarDeposito();
                    break;
                case 4:
                    mostrarExtrato();
                    break;
            }

        }

    }

    private static void mostrarExtrato() {

        ContaBancaria conta = escolherContaBancaria();

        if (conta != null) {

            System.out.println(sistema.obterExtrato(conta));

        }

    }

    public static void imprimeMenu() {

        System.out.println("Escolha uma opção:");
        System.out.println("0 - Sair");
        System.out.println("1 - Cadastrar Conta");
        System.out.println("2 - Realizar Saque");
        System.out.println("3 - Realizar Deposito");
        System.out.println("4 - Exibir Extrato");

    }

    private static void cadastrarConta() {

        System.out.println("Digite o tipo de conta que deseja cadastrar:");
        System.out.println("1 - Conta Corrente");
        System.out.println("2 - Conta Salario");

        int escolha = s.nextInt();

        switch (escolha) {
            case 1:
                sistema.cadastrarConta(novaContaCorrente());
                break;
            case 2:
                sistema.cadastrarConta(novaContaSalario());
                break;
            default:
                System.out.println("Escolha inválida!");
                break;
        }
    }

    private static ContaCorrente novaContaCorrente() {

        ContaCorrente conta = new ContaCorrente();

        System.out.println("Digite o cpf:");
        conta.setCpf(s.nextInt());

        return conta;
    }

    private static ContaSalario novaContaSalario() {

        ContaSalario conta = new ContaSalario();

        System.out.println("Digite o cpf:");
        conta.setCpf(s.nextInt());

        System.out.println("Digite o cnpj da empresa:");
        conta.setCpnjEmpresa(s.nextInt());

        return conta;
    }

    private static void realizarSaque() {

        ContaBancaria conta = escolherContaBancaria();

        if (conta != null) {
            System.out.println("Digite o valor a ser sacado:");
            int valor = s.nextInt();

            System.out.println(sistema.realizarSaque(conta, valor));
        }

    }

    private static void exibirContas() {
        for (int i = 0; i < sistema.getQuantidadeContas(); i++) {
            System.out.println("Conta " + i + ":\n" + sistema.getContaBancarias()[i].toString() + "\n");
        }
    }

    private static ContaBancaria escolherContaBancaria() {

        exibirContas();
        System.out.println("Escolha uma conta:");

        int conta = s.nextInt();

        if (conta < sistema.getQuantidadeContas()) {
            return sistema.getContaBancarias()[conta];
        }

        return null;

    }

    private static void realizarDeposito() {

        ContaBancaria conta = escolherContaBancaria();

        if (conta != null) {

            if (conta instanceof ContaCorrente) {

                System.out.println("Digite um valor a ser depositado:");
                int valor = s.nextInt();

                sistema.realizarDeposito((ContaCorrente) (conta), valor);
                System.out.println("Deposito realizado com sucesso!");
                System.out.println(sistema.obterExtrato((ContaCorrente) (conta)));

            } else {

                System.out.println("Digite um valor a ser depositado:");
                int valor = s.nextInt();

                System.out.println("Digite o cnpj da empresa que está depositando:");
                int cnpj = s.nextInt();

                if (sistema.realizarDeposito((ContaSalario) (conta), valor, cnpj)) {

                    System.out.println("Deposito realizado com sucesso!");
                    System.out.println(sistema.obterExtrato((ContaSalario) (conta)));

                } else {

                    System.out.println("Falha ao depositar!");

                }

            }

        }
    }

}