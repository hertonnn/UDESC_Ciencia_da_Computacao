package resources.classes;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dados.Categoria;

public class CategoriaTableModel extends AbstractTableModel{
    private static final long serialVersionUID = 1L;
    private List<Categoria> categorias = new ArrayList<Categoria>();

    public void setCategorias(ArrayList<Categoria> categorias_lista){

        this.categorias.clear();
        this.categorias.addAll(categorias_lista);

        // fireTableDataChanged abaixo é um metodo herdado
        // do AbstractTableModel que avisa a tabela que os
        // dados foram alterados.

        fireTableDataChanged();
    }
    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public int getRowCount() {
        return categorias.size();
    }
    @Override
    public Object getValueAt(int linhaIndex, int colunaIndex) {
        Categoria categoria = categorias.get(linhaIndex);
        
        switch (colunaIndex) {
            case 0:
                return categoria.getId();
            case 1:
                return categoria.getNome();     
            default:
                throw new IllegalArgumentException();   
        }
    }
    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
                case 0:
                    return "Id";
                case 1:
                    return "Nome";
                default:
                    throw new IllegalArgumentException();
            }
        }
    @Override
    public Class<?> getColumnClass(int columnIndex) {  
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            default:
                throw new IllegalArgumentException();
        }
    }
}
