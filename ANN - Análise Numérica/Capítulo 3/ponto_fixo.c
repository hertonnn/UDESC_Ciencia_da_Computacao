//PAG.56
#include <stdio.h>
#include <math.h>

//ITERAÇÃO DE PONTO FIXO
// - Método Babilônico - 

//Função recursiva que recebe o valor(A) que quero saber a raiz, uma 
//aproximação para essa raíz (r) e um criterio de 
// parada, nesse caso tolerância

double raiz(double A, double r, double TOL){
    double x;

    // média entre o chute(r) e o valor(A)
    x = (r/2) + (A/(2*r));
    printf("\n%f %f", r, x);

    if(fabs(x-r) < TOL){
        return x;
    }
    else{
        raiz(A, x, TOL);
    }
}


// Exemplo 3.3.3
// Zero da função f(x) = xe^x - 10
// para isso desenvolvemos a formula para obter g1(x) e g2(x) e colocamos um alfa na equação de 0.5 e 0.05


// tipo ponteiro para função
typedef double (*Func)(double);

double f(double x){
    return x*exp(x) - 10;
}

double g1(double x, Func f){
    return x - 0.5*f(x);
}
double g2(double x, Func f){
    return x - 0.05*f(x);
}

// Exemplo 3.3.4
// Mostre que o teorema do ponto fixo sea aplica a função g(x) = cos(x) no intervalo [0.5, 1]

double g(double x){
    return cos(x);
}

// Exercicios 3.3.10
// (Fluidos) Na hidráulica, o fator de atrito de Darcy é dado pela implicitamente pela equação de Colebrook-White:
double Col_White(){
    //RESP. 0.0431266
    // Todas as unidades de medidas estarão em metros
    double f = 0.01; // fator de atrito (chute despretensioso),  pois esse é o cara que eu quero
    double e = 0.002; // rugosidade do tubo em metros
    double Rh = 0.05; // raio hidraulico
    double Re = 10000; // numero de Reynolds
    
    int iteracoes = 10; 

    int n = 0;
    for(n; n < iteracoes; n++){
        f = -2*log10(e/(14.8*Rh) + (2.51*f)/Re);
        printf("\n%i | %f", n,f);
    }
    printf("\n\nValor aproximado para o fator de atrito é: %f", f);

}

int main(){
    // 
    double TOL = 0.0001;
    printf("\nA aproximação para a raiz é: %f", raiz(10, 1, TOL));


    // teste exemplo 3.3.3
    int n;
    double x1 = 1.7;
    double x2 = 1.7;

    printf("\n\nn |  x1(n)    x2(n)  ");

    for(n = 0; n < 5; n++){
        printf("\n%i | %3.3f    %3.3f ", n, x1, x2);
        x1 = g1(x1, f);
        x2 = g2(x2, f);
    }

    //teste exemplo 3.3.4

    int i;
    double x = 0.7; // chute inicial da raiz
    double xe = 0.7390851605; // raiz aproximada
    double en = 1; // erro(1 é só pra declarar um valor e n dar erro)
    double TOL1 = 0.001; // tolerância para o erro 


    printf("\n\n n(i) |    x1(i)   | En := |x(n) - x*|");

    for(i = 1; en > TOL1; i++){
        en = fabs(x - xe); // para o mostrar o decaimento do erro
        printf("\n%i    |   %3.5f     | %3.5f", i, x, en);
        x = g(x);
    }

    //Teste do Exercicio 3.3.10
    Col_White();

}