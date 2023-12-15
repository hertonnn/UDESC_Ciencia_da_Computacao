package jlabel;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

public class ExemploJLabel extends JPanel {

    public ExemploJLabel() {

        setLayout(null);

        JLabel texto = new JLabel("Exemplo de texto");
        texto.setBounds(20, 20, 150, 20);
        texto.setHorizontalAlignment(JLabel.LEFT);
        texto.setBorder(BorderFactory.createLineBorder(Color.black));
        add(texto);

        JLabel texto2 = new JLabel("Exemplo de texto");
        texto2.setBounds(20, 50, 150, 20);
        texto2.setHorizontalAlignment(JLabel.CENTER);
        texto2.setBorder(BorderFactory.createLineBorder(Color.black));
        add(texto2);

        JLabel texto3 = new JLabel("Exemplo de texto");
        texto3.setBounds(20, 80, 150, 20);
        texto3.setHorizontalAlignment(JLabel.RIGHT);
        texto3.setBorder(BorderFactory.createLineBorder(Color.black));
        add(texto3);

    }

}