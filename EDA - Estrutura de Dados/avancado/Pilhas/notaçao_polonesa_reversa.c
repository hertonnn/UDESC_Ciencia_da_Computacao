#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct cell
{
    float content;
    struct cell *next;

}cell;

cell *top;
float tam;

void create(){
    top = malloc(sizeof(cell)); // cell = cálula
    top->next = NULL;  
    tam = 0;
}

float isEmpty(){
    if(tam <= 0){
        return 1;
    }
    return 0;
}
void stack(float num){ // stack = empilha / node = nó
    
    if(isEmpty()){
        top->content = num;
    }
    else{
        cell *node;
        
        node = malloc(sizeof(cell));
        node->content = top->content;
        node->next = top->next;

        top->content = num;
        top->next = node;
    }
    tam++;
    
}

float unstack(){ 
   if(!isEmpty()){
        float num;
        num = top->content;
        if(top->next != NULL) top = top->next;
        tam--;
        return num; 
   }
   else{
    printf("Pilha Vazia!");
    exit(1);
    return 0;
   }
}

float resolvePostfix(char *string){
    
    create();

    int j = 0;
    char num[5];
    for(int i = 0; string[i] != '\0'; ++i){
        switch (string[i])
        {   
        case '+':
            float a = unstack();
            float b = unstack();

            stack(a + b);
            break;
        case '-':
            float c = unstack();
            float d = unstack();

            stack(d - c);
            break;
        case '*':
            float e = unstack();
            float f = unstack();

            stack(e * f);
            break;
        case '/':
            float g = unstack();
            float h = unstack();

            stack(h / g);
            break;
        case ' ': if(j != 0) stack(atoi(num));
            j = 0;
            // atoi() Conversão de string to float
            break;
        default: num[j++] = string[i];
            break;
        }
    }
    return unstack();
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
            printf("\nO resultado dê: %s é igual à %0.3f\n", s, resolvePostfix(s));
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
