#include <stdio.h>
#include <stdlib.h>

int compara(const void* a, const void* b){
    return *(int*) a - *(int*) b;
}

int main(int argc, char const *argv[])
{
    int v[] = {1, 2, 0};
    int n = 3;

    qsort(v, n, sizeof(int), compara);

    for(int i = 0; i < n; i++) {
        if((v[i]+1 != v[i+1] && v[i] >= 0)) {
            printf("Numero faltando: %d", v[i]+1);
            break;
        }
    }

    return 0;
}
