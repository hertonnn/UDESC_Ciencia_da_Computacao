package apresentacao.telas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.TableColumn;
import javax.swing.text.NumberFormatter;

import dados.Conta;
import dados.Orcamento;
import dados.Registro;
import recursos.classes.HistoricoTableModel;
import recursos.classes.OrcamentoTableModel;
import recursos.classes.RendererTabela;
import recursos.graficos.GraficoLinha2;
import recursos.jcustons.JButtonCustom;
import recursos.jcustons.JComboBoxCustom;
import recursos.jcustons.JPanelCustom;

public class Historico extends JPanel{

    Conta conta;

    Locale localBrasil = new Locale("pt", "BR");

    private GraficoLinha2 graficoHistorico;

    private JButtonCustom lixeira_registros;
    private JPanelCustom painel_conta;
    private JButtonCustom lixeira_orcamentos;
    private JButtonCustom editar;
    private JButtonCustom perfil;
    private JButtonCustom salvar;
    private JPanelCustom fundo_saldo;
    private JButtonCustom olho;
    private JTextField novo_nome_conta;
    private JFormattedTextField novo_saldo_conta;

    private JCheckBox selecionar_registros;
    private JCheckBox selecionar_orcamentos;
    private JComboBoxCustom<String> contas;
    private JTable tabelaHistorico;
    private JTable tabelaOrcamentos;
    private OrcamentoTableModel orcamentoTableModel;
    private HistoricoTableModel historicoTableModel;

    JPanel painel_Sup;
    JPanel painel_Inf;

    JLabel total_despesa;
    JLabel graficoConta_text;
    JLabel edite_text;
    JLabel conta_invalida;
    JLabel novo_nome_text;
    JLabel novo_saldo_text;
    JLabel contas_text;
    JLabel total_receita;
    JLabel total;
    JLabel saldo;
    JLabel conta_nome;
    JLabel textoSup;
    JLabel orcamentos_text;
    JLabel historico_text;
    JLabel conta_saldo;
    
    String azulClaro = "#0CC0DF";
    int TAMLARGURA;
    int TAMALTURA;
    int TAMICONES;
    int MARGEM;
    int TAMFONTE;

    Font quickSand = null;


