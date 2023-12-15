package persistencia;

import java.util.LinkedList;
import java.util.List;

import dados.Contato;

public class ArquivoContatoDAO {

    private EditorTexto arquivo = new EditorTexto();
    private final String caminho = "files/contatos.csv";

    private String toCSV(Contato contato) {
        return contato.getNome() + "," + contato.getTelefone();
    }

    private Contato fromCSV(String linha) {

        String[] atributos = linha.split(",");

        Contato contato = new Contato();
        contato.setNome(atributos[0]);
        contato.setTelefone(Integer.parseInt(atributos[1]));

        return contato;
    }

    public List<Contato> lerContatos() {

        List<Contato> contatos = new LinkedList<Contato>();

        for (String linha : arquivo.leTexto(caminho)) {

            contatos.add(fromCSV(linha));

        }

        return contatos;

    }

    public void salvarContatos(List<Contato> contatos) {

        List<String> linhas = new LinkedList<String>();

        for (Contato contato : contatos) {

            linhas.add(toCSV(contato));

        }

        arquivo.gravaTexto(caminho, linhas);

    }

    public void salvarContato(Contato contato) {
        arquivo.gravaTexto(caminho, toCSV(contato));
    }

}