//PAG. 58
// Teorema de Bolzano: 
// Se f: [a,b]→R,y=f(x), é uma função contínua tal que f(a)·f(b)<0,
// (f(a) ou  f(b) é um valor negativo) então existe x ∈(a,b) tal 
// que f(x) = 0

#include <stdio.h>
#include <math.h>


//Primeiramente, observamos que resolver a equação e^x=x+ 2 é equivalente a resolver f(x) = 0 com f(x) =e^x−x−2. Agora, como f(−2)= e−2>0 e f(0) =−2<0, temos do teorema de Bolzano que existe pelo menos um zero def(x)no intervalo(−2,0). E, portanto, existe pelo menos uma solução da equaçãodada no intervalo(−2,0).


double f(double x){
    return exp(x) - x - 2;
}

int main(){
    //existe troca de sinal nos valores de f(x)
    printf("(%f, %f)", f(-2.0), f(0.0));
}