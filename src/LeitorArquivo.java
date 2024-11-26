import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
public class LeitorArquivo {
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

    public ResultadoLeitura lerArquivo(String caminhoInput) throws IOException {
        List<String> linhas = Files.readAllLines(Paths.get(caminhoInput)); //Lê todas as linhas do arquivo e guarda em uma lista
        int contadorLinha = 0; //Variável para controlar qual linha está sendo lida
        int qtdTiposCaixa = 0; //Váriavel para quantos tipos de caixa
        ArrayList<Caixa> caixas = new ArrayList<>(); //Lista para guardamos as caixas
        Container container = null;
        for(String linha : linhas) { //Percorrendo cada linnha
            String[] valores = linha.split(" ");//Separando cada valor daquela linha
            if(contadorLinha == 0) { //Na primeira linha temos o comprimento e a altura do container
                container = new Container(Integer.parseInt(valores[0]),Integer.parseInt(valores[1])); //Criamos o novo Container
            }
            if(contadorLinha == 1) { //Segunda linha temos a quantidade de tipos de caixa -> Será usado para o looping interno
                qtdTiposCaixa = Integer.parseInt(valores[0]);
            }
            if(contadorLinha > 1) { //A partir da 3 linhas realizamos a leitura das próximas linhas de acordo com o número de tipos de caixas
                for(int j = 0; j < qtdTiposCaixa; j++) {
                    caixas.add(new Caixa(Integer.parseInt(valores[0]),Integer.parseInt(valores[1]),Integer.parseInt(valores[2]),Integer.parseInt(valores[3]))); //Guardamos as especificações dos tipos de caixa no nosso array
                }
            }
            contadorLinha++;
        }
        return new ResultadoLeitura(container, caixas);
    }
}
