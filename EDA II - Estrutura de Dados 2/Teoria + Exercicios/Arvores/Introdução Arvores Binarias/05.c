#include <stdio.h>
#include <stdlib.h>

typedef struct no {
    struct no* pai;      //ponteiro para o nó pai
    struct no* esquerda; //ponteiro para o nó filho a esquerda
    struct no* direita;  //ponteiro para o nó filho a direita
    int valor;           //conteúdo de um nó arbitrário da árvore
} No;

typedef struct arvore {
    struct no* raiz;
} Arvore;

Arvore* criar() {
    Arvore *a = (Arvore*) malloc(sizeof(Arvore));
    a->raiz = NULL;
    return a;
}

No* adicionar(Arvore* arvore, No* pai, int valor) {
    No *no = (No*) malloc(sizeof(No));

    no->pai = pai;
    no->valor = valor;

    no->esquerda = NULL;
    no->direita = NULL;
  
    if (pai == NULL) {
        arvore->raiz = no;
    }

    return no;
}

int unival(No *no, int *contador) {
    int esq = 1,dir = 1;
    
    if (no->esquerda != NULL) {
        esq = unival(no->esquerda, contador);

        if (no->esquerda->valor != no->valor)
            esq = 0;
    }

    if (no->direita != NULL) {
        dir = unival(no->direita, contador);

        if (no->direita->valor != no->valor)
            dir = 0;
    }

    if (esq && dir)
        (*contador)++;

    return esq && dir;
}

int main() {
    Arvore *a = criar();

    No *no1 = adicionar(a,NULL,0);
    
    No *no2 = adicionar(a,no1,1);
    No *no3 = adicionar(a,no1,1);
    
    no1->esquerda = no2;
    no1->direita = no3;

    No *no4 = adicionar(a,no3,1);
    No *no5 = adicionar(a,no3,1);

    no3->esquerda = no4;
    no3->direita = no5;

    No *no6 = adicionar(a,no4,1);
    No *no7 = adicionar(a,no4,1);

    no4->esquerda = no6;
    no4->direita = no7;

    int contador = 0;
    unival(no1, &contador);
    printf("Contador: %d\n", contador);
}
