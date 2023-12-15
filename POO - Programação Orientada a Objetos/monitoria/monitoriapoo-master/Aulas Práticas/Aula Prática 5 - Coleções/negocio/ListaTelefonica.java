package negocio;

import java.util.List;
import java.util.LinkedList;

import dados.Contato;

import java.util.Map;
import java.util.HashMap;

public class ListaTelefonica {

    private Map<Character, List<Contato>> contatos = new HashMap<Character, List<Contato>>();

    public ListaTelefonica() {

        for (char i = 65; i < 91; i++) {

            List<Contato> lista = new LinkedList<Contato>();
            contatos.put(i, lista);

        }

    }

    public void adicionaContato(Contato contato) {

        String nome = contato.getNome().toUpperCase();

        contatos.get(nome.charAt(0)).add(contato);

    }

    public void removeContato(Contato contato) {

        String nome = contato.getNome().toUpperCase();

        contatos.get(nome.charAt(0)).remove(contato);

    }

    public List<Contato> buscarContatos(char letra) {
        return contatos.get(letra);
    }

    public Map<Character, List<Contato>> buscarContatos() {
        return contatos;
    }

}