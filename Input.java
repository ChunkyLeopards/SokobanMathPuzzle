import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXFormula.TeXIconBuilder;
import org.scilab.forge.jlatexmath.TeXIcon;

public class Input {
   
   private JPanel display;
   private JLabel displayLabel;
   private JTextField input;
   private LaTeXInputRepresentation formula;
   private boolean shift;
   
   public Input() {
      
      formula = new LaTeXInputRepresentation();
      formula.setActive();
      display = new JPanel();
      display.setFocusable(true);
      TeXFormula form = new TeXFormula(formula.buildLaTeX());
      TeXIcon icon = form.new TeXIconBuilder().setStyle(TeXConstants.STYLE_DISPLAY).setSize(15)
            .setWidth(TeXConstants.UNIT_PIXEL, 256f, TeXConstants.ALIGN_CENTER).setIsMaxWidth(true)
            .setInterLineSpacing(TeXConstants.UNIT_PIXEL, 20f).build();
      displayLabel = new JLabel();
      displayLabel.setIcon(icon);
      display.add(displayLabel);
      display.addKeyListener(new KeyListener() {

         @Override
         public void keyPressed(KeyEvent arg0) {
            char c = arg0.getKeyChar();
            int in = arg0.getKeyCode();
            switch(in) {
            case 8: //backspace
               backspace();
               break;
            case 16: //shift
               shift = true;
               break;
            case 37: //arrowLeft
               moveCursorLeft();
               break;
            case 38: //arrowUp
               moveCursorUp();
               break;
            case 39: //arrowRight
               moveCursorRight();
               break;
            case 40: //arrowDown
               moveCursorDown();
               break;
            case 65: //letters
            case 66:
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
            case 81:
            case 82:
            case 83:
            case 84:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
               if(!shift) {
                  addChar(c);
               }
               else {
                  addChar(Character.toUpperCase(c));
               }
               break;
            default:
               System.out.println(c);
               System.out.println(in);
               //addChar(in);
            }
            
            TeXFormula form = new TeXFormula(formula.buildLaTeX());
            TeXIcon icon = form.new TeXIconBuilder().setStyle(TeXConstants.STYLE_DISPLAY).setSize(15)
                  .setWidth(TeXConstants.UNIT_PIXEL, 256f, TeXConstants.ALIGN_CENTER).setIsMaxWidth(true)
                  .setInterLineSpacing(TeXConstants.UNIT_PIXEL, 20f).build();
            displayLabel = new JLabel();
            displayLabel.setIcon(icon);
            display.removeAll();
            display.add(displayLabel);
            //display.update(display.getGraphics());
            display.validate();
            
         }

         @Override
         public void keyReleased(KeyEvent arg0) {
            
            int in = arg0.getKeyCode();
            switch(in) {
            case 16:
               shift = false;
            }
            
         }

         @Override
         public void keyTyped(KeyEvent arg0) {
            // TODO Auto-generated method stub
            
         }
         
      });
      
   }
   
   public void addChar(char c) {
      
      LaTeXInputRepresentation current = findActive(formula);
      if(current == null) {
         System.err.println("There is a big error here somewhere.");
      }
      else {
         current.addChar(c);
      }
      
   }
   
   public void addFormula(String f) {
      
      System.out.println(f);
      
   }
   
   public void moveCursorLeft() {
      
      LaTeXInputRepresentation current = findActive(formula);
      if(current == null) {
         System.err.println("There is a big error here somewhere.");
      }
      else {
         current.moveCursorLeft();
      }
      
   }
   
   public void moveCursorRight() {
      
      LaTeXInputRepresentation current = findActive(formula);
      if(current == null) {
         System.err.println("There is a big error here somewhere.");
      }
      else {
         current.moveCursorRight();
      }
      
   }
   
   public void moveCursorDown() {
      
      LaTeXInputRepresentation current = findActive(formula);
      if(current == null) {
         System.err.println("There is a big error here somewhere.");
      }
      else {
         current.moveCursorDown();
      }
      
   }
   
   public void moveCursorUp() {
      
      LaTeXInputRepresentation current = findActive(formula);
      if(current == null) {
         System.err.println("There is a big error here somewhere.");
      }
      else {
         current.moveCursorUp();
      }
      
   }
   
   public void backspace() {
      
      /*if(!activeSelection) {
         
         LaTeX = LaTeX.substring(0, possibleCursorLocations[cursorLocationIndex - 1]).concat(LaTeX.substring(possibleCursorLocations[cursorLocationIndex]));
         for(int i = cursorLocationIndex - 1; i < possibleCursorLocations.length - 1; i++) {
            possibleCursorLocations[i] = possibleCursorLocations[i + 1];
         }
         cursorLocationIndex--;
         
      }*/
      
   }
   
   public JPanel getPanel() {
      
      return display;
      
   }
   
   public LaTeXInputRepresentation findActive(LaTeXInputRepresentation head) {
      
      if(head.getActive()) {
         return head;
      }
      else {
         LaTeXInputRepresentation children[] = head.getChildren();
         for(int i = 0; i < children.length; i++) {
            LaTeXInputRepresentation active = findActive(children[i]);
            if(active != null) {
               return active;
            }
         }
      }
      return null;
      
   }
   
}
