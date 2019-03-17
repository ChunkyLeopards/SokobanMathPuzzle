
import java.io.File;
import java.io.IOException;

public class InterpreterTester {

   public static void main(String[] args) throws IOException {
      
      File read = new File("ErrCase6b.spsf");
      SokobanInterpreter s = new SokobanInterpreter(read);
      SokobanRuntimeStorage puzzle = s.readPuzzleFile();
      if (puzzle != null)
          DisplayPuzzle.displayWindow(puzzle);
      
   }
   
}