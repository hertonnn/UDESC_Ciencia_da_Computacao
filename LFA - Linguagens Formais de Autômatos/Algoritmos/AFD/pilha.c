//Pilha implementada em uma lista encadeada
//Decisões de projeto: 
// 1. Nossa pilha terá uma célula-cabeça com conteúdo # (pilha vazia)

#include <stdio.h>
#include <stdlib.h>

#define EMPTY 'e'

typedef struct Cell
{
    char content;
    struct Cell *next;

}Cell;

Cell* createStack(){
    Cell* stack = malloc(sizeof(stack));
    stack->content = EMPTY;

    return stack;
}

Cell *push(Cell *head, char letter){ 
    Cell *node;
    node = malloc(sizeof(Cell));
    
    if(node){
        node->content = letter;
        node->next = head;
        return node;
    }
    printf("\nErro ao alocar memória...\n");
    return NULL;
}

// Para alterar o conteúdo da variável declarada em outra função / procedimento, precisamos
// receber o endereço dessa variável. Isso implica em receber como parãmetro um ponteiro para 
// ponteiro, o endereço da variável declarada na função main que contém o endereço do topo da
// pilha, assim:

Cell *pop(Cell** head){ 
    
    if(*head == NULL){
        printf("\nPilha Vazia!\n");
        exit(1);
    }
    Cell *remote = *head;
    *head = remote->next;
    return remote;
}
int isEmpty(Cell* head){
    if(head->content != EMPTY){
        return 0;
    }
    return 1;
}

// int main(){

    
//     Cell *head = malloc(sizeof(Cell));

//     head = push(head, 'a');
//     head = push(head, 'b');
//     head = push(head, 'c');

//     //printf("%c", pop(&head)->content); // &head o endereço de memoria do ponteiro para o topo da pilha
    
//     Cell *remote;
//     remote = pop(&head);

//     while (remote != NULL)
//     {
//         printf("%c", remote->content);
//         remote = pop(&head); 
//     }
    
// }