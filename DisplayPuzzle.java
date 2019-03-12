import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
public class DisplayPuzzle extends JPanel{
   
   private SokobanRuntimeStorage puzzle;
   private static JFrame window;
   
   public DisplayPuzzle(SokobanRuntimeStorage p) {
      
      puzzle = p;
      
   }
   
   public static void displayWindow(SokobanRuntimeStorage p) {
      
      window = new JFrame(p.getName());
      window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      window.add(new DisplayPuzzle(p));
      window.pack();
      window.setVisible(true);
      new Movement(window, p);
      
      /*while(true) {
         
         window.repaint();
         
      }*/
      
   }
   
   @Override
   public Dimension getPreferredSize() {
      
      return new Dimension(500, 500);
      
   }
   
   @Override
   public void paintComponent(Graphics g) {
      
      super.paintComponent(g);
      g.setColor(new Color(0, 0, 0));
      
      /*
       * The bytes stored are decimal conversions of binary numbers.
       * The binary numbers are 5 digits long.
       * From smallest to largest, the bits are:
       * Wall
       * Internal space
       * Player
       * Box
       * Target
       * 
       * A 0 represents no element, while a 1 represents an element of the appropriate type.
       * Not all combinations are allowed. Input interpreter should catch invalid square contents.
       * 
       * A byte value of 0 represents external space.
       */
      
      int width = puzzle.getWidth();
      int height = puzzle.getHeight();
      byte square;
      int drawableX = (int) getSize().getWidth();
      int drawableY = (int) getSize().getHeight();
      int offset = 10;
      int squareX;
      int squareY = offset;
      int squareDim = Math.min((drawableY - (offset * 2)) / height, (drawableX - (offset * 2)) / width);
      
      for (int i = 0; i < height; i++) {
         
         squareX = offset;
         
         for (int j = 0; j < width; j++) {
            
            square = puzzle.getValue(j, i);
            
            switch (square) {
            
            case 1:
               
               /* Wall */
               g.setColor(new Color(0, 0, 0));
               g.drawRect(squareX, squareY, squareDim, squareDim);
               g.drawLine(squareX, squareY + squareDim / 2, squareX + squareDim, squareY + squareDim / 2);
               g.drawLine(squareX + squareDim / 2, squareY, squareX + squareDim / 2, squareY + squareDim);
               break;
               
            case 2:
               
               /* Internal Space */
               g.setColor(new Color(0, 200, 0));
               g.drawOval(squareX + squareDim / 2 - 3, squareY + squareDim / 2 - 3, 7, 7);
               break;
               
            case 6:
               
               /* Internal Space and Player */
               g.setColor(new Color(0, 200, 0));
               g.drawOval(squareX + squareDim / 2 - 3, squareY + squareDim / 2 - 3, 7, 7);
               g.setColor(new Color(0, 0, 200));
               g.drawOval(squareX, squareY, squareDim, squareDim);
               break;
               
            case 10:
               
               /* Internal Space and Box */
               g.setColor(new Color(0, 200, 0));
               g.drawOval(squareX + squareDim / 2 - 3, squareY + squareDim / 2 - 3, 7, 7);
               g.setColor(new Color(200, 150, 0));
               g.drawRect(squareX + 2, squareY + 2, squareDim - 4, squareDim - 4);
               break;
               
            case 18:
               
               /* Internal Space and Target */
               g.setColor(new Color(0, 200, 0));
               g.drawOval(squareX + squareDim / 2 - 3, squareY + squareDim / 2 - 3, 7, 7);
               g.setColor(new Color(255, 0, 0));
               g.drawLine(squareX, squareY, squareX + squareDim, squareY + squareDim);
               g.drawLine(squareX, squareY + squareDim, squareX + squareDim, squareY);
               break;
               
            case 22:
               
               /* Internal Space and Player and Target */
               g.setColor(new Color(0, 200, 0));
               g.drawOval(squareX + squareDim / 2 - 3, squareY + squareDim / 2 - 3, 7, 7);
               g.setColor(new Color(255, 0, 0));
               g.drawLine(squareX, squareY, squareX + squareDim, squareY + squareDim);
               g.drawLine(squareX, squareY + squareDim, squareX + squareDim, squareY);
               g.setColor(new Color(0, 0, 200));
               g.drawOval(squareX, squareY, squareDim, squareDim);
               break;
               
            case 26:
               
               /* Internal Space and Box and Target */
               g.setColor(new Color(0, 200, 0));
               g.drawOval(squareX + squareDim / 2 - 3, squareY + squareDim / 2 - 3, 7, 7);
               g.setColor(new Color(255, 0, 0));
               g.drawLine(squareX, squareY, squareX + squareDim, squareY + squareDim);
               g.drawLine(squareX, squareY + squareDim, squareX + squareDim, squareY);
               g.setColor(new Color(200, 150, 0));
               g.drawRect(squareX + 2, squareY + 2, squareDim - 4, squareDim - 4);
               break;
               
            default:
            
            }
            
            squareX += squareDim;
            
         }
         
         squareY += squareDim;
         
      }
      
   }

}