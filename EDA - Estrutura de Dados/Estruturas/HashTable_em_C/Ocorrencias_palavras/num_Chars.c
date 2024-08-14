/*
Estruturas de Dados – PUC-Rio17-9
Vamos considerar que uma palavra se caracteriza por uma seqüência de uma ou maisletras (maiúsculas ou minúsculas). Para contar o número de ocorrências de cada palavra,podemos armazenar as palavras lidas numa tabela de dispersão, usando a  própriapalavra como chave de busca. Guardaremos na estrutura de dados quantas vezes cadapalavra foi encontrada. 
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#define WORD_SIZE 64
#define LINE_SIZE 100
#define LIST_HASH_SIZE 100

typedef struct word{
    char value[WORD_SIZE];
    int num;
    struct word *next;
}*Word;

typedef struct hash{
    Word list[LIST_HASH_SIZE];
}*Hash;


void count_words(FILE *file, Hash hash);
void show_list(Hash hash);
void show_words(Word word);
int read_word(FILE *file, char *s);

int hashCode(char *key);
void free_words(Hash hash);
void insertWord(Hash hash, char *value);
Word searchWord(Hash hash, char *key);
Word createWord(char *value);

int main(){
    FILE *file;
    file = fopen("/home/herton/Documentos/PROGRAMAÇÃO/UDESC/EDA - Estrutura de Dados/avancado/HashTable_em_C/Ocorrencias_palavras/words.txt", "rt");
    Hash hash = malloc(sizeof(struct hash));

    if(file){
        count_words(file, hash);
        show_list(hash);
        free_words(hash);

        return 0;
    }
    printf("Error in file open...");
    exit(1);
    return 0;


}

void count_words(FILE *file, Hash hash){
    
    char *word = malloc(WORD_SIZE * sizeof(char *));

    while (read_word(file, word))
    {
        insertWord(hash, word);
    }
    free(word);
    return;
    
}

int read_word(FILE *file, char *s){

    char c;
    int i = 0;

    // descarta tudo que não é letra ate achar uma
    while ((c = fgetc(file)) != EOF)
    {
        if(isalpha(c)){
            break;
        }
    }
    
    // caso ele chegue ao fim do arquivo sem uma letra achada
    if(c == EOF){
        return 0;
    }
    s[i++] = c; // Já captura a cabeça
    // lê enquanto é um char e não é EOF e i não atingiu o tamanho máximo de chars de palavra
    while (i < WORD_SIZE - 1 && (c = fgetc(file)) != EOF && isalpha(c))
    {
        s[i++] = c;
    }
    s[i] = '\0';
    return 1;
}

void show_list(Hash hash){

   for(int i = 0; i < LIST_HASH_SIZE - 1; i++){
        if(hash->list[i]){
            printf("\n--- Posição/HashCode %i do Array ---\n", i);
            show_words(hash->list[i]);
        }
   }
    
}
void show_words(Word head){

    Word word = head;
    
    while (word)
    {
        printf("\n -> A Palavra %s se repetiu %i.\n", word->value, word->num);
        word = word->next;
    }
    
}

void insertWord(Hash hash, char *value){
    unsigned int index = hashCode(value);
    Word word = hash->list[index];

    if(word == NULL){
        hash->list[index] = createWord(value);
    }
    else{
        while (1) 
        {
            char *key = word->value;
            if(strcmp(value, key) == 0){
                word->num = word->num + 1;
                return;
            }
            if(word->next == NULL){
                break;
            }
            word = word->next;
        }
        word->next = createWord(value);
    }
}

Word searchWord(Hash hash, char *key){

    unsigned int index = hashCode(key);
    Word word = hash->list[index];

    if(word){
        while (word)
        {
            char *value = word->value;
            if(strcmp(value, key) == 0){
                return word;
            }
            word = word->next;
        }
        
    }
    return NULL;
}
Word createWord(char *value){
    Word word = malloc(sizeof(struct word));
        strcpy(word->value, value);
        word->next = NULL;
        word->num = 1;
    return word;
}

void free_words(Hash hash){
    int i = 0;
    Word word = hash->list[i++];
    Word next = word->next;

    while (word)
    {
        Word temp = next;
        while (next)
        {
            temp = next;
            next = next->next;
            free(temp);
        }
        word = hash->list[i++];  
        if(word){
            next = word->next; 
        }   
    }
    
    free(hash);
}


// Algoritmo de Hash simples
int hashCode(char *key){
    unsigned long hash = 2024;
    unsigned int c;

    while (c = *key++) 
    {
        hash = ((hash<<5) + hash) + c;
    }
    return hash % LIST_HASH_SIZE;
}