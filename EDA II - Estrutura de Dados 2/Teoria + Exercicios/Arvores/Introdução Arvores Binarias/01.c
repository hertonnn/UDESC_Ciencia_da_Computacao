#include <stdlib.h>
#include <stdio.h>

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
    Arvore *arvore = malloc(sizeof(Arvore));
    arvore->raiz = NULL;
  
    return arvore;
}

int vazia(Arvore* arvore) {
    return arvore->raiz == NULL;
}

No* adicionar(Arvore* arvore, No* pai, int valor) {
    No *no = malloc(sizeof(No));

    no->pai = pai;
    no->esquerda = NULL;
    no->direita = NULL;
    no->valor = valor;
    
    if (pai == NULL) {
        arvore->raiz = no;
    }

    return no;
}

void remover(Arvore* arvore, No* no) {
    if (no->esquerda != NULL) {
        remover(arvore, no->esquerda); 
    }
  
    if (no->direita != NULL) {
        remover(arvore, no->direita);
    }
  
    if (no->pai == NULL) {
        arvore->raiz = NULL;
    } else {
        if (no->pai->esquerda == no) {
            no->pai->esquerda = NULL;
        } else {
            no->pai->direita = NULL;
        }
    }

    free(no);
}

void percorrerProfundidadeInOrder(No* no, void (callback)(int)) {
    if (no != NULL) {
        percorrerProfundidadeInOrder(no->esquerda,callback);
        callback(no->valor);
        percorrerProfundidadeInOrder(no->direita,callback);
    }
}

void percorrerProfundidadePreOrder(No* no, void (callback)(int)) {
    if (no != NULL) {
        callback(no->valor);
        percorrerProfundidadePreOrder(no->esquerda,callback);
        percorrerProfundidadePreOrder(no->direita,callback);
    }
}

void percorrerProfundidadePosOrder(No* no, void (callback)(int)) {
    if (no != NULL) {
        percorrerProfundidadePosOrder(no->esquerda,callback);
        percorrerProfundidadePosOrder(no->direita,callback);
        callback(no->valor);
    }
}

void visitar(int valor){
    printf("%d ", valor);
}

int main() {
    Arvore* a = criar();

    No* no4 = adicionar(a, NULL, 4);
    
    No* no2 = adicionar(a, no4, 2);
    no4->esquerda = no2;

    No* no8 = adicionar(a, no4, 8);
    no4->direita = no8;

    No* no1 = adicionar(a, no2, 1);
    no2->esquerda = no1;

    No* no3 = adicionar(a, no2, 3);
    no2->direita = no3;

    No* no6 = adicionar(a, no8, 6);
    no8->esquerda = no6;

    No* no9 = adicionar(a, no8, 9);
    no8->direita = no9;

    No* no5 = adicionar(a, no6, 5);
    no6->esquerda = no5;

    No* no7 = adicionar(a, no6, 7);
    no6->direita = no7;

    printf("In-order: ");
    percorrerProfundidadeInOrder(a->raiz,visitar);
    printf("\nPre-order: ");
    percorrerProfundidadePreOrder(a->raiz,visitar);
    printf("\nPos-order: ");
    percorrerProfundidadePosOrder(a->raiz,visitar);
    printf("\n");
}