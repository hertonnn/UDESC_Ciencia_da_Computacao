package layouts;

import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ExemploCardLayout extends JFrame {

    private CardLayout cardLayout = new CardLayout();
    private JPanel painelCard = new JPanel(cardLayout);
    private JButton botao = new JButton("Trocar");

    public ExemploCardLayout() {

        setTitle("Exemplo");
        setSize(300, 300);
        setLocation(50, 50);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(painelCard, BorderLayout.CENTER);
        add(botao, BorderLayout.SOUTH);

        JPanel vermelho = new JPanel();
        vermelho.setBackground(Color.RED);
        painelCard.add(vermelho);

        JPanel azul = new JPanel();
        azul.setBackground(Color.blue);
        painelCard.add(azul);

        botao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                cardLayout.next(painelCard);
            }
        });

    }

    public static void main(String[] args) {
        ExemploCardLayout exemplo = new ExemploCardLayout();
        exemplo.setVisible(true);
    }

}