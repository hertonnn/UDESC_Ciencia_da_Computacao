import java.util.Scanner;

public class Ex2 {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        String[] nomes = new String[3];
        int[] idades = new int[3];

        for (int i = 0; i < 3; i++) {

            nomes[i] = s.next();
            idades[i] = s.nextInt();
        }

        for (int i = 0; i < 3; i++) {

            System.out.println("Nome: " + nomes[i] + " - Idade: " + idades[i]);
        }

    }

}