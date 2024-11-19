#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>

typedef enum
{
    AVL,
    RUBRO_NEGRA,
    B
} tipoArvore;

typedef enum
{
    VERMELHO,
    PRETO
} Cor;

typedef struct No
{
    struct No *pai;
    struct No **filhos;
    struct No *esq;
    struct No *dir;
    int *chaves;
    int valor;
    int total;

    tipoArvore tipo;

    union
    {
        struct
        {
            int altura;
        } AVL;
        struct
        {
            Cor cor;
        } RUBRO_NEGRA;
        struct
        {
            int ordem;
        } B;
    } caracteristica;
} No;

typedef struct Arvore
{
    No *raiz;
    No *nulo;
    int ordem;
    tipoArvore tipo;
} Arvore;

// Sobre o esforço:
/*
---- CÁLCULO DO ESFORÇO NA ÁRVORE B ----

Operações Pesquisa binaria - Soma-se mais 2 ao esforço 
Operações de Localizar No - Soma-se mais 1 ao esforço a cada no verificado da arvore

*/
// Variaveis globais de esforço
int esforcoInserirB;
int esforcoRemoverB;

// Declaração de protótipos de funções
// OUTRAS ARVORES
void* criarArvore(tipoArvore tipo, int ordem);
No* criarNo(Arvore *arvore, No *pai, int valor);
No* criaNoB(Arvore *arvore);
int vazia(Arvore *arvore);
No* adicionarNo(Arvore *arvore, No *no, int valor);
No* adicionar(Arvore *arvore, int valor);
No* localizarNo(Arvore *arvore, int valor);
void balancearArvore(Arvore *arvore, No *no);
void visitar(int valor);
void balanceamentoAVL(Arvore *arvore, No *no);
void balanceamentoRubroNegra(Arvore *arvore, No *no);
void percorrer(No *no, void (*callback)(int));
void percorrerProfundidadeInOrder(Arvore *arvore, No *no, void (*callback)(int));
void percorreArvore(No *no, void(visita)(int chave));
No* rse(Arvore *arvore, No *no);
No* rsd(Arvore *arvore, No *no);
No* rde(Arvore *arvore, No *no);
No* rdd(Arvore *arvore, No *no);
void rotacionaresq(Arvore *arvore, No *no);
void rotacionardir(Arvore *arvore, No *no);
int altura(No *no);
int fb(No *no);

// ARVORE B
int pesquisaBinaria(No *no, int chave);
int encontrarIndiceChave(No *no, int chave);
int obterSucessor(No *no);
void balancearAposRemocao(Arvore *arvore, No *pai, int idx);
void fundirNos(Arvore *arvore, No *pai, int idx);
int removeChaveIterativa(Arvore *arvore, int chave);
void imprimirArvoreB(No* no, int nivel);
int ehFolha(No* no);

// Implementação das funções a seguir

void *criarArvore(tipoArvore tipo, int ordem)
{
    Arvore *arvore = malloc(sizeof(Arvore));
    arvore->raiz = NULL;
    arvore->tipo = tipo;

    if (tipo == B)
    {
        arvore->ordem = ordem;
        arvore->raiz = criaNoB(arvore);
    }
    else
    {
        arvore->nulo = (tipo == RUBRO_NEGRA) ? criarNo(arvore, NULL, 0) : NULL;
        if (arvore->nulo != NULL)
        {
            arvore->nulo->caracteristica.RUBRO_NEGRA.cor = PRETO;
        }
    }

    return arvore;
}

No *criarNo(Arvore *arvore, No *pai, int valor)
{
    No *no = malloc(sizeof(No));
    no->pai = pai;
    no->valor = valor;
    no->esq = no->dir = (arvore->tipo == RUBRO_NEGRA) ? arvore->nulo : NULL;

    if (arvore->tipo == AVL)
    {
        no->caracteristica.AVL.altura = 1;
    }
    else if (arvore->tipo == RUBRO_NEGRA)
    {
        no->caracteristica.RUBRO_NEGRA.cor = VERMELHO;
    }

    return no;
}

No *criaNoB(Arvore *arvore)
{
    int max = arvore->ordem * 2;
    No *no = malloc(sizeof(No));
    no->pai = NULL;
    no->tipo = B;
    no->chaves = malloc(sizeof(int) * (max + 1));
    no->filhos = malloc(sizeof(No *) * (max + 2));
    no->total = 0;
    for (int i = 0; i < max + 2; i++)
        no->filhos[i] = NULL;
    return no;
}

