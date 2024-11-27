public class Caixa extends Objeto2D{
    public int tipo;
    public int quantidade;
    public Caixa(int tipo,int comprimento, int altura, int quantidade) {
        super(comprimento, altura);
        this.tipo = tipo;
        this.quantidade = quantidade;
    }
    public int AreaBlocoDeCaixas(Container c){
        //Quantas Caixas desse tipo Cabem Dentro do Container
        int qtdCaixasContainer = c.calcArea()/this.calcArea();
        //Qual área ocuparia um bloco composto por essas caixas
        return qtdCaixasContainer * this.calcArea();
    }
}
