#include <stdio.h>
#include <stdlib.h>

int main(int argc, char const *argv[])
{
    int v[] = {2,2,3,4,5};
    int n = 5;
    int *v1 = (int *) malloc(n * sizeof(int));

    int count = v[0];
    for(int i = 1; i < n; i++){
        count = count * v[i];
    }

    for(int j = 0; j < n; j++){
        v1[j] = count/v[j];
        printf("%i ", v1[j]);
    }
    
    return 0;
}