int vazia(Arvore *arvore)
{
    return arvore->raiz == NULL;
}

No *adicionarNo(Arvore *arvore, No *no, int valor)
{
    if (valor > no->valor)
    {
        if (no->dir == arvore->nulo || no->dir == NULL)
        {
            no->dir = criarNo(arvore, no, valor);
            return no->dir;
        }
        else
        {
            return adicionarNo(arvore, no->dir, valor);
        }
    }
    else
    {
        if (no->esq == arvore->nulo || no->esq == NULL)
        {
            no->esq = criarNo(arvore, no, valor);
            return no->esq;
        }
        else
        {
            return adicionarNo(arvore, no->esq, valor);
        }
    }
}

No *adicionar(Arvore *arvore, int valor)
{
    if (vazia(arvore))
    {
        arvore->raiz = criarNo(arvore, arvore->nulo, valor);
        if (arvore->tipo == RUBRO_NEGRA)
        {
            arvore->raiz->caracteristica.RUBRO_NEGRA.cor = PRETO;
        }
        return arvore->raiz;
    }
    else
    {
        No *no = adicionarNo(arvore, arvore->raiz, valor);
        if (arvore->tipo == RUBRO_NEGRA)
        {
            balanceamentoRubroNegra(arvore, no);
        }
        else if (arvore->tipo == AVL)
        {
            balanceamentoAVL(arvore, no);
        }
        return no;
    }
}

No *localizarNo(Arvore *arvore, int valor)
{
    if(vazia(arvore)){
        return NULL;
    }

    if (arvore->tipo == B)
    {
        No *no = arvore->raiz;
        while (no != NULL)
        {
            int i = pesquisaBinaria(no, valor);
            if (i < no->total && no->chaves[i] == valor)
                return no;
            no = no->filhos[i];
        }
        return NULL;
    }
    else
    {
        No *no = arvore->raiz;
        while (no != arvore->nulo)
        {
            if (no->valor == valor)
            {
                return no;
            }
            else
            {
                no = (valor < no->valor) ? no->esq : no->dir;
            }
        }
        return NULL;
    }
}

void balancearArvore(Arvore *arvore, No *no)
{
    if (arvore->tipo == AVL)
    {
        balanceamentoAVL(arvore, no);
    }
    else if (arvore->tipo == RUBRO_NEGRA)
    {
        balanceamentoRubroNegra(arvore, no);
    }
}

void visitar(int valor)
{
    printf("%d ", valor);
}

//-------------------------------------------------------------------------------------------------------
// IMPLEMENTAÇÃO DAS FUNÇÕES DE BALANCEAMENTO
//AVL
void balanceamentoAVL(Arvore *arvore, No *no){
    while (no != NULL) {
        int fator = fb(no);
        
        no->caracteristica.AVL.altura = (no->esq->caracteristica.AVL.altura > no->dir->caracteristica.AVL.altura
			? no->esq->caracteristica.AVL.altura 
			: no->dir->caracteristica.AVL.altura) + 1;
				

        if (fator > 1) { // arvore mais profunda para ->esq
            if (fb(no->esq) > 0) {
                printf("RSD(%d)\n",no->valor);
                rsd(arvore, no); 
            } else {
                printf("RDD(%d)\n",no->valor);
                rdd(arvore, no); 
            }
        } else if (fator < -1) { // arvore mais profunda direita
            if (fb(no->dir) < 0) {
                printf("RSE(%d)\n",no->valor);
                rse(arvore, no); 
            } else {
                printf("RDE(%d)\n",no->valor);
                rde(arvore, no);
            }
        }

        no = no->pai; 
    }
}

