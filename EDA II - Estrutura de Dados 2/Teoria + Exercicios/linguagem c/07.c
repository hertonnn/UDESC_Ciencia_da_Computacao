#include <stdio.h>
#include <stdlib.h>

/**
 * Dadas duas listas encadeadas acíclicas de inteiros que se cruzam em algum
 * ponto, localize o primeiro nó de interseção.
 * Exemplo, dado A = 3>7>8->10 e B = 99>1>8>10, a saída esperada será o valor 8
 */

typedef struct no {
    struct no* anterior;
	struct no* proximo;
		
	int valor;
} No;

typedef struct lista {
    No* cabeca;
	No* cauda;
} Lista;

Lista* cria() {
    return malloc(sizeof(Lista));
}

int vazia(Lista* l) {
    return l->cabeca == NULL;
}
		
void adiciona(Lista* l, int v) {
	No* no = malloc(sizeof(No));
	no->valor = v;
			
    if (l->cauda != NULL) {
        l->cauda->proximo = no;
    }
			
    no->anterior = l->cauda;
    no->proximo = NULL;
			
	l->cauda = no;
			
    if (l->cabeca == NULL) {
        l->cabeca = l->cauda;
    }
}

int main() {
    Lista* a = cria();
	Lista* b = cria();
		
	adiciona(a,3);
	adiciona(a,7);
	adiciona(a,8);
	adiciona(a,10);
		
	adiciona(b,99);
	adiciona(b,1);
	adiciona(b,8);
	adiciona(b,10);
		
	No* noa = a->cabeca;
		
	while (noa != NULL) {
        No* nob = b->cabeca;
        
        while (nob != NULL) {
            if (noa->valor == nob->valor) {
                printf("Interseção em %d\n",noa->valor);
                return 0;
            }
            
            nob = nob->proximo;
        }
        
        noa = noa->proximo;
    }
		
	printf("Sem interseção\n");
}
