#include<stdio.h>

#define PESS "pessoaB.dat"


struct PF{
    char nome[50];
    char cpf[8];
    int idade;
};
typedef struct PF pf;

int main (){
  
  int num;

  FILE *arq;

  pf pes;

  int i;

/*   trecho para gerar um arquivo binário */

//   arq = fopen (PESS, "ab");

//   if (arq==NULL)    {

//       perror ("Erro ao abrir arquivo.");

//       exit (1);

//     }


//   for (i = 0; i < 3; i++)    {

//       printf ("Nome : ");

//       scanf ("%[^\n]", pes.nome);

//       getchar();

//       printf ("CPF : ");

//       scanf ("%s", pes.cpf);

//       getchar ();

//       printf ("Idade: ");

//       scanf ("%i", &pes.idade);

//       getchar ();

//       printf ("\n");


//       fwrite (&pes, sizeof(pf),1, arq);

//   }

//       fclose (arq);

    printf("\n\nEscolha um número de 1 a %i:\n", i);

    scanf("%d", &num);

    fseek (arq, (-num)* sizeof (pes), SEEK_END);

    fread (&pes, sizeof (pes), 1, arq);

    printf ("Nome:%s cpf:%s idade:%i\n", pes.nome, pes.cpf, pes.idade);

   fclose (arq);

}