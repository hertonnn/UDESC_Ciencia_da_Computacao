package dados;

public class Orcamento{
    private double limite;
    private boolean limiteAtingido;
    private Categoria categoria;
    private Tempo periodo;
    private String nota;

    public Orcamento(double valorLimite, Categoria categoria, Tempo periodo, String nota){
        this.limite = valorLimite;
        this.categoria = categoria;
        this.periodo = periodo;
        this.nota = nota;
        this.limiteAtingido = false;
    }

    public double getLimite() {
        return limite;
    }
    public void setLimite(double limite) {
        this.limite = limite;
    }
    public void setLimiteAtingido(boolean limiteAtingido) {
        this.limiteAtingido = limiteAtingido;
    }
}
