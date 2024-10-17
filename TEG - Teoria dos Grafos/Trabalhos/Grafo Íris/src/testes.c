#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>


typedef struct Iris{
    int variety; // 0 - setosa, 1 - versicolor, 2 - virginica
    float slength;
    float swidth;
    float plength;
    float pwidth;
}*Iris;

int parseVariety(char* variety){
    if (strcmp(variety, "Setosa") == 0) return 0;
    else if (strcmp(variety, "Versicolor") == 0) return 1;
    else if (strcmp(variety, "Virginica") == 0) return 2;
    return -1; // Espécie não corresponde.
}


// Distancia euclideana entre dois pontos no espaço quadridimensional.
// Pensando no dataset temos: (sepal.length,sepal.width,petal.length,petal.width) = (x,y,z,w).
float EuclideanDist(Iris a, Iris b){
    float result = pow((b->slength - a->slength), 2) + pow((b->swidth - a->swidth), 2) + pow((b->plength - a->plength), 2) + pow((b->pwidth - b->pwidth), 2);

    result = sqrt(result);

    return result;
}

Iris* readDataset(FILE *file){

    if(file == NULL){
        fprintf(stderr, "Error opening file\n");
        exit(1);
    }
  
    char line[100];
    fgets(line, sizeof(line), file);
    int count = 0;

    Iris *dataset = malloc(150 * sizeof(Iris)); // 150 linhas no dataset

    while (fgets(line, sizeof(line), file))
    {
        Iris iris = malloc(sizeof(Iris));
        char varietyStr[20];

        int parsed = sscanf(line, "%19[^,],%f,%f,%f,%f", varietyStr, &iris->slength, &iris->swidth, &iris->plength, &iris->pwidth);
        
        // Checando se foram 5 valores achados em cada linha
        if (parsed == 5) {
            iris->variety = parseVariety(varietyStr);
            dataset[count++] = iris;
        } else {
            fprintf(stderr, "Failed to parse line: %s\n", line);
            free(iris);
        }
    }
    fclose(file);

    return dataset;
}

int main(int argc, char const *argv[])
{
    FILE *file = fopen("IrisDataset.csv","r");
    Iris *dataset = readDataset(file);
    int lines = 150; // lines = columns

    // Alocando uma matriz quadrática de floats de dimensão lines de forma dinãmica.
    float **distMat = malloc(lines * sizeof(float*));
    for(int i = 0; i < lines; i++){
        distMat[i] = malloc(lines * sizeof(float));
    }

    // Povoando a matriz com as distancias euclidianas entres os pontos.
    // - Excluindo distancias entre mesmo ponto (gera zero) e calculos de distancias
    // desnecessárias, nesse caso atribui zero. Formando assim uma matriz triangular 
    // superior com as distancias.

    for(int i = 0; i < lines; i++){
        for(int j = 0; j < lines; j++){
            if(i >= j){
                distMat[i][j] = 0;
            }
            else{
                distMat[i][j] = EuclideanDist(dataset[i], dataset[j]);
            }
        }
    }

    // Cálculo da média de todas as distancias pra usar na normalização
    int sum = 0;
    for(int i = 0; i < lines; i++){
        for(int j = 0; j < lines; j++){
            sum += distMat[i][j];
        }
    }
    float aveage = sum/pow(lines, 2); // média 

    printf("%f", aveage);

    // Normalização da matriz



    //  // Saida para verificar os dados
    // for (int i = 0; i < 150; i++) {
    //     printf("Iris #%d - Variety: %d, Sepal Length: %.2f, Sepal Width: %.2f, Petal Length: %.2f, Petal Width: %.2f\n",
    //            i + 1, dataset[i]->variety, dataset[i]->slength, dataset[i]->swidth, dataset[i]->plength, dataset[i]->pwidth);
    //     free(dataset[i]);
    // }
    // free(dataset);

    return 0;
}


