package jtable;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ExemploJTable extends JScrollPane {

    public ExemploJTable() {
        Tabela cidades = new Tabela();
        JTable table = new JTable(cidades);
        setViewportView(table);
        cidades.adicionarCidade(new Cidade("Joinville", "Santa Catarina"));
        cidades.adicionarCidade(new Cidade("Curitiba", "Parana"));
        cidades.adicionarCidade(new Cidade("Florianopolis", "Santa Catarina"));
    }

}
