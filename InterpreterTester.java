import java.io.File;
import java.io.IOException;

public class InterpreterTester {

   public static void main(String[] args) throws IOException {
      
      File read = new File("DefaultTestPuzzle.spsf");
      SokobanInterpreter s = new SokobanInterpreter(read);
      SokobanRuntimeStorage puzzle = s.readPuzzleFile();
      DisplayPuzzle.displayWindow(puzzle);
      
   }
   
}