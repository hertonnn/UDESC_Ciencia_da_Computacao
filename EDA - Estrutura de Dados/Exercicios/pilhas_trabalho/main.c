#include "arq.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "pilhaSE.c"

int verificar_tag(char tags_ignoradas[NUM_TAGS_IGNORADAS][MAX_TAM_TAG], char tag[MAX_TAM_TAG]){
    for(int i = 0; i < NUM_TAGS_IGNORADAS; i++){
        if(strcmp(tag, tags_ignoradas[i]) == 0){
            return 1;
        }
    }
    return 0;
}

int main(int argc, char *argv[]){

FILE *file = fopen(argv[1], "r");
info info_palavra;
char tag[MAX_TAM_TAG];
char tag_atual[MAX_TAM_TAG];
char linha[1024];
char tags_ignoradas[NUM_TAGS_IGNORADAS][MAX_TAM_TAG] = {
        "area", "base", "br", "col", "embed",
        "hr", "img", "input", "keygen", "link",
        "meta", "param", "source", "track", "wbr", "!DOCTYPE"
};

struct pilha *pilha = NULL;
pilha = cria();

    if (file) {
    int cont = 1;
    while (fgets(linha, sizeof(linha), file) != NULL) { // loop while para ler linha por linha do HTML
        cont++;

        for (int i = 0; i < sizeof(linha) - 1; i++) {   // loop for para ler byte a byte da linha
            if (linha[i] == '\0' || linha[i] == '\n') {
                break;
            }

            if (linha[i] == '<' && linha[i + 1] != '/' && linha[i + 1] != '!') {
                i++;
                int j = 0;
                while (linha[i] != '>' && linha[i] != ' ') {
                    tag[j] = linha[i++];
                    j++;
                }
                tag[j] = '\0';
                if (!(verificar_tag(tags_ignoradas, tag))) {  // verificando se a tag faz parte da matriz de tags que devem ser ignoradas
                    info_palavra.palavra = strdup(tag);
                    empilha(&info_palavra, pilha);
                }
            }

            if (linha[i] == '<' && linha[i+1] == '/' && linha[i+1] != '!') {
                i += 2;
                int j = 0;
                while (linha[i] != '>' && linha[i] != ' ') {
                    tag[j] = linha[i++];
                    j++;
                }
                tag[j] = '\0';
                if (!(verificar_tag(tags_ignoradas, tag))) {  // verificando se a tag faz parte da matriz de tags que devem ser ignoradas
                    info_palavra.palavra = strdup(tag);
                    if (compara_topo(&info_palavra, pilha)) {
                        desempilha(&pilha->topo->dados, pilha);
                    } else {
                        printf("ERRO: na linha %d esperado </%s>, encontrado </%s>\n", cont - 1, pilha->topo->dados.palavra, tag);
                        system("pause");
                        exit(1);
                    }
                }
            }
        }
    }

        if (vazia(pilha)) { // Se a pilha estiver vazia, significa que o arquivo HTML está bem aninhado
            printf("Todas as tags foram fechadas com sucesso!\n");
            system("pause");
        } 
    }

    else{
        printf("ERRO: Arquivo nao encontrado!\n");
        exit(1);
    }

    return 0;
}