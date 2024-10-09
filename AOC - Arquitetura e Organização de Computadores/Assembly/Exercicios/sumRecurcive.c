#include<stdio.h>

int sum(int x) {
    if (x == 0)
        return 0;
    return x + sum(x-1);
}

int main() {
    int a;
    scanf("%i", &a);
    a = sum(a);
    printf("%i\n", a);
    return 0;
}