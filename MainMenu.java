import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
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
import javax.swing.SpringLayout;
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
   private static JPanel exitButton;
   private static JLabel exit;
   private static JLabel title;
   
   public static void main(String[] args) throws IOException {
      
      window = new JFrame("∫oκσbαπ");
      window.setUndecorated(true);
      window.setLayout(new SpringLayout());
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
      puzzles.setAlignmentY(Component.CENTER_ALIGNMENT);
      puzzles.setFont(new Font("TimesRoman", Font.BOLD + Font.ITALIC, 20));
      puzzles.setBorder(new EmptyBorder(5, 5, 5, 5));
      puzzleButton = new JPanel();
      puzzleButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      puzzleButton.setAlignmentY(0.2f);
      puzzleButton.setMixingCutoutShape(new Rectangle());
      puzzleButton.setSize(puzzles.getWidth() + 6, puzzles.getHeight() + 6);
      puzzleButton.setBackground(Color.gray);
      puzzleButton.add(puzzles);
      editor = new JLabel("Puzzle Editor");
      editor.setBackground(Color.gray);
      editor.setAlignmentX(Component.CENTER_ALIGNMENT);
      editor.setFont(new Font("TimesRoman", Font.BOLD + Font.ITALIC, 20));
      editor.setBorder(new EmptyBorder(5, 5, 5, 5));
      editorButton = new JPanel();
      editorButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      editorButton.setMixingCutoutShape(new Rectangle());
      editorButton.setSize(editor.getWidth() + 6, editor.getHeight() + 6);
      editorButton.setBackground(Color.gray);
      editorButton.add(editor);
      options = new JLabel("Options");
      options.setBackground(Color.gray);
      options.setAlignmentX(Component.CENTER_ALIGNMENT);
      options.setFont(new Font("TimesRoman", Font.BOLD + Font.ITALIC, 20));
      options.setBorder(new EmptyBorder(5, 5, 5, 5));
      optionButton = new JPanel();
      optionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      optionButton.setMixingCutoutShape(new Rectangle());
      optionButton.setSize(options.getWidth() + 6, options.getHeight() + 6);
      optionButton.setBackground(Color.gray);
      optionButton.add(options);
      exit = new JLabel("Exit");
      exit.setBackground(Color.gray);
      exit.setAlignmentX(Component.CENTER_ALIGNMENT);
      exit.setFont(new Font("TimesRoman", Font.BOLD + Font.ITALIC, 20));
      exit.setBorder(new EmptyBorder(5, 5, 5, 5));
      exitButton = new JPanel();
      exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      exitButton.setMixingCutoutShape(new Rectangle());
      exitButton.setSize(exit.getWidth() + 6, exit.getHeight() + 6);
      exitButton.setBackground(Color.gray);
      exitButton.add(exit);
      panel.add(title);
      panel.add(Box.createRigidArea(new Dimension(0,50)));
      panel.add(puzzleButton);
      panel.add(Box.createRigidArea(new Dimension(0,5)));
      panel.add(editorButton);
      panel.add(Box.createRigidArea(new Dimension(0,5)));
      panel.add(optionButton);
      panel.add(Box.createRigidArea(new Dimension(0,5)));
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
            File read = new File("TestPuzzle2.spsf");
            SokobanInterpreter s = new SokobanInterpreter(read);
            SokobanRuntimeStorage puzzle = null;
            
            try {
               
               puzzle = s.readPuzzleFile();
               
            }
            
            catch (IOException e) {
               
               e.printStackTrace();
               
            }
            
            Validation.validate(puzzle);
            DisplayPuzzle.displayWindow(puzzle);
            
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
            
            puzzleButton.setBackground(Color.gray);
            
         }
         
      });
      
      editorButton.addMouseListener(new MouseListener() {

         @Override
         public void mouseClicked(MouseEvent arg0) {
            
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
            
            editorButton.setBackground(Color.gray);
            
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
            
            optionButton.setBackground(Color.gray);
            
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
            
            exitButton.setBackground(Color.gray);
            
         }
         
      });
      
   }
   
   @Override
   public void paintComponent(Graphics g) {
      
   }
   
}