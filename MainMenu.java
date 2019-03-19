import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
public class MainMenu extends JPanel {
   
   private static JFrame window;
   private static JPanel panel;
   private static JButton puzzles;
   private static JButton editor;
   private static JButton options;
   private static JTextArea title;
   
   public static void main(String[] args) throws IOException {
            
      window = new JFrame("∫oκσbαπ");
      panel = new JPanel();
      title = new JTextArea();
      title.setEditable(false);
      title.setText("∫oκσbαπ");
      title.setOpaque(false);
      title.setFont(new Font("TimesRoman", Font.BOLD + Font.ITALIC, 80));
      title.setSize(title.getMinimumSize());
      panel.setSize(750, 750);
      panel.setBackground(Color.red);
      title.setLocation(panel.getWidth() / 2 - title.getMinimumSize().width / 2, panel.getHeight() / 8 + title.getMinimumSize().height / 2);
      puzzles = new JButton("Puzzles");
      puzzles.setSize(puzzles.getMinimumSize());
      puzzles.setLocation(panel.getWidth() / 2 - puzzles.getMinimumSize().width / 2, panel.getHeight() / 4 - puzzles.getMinimumSize().height / 2);
      editor = new JButton("Puzzle Editor");
      editor.setSize(editor.getMinimumSize());
      editor.setLocation(panel.getWidth() / 2 - editor.getMinimumSize().width / 2, panel.getHeight() / 2 - editor.getMinimumSize().height / 2);
      options = new JButton("Options");
      options.setSize(options.getMinimumSize());
      options.setLocation(panel.getWidth() / 2 - options.getMinimumSize().width / 2, (panel.getHeight() / 4) * 3 - options.getMinimumSize().height / 2);
      window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      panel.add(title);
      panel.add(puzzles);
      panel.add(editor);
      panel.add(options);
      new MainMenu(panel);
      window.add(panel);
      window.setSize(panel.getSize());
      window.setVisible(true);
      
   }
   
   public MainMenu(JPanel p) {
      
      puzzles.addMouseListener(new MouseListener() {

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
            
         }

         @Override
         public void mouseExited(MouseEvent arg0) {
            
         }

         @Override
         public void mousePressed(MouseEvent arg0) {
            
         }

         @Override
         public void mouseReleased(MouseEvent arg0) {
            
         }
         
      });
      
      editor.addMouseListener(new MouseListener() {

         @Override
         public void mouseClicked(MouseEvent arg0) {
            
         }

         @Override
         public void mouseEntered(MouseEvent arg0) {
            
         }

         @Override
         public void mouseExited(MouseEvent arg0) {
            
         }

         @Override
         public void mousePressed(MouseEvent arg0) {
            
         }

         @Override
         public void mouseReleased(MouseEvent arg0) {
            
         }
         
      });
      
      options.addMouseListener(new MouseListener() {

         @Override
         public void mouseClicked(MouseEvent arg0) {
            
         }

         @Override
         public void mouseEntered(MouseEvent arg0) {
            
         }

         @Override
         public void mouseExited(MouseEvent arg0) {
            
         }

         @Override
         public void mousePressed(MouseEvent arg0) {
            
         }

         @Override
         public void mouseReleased(MouseEvent arg0) {
            
         }
         
      });
      
   }
   
   @Override
   public void paintComponent(Graphics g) {
      
   }
   
}