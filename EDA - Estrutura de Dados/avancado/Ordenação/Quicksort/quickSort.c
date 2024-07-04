// Método de Ordenação Quick Sort:

/*
Um dos métodos mais utilizados e também um dos mais rápidos para as mais variadas situações. Ele é baseado no termo “dividir para conquistar”, o qual divide o vetor em duas partes, baseando — se em um elemento “pivô”, os elementos menores que o pivô ficam de um lado e os maiores de outro, logo, o procedimento é feito na lista toda tornando ela única novamente. A complexidade de tempo do deste algoritmo depende da escolha do pivô e da distribuição dos elementos no array, podendo possuir complexidade de tempo média O(n log n) e complexidade de tempo no pior caso O(n²).
*/

#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include <math.h>

#define SIZE 100
#define RANGE 100000
int comparisons = 0;
int swaps = 0;

int *arrayRandom();
void quickSort(int *array, int left, int right);
int partition(int *array, int left, int right);

int main(){

    int *array = arrayRandom();

    printf("\nArray original: [\n");
    for(int i = 0; i < SIZE; i++){
        printf("%i ", array[i]);
    }
    printf("]\n");

    //Aplicando método
    int left = 0;
    int right = SIZE - 1;
    quickSort(array, left, right);
    printf("\nNúmero de comparações: %i\n", comparisons);
    printf("Número de trocas: %i\n", swaps);

    printf("\nArray ordenado por QuickSort: [\n");
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

void quickSort(int *array, int left, int right){

    if(left < right){
        int index_pivot = partition(array, left, right);
        quickSort(array, left, index_pivot - 1);
        quickSort(array, index_pivot + 1, right);
    }
}

int partition(int *array, int left, int right){
    int pivot = array[left];

    int j = left;

    for(int i = left + 1; i <= right; i++){
        if(array[i] <= pivot){
            j+=1;
            int aux = array[j];
            array[j] = array[i];
            array[i] = aux;
            swaps++;
        }
        comparisons++;
    }

    int aux = array[j];
    array[j] = array[left];
    array[left] = aux;
    swaps++;

    return j;
}