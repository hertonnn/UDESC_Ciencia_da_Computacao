package apresentacao.telas;


import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import dados.Categoria;
import dados.Orcamento;
import dados.Registro;
import negocio.Sistema;
import resources.classes.Meses;
import resources.graficos.GraficoBarra;
import resources.graficos.GraficoDial;
import resources.graficos.GraficoLinha;
import resources.graficos.GraficoPizza;
import resources.jcustons.JButtonCustom;
import resources.jcustons.JComboBoxCustom;
import resources.jcustons.JPanelCustom;

public class Dashboard extends JFrame {
    
    Sistema sistema;
    Adicionar add;
    Historico historico;
    
    GraficoDial graficoDial;
    GraficoBarra graficoBarra = new GraficoBarra();
    GraficoLinha graficoLinhas = new GraficoLinha();
    GraficoPizza graficoPizza = new GraficoPizza();
    JPanelCustom imgErro;
    
    Dimension TAMTELA;
    
    private JPanel tela;
    private JPanel divSup;
    private JPanel divInf;
    private JPanel painelGrafico1;
    private JPanel painelGrafico2;
    private JPanel painelGrafico3;
    private JPanel painelGrafico4;
    private JPanel filtroPainel;
    private JLabel tituloGrafico1;
    JLabel valorSaldo;
    JButtonCustom saldo;
    JButtonCustom botaoAdd;
    JButtonCustom fotoAdd;
    JButtonCustom configBotao;
    JComboBoxCustom<String> comboBoxCategorias;
    JComboBoxCustom<String> comboBoxMeses;
    Configuracoes configuracoes;
    
    Double PROP_LATERAL = 0.85;
    Double PROP_VERTICAL = 0.7;
    
    // as duas divs tem a mesma largura e diferentes alturas
    int tamLargura;
    int tamYDivInf;
    int tamYDivSup;
    int TAMICONES;
    int MARGEM;
    int TAMLABELS;
    
