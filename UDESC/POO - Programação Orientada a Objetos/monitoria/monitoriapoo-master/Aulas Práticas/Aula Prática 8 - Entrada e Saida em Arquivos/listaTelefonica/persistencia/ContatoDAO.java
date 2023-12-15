package persistencia;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import dados.Contato;

public class ContatoDAO {

    private ArquivoContatoDAO arquivoContatoDAO = new ArquivoContatoDAO();

    public void adicionarContato(Contato contato) {
        arquivoContatoDAO.salvarContato(contato);
    }

    public void removerContato(Contato contato) {
        List<Contato> contatos = arquivoContatoDAO.lerContatos();
        contatos.remove(contato);
        arquivoContatoDAO.salvarContatos(contatos);
    }

    public Map<Character, List<Contato>> getAll() {

        Map<Character, List<Contato>> contatos = new HashMap<Character, List<Contato>>();

        for (char i = 65; i < 91; i++) {

            List<Contato> lista = new LinkedList<Contato>();
            contatos.put(i, lista);

        }

        for (Contato contato : arquivoContatoDAO.lerContatos()) {

            char inicial = contato.getNome().toUpperCase().charAt(0);

            contatos.get(inicial).add(contato);

        }

        return contatos;
    }
}