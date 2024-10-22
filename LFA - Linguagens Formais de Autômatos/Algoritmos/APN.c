// AFD - Autômato Finito Determinístico

// M = (∑, Q, δ, q0, F, V) Representa o Autômato

// Q = {q0, q1} - Conjunto de estados
// Σ = {a, b} - Alfabeto

/*
(estado_atual, caracter_lido, desempilha) -> {(proximo_estado, empilha)}

δ = {
        δ: (q0, a, ε) -> {(q0, A)},  ε = Movimento vazio
        δ: (q0, b, A) -> {(q1, ε)},  ? = Pilha vazia?
        δ: (q1, ε, ?) -> {(q0, A)},
        δ: (q1, b, B) -> {(q1, ε)}
        ...
}*/

// q0 = q0 Estado iniciai
// F = {q1} Estados finais
// V = {A} Alfabeto da pilha

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "pilha.c"

#define SIZEALPHABET 27
#define SIZENAMES 10
#define SIZEINPUT 100
#define EMPTY 'e' // Movimento vazio
#define EMPTYSTACK '?' // Pilha Vazia?

struct State{
    int end;
    char name[SIZENAMES];
};

struct Transition{
    char char_stack;
    char char_unstack;
    char symbol;
    struct State* current_state;
    struct State* next_state;
};

struct AFD{
    int num_states;
    int num_transitions;
    char alphabet[SIZEALPHABET];
    char alphabetStack[SIZEALPHABET];
    struct State* init_state;
    struct State** states;
    struct Transition** transitions;
};

struct State* getState(char* name_state, struct AFD* automaton){
    for(int i = 0; i < automaton->num_states; i++){
        if(strcmp(name_state, automaton->states[i]->name) == 0){
            return automaton->states[i];
        }
    }
    return NULL;
}

struct Transition* getTransition(struct State* current_state, char symbol, char unstack, struct AFD* automaton){
    if(unstack == EMPTYSTACK){
        for(int i = 0; i < automaton->num_transitions; i++){
            if(current_state == automaton->transitions[i]->current_state && symbol == automaton->transitions[i]->symbol && automaton->transitions[i]->char_unstack == unstack){
                    return automaton->transitions[i];
                }
        }
    }
    for(int i = 0; i < automaton->num_transitions; i++){
        if(current_state == automaton->transitions[i]->current_state && symbol == automaton->transitions[i]->symbol){
                return automaton->transitions[i];
            }
    }
    
    return NULL;
}

// Verifica input
int init_APN(char* input, struct AFD* automaton, Cell* stack){

    struct State* current_state = automaton->init_state;
    Cell* head = stack;

    struct Transition** possible_changes_states = checkChangeStates(current_state);

    int accepted = 1;
    int index_transition = 0;

    while (/*Enquanto eu não sei o que*/)
    {
        current_state = possible_changes_states[index_transition]->next_state;
        possible_changes_states = checkChangeStates(current_state);
    }
    


    // for(int i = 0; input[i] != '\0'; i++){
    //     printf("<%s> ", current_state->name);
    //     printf("%c ", input[i]);

    //     current_transition = getTransition(current_state, input[i], ' ', automaton);
        
    //     // não achou transição
    //     if(!current_transition){
    //         accepted = 0;
    //         break;
    //     }
    //     if(current_transition->char_stack != EMPTY){
    //         head = push(head, current_transition->char_stack);
    //     }
    //     if(current_transition->char_unstack != EMPTY){
    //         // o topo não condiz com o que quero desempilhar
    //         if(head->content != current_transition->char_unstack){
    //             accepted = 0;
    //             break;
    //         }
    //         pop(&head);
    //     }
    //     current_state = current_transition->next_state;
    // }
    // head = pop(&head); // Se a pilha estiver vazia ele retira a cabeça que tem como conteúdo '#'
    // if(isEmpty(head) && accepted){
    //     current_transition = getTransition(current_state, EMPTYSTACK, EMPTYSTACK, automaton);
    //     if(current_transition != NULL && current_transition->next_state->end == 1){
    //         printf("<%s> ", current_state->name);
    //         return 1;
    //     }
    // }
    return 0;
}

struct AFD* createAutomaton(FILE* input){

    struct AFD* automaton = malloc(sizeof(struct AFD));
    char buffer[SIZEINPUT];

    input = fopen("input_AP.txt","r");

    if(!input){
        printf("\nArquivo não encontrado!\n");
        exit(1);
    }

    // Ler Estados
    int num_state;
    int state_count = 0;

    fgets(buffer, SIZEINPUT, input); // Lê o cabeçalho e descarta
    fgets(buffer, SIZEINPUT, input); // Lê a quantidade de estados

    num_state = atoi(buffer);
    automaton->num_states = num_state;

    automaton->states = malloc(num_state * sizeof(struct State));

