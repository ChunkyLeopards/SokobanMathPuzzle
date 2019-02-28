import java.util.Arrays;

public class SokobanRuntimeStorage {
   
   private String name;
   private byte puzzle[][];
   
   public SokobanRuntimeStorage(String n, int w, int h) {
      
      name = n;
      puzzle = new byte[w][h];
      
      for(int i = 0; i < w; i++) {
         
         Arrays.fill(puzzle[i], (byte) 0);
         
      }
      
   }
   
   public void setSquare(byte s, int w, int h ) {
      
      /*
       * The bytes stored are decimal conversions of binary numbers.
       * The binary numbers are 5 digits long.
       * From smallest to largest, the bits are:
       * Wall
       * Internal space
       * Player
       * Box
       * Target
       * 
       * A 0 represents no element, while a 1 represents an element of the appropriate type.
       * Not all combinations are allowed. Input interpreter should catch invalid square contents.
       * 
       * A byte value of 0 represents external space.
       */
      
      puzzle[w][h] = s;
      
   }
   
   public void empty() {
      
      name = null;
      puzzle = null;
      
   }
   
   public String getName() {
      
      return name;
      
   }
   
   public int getHeight() {
      
      return puzzle[0].length;
      
   }
   
   public int getWidth() {
      
      return puzzle.length;
      
   }
   
   public byte getValue(int w, int h) {
      
      return puzzle[w][h];
      
   }
   
}
