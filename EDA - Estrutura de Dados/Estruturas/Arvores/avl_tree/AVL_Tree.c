#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct nodoAVL {
  int info;
  struct nodoAVL *esq;
  struct nodoAVL *dir;
  int alt;
} nodoAVL;

int max(int a, int b);
int altura(nodoAVL *subarvore);
nodoAVL *simp_roda_esq(nodoAVL *k2);
nodoAVL *simp_roda_dir(nodoAVL *k2);
nodoAVL *dup_roda_esq(nodoAVL *k3);
nodoAVL *dup_roda_dir(nodoAVL *k3);
nodoAVL *insereAVL(int info, nodoAVL *arv);
nodoAVL * removeNode(nodoAVL* head, int info);
nodoAVL * pesquisaNode(nodoAVL* head, int info);
void imprimir(nodoAVL *raiz, int nivel);

int main() {   
    nodoAVL *root = NULL;

    root = insereAVL(20, root);
    root = insereAVL(30, root);
    root = insereAVL(40, root);
    root = insereAVL(10, root);
    root = insereAVL(0, root);
    root = insereAVL(-10, root);
    root = insereAVL(-20, root);
    root = insereAVL(-30, root);

    imprimir(root, 0);

    removeNode(root, -30);
    printf("\nExcluindo -30\n");
    printf("\n---------------------\n");

    imprimir(root, 0);

    printf("\n O nó: |%i| foi achado ", pesquisaNode(root, 40)->info);    
    return 0;
}
nodoAVL * pesquisaNode(nodoAVL* head, int info){
    if(head == NULL) return NULL;
    if(head->info == info){
        return head;
    }
    else if(head->info > info){
        pesquisaNode(head->esq, info);
    }else{
        pesquisaNode(head->dir, info);
    }
}

nodoAVL * removeNode(nodoAVL* head, int info){
    if(head == NULL) return NULL;
    if(info < head->info){
        head->esq = removeNode(head->esq, info);
    }else if(info > head->info){
        head->dir = removeNode(head->dir, info);
    }else{
        nodoAVL * r = head->dir;
        if(head -> dir ==NULL){
            nodoAVL * l = head->esq;
            free(head);
            head = l;
        }else if(head->esq == NULL){
            free(head);
            head = r;
        }else{
            while(r->esq != NULL) r = r->esq;
            head->info = r->info;
            head->dir = removeNode(head->dir, r->info);
        }
    }
    if(head==NULL) return head;
    head->alt = 1 + max(altura(head->esq), altura(head->dir));

    int bal = altura(head->esq) - altura(head->dir);
    if(bal > 1){
        if(altura(head->esq) >= altura(head->dir)){
                    return simp_roda_esq(head);
                }else{
                    head->esq = simp_roda_esq(head->esq);
                    return simp_roda_dir(head);
                }
    }else if(bal < -1){
        if(altura(head->dir) >= altura(head->esq)){
                    return simp_roda_esq(head);
                }else{
                    head->dir = simp_roda_dir(head->dir);
                    return simp_roda_esq(head);
                }
    }
    return head;
}

int max(int a, int b) {
    return (a > b) ? a : b;
}

int altura(nodoAVL *subarvore) {
    if (subarvore == NULL)
        return -1;  /* A altura de uma subarvore inexistente foi definida como -1 */
    else
        return subarvore->alt;
}

nodoAVL *simp_roda_esq(nodoAVL *k2) {
    nodoAVL *k1 = k2->esq;
    k2->esq = k1->dir;
    k1->dir = k2;

    /* atualize alturas */
    k2->alt = max(altura(k2->esq), altura(k2->dir)) + 1;
    k1->alt = max(altura(k1->esq), k2->alt) + 1;

    return k1;  /* nova raiz da subarvore */
}

nodoAVL *simp_roda_dir(nodoAVL *k2) {
    nodoAVL *k1 = k2->dir;
    k2->dir = k1->esq;
    k1->esq = k2;

    /* atualize alturas */
    k2->alt = max(altura(k2->dir), altura(k2->esq)) + 1;
    k1->alt = max(altura(k1->dir), k2->alt) + 1;

    return k1;  /* nova raiz da subarvore */
}

nodoAVL *dup_roda_esq(nodoAVL *k3) {
    /* Rotacione entre k1 e k2 */
    k3->esq = simp_roda_dir(k3->esq);

    /* Rotaciona entre k3 e k2 */
    return simp_roda_esq(k3);
}

nodoAVL *dup_roda_dir(nodoAVL *k3) {
    /* Rotacione entre k1 e k2 */
    k3->dir = simp_roda_esq(k3->dir);

    /* Rotaciona entre k3 e k2 */
    return simp_roda_dir(k3);
}

nodoAVL *insereAVL(int info, nodoAVL *arv) {
    if (arv == NULL) {
        /* Folha: aloca novo nodo */
        arv = (nodoAVL *)malloc(sizeof(nodoAVL));
        if (arv == NULL)
            return NULL;
        arv->info = info;
        arv->alt = 0;
        arv->esq = NULL;
        arv->dir = NULL;
    } else {
        if (info < arv->info) {
            arv->esq = insereAVL(info, arv->esq);
            if (altura(arv->esq) - altura(arv->dir) > 1) {
                if (info < arv->esq->info)
                    arv = simp_roda_esq(arv);
                else
                    arv = dup_roda_esq(arv);
            }
        } else if (info > arv->info) {
            arv->dir = insereAVL(info, arv->dir);
            if (altura(arv->dir) - altura(arv->esq) > 1) {
                if (info > arv->dir->info)
                    arv = simp_roda_dir(arv);
                else
                    arv = dup_roda_dir(arv);
            }
        } else {
            /* ERRO: chave já está na árvore */
            return arv;
        }
        arv->alt = max(altura(arv->esq), altura(arv->dir)) + 1;
    }
    return arv;
}

void imprimir(nodoAVL *raiz, int nivel) {
    int i;

    if (raiz) {
        imprimir(raiz->dir, nivel + 1);
        printf("\n\n");

        for (i = 0; i < nivel; i++)
            printf("\t");

        printf("%d", raiz->info);
        imprimir(raiz->esq, nivel + 1);
    }
}
