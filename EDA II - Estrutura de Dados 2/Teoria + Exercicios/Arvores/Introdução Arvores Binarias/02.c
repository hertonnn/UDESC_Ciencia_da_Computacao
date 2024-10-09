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

typedef struct elemento {
    struct elemento* proximo;
    struct elemento* anterior;
    struct no* valor;
} Elemento;

typedef struct fila {
    struct elemento* cabeca;
    struct elemento* cauda;    
} Fila;

typedef struct pilha {
    struct elemento* topo;
} Pilha;

Fila* criarFila() {
    Fila *fila = malloc(sizeof(Fila));
    fila->cabeca = NULL;
    fila->cauda = NULL;
  
    return fila;
}

void adicionarFila(Fila* fila, No* valor) {
    Elemento* elemento = malloc(sizeof(Elemento));
    
    elemento->valor = valor;
    elemento->proximo = NULL;

    if (fila->cauda != NULL) {
        fila->cauda->proximo = elemento;
        fila->cauda = elemento;
    } else {
        fila->cabeca = elemento;
        fila->cauda = elemento;
    }
}

No* removerFila(Fila* fila) {
    Elemento* cabeca = fila->cabeca;
    No* valor = cabeca->valor;

    fila->cabeca = cabeca->proximo;

    if (fila->cabeca == NULL) {
        fila->cauda = NULL;
    }

    free(cabeca);

    return valor;
}

int filaVazia(Fila* fila) {
    return fila->cabeca == NULL;
}

Pilha* criarPilha() {
    Pilha *pilha = malloc(sizeof(Pilha));
    pilha->topo = NULL;
  
    return pilha;
}

void adicionarPilha(Pilha* pilha, No* valor) {
    Elemento* elemento = malloc(sizeof(Elemento));
    
    elemento->valor = valor;
    elemento->anterior = pilha->topo;

    pilha->topo = elemento;
}

No* removerPilha(Pilha* pilha) {
    Elemento* topo = pilha->topo;
    No* valor = topo->valor;

    pilha->topo = topo->anterior;

    free(topo);

    return valor;
}

int pilhaVazia(Pilha* pilha) {
    return pilha->topo == NULL;
}

Arvore* criarArvore() {
    Arvore *arvore = malloc(sizeof(Arvore));
    arvore->raiz = NULL;
  
    return arvore;
}

int arvoreVazia(Arvore* arvore) {
    return arvore->raiz == NULL;
}

No* adicionarArvore(Arvore* arvore, No* pai, int valor) {
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

void removerArvore(Arvore* arvore, No* no) {
    if (no->esquerda != NULL) {
        removerArvore(arvore, no->esquerda); 
    }
  
    if (no->direita != NULL) {
        removerArvore(arvore, no->direita);
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

void percorrerLargura(No* no, void (callback)(int)) {
    Fila* fila = criarFila();
    adicionarFila(fila, no);
    
    while (!filaVazia(fila)) {
        No* no = removerFila(fila);

        if (no->esquerda != NULL) {
            adicionarFila(fila, no->esquerda);
        }

        if (no->direita != NULL) {
            adicionarFila(fila, no->direita);
        }

        callback(no->valor);
    }
}

void percorrerProfundidade(No* no, void (callback)(int)) {
    Pilha* pilha = criarPilha();
    adicionarPilha(pilha, no);
    
    while (!pilhaVazia(pilha)) {
        No* no = removerPilha(pilha);

        if (no->direita != NULL) {
            adicionarPilha(pilha, no->direita);
        }

        if (no->esquerda != NULL) {
            adicionarPilha(pilha, no->esquerda);
        }

        callback(no->valor);
    }
}

void visitar(int valor){
    printf("%d ", valor);
}

int main() {
    Arvore* a = criarArvore();

    No* no4 = adicionarArvore(a, NULL, 4);
    
    No* no2 = adicionarArvore(a, no4, 2);
    no4->esquerda = no2;

    No* no8 = adicionarArvore(a, no4, 8);
    no4->direita = no8;

    No* no1 = adicionarArvore(a, no2, 1);
    no2->esquerda = no1;

    No* no3 = adicionarArvore(a, no2, 3);
    no2->direita = no3;

    No* no6 = adicionarArvore(a, no8, 6);
    no8->esquerda = no6;

    No* no9 = adicionarArvore(a, no8, 9);
    no8->direita = no9;

    No* no5 = adicionarArvore(a, no6, 5);
    no6->esquerda = no5;

    No* no7 = adicionarArvore(a, no6, 7);
    no6->direita = no7;

    printf("Largura: ");
    percorrerLargura(a->raiz,visitar);
    printf("\n");

    printf("Profundidade (iterativa): ");
    percorrerProfundidade(a->raiz,visitar);
    printf("\n");
}