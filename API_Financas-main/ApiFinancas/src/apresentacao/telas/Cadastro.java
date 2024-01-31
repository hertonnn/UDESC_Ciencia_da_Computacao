package apresentacao.telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import recursos.jcustons.JButtonCustom;
import recursos.jcustons.JPanelCustom;

public class Cadastro extends JDialog{

    private JPanel painelCadastro;
    private JTextField campoNomeCad;
    private JPasswordField campoSenhaCad;
    private JLabel cadastro_text;
    private JLabel nome_text;
    private JLabel senha_text;
    JButtonCustom entrar;
    JButtonCustom voltar;
    JLabel aviso;
    Font quickSand=null;

    int WIDTH = 820;
    int HEIGHT = (int)(0.61*WIDTH);

    int x = (int)(WIDTH*0.05);
    int y = (int)(HEIGHT*0.4);
        
    int y1 = (int)(HEIGHT*0.56);

    public Cadastro(){
        // Criando janela
        setSize(WIDTH,HEIGHT);
        setTitle("API Finanças/Cadastro");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        configTextF();
        configButton();
        configButtonVoltar();
        configPanel();
        repaint();
        
        add(painelCadastro);
    }
    public void fechar(){
        dispose();
    }
    public void abrir(){
        setVisible(true);
    }
    // tela
    public void configPanel(){
        
        String caminho = "src/recursos/images/cadastro/img_graficos.png";
        String azulClaro = "#0CC0DF";
        
        try{
        quickSand = Font.createFont(Font.TRUETYPE_FONT, new File("src/recursos/fontes/quicksand/Quicksand_Book.otf"));
        }
        catch(IOException|FontFormatException e){
            System.out.println(e);
        }

        cadastro_text = new JLabel("Cadastro", JLabel.CENTER);
        cadastro_text.setBounds(0,50, WIDTH/2, 80);
        cadastro_text.setFont(quickSand.deriveFont(Font.PLAIN, 75));
        cadastro_text.setForeground(Color.decode(azulClaro));

        nome_text = new JLabel("nome", JLabel.LEFT);
        senha_text = new JLabel("senha", JLabel.LEFT);

        nome_text.setBounds(x,y - 30, 100, 30);
        nome_text.setFont(quickSand.deriveFont(Font.BOLD, 20));
        nome_text.setForeground(Color.decode(azulClaro));
        senha_text.setBounds(x,y1 - 30, 100, 30); 
        senha_text.setFont(quickSand.deriveFont(Font.BOLD, 20));
        senha_text.setForeground(Color.decode(azulClaro));

        painelCadastro = new JPanel();
        painelCadastro.setBounds(0, 0, WIDTH, HEIGHT);
        painelCadastro.setLayout(null);
        painelCadastro.setBackground(Color.WHITE);
        painelCadastro.add(campoNomeCad);
        painelCadastro.add(campoSenhaCad);
        painelCadastro.add(entrar);
        painelCadastro.add(voltar);
        painelCadastro.add(cadastro_text);
        painelCadastro.add(senha_text);
        painelCadastro.add(nome_text);

        JPanelCustom divEsquerda = new JPanelCustom(caminho);
        divEsquerda.setBounds(WIDTH/2, 0, WIDTH/2, HEIGHT - 30);

        aviso = new JLabel("Senha e/ou Nome inválidos"); 
        aviso.setBounds(x, 0, 300, 300);
        aviso.setFont(new Font("Arial", 10, 15));
        aviso.setForeground(Color.WHITE);
        painelCadastro.add(aviso);
        painelCadastro.add(divEsquerda);
    }
    public void configTextF(){
        
        campoNomeCad = new JTextField();
        campoSenhaCad = new JPasswordField();

        campoNomeCad.setBounds(x, y, 320, 25);
        campoNomeCad.setOpaque(false);
        campoNomeCad.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        campoSenhaCad.setBounds(x, y1, 320, 25);
        campoSenhaCad.setOpaque(false);
        campoSenhaCad.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

    }
    public void configButton(){

        entrar = new JButtonCustom("src/recursos/images/cadastro/img_cadastro.png", 200, 45);
        entrar.setBounds(95, HEIGHT - 180, 200, 45);
        entrar.setContentAreaFilled(false);
        entrar.setBorderPainted(false);

    }
    public void configButtonVoltar(){

        voltar = new JButtonCustom("src/recursos/images/cadastro/img_voltar.png", 143, 35);
        voltar.setBounds(122, HEIGHT - HEIGHT/4, 143, 35);
        voltar.setContentAreaFilled(false);
        voltar.setBorderPainted(false);
        
    }

    public void cadastrado(){
        //divEsquerda.setCaminho("src/recursos/images/cadastro/cadastrado.jpg");
        pausaMili(400);     
    }
    public void invalido(){

        aviso.setForeground(Color.RED);
        campoNomeCad.setText("");
        campoSenhaCad.setText("");
        campoNomeCad.repaint();
        campoSenhaCad.repaint();
 
    }
    public void pausaMili(int pausa){
        try {
            TimeUnit.MILLISECONDS.sleep(pausa);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    public JTextField getCampoNome(){

        return campoNomeCad;
    }
    public JPasswordField getCampoSenha(){
        
        return campoSenhaCad;
    }

    public JButtonCustom getBotao(){
        return entrar;
    }
    public JButtonCustom getBotaoVoltar(){
        return voltar;
    }
    public boolean vazio(){
        if(estaVazio(campoNomeCad) || estaVazio(campoSenhaCad)){
            return false;   
        }
        return true;
    }
    public boolean estaVazio(JTextField campo) {
        return campo.getText() != null && !campo.getText().trim().isEmpty();
    }
    
}
