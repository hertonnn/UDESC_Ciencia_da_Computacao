package resources.classes;


import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import dados.Registro;

import javax.swing.table.AbstractTableModel;

public class HistoricoTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private List<Registro> historico = new ArrayList<Registro>();
    private DecimalFormat currencyFormat = (DecimalFormat) DecimalFormat.getCurrencyInstance();
    private int total;
    public void setRegistros(ArrayList<Registro> registros){

        this.historico.clear();
        this.historico.addAll(registros);

        // fireTableDataChanged abaixo é um metodo herdado
        // do AbstractTableModel que avisa a tabela que os
        // dados foram alterados.

        fireTableDataChanged();
    }
    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public int getRowCount() {
        return historico.size();
    }
    @Override
    public Object getValueAt(int linhaIndex, int colunaIndex) {
        Registro registro = historico.get(linhaIndex);

        switch (colunaIndex) {
            case 0:
                return registro.getCategoria().getNome();
            case 1:
                return registro.getData().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")); 
            case 2:
                return registro.getConta().getNome();
            case 3:
                return registro.getNota();
            case 4:
                return registro.getTipo().name();
            case 5:
                String retorno = null;
                Double valor;
                if(registro.getTipo().getTipo().equals("DESPESA")){
                    valor = -1 * registro.getValor();
                }
                else{
                    valor = registro.getValor();
                }
                retorno = currencyFormat.format(valor);
                total += valor;
                return retorno;       
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
                    return "Data";
                case 2:
                    return "Conta";
                case 3:
                    return "Nota";
                case 4:
                    return "Tipo";
                case 5:
                    return "R$ Valor";
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
    public String getTotal() {
        for(Registro registro : historico){
            if(registro.getTipo().getTipo().equals("DESPESA")){
                this.total -= registro.getValor();
                }
            else{
                this.total += registro.getValor();
            }
        }
        return currencyFormat.format(this.total);
    }   
    
}
