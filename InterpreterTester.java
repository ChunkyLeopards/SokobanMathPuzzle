import java.io.File;
import java.io.IOException;

public class InterpreterTester {

   public static void main(String[] args) throws IOException {
      
      File read = new File("TestPuzzle2.spsf");
      SokobanInterpreter s = new SokobanInterpreter(read);
      SokobanRuntimeStorage puzzle = s.readPuzzleFile();
      Validation.validate(puzzle);
      DisplayPuzzle.displayWindow(puzzle);
      
   }
   
}