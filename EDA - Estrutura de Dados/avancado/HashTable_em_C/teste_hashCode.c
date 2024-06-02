#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>


/*
Código criado para testar várias possibilidades de string na função de hash 
e assim tentar achar combinações iguais. 
*/
#define HASH_SIZE 100 

int hashCode(char *key);

int main(){
    srand(time(NULL));   // Initialization, should only be called once.

    char *validchars = "abcdefghijklmnopqrstuvwxyz";
    char *s1 = "Silveira"; // String comparada
    char *s2 = ( char * ) malloc ( (strlen(s1) + 1) * sizeof(char));

    int num = 0;

    while (hashCode(s1) != hashCode(s2))
    {
        int tam = rand()%strlen(validchars) - 1;
        for(int i = 0; i < tam; i++){
            int index = rand()%strlen(validchars);
            s2[i] = validchars[index];
            s2[i+1] = 0x0;
        }

        printf("String comparada %s != %s\n", s1, s2);
        num++;
    }
    printf("String com para %s == %s\n\n", s1, s2);
    printf("O hash de %s é: %i sendo igual ao da string %s: %i", s1, hashCode(s1), s2, hashCode(s2));
    printf("\nForam testados mais de %i casos\n", num);

}


int hashCode(char *key){
    unsigned long hash = 2024; // inteiro sem sinal com capacidade de armazenamento maior
    unsigned int c;

    while (c = *key++) // char a char da cadeia de chars :)
    {
        hash = ((hash<<5) + hash) + c; // hash * 33 + c
    }
    return hash % HASH_SIZE;
}