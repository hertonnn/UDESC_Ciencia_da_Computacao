#include "stdio.h"
#include "stdlib.h"
#include "string.h"
#define SIM 1
#define NAO 0
#define SUCESSO 1
#define FRACASSO 0
#define MAX_TAM_TAG 50
#define NUM_TAGS_IGNORADAS 16

typedef struct info{ 
    char *palavra;
    }info;

//======== Modelo de dados utilizados na implementação da PILHA	
//	
// Nó contendo campo de encadeamento (apontador "abaixo") e área 
// para os dados da aplicação (campo "info")
struct noPSE{	
	info dados;
	struct noPSE *abaixo;
	};	
//
// Descritor da pilha, contendo:
// O apontador "topo", o qual indica o endereço do descritor da pilha e
// o "tamanho" atual da pilha
struct pilha{ 
	struct noPSE *topo;
	int tamPilha;
	};

//================================================
//
//
//====== Operações providas na interface da PILHA
//
struct pilha * cria(void);
int vazia(struct pilha *pil);
int empilha(info *reg, struct pilha *pil);
int desempilha(info *reg, struct pilha *pil);
int busca(info *reg, struct pilha *pil);
void reinicia(struct pilha *pil);
struct pilha *destroi(struct pilha *pil);


//====== Novas operações utilizadas no verificador de tags
void mostra(struct pilha *pil);
int compara_topo(info *info, struct pilha *pil);
int verificar_tag(char tags_ignoradas[NUM_TAGS_IGNORADAS][MAX_TAM_TAG], char tag[MAX_TAM_TAG]);