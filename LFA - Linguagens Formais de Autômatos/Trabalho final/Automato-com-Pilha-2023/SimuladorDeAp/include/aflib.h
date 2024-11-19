#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "datast.h"

#ifndef AFLIB_H
#define AFLIB_H

typedef struct estado ESTADO;
typedef struct transicao TRANSICAO;
typedef struct af AF;

typedef struct af {
    ESTADO *h_estado;
    TRANSICAO *h_transicao;

    int n_estados;
} AF;

AF* criarAF() {
    AF *novoAF = (AF*) malloc(sizeof(AF));

    if (novoAF == NULL) {
        printf("Erro ao criar AF!! (Mem alloc)\n");
        exit(1);
    }

    novoAF->h_estado = NULL;
    novoAF->h_transicao = NULL;
    novoAF->n_estados = 0;

    return novoAF;
}




typedef struct estado {
    int inicial; // 0 = false, 1 = true
    int final; // 0 = false, 1 = true
    char *nome;

    struct estado * next;
} ESTADO;

ESTADO* criarEstado(char *nome, int inicial, int final) {
    ESTADO *novoEstado = (ESTADO*) malloc(sizeof(ESTADO));
    novoEstado->nome = (char*) malloc(sizeof(char) * 5);

    if (novoEstado == NULL) {
        printf("Erro ao criar estado!! (Mem alloc)\n");
        exit(1);
    }

    strcpy(novoEstado->nome, nome);
    novoEstado->inicial = inicial;
    novoEstado->final = final;
    novoEstado->next = NULL;

    return novoEstado;
}

void inserirEstado(char *nome, int inicial, int final, AF *af) {
    ESTADO *estado = criarEstado(nome, inicial, final);
    if (af == NULL) {
        printf("AF invalido!! \n");
        return;
    }

    if (af->h_estado == NULL) {  //primeiro estado registrado
        af->h_estado = estado;
        return;
    }

    if (estado->inicial == 1) { //estado inicial, insere no inicio
        ESTADO *old_estado = af->h_estado;
        af->h_estado = estado;
        estado->next = old_estado;

        free(old_estado);
        return;
    }
    
    //nao eh estado inicial, insere ao final
    ESTADO *currentEstado = af->h_estado;
    while (currentEstado->next != NULL) {
        currentEstado = currentEstado->next;
    }
    currentEstado->next = estado;

    af->n_estados++;
}

ESTADO* getEstadoByName(char *nome, AF *af) {
    ESTADO *state = af->h_estado;
    while (state != NULL) {
        if (strcmp(state->nome, nome) == 0) {
            return state;
        }

        state = state->next;
    }

    return NULL;
}

typedef struct transicao {
    char symbol;
    char *insertStack;
    char popStack;

    char *q_from;
    char *q_to;

    struct transicao * next;
} TRANSICAO;

TRANSICAO* criarTransicao(char symbol, char *insertStack, char popStack, char *from, char *to) {
    TRANSICAO *novaTransicao = (TRANSICAO*) malloc(sizeof(TRANSICAO));

    if (novaTransicao == NULL) {
        printf("Erro ao criar transicao!! (Mem alloc)\n");
        exit(1);
    }

    
    novaTransicao->symbol = symbol;
    novaTransicao->insertStack = insertStack;
    novaTransicao->popStack = popStack;

    novaTransicao->q_from = (char*) malloc(sizeof(char*) * 5);
    novaTransicao->q_to = (char*) malloc(sizeof(char*) * 5);
    strcpy(novaTransicao->q_from, from);
    strcpy(novaTransicao->q_to, to);

    novaTransicao->next = NULL;

    return novaTransicao;
}

void inserirTransicao(char symbol, char popStack, char *insertStack, char *from, char *to, AF *af) {
    TRANSICAO *transicao = criarTransicao(symbol, insertStack, popStack, from, to);

    if (af == NULL) {
        printf("AF invalido!! \n");
        return;
    }

    if (af->h_transicao == NULL) {
        af->h_transicao = transicao;
    }
    else {
        TRANSICAO *currentTransicao = af->h_transicao;
        while (currentTransicao->next != NULL) {
            currentTransicao = currentTransicao->next;
        }
        currentTransicao->next = transicao;
    }
}


void deleteAf(AF *af) {
    if (af == NULL) {return;}

    ESTADO *estado = af->h_estado;
    while (estado != NULL) {
        ESTADO *next = estado->next;
        
        free(estado->nome);
        free(estado);
        estado = next;
    }

    TRANSICAO *transicao = af->h_transicao;
    while (transicao != NULL) {
        TRANSICAO *next = transicao->next;

        free(transicao->q_from);
        free(transicao->q_to);
        free(transicao);
        transicao = next;
    }
    
}

ESTADO** getArrayEstado(int size) {
    ESTADO **array = (ESTADO**) malloc(sizeof(ESTADO*) * size);

    for (int i = 0; i < size; i++) {
        array[i] = NULL;
    }

    return array;
}

