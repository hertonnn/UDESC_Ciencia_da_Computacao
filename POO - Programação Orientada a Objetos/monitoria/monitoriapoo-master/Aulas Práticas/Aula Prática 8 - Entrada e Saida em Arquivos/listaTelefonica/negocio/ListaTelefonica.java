package negocio;

import java.util.List;

import dados.Contato;
import persistencia.ContatoDAO;

import java.util.Map;

public class ListaTelefonica {

    private ContatoDAO contatoDAO = new ContatoDAO();

    public void adicionaContato(Contato contato) {

        contatoDAO.adicionarContato(contato);

    }

    public void removeContato(Contato contato) {

        contatoDAO.removerContato(contato);

    }

    public List<Contato> buscarContatos(char letra) {
        return contatoDAO.getAll().get(letra);
    }

    public Map<Character, List<Contato>> buscarContatos() {
        return contatoDAO.getAll();
    }

}