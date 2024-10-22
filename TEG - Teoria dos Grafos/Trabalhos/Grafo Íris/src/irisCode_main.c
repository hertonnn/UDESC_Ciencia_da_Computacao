#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include "busca_BFS.c"


#define NUM_CARACTERISTICAS 4 // Número de características por flor
#define MAX_AMOSTRAS 150      // Número máximo de amostras (ajustado para o tamanho do dataset)
#define LIMIAR 0.3            // Limiar para a criação de arestas


// Grafo para a BFS
struct Graph* graph;

// Função para encontrar os valores máximos e mínimos para normalização
void encontrar_max_min(Flower dataset[], int num_amostras, Flower *max, Flower *min) {
    *max = *min = dataset[0]; // Inicializa com a primeira amostra

    for (int i = 1; i < num_amostras; i++) {
        if (dataset[i].sepal_length > max->sepal_length)
            max->sepal_length = dataset[i].sepal_length;
        if (dataset[i].sepal_width > max->sepal_width)
            max->sepal_width = dataset[i].sepal_width;
        if (dataset[i].petal_length > max->petal_length)
            max->petal_length = dataset[i].petal_length;
        if (dataset[i].petal_width > max->petal_width)
            max->petal_width = dataset[i].petal_width;

        if (dataset[i].sepal_length < min->sepal_length)
            min->sepal_length = dataset[i].sepal_length;
        if (dataset[i].sepal_width < min->sepal_width)
            min->sepal_width = dataset[i].sepal_width;
        if (dataset[i].petal_length < min->petal_length)
            min->petal_length = dataset[i].petal_length;
        if (dataset[i].petal_width < min->petal_width)
            min->petal_width = dataset[i].petal_width;
    }
}

// Função para normalizar os dados de uma flor
Flower normalizar_flor(Flower f, Flower max, Flower min) {
    Flower normalizada;
    normalizada.sepal_length = (f.sepal_length - min.sepal_length) / (max.sepal_length - min.sepal_length);
    normalizada.sepal_width = (f.sepal_width - min.sepal_width) / (max.sepal_width - min.sepal_width);
    normalizada.petal_length = (f.petal_length - min.petal_length) / (max.petal_length - min.petal_length);
    normalizada.petal_width = (f.petal_width - min.petal_width) / (max.petal_width - min.petal_width);
    normalizada.key = f.key;
    return normalizada;
}

// Função para calcular a distância euclidiana entre duas flores
float calcular_distancia_euclidiana(Flower f1, Flower f2) {
    float soma = pow(f1.sepal_length - f2.sepal_length, 2) +
                 pow(f1.sepal_width - f2.sepal_width, 2) +
                 pow(f1.petal_length - f2.petal_length, 2) +
                 pow(f1.petal_width - f2.petal_width, 2);
    return sqrt(soma);
}
// Função para ler os dados do arquivo CSV
void ler_dados_csv(const char *filename, Flower dataset[], int *num_amostras) {
    FILE *file = fopen(filename, "r");
    if (!file) {
        printf("Erro ao abrir o arquivo.\n");
        return;
    }

    char tipo_flor[20]; // Para armazenar o tipo da flor, mas não será utilizado
    Flower flor;
    *num_amostras = 0;

    // Ignorar o cabeçalho
    fscanf(file, "%*[^\n]"); // Lê e descarta a primeira linha (cabeçalho)

    while (fscanf(file, "%[^,],%f,%f,%f,%f\n", tipo_flor,
                  &flor.sepal_length,
                  &flor.sepal_width,
                  &flor.petal_length,
                  &flor.petal_width) == 5){
        flor.key = *num_amostras;
        dataset[(*num_amostras)++] = flor;
    }
}

// Função para gerar a matriz de adjacências
void gerar_matriz_adjacencias(Flower dataset[], int num_amostras, int matriz[num_amostras][num_amostras]) {
    Flower max, min;
    FILE *arestas = fopen("./grafo-total.txt","w");

    // Encontrar valores máximos e mínimos para normalização
    encontrar_max_min(dataset, num_amostras, &max, &min);

    // Inicializar a matriz de adjacências
    for (int i = 0; i < num_amostras; i++) {
        for (int j = 0; j < num_amostras; j++) {
            matriz[i][j] = 0; // Inicialmente, não há arestas
        }
    }
    // Calcular as distâncias e normalizar
    for (int i = 0; i < num_amostras; i++) {
        Flower flor_normalizada_1 = normalizar_flor(dataset[i], max, min);
        for (int j = i + 1; j < num_amostras; j++) {
            Flower flor_normalizada_2 = normalizar_flor(dataset[j], max, min);
            float distancia = calcular_distancia_euclidiana(flor_normalizada_1, flor_normalizada_2);

            // Aplicar o limiar
            if (distancia <= LIMIAR) {
                matriz[i][j] = 1;
                matriz[j][i] = 1; // Grafo não direcionado

                // Adiciona cada conexão (aresta) num arquivo para plotar em python
                fprintf(arestas, "%i %i\n", flor_normalizada_1.key, flor_normalizada_2.key);
                // Adiciona cada conexão (aresta) em uma fila para a busca BFS
                addEdge(graph, flor_normalizada_1, flor_normalizada_2);
        
            }
        }
    }

    fclose(arestas);
}

// Função para imprimir a matriz de adjacências
void imprimir_matriz_adjacencias(int matriz[][MAX_AMOSTRAS], int num_amostras) {
    printf("\n------------------------- Matriz de adjacências -------------------------\n\n");
    for (int i = 0; i < num_amostras; i++) {
        for (int j = 0; j < num_amostras; j++) {
            printf("%d ", matriz[i][j]);
        }
        printf("\n");
    }
}

int main() {
    //BFS
    graph = createGraph(150);
    FILE *grupo_1 = fopen("./grafo_grupo_1.txt","w"); 
    FILE *grupo_2 = fopen("./grafo_grupo_2.txt","w"); 
    //
    Flower dataset[MAX_AMOSTRAS]; // Armazena todas as amostras
    int num_amostras;

    // Ler os dados do arquivo CSV
    ler_dados_csv("IrisDataset.csv", dataset, &num_amostras);

    // Matriz de adjacências
    int matriz_adjacencias[MAX_AMOSTRAS][MAX_AMOSTRAS];

    // Gerar matriz de adjacências
    gerar_matriz_adjacencias(dataset, num_amostras, matriz_adjacencias);

    // Imprimir matriz de adjacências
    imprimir_matriz_adjacencias(matriz_adjacencias, num_amostras);
    
    printGraph(graph);

    //BFS
    bfs(graph,0);
    
    fclose(grupo_1);
    fclose(grupo_2);

    return 0;
}