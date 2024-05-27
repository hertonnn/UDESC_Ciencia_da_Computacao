//13.1.  Árvores binárias

// Resumo desse arquivo:
/*
- criar árvore binaria
- percorrer ela, printar e limpar de maneira recursiva
- calcular altura dela de maneira recursiva
- verificação de existencia de um valor na arvore
- busca em arvore binaria de maneira recursiva e retorno do elemento
- e por ultimo eu consegui compreender toda a composiçao da arvore para que a busca ocorra

Numa árvore binária, cada nó tem zero, um ou dois filhos. De maneira recursiva, podemos definir uma árvore binária como sendo:
• Uma árvore vazia; ou
• Um nó raiz tendo duas sub-árvores, identificadas como a sub-árvore da direita (sad) e a sub-árvore da esquerda (sae)

*/ 

#include <stdio.h>
#include <stdlib.h>

typedef struct tree{
    char content;
    struct tree *right;
    struct tree *left;
}*Tree; // tree = arvore // já é do tipo ponteiro para arvore

Tree create_arv(Tree right, char content, Tree left);
void print_arv(Tree tree);
Tree release_arv(Tree tree);
int height_arv(Tree tree);
int isEmpty(Tree tree);
int search_arv(Tree tree, char content);
Tree search_arv_chars(Tree tree, char content);


int main(){
    Tree root;
    Tree a1 = create_arv(NULL, 'G', NULL);
    Tree a3 = create_arv(NULL, 'E', NULL);
    Tree a5 = create_arv(NULL, 'C', NULL);
    Tree a6 = create_arv(NULL, 'A', NULL);
    Tree a4 = create_arv(a3, 'F', a1);
    Tree a2 = create_arv(a6, 'B', a5);
    root = create_arv(a2, 'D', a4);

    // Podemos acrescentar alguns nós, com:

    // root->left->left = create_arv(NULL, 'G', NULL);
    // root->left->left->left = create_arv(NULL, 'h', NULL);
    // root->left->left->left->right = create_arv(NULL, 'h', NULL);
    print_arv(root);
    printf("\n");
    
    printf("\n%c", search_arv_chars(root, 'G')->content);

    return 0;
}

/*Devemos notar que a  definição de árvore, por ser recursiva, não faz distinção entre árvores e sub-árvores. Assim, cria pode ser usada para acrescentar (“enxertar”) umasub-árvore em um ramo de uma árvore, e libera  pode ser usada para remover(“podar”) uma sub-árvore qualquer de uma árvore dada.*/

Tree create_arv(Tree left, char content, Tree right){
    Tree root = malloc(sizeof(Tree));

    if(root){
        root->content = content;
        root->right = right;
        root->left = left;
        return root;
    }
    printf("\nError in allocate...\n");
    return NULL;
}

// Retorna se a arvore está vazia
int isEmpty(Tree tree){
    return tree == NULL;
}
// Printa todos os valores da arvore de forma recursiva
void print_arv(Tree tree){
    if(!isEmpty(tree)){
        printf("%c", tree->content); // printa o valor da sub-arvore atual
        print_arv(tree->right); // aplica de forma recursiva na sub-arvore esquerda
        print_arv(tree->left); // aplica de forma recursiva na sub-arvore direita
    }
}

Tree release_arv(Tree tree){
    if(!isEmpty(tree)){
        release_arv(tree->right); // aplica de forma recursiva na sub-arvore esquerda
        release_arv(tree->left); // aplica de forma recursiva na sub-arvore direita
        free(tree); // libera a raiz atual por último
    }
    return NULL;
}

// Calcula de maneira recursiva o maior caminho da raiz até uma folha
int height_arv(Tree tree){
    if(isEmpty(tree)){
       return -1; 
    }
    else{
        int right = height_arv(tree->right); 
        int left = height_arv(tree->left);

        if(right > left){
            return right + 1;
        }
        else{
            return left + 1;
        }
    }
}

//Ordens de percurso em árvores binárias

/*
Muitas operações em árvores binárias envolvem o  percurso de todas as sub-árvores,executando alguma ação de tratamento em cada nó, de forma que é comum percorreruma árvore em uma das seguintes ordens:

• Pré-ordem: trata raiz, percorre sae, percorre sad;
• Ordem simétrica: percorre sae, trata raiz, percorre sad;
• Pós-ordem: percorre sae, percorre sad, trata raiz.

*/

// busca em Pré-ordem

/*
Note que esta forma de programar busca, em C, usando o operador lógico || (“ou”)faz com que a busca seja interrompida assim que o elemento é encontrado. Isto aconteceporque se c==a->info  for verdadeiro, as duas outras expressões não chegam a  seravaliadas. Analogamente, se o  caractere for encontrado na sub-árvore da esquerda, abusca não prossegue na sub-árvore da direita.
*/
int search_arv(Tree tree, char content){
    if(isEmpty(tree)){
        return 0; // Árvore vazia, não encontra nada
    }
    return tree->content == content || search_arv(tree->left, content) || search_arv(tree->right, content);
}


// Uma versão que retorne o próprio nó em uma árvore de chars.

/* No pior caso, a busca consome tempo proporcional à altura da árvore.  Se a árvore for balanceada, o consumo será proporcional a  log n ,  sendo n o número de nós.  Esse tempo é da mesma ordem que a busca binária num vetor ordenado.*/


Tree search_arv_chars(Tree tree, char content){
    if(isEmpty(tree)){
        return NULL; // Árvore vazia, não encontra nada
    }
    else if(tree->content == content){
        return tree;
    }
    else if(tree->content > content){
        return search_arv_chars(tree->left, content);
    }
    else{
        return search_arv_chars(tree->right, content);
    }
}