/*
Dado um vetor de números inteiros v de tamanho n e
um número k, retorne verdadeiro se a soma de 
qualquer par de números distintos em v for igual a k.
Exemplo: dado v = [10,15,3,7] e k = 17, a saída 
deve ser true, pois 10 + 7 é 17
*/
#include <stdio.h>

int compara(const void* a, const void* b) {
    return *(int *) a - *(int *) b;
}

int pesquisabinaria(int v[], int n, int chave) {
    int inicio = 0, fim = n - 1;

    while (inicio  <= fim) {
        int meio = (fim + inicio) / 2;

        if (chave == v[meio]) {
            return meio;
        } else if (chave > v[meio]) {
            inicio = meio + 1;
        } else {
            fim = meio - 1;
        }
    }

    return -1;
}

int main() {
    int v[] = {10,15,3,7};
    int n = 4;
    int k = 99;

    qsort(v, n, sizeof(int), compara); //  n * log(n)
    // 2n * log(n) => n * log(n)
    for (int i = 0; i < n; i++) { // n
        int vj = k -v[i];
        int j = pesquisabinaria(v, n, vj); // log(n)

        if (j != -1 && i != j) {
            printf("verdadeiro\n");
        }
    }

    printf("falso\n");
    return 0;
}