#include <stdio.h>
#include <stdlib.h>

int maior(int vetor[], int n){
   int inicio = vetor[0], excl = 0, novo, i;

   for (i = 1; i < n; i++) {
      novo = inicio > excl ? inicio : excl;
      
      inicio = excl + vetor[i];
      excl = novo;
   }

   return inicio > excl ? inicio : excl;
}

int main() {
   int v1[] = {2,4,6,2,5};
	
   printf("%d\n", soma(v1,5));

   return 0;
}
