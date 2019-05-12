import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Movement {

   private boolean isMoveable;
   private boolean ctrl;

   public Movement(JFrame area, SokobanRuntimeStorage p, JPanel input, MathPanel math) {

      isMoveable = true;

      area.addKeyListener(new KeyListener() {

         @Override
         public void keyPressed(KeyEvent key) {

            if (isMoveable) {
               switch (key.getKeyCode()) {
               case 17:
                  ctrl = true;
                  break;
               case 37:
                  p.move(SokobanRuntimeStorage.LEFT);

                  if (Validation.checkSolved(p)) {

                     input.setVisible(true);
                     math.getTextFocus();
                     isMoveable = false;

                  }

                  else if (/*!Validation.checkIndentHor(p) || !Validation.checkIndentVert(p)
                        ||*/ !Validation.checkStructure(p)) {

                     SokobanEvents.stuck();

                  }

                  break;

               case 38:
                  p.move(SokobanRuntimeStorage.UP);

                  if (Validation.checkSolved(p)) {

                     input.setVisible(true);
                     math.getTextFocus();
                     isMoveable = false;

                  }

                  else if (/*!Validation.checkIndentHor(p) || !Validation.checkIndentVert(p)
                        ||*/ !Validation.checkStructure(p)) {

                     SokobanEvents.stuck();

                  }

                  break;

               case 39:
                  p.move(SokobanRuntimeStorage.RIGHT);

                  if (Validation.checkSolved(p)) {

                     input.setVisible(true);
                     math.getTextFocus();
                     isMoveable = false;

                  }

                  else if (/*!Validation.checkIndentHor(p) || !Validation.checkIndentVert(p)
                        ||*/ !Validation.checkStructure(p)) {

                     SokobanEvents.stuck();

                  }

                  break;

               case 40:
                  p.move(SokobanRuntimeStorage.DOWN);

                  if (Validation.checkSolved(p)) {

                     input.setVisible(true);
                     math.getTextFocus();
                     isMoveable = false;

                  }

                  else if (/*!Validation.checkIndentHor(p) || !Validation.checkIndentVert(p)
                        ||*/ !Validation.checkStructure(p)) {

                     SokobanEvents.stuck();

                  }

                  break;

               case 90:
                  if (ctrl) {
                     p.undo();
                  }
                  break;

               default: // System.out.println(key);

               }

               area.repaint();

            }

         }

         @Override
         public void keyReleased(KeyEvent key) {
            switch (key.getKeyCode()) {
            case 17:
               ctrl = false;
               break;
            }
         }

         @Override
         public void keyTyped(KeyEvent key) {

         }

      });

   }

   public Movement(JFrame area, SokobanRuntimeStorage p) {

      isMoveable = true;

      area.addKeyListener(new KeyListener() {

         @Override
         public void keyPressed(KeyEvent key) {

            if (isMoveable) {
               switch (key.getKeyCode()) {

               case 17:

                  ctrl = true;
                  break;

               case 37:
                  p.move(SokobanRuntimeStorage.LEFT);

                  if (Validation.checkSolved(p)) {

                     isMoveable = false;
                     p.setSolvable();
                     area.dispose();

                  }

                  else if (/*!Validation.checkIndentHor(p) || !Validation.checkIndentVert(p)
                        ||*/ !Validation.checkStructure(p)) {

                     SokobanEvents.stuck();

                  }

                  break;

               case 38:
                  p.move(SokobanRuntimeStorage.UP);

                  if (Validation.checkSolved(p)) {

                     isMoveable = false;
                     p.setSolvable();
                     area.dispose();

                  }

                  else if (/*!Validation.checkIndentHor(p) || !Validation.checkIndentVert(p)
                        ||*/ !Validation.checkStructure(p)) {

                     SokobanEvents.stuck();

                  }

                  break;

               case 39:
                  p.move(SokobanRuntimeStorage.RIGHT);

                  if (Validation.checkSolved(p)) {

                     isMoveable = false;
                     p.setSolvable();
                     area.dispose();

                  }

                  else if (/*!Validation.checkIndentHor(p) || !Validation.checkIndentVert(p)
                        ||*/ !Validation.checkStructure(p)) {

                     SokobanEvents.stuck();

                  }

                  break;

               case 40:
                  p.move(SokobanRuntimeStorage.DOWN);

                  if (Validation.checkSolved(p)) {

                     isMoveable = false;
                     p.setSolvable();
                     area.dispose();

                  }

                  else if (/*!Validation.checkIndentHor(p) || !Validation.checkIndentVert(p)
                        ||*/ !Validation.checkStructure(p)) {

                     SokobanEvents.stuck();

                  }

                  break;

               case 90:
                  if (ctrl) {
                     p.undo();
                  }
                  break;

               default: // System.out.println(key);

               }

               area.repaint();

            }

         }

         @Override
         public void keyReleased(KeyEvent key) {
            switch (key.getKeyCode()) {
            case 17:
               ctrl = false;
               break;
            }
         }

         @Override
         public void keyTyped(KeyEvent key) {

         }

      });

   }

}