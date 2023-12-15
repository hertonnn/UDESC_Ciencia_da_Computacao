package joptionpane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ExemploJOptionPane extends JPanel {

    public ExemploJOptionPane() {
        JButton button = new JButton("Pressione");
        add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                JOptionPane.showMessageDialog(null, "O botao foi pressionado", "Botao Pressionado",
                        JOptionPane.INFORMATION_MESSAGE);

            }
        });
    }
}