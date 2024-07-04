// Método de Ordenação Merge Sort:

/*
Este algoritmo também usa o método “dividir para conquistar”, o qual divide o vetor em duas partes iguais e em seguida aplica o Merge sort nas duas sub-listas, ao final do processo o algoritmo junta as listas ordenadas numa lista única. O processo de mesclagem é realizado comparando-se os elementos da sub lista da esquerda e da sub lista da direita e copiando o menor elemento para uma lista temporária. Depois de mesclar as sub listas, a lista temporária é copiada de volta para a lista original. Possui complexidade O(n log n) em todos os casos.

Eu tive que aprender do zero esse algoritmo, então usei como referencia: https://www.geeksforgeeks.org/c-program-for-merge-sort/
*/

#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include <math.h>

#define SIZE 100
#define RANGE 100000

int *arrayRandom();
void mergeSort(int *array, int left, int right);
void merge(int *array, int left, int middle, int right);

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
    mergeSort(array, left, right);

    printf("\nArray ordenado por MergeSort: [\n");
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

void mergeSort(int *array, int left, int right){

    if(left < right){
        // o mesmo que (left + right) / 2, mas evita 
        // overflow para l e r grandes
        int middle = left + (right - left) / 2;

        // ordena e primeira e a segunda metade
        mergeSort(array, left, middle);
        mergeSort(array, middle + 1, right);

        //
        merge(array, left, middle, right);
    }
    
}

void merge(int *array, int left, int middle, int right){
    int i, j, k;

    int n1 = middle - left + 1;
    int n2 = right - middle;


    // Arrays auxiliares
    int arrayA[n1], arrayB[n2];


    // Primeiro arrayA recebe do inicio ao meio do array principal
    // e o arrayB recebe o resto
    for(i = 0; i < n1; i++){
        arrayA[i] = array[left + i];
    }
    for(j = 0; j < n2; j++){
        arrayB[j] = array[middle + j + 1];
    }


    //Resetando indexs para iniciar a comparação de valores
    i = 0;
    j = 0;
    // index inicial do primeiro subarray
    k = left;

    // Percorre os dois subarrays e pega sempre o menor valor e joga para 
    // o array principal
    while (i < n1 && j < n2)
    {
        if(arrayA[i] <= arrayB[j]){
            array[k] = arrayA[i];
            i++;
        }
        else{
            array[k] = arrayB[j];
            j++;
        }
        k++;

    }

    // copia elementos restantes
    while (i < n1) { 
        array[k] = arrayA[i]; 
        i++; 
        k++; 
    } 
  
    // copia elementos restantes
    while (j < n2) { 
        array[k] = arrayB[j]; 
        j++; 
        k++; 
    }
    
    
}
    // printf("\nNúmero de comparações: %i\n", comparisons);
    // printf("Número de trocas: %i\n", swaps);