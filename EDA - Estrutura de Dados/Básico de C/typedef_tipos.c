//PAG: 71
#include <stdio.h>
int main(){
// A linguagem C permite criar nomes de tipos. Por exemplo, se escrevermos: 

    typedef float Real; 

    struct ponto1 {
        float a;
        float b;
    };

    //typedef struct ponto Ponto, *Ponto_para_struct; // crio um tipo ponto, e um tipo ponto para struct;

    // logo, isso é válido

    typedef struct ponto {
        float a;
        float b;
    }Ponto, *Ponteiro;

    Ponto novo_ponto;
    novo_ponto.a = 0.1;

    Ponteiro pponto; // nem precisa do *
    pponto = &novo_ponto;

    printf("%f", pponto->a);
}


