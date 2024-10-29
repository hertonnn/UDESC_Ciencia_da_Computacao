/*****************************************************************
* Analisador Sintatico LR(1)                                     *
* Exemplo p/ Disciplina de Compiladores                          *
* Cristiano Damiani Vasconcellos                                 *
******************************************************************/

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>


/*Terminais */
#define ERRO   0
#define AD     1
#define SUB    2
#define MUL    3
#define DIV    4
#define CONST  5
#define APAR   6
#define FPAR   7
#define FIM    8

/* Aceitacao */
#define ACC     0xFFFFFFFF

// Nao terminais
#define EXPR  1
#define TERMO 2
#define FATOR 3

// Mascaras - Se o bit mais significativo do estado estiver ligado indica redução.
#define RED   0x80000000 // Ligar o bit que indica reducao 
#define NRED  0x7FFFFFFF // Isolar o numero da reducao

#define TAMPILHA 100

struct Pilha {
   int topo;
   int dado[TAMPILHA];
};

// Tabela sintatica

const unsigned int ACAO[16][8] ={{    0,     0,     0,     0,     4,     5,     0,     0},
                                 {    6,     7,     0,     0,     0,     0,     0,   ACC},
                                 {RED|3, RED|3,     8,     9,     0,     0, RED|3, RED|3},
                                 {RED|6, RED|6, RED|6, RED|6,     0,     0, RED|6, RED|6},
                                 {RED|7, RED|7, RED|7, RED|7,     0,     0, RED|7, RED|7},
                                 {    0,     0,     0,     0,     4,     5,     0,     0},
                                 {    0,     0,     0,     0,     4,     5,     0,     0},
                                 {    0,     0,     0,     0,     4,     5,     0,     0},
                                 {    0,     0,     0,     0,     4,     5,     0,     0},
                                 {    0,     0,     0,     0,     4,     5,     0,     0},
                                 {    6,     7,     0,     0,     0,     0,    15,     0},
                                 {RED|1, RED|1,     8,     9,     0,     0, RED|1, RED|1},
                                 {RED|2, RED|2,     8,     9,     0,     0, RED|2, RED|2},
                                 {RED|4, RED|4, RED|4, RED|4,     0,     0, RED|4, RED|4},
                                 {RED|5, RED|5, RED|5, RED|5,     0,     0, RED|5, RED|5},
                                 {RED|8, RED|8, RED|8, RED|8,     0,     0, RED|8, RED|8}};

const unsigned int DESVIO[16][3]={{1,  2,  3},
                                 { 0,  0,  0},
                                 { 0,  0,  0},
                                 { 0,  0,  0},
                                 { 0,  0,  0},
                                 {10,  2,  3},
                                 { 0, 11,  3},
                                 { 0, 12,  3},
                                 { 0,  0, 13},
                                 { 0,  0, 14},
                                 { 0,  0,  0},
                                 { 0,  0,  0},
                                 { 0,  0,  0},
                                 { 0,  0,  0},
                                 { 0,  0,  0},
                                 { 0,  0,  0}};

// Tabela de reducoes
const unsigned int REDUCAO[8][2] = {{EXPR,  3},  // E -> E + T reducao 1
                                    {EXPR,  3},  // E -> E - T reducao 2
                                    {EXPR,  1},  // E -> T     reducao 3
                                    {TERMO, 3},  // T -> T * F reducao 4
                                    {TERMO, 3},  // T -> T / F reducao 5
                                    {TERMO, 1},  // T -> F     reducao 6
                                    {FATOR, 1},  // F -> const reducao 7
                                    {FATOR, 3}}; // F -> (E)   reducao 8


/************************************************************************************
* int lex (char *str, int *pos)                                                     *
* procura o proximo token dentro de str a partir de *pos,incrementa o valor de *pos *
* a medida que faz alguma tranzicao de estados.                                     *
* Retorna o inteiro que identifica o token encontrado.                              *
*************************************************************************************/

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