    fgets(buffer, SIZEINPUT, input); // Agora sim lê os estados
    char* token = strtok(buffer, "{, \n}");
    while (token != NULL) {
        automaton->states[state_count] = (struct State*) malloc(sizeof(struct State));
        strcpy(automaton->states[state_count]->name, token);
        automaton->states[state_count]->end = 0; // Não final inicialmente
        token = strtok(NULL, "{, \n}");
        state_count++;
    }

    // Ler Estado Inicial
    fgets(buffer, SIZEINPUT, input);
    fgets(buffer, SIZEINPUT, input);
    token = strtok(buffer, "\n");
    automaton->init_state = getState(token, automaton);

    // Ler Estado Final
    fgets(buffer, SIZEINPUT, input);
    fgets(buffer, SIZEINPUT, input);
    token = strtok(buffer, "{, }");
    while (token != NULL) {
        struct State* final_state = getState(token, automaton);
        if (final_state){
            final_state->end = 1; // Estado final
        } 
        token = strtok(NULL, "{, }");
    }

    // Ler Alfabeto
    fgets(buffer, SIZEINPUT, input);
    fgets(buffer, SIZEINPUT, input);
    token = strtok(buffer, "{, \n}");
    int i = 0;
    while (token != NULL) {
        automaton->alphabet[i++] = token[0];
        token = strtok(NULL, "{, \n}");
    }

    // Ler Alfabeto da Pilha
    fgets(buffer, SIZEINPUT, input);
    fgets(buffer, SIZEINPUT, input);
    token = strtok(buffer, "{, \n}");
    i = 0;
    while (token != NULL) {
        automaton->alphabetStack[i++] = token[0];
        token = strtok(NULL, "{, \n}");
    }


    // Ler Transições
    int num_transition;
    int transition_count = 0;

    fgets(buffer, SIZEINPUT, input); // Lê o cabeçalho e descarta
    fgets(buffer, SIZEINPUT, input); // Lê a quantidade de transições

    num_transition = atoi(buffer);
    automaton->num_transitions = num_transition;
    automaton->transitions = malloc(num_transition * sizeof(struct Transition));

    while (fgets(buffer, SIZEINPUT, input)) { // Agora sim lendo as transições
        char current_state[SIZENAMES], next_state[SIZENAMES];
        char symbol, char_stack, char_unstack;
        sscanf(buffer, "( %[^, ] , %c , %c ) = { ( %[^, ] , %c ) }", current_state, &symbol, &char_unstack, next_state, &char_stack);
        
        automaton->transitions[transition_count] = (struct Transition*) malloc(sizeof(struct Transition));
        automaton->transitions[transition_count]->current_state = getState(current_state, automaton);
        automaton->transitions[transition_count]->next_state = getState(next_state, automaton);
        automaton->transitions[transition_count]->symbol = symbol;
        automaton->transitions[transition_count]->char_stack = char_stack;
        automaton->transitions[transition_count]->char_unstack = char_unstack;
        transition_count++;
    }

    return automaton;

}

void printAutomaton(struct AFD* atm){
    
    printf("\n============ O Autômato com Pilha ============\n\n");
    printf("Estado inicial: %s\n", atm->init_state->name);
    printf("Estados:(%i) { ", atm->num_states);
    for(int i = 0; i < atm->num_states; i++){
        printf("%s ", atm->states[i]->name);
    }
    printf("}\n");
    printf("Estados finais: { ");
    for(int i = 0; i < atm->num_states; i++){
        if(atm->states[i]->end){
            printf("%s ", atm->states[i]->name);
        }
    }
    printf("}\n");
    printf("Alfabeto: { ");
    int i = 0;
    while(atm->alphabet[i] != '\0'){
        printf("%c ", atm->alphabet[i]);
        i++;
    }
    printf("}\n");
    printf("Alfabeto da pilha: { ");
    i = 0;
    while(atm->alphabetStack[i] != '\0'){
        printf("%c ", atm->alphabetStack[i]);
        i++;
    }
    printf("}\n");
    printf("Transições(%i): \n", atm->num_transitions);
    for(int i = 0; i < atm->num_transitions; i++){
        printf("δ: (%s, %c, %c) -> {(%s, %c)}\n", atm->transitions[i]->current_state->name, atm->transitions[i]->symbol, atm->transitions[i]->char_unstack, atm->transitions[i]->next_state->name, atm->transitions[i]->char_stack);
    }
    printf("\nobs: Verifique seu autômato!\n");
    printf("\n==============================================\n");
    
}

int main(){
    FILE* file_input = fopen("AFD/input_AP.txt","r");
    struct AFD* automaton = createAutomaton(file_input);

    // Verificando informações do Autômato!
    printAutomaton(automaton);


    while (1)
    {
        // Teste da fita
        char input[SIZEINPUT];
        Cell* stack = createStack();

        printf("\nDigite a fita a ser lida: ");
        scanf("%s", input);

        int accepted = init_APN(input, automaton, stack);

        if(accepted){
            printf("\nLinguagem reconhecida!");
        }else{
            printf("\nLinguagem não reconhecida!");
        }

        printf("\n");
    }
    
    return 0;
}