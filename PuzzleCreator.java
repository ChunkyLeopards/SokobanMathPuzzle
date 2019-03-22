/*
   This 



*/

import java.io.File;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
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

   private static EditorButtons b;
   
   public static void display()
   {
      //Creates the outter window
      //Allows for the use to enter the height and width for the
      //puzzle they want to create
      int height = 300;
      int width = 350;
      
      JFrame frame = new JFrame("Puzzle Creator: Dimensions");    
    
      JLabel prompt = new JLabel("Enter the dimensions of your puzzle:\n Must be at least a 3x5 or 5x3 ");
      prompt.setBounds(20, 15, 400, 100);   
      
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
            b = new EditorButtons(exterior, 0);
            puzz.add(b);            
            
            b.addMouseListener(new MouseListener() {

               @Override
               public void mouseClicked(MouseEvent arg0) {
                  switch(b.getTrack()) {

                  case 0:
                     b = new EditorButtons(wall, 1);
                     break;
                  case 1:
                     b = new EditorButtons(interior, 2);
                     break;
                  case 2:
                     b = new EditorButtons(player, 3);
                     break;
                  case 3:
                     b = new EditorButtons(box, 4);
                     break;
                  case 4:
                     b = new EditorButtons(target, 5);
                     break;
                  case 5:
                     b = new EditorButtons(playerTarget, 6);
                     break;
                  case 6:
                     b = new EditorButtons(boxTarget, 7);
                     break;
                  case 7:
                     b = new EditorButtons(exterior, 0);
                     break;
                  default:
                  }
                  
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

         }//end for
         
         }// ends ActionPreformed
	    });//ends button.actionListener
      }//end display

 
}