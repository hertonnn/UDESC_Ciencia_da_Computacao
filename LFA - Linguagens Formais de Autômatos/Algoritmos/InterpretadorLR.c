/*****************************************************************
* Interpretador LR(1)                                            *
* Exemplo p/ Disciplina de Compiladores                          *
* Cristiano Damiani Vasconcellos                                 *
******************************************************************/

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

/* Terminais - Os 3 byte menos significativos indicam a entrada na tabela de simbolos (de constantes)
   o quarto byte indica qual o terminal */
#define ERRO   0
#define AD     1
#define SUB    2
#define MUL    3
#define DIV    4
#define CONST  5
#define APAR   6
#define FPAR   7
#define FIM    8

#define ACC    0xFFFFFFFF

// Nao terminais 
#define EXPR  1
#define TERMO 2
#define FATOR 3

// Mascaras
#define RED   0x80000000
#define NRED  0x7FFFFFFF
#define ATRIB 0x00FFFFFF

// Limites
#define TAMPILHA 100
#define TAMSTR   200

struct Token
{
	int token;     // Identifica o token
	double valor;  // Armazena o valor da constante qunado o token trpresentar uma constante numérica
};

struct Pilha {
   int topo;
   int dado[TAMPILHA];
   double val[TAMPILHA];
};

struct Lista
{
   double cons;
   struct Lista *prox;
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
const int REDUCAO[8][2] = {{EXPR,  3}, // E -> E + T reducao 1
                           {EXPR,  3}, // E -> E - T reducao 2
                           {EXPR,  1}, // E -> T     reducao 3
                           {TERMO, 3}, // T -> T * F reducao 4
                           {TERMO, 3}, // T -> T / F reducao 5
                           {TERMO, 1}, // T -> F     reducao 6
                           {FATOR, 1}, // F -> const reducao 7
                           {FATOR, 3}};// F -> (E)   reducao 8

// cabeca da lista da tabela de simbolos
struct Lista *cabTab = NULL;


/*******************************************************************************************
* struct Token lex (char *str, int *pos)                                                   *
* procura o proximo token dentro de str a partir de *pos,incrementa o valor de *pos        *
* a medida que faz alguma tranzicao de estados.                                            *
* Retorna uma estrutura de dados que representa o token reconhecido e o valor da constante *
* da contante numerica (quando for o caso).                                                *
********************************************************************************************/

struct Token lex (char *str, int *pos)
{
   int estado = 0, pos_t = 0;
   struct Token t;
   char c;
   char token[TAMSTR];

