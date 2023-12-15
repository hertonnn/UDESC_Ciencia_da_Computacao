package resources.classes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class RendererTabela extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component rendererComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            // Define cores diferentes para linhas pares e ímpares
            rendererComponent.setCursor(new Cursor(Cursor.HAND_CURSOR));
            if(isSelected){
                rendererComponent.setBackground(Color.decode("#D9D9D9"));
            }else{
                rendererComponent.setBackground(Color.WHITE);
            }
            setHorizontalAlignment(SwingConstants.CENTER);
            return rendererComponent;
}
}
