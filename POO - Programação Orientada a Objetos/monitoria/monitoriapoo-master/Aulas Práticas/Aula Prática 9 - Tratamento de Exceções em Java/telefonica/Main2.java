import dados.Contato;
import exceptions.ContatoJaCadastradoException;
import exceptions.ErroNaGravacaoException;
import exceptions.ErroNaLeituraException;
import persistencia.ContatoDAO;

public class Main2 {

    public static void main(String[] args) {
        ContatoDAO dao = new ContatoDAO();

        dao.insert(new Contato());

    }
    
}