package apresentacao;

import java.util.Scanner;

import dados.Animal;
import dados.Aquario;
import dados.Peixe;
import dados.Viveiro;
import negocio.Zoologico;

public class Principal {
	
    private static Zoologico zoo = new Zoologico();
    private static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {

        int opcao = -1;

        while (opcao != 0) {

            imprimeMenu();
            opcao = Integer.parseInt(s.nextLine());

            switch (opcao) {
                case 0:
                    break;
                case 1:
                    cadastrarViveiro();
                    break;
                case 2:
                    cadastrarAnimal();
                    break;
                case 3:
                    mostrarTodosViveiros();
                    break;
                case 4:
                    MostrarTodosAnimais();
                    break;
            }

        }

    }

    private static void mostrarTodosViveiros() {

        Viveiro[] viveiros = zoo.getViveiros();

        for(int i=0; i<zoo.getQuantViveiros(); i++) {

            System.out.println(viveiros[i]);

        }

    }

    private static void MostrarTodosAnimais() {

        Animal[] animais = zoo.getAnimais();

        for(int i=0; i<zoo.getQuantAnimais(); i++) {

            System.out.println(animais[i]);

        }

    }
    public static void imprimeMenu() {

        System.out.println("Escolha uma opção:");
        System.out.println("0 - Sair");
        System.out.println("1 - Cadastrar Viveiro");
        System.out.println("2 - Cadastrar Animal");
        System.out.println("3 - Mostrar Viveiros");
        System.out.println("4 - Mostrar Animais");

    }

    private static void cadastrarViveiro() {

        System.out.println("Digite o tipo de viveiro que deseja cadastrar:");
        System.out.println("1 - Viveiro");
        System.out.println("2 - Aquario");

        int escolha = Integer.parseInt(s.nextLine());

        switch (escolha) {
            case 1:
                zoo.cadastrarViveiro(novoViveiro());
                break;
            case 2:
                zoo.cadastrarViveiro(novoAquario());
                break;
            default:
                System.out.println("Escolha inválida!");
                break;
        }
    }

    private static Viveiro novoViveiro() {

        Viveiro viveiro = new Viveiro(10);

        System.out.println("Digite o nome:");
        viveiro.setNome(s.nextLine());
        System.out.println("Digite o comprimento:");
        viveiro.setComprimento(Float.parseFloat(s.nextLine()));
        System.out.println("Digite a largura:");
        viveiro.setLargura(Float.parseFloat(s.nextLine()));

        return viveiro;
    }

    private static Aquario novoAquario() {

    	Aquario aquirio = new Aquario(10);

        System.out.println("Digite o nome:");
        aquirio.setNome(s.nextLine());
        System.out.println("Digite o comprimento:");
        aquirio.setComprimento(Float.parseFloat(s.nextLine()));
        System.out.println("Digite a largura:");
        aquirio.setLargura(Float.parseFloat(s.nextLine()));
        System.out.println("Digite a altura:");
        aquirio.setAltura(Float.parseFloat(s.nextLine()));
        System.out.println("Digite a temperatura:");
        aquirio.setTemperatura(Float.parseFloat(s.nextLine()));
    	
        return aquirio;
    }


    private static void cadastrarAnimal() {

        System.out.println("Digite o tipo de animal que deseja cadastrar:");
        System.out.println("1 - Animal");
        System.out.println("2 - Peixe");

        int escolha = Integer.parseInt(s.nextLine());

        switch (escolha) {
            case 1:
                zoo.cadastrarAnimal(novoAnimal());
                break;
            case 2:
                zoo.cadastrarAnimal(novoPeixe());
                break;
            default:
                System.out.println("Escolha inválida!");
                break;
        }
    }
    
    private static Animal novoAnimal() {

        Animal animal = new Animal();

        System.out.println("Digite o nome:");
        animal.setNome(s.nextLine());
        System.out.println("Digite o cor:");
        animal.setCor(s.nextLine());
        System.out.println("Digite a especie:");
        animal.setEspecie(s.nextLine());
        System.out.println("Digite a idade:");
        animal.setIdade(Integer.parseInt(s.nextLine()));
        System.out.println("Digite a largura:");
        animal.setLargura(Float.parseFloat(s.nextLine()));
        System.out.println("Digite a comprimento:");
        animal.setComprimento(Float.parseFloat(s.nextLine()));
        System.out.println("Digite a altura:");
        animal.setAltura(Float.parseFloat(s.nextLine()));
        
        mostrarViveiros();
        System.out.println("Digite o número do viveiro:");
        int vi = Integer.parseInt(s.nextLine());
        while(vi >= zoo.getSoViveiros().length) {
        	System.out.println("Número inválido!");
        	System.out.println("Digite o número do viveiro:");
            vi = Integer.parseInt(s.nextLine());
        }
        while(!zoo.alocarAnimal(animal, zoo.getSoViveiros()[vi])) {
        	System.out.println("Não foi possível alocar nesse viveiro");
        	mostrarViveiros();
            System.out.println("Digite o número do viveiro:");
            vi = Integer.parseInt(s.nextLine());
            while(vi >= zoo.getSoViveiros().length) {
            	System.out.println("Número inválido!");
            	System.out.println("Digite o número do viveiro:");
                vi = Integer.parseInt(s.nextLine());
            }
        }
       
        return animal;
    }    
    
    public static void mostrarViveiros() {
    	 Viveiro viveiros[] = zoo.getSoViveiros();
    	 
    	 for (int i = 0; i < viveiros.length; i++) {
    		 System.out.println(i + " - " + viveiros[i].getNome());
		}
    }
   
    private static Peixe novoPeixe() {

        Peixe peixe = new Peixe();

        System.out.println("Digite o nome:");
        peixe.setNome(s.nextLine());
        System.out.println("Digite o cor:");
        peixe.setCor(s.nextLine());
        System.out.println("Digite a especie:");
        peixe.setEspecie(s.nextLine());
        System.out.println("Digite a idade:");
        peixe.setIdade(Integer.parseInt(s.nextLine()));
        System.out.println("Digite a largura:");
        peixe.setLargura(Float.parseFloat(s.nextLine()));
        System.out.println("Digite a comprimento:");
        peixe.setComprimento(Float.parseFloat(s.nextLine()));
        System.out.println("Digite a altura:");
        peixe.setAltura(Float.parseFloat(s.nextLine()));
        System.out.println("Digite a temperatura ideal:");
        peixe.setTemperaturaIdeal(Float.parseFloat(s.nextLine()));
        
        mostrarAquarios();
        System.out.println("Digite o número do aquario:");
        int aq = Integer.parseInt(s.nextLine());
        while(aq >= zoo.getSoAquarios().length) {
        	System.out.println("Número inválido!");
        	System.out.println("Digite o número do aquario:");
            aq = Integer.parseInt(s.nextLine());
        }
        while(!zoo.alocarAnimal(peixe, zoo.getSoAquarios()[aq])) {
        	System.out.println("Não foi possível alocar nesse aquario");
        	mostrarAquarios();
            System.out.println("Digite o número do aquario");
            aq = Integer.parseInt(s.nextLine());
            while(aq >= zoo.getSoAquarios().length) {
            	System.out.println("Número inválido!");
            	System.out.println("Digite o número do aquario:");
                aq = Integer.parseInt(s.nextLine());
            }
        }
       
        return peixe;
    }
    
     
    public static void mostrarAquarios() {
   	 Aquario aquarios[] = zoo.getSoAquarios();
   	 
   	 for (int i = 0; i < aquarios.length; i++) {
   		 System.out.println(i + " - " + aquarios[i].getNome());
		} 
   }
    
    
 

}
