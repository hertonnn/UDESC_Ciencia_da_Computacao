//Pilha implementada em uma lista encadeada 2
//Exercícios 5
//Implemente uma pilha em uma lista encadeada sem célula-cabeça. A pilha será dada pelo endereço da primeira célula da lista (que é o topo da pilha).
//Reescreva as funções bemFormada e infixaParaPosfixa armazenando a pilha em uma lista encadeada.

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct cell
{
    char content;
    struct cell *next;

}cell;

cell *top;
int tam;

void create(){
    top = malloc(sizeof(cell)); // cell = cálula
    top->next = NULL;  
    tam = 0;
}

int isEmpty(){
    if(tam <= 0){
        return 1;
    }
    return 0;
}
void stack(char letter){ // letter = letra / stack = empilha / node = nó
    
    if(isEmpty()){
        top->content = letter;
    }
    else{
        cell *node;
        
        node = malloc(sizeof(cell));
        node->content = top->content;
        node->next = top->next;

        top->content = letter;
        top->next = node;
    }
    tam++;
    
}

char unstack(){ 
    char c;
    c = top->content;
    if(top->next != NULL) top = top->next;
    tam--;
    return c;    
}

// 1 - bemFormada Considere o problema de decidir se uma dada sequência de parênteses e colchetes está bem-formada (ou seja, parênteses e colchetes são fechados na ordem inversa àquela em que foram abertos).  Por exemplo, a sequência:

// ( ( ) [ ( ) ] )


int goodFormat(char *string){
    char c;

    create();
    for(int i = 0; string[i] != '\0'; i++){
        switch (string[i])
        {
        case ')':
            if(isEmpty()) return 0;
            c = unstack();
            if(c != '(') return 0;
            break;
        case ']':
            if(isEmpty()) return 0;
            c = unstack();
            if(c != '[') return 0;
            break;
        case '}':
            if(isEmpty()) return 0;
            c = unstack();
            if(c != '{') return 0;
            break;
        default: stack(string[i]);
            break;
        }
    }
    return isEmpty();
}

// 2 - Nosso problema:  traduzir para notação posfixa a expressão infixa armazenada em uma string inf.  

char *infixToPostfix(char *infix){
    char *postfix;
    int indexPostfix = 0;
    int n = strlen(infix);

    create();
    postfix = malloc((n+1)*sizeof(char)); //alocando a string de saida
    stack(infix[0]); // empillha '('

    for(int indexString = 1; infix[indexString] != '\0'; ++indexString){
        char x;
        switch (infix[indexString])
        {
        case '(': stack(infix[indexString]);
            break;
        case ')': x = unstack();
            while(x != '('){
                postfix[indexPostfix++] = x; // usa i = 0 e dps adiciona i + 1
                x = unstack();
            } 
            break;
        case '+':
        case '-': x = unstack();
            while(x != '('){
                postfix[indexPostfix++] = x;
                x = unstack();
            }
            stack(x);
            stack(infix[indexString]);
            break;
        case '*':
        case '/': x = unstack();
            while(x != '(' && x != '+' && x != '-'){
                postfix[indexPostfix++] = x;
                x = unstack();
            }
            stack(x);
            stack(infix[indexString]);
            break;
        default: postfix[indexPostfix++] = infix[indexString];
            break;
        }
    }
    postfix[indexPostfix] = '\0';
    return postfix;
}

int main(){
    
    int option = 0;
    while (option != 3)
    {
        printf("\nTarefa de Pilha implementada em uma lista encadeada!\n\n");
        printf("Escolha uma opção:\n");
        printf("1 - Expressão bem-formada de parênteses, colchetes e chaves\n");
        printf("2 - Notação polonesa\n");
        printf("3 - Sair\n");
        scanf("%i", &option);

        switch (option)
        {
        case 1:
            char s[250];
            printf("Digite a expressão(no formato: ([({})]):\n");
            scanf("%s", s);

            if(goodFormat(s) == 1) printf("\nBem fomada! (:\n\n");
            else printf("\n\nMal fomada! ):\n\n");
            break;
        case 2:
            char s1[250];
            printf("Digite a expressão(no formato (A+B*C)):\n");
            scanf("%s", s1);

            printf("\nA expressão Pós-fixa é: %s\n", infixToPostfix(s1));
            break;
        default:
            break;
        }
    }
}
