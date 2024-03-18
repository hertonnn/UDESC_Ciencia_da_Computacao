//PAG: 68

#include <math.h>
#include <stdio.h>

struct ponto{ // criando
    float x;
    float y;
};
//Exercicio structs

float distancia(struct ponto *a, struct ponto *b){

    float d = sqrt(pow((a->x) - (b->x), 2) + pow((a->y) - (b->y), 2)); 

    return d;
}

void imprime (struct ponto p) {    
    printf("\nO ponto fornecido foi: (%.2f,%.2f)\n", p.x, p.y); 
} 
void imprime2(struct ponto *p) {    
    printf("O ponto fornecido foi: (%.2f,%.2f)\n", p->x, p->y); 
} 

int main(){

    struct ponto p; // declarando

    //acessando elementos

    p.x = 10.0;
    p.y = 5.0;

    //acesso ao endereço

    float *ponteiro;
    ponteiro = &p.x;

    printf("%3.2f", *ponteiro);

    //Ponteiros para Estruturas

    struct ponto *pp;
    struct ponto ponto2;
    
    //(*pp).x = 12.0;

    //Neste   caso,   os   parênteses   são   indispensáveis,   pois   o   operador   “conteúdo   de”   tem   precedência  menor  que  o  operador  de  acesso.

    pp = &ponto2;

    //Mas tb pode-se usar o operador de acesso:
    pp->x = 12.0;
    pp->y = 10.0;

   printf("\n%3.2f", ponto2.x);
   printf("\n%3.2f", ponto2.y);

   //acessando o endereço um campo com o ponteiro
   //printf("\n%3.2p", &(pp->x));
   //acessando o valor de um campo
   printf("\n%3.2f", pp->x);

   //Passagem de estruturas para funções
    imprime(ponto2);
    // Com ponteiros para estruturas
    imprime2(pp);

    //Testando função distancia

    struct ponto a;
    struct ponto b;

    a.x = 4.2;
    a.y = 5.0;

    b.x = 2.0;
    b.y = 2.1;

    float d = distancia(&a,&b);

    printf("%3.2f", d);

    // Alocação dinâmica de estruturas
    //Da  mesma  forma  que  os  vetores,  as  estruturas  podem  ser  alocadas  dinamicamente.  Por  exemplo, é válido escrever: 

    struct ponto *pontinho;
    pontinho = (struct ponto*) malloc(sizeof(struct ponto));

    

}
