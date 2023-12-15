package jlist;

import javax.swing.JList;
import javax.swing.JPanel;

public class ExemploJList extends JPanel {

    public ExemploJList() {
        String[] array = new String[] { "Item 1", "Item 2", "Item 3" };
        JList<String> jList = new JList<String>(array);
        add(jList);
    }

}
