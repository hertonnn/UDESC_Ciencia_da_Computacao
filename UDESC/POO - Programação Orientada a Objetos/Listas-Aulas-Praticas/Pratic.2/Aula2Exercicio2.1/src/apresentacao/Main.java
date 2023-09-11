package apresentacao;

import java.util.Scanner;
import negocio.SistemaPetShop;
import dados.Veterinario;

public class Main {
	Scanner scan = new Scanner(System.in);
	SistemaPetShop sistema = new SistemaPetShop(10);
	
	public SistemaPetShop getSistema() {
		return sistema;
	}
	
	public int menu() {
		
		System.out.println("Escolha uma opção:");
		System.out.println("1 - Cadastro de Veterinário");
		System.out.println("2 - Cadastro de animal");
		System.out.println("3 - Mostrar veterinários");
		System.out.println("4 - Mostrar os animais de um veterinário");
		System.out.println("0 - Para sair do programa");
		int opcao = Integer.valueOf(scan.nextLine());
		return opcao;
	}
	
	private void cadastrarVeterinario() {
		Veterinario veterinario = new Veterinario(5);
		System.out.println("Digite o nome:");
		veterinario.setNome(scan.nextLine());
		System.out.println("Digite o salário:");
		veterinario.setSalario(Float.valueOf(scan.nextLine()));
		sistema.cadastrarVeterinario(veterinario);
	}
	
	public void mostrarTodosVeterinario() {
		for(int i=0;i<sistema.getQuantVeterinarios();i++) {
			Veterinario vet = sistema.getVeterinarios()[i];
			System.out.println(vet+"\n");
		}
	}
	
	public static void main(String args[]) {
		int opcao;
		
		Main main = new Main();
		opcao = main.menu();
		while(opcao != 0 ) {
			switch (opcao) {
				case 1: main.cadastrarVeterinario();
						break;
				case 3: main.mostrarTodosVeterinario();
						break;
				default: 
					System.out.println("Opção Inválida");
			}
			opcao = main.menu();
		}
		
		
	}



	
	
}
