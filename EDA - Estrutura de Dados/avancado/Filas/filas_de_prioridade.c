//Fila de prioridade ordenada com lista encadeada

// A fila de prioridade nada mais é que uma fila comum que permite que elementos sejam
// adicionados associados com uma prioridade. Cada elemento na fila deve possuir um dado
// adicional que representa sua prioridade de atendimento. Uma regra explicita define que o
// elemento de maior prioridade (o que tem o maior número associado) deve ser o primeiro a ser
// removido da fila, quando uma remoção é requerida. 

#include <stdio.h>
#include <stdlib.h>

struct cell{
    struct cell *next;
    struct cell *prev;
    int priority;
};

typedef struct cell Cell;

// Colocando elemento considerando sua prioridade.
// Ordenada – Antes de um elementos ser inserido a fila, a posição correta (de acordo
// com a sua prioridade) deve ser identificada. 
// # A complexidade dessa algoritmo é no pior caso O(n), onde n é o número de elementos da pilha.
Cell *push(Cell *head, Cell *element){
    if(head->priority <= element->priority){
            element->next = head;
            head->prev = element;   
            return element;
        }
    Cell *node = head;
    while (node->next)
    {
        if(node->priority <= element->priority){
            Cell *aux = node->prev;
            element->next = node;
            aux->next = element;
            node->prev = element;

            return head;
        }
        node = node->next;
    }
    node->next = element;
    element->prev = node;

    return head;
}

// Só remover cabeça, umas vez que ela é sempre o elemento de maior prioridade 
Cell *pop(Cell **head){
    if(*head != NULL){
        Cell *remote = *head;
        *head = remote->next;

        return remote; 
    }
    printf("\nStack is empty...\n");
    return NULL;
    
}

// O código é importante daqui pra cima. A partir daqui eu zaralhei tudo e escrevi tudo em portuga mesmo...
int main(){
   
   int num;
   Cell *head = malloc(sizeof(Cell));
   head->priority = -1;

   while (num != 0)
   {
        printf("\n1-Add elemento\n");
        printf("2-Remover o de maior prioridade\n");
        printf("3-Mostrar todos\n");
        printf("4-Encerrar\n");
        printf("Digite: ");
        scanf("%i",&num);
    switch (num)
    {
    case 1:
        int quant;
        int prioridade;
        printf("Quantos valores quer adicionar?\n");
        scanf("%i", &quant);
        if(head->priority == -1){

            printf("\nDigite a prioridade da cabeça:");
            scanf("%i", &prioridade);
            head->priority = prioridade;
        }
        for(int i = 0; i<quant; i++){
            Cell *node = malloc(sizeof(Cell));
            printf("\nDigite a prioridade do item %i:", i);
            scanf("%i", &prioridade);
            node->priority = prioridade;
            head = push(head, node);
        }
        break;
    case 2:
        printf("\nRemovido o item %i\n", pop(&head)->priority);
        break;
    case 3:
        Cell *aux = head;
        int i = 0;
        while (aux->next)
        {
            printf("\nPrioridade do elemento [%i]: %i\n", i, aux->priority);
            aux = aux->next;
            i++;
        }
        printf("\nPrioridade do elemento [%i]: %i\n", i, aux->priority);
        break;
    default:
        exit(1);
        break;
    }
   }
   free(head);  
}