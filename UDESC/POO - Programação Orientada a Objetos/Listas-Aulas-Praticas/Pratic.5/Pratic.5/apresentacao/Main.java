package apresentacao;

import java.util.Scanner;

import dados.Contato;
import negocio.ListaTelefonica;

public class Main {

    private static ListaTelefonica tell = new ListaTelefonica();
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        int escolha = -1;

        while (escolha != 0) {

        System.out.println("Escolha uma opção:");
        System.out.println("(0) Sair");
        System.out.println("(1) Cadastrar um contato");
        System.out.println("(2) Remover um contato");
        System.out.println("(3) Mostrar todos os contatos");

        escolha = input.nextInt();

        switch (escolha) {
            case 0:
                break;
            case 1:
                Contato contato = novoContato();
                tell.addContato(contato);
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

    
    public static void exibirContatos() {
        
        tell.getContatos().forEach((chave, tell) -> {
            
            System.out.println(chave + ":");
            
            for (Contato contato : tell) {
                
                System.out.println("  " + contato.toString());
                
            }
            
        });
        
    }
    public static Contato novoContato() {

        System.out.println("Digite o nome do contato:");
        String nome = input.nextLine();
        nome = input.nextLine();

        System.out.println("Digite o telefone do contato:");
        int telefone = input.nextInt();

        Contato c = new Contato();
        c.setNome(nome);
        c.setTelefone(telefone);

        return c;
    }
    
    public static void exibirContatos(char letra) {

        for (int i = 0; i < tell.getContatos(letra).size(); i++) {

            System.out.println("Codigo: " + i);
            System.out.println(tell.getContatos(letra).get(i).toString());

        }

    }

    public static void removerContato() {

        System.out.println("Escolha uma letra que deseja remover:");

        String entrada = input.next().toUpperCase();

        if (tell.getContatos(entrada.charAt(0)).size() > 0) {

            exibirContatos(entrada.charAt(0));

            System.out.println("Escolha um contato para remover:");
            int index = input.nextInt();

            if (index < tell.getContatos(entrada.charAt(0)).size()) {

                tell.removeContato(tell.getContatos(entrada.charAt(0)).get(index));

            }

        } else {

            System.out.println("Não existem contatos para serem removidos");

        }

    }
}