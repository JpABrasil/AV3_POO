import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Algoritimo {
    //Classe e método para podermos ler o arquivo
    public static class ResultadoLeitura {
        private Container container;
        private List<Caixa> caixas;

        public ResultadoLeitura(Container container, List<Caixa> caixas) {
            this.container = container;
            this.caixas = caixas;
        }

        public Container getContainer() {
            return container;
        }

        public List<Caixa> getCaixas() {
            return caixas;
        }
    }

    public static ResultadoLeitura lerArquivo(String caminhoInput) throws IOException {
        List<String> linhas = Files.readAllLines(Paths.get(caminhoInput)); //Lê todas as linhas do arquivo e guarda em uma lista
        int contadorLinha = 0; //Variável para controlar qual linha está sendo lida
        int qtdTiposCaixa = 0; //Váriavel para quantos tipos de caixa
        ArrayList<Caixa> caixas = new ArrayList<>(); //Lista para guardamos as caixas
        Container container = null;
        for (String linha : linhas) { //Percorrendo cada linnha
            String[] valores = linha.split(" ");//Separando cada valor daquela linha
            if (contadorLinha == 0) { //Na primeira linha temos o comprimento e a altura do container
                container = new Container(Integer.parseInt(valores[0]), Integer.parseInt(valores[1])); //Criamos o novo Container
                contadorLinha++;
                continue;
            }
            if (contadorLinha == 1) { //Segunda linha temos a quantidade de tipos de caixa -> Será usado para o looping interno
                qtdTiposCaixa = Integer.parseInt(valores[0]);
                contadorLinha++;
                continue;
            }
            if (contadorLinha > 1 && contadorLinha < qtdTiposCaixa) { //A partir da 3 linhas realizamos a leitura das próximas linhas de acordo com o número de tipos de caixas
                caixas.add(new Caixa(Integer.parseInt(valores[0]), Integer.parseInt(valores[1]), Integer.parseInt(valores[2]), Integer.parseInt(valores[3]))); //Guardamos as especificações dos tipos de caixa no nosso array
            }

        }
        return new ResultadoLeitura(container, caixas);
    }

    //Passando Container para script python gerar gráfico
    public static void iniciarGrafico(String caminhoInput, String caminhoOutput) throws IOException, InterruptedException {
        String pythonScriptPath = "D:\\Faculdade\\2_Semestre\\Programação Orientada a Objetos\\AV3_FINAL\\src\\graficos.py"; // Substitua pelo caminho correto
        String pythonExe = "python"; // Ou "python3" dependendo da sua instalação
        String[] parametros = {pythonExe, pythonScriptPath,
                caminhoInput,
                caminhoOutput,
        };
        ProcessBuilder processBuilder = new ProcessBuilder(parametros);
        Process process = processBuilder.start();
        int exitCode = process.waitFor();
    }

    //Salva a solução em um arquivo de saída
    public static void salvarSolucao(Container container,String caminhoOutput) throws IOException {
        // Abre o arquivo em modo de escrita para limpá-lo
        try (PrintWriter escritor = new PrintWriter(new FileWriter(caminhoOutput))) {
            // Apenas para limpar o arquivo; não é necessário escrever nada aqui
        }

        for (int i = 0; i < container.posicoes.size(); i++) {
            int tipoCaixa = container.caixas.get(i).tipo;
            int x = container.posicoes.get(i)[0];
            int y = container.posicoes.get(i)[1];
            String conteudo = tipoCaixa + " " + x + " " + y;
            try (PrintWriter escritor = new PrintWriter(new FileWriter(caminhoOutput, true))) {
                escritor.println(conteudo);
            }
        }
    }

    public static void salvarTempoExecucao(long duration,String caminhoOutput){
        try (PrintWriter escritor = new PrintWriter(new FileWriter(caminhoOutput))) {
            String conteudo = String.valueOf(duration);
            escritor.println(conteudo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
