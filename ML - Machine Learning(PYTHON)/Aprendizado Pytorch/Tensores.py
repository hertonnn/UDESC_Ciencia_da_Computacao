# A estrutura de dados básica em torno da qual o PyTorch é construído é o Tensor. 
# Ele funciona praticamente exatamente como os tensores que já vimos no numpy.

import torch 

# Para criar uma matriz preenchida com valores fornecidos:

matriz = torch.tensor([[1,2,3], [4,5,6]])

# Para criar uma matriz preenchida com números aleatórios 
# distribuídos normalmente (distribuição normal):

matriz_random = torch.randn(3,4)

# Para matrizes de dimensões mais altas (também conhecidas como tensores), 
# basta adicionar mais dimensões: 

matriz_random = torch.randn(3,4,2)
print(matriz_random)

# Podemos obter o tamanho de um tensor com a função size(). Para obter o 
# tamanho de uma dimensão específica, basta adicionar o índice dessa dimensão.

print(matriz_random.size())
print(matriz_random.size(0))

# Assim como no numpy, podemos somar, multiplicar e aplicar funções básicas elemento a elemento.

a, b = torch.randn(3,4), torch.randn(3,4)
c = torch.randn(4,3)

# adição, multiplicação, etc. são todas operações element-wise (realizadas elemento a elemento).

soma = a + b
mult = a * b # de elemento a elemento
exp = a ** 2
sine = torch.sin(a)

# A multiplicação de matrizes é feita através de torch.mm.

mmult = torch.mm(a, c)

# Indexação e fatiamento (slicing) funcionam da mesma forma que no numpy.

print(a)
print()
print("elemento da primeira linha e coluna da matriz a:", a[1,2])
print("primeira linha da a", a[0,:])
print("primeira coluna", a[:,0]) # na horizontal tb


#Você também pode usar a sintaxe básica de fatiamento; i:j refere-se ao intervalo de i até j
#(mais precisamente, i é o primeiro elemento incluído, j é o primeiro elemento excluído)

print("pegando dois elementos do meia de cada linha de a", a[:, 1:3])
print()
print()
#---------------------------------------------------#

# tensor.view()

# Remodelagem (reshaping):
# Podemos pegar os dados de uma matriz na memória e criar uma segunda matriz com 
# outra forma a partir deles. Isso é feito pela função view(), que recebe um tensor 
# e retorna uma nova visualização (view) dos mesmos dados, assumindo uma forma diferente:

matriz = torch.tensor([[1,2,3,4,5],[6,7,8,9,10],[10,11,12,13,15]])

print(matriz)
print(matriz.view(1,15)) # 1 LINHA E OS 15 ELEMENTOS 
print(matriz.view(size=(15,)))

# Você pode usar -1 para um dos argumentos. O PyTorch irá calcular o tamanho dessa 
# dimensão a partir dos outros valores.

print(matriz.view(-1,))

# observe a diferença entre a matriz remodelada da transposta .t()

print()

matriz = torch.tensor([[1,2,3],[4,5,6]])
print(matriz.t())
print(matriz.view(3,2))

# matrix.t().view(2, 3)              # isso falha
matriz.t().contiguous().view(2, 3)   # isso  funciona, mas copia a matriz
print(matriz)

matriz.t().reshape(2, 3) # isso funciona, porém copia a matriz sem avisar você


