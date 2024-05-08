#include <stdio.h>

// Questão 1 a) - certo
// int main(){
//     int b[] = {10,20,30,40};
//     int *bPt;
    
//     bPt = b;

//     printf("%i, %i", b[3], *(bPt + 3));
// }

// Questão 1 b) - certo
// c) - certo
// #include <stdio.h>   //* Algoritmo 1*

// #include <locale.h>

// #define ARRAY_SIZE 4

// int main (void){

//   int b[] = { 10, 20, 30, 40 };

//   int *bPtr = &b[0];


//   int i, desloca;

//   setlocale(LC_ALL, "Portuguese");

//   for (i = 0; i < ARRAY_SIZE; ++i){

//       printf ("\nb[%i] = %d", i, b[i]);
//   }


//   printf ("\nA notação de ponteiro ou deslocamento:\n");

//   for (desloca = 0; desloca < ARRAY_SIZE; ++desloca){

//     printf ("\n*(b + %i) = %d", desloca, *(b + desloca));
//   }

//   printf ("\nNotação de índice no ponteiro:\n");


//   for (i = 0; i < ARRAY_SIZE; ++i){

//      printf ("bPtr[%i] = %d\n", i, bPtr[i]);
//   }


//   printf ("\nNotação de ponteiro:\n");


//   for (desloca = 0; desloca < ARRAY_SIZE; ++desloca){

//      printf ("*(bPtr + %i) = %d\n", desloca, *(bPtr + desloca));
//   }

// return 0;

// }

// Questão 3 - f) certa
// g) - certa
// h) - incorreta

#include <stdio.h>   //* Algoritmo 2

#include <stdlib.h>

#define MAX_STRINGS 5

#define MAX_STRING_LENGTH 20


int main() {   

    char *vector[MAX_STRINGS];

    for (int i = 0; i < MAX_STRINGS; ++i) {

        vector[i] = (char *)malloc(MAX_STRING_LENGTH * sizeof(char));

        if (vector[i] == NULL) {

            printf(" falhou\n");            

            return 1;

        }

    }

    printf("Enter %d strings for the vector:\n", MAX_STRINGS);

    for (int i = 0; i < MAX_STRINGS; ++i)

        scanf("%s", vector[i]);


    printf("The vector of strings is:\n");

    for (int i = 0; i < MAX_STRINGS; ++i)

        printf("%s\n", vector[i]);

    

    for (int i = 0; i < MAX_STRINGS; ++i)

        free(vector[i]);


    return 0;

}