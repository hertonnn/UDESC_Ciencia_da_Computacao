package apresentacao;

import java.util.Random;
import java.util.Scanner;

import dados.CalculadoraComplexos;
import dados.CalculadoraInteiros;
import dados.Complexo;

public class Main {

    public static Scanner s = new Scanner(System.in);
    public static Random r = new Random();

    public static CalculadoraInteiros c1 = new CalculadoraInteiros();
    public static CalculadoraComplexos c2 = new CalculadoraComplexos();

    public static void main(String[] args) {

        do {

            if (r.nextInt(2) == 1) {

                Integer v1 = r.nextInt(100);
                Integer v2 = r.nextInt(100);

                if (r.nextInt(2) == 1) {

                    System.out.println(v1 + " + " + v2 + " = " + c1.soma(v1, v2));

                } else {

                    System.out.println(v1 + " - " + v2 + " = " + c1.subtracao(v1, v2));

                }

            } else {

                Complexo a = new Complexo();
                Complexo b = new Complexo();

                if (r.nextInt(2) == 1) {

                    System.out.println(a + " + " + b + " = " + c2.soma(a, b));

                } else {

                    System.out.println(a + " - " + b + " = " + c2.subtracao(a, b));

                }

            }

            System.out.println("Digite 0 para PARAR");

        } while (s.nextInt() != 0);

    }

}