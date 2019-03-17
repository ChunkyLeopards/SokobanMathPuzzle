import java.awt.Toolkit;
import java.util.Arrays;

public class SokobanRuntimeStorage {
   
   private String name;
   private byte puzzle[][];
   private int playerX;
   private int playerY;
   
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

   public int getPlayerX() {
      
      return playerX;
      
   }

   public void setPlayerX(int x) {
      
      playerX = x;
      
   }

   public int getPlayerY() {
      
      return playerY;
      
   }

   public void setPlayerY(int y) {
      
      playerY = y;
      
   }
   
   public void moveLeft() {
      
      if (puzzle[playerX - 1][playerY] == 1) {
         
         Toolkit.getDefaultToolkit().beep();
         return;
         
      }
      
      else if (puzzle[playerX - 1][playerY] == 10 || puzzle[playerX - 1][playerY] == 26) {
         
         if (puzzle[playerX - 2][playerY] == 1 || puzzle[playerX - 2][playerY] == 10 || puzzle[playerX - 2][playerY] == 26) {
            
            Toolkit.getDefaultToolkit().beep();
            return;   
            
         }
         

         puzzle[playerX - 1][playerY] -= 8; // remove box from space to left
         puzzle[playerX - 2][playerY] += 8; // move box onto next space
         
      }
      
      puzzle[playerX][playerY] -= 4; // remove player from current location
      puzzle[playerX - 1][playerY] += 4; // player location one to the left
      playerX--; // update player x location
      
   }

   public void moveRight() {

      if (puzzle[playerX + 1][playerY] == 1) {

         Toolkit.getDefaultToolkit().beep();
         return;

      }

      else if (puzzle[playerX + 1][playerY] == 10 || puzzle[playerX + 1][playerY] == 26) {

         if (puzzle[playerX + 2][playerY] == 1 || puzzle[playerX + 2][playerY] == 10 || puzzle[playerX + 2][playerY] == 26) {

            Toolkit.getDefaultToolkit().beep();
            return;

         }

         puzzle[playerX + 1][playerY] -= 8;
         puzzle[playerX + 2][playerY] += 8;

      }

      puzzle[playerX][playerY] -= 4;
      puzzle[playerX + 1][playerY] += 4;
      playerX++;

   }

   public void moveUp() {

      if (puzzle[playerX][playerY - 1] == 1) {

         Toolkit.getDefaultToolkit().beep();
         return;

      }

      else if (puzzle[playerX][playerY - 1] == 10 || puzzle[playerX][playerY - 1] == 26) {

         if (puzzle[playerX][playerY - 2] == 1 || puzzle[playerX][playerY - 2] == 10 || puzzle[playerX][playerY - 2] == 26) {

            Toolkit.getDefaultToolkit().beep();
            return;

         }

         puzzle[playerX][playerY - 1] -= 8;
         puzzle[playerX][playerY - 2] += 8;

      }

      puzzle[playerX][playerY] -= 4;
      puzzle[playerX][playerY - 1] += 4;
      playerY--;

   }

   public void moveDown() {

      if (puzzle[playerX][playerY + 1] == 1) {

         Toolkit.getDefaultToolkit().beep();
         return;

      }

      else if (puzzle[playerX][playerY + 1] == 10 || puzzle[playerX][playerY + 1] == 26) {

         if (puzzle[playerX][playerY + 2] == 1 || puzzle[playerX][playerY + 2] == 10 || puzzle[playerX][playerY + 2] == 26) {

            Toolkit.getDefaultToolkit().beep();
            return;

         }

         puzzle[playerX][playerY + 1] -= 8;
         puzzle[playerX][playerY + 2] += 8;

      }

      puzzle[playerX][playerY] -= 4;
      puzzle[playerX][playerY + 1] += 4;
      playerY++;
      
   }
   
}
