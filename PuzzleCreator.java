/*
   This file allows the user to create their own puzzle.
   Puzzles are validated and stored when user is complete
   
   To do:
      -Actions to all buttons
      -Fix Spacing
      -Store finished puzzles
      -Set Preview
      -Validation
      
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
import java.applet.Applet;
import java.awt.Font;
import java.awt.*;


import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

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
   
   public static void display(){

      //Max and min allowed for puzzle creation
      int max = 50;
      int min = 5;
   
      //Size of the window
      int width = 800;
      int height = 800;
      
      //Frame setup
      JFrame frame = new JFrame("Puzzle Creator: Dimensions");    
      Font font1 = new Font("SandSerif",Font.PLAIN,16);
      frame.setSize(width,height);
      //frame.setBackground(Color.red);
      
      //Setting up the layout
      frame.setLayout(new GridBagLayout());
      GridBagConstraints c = new GridBagConstraints();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

     
      //Top buttons
      //Submit
      JButton bSubmit =new JButton("Submit"); 
      bSubmit.setFont(font1);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.weightx = 0.5;
      c.gridx = 0;
      c.gridy = 0;
      frame.add(bSubmit,c);
      
      //Test
      JButton bTest =new JButton("Test");  
      bTest.setFont(font1);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.weightx = 0.5;
      c.gridx = 1;
      c.gridy = 0;
      frame.add(bTest,c);
      
      //Size
      JButton bSize =new JButton("Size");  
      bSize.setFont(font1);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.weightx = 0.5;
      c.gridx = 2;
      c.gridy = 0;
      frame.add(bSize,c);
      
      //Validate
      JButton bValidate =new JButton("Validate");  
      bValidate.setFont(font1); 
      c.fill = GridBagConstraints.HORIZONTAL;
      c.weightx = 0.5;
      c.gridx = 3;
      c.gridy = 0;
      frame.add(bValidate,c);
      
      //Preview
      JButton bPreview =new JButton("Preview");
      bPreview.setFont(font1); 
      c.fill = GridBagConstraints.HORIZONTAL;     
      c.weightx = 0.5;
      c.gridx = 4;
      c.gridy = 0;
      frame.add(bPreview,c);
      
      //Menu
      JButton bMenu =new JButton("Menu");   
      bMenu.setFont(font1);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.weightx = 0.5;
      c.gridx = 5;
      c.gridy = 0;
      frame.add(bMenu,c);
      
      //Prompt and textbox for Rows
      JLabel rowPrompt = new JLabel("Rows: ");
      rowPrompt.setFont(font1);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.weightx = 0.5;
      c.gridx = 0;
      c.gridy = 1;
      frame.add(rowPrompt,c);
      
      JTextField rowEntry = new JTextField();
      rowEntry.setFont(font1);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.weightx = 0.5;
      c.gridx = 1;
      c.gridy = 1;
      frame.add(rowEntry,c);
      
      //Prompt and textbox for Columns
      JLabel columnPrompt = new JLabel("Columns: ");
      columnPrompt.setFont(font1);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.weightx = 0.5;
      c.gridx = 2;
      c.gridy = 1;
      frame.add(columnPrompt,c);
      
      JTextField columnEntry = new JTextField();
      columnEntry.setFont(font1);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.weightx = 0.5;
      c.gridx = 3;
      c.gridy = 1;
      frame.add(columnEntry,c);
     
      //Echo messages
      JLabel windowPrompt = new JLabel("Window: ");
      windowPrompt.setFont(font1);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.anchor = GridBagConstraints.LAST_LINE_START;
      c.weightx = 1.0;
      c.gridx = 0;
      c.gridy = 3;
      frame.add(windowPrompt,c);
      
      //Place holder panel
      JPanel holder = new JPanel();
      c.fill = GridBagConstraints.HORIZONTAL;
      c.weightx = 0.5;
      c.gridwidth = 6;
      c.ipady = height - 150;
      c.gridx = 0;
      c.gridy = 2;
      frame.add(holder,c);
        
      frame.setVisible(true);

      //Setting button actions
      //Submit
      
      //Size
      bSize.addActionListener( new ActionListener(){
          
         @Override
	      public void actionPerformed(ActionEvent arg0){
         
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
         int nh = Integer.parseInt(columnEntry.getText());
         int nw = Integer.parseInt(rowEntry.getText());
         
         //Based on the values, they are made bigger to create the next window to the fitted size
         int newHeight = nh * 50;
         int newWidth = nw * 50;
         
         if( (nh <= min && nw < min) || (nh < min && nw <= min) || (nh > max) || ( nw > max) || (nh > max && nw < 3) || (nh < 3 && nw >50) ){
            windowPrompt.setText("Opps! Values not valid");
         }
         
         else{
         
            windowPrompt.setText("Creating your puzzle!");
           
            //Adding scrolling
            JPanel newPanel = new JPanel();
            JScrollPane newScroll = new JScrollPane(newPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
           
            frame.add(newScroll,c);  
            holder.setVisible(false);
            
            windowPrompt.setText("Click to cycle through");
            
            //array of puzzle
            JPanel arrayPuzzle[][] = new JPanel[nh][nw]; 

            //Creates the grid of buttons
            newPanel.setLayout(new GridLayout(nh,nw));
            
            
            //This is for storing the information
            for( int i = 0 ; i < max ; i++ ){
               for( int j = 0 ; j < max ;j ++){
                  
               }
               
                
           
            }//end for i
            
            //Creating all the panels
            for (int i = 0; i < (nh*nw); i++){
            
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
                  public void mouseClicked(MouseEvent arg0){
                     buttons.next(but);
                  }
   
                  @Override
                  public void mouseEntered(MouseEvent arg0){
                     // TODO Auto-generated method stub
                  }
   
                  @Override
                  public void mouseExited(MouseEvent arg0){
                     // TODO Auto-generated method stub
                  }
   
                  @Override
                  public void mousePressed(MouseEvent arg0){
                     // TODO Auto-generated method stub
                  }
   
                  @Override
                  public void mouseReleased(MouseEvent arg0){
                        // TODO Auto-generated method stub
                  }
   
              });//end MouseListener
                 
               }//end for i
          }//end else
          
        }//end ActionPreformed
      });//end bSize.actionListener
      
      //Validate
      //Preview
      //Menu
      bMenu.addActionListener( new ActionListener(){
          
         @Override
	      public void actionPerformed(ActionEvent arg1){
         

          return; 
   
            
          
        }//end ActionPreformed
      });//end bMenu.actionListener

      
   }//end display
     

}//end clas
