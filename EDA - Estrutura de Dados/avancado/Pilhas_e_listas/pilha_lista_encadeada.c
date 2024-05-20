//Pilha implementada em uma lista encadeada
//Decisões de projeto: 1. Nossa lista terá uma célula-cabeça (e portanto a primeira célula da lista não fará parte da pilha). 2. O topo da pilha ficará na segunda célula e não na última (por quê?). 3. Uma variável global pi apontará a cabeça da lista.

#include <stdio.h>
#include <stdlib.h>

typedef struct Cell
{
    char content;
    struct Cell *next;

}Cell;

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

Cell *pop(Cell **head){ 
    
    if(*head != NULL){
        Cell *remote = *head;
        *head = remote->next;
        return remote;
    }
    printf("\nPilha Vazia!\n");
    return NULL;
}
int isEmpty(Cell **head){
    if(*head != NULL){
        return 0;
    }
    return 1;
}

int main(){

    
    Cell *head = malloc(sizeof(Cell));

    head = push(head, 'a');
    head = push(head, 'b');
    head = push(head, 'c');

    //printf("%c", pop(&head)->content); // &head o endereço de memoria do ponteiro para o topo da pilha
    
    Cell *remote;
    remote = pop(&head);

    while (remote != NULL)
    {
        printf("%c", remote->content);
        remote = pop(&head); 
    }
    
}