package apresentacao.telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;

import com.toedter.calendar.JDateChooser;

import dados.Categoria;
import dados.Orcamento;
import dados.Registro;
import dados.Tipo_Registro;
import negocio.Sistema;
import resources.jcustons.JButtonCustom;
import resources.jcustons.JComboBoxCustom;

public class Adicionar extends JDialog{

    Sistema sistema;

    private JPanel divSuperior;
    private JPanel divInferior;
    private JPanel painelAdd;
    private JPanel bloqInferior;


    // Registro
    private JComboBoxCustom<String> conta;
    private JComboBoxCustom<String> categoria;
    private JComboBoxCustom<String> tipoAdd;
    private JFormattedTextField valor;
    private JDateChooser data;
    private JDateChooser data_fim;
    private JTextArea notaAdd;
    private JLabel notaText;
    private JLabel dataText;
    private JLabel dataText_fim;
    private JLabel contaText;
    private JLabel categText;
    private JLabel valorText;
    private JLabel addInvalido;
    private JButtonCustom despesa;
    private JButtonCustom receita;
    public JButtonCustom realizarAdd;
    
    // Categoria 
    
    private JLabel textCategoria;
    private JTextField nomeCategoria;
    
    // Essa tela não é responsiva(se adapta ao tamanho da tela do pc) igual ao Dashboard, ainda.
    int WIDTH = 550;
    int HEIGHT = 620;
    
    Font quickSand=null;
    
