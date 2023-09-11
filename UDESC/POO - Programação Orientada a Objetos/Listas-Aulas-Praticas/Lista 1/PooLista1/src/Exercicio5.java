# Exercicio a
class Instrutor:
    def __init__(self, nome, especialidade, experiencia):
        self.nome = nome
        self.especialidade = especialidade
        self.experiencia = experiencia

    def __str__(self):
        return f"Instrutor(nome={self.nome}, especialidade={self.especialidade}, experiência={self.experiencia} anos)"


class CategoriaCurso:
    def __init__(self, nome, descricao, nivel):
        self.nome = nome
        self.descricao = descricao
        self.nivel = nivel

    def __str__(self):
        return f"CategoriaCurso(nome={self.nome}, descrição={self.descricao}, nível={self.nivel})"

# Exercicio b
# Instanciando objetos
instrutor1 = Instrutor("Alice Silva", "Programação em Python", 5)
instrutor2 = Instrutor("Carlos Souza", "Desenvolvimento Web", 8)

categoria1 = CategoriaCurso("Programação Básica", "Introdução à programação", "Iniciante")
categoria2 = CategoriaCurso("Desenvolvimento Web", "Construindo websites", "Intermediário")

# Exibindo objetos
print(instrutor1)
print(instrutor2)
print(categoria1)
print(categoria2)