
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SokobanInterpreter {
   
   private File puzzle;
   
   public SokobanInterpreter(File p) {
      
      puzzle = p;
      
   }
   
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
               
               puzz.setSquare((byte) 1, column, row);
               column++;
               break;
               
            case "I":
               
               puzz.setSquare((byte) 2, column, row);
               column++;
               break;
               
            case "IP":
               
               puzz.setSquare((byte) 6, column, row);
               puzz.setPlayerX(column);
               puzz.setPlayerY(row);
               column++;
               break;
               
            case "IB":
               
               puzz.setSquare((byte) 10, column, row);
               column++;
               break;
               
            case "IT":
               
               puzz.setSquare((byte) 18, column, row);
               column++;
               break;
               
            case "IPT":
               
               puzz.setSquare((byte) 22, column, row);
               puzz.setPlayerX(column);
               puzz.setPlayerY(row);
               column++;
               break;
               
            case "ITP":
               
               puzz.setSquare((byte) 22, column, row);
               puzz.setPlayerX(column);
               puzz.setPlayerY(row);
               column++;
               break;
               
            case "IBT":
               
               puzz.setSquare((byte) 26, column, row);
               column++;
               break;
               
            case "ITB":
               
               puzz.setSquare((byte) 26, column, row);
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
