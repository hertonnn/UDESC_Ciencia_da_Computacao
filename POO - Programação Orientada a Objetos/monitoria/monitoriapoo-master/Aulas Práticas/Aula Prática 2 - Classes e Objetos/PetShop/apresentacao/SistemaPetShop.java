package apresentacao;

import java.util.Scanner;

import dados.Animal;
import dados.Dono;
import dados.Endereco;
import dados.Veterinario;

public class SistemaPetShop {

    private static Veterinario[] veterinarios = new Veterinario[50];
    private static int numeroVeterinarios = 0;
    private static Scanner s = new Scanner(System.in);

    public static void imprimeMenu() {
        System.out.println("Escolha uma opção:");
        System.out.println("0 - Sair");
        System.out.println("1 - Cadastrar Veterinario");
        System.out.println("2 - Exibir Veterinarios");
        System.out.println("3 - Cadastrar Endereço do Veterinario");
        System.out.println("4 - Cadastrar Animal");
        System.out.println("5 - Exibir Animais");
        System.out.println("6 - Cadastrar Dono");
    }

    public static void main(String[] args) {

        int opcao = -1;

        while (opcao != 0) {

            imprimeMenu();
            opcao = s.nextInt();

            switch (opcao) {

            case 0:
                break;
            case 1:
                cadastrarVeterinario();
                break;
            case 2:
                mostrarVeterinarios();
                break;
            case 3:
                cadastrarEnderecoVeterinario();
                break;
            case 4:
                cadastrarAnimal();
                break;
            case 5:
                mostrarAnimais();
                break;
            case 6:
                cadastrarDono();
                break;
            default:
                System.out.println("Valor incorreto!");
                break;
            }
        }
    }

    public static void cadastrarVeterinario() {

        if (numeroVeterinarios < 50) {

            Veterinario v = new Veterinario();

            System.out.println("Digite o nome do veterinario:");

            String nome = s.nextLine();
            nome = s.nextLine();
            v.setNome(nome);

            System.out.println("Digite o salario do veterinario:");
            float salario = s.nextFloat();
            v.setSalario(salario);

            veterinarios[numeroVeterinarios] = v;
            numeroVeterinarios++;

        }

    }

    public static void mostrarVeterinarios() {

        for (int i = 0; i < numeroVeterinarios; i++) {
            System.out.println("Codigo: " + i);
            System.out.println(veterinarios[i].toString());
        }
    }

    public static Veterinario escolherVeterinario() {
        System.out.println("Escolha um veterinario para cadastrar um animal:");
        mostrarVeterinarios();

        int escolha = s.nextInt();

        if (escolha > numeroVeterinarios) {
            System.out.println("Número inválido");
            return null;
        } else {
            return veterinarios[escolha];
        }
    }

    public static void cadastrarEnderecoVeterinario() {

        Veterinario vet = escolherVeterinario();

        if (vet != null) {
            vet.setEndereco(cadastrarEndereco());
        }

    }

    public static Endereco cadastrarEndereco() {

        Endereco e = new Endereco();

        System.out.println("Digite o nome da rua:");

        String rua = s.nextLine();
        rua = s.nextLine();
        e.setRua(rua);

        System.out.println("Digite o número da casa:");
        e.setNumero(s.nextInt());

        System.out.println("Digite o nome do bairro:");

        String bairro = s.nextLine();
        bairro = s.nextLine();
        e.setBairro(bairro);

        System.out.println("Digite o nome da cidade:");

        String cidade = s.nextLine();

        e.setCidade(cidade);

        System.out.println("Digite o nome do estado:");

        String estado = s.nextLine();

        e.setEstado(estado);

        System.out.println("Digite o número do cep:");
        e.setCep(s.nextInt());

        return e;
    }

    public static Animal cadastrarAnimal() {

        Animal a = new Animal();

        System.out.println("Digite o nome do animal:");
        String nome = s.nextLine();
        nome = s.nextLine();
        a.setNome(nome);

        System.out.println("Digite a especie do animal:");
        String especie = s.nextLine();
        a.setEspecie(especie);

        System.out.println("Digite a descrição do animal:");
        String descricao = s.nextLine();

        a.setDescricao(descricao);

        return a;
    }

    public static void cadastrarAnimalVeterinario() {

        Veterinario vet = escolherVeterinario();

        if (vet != null) {

            vet.setAnimais(cadastrarAnimal());

        }

    }

    public static Dono cadastrarDono() {

        Dono d = new Dono();

        System.out.println("Digite o nome do dono:");
        String nome = s.nextLine();
        nome = s.nextLine();
        d.setNome(nome);

        System.out.println("Digite o cpf do dono:");
        d.setCpf(s.nextInt());

        d.setEndereco(cadastrarEndereco());

        return d;

    }

    public static void mostrarAnimais() {

        Veterinario vet = escolherVeterinario();

        if (vet != null) {
            for (int i = 0; i < vet.getQuantidadeAnimais(); i++) {
                System.out.println("Animal Atendido " + i);
                System.out.println(vet.getAnimais()[i].toString());
            }
        }
    }

    public static Animal escolherAnimais() {

        Veterinario vet = escolherVeterinario();

        if (vet != null) {
            for (int i = 0; i < vet.getQuantidadeAnimais(); i++) {
                System.out.println("Animal Atendido " + i);
                System.out.println(vet.getAnimais()[i].toString());
            }

            int escolha = s.nextInt();

            if (escolha > vet.getQuantidadeAnimais()) {
                System.out.println("Número inválido");
                return null;
            } else {
                return vet.getAnimais()[escolha];
            }
        }
        return null;
    }

    public static void cadastrarDonoAnimal() {

        Animal a = escolherAnimais();

        if (a != null) {

            a.setDono(cadastrarDono());

        }

    }

}