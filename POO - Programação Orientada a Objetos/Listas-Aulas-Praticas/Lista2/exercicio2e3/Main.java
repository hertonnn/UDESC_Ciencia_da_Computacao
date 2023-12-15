package exercicio2e3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import exercicio2e3.Matriz.Quadrante;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Matriz matriz = new Matriz(5, 5);

        // Preenchendo com valores
        for(int i = 0; i < matriz.getN(); i++){
            for(int j = 0; j < matriz.getM(); j++){
                T numT = new T(i);
                System.out.println("Digite o elemento de posição " + i + "x" + j);
                int num = Integer.parseInt(input.nextLine());
                numT.setNumeroPosicao(num);
                matriz.set(numT, i, j);
            }
        }
        // Printando matriz de T com valores
        for(int i = 0; i < matriz.getN(); i++){
            for(int j = 0; j < matriz.getM(); j++){
                System.out.print(" " + matriz.get(i, j).getNumeroPosicao() + " ");
            }
            System.out.print("\n");
        }
        // teste  getLinha
        System.out.print("Linha 4: ");
        for(T objeto: matriz.getLinha(4)){
            System.out.print("-");
            System.out.print(objeto.getNumeroPosicao());
        }
        System.out.print("\n");

        // teste  getColuna
        System.out.print("Coluna 4: ");
        for(T objeto: matriz.getColuna(4)){
            System.out.print("-");
            System.out.print(objeto.getNumeroPosicao());
        }
        System.out.print("\n");
        // teste getElementosQuadrante
        System.out.print("Quadrante quarto: ");
        for(T objeto: matriz.getElementosQuadrante(Quadrante.QUARTO)){
            System.out.print("-");
            System.out.print(objeto.getNumeroPosicao());
        }
        // teste maior elemento (IMPLEMENTADO DA MANEIRA MAIS PÍFIA POSSÍVEL)
        System.out.print("\n");
        List<T> valores;
        ArrayList<Integer> menores =  new ArrayList<>();
        for(Quadrante quadrante : Quadrante.values()){
            valores = matriz.getElementosQuadrante(quadrante);
            // isso graças ao método toCompare na classe T
            Collections.sort(valores);
            menores.add(valores.get(0).getNumeroPosicao());
            
        }
        Collections.sort(menores);
        System.out.println("O menor elemento é:" + menores.get(0));

        

    }
}
