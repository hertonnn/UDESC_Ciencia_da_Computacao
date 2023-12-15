import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int fim = -1;
        Fabrica fabrica = new Fabrica();
        Scanner input = new Scanner(System.in);

        do {

            System.out.println(fabrica.fabricar().info());
            System.out.println("(0) - interromper a produção");
            System.out.println("(1) - para continuar");
            fim = input.nextInt();

        } while (fim != 0);
    }

}