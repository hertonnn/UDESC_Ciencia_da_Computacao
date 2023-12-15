package persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.List;

import exceptions.ErroNaGravacaoException;
import exceptions.ErroNaLeituraException;

public class EditorTexto {

    public void gravaTexto(String caminho, List<String> dados) throws ErroNaGravacaoException {

        FileWriter arq;
        try {

            arq = new FileWriter(caminho);
            for (int i = 0; i < dados.size(); i++) {
                arq.write(dados.get(i) + "\n");
            }

            arq.close();

        } catch (Exception e) {

            ErroNaGravacaoException erro = new ErroNaGravacaoException();
            erro.setCaminho(caminho);
            throw erro;

        }

    }

    public List<String> leTexto(String caminho) throws ErroNaLeituraException {

        List<String> dados = new LinkedList<String>();

        FileReader arq;
        BufferedReader lerArq;

        try {

            arq = new FileReader(caminho);
            lerArq = new BufferedReader(arq);
            String s = lerArq.readLine();

            while (s != null) {

                dados.add(s);
                s = lerArq.readLine();

            }

            arq.close();

        } catch (Exception e) {

            ErroNaLeituraException erro = new ErroNaLeituraException();
            erro.setCaminho(caminho);
            throw erro;

        }

        return dados;

    }

}