package persistencia;

import java.util.LinkedList;
import java.util.List;

import dados.Pessoa;

public class ArquivoPessoaDAO {

    private final String caminho = "files/pessoas.csv";
    private static EditorTexto arquivo = new EditorTexto();

    private String toCSV(Pessoa pessoa) {
        String p = "";

        p += pessoa.getNome() + ",";
        p += pessoa.getIdade() + ",";
        p += pessoa.getAltura() + ",";
        p += pessoa.getMassa();

        return p;
    }

    private Pessoa fromCSV(String linhaCSV) {

        String[] atributos = linhaCSV.split(",");

        Pessoa pessoa = new Pessoa();
        pessoa.setNome(atributos[0]);
        pessoa.setIdade(Integer.parseInt(atributos[1]));
        pessoa.setAltura(Float.parseFloat(atributos[2]));
        pessoa.setMassa(Float.parseFloat(atributos[3]));

        return pessoa;

    }

    private List<String> listaPessoaToString(List<Pessoa> pessoas) {

        List<String> arquivo = new LinkedList<String>();

        for (Pessoa pessoa : pessoas) {

            arquivo.add(toCSV(pessoa));

        }

        return arquivo;

    }

    private List<Pessoa> stringToListaPessoa(List<String> arquivo) {

        List<Pessoa> pessoas = new LinkedList<Pessoa>();

        for (String linha : arquivo) {
            pessoas.add(fromCSV(linha));
        }

        return pessoas;
    }

    public List<Pessoa> lePessoasArquivo() {
        return stringToListaPessoa(arquivo.leTexto(caminho));
    }

    public void salvaPessoasArquivo(List<Pessoa> pessoas) {
        arquivo.gravaTexto(caminho, listaPessoaToString(pessoas));
    }

    public void salvaPessoaArquivo(Pessoa pessoa) {
        arquivo.gravaTexto(caminho, toCSV(pessoa));
    }

}