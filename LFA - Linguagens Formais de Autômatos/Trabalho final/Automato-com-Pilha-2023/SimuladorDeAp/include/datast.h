#include <stdio.h>
#include <stdlib.h>

#ifndef DATAST_H
#define DATAST_H


typedef struct nodoStack{
    char symbol;
    struct nodoStack *next;
} NodoStack;

typedef struct stack{
    NodoStack *topo;
}PILHA;

PILHA *cria(){
    PILHA *s = (struct stack *)malloc(sizeof(struct stack));

    if(s == NULL){
       printf("Erro ao criar Pilha!\n");
       exit(1); 
    }

    s->topo = NULL;

    return s;
}

int isEmpty(PILHA *s){
    return s->topo == NULL;
}

void push(PILHA *s, char letra){
    //printf("Letra %c\n", letra);
    NodoStack *aux = (NodoStack*)malloc(sizeof(NodoStack));

    if(aux == NULL){
        printf("Erro ao empilhar letra!\n");
        exit(1);
    }
   
   aux->symbol = letra;
   aux->next = s->topo;
   s->topo = aux;
    
}

int pop(char *letra, PILHA *s){

    if(!isEmpty(s)){
        NodoStack *aux = s->topo;
        *letra = aux->symbol;
        s->topo = aux->next;
        free(aux);
        return 1;
    }else{
        return 0;
    }
}

void voidPop(PILHA *s) {
    if(!isEmpty(s)){
        NodoStack *aux = s->topo;
        s->topo = aux->next;
        free(aux);
    }
}

PILHA* clonarPilha(PILHA *pilha) {
    PILHA *novaPilha = (PILHA*) malloc(sizeof(PILHA));
    if (novaPilha == NULL) {
        printf("Erro ao clonar pilha!\n");
        return NULL;
    }

    NodoStack *newStackCurrentNode = NULL;
    NodoStack *oldStackCurrentNode = pilha->topo;

    while (oldStackCurrentNode != NULL) {
        NodoStack *aux = (NodoStack*) malloc(sizeof(NodoStack));
        aux->symbol = oldStackCurrentNode->symbol;
        aux->next = NULL;

        if (newStackCurrentNode == NULL) {
            novaPilha->topo = aux;
        }
        else {
            newStackCurrentNode->next = aux;    
        }
        newStackCurrentNode = aux;

        oldStackCurrentNode = oldStackCurrentNode->next;
    }

    return novaPilha;
}

int reinicia(PILHA *s) {
  NodoStack *aux = NULL;
  if (!isEmpty(s)) {
    aux = s->topo->next;
    while (aux != NULL) {
      free(s->topo);
      s->topo = aux;
      aux = aux->next;
    }
    free(s->topo);
    s->topo = NULL;
    return 1;
  }
  return 0;
}

void destroiPilha(PILHA *s) {
  reinicia(s);
  free(s);
}

char *getStringPilha(PILHA *stack) {
    if (stack == NULL) {
        return NULL;
    }

    char *retorno = malloc(sizeof(char) * 100);
    strcpy(retorno, "");

    NodoStack *no = stack->topo;
    while (no != NULL) {
        if (no->symbol != '\0' && no->symbol != '?') {
             strncat(retorno, &no->symbol, 1);
        }
       
        no = no->next;
    }

    return retorno;
}

#endif