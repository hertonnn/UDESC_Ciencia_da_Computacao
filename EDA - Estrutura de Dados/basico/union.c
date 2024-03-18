//PAG.76

union exemplo {
    int i;
    char c;
}

union exemplo var; // nessa variavel i e c ocupam o mesmo endereço 
// memória

// acessando

var.i = 3; 

// ou 

var.c = 'x';

// No entento, apenas um dos elementos pode estar armazenado, sobreescrevendo o outro entt



