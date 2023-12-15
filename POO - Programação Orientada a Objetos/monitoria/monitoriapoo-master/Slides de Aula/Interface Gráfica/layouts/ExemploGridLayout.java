package layouts;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.GridLayout;

public class ExemploGridLayout extends JFrame {

    public ExemploGridLayout() {
        setTitle("Exemplo");
        setSize(300, 300);
        setLocation(50, 50);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 3));
    }

    public static void main(String[] args) {

        ExemploGridLayout exemplo = new ExemploGridLayout();
        exemplo.setVisible(true);

        JButton botao1 = new JButton("Botão 1");
        exemplo.add(botao1);

        JButton botao2 = new JButton("Botão 2");
        exemplo.add(botao2);

        JButton botao3 = new JButton("Botão 3");
        exemplo.add(botao3);

        JButton botao4 = new JButton("Botão 4");
        exemplo.add(botao4);

        JButton botao5 = new JButton("Botão 5");
        exemplo.add(botao5);

    }

}