// Método de Ordenação BubleSort

/*
O Bubble Sort (Ordenação por Bolha) é um algoritmo de ordenação que funciona trocando repetidamente de posição, os elementos adjacentes em um arranjo que estão fora de ordem entre si. O algoritmo leva esse nome por “borbulhar” os maiores elementos para o final do arranjo.

O Bubble Sort apresenta um desempenho ruim, quando comparado à outros algoritmos de ordenação. No entanto, por ser de simples compreensão, é frequentemente usado para motivar o estudo de outros algoritmos de ordenação.
*/

#include <stdlib.h>
#include <stdio.h>
#include <time.h>

#define SIZE 100
#define RANGE 100000

int *arrayRandom();
void bubbleSort(int *array);

int main(){

    int *array = arrayRandom();

    printf("\nArray original: [\n");
    for(int i = 0; i < SIZE; i++){
        printf("%i ", array[i]);
    }
    printf("]\n");

    //Aplicando método
    bubbleSort(array);

    printf("\nArray ordenado por BubbleSort: [\n");
    for(int j = 0; j < SIZE; j++){
        printf("%i ", array[j]);
    }
    printf("]\n");

    free(array);

}

int *arrayRandom(){
    int *array = (int *) malloc(sizeof(int) * SIZE);

    srand(time(NULL));
    int numRandom;

    for(int i = 0; i < SIZE; i++){
        // Gerando valores mais ou menos entre [-50000, 50000]
        numRandom = (rand() % RANGE) - RANGE/2;
        array[i] = numRandom;
    }

    return array;
}

void bubbleSort(int *array){

    int comparisons = 0;
    int swaps = 0;

    for(int i = 1; i < SIZE; i++){
        for(int j = 0; j < (SIZE - 1); j++){
            if(array[j] > array[j + 1]){
                // troca
                int aux = array[j];
                array[j] = array[j + 1];
                array[j + 1] = aux;
                swaps++;
            }
            comparisons++;
        }
    }

    printf("\nNúmero de comparações: %i\n", comparisons);
    printf("Número de trocas: %i\n", swaps);
}