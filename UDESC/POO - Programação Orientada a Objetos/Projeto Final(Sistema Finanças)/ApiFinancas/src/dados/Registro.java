package dados;

public class Registro {

    private Conta conta;
    private Double valor;
    private TipoRegistro tipo;
    private Categoria categoria;
    private Tempo data;
    private String nota;
    
    
    public enum TipoRegistro{
        RECEITA,
        DESPESA
    }

    public Registro(Conta conta, Double valor, TipoRegistro tipo, Categoria categoria, Tempo data, String nota){
        this.tipo = tipo;
        this.categoria = categoria;
        this.valor = valor;
        this.data = data;
        this.conta = conta;
        this.nota = nota;
    }
    public Tempo getTempo() {
        return this.data;
    }
    public Double getValor() {
        return this.valor;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    public Tempo getData() {
        return data;
    }
    public Conta getConta() {
        return conta;
    }
    public TipoRegistro getTipo() {
        return tipo;
    }
}
