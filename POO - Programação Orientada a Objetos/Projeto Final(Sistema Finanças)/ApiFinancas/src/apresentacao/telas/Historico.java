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

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableColumn;

import dados.Conta;
import resources.classes.HistoricoTableModel;
import resources.classes.OrcamentoTableModel;
import resources.classes.RendererTabela;
import resources.jcustons.JButtonCustom;
import resources.jcustons.JPanelCustom;

public class Historico extends JPanel{

    Conta conta;

    private JButtonCustom lixeira_registros;
    private JPanelCustom painel_conta;
    private JButtonCustom lixeira_orcamentos;
    private JButtonCustom fechar;
    private JButtonCustom editar;
    private JButtonCustom perfil;
    private JPanelCustom fundo_saldo;
    private JButtonCustom olho;

    private JCheckBox selecionar_registros;
    private JCheckBox selecionar_orcamentos;
    private JTable tabelaHistorico;
    private JTable tabelaOrcamentos;
    JPanel painel_Sup;
    JPanel painel_Inf;
    JLabel saldo;
    JLabel nomeConta;
    JLabel textoSup;
    JLabel orcamentos_text;
    JLabel historico_text;
    JLabel conta_saldo;
    
    String azulClaro = "#0CC0DF";
    int TAMLARGURA;
    int TAMALTURA;
    int TAMICONES;

    Font quickSand = null;


    public Historico(Conta conta, int TAMLARGURA, int TAMALTURA){

        // Instanciamentos vão/deverão estar aqui
        painel_Sup = new JPanel();
        selecionar_registros = new JCheckBox();
        historico_text = new JLabel("Registros", SwingConstants.LEFT);
        lixeira_registros = new JButtonCustom("src/resources/images/historico/img_lixeira.png", TAMICONES, TAMICONES);
        tabelaHistorico = new JTable();
        conta_saldo = new JLabel();


        this.conta = conta;
        this.TAMALTURA = TAMALTURA;
        this.TAMLARGURA = TAMLARGURA;
        setVisible(false);
        setLayout(null);
        criaPainel();
    }
   public void criaPainel(){


        try{
        quickSand = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/fontes/quicksand/Quicksand_Book.otf"));
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

        TAMICONES = TAMALTURA/30;

        painel_Sup.setBackground(Color.WHITE);
        painel_Sup.setBounds(TAMLARGURA/3,0,TAMLARGURA*2/3 - 5, TAMALTURA/12);
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
                if (selecionar_registros.isSelected()) {
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
    public void criaTabelaRegistros(){
        
        int tam_altura_celula = TAMALTURA/20;
        
        // Criando tabela e adicionando modelo já criado
        HistoricoTableModel historico = new HistoricoTableModel();
        historico.setRegistros(conta.getHistorico());

        tabelaHistorico.setCursor(new Cursor(Cursor.HAND_CURSOR));
        tabelaHistorico.setModel(historico);
        tabelaHistorico.setGridColor(Color.WHITE);
        tabelaHistorico.setDefaultRenderer(Object.class, new RendererTabela());
        
        //Linha de Total
        JPanel painel_total = new JPanel();
        painel_total.setLayout(null);
        painel_total.setBounds(TAMLARGURA/3, TAMALTURA/3 + tam_altura_celula + tam_altura_celula/2, TAMLARGURA*2/3 - 5, tam_altura_celula);
        painel_total.setBackground(Color.decode("#D9D9D9"));
        JLabel total = new JLabel();
        total.setText("Balanço Total:  " + historico.getTotal());
        total.setHorizontalAlignment(SwingConstants.RIGHT);
        total.setFont(quickSand.deriveFont(Font.PLAIN, TAMALTURA/37));
        total.setBounds(0, tam_altura_celula/5, TAMLARGURA*2/3 - 5 - tam_altura_celula*2, tam_altura_celula);

        // Editando tabela Histórico do Usuario
        JScrollPane tabelaScroll = new JScrollPane(tabelaHistorico); // Adiciona barra de rolagem a tabela
        tabelaScroll.setBounds(TAMLARGURA/3, TAMALTURA/12, TAMLARGURA*2/3 - 5, TAMALTURA/3);
        tabelaScroll.setBackground(Color.decode("#3EDAD8"));

        tabelaHistorico.getTableHeader().setBackground(Color.decode("#3EDAD8"));
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
        
        add(tabelaScroll);
        add(painel_total);
        painel_total.add(total);

    }  

    public void criaDivInf(){

        TAMICONES = TAMALTURA/30;

        painel_Inf = new JPanel();
        painel_Inf.setBackground(Color.WHITE);
        painel_Inf.setBounds(TAMLARGURA/3,TAMALTURA/2 - TAMICONES,TAMLARGURA*2/3 - 5, TAMALTURA/12);
        painel_Inf.setLayout(null);
        
        orcamentos_text = new JLabel("Orçamentos", SwingConstants.LEFT);
        orcamentos_text.setFont(quickSand.deriveFont(Font.PLAIN, TAMALTURA/23));
        orcamentos_text.setBounds(TAMICONES, 0, TAMLARGURA / 3, TAMALTURA/10);
        orcamentos_text.setForeground(Color.BLACK);
        painel_Inf.add(orcamentos_text);
        

        selecionar_orcamentos = new JCheckBox();
        selecionar_orcamentos.setBounds(TAMLARGURA*2/3 - 5 - TAMICONES - TAMICONES/2, TAMICONES, TAMICONES, TAMICONES);
        selecionar_orcamentos.setBackground(Color.WHITE);
        selecionar_orcamentos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selecionar_orcamentos.isSelected()) {
                    tabelaOrcamentos.setRowSelectionInterval(0, tabelaOrcamentos.getRowCount() - 1);
                } else {
                    tabelaOrcamentos.getSelectionModel().clearSelection();
                }
            }
        });
        selecionar_orcamentos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        selecionar_orcamentos.setToolTipText("Selecionar tudo");