// Rubro Negra
void balanceamentoRubroNegra(Arvore *arvore, No *no) {
    while (no->pai->caracteristica.RUBRO_NEGRA.cor == VERMELHO) {
        if (no->pai == no->pai->pai->esq) {
            No *tio = no->pai->pai->dir;
            
            if (tio->caracteristica.RUBRO_NEGRA.cor == VERMELHO) {
                tio->caracteristica.RUBRO_NEGRA.cor = VERMELHO; //Caso 1
                no->pai->caracteristica.RUBRO_NEGRA.cor = PRETO; 

                no->pai->pai->caracteristica.RUBRO_NEGRA.cor = VERMELHO; //Caso 1
                no = no->pai->pai; //Caso 1
            } else {
                if (no == no->pai->dir) {
                    no = no->pai; //Caso 2
                    rotacionaresq(arvore, no); //Caso 2
                } else {
                    no->pai->caracteristica.RUBRO_NEGRA.cor = PRETO; 
                    no->pai->pai->caracteristica.RUBRO_NEGRA.cor = VERMELHO; //Caso 3
                    rotacionardir(arvore, no->pai->pai); //Caso 3
                }
            }
        } else {
            No *tio = no->pai->pai->dir;
            
            if (tio->caracteristica.RUBRO_NEGRA.cor == VERMELHO) {
                tio->caracteristica.RUBRO_NEGRA.cor = PRETO; //Caso 1
                no->pai->caracteristica.RUBRO_NEGRA.cor = PRETO; 

                no->pai->pai->caracteristica.RUBRO_NEGRA.cor = VERMELHO; //Caso 1
                no = no->pai->pai; //Caso 1
            } else {
                if (no == no->pai->esq) {
                    no = no->pai; //Caso 2
                    rotacionardir(arvore, no); //Caso 2
                } else {
                    no->pai->caracteristica.RUBRO_NEGRA.cor = PRETO; 
                    no->pai->pai->caracteristica.RUBRO_NEGRA.cor = VERMELHO; //Caso 3
                    rotacionaresq(arvore, no->pai->pai); //Caso 3
                }
            }
        }
    }
    arvore->raiz->caracteristica.RUBRO_NEGRA.cor = PRETO; //Conserta possível quebra regra 2
}


//-------------------------------------------------------------------------------------------------------
// FUNÇÕES DE PERCORRER
// AVL
void percorrer(No *no, void (*callback)(int))
{
    if (no != NULL)
    {
        percorrer(no->esq, callback);
        callback(no->valor);
        percorrer(no->dir, callback);
    }
}

// RUBRO NEGRA
void percorrerProfundidadeInOrder(Arvore *arvore, No *no, void (*callback)(int))
{
    if (no != arvore->nulo)
    {

        percorrerProfundidadeInOrder(arvore, no->esq, callback);
        callback(no->valor);
        percorrerProfundidadeInOrder(arvore, no->dir, callback);
    }
}

// ARVORE B
void percorreArvore(No *no, void(visita)(int chave))
{
    if (no != NULL)
    {
        for (int i = 0; i < no->total; i++)
        {
            percorreArvore(no->filhos[i], visita);
            visita(no->chaves[i]);
        }
        percorreArvore(no->filhos[no->total], visita);
    }
}

//-------------------------------------------------------------------------------------------------------
// FUNÇÕES DE ROTAÇÃO
// AVL
// rotação esquerda
No *rse(Arvore *arvore, No *no)
{
    No *pai = no->pai;
    No *dir = no->dir;

    if (dir->esq != NULL)
    {
        dir->esq->pai = no;
    }

    no->dir = dir->esq;
    no->pai = dir;

    dir->esq = no;
    dir->pai = pai;

    if (pai == NULL)
    {
        arvore->raiz = dir;
    }
    else
    {
        if (pai->esq == no)
        {
            pai->esq = dir;
        }
        else
        {
            pai->dir = dir;
        }
    }

    return dir;
}

// rotação direita
No *rsd(Arvore *arvore, No *no)
{
    No *pai = no->pai;
    No *esq = no->esq;

    if (esq->dir != NULL)
    {
        esq->dir->pai = no;
    }

    no->esq = esq->dir;
    no->pai = esq;

    esq->dir = no;
    esq->pai = pai;

    if (pai == NULL)
    {
        arvore->raiz = esq;
    }
    else
    {
        if (pai->esq == no)
        {
            pai->esq = esq;
        }
        else
        {
            pai->dir = esq;
        }
    }

    return esq;
}

// rotação dupla esquerda
No *rde(Arvore *arvore, No *no)
{
    no->dir = rsd(arvore, no->dir);
    return rse(arvore, no);
}

// rotação dupla direita
No *rdd(Arvore *arvore, No *no)
{
    no->esq = rse(arvore, no->esq);
    return rsd(arvore, no);
}

