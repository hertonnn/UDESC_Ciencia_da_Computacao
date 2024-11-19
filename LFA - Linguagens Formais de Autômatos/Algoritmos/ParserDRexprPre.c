#include <ctype.h>
#include <stdlib.h>
#include <stdio.h>

#define TSTR 200

char str[TSTR], lookahead;
int pos = 0;

void nextToken()
{
    lookahead = str[pos];
    pos++;
}

void erro(char *msg)
{
   int i;

   printf("%s\n", msg); 
   puts(str);
   for (i = 0; i < pos; i++)
      putchar(' ');
   printf("^\n");
   exit(1);
}

void espacos()
{
    while (lookahead == ' ')
        nextToken();
}

void cons()
{
    if (isdigit(lookahead))
    {
        while (isdigit(lookahead))
            nextToken();
    }
    else
        erro("Erro sintático");
}
void expr ()
{
    espacos();
    switch (lookahead)
    {
       case '+': 
       case '-':
       case '*':
       case '/':
         nextToken(); expr(); expr();
         break;
       default:
         cons();
    }
         
}

int main()
{
   printf("Expressao:");
   fgets(str, TSTR, stdin);
   printf("%s", str);
   nextToken();
   expr();
   printf("\nExpressao Correta");
   return 0;
}