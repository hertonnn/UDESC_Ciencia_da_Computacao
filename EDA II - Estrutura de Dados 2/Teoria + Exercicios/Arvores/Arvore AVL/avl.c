#include <stdlib.h>
#include <stdio.h>

typedef struct no {
    struct no* pai;
    struct no* esquerda;
    struct no* direita;
    int valor;
    int altura;
} No;

typedef struct arvore {
    struct no* raiz;
} Arvore;

void balanceamento(Arvore*, No*);
int altura(No*);
int fb(No*);
No* rsd(Arvore*, No*);
No* rse(Arvore*, No*);
No* rdd(Arvore*, No*);
No* rde(Arvore*, No*);
No* criarNo(No*, int);

Arvore* criar() {
    Arvore *arvore = malloc(sizeof(Arvore));
    arvore->raiz = NULL;
  
    return arvore;
}

int vazia(Arvore* arvore) {
    return arvore->raiz == NULL;
}

No* adicionarNo(No* no, int valor) {
    if (valor > no->valor) {
        if (no->direita == NULL) {
            printf("Adicionando %d\n",valor);
            No* novo = criarNo(no, valor);
            no->direita = novo;
				
            return novo;
        } else {
            return adicionarNo(no->direita, valor);
        }
    } else {
        if (no->esquerda == NULL) {
            printf("Adicionando %d\n",valor);
            No* novo = criarNo(no, valor);
            no->esquerda = novo;
			
            return novo;
        } else {
            return adicionarNo(no->esquerda, valor);
        }
    }
}

No* adicionar(Arvore* arvore, int valor) {
    if (vazia(arvore)) {
        printf("Adicionando %d\n",valor);
        arvore->raiz = criarNo(NULL, valor);
			
        return arvore->raiz;
    } else {
        No* no = adicionarNo(arvore->raiz, valor);
        balanceamento(arvore, no->pai);
        return no;
    }
}

No* criarNo(No* pai, int valor) {
    No* no = malloc(sizeof(No));
    no->valor = valor;
    no->pai = pai;
    no->esquerda = NULL;
    no->direita = NULL;
    return no;
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

void percorrer(No* no, void (*callback)(int)) {
    if (no != NULL) {
        percorrer(no->esquerda,callback);
        callback(no->valor);
        percorrer(no->direita,callback);
    }
}

void visitar(int valor){
    printf("%d ", valor);
}

void balanceamento(Arvore* arvore, No* no) {
    while (no != NULL) {
        int fator = fb(no);
        
        no->altura = (no->esquerda->altura > no->direita->altura
			? no->esquerda->altura 
			: no->direita->altura) + 1;
				

        if (fator > 1) { // arvore mais profunda para a esquerda
            if (fb(no->esquerda) > 0) {
                printf("RSD(%d)\n",no->valor);
                rsd(arvore, no); 
            } else {
                printf("RDD(%d)\n",no->valor);
                rdd(arvore, no); 
            }
        } else if (fator < -1) { // arvore mais profunda a direita
            if (fb(no->direita) < 0) {
                printf("RSE(%d)\n",no->valor);
                rse(arvore, no); 
            } else {
                printf("RDE(%d)\n",no->valor);
                rde(arvore, no);
            }
        }

        no = no->pai; 
    }
}

int altura(No* no){
	if (no == NULL) {
		return 0;
	}
	
    int esquerda = altura(no->esquerda) + 1;
    int direita = altura(no->direita) + 1;

    return esquerda > direita ? esquerda : direita;
}

int fb(No* no) {
    return altura(no->esquerda) - altura(no->direita);
}

No* rse(Arvore* arvore, No* no) {
    No* pai = no->pai;
    No* direita = no->direita;

    if (direita->esquerda != NULL) {
        direita->esquerda->pai = no;
    } 
  
    no->direita = direita->esquerda;
    no->pai = direita;

    direita->esquerda = no;
    direita->pai = pai;

    if (pai == NULL) {
        arvore->raiz = direita;
    } else {
        if (pai->esquerda == no) {
            pai->esquerda = direita;
        } else {
            pai->direita = direita;
        }
    }

    return direita;
}

No* rsd(Arvore* arvore, No* no) {
    No* pai = no->pai;
    No* esquerda = no->esquerda;

    if (esquerda->direita != NULL) {
        esquerda->direita->pai = no;
    } 
  
    no->esquerda = esquerda->direita;
    no->pai = esquerda;
  
    esquerda->direita = no;
    esquerda->pai = pai;

    if (pai == NULL) {
        arvore->raiz = esquerda;
    } else {
        if (pai->esquerda == no) {
            pai->esquerda = esquerda;
        } else {
            pai->direita = esquerda;
        }
    }

    return esquerda;
}

No* rde(Arvore* arvore, No* no) {
    no->direita = rsd(arvore, no->direita);
    return rse(arvore, no);
}

No* rdd(Arvore* arvore, No* no) {
    no->esquerda = rse(arvore, no->esquerda);
    return rsd(arvore, no);
}

int main() {
    Arvore* a = criar();
    int n = 7;
    int i;

    adicionar(a, 2);
    adicionar(a, 5);
    adicionar(a, 1);
    adicionar(a, 0);
    adicionar(a, 6);
    adicionar(a, 4);
    adicionar(a, 3);
    

    printf("In-order: ");
    percorrer(a->raiz,visitar);
    printf("\n");
    printf("Altura da arvore: %d\n", altura(a->raiz));
}
