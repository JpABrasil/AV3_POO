import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

public class Greedy extends Algoritimo {
    //Versão 1 -> Apenas adiciona o maior bloco possível no container
    public static class v01_dq{ //dq -> Desconsiderando Quantidade
        //Exemplo de 3 tipos -> input_1.txt
        public static void solucionar(String caminhoInput, String caminhoOutput, boolean renderizarGrafico) throws IOException, InterruptedException {
            ResultadoLeitura resultadoLeitura = lerArquivo(caminhoInput);
            Container container = resultadoLeitura.getContainer();
            List<Caixa> caixas = resultadoLeitura.getCaixas();

            long startTime = System.nanoTime();

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

            long endTime = System.nanoTime();
            long totalTime = endTime - startTime;

            //Salva o tempo de execução
            String[] tempoExecucao = caminhoOutput.split("\\\\");
            tempoExecucao[0] = "temposExecucao";
            String caminhoTempoExecucao = String.join("\\\\", tempoExecucao);
            salvarTempoExecucao(totalTime,caminhoTempoExecucao);

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

            long startTime = System.nanoTime();
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
            long endTime = System.nanoTime();
            long totalTime = endTime - startTime;
            String[] tempoExecucao = caminhoOutput.split("\\\\");
            tempoExecucao[0] = "temposExecucao";
            String caminhoTempoExecucao = String.join("\\\\", tempoExecucao);
            salvarTempoExecucao(totalTime,caminhoTempoExecucao);

            //Escreve a Solução em um Arquivo de Saída
            salvarSolucao(container,caminhoOutput);


            //Iniciando Gráfico
            if(renderizarGrafico){
                iniciarGrafico(caminhoInput, caminhoOutput);
            }


        }
    }

    //Versão 2 -> Adiciona um segundo bloco mais a direita na tentativa de completar
    public static class v02_dq {//dq -> Desconsiderando Quantidade
        //Exemplo de 3 tipos -> input_1.txt
        public static void solucionar(String caminhoInput, String caminhoOutput, boolean renderizarGrafico) throws IOException, InterruptedException {
            ResultadoLeitura resultadoLeitura = lerArquivo(caminhoInput);
            Container container = resultadoLeitura.getContainer();
            List<Caixa> caixas = resultadoLeitura.getCaixas();

            long startTime = System.nanoTime();
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
            long endTime = System.nanoTime();
            long totalTime = endTime - startTime;
            //Salva o tempo de execução
            String[] tempoExecucao = caminhoOutput.split("\\\\");
            tempoExecucao[0] = "temposExecucao";
            String caminhoTempoExecucao = String.join("\\\\", tempoExecucao);
            salvarTempoExecucao(totalTime,caminhoTempoExecucao);
            //Escreve a Solução em um Arquivo de Saída*/
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


            long startTime = System.nanoTime();
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
                    int[] posicao = {xMaisADireita, yInicial};
                    container.adicionarCaixa(caixaMaisADireita, posicao);
                    yInicial += caixaMaisADireita.altura;
                }
            }
            long endTime = System.nanoTime();
            long totalTime = endTime - startTime;
            //Salva o tempo de execução
            String[] tempoExecucao = caminhoOutput.split("\\\\");
            tempoExecucao[0] = "temposExecucao";
            String caminhoTempoExecucao = String.join("\\\\", tempoExecucao);
            salvarTempoExecucao(totalTime,caminhoTempoExecucao);
            //Escreve a Solução em um Arquivo de Saída
            salvarSolucao(container,caminhoOutput);


