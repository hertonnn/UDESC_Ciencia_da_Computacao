package negocio;

import java.util.List;

import dados.Contato;
import exceptions.ContatoJaCadastradoException;
import exceptions.ContatoNaoCadastradoException;
import exceptions.ErroNaGravacaoException;
import exceptions.ErroNaLeituraException;
import persistencia.ContatoDAO;

import java.util.Map;

public class ListaTelefonica {

    private ContatoDAO contatoDAO = new ContatoDAO();

    public void adicionaContato(Contato contato)
            throws ContatoJaCadastradoException, ErroNaLeituraException, ErroNaGravacaoException {

        contatoDAO.insert(contato);

    }

    public void removeContato(Contato contato)
            throws ContatoNaoCadastradoException, ErroNaLeituraException, ErroNaGravacaoException {

        contatoDAO.delete(contato);

    }

    public List<Contato> buscarContatos(char letra) throws ErroNaLeituraException {
        return contatoDAO.getAll().get(letra);
    }

    public Map<Character, List<Contato>> buscarContatos() throws ErroNaLeituraException {
        return contatoDAO.getAll();
    }

}