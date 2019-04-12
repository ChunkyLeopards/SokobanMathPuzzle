import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class EditorButtons extends JPanel {

   private BufferedImage image = null;
   
   public EditorButtons(BufferedImage icon) {
      
      image = icon;
      
   }
   
   @Override
   public void paintComponent(Graphics g) {
      
      g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), Color.white, null);
      
   }

}
