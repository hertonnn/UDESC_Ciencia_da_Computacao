//PAG.73

#include <stdio.h>

typedef struct ponto
{
    float x;
    float y;
}Ponto;

float area(int n, Ponto *p){
    int i, j;
    float a = 0;

    for(i = 0; i < n; i++){

        j = (i + 1) % n; // próximo ponto, sendo que no ultimo ele zera de forma circular, ou seja,  i + 1 e se i == n então j = 0;(incremento circular)

        a += (p[j].x - p[i].x)*(p[j].y + p[i].y)/2;
    }

    if(a < 0){
        return -a;
    }
    return a;
}

int main(){
    //testando só
    Ponto p[3] = {{1.0, 2.0}, {3.0, 2.0}, {3.0, 4.0}}; // um triangulo retangulo de área 4
    // mais hard 
    Ponto p2[5] = {{2.0, 4.0}, {6.0, 5.0}, {4.0, 0.0}, {1.0, 1.0}, {4.0, 3.0}};

    // um quadrado
    Ponto p1[4] = {{8,2}, {8,4}, {10,4}, {10,2}};

    float a = area(3, p);
    float a2 = area(5, p2);
    float a1 = area(4, p1);

    printf("\nA Área da do polígono 1 é: %3.2f", a);
    printf("\nA Área da do polígono 2 é: %3.2f", a2); // saindo errado, acredito que pq seja um polígono irregular 
    printf("\nA Área da do polígono 2 é: %3.2f", a1);

    return 0;
}

