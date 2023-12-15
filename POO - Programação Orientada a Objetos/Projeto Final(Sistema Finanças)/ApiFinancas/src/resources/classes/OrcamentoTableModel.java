package resources.classes;


import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import dados.Orcamento;

import javax.swing.table.AbstractTableModel;

public class OrcamentoTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private List<Orcamento> orcamentos = new ArrayList<Orcamento>();
    private DecimalFormat currencyFormat = (DecimalFormat) DecimalFormat.getCurrencyInstance();
    private int total_despesa;
    private int total_receira;

    public void setOrcamentos(ArrayList<Orcamento> orcamentos_lista){

        this.orcamentos.clear();
        this.orcamentos.addAll(orcamentos_lista);

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
        return orcamentos.size();
    }
    @Override
    public Object getValueAt(int linhaIndex, int colunaIndex) {
        Orcamento orcamento = orcamentos.get(linhaIndex);

        switch (colunaIndex) {
            case 0:
                return orcamento.getCategoria().getNome();
            case 1:
                return orcamento.getData_inicio().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")); 
            case 2:
                return orcamento.getData_fim().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")); 
            case 3:
                return orcamento.getNota();
            case 4:
                return orcamento.getTipo().name();
            case 5:
                String retorno = null;
                Double valor;
                if(orcamento.getTipo().getTipo().equals("DESPESA")){
                    valor = -1 * orcamento.getLimite();
                }
                else{
                    valor = orcamento.getLimite();
                }
                retorno = currencyFormat.format(valor);
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
                    return "Data Início";
                case 2:
                    return "Data Fim";
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
    public void calcula_total() {
        for(Orcamento orcamento : orcamentos){
            if(orcamento.getTipo().getTipo().equals("DESPESA")){
                this.total_despesa -= orcamento.getLimite();
                }
            else{
                this.total_receira += orcamento.getLimite();
            }
        }
    }
    public int getTotal_despesa() {
        return total_despesa;
    }
    public int getTotal_receira() {
        return total_receira;
    }

    
}
