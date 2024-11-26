import java.util.ArrayList;

public class Container extends Objeto2D{
    public ArrayList<Caixa> caixasDentroDoContainer;
    public Container(int comprimento, int altura) {
        super(comprimento, altura);
    }
    public void adicionarCaixa(Caixa caixa){
        caixasDentroDoContainer.add(caixa);
    }
}
