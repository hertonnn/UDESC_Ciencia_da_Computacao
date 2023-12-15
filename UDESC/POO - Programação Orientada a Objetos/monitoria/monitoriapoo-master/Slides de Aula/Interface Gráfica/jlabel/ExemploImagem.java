package jlabel;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ExemploImagem extends JPanel {

    public ExemploImagem() {

        JLabel imagem = new JLabel(new ImageIcon("jlabel/udesc.png"));
        add(imagem);

    }

}