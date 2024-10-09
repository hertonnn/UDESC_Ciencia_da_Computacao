
#include <stdio.h>
#include <stdlib.h>

/**
 * Dado um vetor de números inteiros v, encontre o primeiro inteiro positivo
 * ausente no vetor. Em outras palavras, deve ser retornado o menor inteiro
 * positivo que não existe no vetor. A matriz pode conter duplicados e números
 * negativos também. O algoritmo deve apresentar complexidade de espaço constante.
 * Exemplo 1, dado v = [3,4,-1,1], a saída esperada é 2
 * Exemplo 2, dado v = [1,2,0], a saída esperada é 3
 */
void ordena(int *v, int tamanho) {
    for (int i = 0; i < tamanho - 1; i++) {
        for (int j = i + 1; j < tamanho; j++) {
            if (v[i] > v[j]) {
                int aux = v[i];
                v[i] = v[j];
                v[j] = aux;
            }
        }
    }
}
	
int primeiro(int vetor[], int tamanho) {
    int p = 1;

    for(int i = 0; i < tamanho; i++) {
        if(vetor[i] > tamanho + 1){
            vetor[i] == -1;
        } else if (vetor[i] < 0) {

        }
    }
    
    ordena(vetor, tamanho);
		
    for (int i = 0; i < tamanho; i++) {
        if (vetor[i] < 0)
            continue;
        
        if (vetor[i] == p)
            p++;
    }
    
    return p;
}
	
int main() {
	int v1[] = {3,4,-1,1};	
	printf("%d\n",primeiro(v1,4));

    int v2[] = {1,2,0};
	printf("%d\n",primeiro(v2,3));
}
