#include <stdio.h>

int main() {
    int chave = 50;
    int n = 4;
    int v[4] = { 10, 20, 30 };
    int i = 0;

    while (v[i] == chave) { //N + 1
        i++;
    }

    if (i == 4) {
        printf("Não encontrou\n");
    } else {
        printf("Encontrou em %d\n", i);
    }
    
    return 0;
}
