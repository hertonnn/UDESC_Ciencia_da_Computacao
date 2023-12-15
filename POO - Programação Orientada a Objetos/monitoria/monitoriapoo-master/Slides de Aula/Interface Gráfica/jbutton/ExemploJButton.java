package jbutton;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ExemploJButton extends JPanel {

    public ExemploJButton() {

        JButton button = new JButton("Pressione");
        add(button);

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                trocaCor();
            }

        });
    }

    public void trocaCor() {
        if (getBackground().equals(Color.BLUE)) {
            setBackground(Color.RED);
        } else {
            setBackground(Color.BLUE);
        }
    }

}
