package jframe;

import javax.swing.JFrame;

class ExemploJFrame extends JFrame {

    public ExemploJFrame() {
        setTitle("Exemplo");
        setBounds(50, 50, 300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    public static void main(String[] args) {
        ExemploJFrame exemplo = new ExemploJFrame();
        exemplo.setVisible(true);
    }

}