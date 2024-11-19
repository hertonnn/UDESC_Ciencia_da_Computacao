import pandas as pd
import matplotlib.pyplot as plt
import glob
import re

# Caminho para os arquivos CSV
caminho_arquivos = "./TabelasGeradas/insercoesGrupo*Tamanho*.csv"
arquivos_csv = glob.glob(caminho_arquivos)

# Dicionário para armazenar dataframes organizados por tamanho de conjunto
dataframes_por_tamanho = {}

for arquivo in arquivos_csv:
    # Extrair o tamanho do conjunto do nome do arquivo usando regex
    match = re.search(r"Tamanho(\d+)", arquivo)
    if match:
        tamanho = int(match.group(1))
        
        # Ler o arquivo CSV
        df = pd.read_csv(arquivo)
        
        # Agrupar dataframes pelo tamanho do conjunto
        if tamanho not in dataframes_por_tamanho:
            dataframes_por_tamanho[tamanho] = []
        dataframes_por_tamanho[tamanho].append(df)

# DataFrame final para armazenar médias de inserção por tamanho de conjunto
medias_por_tamanho = []

# Calcular média para cada tamanho
for tamanho, dfs in dataframes_por_tamanho.items():
    # Concatenar todos os dataframes de um mesmo tamanho
    df_concat = pd.concat(dfs)
    
    # Calcular a média para este tamanho de conjunto
    df_media = df_concat.mean(numeric_only=True)
    df_media["Tamanho do Conjunto"] = tamanho
    medias_por_tamanho.append(df_media)

# Criar DataFrame final com todas as médias
df_medias_finais = pd.DataFrame(medias_por_tamanho).set_index("Tamanho do Conjunto").sort_index()

# Plotar o gráfico
plt.figure(figsize=(10, 6))
plt.plot(df_medias_finais.index, df_medias_finais["Media AVL"], label="AVL", marker='o')
plt.plot(df_medias_finais.index, df_medias_finais["Media RN"], label="Rubro-Negra", marker='s')
plt.plot(df_medias_finais.index, df_medias_finais["Media B(1)"], label="B (Ordem 1)", marker='^')
plt.plot(df_medias_finais.index, df_medias_finais["Media B(5)"], label="B (Ordem 5)", marker='x')
plt.plot(df_medias_finais.index, df_medias_finais["Media B(10)"], label="B (Ordem 10)", marker='*')

plt.xlabel("Tamanho do Conjunto")
plt.ylabel("Média das Operações de Inserção")
plt.title("Média das Operações de Inserção para Diferentes Estruturas de Árvore")
plt.legend()
plt.grid(True)

plt.show()