// RUBRO NEGRA
void rotacionaresq(Arvore *arvore, No *no)
{
    No *dir = no->dir;
    no->dir = dir->esq;

    if (dir->esq != arvore->nulo)
    {
        dir->esq->pai = no;
    }

    dir->pai = no->pai;

    if (no->pai == arvore->nulo)
    {
        arvore->raiz = dir;
    }
    else if (no == no->pai->esq)
    {
        no->pai->esq = dir;
    }
    else
    {
        no->pai->dir = dir;
    }

    dir->esq = no;
    no->pai = dir;
}

void rotacionardir(Arvore *arvore, No *no)
{
    No *esq = no->esq;
    no->esq = esq->dir;

    if (esq->dir != arvore->nulo)
    {
        esq->dir->pai = no;
    }

    esq->pai = no->pai;

    if (no->pai == arvore->nulo)
    {
        arvore->raiz = esq;
    }
    else if (no == no->pai->esq)
    {
        no->pai->esq = esq;
    }
    else
    {
        no->pai->dir = esq;
    }

    esq->dir = no;
    no->pai = esq;
}

//-------------------------------------------------------------------------------------------------------
// AVL
int altura(No *no)
{
    if (no == NULL)
    {
        return 0;
    }

    int esq = altura(no->esq) + 1;
    int dir = altura(no->dir) + 1;

    return esq > dir ? esq : dir;
}

int fb(No *no)
{
    return altura(no->esq) - altura(no->dir);
}

//-------------------------------------------------------------------------------------------------------
// B
No* localizaChave(Arvore* arvore, int chave) {
    No* no = arvore->raiz;
    while (no != NULL) {
        int i = pesquisaBinaria(no, chave);
        if (i < no->total && no->chaves[i] == chave) {
            return no;  // Encontrou a chave
        } else if (no->filhos[i] != NULL) {
            no = no->filhos[i];  // Desce para o filho adequado
        } else {
            return no;  // Retorna o nó onde a chave pode ser inserida
        }

        //A cada no verificado
        esforcoInserirB += 1;
    }

    return NULL;
}
int transbordo(Arvore *arvore, No *no)
{
    return no->total > arvore->ordem * 2;
}
void adicionaChaveNo(No *no, No *direita, int chave)
{

    int i = pesquisaBinaria(no, chave);
    for (int j = no->total - 1; j >= i; j--)
    {
        no->chaves[j + 1] = no->chaves[j];
        no->filhos[j + 2] = no->filhos[j + 1];
    }
    no->chaves[i] = chave;
    no->filhos[i + 1] = direita;
    no->total++;
}
int pesquisaBinaria(No *no, int chave)
{
    int inicio = 0, fim = no->total - 1, meio;
    while (inicio <= fim)
    {
        meio = (inicio + fim) / 2;
        if (no->chaves[meio] == chave)
        {
            return meio; // encontrou
        }
        else if (no->chaves[meio] > chave)
        {
            fim = meio - 1;
        }
        else
        {
            inicio = meio + 1;
        }

    }
    esforcoInserirB += 2; 
    return inicio; // não encontrou
}

No* divideNo(Arvore* arvore, No* no) {
    int meio = no->total / 2;
    No* novo = criaNoB(arvore);
    novo->pai = no->pai;

    for (int i = meio + 1; i < no->total; i++) {
        novo->chaves[novo->total] = no->chaves[i];
        novo->filhos[novo->total] = no->filhos[i];

        if (novo->filhos[novo->total] != NULL) {
            novo->filhos[novo->total]->pai = novo;
        }
        novo->total++;
    }

    // Conecta o último filho
    novo->filhos[novo->total] = no->filhos[no->total];
    if (novo->filhos[novo->total] != NULL) {
        novo->filhos[novo->total]->pai = novo;
    }

    // Ajusta o total do nó original e remove as referências duplicadas
    no->total = meio;

    // Limpa chaves e filhos residuais do nó original
    for (int i = meio + 1; i <= arvore->ordem * 2; i++) {
        no->chaves[i] = 0;         // Limpa chave residual
        no->filhos[i + 1] = NULL;  // Limpa filho residual
    }

    return novo;
}

void adicionaChaveRecursivo(Arvore* arvore, No* no, No* novoFilho, int chave) {

    adicionaChaveNo(no, novoFilho, chave);

    if (transbordo(arvore, no)) {
        int promovido = no->chaves[arvore->ordem];
        No* novoNo = divideNo(arvore, no);

        if (no->pai == NULL) {
            No* novaRaiz = criaNoB(arvore);
            novaRaiz->filhos[0] = no;
            adicionaChaveNo(novaRaiz, novoNo, promovido);

            // Ajusta ponteiros pai e raiz da árvore
            no->pai = novaRaiz;
            novoNo->pai = novaRaiz;
            arvore->raiz = novaRaiz;
        } else {
            adicionaChaveRecursivo(arvore, no->pai, novoNo, promovido);
        }
    }
}

