package jcombobox;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class ExemploJComboBox extends JPanel {

    public ExemploJComboBox() {
        String[] itens = new String[] { "Escolha 1", "Escolha 2" };
        JComboBox<String> comboBox = new JComboBox<String>(itens);
        add(comboBox);
    }

}
