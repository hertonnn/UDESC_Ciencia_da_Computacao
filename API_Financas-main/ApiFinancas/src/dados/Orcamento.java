package dados;

import java.time.LocalDate;

public class Orcamento{
    private int id;
    private Usuario usuario;
    private double limite;
    private Categoria categoria;
    private LocalDate data_criacao;
    private LocalDate data_inicio;
    private LocalDate data_fim;
    private Tipo_Registro tipo;
    private String nota;

    public Orcamento(Usuario usuario , Tipo_Registro tipo, double valorLimite, Categoria categoria, LocalDate data_criacao,  LocalDate data_inicio, LocalDate data_fim, String nota){
        this.limite = valorLimite;
        this.categoria = categoria;
        this.data_criacao = data_criacao;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
        this.tipo = tipo;
        this.usuario = usuario;
        this.nota = nota;
    }

    public double getLimite() {
        return limite;
    }
    public void setLimite(double limite) {
        this.limite = limite;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    public LocalDate getData_criacao() {
        return data_criacao;
    }
    public LocalDate getData_inicio() {
        return data_inicio;
    }
    public LocalDate getData_fim() {
        return data_fim;
    }
    public Tipo_Registro getTipo() {
        return tipo;
    }
    public String getNota() {
        return nota;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
