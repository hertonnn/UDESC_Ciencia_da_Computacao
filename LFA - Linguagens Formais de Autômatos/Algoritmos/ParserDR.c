/*****************************************************************
* Interpretador de expressoes Descendente Recursivo              *
* Exemplo p/ Disciplina de Compiladores                          *
* Cristiano Damiani Vasconcellos.                                *
******************************************************************/

/* Analisador sintatico descendente recusivo para expressoes infixas.
Gramatica:
S  ->E#
E  ->TE'
E' ->+TE'
     |-TE'
     |vazio
T  ->FT'
T' ->*FT'
     |/FT'
     |vazio
F  ->(E)
     |CONST */

#include <stdio.h>
#include <ctype.h>
#include <stdlib.h>
#define TSTR 200

#define ERRO   0x00
#define CONST  0x01
#define AD     0x02
#define SUB    0x03
#define MUL    0x04
#define DIV    0x05
#define APAR   0x06
#define FPAR   0x07
#define FIM    0x08


void expr();
void exprl();
void termo();
void termol();
void fator();
void reconhecer(int);
void erro();
int lex (char *, int *);


char str[50];
int  pos = 0;
int lookahead;

void partida()
{
   expr();
   reconhecer(FIM);
}

void expr()
{
   termo();
   exprl();
}
void exprl()
{
   if (lookahead == AD)
   {
      reconhecer(AD);
      termo();
      exprl();
   }
   else
      if(lookahead == SUB)
      {
         reconhecer(SUB);
         termo();
         exprl();
      }
}
void termo()
{
   fator();
   termol();
}
void termol()
{
   if (lookahead == MUL)
   {
      reconhecer(MUL);
      fator();
      termol();
   }
   else
      if (lookahead == DIV)
      {
         reconhecer(DIV);
         fator();
         termol();
      }
}
void fator()
{
   if (lookahead == APAR)
   {
      reconhecer(APAR);
      expr();
      reconhecer(FPAR);
   }
   else
      if (lookahead == CONST)
         reconhecer(CONST);
      else
         erro();
}
void reconhecer(int token)
{
   if (lookahead == token)
      lookahead = lex(str, &pos);
   else
      erro();
}

void erro()
{
   int i;

   puts(str);
   for (i = 0; i < pos; i++)
      putchar(' ');
   printf("^\n");
   exit(1);
}

int lex (char *str, int *pos)
{
   int estado = 0;
   char c;

   while (1)
   {
      c =  str[*pos];

      switch(estado)
      {
         case 0:
            if (isdigit(c))
            {
               (*pos)++;
               estado = 1;
            }
            else
               switch (c)
               {
                  case ' ':
                     (*pos)++;
                     break;
                  case '.':
                        (*pos)++;
                        estado = 2;
                        break;
                  case '+':
                        (*pos)++;
                        return AD;
                  case '-':
                        (*pos)++;
                        return SUB;
                  case '*':
                        (*pos)++;
                        return MUL;
                  case '/':
                        (*pos)++;
                        return DIV;
                  case '(':
                        (*pos)++;
                        return APAR;
                  case ')':
                        (*pos)++;
                        return FPAR;
                  case '\n':
                  case '\0':
                        return FIM;
                  default:
                        (*pos)++;
                        return ERRO;
               }
               break;
         case 1:
            if(isdigit(c))
               (*pos)++;
            else
               if (c == '.')
               {
                  estado = 3;
                  (*pos)++;
               }
               else
               {
                  //Adicionar constante na tabela de simbolos.
                  return CONST;
               }
               break;
         case 2:
            if (isdigit(c))
            {
               (*pos)++;
               estado = 3;
            }
            else
            {
               (*pos)++;
               return ERRO;
            }
            break;
         case 3:
            if (isdigit(c))
               (*pos)++;
            else
            {
               //Adicionar a constante na tabela de simbolos.
               return CONST;
            }
            break;
         default:
               printf("Lex:Estado indefinido");
               exit(1);
      }
   }
}

int main()
{
   printf("Expressao:");
   fgets(str, TSTR, stdin);
   lookahead = lex(str, &pos);
   partida();
   printf("Expressao Correta");
   return 0;

}
