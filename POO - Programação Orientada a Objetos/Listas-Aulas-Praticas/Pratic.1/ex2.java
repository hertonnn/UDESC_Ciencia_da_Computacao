import java.util.Scanner;

public class ex2 {
    public static void main(String[] args) {

        Scanner leitor = new Scanner(System.in);
        int[] idades = new int[3];
        String[] nome = new String[3];

        for(int i = 0; i < 3; i++){
            System.out.println("Digite o nome: ");
            nome[i] = leitor.nextLine();
        }
        for(int j = 0; j < 3; j++){
            System.out.println("Digite a idade dê: " + nome[j] + ": ");
            idades[j] = leitor.nextInt();
        }
        for(int j = 0; j < 3; j++){
            System.out.println(nome[j] + " possui " + idades[j] + " anos.");
        }
    }
}
