package dados;

import java.time.LocalDate;

public class Registro {

    private int id;
    private Conta conta;
    private Double valor;
    private Tipo_Registro tipo;
    private Categoria categoria;
    private LocalDate data;
    private String nota;

    public Registro(Conta conta, Double valor, Tipo_Registro tipo, Categoria categoria, LocalDate data, String nota){
        this.tipo = tipo;
        this.categoria = categoria;
        this.valor = valor;
        this.data = data;
        this.conta = conta;
        this.nota = nota;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public String getNota() {
        return nota;
    }
    public void setNota(String nota) {
        this.nota = nota;
    }
    public LocalDate getTempo() {
        return this.data;
    }
    public Double getValor() {
        return this.valor;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    public LocalDate getData() {
        return data;
    }
    public Conta getConta() {
        return conta;
    }
    public Tipo_Registro getTipo() {
        return this.tipo;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public void setConta(Conta conta) {
        this.conta = conta;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public void setTipo(Tipo_Registro tipo) {
        this.tipo = tipo;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }

}
