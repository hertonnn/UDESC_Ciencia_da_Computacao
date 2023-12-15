package resources.jcustons;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;


import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class JPanelCustom extends JPanel {
    
    private String caminho;
    public JPanelCustom(String caminhoImagem){
        caminho = caminhoImagem;
    }

    public void setCaminho(String novoCaminho){
        caminho = novoCaminho;
        paintComponent(getGraphics());
    }
    public void setCor(String cor) {
        paintComponent(getGraphics());
    }

    @Override
    protected void paintComponent(Graphics g){
        try{
            Graphics2D grafico2d = (Graphics2D) g;
            //grafico2d.setBackground(Color.decode(this.cor));
            BufferedImage bufferedImage = ImageIO.read(new FileInputStream(caminho));
            Image imagemRedim = bufferedImage.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
            grafico2d.drawImage(imagemRedim, 0, 0, this);
        }catch(Exception e){
            System.err.println(e);
        }
    }
}
