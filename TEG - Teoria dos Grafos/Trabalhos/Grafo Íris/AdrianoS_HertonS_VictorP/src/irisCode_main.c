#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>

#define LIMIAR 0.3
#define NUM_CLUSTERS 2
#define MAX_ITER 100

typedef struct
{
    float sepal_length;
    float sepal_width;
    float petal_length;
    float petal_width;
    char especie[20];
} Flor;

void lerDados(const char *arquivo, Flor dataset[], int numAmostras)
{
    FILE *file = fopen(arquivo, "r");
    if (!file)
    {
        printf("Erro ao abrir o arquivo.\n");
        return;
    }
    fscanf(file, "%*[^\n]"); // Ignora a linha de cabeçalho
    for (int i = 0; i < numAmostras; i++)
    {
        fscanf(file, "%[^,],%f,%f,%f,%f\n",
               dataset[i].especie,
               &dataset[i].sepal_length,
               &dataset[i].sepal_width,
               &dataset[i].petal_length,
               &dataset[i].petal_width);
    }
    fclose(file);
}

float **alocarMatriz(int numAmostras)
{
    float **matriz = (float **)malloc(numAmostras * sizeof(float *));
    for (int i = 0; i < numAmostras; i++)
    {
        matriz[i] = (float *)calloc(numAmostras, sizeof(float));
    }
    return matriz;
}

void calcularMatrizAdjacencia(Flor dataset[], int numAmostras, float **matrizAdjacencia)
{
    FILE *arestas = fopen("./arestas.txt", "w");
    float maxDist = 0, minDist = INFINITY;
    float maxDistDEN = 0, minDistDEN = INFINITY;
    int amostraMaxDE1 = 0, amostraMaxDE2 = 0, amostraMaxDEN1 = 0, amostraMaxDEN2 = 0;
    int amostraMinDE1 = 0, amostraMinDE2 = 0, amostraMinDEN1 = 0, amostraMinDEN2 = 0;

    for (int i = 0; i < numAmostras; i++)
    {
        for (int j = i + 1; j < numAmostras; j++)
        {
            float dist = pow(dataset[i].sepal_length - dataset[j].sepal_length, 2) +
                         pow(dataset[i].sepal_width - dataset[j].sepal_width, 2) +
                         pow(dataset[i].petal_length - dataset[j].petal_length, 2) +
                         pow(dataset[i].petal_width - dataset[j].petal_width, 2);
            dist = sqrt(dist);

            if (dist > maxDist){
                amostraMaxDE1 = i;
                amostraMaxDE2 = j;
                maxDist = dist;
            }
            if (dist < minDist){
                amostraMinDE1 = i;
                amostraMinDE2 = j;
                minDist = dist;
            }

            matrizAdjacencia[i][j] = matrizAdjacencia[j][i] = dist;
        }
    }

    for (int i = 0; i < numAmostras; i++)
    {
        for (int j = i + 1; j < numAmostras; j++)
        {
            float calc = (matrizAdjacencia[i][j] - minDist) / (maxDist - minDist);
            if(calc > maxDistDEN){
                maxDistDEN = calc;
                amostraMaxDEN1 = i;
                amostraMaxDEN2 = j;
            }
            if(calc < minDistDEN){
                minDistDEN = calc;
                amostraMinDEN1 = i;
                amostraMinDEN2 = j;
            }

            matrizAdjacencia[i][j] = matrizAdjacencia[j][i] = calc < LIMIAR ? 1 : 0;

            if (matrizAdjacencia[i][j] == 1)
            {
                fprintf(arestas, "%i %i\n", i, j);
            }
        }
        matrizAdjacencia[i][i] = 0;
    }

    printf("\nO total de vértices lidos = %i\n", numAmostras);
    printf("A maior DE e o par de vértices (vi vj) que a determinaram = %f (%i, %i)\n", maxDist, amostraMaxDE1, amostraMaxDE2);
    printf("A menor DE e o par de vértices (vi vj) que a determinaram = %f (%i, %i)\n", minDist, amostraMinDE1, amostraMinDE2);
    printf("A maior DEN e o par de vértices (vi vj) que a determinaram = %f (%i, %i)\n", maxDistDEN, amostraMaxDEN1, amostraMaxDEN2);
    printf("A menor DEN e o par de vértices (vi vj) que a determinaram = %f (%i, %i);\n\n", minDistDEN, amostraMinDEN1, amostraMinDEN2);
}

