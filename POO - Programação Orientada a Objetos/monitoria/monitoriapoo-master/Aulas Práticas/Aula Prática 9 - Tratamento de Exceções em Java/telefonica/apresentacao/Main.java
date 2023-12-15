package apresentacao;

import java.util.Scanner;

import dados.Contato;
import exceptions.ContatoJaCadastradoException;
import exceptions.ContatoNaoCadastradoException;
import exceptions.ErroNaGravacaoException;
import exceptions.ErroNaLeituraException;
import negocio.ListaTelefonica;

public class Main {

    private static Scanner s = new Scanner(System.in);
    private static ListaTelefonica lista = new ListaTelefonica();

    public static void main(String[] args) {

        int opcao = -1;

        while (opcao != 0) {

            System.out.println("Escolha uma opção:");
            System.out.println("0 - Sair");
            System.out.println("1 - Cadastrar um contato");
            System.out.println("2 - Remover um contato");
            System.out.println("3 - Mostrar todos os contatos");

            opcao = s.nextInt();

            switch (opcao) {
                case 0:
                    break;
                case 1:
                    Contato contato = novoContato();
                    try {
                        lista.adicionaContato(contato);
                    } catch (ErroNaLeituraException e) {
                        System.out.println(e.getMessage());
                        System.out.println("Caminho informado: " + e.getCaminho());
                    } catch (ErroNaGravacaoException e) {
                        System.out.println(e.getMessage());
                        System.out.println("Caminho informado: " + e.getCaminho());
                    } catch (ContatoJaCadastradoException e) {
                        System.out.println(e);
                    }
                    break;
                case 2:
                    removerContato();
                    break;
                case 3:
                    exibirContatos();
                    break;
                default:
                    break;
            }
        }

    }

    public static Contato novoContato() {

        System.out.println("Digite o nome do contato:");
        String nome = s.nextLine();
        nome = s.nextLine();

        System.out.println("Digite o telefone do contato:");
        int telefone = s.nextInt();

        Contato c = new Contato();
        c.setNome(nome);
        c.setTelefone(telefone);

        return c;
    }

    public static void exibirContatos() {

        try {
            lista.buscarContatos().forEach((chave, lista) -> {
                System.out.println(chave + ":");
                for (Contato contato : lista) {
                    System.out.println("  " + contato.toString());
                }
            });
        } catch (ErroNaLeituraException e) {
            System.out.println(e.getMessage());
            System.out.println("Caminho informado: " + e.getCaminho());
        }

    }

    public static void exibirContatos(char letra) {
        try {
            for (int i = 0; i < lista.buscarContatos(letra).size(); i++) {
                System.out.println("Codigo: " + i);
                System.out.println(lista.buscarContatos(letra).get(i).toString());
            }
        } catch (ErroNaLeituraException e) {
            System.out.println(e.getMessage());
            System.out.println("Caminho informado: " + e.getCaminho());
        }
    }

    public static void removerContato() {

        System.out.println("Escolha uma letra que deseja remover:");
        String entrada = s.next().toUpperCase();

        try {
            if (lista.buscarContatos(entrada.charAt(0)).size() > 0) {
                exibirContatos(entrada.charAt(0));
                System.out.println("Escolha um contato para remover:");
                int index = s.nextInt();
                if (index < lista.buscarContatos(entrada.charAt(0)).size()) {
                    lista.removeContato(lista.buscarContatos(entrada.charAt(0)).get(index));
                }
            } else {
                System.out.println("Não existem contatos para serem removidos");
            }
        } catch (ErroNaLeituraException e) {
            System.out.println(e.getMessage());
            System.out.println("Caminho informado: " + e.getCaminho());
        } catch (ErroNaGravacaoException e) {
            System.out.println(e.getMessage());
            System.out.println("Caminho informado: " + e.getCaminho());
        } catch (ContatoNaoCadastradoException e) {
            System.out.println(e.getMessage());
        }

    }
}