#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>

int compara(const void* a, const void* b) {
    return *(int*) a - *(int*) b;
}

int* geraVetor(int n) {
    int* v = malloc(sizeof(int) * (n + 1));
     
    for (int i = 0; i < n; i++) {
        v[i] = rand() % n;
    }

    qsort(v, n, sizeof(int), compara);

    return v;
}

int pesquisaSequencial(int chave, int v[], int n) {
    int contador = 1;
    for (int i = 0; i < n; i++) {
        contador++;
        if (v[i] == chave) {
            return contador; //sucesso
        }
        contador++;
    }
    return contador; //insucesso
}

int pesquisaSequencialSentinela(int chave, int v[], int n) {
    int contador = 1, i = 0;
    v[n] = chave;
    while (v[i] != chave) {
        i++;
        contador++;
    }
    contador++;
    if (i < n) return contador; //sucesso
    return contador; //insucesso
}

int pesquisaBinaria(int chave, int v[], int n) {
    int inicio = 0, fim = n - 1, contador = 1;
    while (inicio <= fim) {
        int meio = (inicio + fim) / 2;
        contador++;
        if (chave == v[meio]) {
            return contador; //sucesso
        } else if (chave < v[meio]) {
            fim = meio - 1;
        } else {
            inicio = meio + 1;
        }
        contador += 2;
    }

    return contador; //insucesso
}

int pesquisaInterpolacao(int chave, int v[], int n) {
    int inicio = 0, meio, fim = n - 1, contador = 1;

    if (v[inicio] == v[fim]) {
        contador++;
        return v[inicio] == chave ? contador /* sucesso */ : contador /* insucesso */;
    }

    contador += 3;
    while (inicio <= fim && chave >= v[inicio] && chave <= v[fim]) {
        contador++;
        if (inicio == fim) {
            contador++;
            if (v[inicio] == chave) {
                return contador; // sucesso
                } else {
                    return contador; // insucesso
                }
        }
        
        meio = inicio + (((double) (fim - inicio) / (v[fim] - v[inicio])) * (chave - v[inicio]));
        
        contador++;
        if (chave == v[meio]) {    
            return contador; // sucesso
        } else if (chave < v[meio]) {
            fim = meio - 1;
        } else {
            inicio = meio + 1;
        }

        contador += 4;
    }

    return contador; //insucesso
}

int main() {
    FILE *dataInfo;
    dataInfo = fopen("dataInfo.txt", "w+");

    FILE *data;
    data = fopen("data.txt", "w+");
    

    int tamanhos[] = {1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000};
    int tamTamanhos = 10;

    for(int i = 0; i < tamTamanhos; i++){
        int n = tamanhos[i];
        int testes = 10;

        // Valores acumulativos para calc da media
        int pesqSeq = 0;
        int pesqSS = 0;
        int pesqBin = 0;
        int pesqInt = 0;

        fprintf(dataInfo, "---- Vetor de tamanho %i ----\n", n);

        for(int j = 0; j < testes; j++){

            fprintf(dataInfo,"   Vetor aleatório %i\n", j);
            
            int* v = geraVetor(n);
            int chave = v[rand() % n];

            pesqSeq += pesquisaSequencial(chave, v, n);
            pesqSS += pesquisaSequencialSentinela(chave, v, n);
            pesqBin += pesquisaBinaria(chave, v, n);
            pesqInt += pesquisaInterpolacao(chave, v, n);

            //Arquivo com dados detalhados
            fprintf(dataInfo,
                "     Pesquisa sequencial - caso medio: %d\n", 
                pesqSeq);

            fprintf(dataInfo,
                "     Pesquisa sequencial sentinela - caso medio: %d\n", 
                pesqSS);

            fprintf(dataInfo,
                "     Pesquisa binaria - caso medio: %d\n", 
                pesqBin);

            fprintf(dataInfo,
                "     Pesquisa por interpolacao - caso medio: %d\n", 
                pesqInt);
            fprintf(dataInfo, "\n");

        }
        //Arquivo com dados tratados
        fprintf(data, "%i %i %i %i\n", pesqSeq/10, pesqSS/10, pesqBin/10, pesqInt/10);
    }

    fclose(data);
    fclose(dataInfo);
}
