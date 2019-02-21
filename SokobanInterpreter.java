import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SokobanInterpreter {
   private File puzzle;
   
   public SokobanInterpreter(File p) {
      puzzle = p;
   }
   
   public void outputDisplay() throws IOException {
      BufferedReader b = new BufferedReader(new FileReader(puzzle));
      String interp;
      boolean comment = false;
      while ((interp = b.readLine()) != null) {
         if (interp.startsWith("Title:") && comment == false) {
            System.out.println(interp.substring(6) + "\n");
         }
         else if (interp.startsWith("Puzzle:") && comment == false) {
            outputPuzzle(interp.substring(7));
         }
         else if (interp.contains("\\*")) {
            comment = true;
         }
         else if (interp.contains("*\\") && comment == true) {
            comment = false;
         }
      }
      b.close();
   }
   public void outputPuzzle(String puzzle) {
      String nextLine = puzzle.substring(0, puzzle.indexOf("\\"));
      String remaining = puzzle.substring(puzzle.indexOf("\\") + 1);
      String nextSquare;
      char s;
      boolean eol = false;
      while (!eol) {
         while (nextLine.indexOf(";") >= 0) {
            nextSquare = nextLine.substring(0, nextLine.indexOf(";") + 1);
            nextLine = nextLine.substring(nextLine.indexOf(";") + 1);
            s = nextSquare.charAt(0);
            switch (s) {
            case 'W':
               System.out.print("WW");
               break;
            case ';':
               System.out.print("  ");
               break;
            case 'I':
               if (nextSquare.length() <= 2) {
                  System.out.print("  ");
                  break;
               }               
               if (nextSquare.contains("P")) {
                  System.out.print("PP");
               }
               if (nextSquare.contains("T")) {
                  System.out.print("T" + nextSquare.charAt(nextSquare.indexOf("T") + 1));
               }
               if (nextSquare.contains("B")) {
                  System.out.print("B" + nextSquare.charAt(nextSquare.indexOf("B") + 1));                  
               }
            }
         }
         System.out.println();
         if (remaining.indexOf("\\") >= 0) {
            nextLine = remaining.substring(0, remaining.indexOf("\\"));
            remaining = remaining.substring(remaining.indexOf("\\") + 1);
         }
         else {
            eol = true;
         }
      }
      System.out.println();
   }
}
