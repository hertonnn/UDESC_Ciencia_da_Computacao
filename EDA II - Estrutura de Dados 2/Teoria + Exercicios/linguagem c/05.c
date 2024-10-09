#include <stdio.h>
#include <stdlib.h>

/**
 * Considere uma escadaria com n degraus e você pode subir 1 ou 2 degraus por
 * vez. Dado n, retorne o número de maneiras únicas de subir a escada.
 * Exemplo, dado n = 4, existem 5 maneiras exclusivas
 * [1,1,1,1], [2,1,1], [1,2,1], [1,1,2], [2, 2]
 */	
int degrau(int n) {

    int resposta = 0, i;

    if (n <= 1)
        return n;

    for (i = 1; i <= 2 && i <= n; i++) {
        resposta = resposta + degrau(n - i);
    }
    
    return resposta;
}

int main () {
    degrau(4);

    return 0;
}
