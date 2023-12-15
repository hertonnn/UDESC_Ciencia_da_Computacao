package apresentacao.telas;

import java.awt.Color;
import java.awt.Font;
import java.util.concurrent.TimeUnit;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import resources.jcustons.JButtonCustom;
import resources.jcustons.JPanelCustom;

public class Cadastro extends JDialog{

    private JPanelCustom painelCadastro;
    private JTextField campoNomeCad;
    private JPasswordField campoSenhaCad;
    JButtonCustom entrar;
    JButtonCustom voltar;
    JLabel aviso;

    int WIDTH = 820;
    int HEIGHT = (int)(0.61*WIDTH);

    public Cadastro(){
        // Criando janela
        setSize(WIDTH,HEIGHT);
        setTitle("API Finanças/Cadastro");
        //setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        configTextF();
        configButton();
        configButtonVoltar();
        configPanel();
        repaint();
        painelCadastro.repaint();
        
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
        
        String caminho = "./src/resources/images/cadastro/Cadastro(Claro).jpg";

        painelCadastro = new JPanelCustom(caminho);
        painelCadastro.setPreferredSize(getSize());
        painelCadastro.setLayout(null);
        painelCadastro.add(campoNomeCad);
        painelCadastro.add(campoSenhaCad);
        painelCadastro.add(entrar);
        painelCadastro.add(voltar);

        int x = (int)(WIDTH*0.05);

        aviso = new JLabel("Senha e/ou Nome inválidos"); 
        aviso.setBounds(x, 0, 300, 300);
        aviso.setFont(new Font("Arial", 10, 15));
        aviso.setForeground(Color.WHITE);
        painelCadastro.add(aviso);
    }
    public void configTextF(){
        
        campoNomeCad = new JTextField();
        campoSenhaCad = new JPasswordField();

        int x = (int)(WIDTH*0.05);
        int y = (int)(HEIGHT*0.4);
        
        int y1 = (int)(HEIGHT*0.56);

        campoNomeCad.setBounds(x, y, 300, 20);
        campoNomeCad.setOpaque(false);
        campoNomeCad.setBorder(null);
        campoSenhaCad.setBounds(x, y1, 300, 20);
        campoSenhaCad.setOpaque(false);
        campoSenhaCad.setBorder(null);

    }
    public void configButton(){

        int x = (int)(WIDTH*0.05);
        int y = (int)(HEIGHT*0.72);

        entrar = new JButtonCustom();
        entrar.setBounds(x, y, 310, 30);
        entrar.setContentAreaFilled(false);
        entrar.setBorderPainted(false);

    }
    public void configButtonVoltar(){

        int x = (int)(WIDTH*0.05);
        int y = (int)(HEIGHT*0.72);

        voltar = new JButtonCustom();
        voltar.setBounds(x, y + 50, 310, 30);
        voltar.setContentAreaFilled(false);
        voltar.setBorderPainted(false);
        
    }

    public void cadastrado(){
        painelCadastro.setCaminho("src/resources/images/cadastro/cadastrado.jpg");
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
