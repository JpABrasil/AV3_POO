public class Objeto2D {
    public int comprimento;
    public int altura;
    public Objeto2D(int comprimento, int altura) {
        this.comprimento = comprimento;
        this.altura = altura;
    }
    public int calcArea(){
        return (this.altura * this.comprimento);
    }

}