    public Adicionar(Sistema sistema){

        this.sistema = sistema;
        setSize(WIDTH,HEIGHT);
        setTitle("Adicionar");
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        try{
        quickSand = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/fontes/quicksand/Quicksand_Book.otf"));
        }
        catch(IOException|FontFormatException e){
          System.out.println(e);
        }
        
    
        criaDivSuperior();
        criaDivInferior();
    }
    public void criaDivSuperior(){
        
        divSuperior = new JPanel();
        divSuperior.setBounds(0, 0, WIDTH, HEIGHT/10);
        divSuperior.setLayout(null);
        divSuperior.setBackground(Color.WHITE);
        divSuperior.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        
        tipoAdd = new JComboBoxCustom<>();
        tipoAdd.setFont(quickSand.deriveFont(Font.BOLD, 18));
        tipoAdd.addItem("Registro");
        tipoAdd.addItem("Orçamento");
        tipoAdd.addItem("Categoria");
        tipoAdd.setBounds(280 + 20, HEIGHT/20 - HEIGHT/40, WIDTH/4, HEIGHT/22);
        
        JLabel tipoAddText = new JLabel("Selecione o tipo de adição:");
        tipoAddText.setFont(quickSand.deriveFont(Font.PLAIN, 20));
        tipoAddText.setBounds(20, HEIGHT/20 - HEIGHT/40, 280, HEIGHT/20);
        
        
        divSuperior.add(tipoAddText);
        divSuperior.add(tipoAdd);
        add(divSuperior);
        
    }
    public void criaDivInferior(){
        // painel para bloquear parte da div, caso adição de categoria e outros
        bloqInferior = new JPanel();
        bloqInferior.setBackground(new Color(0,0,0,95));
        bloqInferior.setBounds(0, HEIGHT/10, WIDTH, HEIGHT/2 - HEIGHT/10 );
        
        divInferior = new JPanel();
        divInferior.setBounds(0, HEIGHT/10, WIDTH, HEIGHT);
        divInferior.setBackground(Color.WHITE);
        divInferior.setLayout(null); 
        
        // Um painel para inf especificas
        painelAdd = new JPanel();
        painelAdd.setBounds(0,0,WIDTH, HEIGHT/2 - HEIGHT/10);
        painelAdd.setBackground(Color.decode("#0CC0DF"));
        painelAdd.setLayout(null);
        
        //-- Elementos que vão nesse painel --
        addInvalido = new JLabel("", SwingConstants.LEFT);

        // selecionar conta
        contaText = new JLabel("Conta");
        contaText.setFont(quickSand.deriveFont(Font.BOLD, 15));
        contaText.setBounds(WIDTH/2 - WIDTH/4 - 10, HEIGHT/6, 100, 20);

        conta = new JComboBoxCustom<>();
        conta.addItem(sistema.getUsuarioLogado().getConta().getNome());
        conta.setFont(quickSand.deriveFont(Font.BOLD, 15));
        conta.setBounds(WIDTH/2 - WIDTH/4 - 10, HEIGHT/6+20, WIDTH/4, 25);

        // selecionar categoria
        categText = new JLabel("Categoria");
        categText.setFont(quickSand.deriveFont(Font.BOLD, 15));
        categText.setBounds(WIDTH/2 + 10, HEIGHT/6, 100, 20);

        categoria = new JComboBoxCustom<>();
        categoria.setFont(quickSand.deriveFont(Font.BOLD, 15));
        categoria.setBounds(WIDTH/2 + 10, HEIGHT/6+20, WIDTH/4, 25);
        
        for(Categoria categoriaItem : sistema.getUsuarioLogado().getConta().getCategorias()){
            categoria.addItem(categoriaItem.getNome());
        }
        
        // valor
        valorText = new JLabel("Valor + ");
        valorText.setFont(quickSand.deriveFont(Font.BOLD, 15));
        valorText.setBounds(WIDTH/2 - 90, HEIGHT/4+10, 110, 30);
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();

        NumberFormatter formatter = new NumberFormatter(currencyFormat);
        formatter.setValueClass(Double.class);
        formatter.setMinimum(0.0);
        formatter.setMaximum(Double.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);

        valor = new JFormattedTextField(formatter);
        valor.setValue(0.0); // Define um valor inicial
        valor.setFont(quickSand.deriveFont(Font.BOLD, 15));
        valor.setBounds(WIDTH/3 + 60, HEIGHT/4+11, 85, 25);
        valor.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        valor.setHorizontalAlignment(SwingConstants.CENTER);
        
        // selecionar receita ou despesa
        
        receita = new JButtonCustom();
        receita.setText("RECEITA");
        receita.setFont(quickSand.deriveFont(Font.BOLD, 15));
        receita.setBounds(WIDTH/2 - WIDTH/6, HEIGHT/12, WIDTH/6, 25);
        receita.setBackground(Color.WHITE);
        receita.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        despesa = new JButtonCustom();
        despesa.setText("DESPESA");
        despesa.setFont(quickSand.deriveFont(Font.BOLD, 15));
        despesa.setBounds(WIDTH/2, HEIGHT/12, WIDTH/6, 25);
        despesa.setBackground(null);
        despesa.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        
        
        // Selecionar data
        
        dataText = new JLabel("Selecione a data");
        dataText.setFont(quickSand.deriveFont(Font.BOLD, 13));
        dataText.setBounds(WIDTH/5, HEIGHT/2-25,  110, 25);

        data = new JDateChooser();
        data.setBounds(WIDTH/5, HEIGHT/2, 120, 20);
        data.setFont(quickSand.deriveFont(Font.PLAIN, 15));
        data.setDateFormatString("dd/MM/yyyy");

        dataText_fim = new JLabel("Fim");
        dataText_fim.setFont(quickSand.deriveFont(Font.BOLD, 13));
        dataText_fim.setBounds(WIDTH-220, HEIGHT/2-25,  110, 25);

        data_fim = new JDateChooser();
        data_fim.setBounds(WIDTH-220, HEIGHT/2, 120, 20);
        data_fim.setFont(quickSand.deriveFont(Font.PLAIN, 15));
        data_fim.setDateFormatString("dd/MM/yyyy");
        //data.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        
        // notaAdd da adição
        
        notaText = new JLabel("Nota");
        notaText.setFont(quickSand.deriveFont(Font.BOLD, 13));
        notaText.setBounds(WIDTH/2 - 120, HEIGHT*4/7-10, 250, 20);
        notaAdd = new JTextArea();
        notaAdd.setBounds(WIDTH/2 - 120, HEIGHT*4/7+10, 240, 70);
        notaAdd.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        
        // botao de adicionar 
        
        realizarAdd = new JButtonCustom();
        realizarAdd.setText("Realizar adição");
        realizarAdd.setFont(quickSand.deriveFont(Font.PLAIN, 17));
        realizarAdd.setBackground(Color.decode("#00BF63"));
        realizarAdd.setBorder(null);
        realizarAdd.setForeground(Color.WHITE);
        realizarAdd.setBounds(WIDTH/2 - 350/2, HEIGHT*3/4, 350, 30);

        
        // adicionando os elementos em seus respectivos paineis 
        painelAdd.add(receita);
        painelAdd.add(despesa);
        painelAdd.add(contaText);
        painelAdd.add(conta);
        painelAdd.add(categText);
        painelAdd.add(categoria);
        painelAdd.add(valorText);
        painelAdd.add(valor);
        divInferior.add(dataText);
        divInferior.add(data);
        divInferior.add(notaAdd);
        divInferior.add(notaText);
        divInferior.add(realizarAdd);
        divInferior.add(painelAdd);
        add(divInferior);

        // itens de adição de categoria, ocultos inicialmente.

        textCategoria = new JLabel("Digite o nome da nova categoria");
        textCategoria.setFont(quickSand.deriveFont(Font.BOLD, 18));
        textCategoria.setBounds(WIDTH/2 - 150, HEIGHT/2, 300, 20);

        nomeCategoria = new JTextField();
        nomeCategoria.setBounds(WIDTH/2 - 150, HEIGHT/2 + 20, 300, 35);
        nomeCategoria.setFont(quickSand.deriveFont(Font.BOLD, 20));

        
    }

