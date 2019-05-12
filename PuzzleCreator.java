
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
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.JScrollPane;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
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
   private static SokobanRuntimeStorage writePuzzle;
   private static int nh;
   private static int nw;
   private static JPanel newPanel;
   private static JPanel gridPanel;
   private static JScrollPane newScroll;

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
      frame.setUndecorated(true);
      frame.setResizable(false);
      // frame.setBackground(Color.red);

      // Setting up the layout
      frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      JPanel menuButtons = new JPanel();
      menuButtons.setLayout(new BoxLayout(menuButtons, BoxLayout.LINE_AXIS));
      // Top buttons
      // Submit
      bSubmit = new JButton("Submit");
      bSubmit.setFont(font1);
      bSubmit.setEnabled(false);
      menuButtons.add(bSubmit);

      // Test
      bTest = new JButton("Test");
      bTest.setFont(font1);
      bTest.setEnabled(false);
      menuButtons.add(bTest);

      // Validate
      bValidate = new JButton("Validate");
      bValidate.setFont(font1);
      bValidate.setEnabled(false);
      menuButtons.add(bValidate);

      // Size
      bSize = new JButton("Set Size");
      bSize.setFont(font1);
      menuButtons.add(bSize);

      // Preview
      bPreview = new JButton("Preview");
      bPreview.setFont(font1);
      menuButtons.add(bPreview);

      // Menu
      bMenu = new JButton("Main Menu");
      bMenu.setFont(font1);
      menuButtons.add(bMenu);

      frame.add(menuButtons);

      JPanel sizePanel = new JPanel();
      sizePanel.setLayout(new BoxLayout(sizePanel, BoxLayout.LINE_AXIS));

      // Prompt and textbox for Rows
      JLabel rowPrompt = new JLabel("Rows: ");
      rowPrompt.setFont(font1);
      sizePanel.add(rowPrompt);

      JTextField rowEntry = new JTextField();
      rowEntry.setMaximumSize(new Dimension(Integer.MAX_VALUE, rowEntry.getPreferredSize().height));
      rowEntry.setFont(font1);
      sizePanel.add(rowEntry);

      // Prompt and textbox for Columns
      JLabel columnPrompt = new JLabel("Columns: ");
      columnPrompt.setFont(font1);
      sizePanel.add(columnPrompt);

      JTextField columnEntry = new JTextField();
      columnEntry.setMaximumSize(new Dimension(Integer.MAX_VALUE, rowEntry.getPreferredSize().height));
      columnEntry.setFont(font1);
      sizePanel.add(columnEntry);

      frame.add(sizePanel);

      Component holder = Box.createRigidArea(new Dimension(500, 500));

      frame.add(holder);

      // Echo messages
      JPanel prompt = new JPanel();
      prompt.setLayout(new BoxLayout(prompt, BoxLayout.LINE_AXIS));
      JLabel windowPrompt = new JLabel("Window: ");
      windowPrompt.setFont(font1);
      prompt.add(windowPrompt);
      prompt.add(Box.createHorizontalGlue());
      frame.add(prompt);

      // Place holder panel

      frame.pack();
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
            } catch (NumberFormatException e) {
               windowPrompt.setText("Please input integer numbers for the row and column.");
               frame.validate();
               return;
            }

            if (nh < min || nw < min) {
               windowPrompt.setText("Size values must be greater than " + min + ".");
               frame.validate();
            } else if (nh > max || nw > max) {
               windowPrompt.setText("Size values must be less than " + max + ".");
               frame.validate();
            }

            else {
               
               bValidate.setEnabled(true);
               windowPrompt.setText("Modifying puzzle size!");
               frame.validate();
               // Adding scrolling
               boolean modify = false;
               if (newPanel != null) {
                  modify = true;
                  newPanel.removeAll();
                  newPanel.setLayout(new GridLayout(nw, nh));
                  for (int i = 0; i < nw; i++) {
                     for (int j = 0; j < nh; j++) {
                        newPanel.add(arrayPuzzle[i][j]);
                     }
                  }
                  newPanel.validate();
               } else {
                  gridPanel = new JPanel();
                  gridPanel.setLayout(new BoxLayout(gridPanel, BoxLayout.LINE_AXIS));
                  newPanel = new JPanel();
                  newScroll = new JScrollPane(newPanel);
                  gridPanel.add(newScroll);
                  gridPanel.add(Box.createHorizontalGlue());
                  frame.remove(holder);
                  frame.remove(prompt);
                  frame.add(gridPanel);
                  frame.add(prompt);
                  frame.add(Box.createVerticalGlue());
                  frame.validate();
               }
               windowPrompt.setText("Click a grid to cycle through possible values.");
               frame.validate();

               // initialize storage array
               if (!modify) {
                  arrayPuzzle = new JPanel[max][max];
               }

               // Creates the grid of buttons
               newPanel.setLayout(new GridLayout(nw, nh));

               // This is for storing the information
               for (int i = 0; i < max; i++) {
                  for (int j = 0; j < max; j++) {
                     if (!modify) {
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

                        buttons.first(arrayPuzzle[i][j]);

                        arrayPuzzle[i][j].addMouseListener(new MouseListener() {
                           @Override
                           public void mouseClicked(MouseEvent arg0) {
                              buttons.next((Container) arg0.getComponent());
                              bTest.setEnabled(false);
                              bSubmit.setEnabled(false);
                              switch (((Container) arg0.getComponent()).getName()) {
                              case "0":
                                 ((Container) arg0.getComponent()).setName("1");
                                 break;
                              case "1":
                                 ((Container) arg0.getComponent()).setName("2");
                                 break;
                              case "2":
                                 ((Container) arg0.getComponent()).setName("3");
                                 break;
                              case "3":
                                 ((Container) arg0.getComponent()).setName("4");
                                 break;
                              case "4":
                                 ((Container) arg0.getComponent()).setName("5");
                                 break;
                              case "5":
                                 ((Container) arg0.getComponent()).setName("6");
                                 break;
                              case "6":
                                 ((Container) arg0.getComponent()).setName("7");
                                 break;
                              case "7":
                                 ((Container) arg0.getComponent()).setName("0");
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

                     if (i < nw && j < nh) {
                        newPanel.add(arrayPuzzle[i][j]);
                     }
                  }
               } // end for i
               newPanel.validate();
               newPanel.repaint();
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
            writePuzzle = new SokobanRuntimeStorage("Test Puzzle", nh, nw);
            for (int i = 0; i < nw; i++) {
               for (int j = 0; j < nh; j++) {
                  switch (arrayPuzzle[i][j].getName()) {
                  case "0":
                     testPuzzle.setSquare(SokobanInterpreter.EXTERNAL, j, i);
                     writePuzzle.setSquare(SokobanInterpreter.EXTERNAL, j, i);
                     break;
                  case "1":
                     testPuzzle.setSquare(SokobanInterpreter.WALL, j, i);
                     writePuzzle.setSquare(SokobanInterpreter.WALL, j, i);
                     break;
                  case "2":
                     testPuzzle.setSquare(SokobanInterpreter.INTERNAL, j, i);
                     writePuzzle.setSquare(SokobanInterpreter.INTERNAL, j, i);
                     break;
                  case "3":
                     testPuzzle.setSquare(SokobanInterpreter.INTERNAL_PLAYER, j, i);
                     writePuzzle.setSquare(SokobanInterpreter.INTERNAL_PLAYER, j, i);
                     testPuzzle.setPlayerX(j);
                     testPuzzle.setPlayerY(i);
                     break;
                  case "4":
                     testPuzzle.setSquare(SokobanInterpreter.INTERNAL_BOX, j, i);
                     writePuzzle.setSquare(SokobanInterpreter.INTERNAL_BOX, j, i);
                     break;
                  case "5":
                     testPuzzle.setSquare(SokobanInterpreter.INTERNAL_TARGET, j, i);
                     writePuzzle.setSquare(SokobanInterpreter.INTERNAL_TARGET, j, i);
                     break;
                  case "6":
                     testPuzzle.setSquare(SokobanInterpreter.INTERNAL_BOX_TARGET, j, i);
                     writePuzzle.setSquare(SokobanInterpreter.INTERNAL_BOX_TARGET, j, i);
                     break;
                  case "7":
                     testPuzzle.setSquare(SokobanInterpreter.INTERNAL_PLAYER_TARGET, j, i);
                     writePuzzle.setSquare(SokobanInterpreter.INTERNAL_PLAYER_TARGET, j, i);
                     testPuzzle.setPlayerX(j);
                     testPuzzle.setPlayerY(i);
                     break;
                  }
               }
            }
            if (Validation.validate(testPuzzle)) {
               windowPrompt.setText("Your puzzle has a valid structure.");
               bTest.setEnabled(true);
            } else {
               windowPrompt.setText("Your puzzle has an invalid structure.");
            }
         }

      });

      bTest.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent arg0) {

            DisplayPuzzle.displayWindow(testPuzzle, true).addWindowListener(new WindowListener() {

               @Override
               public void windowActivated(WindowEvent arg0) {
                  // TODO Auto-generated method stub

               }

               @Override
               public void windowClosed(WindowEvent arg0) {

                  if (testPuzzle.getSolvable()) {

                     bSubmit.setEnabled(true);
                     windowPrompt.setText("You have successfully solved your puzzle. Submit?");

                  }

               }

               @Override
               public void windowClosing(WindowEvent arg0) {
                  // TODO Auto-generated method stub

               }

               @Override
               public void windowDeactivated(WindowEvent arg0) {
                  // TODO Auto-generated method stub

               }

               @Override
               public void windowDeiconified(WindowEvent arg0) {
                  // TODO Auto-generated method stub

               }

               @Override
               public void windowIconified(WindowEvent arg0) {
                  // TODO Auto-generated method stub

               }

               @Override
               public void windowOpened(WindowEvent arg0) {
                  // TODO Auto-generated method stub

               }

            });

         }

      });

      bSubmit.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent arg0) {

            JDialog name = new JDialog();
            name.setTitle("Name");
            name.setLayout(new BoxLayout(name.getContentPane(), BoxLayout.PAGE_AXIS));
            JLabel question = new JLabel("Your puzzle requires a name.");
            JTextField getName = new JTextField();
            JButton submit = new JButton("Check Name");
            JButton cancel = new JButton("Cancel");
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
            submit.addActionListener(new ActionListener() {

               @Override
               public void actionPerformed(ActionEvent e) {

                  File newPuzzle = new File("puzzles//" + getName.getText() + ".spsf");
                  try {
                     if (!newPuzzle.createNewFile()) {
                        JDialog error = new JDialog();
                        error.setTitle("Error");
                        JLabel message = new JLabel("Your name is not unique. Try another name.");
                        JButton ok = new JButton("Ok");
                        ok.addActionListener(new ActionListener() {

                           @Override
                           public void actionPerformed(ActionEvent arg0) {

                              error.dispose();

                           }

                        });
                        error.setLayout(new BoxLayout(error.getContentPane(), BoxLayout.PAGE_AXIS));
                        error.add(message);
                        error.add(ok);
                        error.pack();
                        error.setVisible(true);
                     } else {

                        PuzzleWriter p = new PuzzleWriter(newPuzzle, writePuzzle, getName.getText());
                        p.writePuzzle();
                        JDialog puzzleCreated = new JDialog();
                        puzzleCreated.setTitle("Puzzle Created");
                        JLabel confirmation = new JLabel("Your puzzle has been created successfully.");
                        JButton mainMenu = new JButton("Return to Main Menu");
                        mainMenu.addActionListener(new ActionListener() {

                           @Override
                           public void actionPerformed(ActionEvent arg0) {

                              puzzleCreated.dispose();
                              name.dispose();
                              frame.dispose();
                              MainMenu.reopenMainMenu();

                           }

                        });
                        puzzleCreated.setLayout(new BoxLayout(puzzleCreated.getContentPane(), BoxLayout.PAGE_AXIS));
                        puzzleCreated.add(confirmation);
                        puzzleCreated.add(mainMenu);
                        puzzleCreated.pack();
                        puzzleCreated.setVisible(true);

                     }
                  } catch (IOException e1) {

                     JDialog error = new JDialog();
                     error.setTitle("Error");
                     JLabel message = new JLabel("Your name contains invalid characters. Try another name.");
                     JButton ok = new JButton("Ok");
                     ok.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent arg0) {

                           error.dispose();

                        }

                     });
                     error.setLayout(new BoxLayout(error.getContentPane(), BoxLayout.PAGE_AXIS));
                     error.add(message);
                     error.add(ok);
                     error.pack();
                     error.setVisible(true);

                  }

               }

            });

            cancel.addActionListener(new ActionListener() {

               @Override
               public void actionPerformed(ActionEvent arg0) {

                  name.dispose();

               }

            });

            name.add(question);
            name.add(getName);
            buttonPanel.add(Box.createHorizontalGlue());
            buttonPanel.add(submit);
            buttonPanel.add(cancel);
            name.add(buttonPanel);
            name.pack();
            name.setVisible(true);

         }

      });

   }// end display

}// end clas
