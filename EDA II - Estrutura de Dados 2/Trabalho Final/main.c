#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>
#include "arvores.c"

int main(int argc, char const *argv[])
{
	int numBateriaDeTestes = 10;
	int numConjuntosAvaliados = 0;

	while(numConjuntosAvaliados < numBateriaDeTestes) { // Esse loop vai automatizar os testes, para que não seja necessário rodar o programa x vezes

		float mediaDeOperacoesInsercaoAVL = 0.0;
		float mediaDeOperacoesRemocaoAVL = 0.0;

		float mediaDeOperacoesInsercaoRN = 0.0;
		float mediaDeOperacoesRemocaoRN = 0.0;
		
		float mediaDeOperacoesInsercaoB1 = 0.0;
		float mediaDeOperacoesRemocaoB1 = 0.0;
		
		float mediaDeOperacoesInsercaoB5 = 0.0;
		float mediaDeOperacoesRemocaoB5 = 0.0;
		
		float mediaDeOperacoesInsercaoB10 = 0.0;
		float mediaDeOperacoesRemocaoB10 = 0.0;

		for(int tam = 1000; tam <= 10000; tam += 1000) { // Ajustem o tamanho dos conjuntos gerados nesse for
			char gerarBases[30];
			snprintf(gerarBases, 30, "./geradorDeBases %d", tam);
			system(gerarBases);

			int chave = 0;
			char arq[100];
			char linha[100];

			snprintf(arq, 100, "./Bases/conjuntoDeChavesTam%d.txt", tam);
			FILE *base = fopen(arq, "r");

			/*

			Declare as arvores aqui

			*/

			Arvore* arvoreB1 = criarArvore(B, 1);
			Arvore* arvoreB5 = criarArvore(B, 5);
			Arvore* arvoreB10 = criarArvore(B, 10);

			// LOOPS DE INSERÇÃO E REMOÇÃO PARA ARVORE AVL

			while(fgets(linha, 100, base)) {
				chave = atoi(linha);
				
				//int numOperacoesInsercao = inserir(ArvoreAVL, chave);
				// mediaDeOperacoesInsercaoAVL += (float) numOperacoesInsercao;

			}

			// mediaDeOperacoesInsercaoAVL = mediaDeOperacoesInsercaoAVL / (float) tam;

			rewind(base);

			while(fgets(linha, 100, base)) {
				chave = atoi(linha);
				
				//int numOperacoesRemocao = remover(ArvoreAVL, chave);
				// mediaDeOperacoesRemocaoAVL += (float) numOperacoesRemocao;

			}

			// mediaDeOperacoesRemocaoAVL = mediaDeOperacoesRemocaoAVL / (float) tam; 

			rewind(base);

			// LOOPS DE INSERÇÃO E REMOÇÃO PARA ARVORE RUBRO NEGRA

			while(fgets(linha, 100, base)) {
				chave = atoi(linha);
				
				//int numOperacoesInsercao = inserir(ArvoreRN, chave);
				// mediaDeOperacoesInsercaoRN += (float) numOperacoesInsercao;

			}

			// mediaDeOperacoesInsercaoRN = mediaDeOperacoesInsercaoRN / (float) tam;

			rewind(base);

			while(fgets(linha, 100, base)) {
				chave = atoi(linha);
				
				//int numOperacoesRemocao = remover(ArvoreRN, chave);
				// mediaDeOperacoesRemocaoRN += (float) numOperacoesRemocao;

			}

			// mediaDeOperacoesRemocaoRN = mediaDeOperacoesRemocaoRN / (float) tam; 

			rewind(base);

			// LOOPS DE INSERÇÃO E REMOÇÃO PARA ARVORE B ORDEM 1

			while(fgets(linha, 100, base)) {
				chave = atoi(linha);
				
				int numOperacoesInsercao = adicionaChave(arvoreB1, chave);
				mediaDeOperacoesInsercaoB1 += (float) numOperacoesInsercao;

			}

			mediaDeOperacoesInsercaoB1 = mediaDeOperacoesInsercaoB1 / (float) tam;

			rewind(base);


			int count = 0;
			while(fgets(linha, 100, base) && count != 20) {
				chave = atoi(linha);
				
				int numOperacoesRemocao = removeChaveIterativa(arvoreB1, chave);
				mediaDeOperacoesRemocaoB1 += (float) numOperacoesRemocao;
				count++;
			}

			mediaDeOperacoesRemocaoB1 = mediaDeOperacoesRemocaoB1 / (float) tam; 

			rewind(base);

			// LOOPS DE INSERÇÃO E REMOÇÃO PARA ARVORE B ORDEM 5

			while(fgets(linha, 100, base)) {
				chave = atoi(linha);
				
				int numOperacoesInsercao = adicionaChave(arvoreB5, chave);
				mediaDeOperacoesInsercaoB5 += (float) numOperacoesInsercao;

			}

			mediaDeOperacoesInsercaoB5 = mediaDeOperacoesInsercaoB5 / (float) tam;

			rewind(base);

			count = 0;
			while(fgets(linha, 100, base) && count != 20) {
				chave = atoi(linha);
				
				int numOperacoesRemocao = removeChaveIterativa(arvoreB5, chave);
				mediaDeOperacoesRemocaoB5 += (float) numOperacoesRemocao;
				count++;
			}

			mediaDeOperacoesRemocaoB5 = mediaDeOperacoesRemocaoB1 / (float) tam; 

			rewind(base);

			// LOOPS DE INSERÇÃO E REMOÇÃO PARA ARVORE B ORDEM 10

			while(fgets(linha, 100, base)) {
				chave = atoi(linha);
				
				int numOperacoesInsercao = adicionaChave(arvoreB10, chave);
				mediaDeOperacoesInsercaoB10 += (float) numOperacoesInsercao;

			}

			mediaDeOperacoesInsercaoB10 = mediaDeOperacoesInsercaoB10 / (float) tam;

			rewind(base);

			count = 0;
			while(fgets(linha, 100, base) && count != 20) {
				chave = atoi(linha);
				
				int numOperacoesRemocao = removeChaveIterativa(arvoreB10, chave);
				mediaDeOperacoesRemocaoB10 += (float) numOperacoesRemocao;
				count++;
			}

			mediaDeOperacoesRemocaoB10 = mediaDeOperacoesRemocaoB10 / (float) tam; 

			rewind(base);

			fclose(base);
			
			char nomeArqOutputInsercoes[100];
			snprintf(nomeArqOutputInsercoes, 100, "./TabelasGeradas/insercoesGrupo%dTamanho%d.csv", numConjuntosAvaliados, tam);

			FILE *arqOutputInsercoes = fopen(nomeArqOutputInsercoes, "w");

			fprintf(arqOutputInsercoes, "Tamanho do Conjunto,Media AVL,Media RN,Media B(1),Media B(5),Media B(10)\n%d,%.2f,%.2f,%.2f,%.2f,%.2f", tam, mediaDeOperacoesInsercaoAVL, mediaDeOperacoesInsercaoRN, mediaDeOperacoesInsercaoB1,
				 mediaDeOperacoesInsercaoB5, mediaDeOperacoesInsercaoB10);
			
			fclose(arqOutputInsercoes);

			char nomeArqOutputRemocoes[100];
			snprintf(nomeArqOutputRemocoes, 100, "./TabelasGeradas/remocoesGrupo%dTamanho%d.csv", numConjuntosAvaliados, tam);

			FILE *arqOutputRemocoes = fopen(nomeArqOutputRemocoes, "w");

			fprintf(arqOutputRemocoes, "Tamanho do Conjunto,Media AVL,Media RN,Media B(1),Media B(5),Media B(10)\n%d,%.2f,%.2f,%.2f,%.2f,%.2f", tam, mediaDeOperacoesRemocaoAVL, mediaDeOperacoesRemocaoRN, mediaDeOperacoesRemocaoB1,
				 mediaDeOperacoesRemocaoB5, mediaDeOperacoesRemocaoB10);

			fclose(arqOutputRemocoes);

			mediaDeOperacoesInsercaoAVL = 0.0;
			mediaDeOperacoesRemocaoAVL = 0.0;

			mediaDeOperacoesInsercaoRN = 0.0;
			mediaDeOperacoesRemocaoRN = 0.0;
			
			mediaDeOperacoesInsercaoB1 = 0.0;
			mediaDeOperacoesRemocaoB1 = 0.0;
			
			mediaDeOperacoesInsercaoB5 = 0.0;
			mediaDeOperacoesRemocaoB5 = 0.0;
			
			mediaDeOperacoesInsercaoB10 = 0.0;
			mediaDeOperacoesRemocaoB10 = 0.0;

		}

		numConjuntosAvaliados++;
	}

	return 0;
}