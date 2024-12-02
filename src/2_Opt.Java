public class Opt extends Algoritimo {
    public calcularPerformance(caminhoInput, caminhoOutput){
        List<Caixa> caixas = lerArquivo(caminhoInput).getCaixas();
        Container container = lerArquivo(caminhoInput).getContainer();
        List<String> linhas = Files.readAllLines(Paths.get(caminhoOutput)); 
        for (String linha : linhas) { //Percorrendo cada linnha
            String[] valores = linha.split(" ");//Separando cada valor daquela linha
            if (contadorLinha > 1 && contadorLinha < qtdTiposCaixa) { 
            }
        }
        return new ResultadoLeitura(container, caixas);
    }
    public static void solucionar(String caminhoInput, String caminhoOutput, boolean renderizarGrafico) throws IOException, InterruptedException {
        ResultadoLeitura resultadoLeitura = lerArquivo(caminhoInput);
        Container container = resultadoLeitura.getContainer();
        List<Caixa> caixas = resultadoLeitura.getCaixas();
        long startTime = System.nanoTime();
        //Compare a performancce de greedy e dac no problema em questão
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