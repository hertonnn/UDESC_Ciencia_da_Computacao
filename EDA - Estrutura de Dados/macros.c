//PAG. 46
#include <stdio.h>
//Definindo um Macro, que é uma diretiva de definição com parametros
#define MAX(a,b) ((a) > (b) ? (a) : (b))

int main(void){
    printf("%d", MAX(10,9));
}
//Atenção: O Compilador indicará um erro na linha em que se utiliza a macro e não na linha de definição da  macro  (onde  efetivamente  encontra-se  o  erro).

//Como  regra  básica  para  a  definição  de  macros,  devemos  envolver cada parâmetro, e a macro como um todo, com parênteses. 