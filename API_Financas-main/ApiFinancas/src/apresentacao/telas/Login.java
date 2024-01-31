package apresentacao.telas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import recursos.jcustons.JButtonCustom;
import recursos.jcustons.JPanelCustom;

public class Login extends JFrame{

    private JPanel painel;
    private JTextField campoNome;
    private JPasswordField campoSenha;
    private JLabel login_text;
    private JLabel nome_text;
    private JLabel senha_text;
    JLabel aviso;
    JButtonCustom entrar;
    JButtonCustom cadastro;
    Font quickSand=null;

    int WIDTH = 820;
    int HEIGHT = (int)(0.61*WIDTH);

    int x = (int)(WIDTH*0.05);
    int y = (int)(HEIGHT*0.4);
        
    int y1 = (int)(HEIGHT*0.56);

    public Login(){
        // Criando janela
        
        setSize(WIDTH,HEIGHT);
        setTitle("API Finanças/Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        configTextF();
        configButton();
        configButtonCadastro();
        configPanel();
        repaint();
        painel.repaint();

        add(painel);
        setVisible(true);

    }
    public void fechaLogin(){
        dispose();
    }
    public void abreLogin(){
        setVisible(true);
    }
    // tela
    public void configPanel(){
        
        String caminho = "src/recursos/images/login/img_graficos.png";
        String azulClaro = "#0CC0DF";

        try{
        quickSand = Font.createFont(Font.TRUETYPE_FONT, new File("src/recursos/fontes/quicksand/Quicksand_Book.otf"));
        }
        catch(IOException|FontFormatException e){
            System.out.println(e);
        }

        login_text = new JLabel("Login", JLabel.CENTER);
        login_text.setBounds(0,50, WIDTH/2, 80);
        login_text.setFont(quickSand.deriveFont(Font.PLAIN, 75));
        login_text.setForeground(Color.decode(azulClaro));

        nome_text = new JLabel("nome", JLabel.LEFT);
        senha_text = new JLabel("senha", JLabel.LEFT);

        nome_text.setBounds(x,y - 30, 100, 30);
        nome_text.setFont(quickSand.deriveFont(Font.BOLD, 20));
        nome_text.setForeground(Color.decode(azulClaro));
        senha_text.setBounds(x,y1 - 30, 100, 30); 
        senha_text.setFont(quickSand.deriveFont(Font.BOLD, 20));
        senha_text.setForeground(Color.decode(azulClaro));

        painel = new JPanel();
        painel.setBounds(0,0,WIDTH, HEIGHT);
        painel.setBackground(Color.WHITE);
        painel.setLayout(null);
        painel.add(campoNome);
        painel.add(campoSenha);
        painel.add(entrar);
        painel.add(cadastro);
        painel.add(login_text);
        painel.add(senha_text);
        painel.add(nome_text);

        JPanelCustom divDireita = new JPanelCustom(caminho);
        divDireita.setBounds(WIDTH/2, 0, WIDTH/2, HEIGHT - 30);
        painel.add(divDireita);


        aviso = new JLabel("Senha e/ou Nome inválidos"); 
        aviso.setBounds(x, 0, 300, 300);
        aviso.setFont(new Font("Arial", 10, 15));
        aviso.setForeground(Color.RED);
        aviso.setVisible(false);
        painel.add(aviso);
    
    }
    public void configTextF(){
        
        campoNome = new JTextField("");
        campoSenha = new JPasswordField("");

        campoNome.setBounds(x, y, 320, 25);
        campoNome.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        campoSenha.setBounds(x, y1, 320, 25);
        campoSenha.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));


    }
    public void configButton(){

        entrar = new JButtonCustom("src/recursos/images/login/img_entrar.png", 165, 45);
        entrar.setBounds(110, HEIGHT - 180, 165, 45);
        entrar.setContentAreaFilled(false);
        entrar.setBorderPainted(false);

    }
    public void configButtonCadastro(){

        cadastro = new JButtonCustom("src/recursos/images/cadastro/img_cadastro.png", 143, 30);
        cadastro.setBounds(121, HEIGHT - HEIGHT/4, 143, 30);
        cadastro.setContentAreaFilled(false);
        cadastro.setBorderPainted(false);
        
    }
    public void invalido(){

        aviso.setVisible(true);
        campoSenha.setText("");
        campoNome.setText("");
        campoNome.repaint();
        campoSenha.repaint();
    }
    public void pausaMili(int pausa){
        try {
            TimeUnit.MILLISECONDS.sleep(pausa);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    public void fecharLogin(){
        aviso.setVisible(false);
        dispose();
    }

    public JTextField getCampoNome(){
        
        return campoNome;
    }
    public JPasswordField getCampoSenha(){
        
        return campoSenha;
    }
    public boolean vazio(){
        if(estaVazio(campoNome) || estaVazio(campoSenha)){
            return false;   
        }
        return true;
    }

    public JButtonCustom getBotao(){
        return entrar;
    }
    public JButtonCustom getBotaoCadastro(){
        return cadastro;
    }
    public JTextField getPegaNome() {
        return campoNome;
    }
    public boolean estaVazio(JTextField campo) {
        return campo.getText() != null && !campo.getText().trim().isEmpty();
    }


}