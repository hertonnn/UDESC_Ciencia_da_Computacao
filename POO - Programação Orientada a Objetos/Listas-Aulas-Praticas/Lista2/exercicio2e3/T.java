package exercicio2e3;

public class T implements Comparable<T> {
    private int numeroPosicao;

    public T(int numeroPosicao){
        this.numeroPosicao = numeroPosicao;
    }

    public int getNumeroPosicao() {
        return numeroPosicao;
    }
    public void setNumeroPosicao(int numeroPosicao) {
        this.numeroPosicao = numeroPosicao;
    }
    @Override
    public int compareTo(T outro){
        if(this.numeroPosicao < outro.numeroPosicao){
            return -1;
        }
        if(this.numeroPosicao > outro.numeroPosicao){
            return 1;
        }
        else{
            return 0;
        }
    }

}
