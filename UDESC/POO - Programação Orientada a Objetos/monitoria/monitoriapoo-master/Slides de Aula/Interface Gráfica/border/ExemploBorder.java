package border;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

public class ExemploBorder extends JPanel {

    public ExemploBorder() {
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK);
        setBorder(BorderFactory.createTitledBorder(lineBorder, "Exemplo de borda"));
    }

}