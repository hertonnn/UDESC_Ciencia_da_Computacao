package negocio;

import java.util.List;
import java.util.LinkedList;

import dados.Contato;

import java.util.Map;
import java.util.HashMap;

public class ListaTelefonica {

    private Map<Character, List<Contato>> contt = new HashMap<Character, List<Contato>>();

    public ListaTelefonica() {

        for (char i = 65; i < 91; i++) {

            List<Contato> lista = new LinkedList<Contato>();
            contt.put(i, lista);

        }

    }
    
    public void removeContato(Contato contato) {

        String nome = contato.getNome().toUpperCase();

        contt.get(nome.charAt(0)).remove(contato);

    }
    
    public void addContato(Contato contato) {

        String nome = contato.getNome().toUpperCase();

        contt.get(nome.charAt(0)).add(contato);

    }
    public List<Contato> getContatos(char letra) {
        return contt.get(letra);
    }

    public Map<Character, List<Contato>> getContatos() {
        return contt;
    }
    

}