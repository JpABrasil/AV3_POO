import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DaC extends Algoritimo {
    public static void solucionar(String caminhoInput, String caminhoOutput, boolean renderizarGrafico) throws IOException, InterruptedException {
        // le os dados de entrada do container lust
        ResultadoLeitura resultadoLeitura = lerArquivo(caminhoInput);
        Container container = resultadoLeitura.getContainer();
        List<Caixa> caixas = resultadoLeitura.getCaixas();

        long startTime = System.nanoTime();
        // ordena as caixas por area em ordem decrescente
        caixas.sort(Comparator.comparingInt(c -> -c.calcArea()));

        // executa o algoritmo divide and conquer
        executarDivideAndConquer(caixas, container);
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        //Salva o tempo de execução
        String[] tempoExecucao = caminhoOutput.split("\\\\");
        tempoExecucao[0] = "temposExecucao";
        String caminhoTempoExecucao = String.join("\\\\", tempoExecucao);
        salvarTempoExecucao(totalTime,caminhoTempoExecucao);
        // salva a solucao no arquivo de saida
        salvarSolucao(container, caminhoOutput);

        // renderiza o grafico se botar true 
        if (renderizarGrafico) {
            iniciarGrafico(caminhoInput, caminhoOutput);
        }
    }

    public static void executarDivideAndConquer(List<Caixa> caixas, Container container) {
        // resolve pra grupinhos 
        if (caixas.size() <= 5) {
            resolverDiretamente(caixas, container);
            return;
        }

        // divide as caixas em dois grupos balanceados pela area total
        List<Caixa> grupo1 = new ArrayList<>();
        List<Caixa> grupo2 = new ArrayList<>();
        int areaTotal = caixas.stream().mapToInt(Caixa::calcArea).sum();
        int areaGrupo1 = 0;

        for (Caixa caixa : caixas) {
            if (areaGrupo1 + caixa.calcArea() <= areaTotal / 2) {
                grupo1.add(caixa);
                areaGrupo1 += caixa.calcArea();
            } else {
                grupo2.add(caixa);
            }
        }

        // aqui repete o processo se o grupo ainda for maior que 5, caso seja menor entra no if de cima
        executarDivideAndConquer(grupo1, container);
        executarDivideAndConquer(grupo2, container);
    }
    // metodo para grupinhos de caixa
    private static void resolverDiretamente(List<Caixa> caixas, Container container) {
        // tenta alocar todas as caixas no container
        for (Caixa caixa : caixas) { // caixa caixa caixas
            int[] melhorPosicao = buscarMelhorPosicao(container, caixa);
            while (melhorPosicao != null) { // botei um while pra enquanto tiver espaco ele meter caixa
                container.adicionarCaixa(caixa, melhorPosicao);
                melhorPosicao = buscarMelhorPosicao(container, caixa); // busca outra posicao para a mesma caixa
            }
        }
    }

    // busca a melhor posicao para uma caixa no container
    private static int[] buscarMelhorPosicao(Container container, Caixa caixa) {
        int melhorX = -1, melhorY = -1;

        // percorre cada posicao possivel no container
        for (int x = 0; x + caixa.comprimento <= container.comprimento; x++) {
            for (int y = 0; y + caixa.altura <= container.altura; y++) {
                int[] posicao = {x, y};
                if (container.cabeCaixa(caixa, posicao)) { // verifica se a caixa cabe
                    return posicao; // retorna a primeira posicao valida encontrada
                }
            }
        }

        // retorna null se nenhuma posicao valida for encontrada
        return null;
    }
}
