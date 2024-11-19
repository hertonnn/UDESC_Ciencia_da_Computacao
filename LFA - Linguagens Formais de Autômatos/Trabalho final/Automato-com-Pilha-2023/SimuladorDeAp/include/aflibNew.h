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

    size_t n_estados;
} AF;

AF* criarAF() {
    AF *novoAF = (AF*) malloc(sizeof(AF));

    if (novoAF == NULL) {
        printf("Erro ao criar AF!! (Mem alloc)\n");
        exit(1);
    }

    novoAF->h_estado = NULL;
    novoAF->h_transicao = NULL;

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

    if (af->h_estado == NULL) {  // primeiro estado registrado
        af->h_estado = estado;
        return;
    }

    if (estado->inicial == 1) { // estado inicial, insere no início
        ESTADO *old_estado = af->h_estado;
        af->h_estado = estado;
        estado->next = old_estado;
        return;
    }

    // não é estado inicial, insere ao final
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
    char *insertPile;
    char popPile;

    char *q_from;
    char *q_to;

    struct transicao * next;
} TRANSICAO;

TRANSICAO* criarTransicao(char symbol, char *insertPile, char popPile, char *from, char *to) {
    TRANSICAO *novaTransicao = (TRANSICAO*) malloc(sizeof(TRANSICAO));

    if (novaTransicao == NULL) {
        printf("Erro ao criar transicao!! (Mem alloc)\n");
        exit(1);
    }

    novaTransicao->symbol = symbol;
    novaTransicao->insertPile = insertPile;
    novaTransicao->popPile = popPile;

    novaTransicao->q_from = (char*) malloc(sizeof(char) * 5);
    novaTransicao->q_to = (char*) malloc(sizeof(char) * 5);
    strcpy(novaTransicao->q_from, from);
    strcpy(novaTransicao->q_to, to);

    novaTransicao->next = NULL;

    return novaTransicao;
}

void inserirTransicao(char symbol, char *insertPile, char popPile, char *from, char *to, AF *af) {
    TRANSICAO *transicao = criarTransicao(symbol, insertPile, popPile, from, to);

    if (af == NULL) {
        printf("AF invalido!! \n");
        return;
    }

    if (af->h_transicao == NULL) {
        af->h_transicao = transicao;
    } else {
        TRANSICAO *currentTransicao = af->h_transicao;
        while (currentTransicao->next != NULL) {
            currentTransicao = currentTransicao->next;
        }
        currentTransicao->next = transicao;
    }
}

void deleteAf(AF *af) {
    if (af == NULL) {
        return;
    }

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

ESTADO** getArrayEstado(size_t size) {
    ESTADO **array = (ESTADO**) malloc(sizeof(ESTADO*) * size);

    for (int i = 0; i < size; i++) {
        array[i] = NULL;
    }

    return array;
}

int reconhecerPalavra(char *palavra, int index, ESTADO *currentState, ESTADO **visited, int visited_count, AF *af, char *stack, int stack_top) {
    if (index == strlen(palavra)) {
        return currentState->final && stack_top == -1;
    }

    TRANSICAO *tr = af->h_transicao;
    while (tr != NULL) { // Executa as transições que partem do estado atual
        if (strcmp(tr->q_from, currentState->nome) == 0) {
            if (tr->symbol == palavra[index] || tr->symbol == '#') {
                char new_stack[100];
                int new_stack_top = stack_top;
                memcpy(new_stack, stack, sizeof(char) * (stack_top + 1));

                if (tr->popPile != '#') {
                    if (stack_top == -1 || new_stack[stack_top] != tr->popPile) {
                        tr = tr->next;
                        continue;
                    } else {
                        new_stack_top--;
                    }
                }

                if (tr->insertPile != NULL && strcmp(tr->insertPile, "#") != 0) {
                    for (int i = strlen(tr->insertPile) - 1; i >= 0; i--) {
                        new_stack[++new_stack_top] = tr->insertPile[i];
                    }
                }

                int already_visited = 0;
                for (int i = 0; i < visited_count; i++) {
                    if (strcmp(visited[i]->nome, tr->q_to) == 0) {
                        already_visited = 1;
                        break;
                    }
                }

                if (!already_visited) {
                    visited[visited_count] = getEstadoByName(tr->q_from, af);
                    if (reconhecerPalavra(palavra, tr->symbol == '#' ? index : index + 1, getEstadoByName(tr->q_to, af), visited, visited_count + 1, af, new_stack, new_stack_top)) {
                        return 1;
                    }
                }
            }
        }
        tr = tr->next;
    }

    return 0;
}

int verificarPalavra(char *palavra, AF *af) {
    int n = af->n_estados;
    ESTADO **visitedArray = getArrayEstado(n);

    return reconhecerPalavra(palavra, 0, af->h_estado, visitedArray, 0, af, NULL, -1);
}

#endif
