#include <stdio.h>
#include <stdlib.h>

typedef struct Cell
{
    float content;
    struct Cell *next;

}Cell;

Cell *push(Cell *head, float num){ 
    Cell *node;
    node = malloc(sizeof(Cell));
    
    if(node){
        node->content = num;
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


Cell *resolvePostfix(char *string){

    Cell *head = malloc(sizeof(Cell));
    int j = 0;
    char num[5];
    for(int i = 0; string[i] != '\0'; ++i){
        switch (string[i])
        {   
        case '+':

            float a = pop(&head)->content;
            float b = pop(&head)->content;

            head = push(head, a + b);
            break;
        case '-':
            float c = pop(&head)->content;
            float d = pop(&head)->content;

            head = push(head, d - c);
            break;
        case '*':
            float e = pop(&head)->content;
            float f = pop(&head)->content;

            head = push(head, e * f);
            break;
        case '/':
            float g = pop(&head)->content;
            float h = pop(&head)->content;

            head = push(head, h / g);
            break;
        case ' ': 
            if(j != 0) 
            head = push(head, atoi(num)); // atoi() Conversão de string to float
            j = 0;
            break;
        default: num[j++] = string[i];
            break;
        }
    }
    return pop(&head);
}

int main(){


    int option = 0;
    while (option != 3)
    {
        printf("\nTarefa de Pilha implementada em uma lista encadeada!\n\n");
        printf("Escolha uma opção:\n");
        printf("1 - Resolver notação polonesa reversa\n");
        printf("2 - Sair\n");
        scanf("%i", &option);

        switch (option)
        {
        case 1:
            char s[250];
            printf("Digite a expressão(no formato: 10 20 15 * + ):\n");
            
            scanf("\n%[^\n]s", s);
            printf("\nO resultado dê: %s é igual à %0.3f\n", s, resolvePostfix(s)->content);
            break;
        case 2:
            exit(1);
            break;
        default:
            exit(1);
            break;
        }
    }
}
