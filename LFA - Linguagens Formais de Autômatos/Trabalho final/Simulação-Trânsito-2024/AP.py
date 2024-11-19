class AP:
    def __init__(self, estados, alfabeto, alfabeto_pilha, transicoes, estado_inicial, estados_finais, simbolo_inicial_pilha):
        """
        Inicializa o Autômato com Pilha (AP).
        :param estados: Lista de estados do AP.
        :param alfabeto: Conjunto de símbolos aceitos na entrada.
        :param alfabeto_pilha: Conjunto de símbolos da pilha.
        :param transicoes: Dicionário de transições no formato:
            {(estado_atual, simbolo_entrada, topo_pilha): [(estado_destino, novos_simbolos_pilha)]}.
        :param estado_inicial: Estado inicial do AP.
        :param estados_finais: Conjunto de estados finais.
        :param simbolo_inicial_pilha: Símbolo inicial da pilha.
        """
        self.estados = estados
        self.alfabeto = alfabeto
        self.alfabeto_pilha = alfabeto_pilha
        self.transicoes = transicoes
        self.estado_inicial = estado_inicial
        self.estados_finais = estados_finais
        self.simbolo_inicial_pilha = simbolo_inicial_pilha

    def processar(self, cadeia):
        """
        Processa uma cadeia de entrada e verifica se é aceita pelo AP.
        :param cadeia: String de entrada.
        :return: True se a cadeia for aceita, False caso contrário.
        """
        return self._processar_recursivo(self.estado_inicial, cadeia, [self.simbolo_inicial_pilha])

    def _processar_recursivo(self, estado, cadeia, pilha):
        """
        Função recursiva que processa o AP.
        :param estado: Estado atual.
        :param cadeia: Cadeia restante a ser processada.
        :param pilha: Configuração atual da pilha.
        :return: True se a cadeia for aceita, False caso contrário.
        """
        # Se não há mais símbolos na entrada e o estado atual é final e a pilha contém apenas o símbolo inicial
        if not cadeia and estado in self.estados_finais and pilha == [self.simbolo_inicial_pilha]:
            return True

        # Obter o símbolo atual da entrada e o topo da pilha
        simbolo_atual = cadeia[0] if cadeia else None
        topo_pilha = pilha[-1] if pilha else None

        # Percorrer todas as transições possíveis a partir do estado atual
        for (estado_atual, simbolo_entrada, topo), transicoes in self.transicoes.items():
            if estado_atual == estado and (simbolo_entrada == simbolo_atual or simbolo_entrada == 'ε') and topo == topo_pilha:
                for (estado_destino, novos_simbolos_pilha) in transicoes:
                    # Atualizar a pilha: remover o topo e empilhar os novos símbolos (se existirem)
                    nova_pilha = pilha[:-1] if pilha else []
                    if novos_simbolos_pilha != ['ε']:
                        nova_pilha.extend(reversed(novos_simbolos_pilha))

                    # Avançar na cadeia de entrada se não for uma transição ε
                    nova_cadeia = cadeia[1:] if simbolo_entrada != 'ε' else cadeia

                    # Chamada recursiva
                    if self._processar_recursivo(estado_destino, nova_cadeia, nova_pilha):
                        return True

        return False


# # Configurando o autômato
# estados = {'q0', 'q1', 'q2', 'q3', 'q4'}
# alfabeto = {'a', 'b'}
# alfabeto_pilha = {'a', 'b', 'Z'}
# transicoes = {
#     # Empilha 'a' para cada 'a' na primeira parte
#     ('q0', 'a', 'Z'): [('q1', ['a', 'Z'])],
#     ('q1', 'a', 'a'): [('q1', ['a', 'a'])],
    
#     # Transita para empilhar 'b' ao encontrar o primeiro 'b'
#     ('q1', 'b', 'a'): [('q2', ['b', 'a'])],
#     ('q2', 'b', 'b'): [('q2', ['b', 'b'])],
    
#     # Transita para desempilhar ao encontrar o primeiro 'a' após 'b'
#     ('q2', 'a', 'b'): [('q3', ['ε'])],
#     ('q3', 'a', 'a'): [('q3', ['ε'])],
#     ('q3', 'a', 'b'): [('q3', ['ε'])],
    
#     # Finaliza se a pilha estiver no estado inicial e a cadeia acabar
#     ('q3', 'ε', 'Z'): [('q4', ['Z'])],
# }
# estado_inicial = 'q0'
# estados_finais = {'q4'}
# simbolo_inicial_pilha = 'Z'

# # Criando o autômato
# ap = AP(estados, alfabeto, alfabeto_pilha, transicoes, estado_inicial, estados_finais, simbolo_inicial_pilha)

# # Testando o autômato
# cadeias = [
#     "aaabbbbaaaa",  # Aceita: n=3, m=4
#     "abaaa",        # Aceita: n=1, m=1
#     "aaa",          # Rejeita: falta parte do meio
#     "aabb",         # Rejeita: parte final inválida
# ]

# for cadeia in cadeias:
#     print(f"Cadeia '{cadeia}' é aceita? {ap.processar(cadeia)}")
