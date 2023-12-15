package dados;

import java.util.ArrayList;

public class Conta {
    private int id;
    private String nome;
    private double saldo;
    private ArrayList<Registro> historico;
    private ArrayList<Orcamento> orcamentos;
    private ArrayList<Categoria> categorias;

    public Conta(String nome, double saldo){
        this.nome = nome;
        this.saldo = saldo;
        historico = new ArrayList<>();
        orcamentos = new ArrayList<>();
        categorias = new ArrayList<>();
    }
    // lógica de registro, RECEITA OU DESPESA
    public void deposita(double valor) {
        this.saldo += valor;
    }
    public boolean saca(double valor) {
        if(this.saldo < valor){
            return false;
        }
        else{
            this.saldo -= valor;
            return true;
        }
    }
    // getters e setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public ArrayList<Categoria> getCategorias() {
        return categorias;
    }
    public ArrayList<Registro> getHistorico() {
        return historico;
    }
    public ArrayList<Orcamento> getOrcamentos() {
        return orcamentos;
    }
    public void setCategorias(ArrayList<Categoria> categorias) {
        this.categorias = categorias;
    }
    public void setHistorico(ArrayList<Registro> historico) {
        this.historico = historico;
    }
    public void setOrcamentos(ArrayList<Orcamento> orcamentos) {
        this.orcamentos = orcamentos;
    }
    public double getSaldo() {
        return saldo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    // adicionar apenas um item
    public void addCategoria(Categoria categoria) {
        this.categorias.add(categoria);
    }
    public void addHistorico(Registro historico) {
        this.historico.add(historico);
    }
    public void addOrcamento(Orcamento orcamentos) {
        this.orcamentos.add(orcamentos);
    }
}