        lixeira_orcamentos = new JButtonCustom("src/resources/images/historico/img_lixeira.png", TAMICONES, TAMICONES);
        lixeira_orcamentos.setBounds(TAMLARGURA*2/3 - 5 - 3*TAMICONES - 5, TAMICONES, TAMICONES, TAMICONES);
        lixeira_orcamentos.setToolTipText("Excluir selecionados");
        
        painel_Inf.add(selecionar_orcamentos);
        painel_Inf.add(lixeira_orcamentos);
        add(painel_Inf);
    }
    public void criaTabelaOrcamentos(){

        int tam_altura_celula = TAMALTURA/20;

        // Criando tabela e adicionando modelo já criado
        OrcamentoTableModel historico = new OrcamentoTableModel();
        historico.setOrcamentos(conta.getOrcamentos());
        
        tabelaOrcamentos = new JTable();
        tabelaOrcamentos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        tabelaOrcamentos.setModel(historico);
        tabelaOrcamentos.setGridColor(Color.WHITE);
        tabelaOrcamentos.setDefaultRenderer(Object.class, new RendererTabela());
        
        //Calculando os totais de receita e despesa
        historico.calcula_total();
        
        // Linha de Total Receita
        JPanel painel_total_receita = new JPanel();
        painel_total_receita.setLayout(null);
        painel_total_receita.setBounds(TAMLARGURA/3, TAMALTURA - 2*tam_altura_celula - 10, TAMLARGURA*2/3 - 5, tam_altura_celula);
        painel_total_receita.setBackground(Color.decode("#D9D9D9"));
        JLabel total_receita = new JLabel();
        total_receita.setText("Receitas totais:  " + historico.getTotal_receira());
        total_receita.setHorizontalAlignment(SwingConstants.RIGHT);
        total_receita.setFont(quickSand.deriveFont(Font.PLAIN, TAMALTURA/37));
        total_receita.setBounds(0, tam_altura_celula/10, TAMLARGURA*2/3 - 5 - tam_altura_celula*2, tam_altura_celula);

        // Linha de total Despesa
        JPanel painel_total_despesa = new JPanel();
        painel_total_despesa.setLayout(null);
        painel_total_despesa.setBounds(TAMLARGURA/3, TAMALTURA - tam_altura_celula - 5, TAMLARGURA*2/3 - 5, tam_altura_celula);
        painel_total_despesa.setBackground(Color.decode("#D9D9D9"));
        JLabel total_despesa = new JLabel();
        total_despesa.setText("Despesas totais:  " + historico.getTotal_despesa());
        total_despesa.setHorizontalAlignment(SwingConstants.RIGHT);
        total_despesa.setFont(quickSand.deriveFont(Font.PLAIN, TAMALTURA/37));
        total_despesa.setBounds(0, tam_altura_celula/10, TAMLARGURA*2/3 - 5 - tam_altura_celula*2, tam_altura_celula);

        // Editando tabela orcamentos do Usuario
        JScrollPane tabelaScroll_orcamentos = new JScrollPane(tabelaOrcamentos); // Adiciona barra de rolagem a tabela
        tabelaScroll_orcamentos.setBounds(TAMLARGURA/3, TAMALTURA/2 + tam_altura_celula, TAMLARGURA*2/3 - 5, TAMALTURA/3);
        tabelaScroll_orcamentos.setBackground(Color.decode("#3EDAD8"));

        tabelaOrcamentos.getTableHeader().setBackground(Color.decode("#3EDAD8"));
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
        add(tabelaScroll_orcamentos);
        add(painel_total_despesa);
        add(painel_total_receita);

    }
    public void criaDivConta(){
        int MARGEM = 5;
        int tam_fundo_saldo = TAMLARGURA/3;

        // Fundo de informações da conta
        painel_conta = new JPanelCustom("src/resources/images/historico/img_fundo.png");
        painel_conta.setLayout(null);
        painel_conta.setBounds(MARGEM, MARGEM, tam_fundo_saldo- 2*MARGEM, TAMALTURA- 2*MARGEM);

        // botao fechar
        fechar = new JButtonCustom("/home/herton/Documentos/PROGRAMAÇÃO/UDESC/POO - Programação Orientada a Objetos/Projeto Final(Sistema Finanças)/ApiFinancas/src/resources/images/historico/img_fechar.png", TAMICONES, TAMICONES);

        fechar.setBounds(tam_fundo_saldo - 3*TAMICONES, TAMICONES ,TAMICONES + 5,TAMICONES + 5);

        // botao perfil
        perfil = new JButtonCustom("src/resources/images/historico/img_perfil2.png", TAMALTURA/5, TAMALTURA/5);
        perfil.setBounds(tam_fundo_saldo/6, tam_fundo_saldo/6, TAMALTURA/5, TAMALTURA/5);

        // fundo saldo
        int calc = TAMALTURA/5 - (TAMALTURA/6 + MARGEM);

        fundo_saldo = new JPanelCustom("src/resources/images/historico/img_saldo2.png");
        fundo_saldo.setLayout(null);
        fundo_saldo.setBounds(TAMICONES*8 - MARGEM, tam_fundo_saldo/6 + calc/2, tam_fundo_saldo/2, TAMALTURA/6 + MARGEM);


        // olhinho de ver saldo

        olho = new JButtonCustom("src/resources/images/historico/img_olho.png", 26, 18);
        olho.setBounds(140, 12, 26, 18);

        // Área de conta e saldo
        JLabel conta_nome = new JLabel(conta.getNome());
        conta_nome.setBounds(30,15, 100, 30);
        conta_nome.setFont(quickSand.deriveFont(Font.PLAIN, 22));
        conta_nome.setForeground(Color.decode(azulClaro));

        conta_saldo.setBounds(30,45, tam_fundo_saldo, 30);
        conta_saldo.setFont(quickSand.deriveFont(Font.PLAIN, 27));
        conta_saldo.setForeground(Color.decode(azulClaro));

        // Botao de editar 

        editar = new JButtonCustom("src/resources/images/historico/img_editar.png", TAMICONES + 5, TAMICONES + 5);
        editar.setBounds(tam_fundo_saldo - 4*TAMICONES, 170,TAMICONES + 5,TAMICONES + 5);

        
        fundo_saldo.add(conta_saldo);
        fundo_saldo.add(conta_nome);
        fundo_saldo.add(olho);
        painel_conta.add(editar);
        painel_conta.add(fundo_saldo);
        painel_conta.add(perfil);
        painel_conta.add(fechar);
        add(painel_conta);
    }
    public void setSaldo(String saldo) {
        conta_saldo.setText(saldo);
        repaint();
    }
    public JButtonCustom getPerfil() {
        return perfil;
    }
    public JLabel getConta_saldo() {
        return conta_saldo;
    }
    public JButtonCustom getOlho() {
        return olho;
    }
}
