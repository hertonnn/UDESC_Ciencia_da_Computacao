import AFD 
import AP

class Sistema:
    def __init__(self, entradas, retornos, estacionamentos, cruzamentos, transicoes):
        """
        Inicializa o sistema com os atributos principais.
        :param entradas: Lista ou conjunto de entradas no sistema.
        :param retornos: Lista ou conjunto de retornos no sistema.
        :param estacionamentos: Lista ou conjunto de estacionamentos disponíveis.
        :param cruzamentos: Lista ou conjunto de cruzamentos no sistema.
        """
        self.entradas = entradas 
        self.retornos = retornos  
        self.estacionamentos = estacionamentos 
        self.cruzamentos = cruzamentos  
        self.transicoes = transicoes
        
    def calc_tragetorias(self):
        estados = entradas | cruzamentos | retornos
        
        for estado_inicial in entradas:
            afd = AFD()

        

class Cruzamento:
    def __init__(self, nome, semaforo = None):

        self.nome = nome
        self.semaforo = semaforo


# Etapa 1: Representação do Movimento dos Veículos
# - modelando para um cenário específico de trânsito!

entradas = {'A', 'B', 'C', 'D'}
retornos = {'R1', 'R2', 'R3', 'R4'}
estacionamentos = {'P1', 'P2', 'P3', 'P4', 'P5'}

C1 = Cruzamento('C1')
C2 = Cruzamento('C2')
C3 = Cruzamento('C3')
C4 = Cruzamento('C4')

cruzamentos = {C1, C2, C3, C4}

transicoes = 'aqui'

sistema = Sistema(entradas, retornos, estacionamentos, cruzamentos, transicoes)
sistema.calc_tragetorias()

