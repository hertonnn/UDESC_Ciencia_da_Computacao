package dados;

public class Registro {

    protected Conta conta;
    protected Double valor;
    protected TipoRegistro tipo;
    protected Categoria categoria;
    protected Tempo data;
    protected String nota;
    
    
    public enum TipoRegistro{
        RECEITA,
        DESPESA
    }
    // Implementando padrão builder 
    // Referência https://www.youtube.com/watch?v=rM0oDoeXgzU

    private Registro(Conta conta, Double valor, TipoRegistro tipo, Categoria categoria, Tempo data, String nota){}

    public static class RegistroBuilder{
         
        protected TipoRegistro tipo;
        protected Categoria categoria;
        protected Double valor;
        protected Tempo data;
        protected Conta conta;
        protected String nota;

        public RegistroBuilder(){ // Todos os atributos colocados aqui ficam obrigatórios, ou seja, null não é aceito !!

        }

        public RegistroBuilder tipo(TipoRegistro tipo){
            this.tipo = tipo;
            return this;
        }
        public RegistroBuilder categoria(Categoria categoria){
            this.categoria = categoria;
            return this;
        }
        public RegistroBuilder valor(Double valor){
            this.valor = valor;
            return this;
        }
        public RegistroBuilder data(Tempo data){
            this.data = data;
            return this;
        }
        public RegistroBuilder conta(Conta conta){
            this.conta = conta;
            return this;
        }
        public RegistroBuilder nota(String nota){
            this.nota = nota;
            return this;
        }
        public Registro criaRegistro(){
            return new Registro(conta, valor, tipo, categoria, data, nota);
        }

        /*
        - Ao instanciar Registro tenho algo como:
        Registro registro = new Registro.RegistroBuilder()
        .conta(null)
        .valor(null)
        .tipo(null)
        .categoria(null)
        .data(null)
        .nota(null)
        .criaRegistro();
        */

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