   while (1)
   {
      token[pos_t] = c = str[*pos];
      pos_t++;
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
						t.token = AD;
                        return t;
                  case '-':
                        (*pos)++;
                        t.token = SUB;
                        return t;
                  case '*':
                        (*pos)++;
                        t.token = MUL;
                        return t;
                  case '/':
                        (*pos)++;
                        t.token = DIV;
                        return t;
                  case '(':
                        (*pos)++;
                        t.token = APAR;
                        return t;
                  case ')':
                        (*pos)++;
                        t.token = FPAR;
                        return t;
                  case '\0':
                        t.token = FIM;
                        return t;
                  default:
                        (*pos)++;
						t.token - ERRO;
                        return t;
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
                  token[pos_t] = '\0';
				  t.token = CONST;
				  t.valor = atof(token);
                  return t;
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
			   t.token = ERRO;
               return t;
            }
            break;
         case 3:
            if (isdigit(c))
               (*pos)++;
            else
            {
               token[pos_t] = '\0';
               t.token = CONST;
			   t.valor = atof(token);
			   return t;
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

void inicializa(struct Pilha *p)
{
   p->topo = -1;
}

/*****************************************************************
* void empilha (struct Pilha *p, int elemento                     *
* Insere o valor de elemento no topo da pilha apontada por p.    *
******************************************************************/

void empilha (struct Pilha *p, int elemento)
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
* int desempilha (struct Pilha *p)                               *
* Remove e retorna o valor armazenado no topo da pilha apontada  *
* por p                                                          *
******************************************************************/

int desempilha (struct Pilha *p)
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
/******************************************************************************
* void acao (struct Pilha *p, int red, double valor)                          *      
* Executa a reducao correspondente a reducao numero red, valor contem o valor *
* de tokens numericos (const)                                                 *
* Variaveis Locais Consultadas: REDUCAO                                       *
*******************************************************************************/

void acao(struct Pilha *p, int red, double valor)
{
   int ntopo = p->topo - REDUCAO[red-1][1] + 1;

   switch (red)
   {
      case 1: // E -> E + T
            //printf("%lf + %lf\n", p->val[p->topo-2], p->val[p->topo]);
            p->val[ntopo] = p->val[p->topo-2] + p->val[p->topo];
            break;
      case 2: // E -> E - T
            //printf("%lf - %lf\n", p->val[p->topo-2], p->val[p->topo]);
            p->val[ntopo] = p->val[p->topo-2] - p->val[p->topo];
            break;
      case 3:
            break;
      case 4: // T -> T * F
            //printf("%lf * %lf\n", p->val[p->topo-2], p->val[p->topo]);
            p->val[ntopo] = p->val[p->topo-2] * p->val[p->topo];
            break;
      case 5: // T -> T / F
            //printf("%lf / %lf\n", p->val[p->topo-2], p->val[p->topo]);
            p->val[ntopo] = p->val[p->topo-2] / p->val[p->topo];
            break;
      case 6:
            break;
      case 7: // F -> const
            p->val[ntopo] = valor;
            break;
      case 8: // F -> (E)
            p->val[ntopo] = p->val[p->topo-1];
            break;
      default:
            printf("Reducao invalida");
            exit(1);
   }
}

/**************************************************************************************************************************
* reduzir(struct Pilha *p, int a, double valor)                                                                           *
* Remove da pilha n estados, o valor de n eh o numero de simbolos senteciais que a producao de numero a possui.           *
* Este valor esta armazenado em REDUCOES[NRED&a-1][1], sendo NRED uma mascara de bits com o bit mais significativo ligado.*
* valor contem o valor de tokens numericos (const).                                                                       *
* Retorna o inteiro correspondente ao nao-terminal para o qual o handle foi reduzido.                                     *
* Variaveis Globais Consultadas: REDUCOES                                                                                 *
***************************************************************************************************************************/

int reduzir(struct Pilha *p, int a, double valor)
{
   int i, red;

   red = NRED&a;
   acao (p, red, valor);
   for (i = 0; i < REDUCAO[red-1][1]; i++)
      desempilha(p);
   return REDUCAO[NRED&a-1][0];
}

/*********************************************************************
* void parser (char *expr)                                           *
* Verifica se a string apontada por expr esta sintaticamente correta.*
* Variaveis Globais Consultadas: ACAO e DESVIO                       *
**********************************************************************/

void parser(char *expr)
{
   struct Pilha p;
   int token, lookahead, s, aux, nter, pos;
   struct Token t;

   inicializa(&p);
   pos = 0;
   s = 0;
   empilha(&p, 0);
   t = lex(expr, &pos);
   if (t.token == ERRO)
      erro("Token invalido", expr, pos);
   lookahead = t.token;
   while(1)
   {
      //printf ("=> Estado=%d Token=%d Tab=%x\n", s, lookahead, ACAO[s][lookahead-1]);
      if (ACAO[s][lookahead-1] == ERRO)
         erro("Erro sintatico", expr, pos);
      if ((RED & ACAO[s][lookahead-1]) == 0) //Transicao de estado
      {
         empilha(&p, ACAO[s][lookahead-1]);
         t = lex(expr, &pos);
         if (t.token == ERRO)
            erro("Token invalido", expr, pos);
         lookahead = t.token;
      }
      else // Reducao
      {
         if (ACAO[s][lookahead-1] == ACC)
            break;
         nter = reduzir(&p, ACAO[s][lookahead-1], t.valor);
         aux = consulta(&p);
         empilha(&p, DESVIO[aux][nter-1]);
      }
	  s = consulta(&p);
   }
   printf("= %lf\n", p.val[1]);
}

int main()
{
   char expr[TAMSTR];

   printf("\nDigite uma expressao: ");
   gets(expr);
   parser(expr);
   return 0;
}