    public Historico(Conta conta, int TAMLARGURA, int TAMALTURA){
        
        TAMICONES = TAMALTURA/30;

        // Como nessa tela são muitos itens, os instanciamentos principais vão/deverão estar aqui:

        // Painéis e derivados
        painel_Sup = new JPanel();
        painel_Inf = new JPanel();
        fundo_saldo = new JPanelCustom("src/recursos/images/historico/img_saldo2.png");
        painel_conta = new JPanelCustom("src/recursos/images/historico/img_fundo.png");

        //Botões e derivados
        salvar = new JButtonCustom();
        editar = new JButtonCustom("src/recursos/images/historico/img_editar.png", TAMICONES*2, TAMICONES*2);
        perfil = new JButtonCustom("src/recursos/images/historico/img_perfil2.png", TAMALTURA/5, TAMALTURA/5);
        olho = new JButtonCustom("src/recursos/images/historico/img_olho.png", 26, 18);
        lixeira_registros = new JButtonCustom("src/recursos/images/historico/img_lixeira.png", TAMICONES, TAMICONES);
        lixeira_orcamentos = new JButtonCustom("src/recursos/images/historico/img_lixeira.png", TAMICONES, TAMICONES);

        //JLabels
        conta_invalida = new JLabel("Campos vazios e/ou inválidos", JLabel.CENTER);
        edite_text = new JLabel("Edite sua Conta", JLabel.CENTER);
        total = new JLabel();
        conta_saldo = new JLabel();
        historico_text = new JLabel("Registros", SwingConstants.LEFT);
        orcamentos_text = new JLabel("Orçamentos", SwingConstants.LEFT);
        total_receita = new JLabel();
        total_despesa = new JLabel();
        contas_text = new JLabel("Selecionar conta", JLabel.LEFT);
        conta_nome = new JLabel(conta.getNome());
        novo_nome_text = new JLabel("Novo Nome", JLabel.LEFT);
        novo_saldo_text = new JLabel("Saldo Atual", JLabel.LEFT);

        //JTables e TableModels
        orcamentoTableModel = new OrcamentoTableModel();
        historicoTableModel = new HistoricoTableModel();
        tabelaHistorico = new JTable();
        tabelaOrcamentos = new JTable();

        //JChecks
        selecionar_orcamentos = new JCheckBox();
        selecionar_registros = new JCheckBox();

        // JTextField e derivados
        novo_nome_conta = new JTextField();

        // Grafico
        graficoConta_text = new JLabel("Histórico de saldo", JLabel.CENTER);
        graficoHistorico = new GraficoLinha2();

        this.conta = conta;
        this.TAMALTURA = TAMALTURA;
        this.TAMLARGURA = TAMLARGURA;
        this.MARGEM = TAMALTURA/100;
        this.TAMICONES = TAMALTURA/30;
        this.TAMFONTE = TAMALTURA/20;


        setVisible(false);
        setLayout(null);
        criaPainel();
    }
   public void criaPainel(){
        try{
        quickSand = Font.createFont(Font.TRUETYPE_FONT, new File("src/recursos/fontes/quicksand/Quicksand_Book.otf"));
        }
        catch(IOException|FontFormatException e){
            System.out.println(e);
        }
        
        setBackground(Color.WHITE);
        setBounds(0, 0, TAMLARGURA, TAMALTURA);

        criaDivSup();
        criaTabelaRegistros();
        criaDivInf();
        criaTabelaOrcamentos();
        criaDivConta();
    }
    public void criaDivSup(){

        painel_Sup.setBackground(Color.WHITE);
        painel_Sup.setBounds(TAMLARGURA/3,0,TAMLARGURA*2/3, TAMALTURA/2 - TAMICONES);
        painel_Sup.setLayout(null);
        
        historico_text.setFont(quickSand.deriveFont(Font.PLAIN, TAMALTURA/23));
        historico_text.setBounds(TAMICONES, 0, TAMLARGURA / 3, TAMALTURA/10);
        historico_text.setForeground(Color.BLACK);
        painel_Sup.add(historico_text);
        
        selecionar_registros.setBounds(TAMLARGURA*2/3 - 5 - TAMICONES - TAMICONES/2, TAMICONES, TAMICONES, TAMICONES);
        selecionar_registros.setBackground(Color.WHITE);
        selecionar_registros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selecionar_registros.isSelected() && tabelaHistorico.getRowCount() > 0) {
                    tabelaHistorico.setRowSelectionInterval(0, tabelaHistorico.getRowCount() - 1);
                } else {
                    tabelaHistorico.getSelectionModel().clearSelection();
                }
            }
        });
        selecionar_registros.setCursor(new Cursor(Cursor.HAND_CURSOR));
        selecionar_registros.setToolTipText("Selecionar tudo");

        lixeira_registros.setBounds(TAMLARGURA*2/3 - 5 - 3*TAMICONES - 5, TAMICONES, TAMICONES, TAMICONES);
        lixeira_registros.setToolTipText("Excluir selecionados");
        
        painel_Sup.add(selecionar_registros);
        painel_Sup.add(lixeira_registros);
        add(painel_Sup);
    }
    public JPanel criaGraficoConta(){
        int aux = conta.getHistorico().size();
        int tamLista = 7;
        int first = aux - tamLista;

        if(first < 0){
            return new JPanelCustom("src/recursos/images/dashboard/img_padrao.png");
        }
        Registro registro = null;

        ArrayList<Registro> historicoRegistros = conta.getHistorico();
        ArrayList<Registro> ultimosRegistros = new ArrayList<>(); 

        for(int i = 0; i < tamLista; i++){
            registro = historicoRegistros.get(first + i);
            ultimosRegistros.add(registro);
        }

        JPanel grafico = graficoHistorico.createPanel(ultimosRegistros, conta.getSaldo());

        return grafico;

    }
    public void criaTabelaRegistros(){
        
        int tam_altura_celula = TAMALTURA/20;
        
        // Criando tabela e adicionando modelo já criado
        
        historicoTableModel.setRegistros(conta.getHistorico());

        tabelaHistorico.setCursor(new Cursor(Cursor.HAND_CURSOR));
        tabelaHistorico.setModel(historicoTableModel);
        tabelaHistorico.setGridColor(Color.WHITE);
        tabelaHistorico.setDefaultRenderer(Object.class, new RendererTabela());
        
        //Linha de Total
        JPanel painel_total = new JPanel();
        painel_total.setLayout(null);
        painel_total.setBounds(0, TAMALTURA/2 - TAMICONES - tam_altura_celula, TAMLARGURA*2/3, tam_altura_celula);
        painel_total.setBackground(Color.decode("#D9D9D9"));
        total.setText("Balanço Total:  " + NumberFormat.getCurrencyInstance(localBrasil).format(historicoTableModel.getTotal()));
        total.setHorizontalAlignment(SwingConstants.RIGHT);
        total.setFont(quickSand.deriveFont(Font.PLAIN, TAMALTURA/37));
        total.setBounds(0, 0, TAMLARGURA*2/3 - 5 - tam_altura_celula*2, tam_altura_celula);

        // Editando tabela Histórico do Usuario
        JScrollPane tabelaScroll = new JScrollPane(tabelaHistorico); // Adiciona barra de rolagem a tabela
        tabelaScroll.setBounds(0, 2*TAMICONES + MARGEM, TAMLARGURA*2/3, TAMALTURA/3);
        tabelaScroll.setBackground(Color.decode(azulClaro));

        tabelaHistorico.getTableHeader().setBackground(Color.decode(azulClaro));
        tabelaHistorico.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.decode(azulClaro)));
        tabelaHistorico.getTableHeader().setPreferredSize(new Dimension(0, tam_altura_celula));
        tabelaHistorico.setFont(quickSand.deriveFont(Font.PLAIN, TAMALTURA/37));
        tabelaHistorico.setFillsViewportHeight(true);
        tabelaHistorico.setRowHeight(tam_altura_celula);
        tabelaHistorico.setForeground(Color.BLACK);

        tabelaScroll.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        tabelaScroll.setForeground(Color.BLACK);
        tabelaScroll.getVerticalScrollBar().setCursor(new Cursor(Cursor.HAND_CURSOR));
        tabelaScroll.getVerticalScrollBar().setBackground(Color.WHITE);

        TableColumn column_categoria = tabelaHistorico.getColumnModel().getColumn(0);
        column_categoria.setPreferredWidth(TAMLARGURA/10);
        TableColumn column_conta = tabelaHistorico.getColumnModel().getColumn(1);
        column_conta.setPreferredWidth(TAMLARGURA/20);
        TableColumn column_valor = tabelaHistorico.getColumnModel().getColumn(2);
        column_valor.setPreferredWidth(TAMLARGURA/10);
        TableColumn column_nota = tabelaHistorico.getColumnModel().getColumn(3);
        column_nota.setPreferredWidth(TAMLARGURA/10);
        TableColumn column_tipo = tabelaHistorico.getColumnModel().getColumn(4);
        column_tipo.setPreferredWidth(TAMLARGURA/20);
        TableColumn column_data = tabelaHistorico.getColumnModel().getColumn(5);
        column_data.setPreferredWidth(TAMLARGURA/10);

        painel_Sup.add(tabelaScroll);
        painel_Sup.add(painel_total);
        painel_total.add(total);

    }  

    public void criaDivInf(){
        
        painel_Inf.setBackground(Color.WHITE);
        painel_Inf.setBounds(TAMLARGURA/3,TAMALTURA/2,TAMLARGURA*2/3, TAMALTURA/2);
        painel_Inf.setLayout(null);
        
        orcamentos_text.setFont(quickSand.deriveFont(Font.PLAIN, TAMALTURA/23));
        orcamentos_text.setBounds(TAMICONES, 0, TAMLARGURA / 3, TAMALTURA/10);
        orcamentos_text.setForeground(Color.BLACK);
        painel_Inf.add(orcamentos_text);
        
        selecionar_orcamentos.setBounds(TAMLARGURA*2/3 - 5 - TAMICONES - TAMICONES/2, TAMICONES, TAMICONES, TAMICONES);
        selecionar_orcamentos.setBackground(Color.WHITE);
        selecionar_orcamentos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selecionar_orcamentos.isSelected() && tabelaOrcamentos.getRowCount() > 0) {
                    tabelaOrcamentos.setRowSelectionInterval(0, tabelaOrcamentos.getRowCount() - 1);
                } else {
                    tabelaOrcamentos.getSelectionModel().clearSelection();
                }
            }
        });
        selecionar_orcamentos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        selecionar_orcamentos.setToolTipText("Selecionar tudo");

        lixeira_orcamentos.setBounds(TAMLARGURA*2/3 - 5 - 3*TAMICONES - 5, TAMICONES, TAMICONES, TAMICONES);
        lixeira_orcamentos.setToolTipText("Excluir selecionados");
        
        painel_Inf.add(selecionar_orcamentos);
        painel_Inf.add(lixeira_orcamentos);
        add(painel_Inf);
    }
    public void criaTabelaOrcamentos(){

        int tam_altura_celula = TAMALTURA/20;

        // Criando tabela e adicionando modelo já criado
        orcamentoTableModel.setOrcamentos(conta.getOrcamentos());
        
        tabelaOrcamentos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        tabelaOrcamentos.setModel(orcamentoTableModel);
        tabelaOrcamentos.setGridColor(Color.WHITE);
        tabelaOrcamentos.setDefaultRenderer(Object.class, new RendererTabela());
        
        //Calculando os totais de receita e despesa
        orcamentoTableModel.calcula_total();
        
        // Linha de Total Receita
        JPanel painel_total_receita = new JPanel();
        painel_total_receita.setLayout(null);
        painel_total_receita.setBounds(0, TAMALTURA/2 - tam_altura_celula, TAMLARGURA*2/3, tam_altura_celula);
        painel_total_receita.setBackground(Color.decode("#D9D9D9"));
        total_receita.setText("Receitas totais:  " + NumberFormat.getCurrencyInstance(localBrasil).format(orcamentoTableModel.getTotal_receira()));
        total_receita.setHorizontalAlignment(SwingConstants.RIGHT);
        total_receita.setFont(quickSand.deriveFont(Font.PLAIN, TAMALTURA/37));
        total_receita.setBounds(0, tam_altura_celula/10, TAMLARGURA*2/3 - MARGEM - tam_altura_celula*2, tam_altura_celula);

        // Linha de total Despesa
        JPanel painel_total_despesa = new JPanel();
        painel_total_despesa.setLayout(null);
        painel_total_despesa.setBounds(0, TAMALTURA/2 - 2*tam_altura_celula - MARGEM, TAMLARGURA*2/3, tam_altura_celula);
        painel_total_despesa.setBackground(Color.decode("#D9D9D9"));
        total_despesa.setText("Despesas totais:  " + NumberFormat.getCurrencyInstance(localBrasil).format(orcamentoTableModel.getTotal_despesa()));
        total_despesa.setHorizontalAlignment(SwingConstants.RIGHT);
        total_despesa.setFont(quickSand.deriveFont(Font.PLAIN, TAMALTURA/37));
        total_despesa.setBounds(0, tam_altura_celula/10, TAMLARGURA*2/3 - MARGEM - tam_altura_celula*2, tam_altura_celula);

        // Editando tabela orcamentos do Usuario
        JScrollPane tabelaScroll_orcamentos = new JScrollPane(tabelaOrcamentos); // Adiciona barra de rolagem a tabela
        tabelaScroll_orcamentos.setBounds(0, 2*TAMICONES + MARGEM, TAMLARGURA*2/3, TAMALTURA/3 - MARGEM);
        tabelaScroll_orcamentos.setBackground(Color.decode(azulClaro));

        tabelaOrcamentos.getTableHeader().setBackground(Color.decode(azulClaro));
        tabelaOrcamentos.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.decode(azulClaro)));
        tabelaOrcamentos.getTableHeader().setPreferredSize(new Dimension(0, tam_altura_celula));
        tabelaOrcamentos.setFont(quickSand.deriveFont(Font.PLAIN, TAMALTURA/37));
        tabelaOrcamentos.setFillsViewportHeight(true);
        tabelaOrcamentos.setRowHeight(tam_altura_celula);
        tabelaOrcamentos.setForeground(Color.BLACK);

        tabelaScroll_orcamentos.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        tabelaScroll_orcamentos.setForeground(Color.BLACK);
        tabelaScroll_orcamentos.getVerticalScrollBar().setCursor(new Cursor(Cursor.HAND_CURSOR));
        tabelaScroll_orcamentos.getVerticalScrollBar().setBackground(Color.WHITE);

        TableColumn column_categoria = tabelaOrcamentos.getColumnModel().getColumn(0);
        column_categoria.setPreferredWidth(TAMLARGURA/10);
        TableColumn column_data_inicio = tabelaOrcamentos.getColumnModel().getColumn(1);
        column_data_inicio.setPreferredWidth(TAMLARGURA/20);
        TableColumn column_data_fim = tabelaOrcamentos.getColumnModel().getColumn(2);
        column_data_fim.setPreferredWidth(TAMLARGURA/20);
        TableColumn column_nota = tabelaOrcamentos.getColumnModel().getColumn(3);
        column_nota.setPreferredWidth(TAMLARGURA/10);
        TableColumn column_tipo = tabelaOrcamentos.getColumnModel().getColumn(4);
        column_tipo.setPreferredWidth(TAMLARGURA/20);
        TableColumn column_valor = tabelaOrcamentos.getColumnModel().getColumn(5);
        column_valor.setPreferredWidth(TAMLARGURA/10);
        
        painel_total_receita.add(total_receita);
        painel_total_despesa.add(total_despesa);
        
        painel_Inf.add(tabelaScroll_orcamentos);
        tabelaScroll_orcamentos.setFocusable(false);
        painel_Inf.add(painel_total_despesa);
        painel_Inf.add(painel_total_receita);

    }
    public void criaDivConta(){
        int TAMDIV = TAMLARGURA/3;

        // Fundo de informações da conta
        
        painel_conta.setLayout(null);
        painel_conta.setBounds(0, 0, TAMDIV - 2*MARGEM, TAMALTURA);

        // botao perfil
        perfil.setBounds(TAMDIV/6, TAMDIV/6 - MARGEM, TAMALTURA/5, TAMALTURA/5);
        
        fundo_saldo.setLayout(null);
        fundo_saldo.setBounds(TAMDIV/3, TAMALTURA/8, TAMDIV/2, TAMALTURA/6 + MARGEM);


        // olhinho de ver saldo
        olho.setBounds(TAMDIV/2 - 3*TAMICONES, TAMICONES, 2*TAMICONES, TAMICONES);
        olho.setToolTipText("Ligar/Desligar visualização do seu saldo");

        // Área de conta e saldo
        conta_nome.setBounds(TAMDIV/15, TAMICONES, TAMDIV/4, 2*TAMICONES);
        conta_nome.setFont(quickSand.deriveFont(Font.PLAIN, TAMFONTE - (conta_nome.getText().length())/2));
        conta_nome.setForeground(Color.decode(azulClaro));

        conta_saldo.setBounds(TAMDIV/15, 2*TAMICONES + MARGEM*2, TAMDIV, 2*TAMICONES);
        conta_saldo.setFont(quickSand.deriveFont(Font.PLAIN, TAMFONTE));
        conta_saldo.setForeground(Color.decode(azulClaro));

        // Botao de editar 
        editar.setBounds(TAMDIV - 5*TAMICONES, TAMALTURA/3, TAMICONES*2,TAMICONES*2);
        editar.setToolTipText("Editar informações da sua conta");

        // JCombobox de seleção de conta

        contas = new JComboBoxCustom<>();
        contas.setFont(quickSand.deriveFont(Font.BOLD, TAMFONTE/2));
        contas.addItem(conta.getNome());
        contas.setBounds(TAMDIV/6, TAMALTURA/3 + TAMICONES, TAMDIV/3, TAMICONES + MARGEM);
        
        contas_text.setFont(quickSand.deriveFont(Font.BOLD, TAMFONTE/2));
        contas_text.setForeground(Color.WHITE);
        contas_text.setBounds(TAMDIV/6, TAMALTURA/3 - MARGEM, TAMDIV/3, TAMICONES + MARGEM);

        // Para editar conta

        edite_text.setBounds(0, 2*TAMICONES, TAMDIV, 2*TAMICONES);
        edite_text.setVisible(false);
        edite_text.setForeground(Color.WHITE);
        edite_text.setFont(quickSand.deriveFont(Font.BOLD, TAMFONTE));

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(localBrasil);
        NumberFormatter formatter = new NumberFormatter(currencyFormat);

        formatter.setValueClass(Double.class);
        formatter.setMinimum(0.0);
        formatter.setMaximum(Double.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);

        novo_saldo_conta = new JFormattedTextField(formatter);
        novo_saldo_conta.setValue(0.0); // Define um valor inicial
        novo_saldo_conta.setFont(quickSand.deriveFont(Font.BOLD, TAMFONTE));
        novo_saldo_conta.setHorizontalAlignment(SwingConstants.CENTER);

        novo_nome_conta.setBounds(TAMDIV/6, TAMALTURA/5, TAMDIV/3, TAMICONES + MARGEM);
        novo_saldo_conta.setBounds(TAMDIV/6, TAMALTURA/4 + TAMICONES, TAMDIV/3, TAMICONES + MARGEM);
        novo_nome_conta.setFont(quickSand.deriveFont(Font.BOLD, TAMFONTE/2));
        novo_saldo_conta.setFont(quickSand.deriveFont(Font.BOLD, TAMFONTE/2));
        novo_saldo_conta.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        novo_nome_conta.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        novo_nome_conta.setVisible(false);
        novo_saldo_conta.setVisible(false);

        novo_nome_text.setBounds(TAMDIV/6, TAMALTURA/5 - TAMICONES, TAMDIV, TAMICONES);
        novo_saldo_text.setBounds(TAMDIV/6, TAMALTURA/4, TAMDIV, TAMICONES);
        novo_nome_text.setFont(quickSand.deriveFont(Font.BOLD, TAMFONTE/2));
        novo_saldo_text.setFont(quickSand.deriveFont(Font.BOLD, TAMFONTE/2));
        novo_saldo_text.setForeground(Color.WHITE);
        novo_nome_text.setForeground(Color.WHITE);
        novo_nome_text.setVisible(false);
        novo_saldo_text.setVisible(false);

        salvar.setBounds(TAMDIV/2 - TAMDIV/4, TAMALTURA/3 + TAMICONES, TAMDIV/2, TAMICONES + 10);
        salvar.setVisible(false);
        salvar.setBackground(Color.decode("#00BF63"));
        salvar.setBorder(null);
        salvar.setForeground(Color.WHITE);
        salvar.setFont(quickSand.deriveFont(Font.PLAIN, TAMFONTE - MARGEM));
        salvar.setText("Salvar");

        conta_invalida.setBounds(0, 3*TAMICONES + MARGEM, TAMDIV, 2*TAMICONES);
        conta_invalida.setVisible(false);
        conta_invalida.setForeground(Color.RED);
        conta_invalida.setFont(quickSand.deriveFont(Font.PLAIN, TAMFONTE/2));
        conta_invalida.setBackground(null);

        // Grafico conta
        graficoConta_text.setFont(quickSand.deriveFont(Font.PLAIN, TAMFONTE));
        graficoConta_text.setForeground(Color.white);
        graficoConta_text.setBounds(0, TAMALTURA/2, TAMDIV, 2*TAMICONES);

        JPanel graficoConta = criaGraficoConta();
        graficoConta.setBounds(3, TAMALTURA - 220, TAMDIV, 185);

        // Adicionando tudo aos paineis

        painel_conta.add(graficoConta_text);
        painel_conta.add(graficoConta);
        fundo_saldo.add(conta_saldo);
        fundo_saldo.add(conta_nome);
        fundo_saldo.add(olho);
        painel_conta.add(novo_nome_conta);
        painel_conta.add(novo_saldo_conta);
        painel_conta.add(conta_invalida);
        painel_conta.add(novo_nome_text);
        painel_conta.add(novo_saldo_text);
        painel_conta.add(edite_text);
        painel_conta.add(salvar);
        painel_conta.add(contas);
        painel_conta.add(contas_text);
        painel_conta.add(editar);
        painel_conta.add(fundo_saldo);
        painel_conta.add(perfil);
        add(painel_conta);
    }
    public void atualizaTabelas(ArrayList<Registro> registros, ArrayList<Orcamento> orcamentos){
        historicoTableModel.setRegistros(registros);
        orcamentoTableModel.setOrcamentos(orcamentos);
        tabelaHistorico.repaint();
        tabelaOrcamentos.repaint();
        orcamentoTableModel.calcula_total();
        total_despesa.setText("Despesas totais:  " + NumberFormat.getCurrencyInstance(localBrasil).format(orcamentoTableModel.getTotal_despesa()));
        total_receita.setText("Receitas totais:  " + NumberFormat.getCurrencyInstance(localBrasil).format(orcamentoTableModel.getTotal_receira()));
        total.setText("Balanço Total:  " + NumberFormat.getCurrencyInstance(localBrasil).format(historicoTableModel.getTotal()));

    }
    public void editaConta(boolean ativo){
        novo_nome_conta.setText("");
        novo_saldo_conta.setValue(0);
        conta_invalida.setVisible(false);

        if(ativo){
            fundo_saldo.setVisible(false);
            contas_text.setVisible(false);
            perfil.setVisible(false);
            contas.setVisible(false);
            
            novo_nome_text.setVisible(true);
            novo_saldo_text.setVisible(true);
            novo_nome_conta.setVisible(true);
            novo_saldo_conta.setVisible(true);
            salvar.setVisible(true);
            edite_text.setVisible(true);
        }
        else{
            fundo_saldo.setVisible(true);
            contas_text.setVisible(true);
            perfil.setVisible(true);
            contas.setVisible(true);
            
            edite_text.setVisible(false);
            novo_nome_text.setVisible(false);
            novo_saldo_text.setVisible(false);
            novo_nome_conta.setVisible(false);
            novo_saldo_conta.setVisible(false);
            salvar.setVisible(false);
        }
    }
    public Conta getNovaConta(){

        Conta novaConta = null;
        if(novo_nome_conta.getText().length() > 4){
            String novoNome = novo_nome_conta.getText();
            Number temp =  (Number) novo_saldo_conta.getValue();
            Double novoSaldo = temp.doubleValue();
    
            novaConta = new Conta(novoNome, novoSaldo);
        }
        return novaConta;
    }
    public void setSaldo(String saldo) {
        conta_saldo.setText(saldo);
        repaint();
    }
    public void setConta_nome(String novoNome) {
        conta_nome.setText(novoNome);
        conta_nome.setFont(quickSand.deriveFont(Font.PLAIN, TAMFONTE - (conta_nome.getText().length())/2));
        contas.removeAllItems();
        contas.addItem(novoNome);
        repaint();
    }
    public JButtonCustom getPerfil() {
        return perfil;
    }
    public JLabel getConta_saldo() {
        return conta_saldo;
    }
    public JButtonCustom getEditar() {
        return editar;
    }
    public JButtonCustom getOlho() {
        return olho;
    }
    public JButtonCustom getSalvar() {
        return salvar;
    }
    public JButtonCustom getLixeira_orcamentos() {
        return lixeira_orcamentos;
    }
    public JButtonCustom getLixeira_registros() {
        return lixeira_registros;
    }
    public JLabel getConta_invalida() {
        return conta_invalida;
    }
    public JPanelCustom getFundo_saldo() {
        return fundo_saldo;
    }
    public JTable getTabelaHistorico() {
        return tabelaHistorico;
    }
    public JTable getTabelaOrcamentos() {
        return tabelaOrcamentos;
    }
    public OrcamentoTableModel getOrcamentoTableModel() {
        return orcamentoTableModel;
    }
    public HistoricoTableModel getHistoricoTableModel() {
        return historicoTableModel;
    }
    // eu sei eu sei, essas implementações de funções se repetem por varias classes
    public boolean estaVazio(JTextField campo) {
        return campo.getText() == null || campo.getText().trim().isEmpty();
    }
}