int adicionaChave(Arvore* arvore, int chave) {

    esforcoInserirB = 0;
    // Verifica se a chave já existe
    No* no = localizaChave(arvore, chave);

    int idx = pesquisaBinaria(no, chave);
    if (idx < no->total && no->chaves[idx] == chave) {
        printf("Chave %d já existe na árvore.\n", chave);
        return 0;
    }

    // Cria a raiz se a árvore está vazia
    if (arvore->raiz == NULL) {
        no = criaNoB(arvore);
        arvore->raiz = no;
    }

    // Adiciona a chave
    adicionaChaveRecursivo(arvore, no, NULL, chave);

    return esforcoInserirB;
}

// // --------------------------------------------------
// Função recursiva para imprimir a árvore B
void imprimirArvoreB(No* no, int nivel) {
    if (no == NULL) return;

    // Indentação para o nível atual
    for (int i = 0; i < nivel; i++) {
        printf("        "); // Indentação para cada nível
    }

    // Exibe as chaves do nó atual entre colchetes
    if (no->total > 0) {  // Só imprime se o nó tiver chaves
        printf("[");
        for (int i = 0; i < no->total; i++) {
            printf("%d", no->chaves[i]);
            if (i < no->total - 1) {
                printf(", ");
            }
        }
        printf("]\n");
    }

    // Verifica se o nó possui filhos não nulos (indicando que não é folha)
    int ehFolha = 1;
    for (int i = 0; i <= no->total; i++) {
        if (no->filhos[i] != NULL) {
            ehFolha = 0;
            break;
        }
    }

    // Se o nó não for folha, imprime as linhas de galhos e chama recursivamente para os filhos
    if (!ehFolha) {
        // Indentação para a linha de galhos
        for (int i = 0; i < nivel; i++) {
            printf("        ");
        }

        // Imprime os galhos (conectando os filhos)
        for (int i = 0; i < no->total + 1; i++) {
            if (i == 0) {
                printf("  /");
            } else if (i == no->total) {
                printf("    \\");
            } else {
                printf("     ");
            }
        }
        printf("\n");

        // Chamada recursiva para os filhos
        for (int i = 0; i <= no->total; i++) {
            if (no->filhos[i] != NULL) {
                imprimirArvoreB(no->filhos[i], nivel + 1);
            }
        }
    }
}

// ------ REMOÇÃO -------
int removeChaveIterativa(Arvore *arvore, int chave) {
    if (arvore->raiz == NULL) {
        printf("A árvore está vazia.\n");
        return 5;
    }

    No *noAtual = arvore->raiz;
    int idx;

    while (noAtual != NULL) {
        idx = encontrarIndiceChave(noAtual, chave);

        // Se a chave for encontrada
        if (idx < noAtual->total && noAtual->chaves[idx] == chave) {

            if (ehFolha(noAtual)) {
                // Caso 1: O nó é folha
                for (int i = idx + 1; i < noAtual->total; i++) {
                    noAtual->chaves[i - 1] = noAtual->chaves[i];
                }
                noAtual->total--;

                // Ajuste se a raiz ficar vazia
                if (noAtual == arvore->raiz && noAtual->total == 0) {
                    free(noAtual);
                    arvore->raiz = NULL;
                }
                return 5;
            } else {
                // Caso 2: O nó não é folha
                int sucessor = obterSucessor(noAtual->filhos[idx + 1]);
                noAtual->chaves[idx] = sucessor;
                chave = sucessor;  // Substitui a chave a ser removida
                noAtual = noAtual->filhos[idx + 1];
            }
        } else if (!ehFolha(noAtual)) {
            // Continua descendo pela árvore
            No *filho = noAtual->filhos[idx];

            // Verifica necessidade de balanceamento no filho
            if (filho->total < arvore->ordem) {
                balancearAposRemocao(arvore, noAtual, idx);
            }

            noAtual = filho;
        } else {
            // A chave não foi encontrada na árvore
            //printf("Chave %d não está na árvore.\n", chave);
            return 5;
        }
    }

    return 5;
    
}
// Função para encontrar o índice da chave no nó
int encontrarIndiceChave(No *no, int chave) {
    int idx = 0;
    while (idx < no->total && no->chaves[idx] < chave) {
        idx++;
    }
    return idx;
}

