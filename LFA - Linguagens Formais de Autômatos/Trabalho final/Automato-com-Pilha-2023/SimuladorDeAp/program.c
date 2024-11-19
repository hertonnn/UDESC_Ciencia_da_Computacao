#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "include/aflib.h"
#include "include/grviz.c"

void simulacao();

int main() {
    while (1) {
        printf("---------Menu---------\n1 - Iniciar simulacao\n2 - Encerrar o programa\ncomando >> ");
        
        int opcao = 0;
        scanf("%d", &opcao);

        if (opcao == 1) {
            simulacao();
        }
        else if (opcao == 2) {
            printf("Programa finalizado!");
            break;
        }
        else {
            printf("Opcao nao identificada!");
        }
    }

    return 0;
}


void simulacao() {
    AF *af = criarAF();

    //Leitura dos estados
    int opcao = 0;
    int nEstados = 0;

    while (1) { //Criacao de estados
        printf("\n\n1 - Criar novo estado\n2 - Proximo passo\n");
        printf("comando >> ");
        scanf("%d", &opcao);
        int c;
        while ((c = getchar()) != '\n' && c != EOF);

        if (opcao == 2 && nEstados < 1) {
            printf("Crie ao menos um estado!");
            continue;
        }

         if (opcao == 2) {
            break;
        }

        char nome[5];
        printf("Digite o nome do estado: \n");
        scanf("%s", nome);

        int inicial = 0;
        int final = 0;

        if (af->h_estado == NULL || af->h_estado != NULL && af->h_estado->inicial == 0) {
            printf("Eh inicial? (0 ou 1) ");
            scanf("%d", &inicial);
        }

        printf("Eh final? (0 ou 1) ");
        scanf("%d", &final);

        inserirEstado(nome, inicial, final, af);
        nEstados++;
    }

    printf("\n\n-------------------\nExemplo de registro de transicao: q0->q1 (a, B, #)\nOBS: # == epsilon\n     ? == fita/pilha vazia\n");
    while (1) { //Criacao de transicoes
        printf("\n1 - Criar nova transicao\n2 - Proximo passo\n");
        printf("comando >> ");
        scanf("%d", &opcao);
        int c;
        while ((c = getchar()) != '\n' && c != EOF);

        if (opcao == 2) {
            break;
        }

        printf("Transicao: ");

        char *q0 = (char*) malloc(sizeof(char) * 5);
        char *q1 = (char*) malloc(sizeof(char) * 5);
        char symbol;
        char pop;
        char *push = (char*) malloc(sizeof(char) * 5);
        char linha[30], *result;

        if ((result = fgets(linha, 30, stdin)) != NULL) {
            sscanf(result, "%[^-]->%4s (%c,%c,%[^)]", q0, q1, &symbol, &pop, push);
        }
        
        inserirTransicao(symbol, pop, push, q0, q1, af);
        printf("\nregistrado: qDe:|%s| qPara:|%s| s:|%c| pull:|%c| push:|%s|\n", q0, q1, symbol, pop, push);
    }

    printf("\n**Reconhecimento de palavras**\n");
    while (1) {
        printf("Digite uma palavra (ou 'exit' para sair)>> ");
        char palavra[99] = "";
        scanf("%s", palavra);
        printf("%s\n", palavra);
        if (strcmp(palavra, "exit") == 0) {break;}

        int validade = verificarPalavra(palavra, af);
        if (validade) {
            printf("Palavra valida!\n");
        }
        else {
            printf("Palavra invalida!\n");
        }
    }

    printf("\nGerando grafo visual...\n");
    char comando[10000] = "start \"\" \"";
    geraLinkGraphviz(comando, af);
    strcat(comando, "\"");
    system(comando);
    
    printf("\nDeletando simulacao anterior...\n");
    deleteAf(af);
    printf("Simulacao deletada com sucesso!\n\n");
}


