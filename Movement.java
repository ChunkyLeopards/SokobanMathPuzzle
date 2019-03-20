import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Movement {
   
   public Movement(JFrame area, SokobanRuntimeStorage p) {
      
      area.addKeyListener(new KeyListener() {

         @Override
         public void keyPressed(KeyEvent key) {
            
            switch (key.getKeyCode()) {
            
            case 37: p.move(SokobanRuntimeStorage.LEFT);
                     break;
                     
            case 38: p.move(SokobanRuntimeStorage.UP);
                     break;
                     
            case 39: p.move(SokobanRuntimeStorage.RIGHT);
                     break;
            
            case 40: p.move(SokobanRuntimeStorage.DOWN);
                     break;
                     
            case 90: p.undo();
                     break;
                     
            default: //System.out.println(key);
            
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