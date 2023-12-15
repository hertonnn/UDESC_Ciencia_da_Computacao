package layouts;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

class ExemploBorderLayout extends JFrame {

    public ExemploBorderLayout() {
        setTitle("Exemplo");
        setSize(300, 300);
        setLocation(50, 50);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    public static void main(String[] args) {

        ExemploBorderLayout exemplo = new ExemploBorderLayout();
        exemplo.setVisible(true);

        JButton botao1 = new JButton("Botão 1");
        exemplo.add(botao1, BorderLayout.NORTH);

        JButton botao2 = new JButton("Botão 2");
        exemplo.add(botao2, BorderLayout.SOUTH);

        JButton botao3 = new JButton("Botão 3");
        exemplo.add(botao3, BorderLayout.EAST);

        JButton botao4 = new JButton("Botão 4");
        exemplo.add(botao4, BorderLayout.WEST);

        JButton botao5 = new JButton("Botão 5");
        exemplo.add(botao5, BorderLayout.CENTER);

    }

}