            //Iniciando Gráfico
            if(renderizarGrafico){
              iniciarGrafico(caminhoInput, caminhoOutput);
            }


        }
    }

    //Versão 3-> Verifica blocos no eixo x e no eixo y
    public static class v03 {
        public static boolean estaLivre(Container container, Caixa novaCaixa, int x, int y) {
            // Verifica se a nova caixa ultrapassa os limites do contêiner
            if (y + novaCaixa.altura > container.altura || x + novaCaixa.comprimento > container.comprimento) {
                return false; // A caixa ultrapassa os limites do contêiner
            }

            // Verifica se a posição da nova caixa colide com qualquer caixa existente
            for (int i = 0; i < container.posicoes.size(); i++) {
                int[] posicaoExistente = container.posicoes.get(i);
                Caixa caixaExistente = container.caixas.get(i);

                // Verifica se há sobreposição entre a nova caixa e a caixa existente
                if (temSobreposicao(posicaoExistente, caixaExistente, x, y, novaCaixa)) {
                    return false; // Se houver sobreposição, a posição está ocupada
                }
            }

            return true; // Se não houver sobreposição, a posição está livre
        }

        // Verifica se há sobreposição entre a nova caixa e uma caixa existente
        public static boolean temSobreposicao(int[] posicaoExistente, Caixa caixaExistente, int x, int y, Caixa novaCaixa) {
            // Calcula os limites da caixa existente
            int yMaxExistente = posicaoExistente[1] + caixaExistente.altura - 1;
            int xMaxExistente = posicaoExistente[0] + caixaExistente.comprimento - 1;

            // Calcula os limites da nova caixa
            int yMaxNova = y + novaCaixa.altura - 1;
            int xMaxNova = x + novaCaixa.comprimento - 1;

            // Verifica se há sobreposição entre as caixas
            return !(yMaxNova < posicaoExistente[1] || y > yMaxExistente ||
                    xMaxNova < posicaoExistente[0] || x > xMaxExistente);
        }

        public static List<int[]> encontrarPosicoesLivres(Container container, Caixa novaCaixa) {
            List<int[]> posicoesLivres = new ArrayList<>();

            // Itera por todas as posições possíveis no contêiner
            for (int y = 0; y <= container.altura - novaCaixa.altura; y++) {
                for (int x = 0; x <= container.comprimento - novaCaixa.comprimento; x++) {
                    // Verifica se a posição (x, y) está livre para a nova caixa
                    if (estaLivre(container, novaCaixa, x, y)) {
                        posicoesLivres.add(new int[] {x, y});
                    }
                }
            }

            return posicoesLivres;
        }

        public static void ordenarPorArea(List<Caixa> caixas) {
            Collections.sort(caixas, new Comparator<Caixa>() {
                @Override
                public int compare(Caixa c1, Caixa c2) {
                    // Calcula a área das duas caixas
                    int area1 = c1.comprimento * c1.altura;
                    int area2 = c2.comprimento * c2.altura;

                    // Ordena de forma decrescente (maior para menor)
                    return Integer.compare(area2, area1);
                }
            });
        }

        public static void solucionar(String caminhoInput, String caminhoOutput, boolean renderizarGrafico) throws IOException, InterruptedException {
            ResultadoLeitura resultadoLeitura = lerArquivo(caminhoInput);
            Container container = resultadoLeitura.getContainer();
            List<Caixa> caixas = resultadoLeitura.getCaixas();
            ordenarPorArea(caixas);


            long startTime = System.nanoTime();
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
                    int[] posicao = {xMaisADireita, yInicial};
                    container.adicionarCaixa(caixaMaisADireita, posicao);
                    yInicial += caixaMaisADireita.altura;
                }
            }

            //Verificação final tentando adicionar caixas
            for (Caixa caixa : caixas) {
                while (caixa.quantidade > 0) {
                    // Encontra todas as posições livres para a caixa atual
                    List<int[]> posicoesLivres = encontrarPosicoesLivres(container, caixa);

                    // Se houver pelo menos uma posição livre, adiciona a caixa na primeira posição encontrada
                    if (!posicoesLivres.isEmpty()) {
                        int[] posicao = posicoesLivres.get(0); // Pega a primeira posição disponível
                        container.adicionarCaixa(caixa, posicao);
                        caixa.quantidade--; // Reduz a quantidade de caixas restantes
                    } else {
                        break; // Não há mais posições livres para esta caixa
                    }
                }
            }





            long endTime = System.nanoTime();
            long totalTime = endTime - startTime;
            //Salva o tempo de execução
            String[] tempoExecucao = caminhoOutput.split("\\\\");
            tempoExecucao[0] = "temposExecucao";
            String caminhoTempoExecucao = String.join("\\\\", tempoExecucao);
            salvarTempoExecucao(totalTime,caminhoTempoExecucao);
            //Escreve a Solução em um Arquivo de Saída
            salvarSolucao(container,caminhoOutput);


            //Iniciando Gráfico
            if(renderizarGrafico){
                iniciarGrafico(caminhoInput, caminhoOutput);
            }


        }
    }

}
