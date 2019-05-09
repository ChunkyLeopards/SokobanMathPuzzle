
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import java.applet.Applet;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

@SuppressWarnings("serial")

public class PuzzleCreator extends JFrame {

   private static File exterior;
   private static File wall;
   private static File interior;
   private static File player;
   private static File target;
   private static File box;
   private static File boxTarget;
   private static File playerTarget;
   private static JButton bSubmit;
   private static JButton bTest;
   private static JButton bSize;
   private static JButton bValidate;
   private static JButton bPreview;
   private static JButton bMenu;
   private static BufferedImage exteriorImage;
   private static BufferedImage wallImage;
   private static BufferedImage interiorImage;
   private static BufferedImage playerImage;
   private static BufferedImage targetImage;
   private static BufferedImage boxImage;
   private static BufferedImage boxTargetImage;
   private static BufferedImage playerTargetImage;
   private static JPanel arrayPuzzle[][];
   private static SokobanRuntimeStorage testPuzzle;
   private static int nh;
   private static int nw;
   private static JPanel newPanel;

   public static void display() {

      // Max and min allowed for puzzle creation
      int max = 50;
      int min = 5;

      // Size of the window
      int width = 800;
      int height = 800;

      // Frame setup
      JFrame frame = new JFrame("Puzzle Creator: Dimensions");
      Font font1 = new Font("SandSerif", Font.PLAIN, 16);
      frame.setSize(width, height);
      // frame.setBackground(Color.red);

      // Setting up the layout
      frame.setLayout(new GridBagLayout());
      GridBagConstraints c = new GridBagConstraints();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // Top buttons
      // Submit
      bSubmit = new JButton("Submit");
      bSubmit.setFont(font1);
      bSubmit.setEnabled(false);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.weightx = 0.5;
      c.gridx = 0;
      c.gridy = 0;
      frame.add(bSubmit, c);

      // Test
      bTest = new JButton("Test");
      bTest.setFont(font1);
      bTest.setEnabled(false);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.weightx = 0.5;
      c.gridx = 1;
      c.gridy = 0;
      frame.add(bTest, c);

      // Validate
      bValidate = new JButton("Validate");
      bValidate.setFont(font1);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.weightx = 0.5;
      c.gridx = 2;
      c.gridy = 0;
      frame.add(bValidate, c);

      // Size
      bSize = new JButton("Size");
      bSize.setFont(font1);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.weightx = 0.5;
      c.gridx = 3;
      c.gridy = 0;
      frame.add(bSize, c);

      // Preview
      bPreview = new JButton("Preview");
      bPreview.setFont(font1);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.weightx = 0.5;
      c.gridx = 4;
      c.gridy = 0;
      frame.add(bPreview, c);

      // Menu
      bMenu = new JButton("Menu");
      bMenu.setFont(font1);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.weightx = 0.5;
      c.gridx = 5;
      c.gridy = 0;
      frame.add(bMenu, c);

      // Prompt and textbox for Rows
      JLabel rowPrompt = new JLabel("Rows: ");
      rowPrompt.setFont(font1);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.weightx = 0.5;
      c.gridx = 0;
      c.gridy = 1;
      frame.add(rowPrompt, c);

      JTextField rowEntry = new JTextField();
      rowEntry.setFont(font1);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.weightx = 0.5;
      c.gridx = 1;
      c.gridy = 1;
      frame.add(rowEntry, c);

      // Prompt and textbox for Columns
      JLabel columnPrompt = new JLabel("Columns: ");
      columnPrompt.setFont(font1);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.weightx = 0.5;
      c.gridx = 2;
      c.gridy = 1;
      frame.add(columnPrompt, c);

      JTextField columnEntry = new JTextField();
      columnEntry.setFont(font1);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.weightx = 0.5;
      c.gridx = 3;
      c.gridy = 1;
      frame.add(columnEntry, c);

      // Echo messages
      JLabel windowPrompt = new JLabel("Window: ");
      windowPrompt.setFont(font1);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.anchor = GridBagConstraints.LAST_LINE_START;
      c.weightx = 1.0;
      c.gridx = 0;
      c.gridy = 3;
      frame.add(windowPrompt, c);

      // Place holder panel
      JPanel holder = new JPanel();
      c.fill = GridBagConstraints.HORIZONTAL;
      c.weightx = 0.5;
      c.gridwidth = 6;
      c.ipady = height - 150;
      c.gridx = 0;
      c.gridy = 2;
      frame.add(holder, c);

      frame.setVisible(true);

      // Setting button actions
      // Submit

      // Size
      bSize.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent arg0) {

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

            // Converts the input string into an integer
            try {
               nh = Integer.parseInt(columnEntry.getText());
               nw = Integer.parseInt(rowEntry.getText());
            }
            catch (NumberFormatException e) {
               windowPrompt.setText("Please input integer numbers for the row and column.");
               return;
            }
            
            if(nh < min || nw < min) {
               windowPrompt.setText("Size values must be greater than " + min + ".");
            }
            else if(nh > max || nw > max) {
               windowPrompt.setText("Size values must be less than " + max + ".");
            }

            else {

               windowPrompt.setText("Creating your puzzle!");
               // Adding scrolling
               if(newPanel != null) {
                  newPanel.removeAll();
                  newPanel.setLayout(new GridLayout(nw, nh));
                  for(int i = 0; i < nw; i++) {
                     for(int j = 0; j < nh; j++) {
                        newPanel.add(arrayPuzzle[i][j]);
                     }
                  }
                  newPanel.validate();
               }
               newPanel = new JPanel();
               JScrollPane newScroll = new JScrollPane(newPanel);

               frame.add(newScroll, c);
               holder.setVisible(false);

               windowPrompt.setText("Click to cycle through.");

               // initialize storage array
               arrayPuzzle = new JPanel[max][max];

               // Creates the grid of buttons
               newPanel.setLayout(new GridLayout(nw, nh));

               // This is for storing the information
               for (int i = 0; i < max; i++) {
                  for (int j = 0; j < max; j++) {
                     arrayPuzzle[i][j] = new JPanel();
                     arrayPuzzle[i][j].setBorder(new MatteBorder(1, 1, 1, 1, Color.lightGray));
                     arrayPuzzle[i][j].setPreferredSize(new Dimension(50, 50));
                     CardLayout buttons = new CardLayout();

                     EditorButtons ext = new EditorButtons(exteriorImage);
                     EditorButtons wal = new EditorButtons(wallImage);
                     EditorButtons inter = new EditorButtons(interiorImage);
                     EditorButtons pl = new EditorButtons(playerImage);
                     EditorButtons bo = new EditorButtons(boxImage);
                     EditorButtons tar = new EditorButtons(targetImage);
                     EditorButtons bt = new EditorButtons(boxTargetImage);
                     EditorButtons pt = new EditorButtons(playerTargetImage);

                     arrayPuzzle[i][j].setLayout(buttons);
                     arrayPuzzle[i][j].add(ext);
                     arrayPuzzle[i][j].add(wal);
                     arrayPuzzle[i][j].add(inter);
                     arrayPuzzle[i][j].add(pl);
                     arrayPuzzle[i][j].add(bo);
                     arrayPuzzle[i][j].add(tar);
                     arrayPuzzle[i][j].add(bt);
                     arrayPuzzle[i][j].add(pt);
                     arrayPuzzle[i][j].setName("0");

                     if (i < nw && j < nh) {
                        newPanel.add(arrayPuzzle[i][j]);
                     }

                     buttons.first(arrayPuzzle[i][j]);

                     arrayPuzzle[i][j].addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent arg0) {
                           buttons.next((Container) arg0.getComponent());
                           bTest.setEnabled(false);
                           bSubmit.setEnabled(false);
                           switch(((Container)arg0.getComponent()).getName()) {
                           case "0":
                              ((Container)arg0.getComponent()).setName("1");
                              break;
                           case "1":
                              ((Container)arg0.getComponent()).setName("2");
                              break;
                           case "2":
                              ((Container)arg0.getComponent()).setName("3");
                              break;
                           case "3":
                              ((Container)arg0.getComponent()).setName("4");
                              break;
                           case "4":
                              ((Container)arg0.getComponent()).setName("5");
                              break;
                           case "5":
                              ((Container)arg0.getComponent()).setName("6");
                              break;
                           case "6":
                              ((Container)arg0.getComponent()).setName("7");
                              break;
                           case "7":
                              ((Container)arg0.getComponent()).setName("0");
                              break;
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

                     });// end MouseListener
                  }
               } // end for i
            } // end else

         }// end ActionPreformed
      });// end bSize.actionListener

      // Validate
      // Preview
      // Menu
      bMenu.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent arg1) {

            frame.dispose();
            MainMenu.reopenMainMenu();

            return;

         }// end ActionPreformed
      });// end bMenu.actionListener
      
      bValidate.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent arg0) {
            
            windowPrompt.setText("Validating...");
            testPuzzle = new SokobanRuntimeStorage("Test Puzzle", nh, nw);
            for(int i = 0; i < nw; i++) {
               for(int j = 0; j < nh; j++) {
                  switch(arrayPuzzle[i][j].getName()) {
                  case "0":
                     testPuzzle.setSquare(SokobanInterpreter.EXTERNAL, j, i);
                     break;
                  case "1":
                     testPuzzle.setSquare(SokobanInterpreter.WALL, j, i);
                     break;
                  case "2":
                     testPuzzle.setSquare(SokobanInterpreter.INTERNAL, j, i);
                     break;
                  case "3":
                     testPuzzle.setSquare(SokobanInterpreter.INTERNAL_PLAYER, j, i);
                     testPuzzle.setPlayerX(j);
                     testPuzzle.setPlayerY(i);
                     break;
                  case "4":
                     testPuzzle.setSquare(SokobanInterpreter.INTERNAL_BOX, j, i);
                     break;
                  case "5":
                     testPuzzle.setSquare(SokobanInterpreter.INTERNAL_TARGET, j, i);
                     break;
                  case "6":
                     testPuzzle.setSquare(SokobanInterpreter.INTERNAL_BOX_TARGET, j, i);
                     break;
                  case "7":
                     testPuzzle.setSquare(SokobanInterpreter.INTERNAL_PLAYER_TARGET, j, i);
                     testPuzzle.setPlayerX(j);
                     testPuzzle.setPlayerY(i);
                     break;
                  }
               }
            }
            if(Validation.validate(testPuzzle)) {
               windowPrompt.setText("Your puzzle has a valid structure.");
               bTest.setEnabled(true);
            }
            else {
               windowPrompt.setText("Your puzzle has an invalid structure.");
            }
         }
         
      });
      
      bTest.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent arg0) {
            
            DisplayPuzzle.displayWindow(testPuzzle, true);
            
         }
         
      });
      
   }// end display

}// end clas
