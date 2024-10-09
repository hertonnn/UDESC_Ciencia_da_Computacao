#include <stdlib.h>
#include <stdio.h>

typedef struct no {
    struct no* pai;
    struct no* esquerda;
    struct no* direita;
    int valor;
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

No* localizar(No* no, int valor) {
    if (no->valor == valor) {
        return no;
    } else {
        if (valor < no->valor) {
            if (no->esquerda != NULL) {
                return localizar(no->esquerda, valor);
            }
        } else {
            if (no->direita != NULL) {
                return localizar(no->direita, valor);
            }
        }
    }

    return NULL;
}

No* localizarIterativa(No* no, int valor) {
    while (no != NULL) {
        if (no->valor == valor) {
            return no;
        } else {
            no = valor < no->valor ? no->esquerda : no->direita;
        }
    }

    return NULL;
}

int caminho(No* no) {
    if (no->pai == NULL) {
        return 1;
    } else {
        return caminho(no->pai) + 1;
    }
}

int caminhoIterativo(No* no) {
    int c = 1;
    
    while (no != NULL) {
        c++;
        no = no->pai;
    }

    return c;
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

    int acesso = 100;

    No* no = localizar(a->raiz, acesso);

    if (no != NULL) {
        printf("Localizar %d: %d\n", acesso, caminho(no));
    }else{
        printf("Chave %d não localizada.\n", acesso);
    }
}