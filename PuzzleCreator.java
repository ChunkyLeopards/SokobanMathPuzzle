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
import javax.swing.JScrollPane;
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
      //Creates the first window
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
            
         
         //Converts the input string into an integer
         int nh = Integer.parseInt(heightEntry.getText());
         int nw = Integer.parseInt(widthEntry.getText());
         
         //Based on the values, they are made bigger to create the next window to the fitted size
         int newHeight = nh * 50;
         int newWidth = nw * 50;
         
         if( (nh <= 3 && nw < 5) || (nh < 5 && nw <=3) ){
            prompt.setText("Opps! Values not valid");
         }
         
         else{
            prompt.setText("Creating your puzzle");
            frame.setVisible(false);	    
            int arrayedPuzzle[][] = new int[nh][nw]; 
            secondWindow();
            JFrame puzz = new JFrame("Puzzle Creator: Choosing Blocks");
           
            puzz.addWindowStateListener(new WindowStateListener() {
   
               @Override
               public void windowStateChanged(WindowEvent arg0) {
                  System.out.println(arg0);
                  
               }
               
            });  
            
            //Creating the panes
            JPanel newPanel = new JPanel();
            JScrollPane newScroll = new JScrollPane(newPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            
            //Adding the scrolling
            puzz.add(newScroll);        
            puzz.setSize(newWidth,newHeight);
            puzz.setVisible(true);
            puzz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	         
   
            //Creates the grid of buttons
            newPanel.setLayout(new GridLayout(nh, nw));
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
               newPanel.add(but);
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
   
               }//end for
          }//end else
        }//end ActionPreformed
      });//end button.actionListener
   }//end display
     
   //Creates the second window
   //Allows for the use to select when they are don
   public static void secondWindow(){
   
      int height = 250;
      int width = 350;
      
      JFrame frame = new JFrame("Puzzle Creator: Complete and Verify");    
    
      JLabel prompt = new JLabel("Done with your puzzle? ");
      JLabel prompt1 = new JLabel("Click on \"Done\" to validate, ");
      JLabel prompt2 = new JLabel("and store your puzzle!");
      prompt.setBounds(20, 0, 400, 100);   
      prompt1.setBounds(20, 16, 400, 100);
      prompt2.setBounds(20, 28, 400, 100);  
       
      JButton button =new JButton("Done");   
      button.setBounds(20,100,140,40);
      
      frame.setVisible(true);
      frame.add(prompt);
      frame.add(prompt1);
      frame.add(prompt2);
      frame.add(button);
      frame.setLayout(null);    
      frame.setSize(height,width);
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

   }//end secondWindow
}//end clas
