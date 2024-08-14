//PAG.65 e 84(aprofundado)

// Declaração basica
#define char alunos[10][10];


//Alocação dinâmica de Matrizes
//Matriz representada por um vetor simples

float *mat;        /* matriz representada por um vetor */ 
mat = (float*) malloc(m*n*sizeof(float)); 

//No entanto, somos obrigados a usar uma notação desconfortável, v[i*n+j],  para  acessar  os elementos, o que pode deixar o código pouco legível. Em que n é o num de colunas e i e j é a posição do elemento em uma matriz bidimensional

//Matriz representada por um vetor de ponteiros

int i; 
float **mat;     /* matriz representada por um vetor de ponteiros */ 

mat = (float**) malloc(m*sizeof(float*)); // aloco o vetor de ponteiros

for (i=0; i<m; i++){
    m[i] = (float*) malloc(n*sizeof(float)); // aloco linha a linha
}

//A  grande  vantagem  desta  estratégia  é  que  o  acesso  aos  elementos  é  feito  da  mesma  forma  que  quando  temos  uma  matriz  criada  estaticamente,  pois,  se  mat  representa  uma  matriz alocada segundo esta estratégia, mat[i] representa o ponteiro para o primeiro elemento da linha i, e, conseqüentemente, mat[i][j] acessa o elemento da coluna j da linha i.

//A liberação do espaço de memória ocupado pela matriz também exige a construção de um laço, pois temos que liberar cada linha antes de liberar o vetor de ponteiros:

for (i=0; i<m; i++){
    free(mat[i]);    
}
free(mat);