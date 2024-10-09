#include <stdio.h>
#include <stdlib.h>
#include <math.h>

/**
 * Powerset é um conjunto gerado a partir da combinação de todos seus subconjuntos.
 * Dado um conjunto v, retorne o powerset deste conjunto de entrada. 
 * Exemplo: dado v = [1,2,3], a saída deve ser: [], [1], [2], [3], [1,2], [1,3],
 * [2,3], [1,2,3]
 */

void powerset(int* v, int tamanho) {
    int total = pow(2, tamanho);
	int** m = malloc(sizeof(int*) * total);
		
    for (int i = 0; i < total; i++) {
        m[i] = malloc(sizeof(int) * tamanho);
    }
		
    for (int j = 0; j < tamanho; j++) {
        int c = 0;
        int b = 0;
        
        for (int i = 0; i < total; i++) {
            m[i][j] = b ? v[j] : -1;
            c++;
            
            if (c == (int) pow(2, j)) {
                c = 0;
                b = !b;
            }
        }
    }
    
    for (int i = 0; i < total; i++) {
        printf("[");
        
        int primeiro = 1;
        
        for (int j = 0; j < tamanho; j++) {
            if (m[i][j] >= 0) {
                if (!primeiro)
                    printf(",");
                
                printf("%d", m[i][j]);
                primeiro = 0;
            }
        }
        
        printf("]\n");
    }
}

int main() {
	int v[] = {1,2,3};
    
    powerset(v, 3);
}
