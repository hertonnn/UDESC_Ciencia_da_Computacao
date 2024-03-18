//PÁG.47

int main(){
    int v[10];
    v[0] = 1;
    v[1] = 2;
    //...

    // A variável v(sem indexação) indica o endereço de memória do inicio do vetor
    printf("Endereço de memória do vetor v: %p", v);

    //Passar  um  vetor  para  uma  função  consiste  em  passar  o  endereço  da  primeira  posição  do  vetor. 
    // Int func(arg1, arg2, int* vetor){}
}
