import java.util.ArrayList;
import java.util.List;

public class Container extends Objeto2D{
    public ArrayList<int[]> posicoes;
    public ArrayList<Caixa> caixas;

    public Container(int comprimento, int altura) {
        super(comprimento, altura);
        posicoes = new ArrayList<>();
        caixas = new ArrayList<>();
    }
    public Caixa caixaMaiorBloco(List<Caixa> caixas){
        Caixa caixaMaiorBloco = caixas.getFirst();
        for(Caixa caixa : caixas) {
            int qtdCaixasMaiorBlocoHorizontal = this.comprimento/caixaMaiorBloco.comprimento;
            int qtdCaixasMaiorBlocoVertical = this.altura/caixaMaiorBloco.altura;
            int qtdCaixasHorizontal = this.comprimento/caixa.comprimento;
            int qtdCaixasVertical = this.altura/caixa.altura;
            if( caixa.calcArea() * qtdCaixasHorizontal * qtdCaixasVertical > caixaMaiorBloco.calcArea() * qtdCaixasMaiorBlocoHorizontal * qtdCaixasMaiorBlocoVertical) {
                caixaMaiorBloco = caixa;
            }
        }
        return caixaMaiorBloco;
    }

    public void adicionarCaixa(Caixa caixa,int[] posicao){
        posicoes.add(posicao);
        caixas.add(caixa);
    };

    public int xMaisADireita(){
       int maisADireita = posicoes.getFirst()[0];
       int comprimentoCaixaADireita = caixas.getFirst().comprimento;
       for(int i = 0; i < posicoes.size(); i++){
           if(posicoes.get(i)[0] > maisADireita){
               maisADireita = posicoes.get(i)[0];
               comprimentoCaixaADireita = caixas.get(i).comprimento;
           }
       }
       return maisADireita+comprimentoCaixaADireita;
   }


    public Caixa caixaQueCabeMaisADireita(List<Caixa> caixas){
        int posicaoMaisADireita = this.xMaisADireita();
        Caixa caixaMaisADireita = null;
        for(Caixa caixa : caixas){
            if(posicaoMaisADireita + caixa.comprimento <= this.comprimento){
                caixaMaisADireita = caixa;
            }
        }
        return caixaMaisADireita;
    }

    public boolean cabeCaixa(Caixa caixa, int[] posicao) {
        int x = posicao[0];
        int y = posicao[1];

        // verifica se a caixa cabe dentro do container
        if (x + caixa.comprimento > this.comprimento || y + caixa.altura > this.altura) {
            return false;
        }
        //se cabe continua

        // checa se a caixa nao ta sobrepondo outra
        for (int i = 0; i < posicoes.size(); i++) {
            int[] posicaoExistente = posicoes.get(i);
            Caixa caixaExistente = caixas.get(i);

            if (x < posicaoExistente[0] + caixaExistente.comprimento && x + caixa.comprimento > posicaoExistente[0] && y < posicaoExistente[1] + caixaExistente.altura && y + caixa.altura > posicaoExistente[1]) {
                return false;
            }
        }

        // se chegou aqui cabe no container
        return true;
    }
   public void removerCaixa(Caixa caixa, int[] posicao){
       for(int i = 0; i < posicoes.size(); i++){
           if(posicoes.get(i)[0] == posicao[0] && posicoes.get(i)[1] == posicao[1]){
               posicoes.remove(i);
               caixas.remove(i);
               break;
           }
       }
   }

}