    String azulClaro = "#0CC0DF";
    String ciano = "#AEBFD4"; 
    String branco = "#FFFFFF";
    String cinza = "#D9D9D9";
    String caminhoImg = "src/resources/images/dashboard/img_perfil.png";
    Font quickSand=null;
    DecimalFormat currencyFormat = (DecimalFormat) DecimalFormat.getCurrencyInstance();

    
    public Dashboard(Sistema sistema){
        
        //Pegar tamanho da tela
        
        Toolkit t = Toolkit.getDefaultToolkit();
        this.TAMTELA = t.getScreenSize();
        this.sistema = sistema;
        setSize(TAMTELA);
        setTitle("Dashboard Pessoal");
        setDefaultCloseOperation(EXIT_ON_CLOSE); // fechar janela 
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        //setUndecorated(true);
        //setExtendedState(MAXIMIZED_BOTH);
        
        add = new Adicionar(sistema);
        configuracoes = new Configuracoes();

        try{
        quickSand = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/fontes/quicksand/Quicksand_Book.otf"));
        }
        catch(IOException|FontFormatException e){
            System.out.println(e);
        }
        
        configTela();
        criaDivSuperior();
        criaDivInferior();
        
        add(tela);
        setVisible(true);

        // EVENTOS
        /* 
        Todos os eventos Listener estão aqui no construtor de Dashboard até eu achar/pensar em um design 
        de código que seja mais robusto e "correto" para a aplicação. 
        (conteúdos que tratam a fundo sobre isso estão rasos na net ou eu que sou um imundo que não sabe achar nada).
        */

        // DASHBOARD
        comboBoxCategorias.addActionListener(atualizar -> {
            atualizaGraficos();
        });
        comboBoxMeses.addActionListener(atualizar ->{
            //divInf.remove(historico);
            atualizaGraficos();
        });
        botaoAdd.addActionListener(adicionar ->{
            //divInf.add(historico, 0);
            //repaint();
            add.resetaAdd();
            add.abreAdd();
        });
        fotoAdd.addActionListener(novaFt ->{
            try {
                addFoto();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        historico.getPerfil().addActionListener(novaFt ->{
            try {
                addFoto();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        // TELA ADICIONAR
        /*
         * Ao fechar tela, resetar a janela add
        */
        add.addWindowListener(new WindowAdapter() {

            @Override
            
            public void windowClosing(WindowEvent e) {
                add.resetaAdd();
            }
            
        });
        /*
         realizar a adição e assim atualizar o dashboard
        */
        add.getRealizarAdd().addActionListener(realizaAdd ->{
            // funcionando, porém está feio. Modificar.
            if(add.addCategoria()){
                add.fecharAdd();
            }
            if(add.addOrcamento()){
                add.fecharAdd();
            }
            if(add.addRegistro()){
                add.fecharAdd();
            }
            atualizaDivSup();
            atualizaGraficos();
        });
        /*
        Alternar entre adições(Categoria, registro, orçamento)
        */
        add.getTipoAdd().addActionListener(adicao ->{
            if(add.getTipoAdd().getSelectedItem().toString().equals("Categoria")){
                add.modoRegistro(false);
                add.modoCategoria(true);
                add.modoOrcamento(false);
            }
            if(add.getTipoAdd().getSelectedItem().toString().equals("Registro")){
                add.modoOrcamento(false);
                add.modoCategoria(false);
                add.modoRegistro(true);
            }
            if(add.getTipoAdd().getSelectedItem().toString().equals("Orçamento")){
                add.modoRegistro(true);
                add.modoOrcamento(true);
                add.modoCategoria(false);
            }
            add.resetaAdd();
        });
        /*
         Alternar entre Receita e Despesa
        */
        add.getReceita().addActionListener(rece ->{
            add.eReceita(true);
        });
        add.getDespesa().addActionListener(desp ->{
            add.eReceita(false);
        });
        // CONFIGURAÇÕES
        configBotao.addActionListener(config->{
            modoConfig();
        });
        // TELA INF. DA CONTA
        valorSaldo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                modoHistorico();
            }
        });
        // Esconder saldo
        historico.getOlho().addActionListener(escondeSaldo ->{
            if(historico.getConta_saldo().equals("$****")){
                historico.setSaldo(currencyFormat.format(sistema.getUsuarioLogado().getConta().getSaldo()));
                valorSaldo.setText(currencyFormat.format(sistema.getUsuarioLogado().getConta().getSaldo()));
            }
            else{
                historico.setSaldo("$****");
                valorSaldo.setText("$****");
            }
        });
        
    }
    public void configTela(){
 
        tamLargura = (int)(TAMTELA.getWidth()*PROP_LATERAL);
        tamYDivSup = (int)(TAMTELA.getHeight()*PROP_VERTICAL/10);
        tamYDivInf = (int)(TAMTELA.getHeight()*PROP_VERTICAL);
        historico = new Historico(sistema.getUsuarioLogado().getConta(), tamLargura, tamYDivInf);
        this.TAMLABELS = (int)(tamYDivSup/2);
        
        tela = new JPanel();
        tela.setBackground(Color.decode(ciano));
        tela.setPreferredSize(getSize());
        tela.setBounds(getBounds());
        tela.setLayout(null);
        // texto boas vindas

        JLabel boasVindas = new JLabel("Bem vindo(a), " + sistema.getUsuarioLogado().getNome() + "!");
        boasVindas.setFont(quickSand.deriveFont(Font.PLAIN, TAMLABELS));
        boasVindas.setForeground(Color.decode(branco));

        // alinhando texto sempre com a div superior

        int posX = (TAMTELA.width / 2) - (tamLargura / 2 );
        int posY = TAMTELA.height/20 - 10;

        boasVindas.setBounds(posX, posY, TAMTELA.width, tamYDivSup);
        tela.add(boasVindas);

    }
    public void criaDivSuperior(){

        //instanciando aqui alguns componentes
        comboBoxMeses = new JComboBoxCustom<>(); 
        comboBoxCategorias = new JComboBoxCustom<>();
        valorSaldo = new JLabel("", JLabel.CENTER);
        filtroPainel = new JPanel();
        
        MARGEM = tamLargura/80;
        TAMICONES = (int)(tamYDivSup*0.7);
        // calculo para encontrar as cooredenadas X e Y para a centralização da janela.
        int posX = (TAMTELA.width / 2) - (tamLargura / 2 );
        int posY = TAMTELA.height/10;

        divSup = new JPanel(); 
        divSup.setBackground(Color.WHITE);
        divSup.setBounds(posX,posY, tamLargura, tamYDivSup);
        divSup.setLayout(null);
        tela.add(divSup);

        criaBotaoAddFoto();
        criaIconeSaldo();
        criaBotaoAdd();
        criaBotaoConfig(tamLargura);
        criaFiltro(tamLargura);

    }
    public void criaIconeSaldo(){

        // Verificando a quantidade de dígitos do saldo
        int qntDigitos = 0;
        double saldoConta = sistema.getUsuarioLogado().getConta().getSaldo();

        while(saldoConta > 1) { 
            saldoConta = saldoConta/10; 
            qntDigitos++; 
        } 

        int posY = tamYDivSup/2 - TAMICONES/2;

        // elementos div superior
        saldo = new JButtonCustom("src/resources/images/dashboard/img_saldo.png", TAMICONES*4, TAMICONES);
        saldo.setBounds(TAMICONES+15,posY,TAMICONES*4,TAMICONES);
        saldo.setLayout(null);

        valorSaldo.setText(currencyFormat.format(sistema.getUsuarioLogado().getConta().getSaldo())); 
        historico.setSaldo(currencyFormat.format(sistema.getUsuarioLogado().getConta().getSaldo()));
        valorSaldo.setToolTipText("Mais sobre conta " + sistema.getUsuarioLogado().getConta().getNome());
        valorSaldo.setFont(quickSand.deriveFont(Font.BOLD, TAMLABELS*85/100 - qntDigitos*180/100));
        valorSaldo.setForeground(Color.WHITE);
        valorSaldo.setBounds(TAMICONES/4 + 2*qntDigitos, TAMICONES/10, TAMICONES*4, TAMICONES);


        saldo.add(valorSaldo);
        divSup.add(saldo);
    }
    public void criaBotaoAdd(){

        int posY = tamYDivSup/2 - TAMICONES/2;
        botaoAdd = new JButtonCustom("src/resources/images/dashboard/img_add.png", TAMICONES - 5, TAMICONES);
        botaoAdd.setBounds(TAMICONES*5+20,posY,TAMICONES,TAMICONES);
        botaoAdd.setToolTipText("Adicionar Registro/Orçamento");

        divSup.add(botaoAdd);

    }
    public void criaBotaoAddFoto(){

        int posY = tamYDivSup/2 - TAMICONES/2;
        // Posiciona o botao de adição
        fotoAdd = new JButtonCustom(caminhoImg, TAMICONES, TAMICONES);
        fotoAdd.setBounds(10,posY,TAMICONES,TAMICONES);
        fotoAdd.setContentAreaFilled(false);
        fotoAdd.setToolTipText("Alterar foto de perfil");
        fotoAdd.setBorderPainted(false);

        divSup.add(fotoAdd);
    }
    public void criaFiltro(int tamLargura){

        comboBoxMeses.setBounds(TAMICONES*13/2 + 10, TAMICONES/2 - TAMICONES*70/100/2, TAMICONES*3, TAMICONES*70/100);
        comboBoxMeses.addItem("Todas");
        
        Meses[] meses = Meses.values();

        for(Meses mes : meses){
            comboBoxMeses.addItem(mes.getNome());
        }    
        comboBoxCategorias.setBounds(TAMICONES*13/2 + TAMICONES*3 + 20, TAMICONES/2 - TAMICONES*70/100/2,  TAMICONES*4, TAMICONES*70/100);
        comboBoxCategorias.addItem("Todas");
        for(Categoria categoria : sistema.getUsuarioLogado().getConta().getCategorias()){
            comboBoxCategorias.addItem(categoria.getNome());
        }


        JLabel labelFiltro = new JLabel("Mês ou/e Categorias:", JLabel.RIGHT);
        labelFiltro.setToolTipText("Filtrar Período");
        labelFiltro.setFont(quickSand.deriveFont(Font.PLAIN, TAMLABELS*80/100));
        labelFiltro.setForeground(Color.BLACK);
        labelFiltro.setBounds(0, 0, TAMICONES*13/2, TAMICONES);
        

        int posY = tamYDivSup/2 - TAMICONES/2;

        filtroPainel.setBounds(tamLargura/2 - (TAMICONES*13)/2,posY,TAMICONES*15,TAMICONES);
        filtroPainel.setLayout(null);
        filtroPainel.setBackground(Color.WHITE);
        filtroPainel.add(comboBoxMeses);
        filtroPainel.add(comboBoxCategorias);
        filtroPainel.add(labelFiltro);       

        comboBoxMeses.setToolTipText("Filtro por mês");
        comboBoxMeses.setFont(quickSand.deriveFont(Font.BOLD, TAMLABELS*70/100));

        comboBoxCategorias.setToolTipText("Filtro por categoria");
        comboBoxCategorias.setFont(quickSand.deriveFont(Font.BOLD, TAMLABELS*70/100));
        
        divSup.add(filtroPainel);

    }
    public void criaBotaoConfig(int tamLargura){
        int posY = tamYDivSup/2 - TAMICONES/2;
        configBotao = new JButtonCustom("src/resources/images/dashboard/img_config.png", TAMICONES,TAMICONES);
        configBotao.setBounds(tamLargura - 2*TAMICONES*70/100,posY,TAMICONES,TAMICONES);
        configBotao.setToolTipText("Configurações");
        divSup.add(configBotao);
    }
    public void criaDivInferior(){

        // calculo para encontrar as cooredenadas X e Y para a centralização da janela.
        int posX = (TAMTELA.width / 2) - (tamLargura / 2);
        int posY = TAMTELA.height/6 + MARGEM;

        divInf = new JPanel(); 
        divInf.setBackground(Color.decode(ciano));
        divInf.setBounds(posX, posY, tamLargura, tamYDivInf);
        divInf.setLayout(null);

        // Painel de Historicos da conta
        divInf.add(historico);
        
        criaPainesGraficos();
        tela.add(divInf);
        criaConfig();
    }
    public void criaPainesGraficos(){

        criaPainel1();
        criaPainel2();
        criaPainel3();
        criaPainel4();

    }
    public void criaPainel1(){

        int tamX1 = tamLargura/3;
        int tamY1 = tamYDivInf/2;
        
        tituloGrafico1 = new JLabel("Orçamentos", JLabel.CENTER);
        tituloGrafico1.setFont(quickSand.deriveFont(Font.PLAIN, TAMLABELS));
        tituloGrafico1.setForeground(Color.decode(azulClaro));
        tituloGrafico1.setBounds(0, TAMLABELS, tamX1, TAMLABELS);

        painelGrafico1 = new JPanel();
        painelGrafico1.setLayout(null);
        painelGrafico1.setBackground(Color.decode(branco));
        painelGrafico1.setBounds(0, 0, tamX1, tamY1);
        
        painelGrafico1.add(tituloGrafico1);
        plotaGraficoDial(sistema.getUsuarioLogado().getConta().getHistorico(), sistema.getUsuarioLogado().getConta().getOrcamentos());
        divInf.add(painelGrafico1);
    }
    public void criaPainel2(){

        int tamX2 = (2*tamLargura)/3 - MARGEM;
        int posX2 = tamLargura/3 + MARGEM;

        JLabel tituloGrafico2 = new JLabel("Evolução dos seus Registros", JLabel.CENTER);
        tituloGrafico2.setFont(quickSand.deriveFont(Font.PLAIN, TAMLABELS));
        tituloGrafico2.setForeground(Color.decode(azulClaro));
        tituloGrafico2.setBounds(0, TAMLABELS, tamX2, TAMLABELS);

        painelGrafico2 = new JPanel();
        painelGrafico2.setLayout(null);
        painelGrafico2.setBackground(Color.decode(branco));
        painelGrafico2.setBounds(posX2, 0, tamX2, tamYDivInf/2);
        
        painelGrafico2.add(tituloGrafico2);
        plotaGraficoLinha(sistema.getUsuarioLogado().getConta().getHistorico());
        divInf.add(painelGrafico2);
    }
    public void criaPainel3(){

        int posY3 = (tamYDivInf/2) + MARGEM; 

        JLabel tituloGrafico3 = new JLabel("Receitas x Despesas", JLabel.CENTER);
        tituloGrafico3.setFont(quickSand.deriveFont(Font.PLAIN, TAMLABELS));
        tituloGrafico3.setForeground(Color.decode(azulClaro));
        tituloGrafico3.setBounds(0, TAMLABELS,  (tamLargura*2)/3, TAMLABELS);

        painelGrafico3 = new JPanel();
        painelGrafico3.setLayout(null);
        painelGrafico3.setBackground(Color.decode(branco));
        painelGrafico3.setBounds(0, posY3, (tamLargura*2)/3, tamYDivInf);
        
        painelGrafico3.add(tituloGrafico3);
        plotaGraficoBarra(sistema.getUsuarioLogado().getConta().getHistorico());
        divInf.add(painelGrafico3);
    }
    public void criaPainel4(){

        int posY4 = (tamYDivInf/2) + MARGEM; 
        int posX4 = (tamLargura*2)/3 + MARGEM;

        JLabel tituloGrafico4 = new JLabel("Estrutura de Gastos", JLabel.CENTER);
        tituloGrafico4.setFont(quickSand.deriveFont(Font.PLAIN, TAMLABELS));
        tituloGrafico4.setForeground(Color.decode(azulClaro));
        tituloGrafico4.setBounds(0, TAMLABELS, tamLargura/3, TAMLABELS);

        painelGrafico4 = new JPanel();
        painelGrafico4.setLayout(null);
        painelGrafico4.setBackground(Color.decode(branco));
        painelGrafico4.setBounds(posX4, posY4, tamLargura/3, tamYDivInf);
        
        painelGrafico4.add(tituloGrafico4);
        plotaGraficoPizza(sistema.getUsuarioLogado().getConta().getHistorico());
        divInf.add(painelGrafico4);
    }
    public void criaConfig(){
        
            int posX = TAMTELA.width - (int)(TAMTELA.width*(1-PROP_LATERAL)/2) - tamLargura/4;//Matemática foi braba kkkkk
            int posY = TAMTELA.height/10 + tamYDivSup;
    
            configuracoes.setBounds(posX,posY,tamLargura/4,tamYDivInf + MARGEM);
            configuracoes.setBorder(new LineBorder(Color.GRAY));
            configuracoes.setBackground(Color.WHITE);
            configuracoes.setVisible(false);
            tela.add(configuracoes);
            tela.setComponentZOrder(configuracoes, 0);
    }
    public void plotaGraficoDial(ArrayList<Registro> registros, ArrayList<Orcamento> orcamentos){

        int tamX1 = tamLargura/3;
        int tamY1 = tamYDivInf/2;

        graficoDial = new GraficoDial();
        double porcentagem = 0.7;
        int tamXGrafico = (int)(tamX1*porcentagem)+15;
        int tamYGrafico = (int)(tamY1*porcentagem);
        int posXGrafico = (tamX1/2) - (tamXGrafico/2);
        int posYGrafico = (tamY1/2) - (tamYGrafico/2)+20;

        if(orcamentos.size() == 0){
            imgErro = new JPanelCustom("src/resources/images/dashboard/img_padrao2.png");
            imgErro.setBounds(0, posYGrafico, tamX1, tamYGrafico);
            painelGrafico1.add(imgErro);
        }
        else{
            JPanel grafico1 = graficoDial.createPanel(registros, orcamentos);
            grafico1.setBounds(posXGrafico, posYGrafico, tamXGrafico, tamYGrafico);
            
            if(graficoDial.atingiu()){
                JPanelCustom atingiu = new JPanelCustom("src/resources/images/dashboard/img_limite.png");
                atingiu.setBounds(painelGrafico1.getWidth()/2 - (tamXGrafico*70/100)/2, posYGrafico, tamXGrafico*70/100, tamYGrafico*21/100);
                
                painelGrafico1.add(atingiu);
            }
            painelGrafico1.add(grafico1);
        }

    }
    public void plotaGraficoLinha(ArrayList<Registro> registros){

        int tamX2 = (2*tamLargura)/3 - MARGEM;

        graficoLinhas = new GraficoLinha();

        int tamXGrafico = (int)(tamX2*0.9);
        int tamYGrafico = (int)(tamYDivInf*0.35);
        int posXGrafico = (tamX2/2) - (tamXGrafico/2);
        int posYGrafico = (tamYDivInf/4) - (tamYGrafico/3) - 20;

        if(registros.size() < 3){
            posXGrafico = (tamX2/2) - (tamXGrafico/4);
            imgErro = new JPanelCustom("src/resources/images/dashboard/img_padrao.png");
            imgErro.setBounds(posXGrafico, posYGrafico, tamXGrafico/2, tamYGrafico);
            painelGrafico2.add(imgErro);
        }
        else{

            JPanel grafico2 = graficoLinhas.createPanel(registros);
            grafico2.setBounds(posXGrafico, posYGrafico, tamXGrafico, tamYGrafico);
            painelGrafico2.add(grafico2);
        }


    }
    public void plotaGraficoBarra(ArrayList<Registro> registros){
        graficoBarra = new GraficoBarra();

        int tamXGrafico = (int)((tamLargura*2)/3*0.9);
        int tamYGrafico = (int)(tamYDivInf*0.35);
        int posXGrafico = ((tamLargura*2)/3/2) - (tamXGrafico/2);
        int posYGrafico = (tamYDivInf/4) - (tamYGrafico/3) - 20;

        if(registros.size() < 3){
            posXGrafico = ((tamLargura*2)/3/2) - (tamXGrafico/4);
            imgErro = new JPanelCustom("src/resources/images/dashboard/img_padrao3.png");
            imgErro.setBounds(posXGrafico, posYGrafico, tamXGrafico/2, tamYGrafico);
            painelGrafico3.add(imgErro);
        }
        else{

            JPanel grafico3 = graficoBarra.createPanel(registros);
            grafico3.setBounds(posXGrafico, posYGrafico, tamXGrafico, tamYGrafico);
            painelGrafico3.add(grafico3);
        }
    }
    public void plotaGraficoPizza(ArrayList<Registro> registros){
        graficoPizza = new GraficoPizza();
        
        int tamXGrafico = (int)(tamLargura/3) - TAMICONES;
        int tamYGrafico = (int)(tamYDivInf*0.4) - TAMLABELS;
        int posXGrafico = (tamLargura/3/2) - (tamXGrafico/2) - 20;
        int posYGrafico = (tamYDivInf/4) - (tamYGrafico/2) + 10;

        if(registros.size() < 3){
            imgErro = new JPanelCustom("src/resources/images/dashboard/img_padrao2.png");
            imgErro.setBounds(0, posYGrafico, tamLargura/3, tamYGrafico);
            painelGrafico4.add(imgErro);
        }
        else{

            JPanel grafico4 = graficoPizza.createPanel(registros, sistema.getUsuarioLogado().getConta().getCategorias());
            grafico4.setBounds(posXGrafico, posYGrafico, tamXGrafico, tamYGrafico);
            painelGrafico4.add(grafico4);
            grafico4.setBackground(null);
        }

    }
    public void atualizaDivSup(){
        // salvar o item selecionado anteriormente
        int atualCat = comboBoxCategorias.getSelectedIndex();
        comboBoxCategorias.removeAllItems();
        comboBoxCategorias.addItem("Todas");
        for(Categoria categoria : sistema.getUsuarioLogado().getConta().getCategorias()){
            comboBoxCategorias.addItem(categoria.getNome());
        }
       
        divSup.remove(saldo);
        saldo.removeAll();
        criaIconeSaldo();
        divSup.repaint();
        comboBoxCategorias.setSelectedIndex(atualCat);
        filtroPainel.repaint();
    }
    public void modoHistorico(){
        if(!historico.isVisible()){
            historico.setVisible(true);
            painelGrafico1.setVisible(false);
            painelGrafico2.setVisible(false);
            painelGrafico3.setVisible(false);
            painelGrafico4.setVisible(false);
        }else{
            historico.setVisible(false);
            painelGrafico1.setVisible(true);
            painelGrafico2.setVisible(true);
            painelGrafico3.setVisible(true);
            painelGrafico4.setVisible(true);
        }
        configuracoes.setVisible(false);
    }
    public void modoConfig(){
        if(!configuracoes.isVisible()){
            configuracoes.setVisible(true);
        }else{
            configuracoes.setVisible(false);
        }
    }
    public void atualizaGraficos(){

        String mesFiltro = "";
        String categoriaFiltro = "";

        if(!comboBoxCategorias.estaVazio(comboBoxCategorias)){
            mesFiltro = comboBoxMeses.getSelectedItem().toString();
            categoriaFiltro = comboBoxCategorias.getSelectedItem().toString();
        }
        int mesNum = 0;

        Meses[] meses = Meses.values();

        for(Meses mes : meses){
            if(mes.getNome().equals(mesFiltro)){
                mesNum = mes.getNum();
            }
        }
        
        atualizaGrafico1(sistema.filtraRegistrosCategoria(categoriaFiltro, sistema.getUsuarioLogado().getConta()), sistema.filtraOrcamentoCategoria(categoriaFiltro, sistema.getUsuarioLogado().getConta()));
        atualizaGrafico2(sistema.filtraRegistros(mesNum, categoriaFiltro, sistema.getUsuarioLogado().getConta()));
        atualizaGrafico3(sistema.filtraRegistros(mesNum, categoriaFiltro, sistema.getUsuarioLogado().getConta()));
        atualizaGrafico4(sistema.filtraRegistrosMes(mesNum, sistema.getUsuarioLogado().getConta()));

    }
    public void atualizaGrafico1(ArrayList<Registro> registros, ArrayList<Orcamento> orcamentos){
        painelGrafico1.removeAll();
        painelGrafico1.add(tituloGrafico1);
        plotaGraficoDial(registros,orcamentos);
        painelGrafico1.repaint();
        
    }
    public void atualizaGrafico2(ArrayList<Registro> registros){

        painelGrafico2.remove(1);
        plotaGraficoLinha(registros);
        painelGrafico2.repaint();

        
    }
    public void atualizaGrafico3(ArrayList<Registro> registros){
        painelGrafico3.remove(1);
        plotaGraficoBarra(registros);
        painelGrafico3.repaint();
    }
    public void atualizaGrafico4(ArrayList<Registro> registros){
        painelGrafico4.remove(1);;
        plotaGraficoPizza(registros);
        painelGrafico4.repaint();
    }

    public void addFoto() throws IOException{
        JFileChooser arquivo = new JFileChooser();
        arquivo.setDialogTitle("Selecione uma foto de perfil");
        arquivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int op = arquivo.showOpenDialog(this);
        
        if(op == JFileChooser.APPROVE_OPTION){
            File file = new File("");
            file = arquivo.getSelectedFile();
            BufferedImage foto = ImageIO.read(file);
            BufferedImage fotoCortada = cortaImagem(foto, 200);
            caminhoImg = "src/resources/images/dashboard/fotoUser.png";
            ImageIO.write(fotoCortada, "png", new File(caminhoImg));
            historico.getPerfil().setIcon(caminhoImg);
            fotoAdd.setIcon(caminhoImg);
        }
    }

    //Funções extras
    public static BufferedImage cortaImagem(BufferedImage image, int cornerRadius) {
        int w = image.getWidth();
        int h = image.getHeight();

        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    
        Graphics2D g2 = output.createGraphics();
        
        // This is what we want, but it only does hard-clipping, i.e. aliasing
        // g2.setClip(new RoundRectangle2D ...)
    
        // so instead fake soft-clipping by first drawing the desired clip shape
        // in fully opaque white with antialiasing enabled...
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, (cornerRadius*w*h)/100, (cornerRadius*w*h)/100));
        
        // ... then compositing the image on top,
        // using the white shape from above as alpha source
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 1, 1, null);
        
        g2.dispose();
        
        return output;
}
}
