// PAG.74

#include <stdio.h>
#include <stdlib.h>

#define MAX 100

typedef  struct aluno {    // estrutura aluno e ponteiro p/ aluno
    char nome[81];    
    int mat;    
    char end[121];    
    char tel[21];
 }Aluno, *pAluno; 


// Uma dica é omitir o typedef e escrever de maneira natural como vc declararia aquele tipo
//aqui eu declaro Tabela que é um vetor de ponteiros p/ struct aluno com tamanho max 100
typedef pAluno Tabela[MAX]; 

// Inicializando toda a tabela de alunos
void inicializa(Tabela tab){
    int i;

    for(i = 0; i < MAX; i++){
        tab[i] = NULL;
    }
}
// Preenchendo cada posição de maneira dinâmica

void preenche(int i, Tabela tab){

    if(tab[i] == NULL){
        tab[i] = (pAluno) (malloc(sizeof(Aluno)));
    }

    printf("Entre com o nome:");    
    scanf(" %80[^\n]", tab[i]->nome);    
    printf("Entre com a matricula:");    
    scanf("%d", &tab[i]->mat);    
    printf("Entre com o endereco:");    
    scanf(" %120[^\n]", tab[i]->end);    
    printf("Entre com o telefone:");    
    scanf(" %20[^\n]", tab[i]->tel);

}

// Remove um aluno e limpa aquele espaço usado por ele
void remove_aluno(int i, Tabela tab){
    if(tab[i] != NULL){
        free(tab[i]); // Libero e atribuo a nulo para as proximas func funcionarem
        tab[i] = NULL;
    }
}

// Imprime aluno
void imprime_aluno(int i, Tabela tab){

    printf("Nome: %s\n", tab[i]->nome);       
    printf("Matrícula: %d\n", tab[i]->mat);       
    printf("Endereço: %s\n", tab[i]->end);       
    printf("Telefone: %s\n", tab[i]->tel);

}

// Imprime toda tabela de alunos
void imprime_tabela(Tabela tab){

    int i = 0;
    for(i; i < MAX; i++){
        if(tab[i] != NULL){
            imprime_aluno(i, tab);
        }
    }
    
}
int main(void){

    Tabela tab[MAX]; // criando vetores de ponteiros para estruturas

    inicializa(tab);
    preenche(1, tab);
    imprime_aluno(1, tab);
    imprime_tabela(tab);
    remove_aluno(1, tab);

    return 0;
}