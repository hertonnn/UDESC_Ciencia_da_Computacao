#include <stdlib.h>
#include <stdio.h>

/*
Dado um vetor de números inteiros v de tamanho n e um número k, retorne verdadeiro
se a soma de qualquer par de números em v for igual a k.
○ Exemplo: dado v = [10,15,3,7] e k = 17, a saída deve ser true, pois 10 + 7 é 17
*/
int sumK(int *v, int tamV, int k);

int main(){
    int v[] = {10,15,3,7};
    int k = 17;

    printf("\nRetorno da função: %i", sumK(v, 4, k));
}

//Primeira tentativa: Força bruta
/*
int sumK(int *v, int tamV, int k){
    for(int i = 0; i < tamV - 1; i++){
        for(int j = 0; j < tamV; j++){
            int sum = v[i] + v[j + 1];
            if(k == sum && i != j + 1){
                printf("%i + %i = %i", v[i], v[j + 1], k);
                return 1;
            }
        }
    }
    return 0;
}
*/
//Segunda tentativa: 
/*
A cada iteração eu verifico qual número que somada ao v[i] atual dá o k e
guardo ele junto ao v[i] em uma lista de tuplas 

v[] = {10,15,3,7}
>> v[0] = 10
>> k = 17
>> 10 + x = k
>> x = k - 10 = 7
>> a tupla será {10, 7}

A cada nova iteração verificamos se o v[i] está nas tuplas, caso esteja eu
já retorno true, ou seja, existe um valor que somado ao cara atual dê k.
*/

typedef struct node{
    int key;
    int value;
    struct node *next;
} node;

int sumK(int *v, int tamV, int k){

    node *head = NULL;
    head = (node *) malloc(sizeof(node)); 
    head -> next = NULL;

    int num = -1 * v[0] + k;
    head -> key = v[0];
    head -> value = num;

    node *aux = head;
    for(int i = 1; i < tamV; i++){
        num = -1 * v[i] + k;

        while (aux != NULL)
        {
            
        }
        
    }    

    return 0;
}



