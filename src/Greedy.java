import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Greedy extends Algoritimo {
    public static class v01_dq{ //dq -> Desconsiderando Quantidade
        //Exemplo de 3 tipos -> input_1.txt
        public static void solucionar(String caminhoInput, String caminhoOutput, boolean renderizarGrafico) throws IOException, InterruptedException {
            ResultadoLeitura resultadoLeitura = lerArquivo(caminhoInput);
            Container container = resultadoLeitura.getContainer();
            List<Caixa> caixas = resultadoLeitura.getCaixas();

            //Algoritimo agrupando as caixas que ocuparem maior área juntas
            //Alocar bloco que ocupar maior área
            Caixa caixaMaiorBloco = container.caixaMaiorBloco(caixas);
            int xInicial = 0;
            int yInicial = 0;
            for (int i = 0; i < container.altura / caixaMaiorBloco.altura; i++) {
                for (int j = 0; j < container.comprimento / caixaMaiorBloco.comprimento; j++) {
                        int[] posicao = {xInicial, yInicial};
                        container.adicionarCaixa(caixaMaiorBloco, posicao);
                        caixaMaiorBloco.quantidade -= 1;
                        xInicial += caixaMaiorBloco.comprimento;
                }
                xInicial = 0;
                yInicial += caixaMaiorBloco.altura;
            }

            //Escreve a Solução em um Arquivo de Saída
            salvarSolucao(container,caminhoOutput);


            //Iniciando Gráfico
            if(renderizarGrafico){
                iniciarGrafico(caminhoInput, caminhoOutput);
            }


        }
    }
    public static class v01{
        public static void solucionar(String caminhoInput, String caminhoOutput, boolean renderizarGrafico) throws IOException, InterruptedException {
            ResultadoLeitura resultadoLeitura = lerArquivo(caminhoInput);
            Container container = resultadoLeitura.getContainer();
            List<Caixa> caixas = resultadoLeitura.getCaixas();

            //Algoritimo agrupando as caixas que ocuparem maior área juntas
            //Alocar bloco que ocupar maior área
            Caixa caixaMaiorBloco = container.caixaMaiorBloco(caixas);
            int xInicial = 0;
            int yInicial = 0;
            for (int i = 0; i < container.altura / caixaMaiorBloco.altura; i++) {
                for (int j = 0; j < container.comprimento / caixaMaiorBloco.comprimento; j++) {
                    if(caixaMaiorBloco.quantidade > 0) {
                        int[] posicao = {xInicial, yInicial};
                        container.adicionarCaixa(caixaMaiorBloco, posicao);
                        caixaMaiorBloco.quantidade -= 1;
                        xInicial += caixaMaiorBloco.comprimento;
                    }
                }
                xInicial = 0;
                yInicial += caixaMaiorBloco.altura;
            }

            //Escreve a Solução em um Arquivo de Saída
            salvarSolucao(container,caminhoOutput);


            //Iniciando Gráfico
            if(renderizarGrafico){
                iniciarGrafico(caminhoInput, caminhoOutput);
            }


        }
    }

    public static class v02_dq {//dq -> Desconsiderando Quantidade
        //Exemplo de 3 tipos -> input_1.txt
        public static void solucionar(String caminhoInput, String caminhoOutput, boolean renderizarGrafico) throws IOException, InterruptedException {
            ResultadoLeitura resultadoLeitura = lerArquivo(caminhoInput);
            Container container = resultadoLeitura.getContainer();
            List<Caixa> caixas = resultadoLeitura.getCaixas();

            //Algoritimo agrupando as caixas que ocuparem maior área juntas
            //Alocar bloco que ocupar maior área
            Caixa caixaMaiorBloco = container.caixaMaiorBloco(caixas);
            int xInicial = 0;
            int yInicial = 0;
            for (int i = 0; i < container.altura / caixaMaiorBloco.altura; i++) {
                for (int j = 0; j < container.comprimento / caixaMaiorBloco.comprimento; j++) {
                    int[] posicao = {xInicial, yInicial};
                    container.adicionarCaixa(caixaMaiorBloco, posicao);
                    xInicial += caixaMaiorBloco.comprimento;
                }
                xInicial = 0;
                yInicial += caixaMaiorBloco.altura;
            }
            yInicial = 0;

            //Verificar se ainda há espaço para outro bloco
            //No eixo X
            Caixa caixaMaisADireita = container.caixaQueCabeMaisADireita(caixas);
            if (!(caixaMaisADireita == null)) {
                int xMaisADireita = container.xMaisADireita();
                for (int i = 0; i < container.altura / caixaMaisADireita.altura; i++) {
                    int[] posicao = {xMaisADireita, yInicial};
                    container.adicionarCaixa(caixaMaisADireita, posicao);
                    yInicial += caixaMaisADireita.altura;
                }
            }


            //Escreve a Solução em um Arquivo de Saída
            salvarSolucao(container,caminhoOutput);


            //Iniciando Gráfico
            if(renderizarGrafico){
                iniciarGrafico(caminhoInput, caminhoOutput);
            }


        }
    }
    public static class v02 {
        public static void solucionar(String caminhoInput, String caminhoOutput, boolean renderizarGrafico) throws IOException, InterruptedException {
            ResultadoLeitura resultadoLeitura = lerArquivo(caminhoInput);
            Container container = resultadoLeitura.getContainer();
            List<Caixa> caixas = resultadoLeitura.getCaixas();

            //Algoritimo agrupando as caixas que ocuparem maior área juntas
            //Alocar bloco que ocupar maior área
            Caixa caixaMaiorBloco = container.caixaMaiorBloco(caixas);
            int xInicial = 0;
            int yInicial = 0;
            for (int i = 0; i < container.altura / caixaMaiorBloco.altura; i++) {
                for (int j = 0; j < container.comprimento / caixaMaiorBloco.comprimento; j++) {
                    if(caixaMaiorBloco.quantidade > 0) {
                        int[] posicao = {xInicial, yInicial};
                        container.adicionarCaixa(caixaMaiorBloco, posicao);
                        caixaMaiorBloco.quantidade -= 1;
                        xInicial += caixaMaiorBloco.comprimento;
                    }
                }
                xInicial = 0;
                yInicial += caixaMaiorBloco.altura;
            }
            yInicial = 0;

            //Verificar se ainda há espaço para outro bloco
            //No eixo X
            Caixa caixaMaisADireita = container.caixaQueCabeMaisADireita(caixas);
            if (!(caixaMaisADireita == null)) {
                int xMaisADireita = container.xMaisADireita();
                for (int i = 0; i < container.altura / caixaMaisADireita.altura; i++) {
                    if(caixaMaisADireita.quantidade > 0) {
                        int[] posicao = {xInicial, yInicial};
                        container.adicionarCaixa(caixaMaisADireita, posicao);
                        caixaMaiorBloco.quantidade -= 1;
                        xInicial += caixaMaisADireita.comprimento;
                    }int[] posicao = {xMaisADireita, yInicial};
                    container.adicionarCaixa(caixaMaisADireita, posicao);
                    yInicial += caixaMaisADireita.altura;
                }
            }


            //Escreve a Solução em um Arquivo de Saída
            salvarSolucao(container,caminhoOutput);


            //Iniciando Gráfico
            if(renderizarGrafico){
                iniciarGrafico(caminhoInput, caminhoOutput);
            }


        }
    }


}
