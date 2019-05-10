import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PuzzleWriter {
   
   private String title;
   private File puzzleFile;
   private SokobanRuntimeStorage puzzle;
   private int width;
   private int height;
   
   public PuzzleWriter(File f, SokobanRuntimeStorage p, String t) {
      
      title = t;
      puzzleFile = f;
      puzzle = p;
      width = puzzle.getWidth();
      height = puzzle.getHeight();
      
   }
   
   public void writePuzzle() {
      
      short value;
      String hasher;
      int hash;
      String hashLine;
      StringBuilder sb = new StringBuilder();
      sb.append("Title:");
      sb.append(title);
      String title = sb.toString();
      sb = new StringBuilder();
      String puzz;
      sb.append("Puzzle:");
      for(int i = 0; i < width; i++) {
         for(int j = 0; j < height; j++) {
            value = puzzle.getValue(i, j);
            switch((byte)value) {
            case SokobanInterpreter.EXTERNAL:
               sb.append(";");
               break;
            case SokobanInterpreter.WALL:
               sb.append("W;");
               break;
            case SokobanInterpreter.INTERNAL:
               sb.append("I;");
               break;
            case SokobanInterpreter.INTERNAL_PLAYER:
               sb.append("IP;");
               break;
            case SokobanInterpreter.INTERNAL_BOX:
               sb.append("IB;");
               break;
            case SokobanInterpreter.INTERNAL_TARGET:
               sb.append("IT;");
               break;
            case SokobanInterpreter.INTERNAL_PLAYER_TARGET:
               sb.append("IPT;");
               break;
            case SokobanInterpreter.INTERNAL_BOX_TARGET:
               sb.append("IBT;");
               break;
            }
         }
         sb.append(":");
      }
      puzz = sb.toString();
      sb = new StringBuilder();
      sb.append(puzzleFile.getPath());
      sb.append(title);
      sb.append(puzz);
      hasher = sb.toString();
      hash = HashPuzzle.genHash(hasher);
      sb = new StringBuilder();
      sb.append("Hash:");
      sb.append(hash);
      hashLine = sb.toString();
      try {
         BufferedWriter bw = new BufferedWriter(new FileWriter(puzzleFile));
         bw.append(hashLine);
         bw.append("\n");
         bw.append(title);
         bw.append("\n");
         bw.append(puzz);
         bw.close();
      } catch (IOException e) {
         System.err.println("Writing failure.");
      }
      
   }
   
}
