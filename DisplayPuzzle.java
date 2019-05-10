import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * Displays a Sokoban puzzle.
 * @author Daniel Moore
 */

@SuppressWarnings("serial")
public class DisplayPuzzle extends JPanel{
   
   private SokobanRuntimeStorage puzzle;
   private static JFrame window;
   private static JMenuBar menuBar;
   private static JMenu menu;
   private static JLabel moves;
   private static JLabel time;
   private static JMenuItem goMain;
   private static JMenuItem options;
   private static JMenuItem exit;
   private static JMenuItem overview;
   private static JMenuItem toCreator;
   private static MathPanel mathP;
   private static JPanel math;
   private static JPanel input;
   private static BufferedImage exteriorImage;
   private static BufferedImage wallImage;
   private static BufferedImage interiorImage;
   private static BufferedImage playerImage;
   private static BufferedImage targetImage;
   private static BufferedImage boxImage;
   private static BufferedImage boxTargetImage;
   private static BufferedImage playerTargetImage;
   private static File exterior;
   private static File wall;
   private static File interior;
   private static File player;
   private static File target;
   private static File box;
   private static File boxTarget;
   private static File playerTarget;
   
   /**
    * Creates a display object from a Sokoban puzzle.
    * @param p The runtime storage of a puzzle.
    */
   
   public DisplayPuzzle(SokobanRuntimeStorage p) {
      
      puzzle = p;
      
   }
   
   /**
    * Method to manage and display a window.
    * @param p The runtime storage of a puzzle.
    */
   
   public static JFrame displayWindow(SokobanRuntimeStorage p, boolean test) {
      
      exterior = new File("data/emptyOS.png");
      wall = new File("data/wall.png");
      interior = new File("data/emptyIS.png");
      player = new File("data/player.png");
      target = new File("data/target.png");
      box = new File("data/box.png");
      boxTarget = new File("data/boxTarget.png");
      playerTarget = new File("data/playerTarget.png");

      try {
         exteriorImage = ImageIO.read(exterior);
         wallImage = ImageIO.read(wall);
         interiorImage = ImageIO.read(interior);
         playerImage = ImageIO.read(player);
         targetImage = ImageIO.read(target);
         boxImage = ImageIO.read(box);
         boxTargetImage = ImageIO.read(boxTarget);
         playerTargetImage = ImageIO.read(playerTarget);
      } catch (IOException e) {
         e.printStackTrace();
      }
      window = new JFrame(p.getName());
      window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.PAGE_AXIS));
      menuBar = new JMenuBar();
      menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.LINE_AXIS));
      window.setUndecorated(true);
      menu = new JMenu("Menu");
      goMain = new JMenuItem("Return to Main Menu");
      
      goMain.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent arg0) {
               
            window.dispose();
            MainMenu.reopenMainMenu();
            
         }
         
      });

      toCreator = new JMenuItem("Return to Creator");
      
      toCreator.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            
            window.dispose();
            
         }
         
      });
      
      options = new JMenuItem("Options");
      
      overview = new JMenuItem("View Puzzle");
      
      exit = new JMenuItem("Exit");
      
      exit.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent arg0) {
            
            System.exit(0);
            
         }
         
      });
      
      moves = new JLabel("Moves: XX");
      time = new JLabel("Time Elapsed: XX:XX");
      window.add(menuBar);
      menu.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
      if(!test) {
         menu.add(goMain);
         menu.add(options);
         menu.add(overview);
         menu.add(exit);
         menuBar.add(moves);
         menuBar.add(Box.createRigidArea(new Dimension(10, 0)));
         menuBar.add(time);
         mathP = new MathPanel();
         math = mathP.createMath();
         window.add(math);
         input = mathP.createInput();
         input.setVisible(false);
         window.add(input);
      }
      else { 
         menu.add(toCreator);
         menu.add(exit);
      }
      menuBar.add(Box.createHorizontalGlue());
      menuBar.add(menu);
      window.add(new DisplayPuzzle(p));
      window.pack();
      window.setVisible(true);
      if(test) {
         new Movement(window, p);
      }
      else {
         new Movement(window, p, input, mathP);
      }
      
      return window;
      
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
      short square;
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
            
            switch ((byte)square) {
            
            case SokobanInterpreter.EXTERNAL:
               g.drawImage(exteriorImage, squareX, squareY, squareDim, squareDim, null);
               break;            
            
            case SokobanInterpreter.WALL:
               
               g.drawImage(wallImage, squareX, squareY, squareDim, squareDim, null);
               break;
               
            case SokobanInterpreter.INTERNAL:

               g.drawImage(interiorImage, squareX, squareY, squareDim, squareDim, null);
               break;
               
            case SokobanInterpreter.INTERNAL_PLAYER:

               g.drawImage(playerImage, squareX, squareY, squareDim, squareDim, null);
               break;
               
            case SokobanInterpreter.INTERNAL_BOX:

               g.drawImage(boxImage, squareX, squareY, squareDim, squareDim, null);
               break;
               
            case SokobanInterpreter.INTERNAL_TARGET:

               g.drawImage(targetImage, squareX, squareY, squareDim, squareDim, null);
               break;
               
            case SokobanInterpreter.INTERNAL_PLAYER_TARGET:

               g.drawImage(playerTargetImage, squareX, squareY, squareDim, squareDim, null);
               break;
               
            case SokobanInterpreter.INTERNAL_BOX_TARGET:

               g.drawImage(boxTargetImage, squareX, squareY, squareDim, squareDim, null);
               break;
               
            default:
            
            }
            
            squareX += squareDim;
            
         }
         
         squareY += squareDim;
         
      }
      
   }

}