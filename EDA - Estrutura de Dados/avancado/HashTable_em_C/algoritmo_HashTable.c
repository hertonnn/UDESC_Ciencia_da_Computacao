#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

/*
Neste capítulo, vamos estudar as estruturas de dados conhecidas como tabelas de dispersão (hashtables), que, se bem projetadas, podem ser usadas para buscar um elemento da tabela em ordem constante: O(1). O preço pago por essa eficiência será um uso maior de memória, mas, como veremos, esse uso excedente não precisa ser tão grande, e é proporcional ao número de elementos armazenados.
*/

#define HASH_SIZE 100 

typedef struct node
{
    char *value;
    char *key;
    struct node *next;
}*Node;

typedef struct Hash{
    Node list[HASH_SIZE];  
}*Hash;

int hashCode(char *key);
void insertNode(Hash hash, char *value, char *key);
Node search(Hash hash, char *key);
Node createNode(char *value, char *key);

int main(){
    Hash hash = malloc(sizeof(struct Hash));
    insertNode(hash, "Herton", "Silveira");
    insertNode(hash, "Fabio", "Akita");
    insertNode(hash, "Carlos", "skcnzj"); // Essa chave tem colisão com Silveira
    insertNode(hash, "Antonio", "vwm");  // Essa chave tem colisão com Akita

    printf("%s\n", search(hash, "Silveira")->value);
    printf("%s\n", search(hash, "Akita")->value);
    printf("%s\n", search(hash, "skcnzj")->value);
    printf("%s\n", search(hash, "vwm")->value);

    free(hash);
}

// Algoritimo simples de hash apenas para exemplificar 
int hashCode(char *key){
    unsigned long hash = 2024; // inteiro sem sinal com capacidade de armazenamento maior
    unsigned int c;

    while (c = *key++) // char a char da cadeia de chars :)
    {
        hash = ((hash<<5) + hash) + c; // hash * 33 + c
    }
    return hash % HASH_SIZE;
}

Node createNode(char *value, char *key){
    Node node = malloc(sizeof(struct node));
    node->key = key;
    node->value = value;
    node->next = NULL;

    return node;
}

void insertNode(Hash hash, char *value, char *key){
    
    unsigned int index = hashCode(key);
    Node node = hash->list[index];

    if(node == NULL){
        hash->list[index] = createNode(value, key);
    }
    else{
        // Se existe uma colisão, ou seja,  o hash index tenha saídas iguais. Ele 
        // caminha por esses itens de mesmo hash até o ultimo e assim indexa o atual
        // ao fim da lista
        while (node->next) 
        {
            node = node->next;
        }
        node->next = createNode(value, key);
    }
    
}
Node search(Hash hash, char *key){

    unsigned int index = hashCode(key);
    
    Node node = hash->list[index];

    if(node){
        while (node)
        {
            if(strcmp(node->key, key) == 0){
                return node;
            }
            printf("\nColisão achada no index %i\n", hashCode(key));
            node = node->next;
        }
        
    }
    return NULL;
}

