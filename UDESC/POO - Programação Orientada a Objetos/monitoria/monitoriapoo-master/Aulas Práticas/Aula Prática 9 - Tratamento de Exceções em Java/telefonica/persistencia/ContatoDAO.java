package persistencia;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import dados.Contato;
import exceptions.ContatoJaCadastradoException;
import exceptions.ContatoNaoCadastradoException;
import exceptions.ErroNaGravacaoException;
import exceptions.ErroNaLeituraException;

public class ContatoDAO {

    private ArquivoContatoDAO arquivoContatoDAO = new ArquivoContatoDAO();

    public void insert(Contato contato)
            throws ContatoJaCadastradoException, ErroNaLeituraException, ErroNaGravacaoException {
        List<Contato> contatos = arquivoContatoDAO.lerContatos();
        if (!contatos.contains(contato)) {
            contatos.add(contato);
            arquivoContatoDAO.salvarContatos(contatos);
        } else {
            throw new ContatoJaCadastradoException();
        }
    }

    public void delete(Contato contato)
            throws ContatoNaoCadastradoException, ErroNaLeituraException, ErroNaGravacaoException {
        List<Contato> contatos = arquivoContatoDAO.lerContatos();
        if (contatos.contains(contato)) {
            contatos.remove(contato);
            arquivoContatoDAO.salvarContatos(contatos);
        } else {
            throw new ContatoNaoCadastradoException();
        }
    }

    public Map<Character, List<Contato>> getAll() throws ErroNaLeituraException {

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