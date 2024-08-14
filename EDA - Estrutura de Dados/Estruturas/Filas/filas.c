//Algoritmo 1 – inserção em fila (HU, 2020), pág.130
#include<stdio.h>
#include<stdlib.h>
#include<string.h>

typedef struct No{
    char dado[50];
    struct No *anterior;
    struct No *proximo;
} No;


No* cabeca = NULL;
No* cauda = NULL;
int tamanho;

// adiciona dados na fila
void adiciona(char dado[]){ //offer//
    No *novo_no = NULL;
    novo_no = (No*) malloc(sizeof(No));
    strcpy(novo_no-> dado, dado);
    novo_no -> proximo = NULL;
    novo_no-> anterior = NULL;
    
    if ( cabeca==NULL){
        cabeca = novo_no;
        cauda = cabeca;
    }
    else{
        novo_no -> proximo = cauda;
        cauda -> anterior = novo_no;
        cauda = novo_no;
    }
    tamanho++;
        
} //fim adiciona

// busca o elemento da frente e o remove da fila
No* cortar(){
    No* p = cabeca;
    if( p==NULL){
        return NULL;
    }
    
    cabeca = cabeca -> anterior;   
    p-> proximo = NULL;
    p-> anterior = NULL;

    tamanho--;
    return p;
}// fim cortar

// fila andando
void saida(){
    //No* p = cabeca;
    printf("cabeca");
    No* No = NULL;
    while((No = cortar())!=NULL){
        printf("\n%s <-", No-> dado);
    }
    printf("\ncauda\n");
}// fim saida

void liberaMemoria(){
    No* p = cabeca;
    No* temp = p;
    while ( p != NULL){
        temp = p ;
        p = p -> proximo;
        free(temp);
    }
}

// listar elementos

void listar(){
    No *p = cabeca;

    if(p == NULL){
        printf("\nLista vazia\n");
    }
    int i = 1;
    while (p != NULL)
    {
        printf("%i - %s\n", i, p -> dado);
        p = p -> anterior;
        i++;
    }
    
}

// adicionar no inicio da fila?? furando fila é? kk
void addInicio(char dado[]){

}

// remove item

int removeItem(int index){
    No* p = cabeca;
    No* temp;

    if(p == NULL){
        printf("\nLista vazia\n");
        return -1;
    }
    if(index == 1){
        cabeca = cabeca->anterior;
    }
    else if (index == tamanho){
        No* c = cauda;
        cauda = cauda -> proximo;
        cauda -> anterior = NULL;
        free(c);
        tamanho--;
        return 1;
    }
    else{
        for(int i = 1; i < index; i++){
            p = p -> anterior;
        }
        // "reconectando a fila"
        temp = p -> proximo;
        temp -> anterior = p -> anterior;

        temp = p -> anterior;
        temp -> proximo = p -> proximo;
    }

    free(p);
    tamanho--;
    return 1;
}
int main(){
    int seletor=0;
    
    while(seletor!=4){
        printf("1-Add elemento\n");
        printf("2-Remover\n");
        printf("3-Mostrar\n");
        printf("4-Encerrar\n");
        printf("Digite: ");
        scanf("%i",&seletor);

            switch (seletor)
            {
            case 1:
                int seletor2 = 0;

                    printf("1-Valor de frente\n");
                    printf("2-Valor no fim da fila\n");
                    printf("3-Mais de um valor\n");
                    printf("4-Menu principal\n");
                    printf("Digite: ");
                    scanf("%i",&seletor2);

                    switch (seletor2)
                    {
                    case 1:
                        /* code */
                        break;
                    case 2:
                        char dado[50];
                        printf("Digite o conteúdo: ");
                        scanf("%s", dado);
                        adiciona(dado);
                        /* code */
                        break;
                    case 3:
                        int quant;
                        printf("Quantos valores quer adicionar?\n");
                        scanf("%i", &quant);
                        for(int i = 0; i<quant; i++){
                            char item[50];

                            printf("\nDigite o item:");
                            scanf("%s", item);
                            adiciona(item);
                        }
                        break;
                    case 4:
                        break;
                    default:
                        printf("Opção inválida\n");
                        break;
                    }
                break;
            case 2:
                listar();
                printf("\nQual item deseja remover? Press o numero do item ou 0(zero) para limpar fila:");
                int index;
                scanf("%i", &index);

                if(index == 0){
                    liberaMemoria();
                }
                else{
                    removeItem(index);
                }
                break;
            case 3:
                int seletor3 = 0;

                printf("1-Valor de frente\n");
                printf("2-Valor no fim da fila\n");
                printf("3-Todos os valores da fila\n");
                printf("4-Tamanho da fila\n");
                printf("5-Menu principal\n");
                printf("Digite: ");
                scanf("%i",&seletor3);

                switch (seletor3)
                {
                case 1:
                    printf("%s", cabeca);
                    break;
                case 2:
                    printf("%s", cauda);
                    break;
                case 3:
                    listar();
                    break;
                case 4:
                    printf("O tamanho da fila é: %i", tamanho);
                    break;
                case 5:
                    break;
                default:
                    printf("Opção inválida\n");
                    break;
                }
                break;    
            default:
                break;
            }
    }
    return 0;
}