// Função para realizar a BFS e marcar todos os vértices do mesmo componente
void bfs(int verticeInicial, float **matrizAdjacencia, int numAmostras, bool *visitados, int *tamanhoComponente)
{
    int fila[numAmostras];
    int inicio = 0, fim = 0;
    fila[fim++] = verticeInicial;
    visitados[verticeInicial] = true;
    *tamanhoComponente = 0;

    while (inicio < fim)
    {
        int v = fila[inicio++];
        (*tamanhoComponente)++;

        for (int i = 0; i < numAmostras; i++)
        {
            if (matrizAdjacencia[v][i] == 1 && !visitados[i])
            {
                visitados[i] = true;
                fila[fim++] = i;
            }
        }
    }
}

// Função para contar componentes conexos e seus tamanhos
void contarComponentesConexos(float **matrizAdjacencia, int numAmostras)
{
    bool *visitados = (bool *)calloc(numAmostras, sizeof(bool));
    int numComponentes = 0;

   // printf("Componentes conexos:\n");

    for (int i = 0; i < numAmostras; i++)
    {
        if (!visitados[i])
        {
            int tamanhoComponente = 0;
            bfs(i, matrizAdjacencia, numAmostras, visitados, &tamanhoComponente);
           // printf("Componente %d: Tamanho %d\n", ++numComponentes, tamanhoComponente);
        }
    }

    free(visitados);
}

/*--------------------------------------------------------------------------------------*/

void inicializarCentros(Flor dataset[], Flor centros[], int numAmostras)
{
    srand(time(NULL));
    for (int i = 0; i < NUM_CLUSTERS; i++)
    {
        int indice;
        bool unico;
        do
        {
            indice = rand() % numAmostras;
            unico = true;
            for (int j = 0; j < i; j++)
            {
                if (indice == j)
                {
                    unico = false;
                    break;
                }
            }
        } while (!unico);
        centros[i] = dataset[indice];
    }
}

void atribuirPontos(Flor dataset[], int numAmostras, Flor centros[], int *clusters, float **matrizAdjacencia)
{
    for (int i = 0; i < numAmostras; i++)
    {
        float distMin = INFINITY; // Inicializa com um valor grande
        int clusterEscolhido = -1;

        for (int k = 0; k < NUM_CLUSTERS; k++)
        {
            if (matrizAdjacencia[i][k] == 1)
            { // Verifica conectividade
                float dist = sqrt(
                    pow(dataset[i].sepal_length - centros[k].sepal_length, 2) +
                    pow(dataset[i].sepal_width - centros[k].sepal_width, 2) +
                    pow(dataset[i].petal_length - centros[k].petal_length, 2) +
                    pow(dataset[i].petal_width - centros[k].petal_width, 2));

                if (dist < distMin)
                {
                    distMin = dist;
                    clusterEscolhido = k;
                }
            }
        }

        // Atribui o cluster mais próximo, ou o cluster 0 caso não haja conectividade
        clusters[i] = clusterEscolhido != -1 ? clusterEscolhido : 0;
    }
}

void recalcularCentros(Flor dataset[], int numAmostras, Flor centros[], int *clusters)
{
    Flor soma[NUM_CLUSTERS] = {0};
    int contador[NUM_CLUSTERS] = {0};

    for (int i = 0; i < numAmostras; i++)
    {
        int cluster = clusters[i];
        soma[cluster].sepal_length += dataset[i].sepal_length;
        soma[cluster].sepal_width += dataset[i].sepal_width;
        soma[cluster].petal_length += dataset[i].petal_length;
        soma[cluster].petal_width += dataset[i].petal_width;
        contador[cluster]++;
    }

    for (int k = 0; k < NUM_CLUSTERS; k++)
    {
        if (contador[k] > 0)
        {
            centros[k].sepal_length = soma[k].sepal_length / contador[k];
            centros[k].sepal_width = soma[k].sepal_width / contador[k];
            centros[k].petal_length = soma[k].petal_length / contador[k];
            centros[k].petal_width = soma[k].petal_width / contador[k];
        }
    }
}

void kMeans(Flor dataset[], int numAmostras, int *clusters, float **matrizAdjacencia)
{
    Flor centros[NUM_CLUSTERS];
    inicializarCentros(dataset, centros, numAmostras);

    for (int iter = 0; iter < MAX_ITER; iter++)
    {
        atribuirPontos(dataset, numAmostras, centros, clusters, matrizAdjacencia);
        recalcularCentros(dataset, numAmostras, centros, clusters);
    }
}

