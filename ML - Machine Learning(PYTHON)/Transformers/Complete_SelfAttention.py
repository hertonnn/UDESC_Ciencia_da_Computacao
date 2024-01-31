import torch
from torch import nn
import torch.nn.functional as F

class SelfAttention(nn.Module):
    def __init__(self, k, heads = 4, mask = False):

        super().__init__()

        assert k % heads == 0 # depurar, verificar o código

        self.k, self.heads = k, heads

        # Estes cálculos são para computar as queries, keys 
        # e values para todas as cabeças (heads).

        self.tokeys = nn.Linear(k,k, bias=False)
        self.toqueries = nn.Linear(k,k, bias=False)
        self.tovalues = nn.Linear(k,k, bias=False)

        # Isso será aplicado após a operação de autoatenção de
        #  várias cabeças (multi-head self-attention).

        self.unifyheads = nn.Linear(k,k)

        def forward(self, x):

            b, t, k = x.size() # Retorna o tamanho do Tensor x

            h = self.heads

            queries = self.toqueries(x)
            keys = self.tokeys(x)
            values = self.tovalues(x)

