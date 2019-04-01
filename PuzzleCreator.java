/*
   This 



*/

import java.io.File;
import java.io.IOException;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")

public class PuzzleCreator extends JFrame{

   
   private static File exterior;
   private static File wall;
   private static File interior;
   private static File player;
   private static File target;
   private static File box;
   private static File boxTarget;
   private static File playerTarget;
   private static BufferedImage exteriorImage;
   private static BufferedImage wallImage;
   private static BufferedImage interiorImage;
   private static BufferedImage playerImage;
   private static BufferedImage targetImage;
   private static BufferedImage boxImage;
   private static BufferedImage boxTargetImage;
   private static BufferedImage playerTargetImage;
   
   public static void display()
   {
      
      //Creates the outter window
      //Allows for the use to enter the height and width for the
      //puzzle they want to create
      int height = 300;
      int width = 350;
      
      JFrame frame = new JFrame("Puzzle Creator: Dimensions");    
    
      JLabel prompt = new JLabel("Enter the dimensions of your puzzle: ");
      JLabel prompt1 = new JLabel("Must be at least a 3x5 or 5x3 ");
      JLabel prompt2 = new JLabel("Click on \"Done\" to create your new puzzle");
      prompt.setBounds(20, 2, 400, 100);   
      prompt1.setBounds(20, 13, 400, 100);
      prompt2.setBounds(20, 24, 400, 100);  
      
      JLabel widthPrompt = new JLabel("Width: ");
      widthPrompt.setBounds(20, 75, 200, 100);
      
      JLabel heightPrompt = new JLabel("Height: ");
      heightPrompt.setBounds(20, 150, 200, 100);
      
      JButton button =new JButton("Done");   
      button.setBounds(30,250,140,40);
      
      JTextField widthEntry = new JTextField();
      widthEntry.setBounds(90, 100, 100, 50);
      
      JTextField heightEntry = new JTextField();
      heightEntry.setBounds(90, 175, 100, 50);
      
      frame.setVisible(true);
      frame.add(prompt);
      frame.add(prompt1);
      frame.add(prompt2);
      frame.add(widthPrompt);
      frame.add(heightPrompt);
      frame.add(widthEntry);      
      frame.add(heightEntry);
      frame.add(button);
      frame.setLayout(null);    
      frame.setSize(height,width);
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      button.addActionListener( new ActionListener(){
          
         //Once the button is clicked the next window is opened 
         //the current one is closed
         @Override
	      public void actionPerformed(ActionEvent arg0)
         {

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
            
   	    prompt.setText("Creating your puzzle");
            frame.setVisible(false);	

         //Reading the values inputted from the user
         String nHeight = heightEntry.getText();      
         String nWidth = widthEntry.getText();
         
         //Converts the input string into an integer
         int nh = Integer.parseInt(nHeight);
         int nw = Integer.parseInt(nWidth);
         
         //Based on the values, they are made bigger to create the next window to the fitted size
         int newHeight = nh * 100;
         int newWidth = nw * 100;
             
         JFrame puzz = new JFrame("Puzzle Creator: Choosing Blocks");  
         puzz.addWindowStateListener(new WindowStateListener() {

            @Override
            public void windowStateChanged(WindowEvent arg0) {
               System.out.println(arg0);
               
            }
            
         });  
         puzz.setLayout(null);    
         puzz.setSize(newWidth,newHeight);
         puzz.setVisible(true);
         puzz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	         

         //Creates the grid of buttons
         puzz.setLayout(new GridLayout(nh, nw));
         for (int i = 0; i < (nh*nw); i++) {
            JPanel but = new JPanel();
            CardLayout buttons = new CardLayout();
            EditorButtons ext = new EditorButtons(exteriorImage);
            EditorButtons wal = new EditorButtons(wallImage);
            EditorButtons inter = new EditorButtons(interiorImage);
            EditorButtons pl = new EditorButtons(playerImage);
            EditorButtons bo = new EditorButtons(boxImage);
            EditorButtons tar = new EditorButtons(targetImage);
            EditorButtons bt = new EditorButtons(boxTargetImage);
            EditorButtons pt = new EditorButtons(playerTargetImage);
            but.setLayout(buttons);
            but.add(ext, "0");
            but.add(wal, "1");
            but.add(inter, "2");
            but.add(pl, "3");
            but.add(bo, "4");
            but.add(tar, "5");
            but.add(bt, "6");
            but.add(pt, "7");
            puzz.add(but);
            buttons.first(but);
            
            but.addMouseListener(new MouseListener() {

               @Override
               public void mouseClicked(MouseEvent arg0) {
                  
                  buttons.next(but);
                  
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

            } // end for

         }// ends ActionPreformed
         
      });// ends button.actionListener
   }// end display

}
