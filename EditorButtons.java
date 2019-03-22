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
   private int track;
   
   public EditorButtons(File icon, int t) {
      
      try {
         image = ImageIO.read(icon);
      } catch (IOException e) {
         e.printStackTrace();
      }
      
      track = t;
      
   }
   
   @Override
   public void paintComponent(Graphics g) {
      
      g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), Color.white, null);
      
   }
   
   public int getTrack() {
      return track;
   }

}
