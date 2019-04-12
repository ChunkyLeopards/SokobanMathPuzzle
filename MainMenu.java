import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class MainMenu extends JPanel {

   private static JFrame window;
   private static JPanel panel;
   private static JPanel puzzleButton;
   private static JLabel puzzles;
   private static JPanel editorButton;
   private static JLabel editor;
   private static JPanel optionButton;
   private static JLabel options;
   private static JPanel creditButton;
   private static JLabel credits;
   private static JPanel exitButton;
   private static JLabel exit;
   private static JLabel title;
   private static JFrame puzzleSelection;
   private static JScrollPane puzzlePane;
   private static JPanel puzzlePanel;
   private static JPanel puzzleList[];
   private static JLabel puzzleName[];
   private static JLabel puzzleListBack;
   private static JPanel puzzleListBackButton;
   private static File puzzleFile;

   public static void main(String[] args) throws IOException {
      
      window = new JFrame("∫oκσbαπ");
      window.setUndecorated(true);
      panel = new JPanel();
      panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
      panel.setAlignmentX(Component.CENTER_ALIGNMENT);
      title = new JLabel("∫oκσbαπ");
      title.setOpaque(false);
      title.setFont(new Font("TimesRoman", Font.BOLD + Font.ITALIC, 80));
      title.setAlignmentX(Component.CENTER_ALIGNMENT);
      panel.setSize(750, 750);
      panel.setBackground(Color.red);
      puzzles = new JLabel("Puzzles");
      puzzles.setAlignmentX(Component.CENTER_ALIGNMENT);
      puzzles.setFont(new Font("TimesRoman", Font.BOLD + Font.ITALIC, 20));
      puzzles.setSize(puzzles.getMinimumSize());
      puzzles.setBorder(new EmptyBorder(5, 5, 5, 5));
      puzzleButton = new JPanel();
      puzzleButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      puzzleButton.setMaximumSize(new Dimension(puzzles.getWidth()  + 30, puzzles.getHeight()));
      puzzleButton.setBackground(Color.gray);
      puzzleButton.add(puzzles);
      editor = new JLabel("Puzzle Editor");
      editor.setBackground(Color.gray);
      editor.setAlignmentX(Component.CENTER_ALIGNMENT);
      editor.setFont(new Font("TimesRoman", Font.BOLD + Font.ITALIC, 20));
      editor.setSize(editor.getMinimumSize());
      editor.setBorder(new EmptyBorder(5, 5, 5, 5));
      editorButton = new JPanel();
      editorButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      editorButton.setMaximumSize(new Dimension(editor.getWidth()  + 30, editor.getHeight()));
      editorButton.setBackground(Color.gray);
      editorButton.add(editor);
      options = new JLabel("Options");
      options.setBackground(Color.gray);
      options.setAlignmentX(Component.CENTER_ALIGNMENT);
      options.setFont(new Font("TimesRoman", Font.BOLD + Font.ITALIC, 20));
      options.setSize(options.getMinimumSize());
      options.setBorder(new EmptyBorder(5, 5, 5, 5));
      optionButton = new JPanel();
      optionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      optionButton.setMaximumSize(new Dimension(options.getWidth()  + 30, options.getHeight()));
      optionButton.setBackground(Color.gray);
      optionButton.add(options);
      credits = new JLabel("Credits");
      credits.setBackground(Color.gray);
      credits.setAlignmentX(Component.CENTER_ALIGNMENT);
      credits.setFont(new Font("TimesRoman", Font.BOLD + Font.ITALIC, 20));
      credits.setSize(credits.getMinimumSize());
      credits.setBorder(new EmptyBorder(5, 5, 5, 5));
      creditButton = new JPanel();
      creditButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      creditButton.setMaximumSize(new Dimension(credits.getWidth()  + 30, credits.getHeight()));
      creditButton.setBackground(Color.gray);
      creditButton.add(credits);
      exit = new JLabel("Exit");
      exit.setBackground(Color.gray);
      exit.setAlignmentX(Component.CENTER_ALIGNMENT);
      exit.setFont(new Font("TimesRoman", Font.BOLD + Font.ITALIC, 20));
      exit.setSize(exit.getMinimumSize());
      exit.setBorder(new EmptyBorder(5, 5, 5, 5));
      exitButton = new JPanel();
      exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      exitButton.setMaximumSize(new Dimension(exit.getWidth() + 30, exit.getHeight()));
      exitButton.setBackground(Color.gray);
      exitButton.add(exit);
      panel.add(title);
      panel.add(Box.createRigidArea(new Dimension(0,100)));
      panel.add(puzzleButton);
      panel.add(Box.createRigidArea(new Dimension(0,20)));
      panel.add(editorButton);
      panel.add(Box.createRigidArea(new Dimension(0,20)));
      panel.add(optionButton);
      panel.add(Box.createRigidArea(new Dimension(0,20)));
      panel.add(creditButton);
      panel.add(Box.createRigidArea(new Dimension(0,20)));
      panel.add(exitButton);
      panel.add(Box.createVerticalGlue());
      new MainMenu(panel);
      window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      window.add(panel);
      window.setSize(panel.getSize());
      window.setVisible(true);
      
   }

   public MainMenu(JPanel p) {

      puzzleButton.addMouseListener(new MouseListener() {

         @Override
         public void mouseClicked(MouseEvent arg0) {

            window.setVisible(false);
            puzzleSelection = new JFrame();
            puzzleSelection.setUndecorated(true);
            puzzleSelection.setSize(750,750);
            puzzlePanel = new JPanel();
            puzzlePanel.setLayout(new BoxLayout(puzzlePanel, BoxLayout.PAGE_AXIS));
            File puzzleDirectory = new File("puzzles");
            File allPuzzles[] =  puzzleDirectory.listFiles();
            puzzleList = new JPanel[allPuzzles.length];
            puzzleName = new JLabel[allPuzzles.length];
            int maxWidth = 0;
            int maxHeight = 0;
            
            for (int i = 0; i < puzzleList.length; i++) {
               
               puzzleName[i] = new JLabel();
               puzzleName[i].setText(allPuzzles[i].getPath());
               puzzleName[i].setFont(new Font("TimesRoman", Font.BOLD, 20));
               puzzleFile = allPuzzles[i];
               
               if (puzzleName[i].getWidth() > maxWidth) {
                  
                  maxWidth = puzzleName[i].getWidth();
                  
               }
               
               if (puzzleName[i].getHeight() > maxHeight) {
                  
                  maxHeight = puzzleName[i].getHeight();
                  
               }
               
               puzzleList[i] = new JPanel();
               puzzleList[i].setBackground(Color.gray);
               puzzleList[i].addMouseListener(new MouseListener() {
                  
                  File puzzle = new File(puzzleFile.getPath());
                  
                  @Override
                  public void mouseClicked(MouseEvent arg0) {
                     
                     puzzleSelection.setVisible(false);
                     SokobanInterpreter s = new SokobanInterpreter(puzzle);
                     SokobanRuntimeStorage puzzle = null;

                     try {

                        puzzle = s.readPuzzleFile();

                     } catch (IOException e) {

                        e.printStackTrace();

                     }

                     DisplayPuzzle.displayWindow(puzzle, false);
                     
                  }

                  @Override
                  public void mouseEntered(MouseEvent arg0) {
                     
                     arg0.getComponent().setBackground(Color.lightGray);
                     
                  }

                  @Override
                  public void mouseExited(MouseEvent arg0) {
                     
                     arg0.getComponent().setBackground(Color.gray);
                     
                  }

                  @Override
                  public void mousePressed(MouseEvent arg0) {
                     
                     arg0.getComponent().setBackground(Color.white);
                     
                  }

                  @Override
                  public void mouseReleased(MouseEvent arg0) {
                     
                     arg0.getComponent().setBackground(Color.lightGray);
                     
                  }
                  
               });
               
               puzzleList[i].add(puzzleName[i]);
               puzzlePanel.add(puzzleList[i]);
               
            }
            
            puzzleListBack = new JLabel("Back");
            puzzleListBack.setFont(new Font("TimesRoman", Font.BOLD, 20));
            puzzleListBackButton = new JPanel();
            puzzleListBackButton.add(puzzleListBack);
            puzzleListBackButton.setBackground(Color.gray);
            puzzleListBackButton.addMouseListener(new MouseListener() {

               @Override
               public void mouseClicked(MouseEvent arg0) {
                  
                  puzzleSelection.setVisible(false);
                  window.setVisible(true);
                  
               }

               @Override
               public void mouseEntered(MouseEvent arg0) {
                  
                  puzzleListBackButton.setBackground(Color.lightGray);
                  
               }

               @Override
               public void mouseExited(MouseEvent arg0) {
                  
                  puzzleListBackButton.setBackground(Color.gray);
                  
               }

               @Override
               public void mousePressed(MouseEvent arg0) {
                  
                  puzzleListBackButton.setBackground(Color.white);
                  
               }

               @Override
               public void mouseReleased(MouseEvent arg0) {
                  
                  puzzleListBackButton.setBackground(Color.lightGray);
                  
               }
               
            });
            
            puzzlePanel.add(puzzleListBackButton);            
            puzzlePane = new JScrollPane(puzzlePanel);
            puzzlePane.getVerticalScrollBar().setUnitIncrement(16);
            puzzleSelection.add(puzzlePane);
            puzzleSelection.setVisible(true);
            
            /*File read = new File("puzzles/TestPuzzle2.spsf");
            SokobanInterpreter s = new SokobanInterpreter(read);
            SokobanRuntimeStorage puzzle = null;

            try {

               puzzle = s.readPuzzleFile();

            } catch (IOException e) {

               e.printStackTrace();

            }

            DisplayPuzzle.displayWindow(puzzle);*/

         }

         @Override
         public void mouseEntered(MouseEvent arg0) {

            puzzleButton.setBackground(Color.lightGray);

         }

         @Override
         public void mouseExited(MouseEvent arg0) {

            puzzleButton.setBackground(Color.gray);

         }

         @Override
         public void mousePressed(MouseEvent arg0) {

            puzzleButton.setBackground(Color.white);

         }

         @Override
         public void mouseReleased(MouseEvent arg0) {

            puzzleButton.setBackground(Color.lightGray);

         }

      });

      editorButton.addMouseListener(new MouseListener() {

         @Override
         public void mouseClicked(MouseEvent arg0) {
            
            window.setVisible(false);
            PuzzleCreator.display();
            
         }

         @Override
         public void mouseEntered(MouseEvent arg0) {

            editorButton.setBackground(Color.lightGray);

         }

         @Override
         public void mouseExited(MouseEvent arg0) {

            editorButton.setBackground(Color.gray);

         }

         @Override
         public void mousePressed(MouseEvent arg0) {

            editorButton.setBackground(Color.white);

         }

         @Override
         public void mouseReleased(MouseEvent arg0) {

            editorButton.setBackground(Color.lightGray);

         }

      });

      optionButton.addMouseListener(new MouseListener() {

         @Override
         public void mouseClicked(MouseEvent arg0) {

         }

         @Override
         public void mouseEntered(MouseEvent arg0) {

            optionButton.setBackground(Color.lightGray);

         }

         @Override
         public void mouseExited(MouseEvent arg0) {

            optionButton.setBackground(Color.gray);

         }

         @Override
         public void mousePressed(MouseEvent arg0) {

            optionButton.setBackground(Color.white);

         }

         @Override
         public void mouseReleased(MouseEvent arg0) {

            optionButton.setBackground(Color.lightGray);

         }

      });

      creditButton.addMouseListener(new MouseListener() {

         @Override
         public void mouseClicked(MouseEvent arg0) {

         }

         @Override
         public void mouseEntered(MouseEvent arg0) {

            creditButton.setBackground(Color.lightGray);

         }

         @Override
         public void mouseExited(MouseEvent arg0) {

            creditButton.setBackground(Color.gray);

         }

         @Override
         public void mousePressed(MouseEvent arg0) {

            creditButton.setBackground(Color.white);

         }

         @Override
         public void mouseReleased(MouseEvent arg0) {

            creditButton.setBackground(Color.lightGray);

         }

      });
      
      exitButton.addMouseListener(new MouseListener() {

         @Override
         public void mouseClicked(MouseEvent arg0) {

            window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));

         }

         @Override
         public void mouseEntered(MouseEvent arg0) {

            exitButton.setBackground(Color.lightGray);

         }

         @Override
         public void mouseExited(MouseEvent arg0) {

            exitButton.setBackground(Color.gray);

         }

         @Override
         public void mousePressed(MouseEvent arg0) {

            exitButton.setBackground(Color.white);

         }

         @Override
         public void mouseReleased(MouseEvent arg0) {

            exitButton.setBackground(Color.lightGray);

         }

      });

   }

   @Override
   public void paintComponent(Graphics g) {

   }
   
   public static void reopenMainMenu() {
      
      window.setVisible(true);
      
   }

}