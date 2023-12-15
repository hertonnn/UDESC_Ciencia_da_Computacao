package resources.jcustons;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;

public class JComboBoxCustom<E> extends JComboBox<String>{
    public JComboBoxCustom(){
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        // Configura o renderizador de itens para centralizá-los
        setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setVerticalAlignment(SwingConstants.VERTICAL);
                return label;
            }
        });
        setBackground(Color.WHITE);
        setForeground(Color.DARK_GRAY);
        setBorder(null);
    }
    public boolean estaVazio(JComboBoxCustom<String> campo) {
        return campo.getSelectedItem() == null;
    }
}
