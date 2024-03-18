//PAG.77

#include <stdio.h>

int main(){

    enum cores {
        AZUL = 1,
        VERMELHO = 2 
    };

    typedef enum cores Cores;

    Cores cor = AZUL;
    printf("%i", cor);
}


