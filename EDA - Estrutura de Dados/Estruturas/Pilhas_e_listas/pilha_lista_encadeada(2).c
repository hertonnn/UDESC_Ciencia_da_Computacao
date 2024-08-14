//Pilha implementada em uma lista encadeada

#include <stdio.h>
#include <stdlib.h>

typedef struct cell
{
    char content;
    struct cell *next;

}cell;

cell *head;
int a;

void create(){
    head = malloc(sizeof(cell)); // cell = cálula
    head->next = NULL;  
    a = 0;
}


void stack(char letter){ // letter = letra / stack = empilha / node = nó
    cell *node;
    node = malloc(sizeof(cell));
    node->content = letter;
    node->next = head->next;

    head->next = node;
}

char unstack(){ 
    cell *node;
    char c;
    node = head->next;
    c = node->content;
    head->next = node->next;
    free(node);
    return c;    
}
int isEmpty(){
    if(head->next == NULL){
        return 1;
    }
    return 0;
}

int main(){
    create();
    
    int tam;
    char c;
    printf("\nEmpilhando itens\n");
    printf("\nQuantos valores terá a pilha?\n");
    scanf("%i", &tam);
    getc(stdin);
    for(int i = 0; i< tam; i++){
        printf("Digite a letra da posição %i = ", i);
        scanf("%c", &c);
        getc(stdin);
        stack(c);
    }
    printf("\nDesempilhando itens\n");
    for(int j = 1; j < tam + 1; j++){
        printf("\nItem %i = %c\n", j, unstack());
    }
    
}