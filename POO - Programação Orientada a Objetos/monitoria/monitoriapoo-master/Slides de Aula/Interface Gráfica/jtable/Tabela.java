package jtable;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class Tabela extends AbstractTableModel {

    private List<Cidade> cidades = new LinkedList<Cidade>();

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public int getRowCount() {
        return cidades.size();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                return cidades.get(linha).getNome();
            case 1:
                return cidades.get(linha).getEstado();
            default:
                throw new IllegalArgumentException();
        }
    }

    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Nome da Cidade";
            case 1:
                return "Estado";
            default:
                throw new IllegalArgumentException();
        }
    }

    public void adicionarCidade(Cidade cidade) {
        cidades.add(cidade);
        this.fireTableStructureChanged();
    }

}