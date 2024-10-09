#include <stdio.h>

int main() {
    int chave = 50;
    int v[] = { 10, 20, 30 };
    int n = 3;
    int i;

    for (i = 0; i < n; i++) { //N + 1
        if (v[i] == chave) { //N
            printf("Encontrou em %d\n", i);
            return 0;
        }
    }

    printf("Não encontrou\n");
    return 0;
}
