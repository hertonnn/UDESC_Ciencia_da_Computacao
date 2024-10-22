// AFD - Autômato Finito Determinístico

// M = {Q, Σ, δ, q0, F} - Quíntupla que representa o Autômato

// Q = {q0, q1} - Conjunto de estados
// Σ = {0, 1} - Alfabeto

/*
δ = {
        δ: (q0, 0) -> q1,
        δ: (q0, 1) -> q1,
        δ: (q1, 1) -> q1,
        δ: (q1, 0) -> q1
        ...
}*/

// q0 = q0 Estado iniciai
// F = {q1} Estados finais

// Código precisando de uma otimização e ajustada no tratamento de erros.

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define SIZEALPHABET 27

struct State{
    int end;
    char name[10];
};

struct Transition{
    struct State* current_state;
    struct State* next_state;
    char symbol;
};

struct AFD{
    int num_states;
    int num_transitions;
    struct State* init_state;
    struct State** states;
    struct Transition** transitions;
    char alphabet[SIZEALPHABET];
};

struct State* getState(char* name_state, struct AFD* automaton){
    for(int i = 0; i < automaton->num_states; i++){
        if(strcmp(name_state, automaton->states[i]->name) == 0){
            return automaton->states[i];
        }
    }
    return NULL;
}
struct Transition* getTransition(struct State* current_state, char symbol, struct AFD* automaton){
    for(int i = 0; i < automaton->num_transitions; i++){
        if(current_state == automaton->transitions[i]->current_state && symbol == automaton->transitions[i]->symbol){
            return automaton->transitions[i];
        }
    }
    return NULL;
}

struct AFD* createAutomaton(){
    // Otimizar esse código!!

    struct AFD* automaton = malloc(sizeof(struct AFD));
    char str[100];
    char str1[100];
    char str2[100];
    char* token;
    int num_states;
    int i = 0;


    printf("Entre com o autômato seguindo o seguinte formato:\n");
    printf("Conjunto de estados: a1 a2 a3\n");
    printf("Estados finais: a2 a3\n\nAgora é sua vez!\n\n");
    printf("Informe a quantidade de estados: ");
    scanf("%i", &num_states);
    automaton->num_states = num_states;
    getchar();
    
    automaton->states = malloc(num_states * sizeof(struct State));

    printf("Informe o conjunto de estados: ");
    scanf("%[^\n]s", str);
    getchar();
    
    token = strtok(str, " ");
   
    while (token)
    {
        struct State* state = malloc(sizeof(struct State));
        state->end = 0;
        strcpy(state->name, token);
        automaton->states[i++] = state;
        token = strtok(NULL, " ");
    }
    
    char name_init_state[10];
    printf("Informe o estado inicial: ");
    scanf("%[^\n]s", name_init_state);
    getchar();
    for(int j = 0; j < num_states; j++){
        if(strcmp(name_init_state, automaton->states[j]->name) == 0){
            automaton->init_state = automaton->states[j];
        }
    }
    
    printf("Informe os estados finais: ");
    scanf("%[^\n]s", str1);
    getchar();

    token = strtok(str1, " ");
    while (token)
    {
        for(int j = 0; j < num_states; j++){
            if(strcmp(token, automaton->states[j]->name) == 0){
                automaton->states[j]->end = 1;
            }
        }
        token = strtok(NULL, " ");
    }

    // alfabeto
    printf("Informe o alfabeto: ");
    scanf("%[^\n]s", str2);
    getchar();

    token = strtok(str2, " ");
    int num_symbol = 0;
    while (token)
    {
        automaton->alphabet[num_symbol++] = *token;
        token = strtok(NULL, " ");
    }
    automaton->alphabet[num_symbol + 1] = '\0';

    // transições

    int max_transitions = num_symbol * num_states;

    automaton->transitions = malloc(max_transitions * sizeof(struct Transition));

    printf("Difina as funções de transição: \n");
    int j = 0;
    int num_transitions = 0;
    char next_state[10];
    for(int i = 0; i < automaton->num_states; i++){
        while (automaton->alphabet[j] != '\0')
        {
            printf("δ: (%s, %c) -> ", automaton->states[i]->name, automaton->alphabet[j]);
            scanf("%[^\n]s", next_state);
            getchar();

            struct Transition* transition = malloc(sizeof(struct Transition));
            transition->current_state = automaton->states[i];
            transition->symbol = automaton->alphabet[j];

            if(strcmp(next_state, ".") == 0){
                transition->next_state = NULL;
            }
            else{
                transition->next_state = getState(next_state, automaton);
            }

            automaton->transitions[num_transitions++] = transition; 
            j++;
        
        }
        j = 0;
    } 

    automaton->num_transitions = num_transitions;

    return automaton;

}

void printAutomaton(struct AFD* atm){
    
    printf("\n============ O Autômato ============\n");
    printf("Estado inicial: %s\n", atm->init_state->name);
    printf("Estados: { ");
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
    printf("Transições: \n");
    for(int i = 0; i < atm->num_transitions; i++){
        printf("δ: (%s, %c) -> %s\n", atm->transitions[i]->current_state->name, atm->transitions[i]->symbol, atm->transitions[i]->next_state->name);
    }
    printf("====================================\n");
    
}

int main()
{   
    struct AFD* automaton = createAutomaton();
    printAutomaton(automaton);


    while (1)
    {
        // reconhecendo linguagens
        char input[100];
        printf("\n\nDigite a fita a ser reconhecida: ");
        scanf("%s", input);

        struct State* current_state = automaton->init_state;
        struct Transition* current_transition;

        printf("\nEvolução dos estados:\n");
        for(int i = 0; input[i] != '\0'; i++){
            printf("<%s> ", current_state->name);
            printf("%c ", input[i]);
            
            current_transition = getTransition(current_state, input[i], automaton);

            if(!current_transition){
                break;
            }
            current_state = current_transition->next_state;

        }
        if(current_state->end == 1 && current_transition != NULL){
            printf("<%s> ", current_state->name);
            printf("\nO autômato reconheceu a fita!");
        }else{
            printf("\nO autômato não reconheceu a fita!");
        }
    }
        
    return 0;
}
