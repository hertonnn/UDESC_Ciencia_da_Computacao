#include <stdio.h>
#include <stdlib.h>

/*
int main(int argc, char const *argv[])
{
    int n = 4;
    int v[] = {10, 15, 3, 7};
    int k = 19;

    for(int i = 0; i < n - 1; i++) {
        for(int j = (i + 1); j < n; j++) {
            printf("%d %d \n", v[i], v[j]);
            if((v[i] + v[j] == k)) {
                printf("Combinacao encontrada! %d + %d\n", v[i], v[j]);
                exit(0);
            }
        }
    }
    printf("Combinacao nao encontrada");
    return 0;
}
*/

// Forma com complexidade menor
int pesquisabinaria(int v[], int n, int chave){
        int inicio = 0, fim = n - 1;

        while(inicio <= fim){
            int meio = (inicio + fim)/2;

            if(chave == v[meio]){
                return meio;
            }else if(chave > v[meio]){
                inicio = meio + 1;
            }else{
                fim = meio - 1;
            }
        }
        return -1;
}
int compara(const void* a, const void* b){
    return *(int*) a - *(int*) b;
}
int main(int argc, char const *argv[])
{
    int n = 4;
    int v[] = {10, 15, 3, 7};
    int k = 99;
    
    qsort(v, n, sizeof(int), compara); // n log n

    for(int i = 0; i < n - 1; i++) { // n
        int vj = k - v[i];
        int j = pesquisabinaria(v, n, vj);
        if(j != -1 && i != j){
            printf("Verdadeiro!");
            return 1;
        }
    }
    printf("Falso");
    return 0;
}
