// PAG.50

#include <stdio.h>
#include <math.h>


// Método da bisseção
// Explora o teorema de bolzano, aonde f : [a,b]→R com f(a)·f(b)<0tem um zero no intervalo(a,b)
// Então é aproximado como zero da função o ponto medio de a até b, e verificado 
// se há mudança de sinal nesses dois intervalos que se formaram, caso haja em um desses, o novo ponto medio será o do próprio intervalo e assim sucessivamente até uma certa margem de erro.


#define MAX 200

// Funções teste 
double f(double x){
    return exp(x) - x - 2;
    // Função com uma raiz
}
double f2(double x){
    return sqrt(x) - cos(x);
}
double f3(double x){
    return - 4 + 8*x - 5*pow(x,2) + pow(x, 3);
    // Raízes x1 = 1 e x2 = 2 / Intervalo [0.5, 3.0]
    // Nessa função ocorre um problema no uso do método, uma vez ele converge para raiz 1 
    // e nunca para dois, pois não há mudança de sinal entre o primeiro ponto medio e o intervalo
    // 3. Isso é mais nítido ao estudar a curva no geogebra.
}

typedef double (*Func)(double); // ponteiro p/ função

// recebe a função, o intervalo em que a função é contínua, e a tolerancia TOL
void bissecao(double a, double b, Func f, double TOL){
    double A = a;
    double B = b;
    int n; 
    double xm;
    double tol; // tolerância durante o loop

    if(!f(A)*f(B) < 0){
        printf("\nErro: f(a)·f(b) > 0");
    }
    printf("n | A(n)   B(n)   xm(n) | f(a(n)) * f(x(n)) | precisão");

    for(n = 0; n < MAX; n++){

        xm = (A + B) / 2;
        tol = fabs((B - A)) / 2;


        printf("\n%i | %3.4f   %3.4f   %3.4f | %i | %3.4f", n, A, B, xm, f(A)*f(xm) < 0, tol);

        if(tol < TOL || f(xm) == 0 ){
            break;
        }
        else if(f(A)*f(xm) < 0){
            B = xm;
        }
        else if(f(B)*f(xm) < 0){
            A = xm;
        }
        
    }
    printf("\nA raiz da equação é aproximadamente: %f", xm);
    

}

int main(){
    bissecao(0.5, 3, f3, 0.00001);
}

// Conclusão do livro sobre o método: O método da bisseção tem a boa propriedade de garantia de
// convergência, bem como de fornecer uma simples estimativa do erro na aproximação calculada. Entretanto,
// a taxa de convergência linear é superada por outros métodos.  A construção de tais métodos está, normalmente, associada à iteração do ponto fixo, a qual exploramos na próxima seção