void imprimirClusters(Flor dataset[], int numAmostras, int *clusters, float **matrizAdjacencia)
{
    // Abertura dos arquivos para escrita
    FILE *grafo1 = fopen("./cluster1.txt", "w");
    FILE *grafo2 = fopen("./cluster2.txt", "w");

    if (grafo1 == NULL || grafo2 == NULL)
    {
        printf("Erro ao abrir arquivos para escrita.\n");
        return;
    }

    // Loop sobre todas as amostras
    for (int i = 0; i < numAmostras; i++)
    {
        //printf("Amostra %d: %s - Cluster %d\n", i, dataset[i].especie, clusters[i]);

        // Adiciona arestas dentro do mesmo cluster, considerando apenas arestas conectadas (matrizAdjacencia[i][j] == 1)
        for (int j = i + 1; j < numAmostras; j++)
        {
            // Verifica se i e j pertencem ao mesmo cluster e estão conectados
            if (clusters[i] == clusters[j] && matrizAdjacencia[i][j] == 1)
            {
                // Verifica qual cluster as amostras i e j pertencem
                if (clusters[i] == 0) // Cluster 1
                {
                    fprintf(grafo1, "%d %d\n", i, j); // Arestas dentro do cluster 1
                }
                else if (clusters[i] == 1) // Cluster 2
                {
                    fprintf(grafo2, "%d %d\n", i, j); // Arestas dentro do cluster 2
                }
            }
            else if (matrizAdjacencia[i][j] == 1)
            {
                // Isso contabiliza as arestas entre clusters diferentes
                //printf("Aresta entre clusters: %d - %d\n", i, j);
            }
        }
    }

    // Fechando os arquivos
    fclose(grafo1);
    fclose(grafo2);
}

    /*
    Com base na predição do modelo (agrupador) e na realidade conhecida (base de dados
identificados) é possível construir uma matriz de confusão (veja a abaixo), identificando os
casos TP (true positive), FP (false positive), TN (true negative) e FN (false negative) e
realizar a extração das métricas de qualidade da classificação, por exemplo a acurácia
    */
void acuracia(Flor dataset[], int numAmostras, int *clusters) {
    int TP = 0, FP = 0, TN = 0, FN = 0;
    
    for (int i = 0; i < numAmostras; i++) {
        // Caso a flor seja "Setosa" e esteja no cluster 1 -> Verdadeiro Positivo
        if (strcmp(dataset[i].especie, "Setosa") == 0 && clusters[i] == 1) {
            TP++;
        }
        // Caso a flor seja "Setosa" mas esteja no cluster 0 -> Falso Negativo
        else if (strcmp(dataset[i].especie, "Setosa") == 0 && clusters[i] == 0) {
            FN++;
        }
        // Caso a flor não seja "Setosa" e esteja no cluster 0 -> Verdadeiro Negativo
        else if (strcmp(dataset[i].especie, "Setosa") != 0 && clusters[i] == 0) {
            TN++;
        }
        // Caso a flor não seja "Setosa" mas esteja no cluster 1 -> Falso Positivo
        else if (strcmp(dataset[i].especie, "Setosa") != 0 && clusters[i] == 1) {
            FP++;
        }
    }
    
    // Exibindo a matriz de confusão
    printf("Matriz de Confusão:\n");
    printf("                Predito Setosa    Predito Nao Setosa\n");
    printf("Real Setosa:        %d (TP)         %d (FN)\n", TP, FN);
    printf("Real Nao Setosa:    %d (FP)         %d (TN)\n", FP, TN);
    
    // Calculando e exibindo a acurácia
    float acuracia = (float)(TP + TN) / numAmostras;
    printf("Acuracia: %.2f%%\n", acuracia * 100);
}

int main()
{
    int numAmostras = 150;
    Flor *dataset = (Flor *)malloc(numAmostras * sizeof(Flor));
    int *clusters = (int *)malloc(numAmostras * sizeof(int));
    memset(clusters, -1, numAmostras * sizeof(int)); // Inicializa com -1 (não atribuído)

    lerDados("IrisDataset.csv", dataset, numAmostras);

    float **matrizAdjacencia = alocarMatriz(numAmostras);
    calcularMatrizAdjacencia(dataset, numAmostras, matrizAdjacencia);

    // Contar componentes conexos usando BFS
    contarComponentesConexos(matrizAdjacencia, numAmostras);

    // Executa o K-means
    kMeans(dataset, numAmostras, clusters, matrizAdjacencia);

    // Imprime os clusters encontrados
    imprimirClusters(dataset, numAmostras, clusters, matrizAdjacencia);

    //Calcular a acurácia 
    acuracia(dataset, numAmostras, clusters);

    // Libera memória alocada
    for (int i = 0; i < numAmostras; i++)
    {
        free(matrizAdjacencia[i]);
    }


    free(matrizAdjacencia);
    free(dataset);
    free(clusters);

    return 0;
}
