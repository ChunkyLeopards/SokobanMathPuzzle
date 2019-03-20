
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Movement {
   
   public Movement(JFrame area, SokobanRuntimeStorage p) {
      
      area.addKeyListener(new KeyListener() {

         @Override
         public void keyPressed(KeyEvent key) {
            
            switch (key.getKeyCode()) {
            
            case 37: p.moveLeft();
                     break;
                     
            case 38: p.moveUp();
                     break;
                     
            case 39: p.moveRight();
                     break;
            
            case 40: p.moveDown();
                     break;
                     
            default:
            
            }
            
            area.repaint();
            
         }

         @Override
         public void keyReleased(KeyEvent key) {
            
         }

         @Override
         public void keyTyped(KeyEvent key) {
            
         }
         
      });
      
   }

}