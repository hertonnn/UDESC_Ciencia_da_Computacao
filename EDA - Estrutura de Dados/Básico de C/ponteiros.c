// O  operador  unário  &(“endereço de”), aplicado a variáveis, resulta no endereço da posição da memória reservada para a variável. 

//O operador unário * (“conteúdo de”), aplicado a variáveis do tipo ponteiro, acessa  o  conteúdo  do  endereço  de  memória  armazenado  pela  variável  ponteiro. 

#include <stdio.h>


// funcão que troca os valores por meio de ponteitos

void troca(int *px, int *py){

    int temp;

    temp = *px;
    *px = *py;
    *py = temp;
}
int main(){
    int a;
    a = 5;

    int *p; 
    p = &a;

    printf("Conteudo de a: %d \nEndereço de a: %d", *p, p);

    // para mudar o conteudo de a

    *p = 6;

    printf("\nConteudo de a: %d \nEndereço de a: %d", *p, p);

    // uso da funcão troca

    int x = 10;
    int y = 20;
    
    //troca(&x, &y); // passo só os endereços de memória

    printf("\n%d\n%d", x,y);

    return 0;
}




