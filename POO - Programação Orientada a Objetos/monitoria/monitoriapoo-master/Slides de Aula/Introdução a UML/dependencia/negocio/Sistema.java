package negocio;

import java.util.LinkedList;

import dados.Pessoa;

public class Sistema {

    private LinkedList<Pessoa> pessoas = new LinkedList<Pessoa>();

    public void cadastrarPessoa(Pessoa p) {
        this.pessoas.add(p);
    }

    public LinkedList<Pessoa> mostrarPessoas() {
        return this.pessoas;
    }

}