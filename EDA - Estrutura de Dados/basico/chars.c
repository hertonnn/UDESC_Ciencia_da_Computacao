//PAG. 57
#include <string.h>
//Funções  análogas  às  funções  comprimento, copia  e  concatena  são  disponibilizadas  pela  biblioteca  padrão  de  C.  As  funções  da  biblioteca  padrão  são,  respectivamente,  strlen, strcpy  e  strcat,  que  fazem  parte  da  biblioteca  de  cadeias  de  caracteres  (strings), string.h. 
//exercicio 1
int letra(char c){
    if((c >= 'A') && (c <= 'Z') || (c >= 'a') && (c <= 'z')){
        return 1;
    }
    else{
        return 0;
    }
}
//exercicio 2

char maiuscula(char c){
    if((c >= 'a') && (c <= 'z')){
        int var = (int) c;
        var = var - 32;

        return (char) var;
    }
    else{
        return c;
    }
}
//exemplo concatenar duas strings
void concatena(char *dest, char *orig){

    int i = 0;

    for(i; dest[i] != '\0'; i++){}

    int j = 0;
    for(j; orig[j] != '\0'; j++){
        dest[i] = orig[j];
        i++;
    }

    dest[i] = '\0';
}
//exemplo aloca
char* duplica(char* s){
    char* copia;
    copia = malloc(sizeof(s));

    int i = 0;
    for(i; s[i] != '\0'; i++){
        copia[i] = s[i];
    }
    copia[i] = '\0';

    return s;
}

int main(){

    int saida1 = letra(';'); 
    int saida2 = maiuscula('B');

    printf("\n%d", saida1);
    printf("\n%c", saida2);

    char v1[] = "PUC";
    char v2[] = "Rio";

    concatena(v1, v2);
    printf("\n\n A string final é: %s", v1);

    char s[] = "COPIA";
    printf("\n%s", duplica(s));
    printf("\n%i", sizeof(s));

    printf("%s", "\n\n");

    //OBS: 
    char s1[] = "Rio de Janeiro"; //VETOR LOCAL, POSSUI 15 BYTES E PODE SER ALTERADO.
    char* s2 = "Rio de Janeiro"; //ENDEREÇO INICIALIZADO APONTANDO PARA A CONSTANTE STRING, NÃO PODE SER ALTERADO E POSSUI 8 BYTES.

    s1[0] = " "; // isso funciona
    s2[0] = " "; // isso da erro de seg.

    printf("%i\n", sizeof(s1));
    printf("%i", sizeof(s2));
}
