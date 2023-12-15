package apresentacao;

import java.util.Scanner;

import dados.Cidade;
import dados.Cliente;
import dados.Reserva;
import negocio.ReservaPassagem;

public class Principal {

    private static Scanner s = new Scanner(System.in);

    private static ReservaPassagem sistema = new ReservaPassagem();

    public static void main(String[] args) {
        menuPrincipal();
    }

    public static void exibeMenuPrincipal() {
        System.out.println("Escolha uma opção:");
        System.out.println("0 - Encerrar");
        System.out.println("1 - Realizar Reserva");
        System.out.println("2 - Cadastrar Cliente");
        System.out.println("3 - Mostrar Reservas");
        System.out.println("4 - Cadastrar Cidade");
    }

    public static void menuPrincipal() {
        int opcao = -1;
        while (opcao != 0) {
            exibeMenuPrincipal();
            opcao = s.nextInt();
            switch (opcao) {
            case 1:
                System.out.println("Realizar Reserva");
                realizarReserva();
                break;
            case 2:
                System.out.println("Cadastrar Cliente");
                cadastrarCliente();
                break;
            case 3:
                System.out.println("Mostrar Reservas");
                mostrarReservas();
                break;
            case 4:
                System.out.println("Cadastrar Cidade");
                cadastrarCidade();
                break;
            default:
                System.out.println("Número inválido");
                break;
            }
        }
    }

    public static void realizarReserva() {

        Cliente c = escolherCliente();

        if (c != null) {

            int opcao = -1;

            while (opcao != 0) {

                System.out.println("Digite 0 para Sair");
                System.out.println("Digite 1 para reservar somente a Ida");
                System.out.println("Digite 2 para reservar Ida e Volta");
                opcao = s.nextInt();

                switch (opcao) {
                case 1:
                    Reserva r = novaReserva();
                    sistema.reservarIda(c, r);
                    break;
                case 2:
                    Reserva r1 = novaReserva();
                    Reserva volta = novaReserva();
                    sistema.reservarVolta(c, r1, volta);
                    break;
                default:
                    System.out.println("Numero invalido");
                    break;
                }

            }

        }

    }

    public static Reserva novaReserva() {

        System.out.println("Digite a data do voo:");
        String data = s.nextLine();
        data = s.nextLine();

        System.out.println("Digite a hora do voo:");
        String hora = s.nextLine();

        System.out.println("Digite o preço do voo:");
        float preco = s.nextFloat();

        System.out.println("Digite a classe do voo:");
        String classe = s.nextLine();
        classe = s.nextLine();

        System.out.println("Digite a poltrona no voo:");
        int poltrona = s.nextInt();

        Cidade origem = escolherCidade();
        Cidade destino = escolherCidade();

        Reserva r = new Reserva();
        r.setDataVoo(data);
        r.setHoraVoo(hora);
        r.setPreco(preco);
        r.setClasseVoo(classe);
        r.setPoltrona(poltrona);
        r.setOrigem(origem);
        r.setDestino(destino);

        return r;

    }

    public static Cliente novoCliente() {

        System.out.println("Digite o cpf:");
        int cpf = s.nextInt();

        System.out.println("Digite o nome:");
        String nome = s.nextLine();
        nome = s.nextLine();

        System.out.println("Digite o endereço:");
        String endereco = s.nextLine();

        System.out.println("Digite o telefone:");
        int telefone = s.nextInt();

        Cliente c = new Cliente();

        c.setCpf(cpf);
        c.setNome(nome);
        c.setEndereco(endereco);
        c.setTelefone(telefone);

        return c;
    }

    public static void cadastrarCliente() {
        sistema.cadastrarCliente(novoCliente());
    }

    public static void mostrarClientes() {
        for (Cliente c : sistema.mostrarClientes()) {
            System.out.println(c.toString());
        }
    }

    public static Cliente escolherCliente() {

        mostrarClientes();
        System.out.println("Digite o CPF do cliente escolhido:");

        Cliente c = sistema.buscarCliente(s.nextInt());

        if (c != null) {
            return c;
        }

        return null;
    }

    public static Cidade novaCidade() {

        System.out.println("Digite o nome da cidade:");
        String nome = s.nextLine();
        nome = s.nextLine();

        System.out.println("Digite o estado da cidade");
        String estado = s.nextLine();

        Cidade c = new Cidade();
        c.setNome(nome);
        c.setEstado(estado);

        return c;

    }

    public static void cadastrarCidade() {

        sistema.cadastrarCidade(novaCidade());

    }

    public static void mostrarCidades() {
        for (int i = 0; i < sistema.mostrarCidades().size(); i++) {
            System.out.println("Cidade " + i);
            System.out.println(sistema.mostrarCidades().get(i).toString());
        }
    }

    public static Cidade escolherCidade() {

        mostrarCidades();
        System.out.println("Escolha uma cidade:");
        int codigo = s.nextInt();

        if (codigo > sistema.mostrarCidades().size()) {
            System.out.println("Cidade inválida");
            return null;
        } else {
            return sistema.mostrarCidades().get(codigo);
        }

    }

    public static void mostrarReservas() {

        Cliente c = escolherCliente();

        for (Reserva r : sistema.mostrarReservas(c.getCpf())) {
            System.out.println(r.toString());
        }

    }

}