/*****************************************************************
* void erro (char *erro, char *expr, int pos)                    *
* imprime a mensagem apontado por erro, a expressao apontada por *
* expr, e uma indicacao de que o erro ocorreu na posicao pos de  *
* expr. Encerra a execucao do programa.                          *
******************************************************************/

void erro (char *erro, char *expr, int pos)
{
   int i;
   printf("%s", erro);
   printf("\n%s\n", expr);
   for (i = 0; i < pos-1; i++)
      putchar(' ');
   putchar('^');
   exit(1);
}
/*****************************************************************
* void inicializa(struct Pilha *p)                               *
* inicializa o topo da pilha em -1, valor que indica que a pilha *
* esta vazia.                                                    *
******************************************************************/

void iniciliza(struct Pilha *p)
{
   p->topo = -1;
}

/*****************************************************************
* void insere (struct Pilha *p, int elemento                     *
* Insere o valor de elemento no topo da pilha apontada por p.    *
******************************************************************/

void insere (struct Pilha *p, int elemento)
{
   if (p->topo < TAMPILHA)
   {
      p->topo++;
      p->dado[p->topo] = elemento;
   }
   else
   {
      printf("estouro de pilha");
      exit (1);
   }
}
/*****************************************************************
* int remover (struct Pilha *p)                                  *
* Remove e retorna o valor armazenado no topo da pilha apontada  *
* por p                                                          *
******************************************************************/

int remover (struct Pilha *p)
{
   int aux;

   if (p->topo >= 0)
   {
      aux = p->dado[p->topo];
      p->topo--;
      return aux;
   }
   else
   {
      printf("Pilha vazia");
      exit(1);
   }
   return 0;
}

/*****************************************************************
* int consulta (struct Pilha *p)                                 *
* Retorna o valor armazenado no topo da pilha apontada por p     *
******************************************************************/


int consulta (struct Pilha *p)
{
   if (p->topo >= 0)
      return p->dado[p->topo];
   printf("Pilha vazia");
   exit(1);
}

/*****************************************************************
* reduzir(struct Pilha *p, int a)                                *
* Remove da pilha n estados, o valor de n e u numero de simbolos *
* senteciais que a producao de numero a possui. Este valor esta  *
* armazenado em REDUCOES[NRED&a-1][1], onde NRED e uma mascara   *
* de bits com o bit mais significativo ligado.                   *
* Retorna o inteiro correspondente ao nao-terminal para o qual   *
* o handle foi reduzido.                                         *
* Variaveis Globais Consultadas: REDUCOES                        *
******************************************************************/

int reduzir(struct Pilha *p, int a)
{
   int i;

   for (i = 0; i < REDUCAO[(NRED&a)-1][1]; i++)
      remover(p);
   return REDUCAO[(NRED&a)-1][0];
}

/**********************************************************************
* void parser (char *expr)                                            *
* Verifica se a string apontada por expr esta sintaticamente correta. *                                                       *
* Variaveis Globais Consultadas: ACAO e DESVIO                        *
***********************************************************************/

void parser(char *expr)
{
   struct Pilha p;
   int a, s, aux, nter, pos;

   iniciliza(&p);
   pos = 0;
   s = 0;
   insere(&p, 0);
   a = lex(expr, &pos);
   while (ACAO[s][a-1] != ACC)
   {
	  //printf ("=> Estado=%d Token=%d Tab=%x\n", s, a, ACAO[s][a-1]); 
      if (ACAO[s][a-1] == 0)
         erro("Erro sintatico", expr, pos);
      if ((RED & ACAO[s][a-1]) == 0)
      {
         insere(&p, ACAO[s][a-1]);
         a = lex(expr, &pos);
         if (a == ERRO)
            erro("Token invalido", expr, pos);
      }
      else
      {
         nter = reduzir(&p, ACAO[s][a-1]);
         aux = consulta(&p);
         insere(&p, DESVIO[aux][nter-1]);
      }
	  s = consulta(&p);
   } 
}

int main()
{
   char expr[200];

   printf("\nDigite uma expressao: ");
   gets(expr);
   parser(expr);
   printf("Ok");
   return 0;
}


