import random
import plotly.graph_objects as go
import networkx as nx
import matplotlib.pyplot as plt
from collections import defaultdict

class Grafo:
    def __init__(self, dirigido=False):
        self.grafo = defaultdict(list)
        self.dirigido = dirigido

    def addAresta(self, vertice, vizinho):
        if vizinho not in self.grafo[vertice]:
            self.grafo[vertice].append(vizinho)
            if not self.dirigido:
                self.grafo[vizinho].append(vertice)
        return self

    def getVertices(self):
        return list(self.grafo.keys())

    def getArestas(self):
        arestas = []
        for vertice, vizinhos in self.grafo.items():
            for vizinho in vizinhos:
                arestas.append((vertice, vizinho))
        return arestas

    def loadFromFile(self, file_path):
        with open(file_path, "r") as file:
            for line in file:
                a, b = map(int, line.split())
                self.addAresta(a, b)

def plot_3D_dual_graphs(file1, file2, separation=2.0):
    # Carregar grafos
    grafo1 = Grafo()
    grafo1.loadFromFile(file1)
    grafo2 = Grafo()
    grafo2.loadFromFile(file2)

    # Posicionar vértices aleatoriamente em 3D
    pos1 = {v: [random.random(), random.random(), random.random()] for v in grafo1.getVertices()}
    pos2 = {v: [random.random() + separation, random.random(), random.random()] for v in grafo2.getVertices()}

    x1, y1, z1 = zip(*[pos1[v] for v in grafo1.getVertices()])
    x2, y2, z2 = zip(*[pos2[v] for v in grafo2.getVertices()])

    x_edges1, y_edges1, z_edges1 = [], [], []
    x_edges2, y_edges2, z_edges2 = [], [], []

    for (u, v) in grafo1.getArestas():
        x_edges1 += [pos1[u][0], pos1[v][0], None]
        y_edges1 += [pos1[u][1], pos1[v][1], None]
        z_edges1 += [pos1[u][2], pos1[v][2], None]

    for (u, v) in grafo2.getArestas():
        x_edges2 += [pos2[u][0], pos2[v][0], None]
        y_edges2 += [pos2[u][1], pos2[v][1], None]
        z_edges2 += [pos2[u][2], pos2[v][2], None]

    # Traços das arestas e vértices
    trace_edges1 = go.Scatter3d(x=x_edges1, y=y_edges1, z=z_edges1, mode='lines', line=dict(color='blue', width=2), hoverinfo='none')
    trace_edges2 = go.Scatter3d(x=x_edges2, y=y_edges2, z=z_edges2, mode='lines', line=dict(color='green', width=2), hoverinfo='none')

    trace_nodes1 = go.Scatter3d(x=x1, y=y1, z=z1, mode='markers', marker=dict(color='skyblue', size=10), name="Grafo 1")
    trace_nodes2 = go.Scatter3d(x=x2, y=y2, z=z2, mode='markers', marker=dict(color='orange', size=10), name="Grafo 2")

    layout = go.Layout(title="Visualização 3D de Dois Grafos Separados", margin=dict(l=0, r=0, t=50, b=0), showlegend=True)
    fig = go.Figure(data=[trace_edges1, trace_nodes1, trace_edges2, trace_nodes2], layout=layout)
    fig.show()

def plot_2D_dual_graphs(file1, file2):
    # Carregar grafos
    grafo1 = Grafo()
    grafo1.loadFromFile(file1)
    grafo2 = Grafo()
    grafo2.loadFromFile(file2)

    # Criar visualizações 2D com NetworkX
    G1 = nx.Graph()
    G1.add_edges_from(grafo1.getArestas())
    
    G2 = nx.Graph()
    G2.add_edges_from(grafo2.getArestas())

    # Configurar layout
    plt.figure(figsize=(10, 5))
    pos1 = nx.spring_layout(G1, seed=42)
    pos2 = {node: (x + 2, y) for node, (x, y) in nx.spring_layout(G2, seed=42).items()}

    # Desenhar grafo 1
    nx.draw(G1, pos=pos1, with_labels=True, node_color='skyblue', edge_color='blue', node_size=500, font_size=10, font_color='black')

    # Desenhar grafo 2 com deslocamento
    nx.draw(G2, pos=pos2, with_labels=True, node_color='orange', edge_color='green', node_size=500, font_size=10, font_color='black')

    plt.title("Visualização 2D de Dois Grafos Separados")
    plt.show()

# Exemplo de uso
if __name__ == "__main__":
    file1 = 'cluster1.txt'  # Substitua pelo caminho do arquivo de arestas do grafo 1
    file2 = 'cluster2.txt'  # Substitua pelo caminho do arquivo de arestas do grafo 2
    plot_3D_dual_graphs(file1, file2)  # Visualização 3D
    plot_2D_dual_graphs(file1, file2)  # Visualização 2D
