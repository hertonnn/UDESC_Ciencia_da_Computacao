package resources.jcustons;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class JButtonCustom extends JButton{

    int TAMX;
    int TAMY;
    // Construtor para botao sem imagem
    public JButtonCustom(){
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    // Com imagem
    public JButtonCustom(String caminho, int TAMX, int TAMY){
        this.TAMX = TAMX;
        this.TAMY = TAMY;

        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setContentAreaFilled(false);
        setBorderPainted(false);
        setBounds(0,0,TAMX,TAMY);
        
        try{            
            BufferedImage bufferedImage = ImageIO.read(new FileInputStream(caminho));
            Image imagemRedim = bufferedImage.getScaledInstance(TAMX, TAMY, Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(imagemRedim));
        } catch(Exception ex){
            System.out.println(ex);
        }
    }
    public void setIcon(String caminho){
        try{            
            BufferedImage bufferedImage = ImageIO.read(new FileInputStream(caminho));
            Image imagemRedim = bufferedImage.getScaledInstance(TAMX, TAMY, Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(imagemRedim));
        } catch(Exception ex){
            System.out.println(ex);
        }
    }
}
