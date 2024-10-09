#include <stdio.h>
#include <stdlib.h>

/**
 * Dado um vetor de números inteiros v, retorne um novo vetor de forma que
 * cada elemento no índice i seja o produto de todos os números na matriz
 * original, com exceção de i.
 * Exemplo 1: dado v = [1,2,3,4,5], a saída esperada é [120,60,40,30,24].
 * Exemplo 2: dado v = [3,2,1], a saída esperada é [2,3,6].
 *
 */
void multiplica(int vetor[], int retorno[], int tamanho) {
    int m = 1;

    for (int i = 0; i < tamanho; i++) {
        m *= vetor[i];
    }

    for (int i = 0; i < tamanho; i++) {
        retorno[i] = m / vetor[i];
    }
}
	
void imprime(int vetor[], int tamanho) {
	printf("[");
		
    for (int i = 0; i < tamanho - 1; i++) {
        printf("%d,", vetor[i]);
    }
		
    if (tamanho > 0) {
        printf("%d", vetor[tamanho - 1]);
    }
		
	printf("]\n");
}

int main() {
    int tamanho;

    int v1[] = {1,2,3,4,5};
    tamanho = sizeof(v1) / sizeof(v1[0]);
    int r1[tamanho];
    
    multiplica(v1,r1,tamanho);
    imprime(r1,tamanho);
    
    int v2[] = {3,2,1};
    tamanho = sizeof(v2) / sizeof(v2[0]);
    int r2[tamanho];

    multiplica(v2,r2,tamanho);
    imprime(r2,tamanho);
}
