class AFD:
    def __init__(self, estados, alfabeto, transicoes, estado_inicial, estados_finais):
        """
        Inicializa o AFD com os parâmetros fornecidos.

        :param estados: Lista de estados do autômato.
        :param alfabeto: Conjunto de símbolos permitidos.
        :param transicoes: Dicionário de transições no formato {estado: {simbolo: estado_destino}}.
        :param estado_inicial: Estado inicial do autômato.
        :param estados_finais: Conjunto de estados finais.
        """
        self.estados = estados
        self.alfabeto = alfabeto
        self.transicoes = transicoes
        self.estado_inicial = estado_inicial
        self.estados_finais = estados_finais

    def processar(self, cadeia):
        """
        Processa uma cadeia de entrada e verifica se é aceita pelo autômato.

        :param cadeia: String a ser processada.
        :return: True se a cadeia for aceita, False caso contrário.
        """
        estado_atual = self.estado_inicial

        for simbolo in cadeia:
            if simbolo not in self.alfabeto:
                print(f"Erro: símbolo '{simbolo}' não pertence ao alfabeto.")
                return False

            if simbolo in self.transicoes[estado_atual]:
                estado_atual = self.transicoes[estado_atual][simbolo]
            else:
                print(f"Erro: não há transição definida para o estado '{estado_atual}' com o símbolo '{simbolo}'.")
                return False

        if estado_atual in self.estados_finais:
            return True
        else:
            return False


# # Configuração do AFD
# estados = ['q0', 'q1', 'q2']
# alfabeto = {'a', 'b'}
# transicoes = {
#     'q0': {'a': 'q1', 'b': 'q0'},
#     'q1': {'a': 'q1', 'b': 'q2'},
#     'q2': {'a': 'q2', 'b': 'q2'}
# }
# estado_inicial = 'q0'
# estados_finais = {'q2'}

# # Criando o AFD
# afd = AFD(estados, alfabeto, transicoes, estado_inicial, estados_finais)

# # Testando o AFD com cadeias de entrada
# cadeias = ['aab', 'ab', 'aaab', 'bbaa']
# for cadeia in cadeias:
#     resultado = afd.processar(cadeia)
#     print(f"A cadeia '{cadeia}' é {'aceita' if resultado else 'rejeitada'} pelo AFD.")
