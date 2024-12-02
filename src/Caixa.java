public class Caixa extends Objeto2D{
    public int tipo;
    public int quantidade;
    public Caixa(int tipo,int comprimento, int altura, int quantidade) {
        super(comprimento, altura);
        this.tipo = tipo;
        this.quantidade = quantidade;
    }

}