    public void modoRegistro(boolean ativo){
        if(!ativo){
            add(bloqInferior,0);
            repaint();
            //realizarAdd.setEnabled(false);
            conta.setEnabled(false);
            categoria.setEnabled(false);
            valor.setEnabled(false);
            valor.setBackground(Color.gray);
            divInferior.remove(data); 
            divInferior.remove(notaAdd);
            divInferior.remove(notaText);
            divInferior.remove(dataText);
            despesa.setEnabled(false);
            receita.setEnabled(false);
        }
        else{
            remove(bloqInferior);
            repaint();
            //realizarAdd.setEnabled(true);
            conta.setEnabled(true);
            categoria.setEnabled(true);
            tipoAdd.setEnabled(true);
            valor.setEnabled(true);
            valor.setBackground(Color.WHITE);
            divInferior.add(data); 
            divInferior.add(notaAdd);
            divInferior.add(notaText);
            divInferior.add(dataText);
            despesa.setEnabled(true);
            receita.setEnabled(true);
        }
    }
    public void modoCategoria(boolean ativo){

        if(ativo){
            divInferior.add(textCategoria);
            divInferior.add(nomeCategoria);
        }
        else{
            divInferior.remove(textCategoria);
            divInferior.remove(nomeCategoria);
        }
    }
    public void modoOrcamento(boolean ativo){

        if(ativo){
            dataText.setText("Início");
            divInferior.add(dataText_fim);
            divInferior.add(data_fim);
        }
        else{
            eReceita(ativo);
            dataText.setText("Data");
            divInferior.remove(data_fim);
            divInferior.remove(dataText_fim);
        }
        painelAdd.repaint();
    }
    public boolean addCategoria(){
        // Atos criminosos foram feitos aqui.
        if(estaVazio(nomeCategoria)){
            invalido(false, true);
            return false;

        }else if(!tipoAdd.getSelectedItem().equals("Categoria")){
            return false;
        }
        else{
            invalido(false, false);   
            
            Categoria categoriaNova = new Categoria(nomeCategoria.getText());
            if(sistema.adicionaCategoria(categoriaNova, sistema.getUsuarioLogado())){
                sistema.getUsuarioLogado().getConta().addCategoria(categoriaNova);
                return true;
            }
            return false;
        }
    }
    public boolean addRegistro(){
        if(estaVazio(valor) || estaVazio(data) || estaVazio(notaAdd)){
            invalido(false, true);
            return false;
        }
        else if(!tipoAdd.getSelectedItem().equals("Registro")){
            return false;
        }
        else{
            Number value = (Number) valor.getValue();
            Double valorRegistro = value.doubleValue();

            String nota = notaAdd.getText();
            Categoria categoriaRegistro = null;

            // isso é feio demais. Preciso mudar.
            for(Categoria item : sistema.getUsuarioLogado().getConta().getCategorias()){
                if(item.getNome().equals(categoria.getSelectedItem().toString())){
                    categoriaRegistro = item;
                }
            }
            Registro registro;
            

            java.util.Date pega = data.getDate();
            SimpleDateFormat formatoDia = new SimpleDateFormat("dd");
            SimpleDateFormat formatoMes = new SimpleDateFormat("MM");
            SimpleDateFormat formatoAno = new SimpleDateFormat("yyyy");

            int dia = Integer.parseInt((formatoDia.format(pega)));
            int mes = Integer.parseInt((formatoMes.format(pega)));
            int ano = Integer.parseInt((formatoAno.format(pega)));

            LocalDate date_LocalDate = LocalDate.of(ano, mes, dia);


            if(receita.getBackground().equals(Color.WHITE)){
                registro = new Registro(sistema.getUsuarioLogado().getConta(), valorRegistro,Tipo_Registro.RECEITA, categoriaRegistro, date_LocalDate, nota);
            }
            else{
                registro = new Registro(sistema.getUsuarioLogado().getConta(), valorRegistro,Tipo_Registro.DESPESA, categoriaRegistro, date_LocalDate, nota);
            }

            if(sistema.realizaRegistro(registro, sistema.getUsuarioLogado(), sistema.getUsuarioLogado().getConta())){
                return true;
            }
            else{
                // saldo insuficiente
                invalido(true, false);
                return false;
            }

        }
    }
    public boolean addOrcamento(){
        if(estaVazio(valor) || estaVazio(data) || estaVazio(notaAdd)){
            invalido(false, true);
            return false;
        }else if(!tipoAdd.getSelectedItem().equals("Orçamento")){
            return false;
        }
        else{
            invalido(false, false);
            Number value = (Number) valor.getValue();
            Double limite = value.doubleValue();

            String nota = notaAdd.getText();
            Categoria categoriaOrcamento = null;

            // isso é feio demais. Preciso mudar.
            for(Categoria item : sistema.getUsuarioLogado().getConta().getCategorias()){
                if(item.getNome().equals(categoria.getSelectedItem().toString())){
                    categoriaOrcamento = item;
                }
            }
            
            java.util.Date pega = data.getDate();
            SimpleDateFormat formatoDia = new SimpleDateFormat("dd");
            SimpleDateFormat formatoMes = new SimpleDateFormat("MM");
            SimpleDateFormat formatoAno = new SimpleDateFormat("yyyy");

            int dia = Integer.parseInt((formatoDia.format(pega)));
            int mes = Integer.parseInt((formatoMes.format(pega)));
            int ano = Integer.parseInt((formatoAno.format(pega)));

            java.util.Date pega_data_fim = data_fim.getDate();

            int dia_fim = Integer.parseInt((formatoDia.format(pega_data_fim)));
            int mes_fim = Integer.parseInt((formatoMes.format(pega_data_fim)));
            int ano_fim = Integer.parseInt((formatoAno.format(pega_data_fim)));

            LocalDate date_LocalDate = LocalDate.of(ano, mes, dia);

            LocalDate date_LocalDate_fim = LocalDate.of(ano_fim, mes_fim, dia_fim);

            Orcamento orcamento = null;
            if(receita.getBackground().equals(Color.WHITE)){
                orcamento = new Orcamento( sistema.getUsuarioLogado(), Tipo_Registro.RECEITA, limite, categoriaOrcamento, LocalDate.now(), date_LocalDate, date_LocalDate_fim, nota);
            }
            else{
                orcamento = new Orcamento(sistema.getUsuarioLogado(), Tipo_Registro.DESPESA, limite, categoriaOrcamento, LocalDate.now(), date_LocalDate, date_LocalDate_fim, nota);
            }
            
            if(sistema.realizaOrcamento(orcamento, sistema.getUsuarioLogado().getConta())){
                return true;
            }
            else{
                invalido(false, true);
                return false;
            }
        }
    } 
    public void fecharAdd(){
        resetaAdd();
        dispose();
    }
    public void abreAdd(){
        setVisible(true);
    }
    public void resetaAdd(){
        valor.setValue(0.0);;
        notaAdd.setText("");
        invalido(false, false);
        data.setDate(null);
        nomeCategoria.setText("");
        //tipoAdd.setSelectedIndex(0);
        
        categoria.removeAllItems();
        for(Categoria categoriaItem : sistema.getUsuarioLogado().getConta().getCategorias()){
            categoria.addItem(categoriaItem.getNome());
        }
    }
    public void invalido(boolean saldoInsuficiente, boolean campoVazio){
        String msg;
        addInvalido.setText("");
        addInvalido.setBounds(WIDTH/5, HEIGHT/2 - 60, WIDTH, 30);
        addInvalido.setFont(quickSand.deriveFont(Font.PLAIN, 14));
        addInvalido.setForeground(Color.RED);

        if(saldoInsuficiente){
            msg = "*Saldo insuficiente na conta " + sistema.getUsuarioLogado().getConta().getNome();
            addInvalido.setText(msg);
            divInferior.add(addInvalido);
        }else if(campoVazio){
            msg = "*Todos os campos são obrigatórios";
            addInvalido.setText(msg);
            divInferior.add(addInvalido);
        }
        else{
            addInvalido.setText("");
        }
        //divInferior.repaint();
        //bloqInferior.repaint();
        //painelAdd.repaint();
    }
    public void eReceita(boolean ereceita){ 
        if(ereceita){   
            valorText.setText("Valor + ");
            receita.setBackground(Color.WHITE);
            despesa.setBackground(null);
        }
        else{
            valorText.setText("Valor - ");
            despesa.setBackground(Color.WHITE);
            receita.setBackground(null);
        }
        painelAdd.repaint();
    }
    public JButtonCustom getRealizarAdd() {
        return realizarAdd;
    }
    public JComboBoxCustom<String> getTipoAdd() {
        return tipoAdd;
    }
    public JButtonCustom getDespesa() {
        return despesa;
    }
    public JButtonCustom getReceita() {
        return receita;
    }
    public JLabel getValorText() {
        return valorText;
    }

    // olha o polimorfismo aqui
    public boolean estaVazio(JTextField campo) {
        return campo.getText() == null || campo.getText().trim().isEmpty();
    }
    public boolean estaVazio(JFormattedTextField campo) {
        return campo.getText() == null || campo.getText().trim().isEmpty();
    }
    public boolean estaVazio(JTextArea campo) {
        return campo.getText() == null || campo.getText().trim().isEmpty();
    }
    public boolean estaVazio(JDateChooser campo) {
        return campo.getDate() == null;
    }
}
