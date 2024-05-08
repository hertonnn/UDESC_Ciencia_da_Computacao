## Eliminação com Pivotamento 
import numpy as np
import math

A = np.array([[0,0,1], [0,1,0], [1,0,0]], dtype='double')
E = np.array([[1,1,1],[1,1,1],[1,1,1]], dtype='double')
v = np.array([[2],[3],[4]], dtype='double')

e = pow(10, -15)

A = A + e*E

print(A)
print(E)
print(v)

def eliminacao(A, B):

    n = len(A)
    X = [0]*n

    for j in range(1, n - 1):
        ## verificar o maior pivo
        pivo = math.fabs(A[j][j])
        linhaPivo = j
        for c in range(j + 1, n):
            if math.fabs(A[c][j]>pivo):
                pivo = math.fabs(A[c][j])
                linhaPivo = c
        ## permutar as linhas
        if linhaPivo != j:
            aux = A[j]
            A[j] = A[linhaPivo] 
            A[linhaPivo] = aux

            bAux = B[j]
            B[j] = B[linhaPivo]
            A[linhaPivo] = bAux
        # Eliminação Gauss
        for i in range(j + 1, n):
            m = A[i][j]/A[j][j]
            for k in range(1,j):
                A[i][j] = 0
            for k in range(j + 1, n):
                A[i][k] = A[i][k] - m*A[i][k]
            B[i] = B[i] - m*A[i][j]
    X[n-1] = B[n-1]/A[n-1][n-1]
    
    for k in range(n-1, 1):
        soma = 0
        for j in range(n):
            soma = soma + A[k][j]*X[j]
        X[k] = (B[k] - soma)/A[k][k]    
    
    print(X)


eliminacao(A, v)
