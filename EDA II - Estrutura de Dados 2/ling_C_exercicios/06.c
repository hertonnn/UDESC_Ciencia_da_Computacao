#include <stdio.h>
#include <stdlib.h>

/**
 * Dado um vetor de inteiros v com tamanho n e k com intervalo 1 ≤ k ≤ n,
 * calcule os valores máximos para cada subvetor de comprimento k gerado a
 * partir do vetor v.
 * Exemplo, dado v = [10,5,2,7,8,7] e k = 3, a saída esperada será [10,7,8,8],
 * visto que:
 *    i) 10 = max(10,5,2)
 *   ii)  7 = max(5,2,7)
 *  iii)  8 = max(2,7,8)
 *   iv)  8 = max(7,8,7)
 */

void imprime(int* v, int tamanho) {
    printf("%d [", tamanho);
		
    for (int i = 0; i < tamanho - 1; i++) {
        printf("%d,", v[i]);
    }
		
    if (tamanho > 0) {
        printf("%d", v[tamanho - 1]);
    }
		
    printf("]\n");
}

void subvetores(int* v, int tamanho, int k) {
    int total = tamanho - (k - 1);

    printf("tamanho %d\n", tamanho);
    
    if (total < 0 || total > tamanho) {
        total =  0;
    }
    
    int r[total];
    
    for (int i = 0; i < total; i++) {
        int max = v[i];
        
        for (int j = i + 1; j < i + k; j++) {
            if (v[j] > max) {
                max = v[j];
            }
        }
        
        r[i] = max;
    }

    printf("total %d\n", total);
    
    imprime(r, total);
}
	
int main() {
	int v[6]  = { 10, 5, 2, 7, 8, 7 };
    int k = 3;
    
    subvetores(v, 6, k);
}

