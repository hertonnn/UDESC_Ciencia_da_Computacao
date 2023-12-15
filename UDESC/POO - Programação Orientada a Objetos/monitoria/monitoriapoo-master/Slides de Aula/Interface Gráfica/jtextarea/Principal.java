package jtextarea;

import java.awt.Color;

import javax.swing.JFrame;

public class Principal extends JFrame {

    public Principal() {
        setTitle("Exemplo");
        setBounds(50, 50, 300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.BLACK);
        setLayout(null);
    }

    public static void main(String[] args) {

        Principal exemplo = new Principal();
        exemplo.add(new ExemploJTextArea());
        exemplo.setVisible(true);

    }
}