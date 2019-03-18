import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Interprets and creates all necessary Sokoban objects from a file.
 * @author Daniel Moore
 */

public class SokobanInterpreter {
   
   public static final byte EXTERNAL = 0;
   public static final byte WALL = 1;
   public static final byte INTERNAL = 2;
   public static final byte PLAYER = 4;
   public static final byte INTERNAL_PLAYER = 6;
   public static final byte BOX = 8;
   public static final byte INTERNAL_BOX = 10;
   public static final byte TARGET = 16;
   public static final byte INTERNAL_TARGET = 18;
   public static final byte INTERNAL_PLAYER_TARGET = 22;
   public static final byte INTERNAL_BOX_TARGET = 26;
   private File puzzle;
   
   /**
    * Constructor that uses an input file to create an interpreter.
    * @param p The puzzle file. 
    */
   
   public SokobanInterpreter(File p) {
      
      puzzle = p;
      
   }
   
   /**
    * Reads a file with a stored Sokoban puzzle.
    * @return SokobanRuntimeStorage object of the appropriate size to store the puzzle.
    * @throws IOException
    */
   
   public SokobanRuntimeStorage readPuzzleFile() throws IOException {
      
      BufferedReader b = new BufferedReader(new FileReader(puzzle));
      String interp;
      SokobanRuntimeStorage puzzle = null;
      String title = "";
      boolean comment = false;
      boolean foundPuzzle = false;
      boolean foundTitle = false;
      
      while ((interp = b.readLine()) != null) {
         
         if (interp.startsWith("Title:") && !comment && !foundTitle) {
            
            title = interp.substring(6);
            foundTitle = true;
            
         }
         
         else if (interp.startsWith("Puzzle:") && !comment && !foundPuzzle && foundTitle) {
            
            String puzz = interp.substring(7).trim();
            int height = 0;
            int maxW = 0;
            int countW = 0;
            char input;
            
            for (int i = 0; i < puzz.length(); i++) {
               
               input = puzz.charAt(i);
               
               if (input == ':') {
                  
                  height++;
                  
                  if (countW > maxW) {
                     
                     maxW = countW;
                     
                  }
                  
                  countW = 0;
                  
               }
               
               else if (input == ';') {
                  
                  countW++;
                  
               }
               
            }
            
            puzzle = new SokobanRuntimeStorage(title, maxW, height);
            storePuzzle(puzz, puzzle);
            
            if (!Validation.validate(puzzle)) {
               
               System.err.println("Invalid level design.");
               b.close();
               return null;
               
            }
            
            foundPuzzle = true;
            
         }
         
         else if (interp.startsWith("Puzzle:") && !comment) {
            
            System.err.println("Improper .spsf file structure. Please refer to the example puzzle file for proper structure.");
            b.close();
            return null;
            
         }
         else if (interp.contains("/*")) {
            
            comment = true;
            
         }
         else if (interp.contains("*/") && comment) {
            
            comment = false;
            
         }
         
         else if (interp.isEmpty() || interp.equals("\n") || comment) {
            
            /* Disregard empty or commented lines */
            
         }
         
         else {
            
            System.out.println(interp);
            System.err.println("Improper .spsf file structure. Please check your file for errors.");
            b.close();
            return null;
            
         }
         
      }
      
      b.close();
      return puzzle;
      
   }
   
   /**
    * Method to interpret a string representing a Sokoban puzzle, and store
    * it in a runtime storage object which will be manipulated to solve the puzzle. 
    * @param puzzle string representation of a Sokoban puzzle.
    * @param puzz The runtime storage object to store the puzzle in.
    */
   
   public void storePuzzle(String puzzle, SokobanRuntimeStorage puzz) {
      
      String nextLine = puzzle.substring(0, puzzle.indexOf(":"));
      String remaining = puzzle.substring(puzzle.indexOf(":") + 1);
      String nextSquare;
      int column = 0;
      int row = 0;
      boolean eol = false;
      
      while (!eol) {
         
         while (nextLine.indexOf(";") >= 0) {
            
            nextSquare = nextLine.substring(0, nextLine.indexOf(";"));
            nextLine = nextLine.substring(nextLine.indexOf(";") + 1);
            switch (nextSquare) {
            
            case "":
               
               column++;
               break;
               
            case "W":
               
               puzz.setSquare(WALL, column, row);
               column++;
               break;
               
            case "I":
               
               puzz.setSquare(INTERNAL, column, row);
               column++;
               break;
               
            case "IP":
               
               puzz.setSquare(INTERNAL_PLAYER, column, row);
               puzz.setPlayerX(column);
               puzz.setPlayerY(row);
               column++;
               break;
               
            case "IB":
               
               puzz.setSquare(INTERNAL_BOX, column, row);
               column++;
               break;
               
            case "IT":
               
               puzz.setSquare(INTERNAL_TARGET, column, row);
               column++;
               break;
               
            case "IPT":
            case "ITP":
               
               puzz.setSquare(INTERNAL_PLAYER_TARGET, column, row);
               puzz.setPlayerX(column);
               puzz.setPlayerY(row);
               column++;
               break;
               
            case "IBT":
            case "ITB":
               
               puzz.setSquare(INTERNAL_BOX_TARGET, column, row);
               column++;
               break;
               
            default:
               
               System.err.println("Invalid square contents. Please refer to the example file for proper square contents.");
               puzz.empty();
               return;
               
            }
                        
         }
         

         column = 0;
         row++;
         
         if (remaining.indexOf(":") >= 0) {
            
            nextLine = remaining.substring(0, remaining.indexOf(":"));
            remaining = remaining.substring(remaining.indexOf(":") + 1);
            
         }
         
         else {
            
            eol = true;
            
         }
         
      }
      
   }
   
}
