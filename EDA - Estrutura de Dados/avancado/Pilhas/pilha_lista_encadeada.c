//Pilha implementada em uma lista encadeada
//Decisões de projeto: 1. Nossa lista terá uma célula-cabeça (e portanto a primeira célula da lista não fará parte da pilha). 2. O topo da pilha ficará na segunda célula e não na última (por quê?). 3. Uma variável global pi apontará a cabeça da lista.


// Nessa implementação tem alguns erros que eu corrijo mais em outros códigos nessa pasta
// como o uso de variáveis globais e verificação de nulidade da pilha
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
    stack('a');
    stack('b');

    printf("%i", isEmpty());
    printf("%c", unstack());
    printf("%i", isEmpty());
    printf("%c", unstack());
    printf("%i", isEmpty());
}