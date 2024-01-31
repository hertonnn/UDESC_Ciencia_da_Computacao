# implementação básica do modelo de atenção

import torch
import torch.nn.functional as F

# suponhamos que temos um tensor x com tamanho (b,t,k)
shape = (2,3,3)
x = torch.rand(shape)

# calculamos os pesos brutos multiplicando a matriz de entrada pela sua 
# transposta

pesos_brutos = torch.bmm(x, x.transpose(1,2))

# torch.bmm é para mult. de matrizes em lote

# aplicando softmax

pesos = F.softmax(pesos_brutos, dim=2)

# calculamos a saida multiplicando os pesos pela entrada

y = torch.bmm(pesos, x)

print(y)