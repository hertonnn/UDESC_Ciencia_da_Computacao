package jtabbedpane;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class ExemploJTabbedPane extends JTabbedPane {

    public ExemploJTabbedPane() {
        this.addTab("Aba 1", new JPanel());
        this.addTab("Aba 2", new JPanel());
        this.addTab("Aba 3", new JPanel());
    }

}
