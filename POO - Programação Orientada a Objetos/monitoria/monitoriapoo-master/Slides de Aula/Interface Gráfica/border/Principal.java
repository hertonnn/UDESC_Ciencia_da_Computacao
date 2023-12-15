package border;

import javax.swing.JFrame;

class Principal extends JFrame {

    public Principal() {
        setTitle("Exemplo");
        setBounds(50, 50, 300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        Principal exemplo = new Principal();
        exemplo.add(new ExemploBorder());
        exemplo.setVisible(true);

    }

}