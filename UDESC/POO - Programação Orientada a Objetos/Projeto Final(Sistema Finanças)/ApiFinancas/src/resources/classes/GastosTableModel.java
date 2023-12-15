package resources.classes;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import dados.Categoria;
import dados.Registro;

import javax.swing.table.AbstractTableModel;

public class GastosTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private List<Registro> historico = new ArrayList<Registro>();
    private List<Categoria> categorias = new ArrayList<Categoria>();

    public void setRegistros(ArrayList<Registro> registros, ArrayList<Categoria> categorias){

        this.historico.clear();
        this.categorias.clear();
        this.historico.addAll(registros);
        this.categorias.addAll(categorias);

        // fireTableDataChanged abaixo é um metodo herdado
        // do AbstractTableModel que avisa a tabela que os
        // dados foram alterados.

        fireTableDataChanged();
    }
    @Override
    public int getColumnCount() {
        return categorias.size();
    }

    @Override
    public int getRowCount() {
        return 12;
    }
    @Override
    public Object getValueAt(int linhaIndex, int colunaIndex) {
        Registro registro = historico.get(linhaIndex);
        DecimalFormat currencyFormat = (DecimalFormat) DecimalFormat.getCurrencyInstance();

        switch (colunaIndex) {
            case 0:
                return registro.getCategoria().getNome();
            case 1:
                return registro.getConta().getNome();
            case 2:

                String retorno = null;
                if(registro.getTipo().getTipo().equals("DESPESA")){
                    retorno = "R$ - " + currencyFormat.format(registro.getValor()) ;
                }
                else{
                    retorno = "R$ + " + currencyFormat.format(registro.getValor());
                }
                return retorno;
            case 3:
                return registro.getNota();
            case 4:
                return registro.getTipo().name();
            case 5:
                return registro.getData().format(DateTimeFormatter.ofPattern("dd/MM/YYYY"));        
            default:
                throw new IllegalArgumentException();   
        }
    }
    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
                case 0:
                    return "Categoria";
                case 1:
                    return "Conta";
                case 2:
                    return "R$ Valor";
                case 3:
                    return "Nota";
                case 4:
                    return "Tipo";
                case 5:
                    return "Data";
                default:
                    throw new IllegalArgumentException();
            }
        }
    @Override
    public Class<?> getColumnClass(int columnIndex) {  
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return String.class;
            case 5:
                return String.class;
            default:
                throw new IllegalArgumentException();
        }
    }
        
    
}
