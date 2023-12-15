package apresentacao.telas;
import java.awt.Color;
import java.awt.Font;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import resources.jcustons.JButtonCustom;
import resources.jcustons.JPanelCustom;

public class Login extends JFrame{

    private JPanelCustom painel;
    private JTextField campoNome;
    private JPasswordField campoSenha;
    JLabel aviso;
    JButtonCustom entrar;
    JButtonCustom cadastro;

    int WIDTH = 820;
    int HEIGHT = (int)(0.61*WIDTH);

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
        
        String caminho = "./src/resources/images/login/Login(Claro).jpg";

        painel = new JPanelCustom(caminho);
        painel.setPreferredSize(getSize());
        painel.setLayout(null);
        painel.add(campoNome);
        painel.add(campoSenha);
        painel.add(entrar);
        painel.add(cadastro);

        int x = (int)(WIDTH*0.05);

        aviso = new JLabel("Senha e/ou Nome inválidos"); 
        aviso.setBounds(x, 0, 300, 300);
        aviso.setFont(new Font("Arial", 10, 15));
        aviso.setForeground(Color.WHITE);
        painel.add(aviso);
    
    }
    public void configTextF(){
        
        campoNome = new JTextField("");
        campoSenha = new JPasswordField("");

        int x = (int)(WIDTH*0.05);
        int y = (int)(HEIGHT*0.4);
        
        int y1 = (int)(HEIGHT*0.56);

        campoNome.setBounds(x, y, 300, 20);
        campoNome.setOpaque(false);
        campoNome.setBorder(null);
        campoSenha.setBounds(x, y1, 300, 20);
        campoSenha.setOpaque(false);
        campoSenha.setBorder(null);


    }
    public void configButton(){

        int x = (int)(WIDTH*0.05);
        int y = (int)(HEIGHT*0.72);

        entrar = new JButtonCustom();
        entrar.setBounds(x, y, 310, 30);
        entrar.setContentAreaFilled(false);
        entrar.setBorderPainted(false);

    }
    public void configButtonCadastro(){

        int x = (int)(WIDTH*0.05);
        int y = (int)(HEIGHT*0.72);

        cadastro = new JButtonCustom();
        cadastro.setBounds(x, y + 50, 310, 30);
        cadastro.setContentAreaFilled(false);
        cadastro.setBorderPainted(false);
        
    }
    public void invalido(){
        javax.swing.SwingUtilities.invokeLater(new Runnable() { 
            public void run() { 
                aviso.setForeground(Color.RED);
                campoSenha.setText("");
                campoNome.setText("");
                campoNome.repaint();
                campoSenha.repaint();
                
            } 
        });
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