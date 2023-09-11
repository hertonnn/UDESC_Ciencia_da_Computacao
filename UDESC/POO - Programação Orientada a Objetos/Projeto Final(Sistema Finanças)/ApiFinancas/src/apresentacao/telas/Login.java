package apresentacao.telas;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

public class Login extends JFrame implements ActionListener{

    private Panel painel;
    private JTextField pegaNome;
    private JTextField pegaSenha;
    JButton entrar;

    int WIDTH = 820;
    int HEIGHT = (int)(0.61*WIDTH);


    public Login(){
        // Criando janela
        setSize(WIDTH,HEIGHT);
        setTitle("API Finanças");
        setDefaultCloseOperation(EXIT_ON_CLOSE); // fechar janela 
        setLocationRelativeTo(null);
        setResizable(false);
        
        configTextF();
        configButton();
        configPanel();
        
        add(painel);
        setVisible(true);


    }
    public void configPanel(){
        
        String caminho = "./src/resources/Login.jpg";

        painel = new Panel(caminho);
        painel.setPreferredSize(getSize());
        painel.setLayout(null);
        painel.add(pegaNome);
        painel.add(pegaSenha);
        painel.add(entrar);
    }
    public void configTextF(){
        
        pegaNome = new JTextField();
        pegaSenha = new JTextField();

        int x = (int)(WIDTH*0.05);
        int y = (int)(HEIGHT*0.4);
        
        int y1 = (int)(HEIGHT*0.56);

        pegaNome.setBounds(x, y, 300, 20);
        pegaNome.setOpaque(false);
        pegaNome.setBorder(null);
        pegaSenha.setBounds(x, y1, 300, 20);
        pegaSenha.setOpaque(false);
        pegaSenha.setBorder(null);

    }
    public void configButton(){

        int x = (int)(WIDTH*0.05);
        int y = (int)(HEIGHT*0.72);

        entrar = new JButton();
        entrar.setBounds(x, y, 310, 30);
        entrar.setContentAreaFilled(false);
        entrar.setBorderPainted(false);

        entrar.addActionListener(this);
    }
    public void animacao(){
        
        int pausa = 200;

        painel.setCaminho("./src/resources/animacaologin1.jpg");
        pausaMili(pausa);
        painel.setCaminho("./src/resources/animacaologin2.jpg");
        pausaMili(pausa);
        painel.setCaminho("./src/resources/animacaologin3.jpg");
        pausaMili(pausa);
        painel.setCaminho("./src/resources/animacaologin4.jpg");
        pausaMili(pausa);
        painel.setCaminho("./src/resources/animacaologin5.jpg");

    
    }
    public void pausaMili(int pausa){
        try {
            TimeUnit.MILLISECONDS.sleep(pausa);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        animacao();
    }
}