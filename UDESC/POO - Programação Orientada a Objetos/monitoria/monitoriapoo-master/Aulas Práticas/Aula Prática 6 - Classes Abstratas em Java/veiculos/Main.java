import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int fim = -1;
        Fabrica fabrica = new Fabrica();
        Scanner s = new Scanner(System.in);

        do {

            System.out.println(fabrica.fabricar().info());
            System.out.println("Digite 0 para interromper a produção");
            System.out.println("Digite qualquer numero para continuar");
            fim = s.nextInt();

        } while (fim != 0);
    }

}