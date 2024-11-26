import sys
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D

def main():
    # Configurações iniciais do gráfico
    argumentos = sys.argv
    comprimento_container = int(argumentos[1])
    altura_container = int(argumentos[2])
    largura_container = int(argumentos[3])
    qtdTiposCaixa = int(argumentos[4])

    # Criando uma figura
    fig = plt.figure()
    ax = fig.add_subplot(111, projection='3d')

    # Adicionando título e rótulos aos eixos
    ax.set_xlabel('Eixo X')
    ax.set_ylabel('Eixo Z')
    ax.set_zlabel('Eixo Y')

    # Ajustando os limites dos eixos
    ax.set_xlim([0, comprimento_container])
    ax.set_zlim([0, altura_container])
    ax.set_ylim([0, largura_container])

    # Proporção dos eixos
    max_range = max(comprimento_container, largura_container, altura_container)
    ax.set_box_aspect([comprimento_container / max_range,
                       largura_container / max_range,
                       altura_container / max_range])

    plt.show()

if __name__ == "__main__":
    main()