int estadoPercorrido(ESTADO *currentState, ESTADO **visited, int n) {
    for (int i = 0; i < n; i++) {
        if (strcmp(currentState->nome,visited[i]->nome) == 0) {
            return 1;
        }
    }

    return 0;
}


int reconhecerPalavra(char *palavra, int index, ESTADO *currentState, PILHA *stack, ESTADO **visitados, int nVisitados, AF *af, int nivel) {
    char identacao[50] = "";
    for (int i = 1; i <= nivel; i++) {
        strcat(identacao, "   ");
    }
    //printf("--------\n%spalavra: %s | letra: %c | indice: %d | estado: %s\n", identacao, palavra, palavra[index], index, currentState->nome);

    if (palavra[index] == '?' || index == strlen(palavra)) {
        //printf("-Entrou if 1\n");
        if (currentState->final) {
            return 1;
        }
    }

    TRANSICAO *tr = af->h_transicao;
    strcat(identacao, " ");
    while (tr != NULL) {
        //printf("%str -> q_from: %s | q_to: %s | symbol: %c | pop: %c | push: %s\n", identacao, tr->q_from, tr->q_to, tr->symbol, tr->popStack, tr->insertStack);
        if (tr->symbol == palavra[index] && strcmp(tr->q_from, currentState->nome) == 0) { //transicao
            if (tr->popStack == '#' || tr->popStack == stack->topo->symbol) {
                //printf("Entrou caso 1\n");
                ESTADO **visitedArray = getArrayEstado(af->n_estados);
            
                if (tr->popStack != '#') {voidPop(stack);}
                if (strcmp(tr->insertStack, "#") != 0) {
                    //printf("Entrou insere pilha\n");
                    for (int j = 0; j < strlen(tr->insertStack); j++) {
                        //printf("insere %c\n", tr->insertStack[j]);
                        push(stack, tr->insertStack[j]);
                    }
                }

                //printf("Depois insere pilha\n");

                ESTADO *q_to = getEstadoByName(tr->q_to, af);
                //printf("Antes reconhece palavra");

                char *stringPilha = getStringPilha(stack);
                
                int reconhece = reconhecerPalavra(palavra, index+1, q_to, stack, visitedArray, 0, af, nivel+1);
                //printf("Depois reconhece palavra\n");
                if (reconhece) {
                    char s = tr->symbol;
                    if (s == '?') {s = ' ';}
                    printf("%d) %c  |  %s->%s  |  %s\n", nivel, s, tr->q_from, tr->q_to, stringPilha);   

                    return 1;
                }

                free(stringPilha);

                if (strcmp(tr->insertStack, "#") != 0) {
                    for (int j = 0; j < strlen(tr->insertStack); j++) {
                        voidPop(stack);
                    }
                }
                if (tr->popStack != '#') {push(stack, tr->popStack);}

            }
        }

        if (tr->symbol == '#' && strcmp(tr->q_from, currentState->nome) == 0) { //transicao vazia
            if (tr->popStack == '#' || tr->popStack == stack->topo->symbol) {
                if (!estadoPercorrido(currentState, visitados, nVisitados)) {
                    nVisitados++;
                    visitados[nVisitados] = currentState;

                    if (tr->popStack != '#') {voidPop(stack);}
                    if (strcmp(tr->insertStack, "#") != 0) {
                        //printf("Entrou insere pilha\n");
                        for (int j = 0; j < strlen(tr->insertStack); j++) {
                            //printf("insere %c\n", tr->insertStack[j]);
                            push(stack, tr->insertStack[j]);
                        }
                    }

                    char *stringPilha = getStringPilha(stack);

                    ESTADO *q_to = getEstadoByName(tr->q_to, af);
                    int reconhece = reconhecerPalavra(palavra, index, q_to, stack, visitados, nVisitados, af, nivel+1);
                    if (reconhece) {
                        char s = tr->symbol;
                        if (s == '?') {s = ' ';}
                        printf("%d) %c  |  %s->%s  |  %s\n", nivel, s, tr->q_from, tr->q_to, stringPilha);     

                        return 1;
                    }

                    if (strcmp(tr->insertStack, "#") != 0) {
                        for (int j = 0; j < strlen(tr->insertStack); j++) {
                            voidPop(stack);
                        }
                    }
                    if (tr->popStack != '#') {push(stack, tr->popStack);}

                    visitados[nVisitados] = NULL;
                    nVisitados--;
                }
            }
        }

        tr = tr->next;
    }

    return 0;
}



int verificarPalavra(char *palavra, AF *af) {
    int n = af->n_estados;
    //printf("n_estados: %d\n", n);

    char newPalavra[101] = "";
    strcat(newPalavra, palavra);
    strcat(newPalavra, "?");

   // printf("%s\n", newPalavra);

    ESTADO **visitedArray = getArrayEstado(n);

    PILHA *stack = (PILHA*) malloc(sizeof(PILHA));
    stack->topo = NULL;
    push(stack, '?');

    int retorno = reconhecerPalavra(newPalavra, 0, af->h_estado, stack, visitedArray, 0, af, 0);
    if (retorno) {
        printf("   %s  %s  |\n", newPalavra, af->h_estado->nome);
    }

    return retorno;
}

#endif