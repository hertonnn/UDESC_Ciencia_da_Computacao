//PAG.53

#include <stdlib.h>
#include <stdio.h>

int main(){
    // MALLOC() - A função basica para alocar é o malloc, recebe o num de bytes que deseja alocar e retorna endereço inicial

    int *v;

    v = malloc(10*sizeof(int));// v aponta para um endereço com 40 bytes disponiveis

    //Logo, isso é válido.

    v[0] = 4; 
    printf("%d\n", v[0]);

    // Se n tiver espaço na memória o retorno do malloc é NULL

    // FREE() - recebe o endereço da memória a ser liberada e o faz

    free(v);
    printf("%d", v[0]);// retorna um endereço lixo

    //Só  podemos  passar  para  a  função  free  um  endereço  de  memória  que  tenha  sido  alocado  dinamicamente.  Devemos  lembrar  ainda  que  não  podemos  acessar  o  espaço  na  memória  depois que o liberamos.
}