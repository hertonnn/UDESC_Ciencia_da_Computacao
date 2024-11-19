#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>

int verificaRepeticaoNoVetor(int num, int *vet, int tam){
	for(int i = 0; i < tam; i++) {
		if(num == vet[i]){
			return 1;
		}
	}
	return 0;
}

void gerarBase(int tam){
	char buffer[50];
	snprintf(buffer, 50, "./Bases/conjuntoDeChavesTam%d.txt", tam);
	FILE *base = fopen(buffer, "w");

	int numGerados = 0;
	int *vet = malloc(sizeof(int));

	while(numGerados < tam) {
		int num = rand();

		if(!(verificaRepeticaoNoVetor(num, vet, numGerados))){
			numGerados++;
			int *temp = realloc(vet, ((numGerados + 1) * sizeof(int)));
			vet = temp;
			vet[numGerados] = num;
			fprintf(base, "%d\n", num);
		}
	}
	free(vet);
	fclose(base);
}

int main(int argc, char const *argv[])
{
	srand(time(NULL));
	int tam = atoi(argv[1]);

	gerarBase(tam);

	return 0;
}