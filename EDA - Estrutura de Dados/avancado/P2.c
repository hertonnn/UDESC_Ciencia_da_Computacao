#include <stdio.h>
#include <stdlib.h>
#include <string.h>


FILE *arqDependentes;
FILE *arqFuncionarios;

struct Dep{
    int relacao;
    char nome[250];
    char cpf[12];
};
typedef struct Dep Dependente;

struct Func{
    int num_dep;
    int ano_adm;
    int estado_civil;
    char nome[250];
    char cpf[12];
    char cpf_dep[20][20];
};
typedef struct Func Funcionario;


Funcionario criaFuncionario(){
    Funcionario func;

    printf("\nDigite o nome:");
    scanf("\n%[^\n]s", func.nome);
    printf("\nDigite o cpf:");
    scanf("\n%[^\n]s", func.cpf);
    printf("\nDigite o numero de dependentes:");
    scanf("\n%i", &func.num_dep);
    printf("\nDigite o ano de admissão:");
    scanf("\n%i", &func.ano_adm);
    printf("\nDigite o estado civil:");
    printf("\n1 - solteiro");
    printf("\n2 - casado/uniao estavel");
    printf("\n3 - divorciado");
    printf("\n4 - viuvo\n");
    scanf("\n%i", &func.estado_civil);

    return func;
}

Dependente criaDependente(){
    Dependente dependente;

    printf("\nDigite o nome:");
    scanf("\n%[^\n]s", dependente.nome);
    printf("\nDigite o cpf:");
    scanf("\n%[^\n]s", dependente.cpf);
    printf("\nDigite a relação:");
    printf("\n1 - conjuge");
    printf("\n2 - filho(a)/enteado(a)");
    printf("\n3 - Mae");
    printf("\n4 - Pai\n");
    scanf("\n%i", &dependente.relacao);

    return dependente;

}

void salvaDependente(Dependente dep){
    arqDependentes = fopen("dependentes.txt", "a+");
    fprintf(arqDependentes, "%s,%s,%i\n", dep.nome,dep.cpf,dep.relacao);
    fclose(arqDependentes);

}
void salvaFuncionario(Funcionario func){
    arqFuncionarios = fopen("funcionarios.txt", "a+");
    fprintf(arqFuncionarios, "%s,%s,[" ,func.nome,func.cpf);
    for(int i = 0; i < func.num_dep; i++){
        fprintf(arqFuncionarios, "%s,", func.cpf_dep[i]);
    }
    fprintf(arqFuncionarios, "]");
    fprintf(arqFuncionarios, "%i,%i\n", func.ano_adm, func.estado_civil);
    fclose(arqFuncionarios);

}
int menu(){
    
    int opcao;
    printf("\nDigite o que deseja fazer:");
    printf("\n1 - Criar funcionario");
    printf("\n2 - Vincular dependentes(do ultimo funcionario cadastrado)");
    printf("\n4 - Sair\n");
    scanf("%i", &opcao);

    return opcao;
}

int main(){
    
    int fim = 1;

    while (fim != 0)   
    {
        Funcionario funcionario;

        int opcao = menu();

        switch (opcao)
        {
        case 1:

            funcionario = criaFuncionario();
            // salva  em arquivo
            printf("Funcionario criado!\n");
            break;
        case 2:
            
            int num_dep = funcionario.num_dep;

            for(int i = 0; i<num_dep; i++){
                Dependente dependente;
                dependente = criaDependente(i, funcionario);
                // salva  em arquivo
                salvaDependente(dependente);
                strcpy(funcionario.cpf_dep[i], dependente.cpf);
            }
            // cpf do dependente
            salvaFuncionario(funcionario);
            printf("Dependentes vinculados!\n");
            break;
        case 4:
            fim = 0;
            break;
        default:
            break;
        }
    }

    
}