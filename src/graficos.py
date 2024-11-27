import sys
import matplotlib.pyplot as plt
import pandas as pd
import numpy as np
from matplotlib.patches import Rectangle
def ler_entrada(nome_arquivo):
    caixas = {}
    with open(nome_arquivo, 'r') as arquivo:
        linhas = []
        contador = 0
        for linha in arquivo:
            linha = linha.strip()
            linha = linha.split(" ")
            if contador == 0:
                comprimento_container = int(linha[0])
                altura_container = int(linha[1])
                pass
            if contador == 1:
                n = int(linha[0])
                pass
            if contador > 1 and contador <= n + 1:
                caixas[int(linha[0])] = [int(linha[1]), int(linha[2]), int(linha[3])]
                pass
            contador+= 1
    return comprimento_container, altura_container, caixas
def main():
    #Ler Argumentos
    argumentos = sys.argv

    #Ler dados de Entrada
    comprimento_container,altura_container,descricao_caixas = ler_entrada(argumentos[1])

    #Calcular Área Total do Container
    area_total = comprimento_container*altura_container
    area_ocupada = 0

    #Ler Solução
    solucao = pd.read_csv(argumentos[2], sep=' ', header=None)
    tipos = solucao[0].unique()
    cores = np.random.rand(len(tipos)+1, 3)
    
    #Gerar gráfico
    fig,ax = plt.subplots()
    ax.set_xlim(0, comprimento_container)
    ax.set_ylim(0, altura_container)
    
    #ax.set_aspect('equal')
    texto_aproveitamento = ax.annotate(f'%Aproveitamento: {area_ocupada/area_total:.2%} ', (comprimento_container/2,altura_container), color='black', fontsize=12, ha='center')
    plt.gca().set_aspect('equal', adjustable='box')
    plt.ion()
    plt.show()
    for i in range(len(solucao)):
        caixa = descricao_caixas[solucao[0][i]]
        comprimento_caixa = caixa[0]
        altura_caixa = caixa[1]
        area_caixa = comprimento_caixa*altura_caixa
        area_ocupada += area_caixa
        x = solucao[1][i]
        y = solucao[2][i]   
        quadrado = Rectangle((x, y),comprimento_caixa, altura_caixa, edgecolor='black', facecolor=cores[solucao[0][i]], linewidth=1)
        ax.add_patch(quadrado) 
        texto_aproveitamento.set_text(f'%Aproveitamento: {area_ocupada/area_total:.2%}')
        plt.draw()
        plt.pause(0.5)
    plt.ioff()
    plt.show()


if __name__ == "__main__":
    main()
