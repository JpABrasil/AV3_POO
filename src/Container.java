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
}