// Função para obter o sucessor (menor chave maior que a chave removida)
int obterSucessor(No *no) {
    No *atual = no;
    while (atual->filhos[0] != NULL) {
        atual = atual->filhos[0];  // Vai para o filho mais à esquerda
    }
    return atual->chaves[0];
}

// Função para balancear a árvore após uma remoção, tentando emprestar ou fundir
void balancearAposRemocao(Arvore *arvore, No *pai, int idx) {
    No *filho = pai->filhos[idx];
    No *irmao;

    // Tenta emprestar do irmão esquerdo
    if (idx > 0 && pai->filhos[idx - 1]->total >= arvore->ordem) {
        irmao = pai->filhos[idx - 1];
        for (int i = filho->total; i > 0; i--) {
            filho->chaves[i] = filho->chaves[i - 1];
            filho->filhos[i + 1] = filho->filhos[i];
        }
        filho->filhos[1] = filho->filhos[0];
        filho->chaves[0] = pai->chaves[idx - 1];
        filho->filhos[0] = irmao->filhos[irmao->total];
        if (irmao->filhos[irmao->total] != NULL) {
            irmao->filhos[irmao->total]->pai = filho;
        }
        pai->chaves[idx - 1] = irmao->chaves[irmao->total - 1];
        filho->total++;
        irmao->total--;

    // Tenta emprestar do irmão direito
    } else if (idx < pai->total && pai->filhos[idx + 1]->total >= arvore->ordem) {
        irmao = pai->filhos[idx + 1];
        filho->chaves[filho->total] = pai->chaves[idx];
        filho->filhos[filho->total + 1] = irmao->filhos[0];
        if (irmao->filhos[0] != NULL) {
            irmao->filhos[0]->pai = filho;
        }
        pai->chaves[idx] = irmao->chaves[0];
        for (int i = 1; i < irmao->total; i++) {
            irmao->chaves[i - 1] = irmao->chaves[i];
            irmao->filhos[i - 1] = irmao->filhos[i];
        }
        irmao->filhos[irmao->total - 1] = irmao->filhos[irmao->total];
        filho->total++;
        irmao->total--;

    // Funde com um irmão
    } else {
        if (idx < pai->total) {
            fundirNos(arvore, pai, idx);
        } else {
            fundirNos(arvore, pai, idx - 1);
        }
    }
}

// Função para fundir dois nós (esquerdo e direito)
void fundirNos(Arvore *arvore, No *pai, int idx) {
    No *esq = pai->filhos[idx];
    No *dir = pai->filhos[idx + 1];

    // Move a chave do pai para o nó esquerdo
    esq->chaves[esq->total] = pai->chaves[idx];
    esq->total++;

    // Move as chaves e filhos do nó direito para o nó esquerdo
    for (int i = 0; i < dir->total; i++) {
        esq->chaves[esq->total] = dir->chaves[i];
        esq->filhos[esq->total] = dir->filhos[i];
        if (dir->filhos[i] != NULL) {
            dir->filhos[i]->pai = esq;
        }
        esq->total++;
    }

    // Conecta o último filho
    esq->filhos[esq->total] = dir->filhos[dir->total];
    if (dir->filhos[dir->total] != NULL) {
        dir->filhos[dir->total]->pai = esq;
    }

    // Ajusta o pai
    int var = idx + 1;
    while (var < pai->total) {
        pai->chaves[var - 1] = pai->chaves[var];
        pai->filhos[var] = pai->filhos[var + 1];
        var++;
    }
    pai->total--;


    // Libera a memória do nó direito
    free(dir);

    // Se o pai ficou vazio e é a raiz, ajusta a raiz
    if (pai->total == 0 && pai == arvore->raiz) {
        arvore->raiz = esq;
        esq->pai = NULL;
        free(pai);
    }
}
int ehFolha(No* no) {
    if (no == NULL) {
        return 0; // Nó nulo não é considerado uma folha
    }

    // Um nó é uma folha se todos os seus ponteiros para filhos forem NULL
    for (int i = 0; i <= no->total; i++) {
        if (no->filhos[i] != NULL) {
            return 0; // O nó tem pelo menos um filho, não é folha
        }
    }

    return 1; // Nenhum filho encontrado, o nó é uma folha
}
