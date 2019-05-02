import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

public class Input {
   
   private JPanel display;
   private JLabel displayLabel;
   private LaTeXInputRepresentation formula;
   private boolean shift;
   private boolean capsLock;
   
   public Input() {
      
      capsLock = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
      formula = new LaTeXInputRepresentation();
      display = new JPanel();
      display.setFocusable(true);
      TeXFormula form = new TeXFormula(formula.buildLaTeX());
      TeXIcon icon = form.new TeXIconBuilder().setStyle(TeXConstants.STYLE_DISPLAY).setSize(20)
            .setWidth(TeXConstants.UNIT_PIXEL, 256f, TeXConstants.ALIGN_CENTER).setIsMaxWidth(true)
            .setInterLineSpacing(TeXConstants.UNIT_PIXEL, 20f).build();
      displayLabel = new JLabel();
      displayLabel.setIcon(icon);
      display.add(displayLabel);
      display.addMouseListener(new MouseListener() {

         @Override
         public void mouseClicked(MouseEvent arg0) {

            display.requestFocus();
            System.out.println("in focus");
            
         }

         @Override
         public void mouseEntered(MouseEvent arg0) {
            // TODO Auto-generated method stub
            
         }

         @Override
         public void mouseExited(MouseEvent arg0) {
            // TODO Auto-generated method stub
            
         }

         @Override
         public void mousePressed(MouseEvent arg0) {
            // TODO Auto-generated method stub
            
         }

         @Override
         public void mouseReleased(MouseEvent arg0) {
            // TODO Auto-generated method stub
            
         }
         
      });
      display.addKeyListener(new KeyListener() {

         @Override
         public void keyPressed(KeyEvent arg0) {
            char c = arg0.getKeyChar();
            int in = arg0.getKeyCode();
            switch(in) {
            case 8: //backspace
               formula.remove(false);
               break;
            case 127: //delete
               formula.remove(true);
               break;
            case 10: //return/enter
               break;
            case 16: //shift
               shift = true;
               break;
            case 20: //capslock
               capsLock = !capsLock;
               break;
            case 32: //space
               break;
            case 35: //end
               formula.moveEnd(shift);
               break;
            case 36: //home
               formula.moveHome(shift);
               break;
            case 37: //arrowLeft
               formula.moveCursorLeft(shift);
               break;
            case 38: //arrowUp
               break;
            case 39: //arrowRight
               formula.moveCursorRight(shift);
               break;
            case 40: //arrowDown
               break;
               //special cases
            case 54: //^
               if(shift ^ capsLock) {
                  formula.addFormula(Character.toUpperCase(c) + "{}");
               }
               else {
                  formula.addChar(c);
               }
               break;
            case 192: //`
               if(shift ^ capsLock) {
                  formula.addFormula("\\" + Character.toUpperCase(c) + "{}");
               }
               else {
                  formula.addChar(c);
               }
               break;
            case 45: //_
            case 91: //{
            case 92: //|
            case 93: //}
            case 50: //@ //numbers with special shift cases
            case 52: //$
            case 53: //%
            case 55: //&
               if(shift ^ capsLock) {
                  formula.addFormula("\\" + Character.toUpperCase(c) + " ");
               }
               else {
                  formula.addChar(c);
               }
               break;
            case 59: //; 
            case 222: //'
            case 44: //,
            case 46: //.
            case 47: ///
            case 61: //=
            case 48: //numbers handled same as letters
            case 49:
            case 51:
            case 56:
            case 57:
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
               if(shift ^ capsLock) {
                  formula.addChar(Character.toUpperCase(c));
               }
               else {
                  formula.addChar(c);
               }
               break;
            default:
               System.out.println(c);
               System.out.println(in);
            }
            
            TeXFormula form = new TeXFormula(formula.buildLaTeX());
            TeXIcon icon = form.new TeXIconBuilder().setStyle(TeXConstants.STYLE_DISPLAY).setSize(20)
                  .setWidth(TeXConstants.UNIT_PIXEL, 256f, TeXConstants.ALIGN_CENTER).setIsMaxWidth(true)
                  .setInterLineSpacing(TeXConstants.UNIT_PIXEL, 20f).build();
            displayLabel = new JLabel();
            displayLabel.setIcon(icon);
            display.removeAll();
            display.add(displayLabel);
            display.validate();
            display.update(display.getGraphics());
            
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
   
   public JPanel getPanel() {
      
      return display;
      
   }
   
   public void addButtonFormula(String s) {
      
      formula.addFormula(s);
      
      TeXFormula form = new TeXFormula(formula.buildLaTeX());
      TeXIcon icon = form.new TeXIconBuilder().setStyle(TeXConstants.STYLE_DISPLAY).setSize(20)
            .setWidth(TeXConstants.UNIT_PIXEL, 256f, TeXConstants.ALIGN_CENTER).setIsMaxWidth(true)
            .setInterLineSpacing(TeXConstants.UNIT_PIXEL, 20f).build();
      displayLabel = new JLabel();
      displayLabel.setIcon(icon);
      display.removeAll();
      display.add(displayLabel);
      display.validate();
      display.update(display.getGraphics());
      
   }
   
}
