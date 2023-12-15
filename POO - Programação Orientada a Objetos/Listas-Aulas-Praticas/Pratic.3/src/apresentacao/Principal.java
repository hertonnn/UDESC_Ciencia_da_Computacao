package apresentacao;

import negocio.ReservaPassagem;
import dados.Cidade;
import dados.Cliente;
import dados.Reserva;
import java.util.Scanner;

import apresentacao.Principal;

public class Principal {
    
    Scanner input;
    ReservaPassagem sistema;
    

    public Principal(){
        this.input = new Scanner(System.in);
        this.sistema = new ReservaPassagem(); // entrada com um valor talvez
    }
    public int menu(){
      System.out.println("\nEscolha uma op\u00e7\u00e3o:");
      System.out.println("1 - Reservar passagem(ida)");
      System.out.println("2 - Reservar passagem(ida e volta)");
      System.out.println("3 - Cadastrar cliente");
      //System.out.println("3 - Cadastrar cidade");
      System.out.println("4 - Mostrar reservas de cliente");
      System.out.println("5 - Mostrar clientes");
      System.out.println("6 - Mostrar cidades");
      System.out.println("0 - Para sair do programa");
      try{
        int opcao = Integer.parseInt(input.nextLine());
        return opcao;
      }
      catch(NumberFormatException e){
        System.out.println("Erro " + e);
        return 20;
      }
    }
    public boolean fazerReserva(boolean idaEvolta){

        System.out.println("Para começar, digite seu cpf: ");
        String cpf = this.input.nextLine();
        
        if(this.sistema.validaCpf(cpf)){
            System.out.println("\nOlá, " + sistema.getCliente(cpf).getNome() + ". Digite as informações do Voo, por favor.\n");
            
            Cidade origem = new Cidade();
            System.out.println("Para onde voc\u00EA vai?\n");
            System.out.println("Estado: ");
            origem.setEstado(this.input.nextLine());
            System.out.println("Cidade: ");
            origem.setNome(this.input.nextLine());
            this.sistema.cadastrarCidade(origem);

            Cidade destino = new Cidade();
            System.out.println("De onde voc\u00EA est\u00E1 saindo?:");
            System.out.println("Estado: ");
            destino.setEstado(this.input.nextLine());
            System.out.println("Cidade: ");
            destino.setNome(this.input.nextLine());
            this.sistema.cadastrarCidade(destino);
            
            Reserva ida = new Reserva();
            ida.setIdaEvolta(idaEvolta);
            System.out.println("Digite a data que pretende ir: ");
            ida.setDataVoo(this.input.nextLine());
            System.out.println("Digite a hora que pretende ir: ");
            ida.setHoraVoo(this.input.nextLine());
            System.out.println("Digite a classe do voo: ");
            ida.setClasseVoo(this.input.nextLine());
            System.out.println("Escolha a poltrona: ");
            ida.setPoltrona(Integer.parseInt(this.input.nextLine()));
            
            ida.setCidadeIda(origem);
            ida.setCidadeVolta(destino);
            
            this.sistema.reservarIda(sistema.getCliente(cpf), ida);
            System.out.println("Passagem reservada com sucesso!");
            
            System.out.println("\n---Informações da Viagem--- " + ida.toString());

            
            if(idaEvolta){
                System.out.println("\nAgora digite as informações da sua volta:\n");

                Reserva volta = new Reserva();
                volta.setIdaEvolta(idaEvolta);
                System.out.println("Digite a data que pretende voltar: ");
                volta.setDataVoo(this.input.nextLine());
                System.out.println("Digite a hora que pretende voltar: ");
                volta.setHoraVoo(this.input.nextLine());
                System.out.println("Digite a classe do voo: ");
                volta.setClasseVoo(this.input.nextLine());
                System.out.println("Escolha a poltrona: ");
                volta.setPoltrona(Integer.parseInt(this.input.nextLine()));

                volta.setCidadeIda(destino);
                volta.setCidadeVolta(origem);

                System.out.println("\n---Informa\u00E7\u00F5es da Viagem--- " + volta.toString());


                this.sistema.reservarVolta(sistema.getCliente(cpf), ida, volta);
                System.out.println("Passagens reservadas com sucesso!");
                return true;

            }
            return true;
            }
            else{
                System.out.println("\nVocê não possui cadastro.\nRedirecionando para a tela inicial para efetuar seu cadastro....\n");
                return false;
                
            }


    }
    public boolean cadastrarCliente(){

        Cliente cliente = new Cliente();

        System.out.println("Digite seu nome:");
        cliente.setNome(this.input.nextLine());
        System.out.println("Digite seu cpf");
        cliente.setCpf(this.input.nextLine());
        System.out.println("Digite seu endereco:");
        cliente.setEndereco(this.input.nextLine());
        System.out.println("Digite seu telefone:");
        cliente.setTelefone(this.input.nextLine());
        System.out.println("\n---Informações de Cadastro--- " + cliente.toString());

        this.sistema.cadastrarCliente(cliente);
        System.out.println("\nCadastro realizado com sucesso.\nBem-vindo(a) " + cliente.getNome() + "!");

        return true;
    }
    public void cadastrarCidade(){

    }
    public void mostrarClientes(){
        int quantClientes = this.sistema.getQuantClient();
        System.out.println("Esses são os clientes cadastrados: \n");
        if(quantClientes == 0){
            System.out.println("Sem clientes cadastrados. \n");
        }
        for(int i = 0; i < quantClientes; i++){
            System.out.println(this.sistema.mostrarClientes()[i].getNome() + "\n");
        }
       }
    public void mostrarCidades(){
        int quantCidades = this.sistema.getQuantCidade();
        System.out.println("Esses são os Cidades cadastradas: \n");
        if(quantCidades == 0){
            System.out.println("Sem cidades cadastradas. \n");
        }
        for(int i = 0; i < quantCidades; i++){
            System.out.println(this.sistema.mostrarCidades()[i].getNome() + "\n");
       }
    }
    public void mostrarReservas(){
        System.out.println("Digite seu Cpf:");
        String cpf = this.input.nextLine();
        if(this.sistema.validaCpf(cpf)){
            int tamReservas = this.sistema.getCliente(cpf).getQuantReservas();
            if(tamReservas == 0){
                System.out.println("Você não possui reservas.");
            }
            else{

                for(int i = 0; i < tamReservas;i++){
                    System.out.println("\n======== " + (i+1) + " =========\n");
                    System.out.println(this.sistema.mostrarReservas(cpf)[i]);
                }
            }
        }
        else{
            System.out.println("Cliente inválido.");
        }
    }

    public static void main(String[] args) {
        Principal main = new Principal();
  
        for(int opcao = main.menu(); opcao != 0; opcao = main.menu()) {
           switch (opcao) {
                case 1:
                    main.fazerReserva(false);
                    break;
                case 2:
                    main.fazerReserva(true);
                    break;
                case 3:
                    main.cadastrarCliente();
                    break;
                case 4:
                    main.mostrarReservas();
                    break;
                case 5:
                    main.mostrarClientes();
                    break;
                case 6:
                    main.mostrarCidades();
                    break;
                default:
                    System.out.println("Op\u00e7\u00e3o Inv\u00e1lida");
                    break;
           }
        }
  
     }

}
