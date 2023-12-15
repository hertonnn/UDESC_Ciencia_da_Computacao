import json

with open("exercicio5/dados.json", encoding='utf-8') as dados_json:
    dados = json.load(dados_json)

class Filme:
    def __init__(self, titulo, ano, classificacao, nota):
        self.titulo = titulo
        self.ano = ano
        self.classificacao = classificacao
        self.nota = nota

    def atribui(self, json_filme):
        self.titulo = json_filme.get("titulo")
        self.ano = json_filme.get("ano")
        self.classificacao = json_filme.get("classificacao")
        self.nota = json_filme.get("nota")

    def print(self):
        print("---------------------------------")
        print("Título: " + self.titulo)
        print("Nota: " + str(self.nota))
        print("Classificação: " + self.classificacao)
        print("Ano: " + str(self.ano))

filmes = []

for dado in dados:
    filme = Filme("", 0, "", 0)
    filme.atribui(dado)
    filmes.append(filme)

filmes_ordenados = sorted(filmes, key=lambda filme: filme.nota, reverse=True)

for filme in filmes_ordenados:
    filme.print()
