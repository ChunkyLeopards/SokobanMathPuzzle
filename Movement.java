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
            
                     if (Validation.checkSolved(p)) {
                        
                        SokobanEvents.win();
                        
                     }
                     
                     else if (!Validation.checkIndentHor(p) || !Validation.checkIndentVert(p) || !Validation.checkStructure(p)) {
                        
                        SokobanEvents.stuck();
                        
                     }
                     
                     break;
                     
            case 38: p.move(SokobanRuntimeStorage.UP);
            
                     if (Validation.checkSolved(p)) {
               
                        SokobanEvents.win();
                  
                     }
                     
                     else if (!Validation.checkIndentHor(p) || !Validation.checkIndentVert(p) || !Validation.checkStructure(p)) {
                        
                        SokobanEvents.stuck();
                        
                     }
                     
                     break;
                     
            case 39: p.move(SokobanRuntimeStorage.RIGHT);
            
                     if (Validation.checkSolved(p)) {
               
                        SokobanEvents.win();
               
                     }
                     
                     else if (!Validation.checkIndentHor(p) || !Validation.checkIndentVert(p) || !Validation.checkStructure(p)) {
                        
                        SokobanEvents.stuck();
                        
                     }
                     
                     break;
            
            case 40: p.move(SokobanRuntimeStorage.DOWN);
            
                     if (Validation.checkSolved(p)) {
               
                        SokobanEvents.win();
               
                     }
                     
                     else if (!Validation.checkIndentHor(p) || !Validation.checkIndentVert(p) || !Validation.checkStructure(p)) {
                        
                        SokobanEvents.stuck();
                        
                     }
                     
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