/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;


public class Produto {

    private long codigo;
    private String nome;
    private String especificacoes;
    private float precoCusto;
    private float precoVenda;
    private boolean habilitadoVendas;   

    public Produto(long codigo, String nome, String especificacoes, float precoCusto, float precoVenda, boolean habilitadoVendas) {
        this.codigo = codigo;
        this.nome = nome;
        this.especificacoes = especificacoes;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.habilitadoVendas = habilitadoVendas;
    }

    public Produto() {
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecificacoes() {
        return especificacoes;
    }

    public void setEspecificacoes(String especificacoes) {
        this.especificacoes = especificacoes;
    }

    public float getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(float precoCusto) {
        this.precoCusto = precoCusto;
    }

    public float getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(float precoVenda) {
        this.precoVenda = precoVenda;
    }

    public boolean isHabilitadoVendas() {
        return habilitadoVendas;
    }

    public void setHabilitadoVendas(boolean habilitadoVendas) {
        this.habilitadoVendas = habilitadoVendas;
    }       
}
