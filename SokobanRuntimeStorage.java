import java.util.Arrays;

/**
 * This class serves as storage for a Sokoban puzzle. It also includes
 * methods to move the player.
 * @author Daniel Moore
 */

public class SokobanRuntimeStorage {
   
   public static final int LEFT = 0;
   public static final int UP = 1;
   public static final int RIGHT = 2;
   public static final int DOWN = 3;
   private String name;
   private short puzzle[][];
   private int playerX;
   private int playerY;
   
   /**
    * Constructs a 2D array of the appropriate width and height, and saves
    * the name of the puzzle. The square value.
    * @param w
    * @param n The name of the puzzle.
    * @param w Width of the puzzle.
    * @param h Height of the puzzle.
    */
   
   public SokobanRuntimeStorage(String n, int w, int h) {
      
      name = n;
      puzzle = new short[w][h];
      
      for(int i = 0; i < w; i++) {
         
         Arrays.fill(puzzle[i], (short) 0);
         
      }
      
   }
   
   /**
    * Sets a square in a Sokoban puzzle.
    * @param s The square value.
    * @param x The column to insert the value.
    * @param y The row to insert the value. 
    */
   
   public void setSquare(short s, int x, int y) {
      
      /*
       * The bytes stored are decimal conversions of binary numbers.
       * The binary numbers are 5 digits long.
       * From smallest to largest, the bits are:
       * Wall
       * Internal space
       * Player
       * Box
       * Targetpuzzle[playerXNew][playerYNew]
       * 
       * A 0 represents no element, while a 1 represents an element of the appropriate type.
       * Not all combinations are allowed. Input interpreter should catch invalid square contents.
       * 
       * A byte value of 0 represents external space.
       */
      
      puzzle[x][y] = s;
      
   }
   
   /**
    * Removes a Sokoban puzzle storage object.
    */
   
   public void empty() {
      
      name = null;
      puzzle = null;
      
   }
   
   /**
    * Method to retrieve the name of a SokobanRuntimeStorage object.
    * @return The name of a Sokoban puzzle.
    */
   
   public String getName() {
      
      return name;
      
   }
   
   /**
    * Method to retrieve the height of a SokobanRuntimeStorage object.
    * @return The height of a Sokoban puzzle.
    */
   
   public int getHeight() {
      
      return puzzle[0].length;
      
   }
   
   /**
    * Method to retrieve the width of a SokobanRuntimeStorage object.
    * @return The width of a Sokoban puzzle.}
    */
   
   public int getWidth() {
      
      return puzzle.length;
      
   }
   
   /**
    * Method to retrieve the value of a square at a particular location
    * in a SokobanRuntimeStorage object.
    * @param x The column to retrieve from. 
    * @param y The row to retrieve from.
    * @return The value at location x, y of a Sokoban puzzle.
    */
   
   public short getValue(int x, int y) {
      
      return puzzle[x][y];
      
   }
   
   /**
    * Method to retrieve the player's x location.
    * @return The player's x location in a Sokoban puzzle.
    */
   
   public int getPlayerX() {
      
      return playerX;
      
   }
   
   /**
    * Method to set the players's x location.
    * @param x The player's x coordinate.
    */

   public void setPlayerX(int x) {
      
      playerX = x;
      
   }
   
   /**
    * Method to retrieve the player's y location.
    * @return The player's y location in a Sokoban puzzle. 
    */
   
   public int getPlayerY() {
      
      return playerY;
      
   }
   
   /**
    * Method to set the player's y location.
    * @param y The player's y coordinate. 
    */
   
   public void setPlayerY(int y) {
      
      playerY = y;
      
   }

   /**
    * Method to move the player around the SokobanRuntimeStorage object.
    * @param direction What direction to move in. Use the static
    * LEFT, RIGHT, UP, DOWN variables.
    */
   
   public void move(int direction) {
      
      int playerXNew = playerX;
      int playerYNew = playerY;
      int boxX = playerX;
      int boxY = playerY;
      
      switch (direction) {
      
      case LEFT:
         
         playerXNew = playerX - 1;
         boxX = playerX - 2;
         break;
         
      case UP:
         
         playerYNew = playerY - 1;
         boxY = playerY - 2;
         break;
         
      case RIGHT:
         
         playerXNew = playerX + 1;
         boxX = playerX + 2;
         break;
         
      case DOWN:
         
         playerYNew = playerY + 1;
         boxY = playerY + 2;
         break;
         
      default:
            
      }
      
      if (puzzle[playerXNew][playerYNew] == SokobanInterpreter.WALL) {
         
         // add bump sound effect
         return;
         
      }
      
      else if ((byte)puzzle[playerXNew][playerYNew] == SokobanInterpreter.INTERNAL_BOX || (byte)puzzle[playerXNew][playerYNew] == SokobanInterpreter.INTERNAL_BOX_TARGET) {
         
         if (puzzle[boxX][boxY] == SokobanInterpreter.WALL || (byte)puzzle[boxX][boxY] == SokobanInterpreter.INTERNAL_BOX || (byte)puzzle[boxX][boxY] == SokobanInterpreter.INTERNAL_BOX_TARGET) {
            
            // add bump sound effect
            return;   
            
         }
         
         short tempBoxValue = (short)(puzzle[playerXNew][playerYNew] / SokobanInterpreter.BOX_TRACK_OFFSET);
         puzzle[playerXNew][playerYNew] -= (SokobanInterpreter.BOX + tempBoxValue * SokobanInterpreter.BOX_TRACK_OFFSET);
         puzzle[boxX][boxY] += (SokobanInterpreter.BOX + tempBoxValue * SokobanInterpreter.BOX_TRACK_OFFSET);
         
      }
      
      puzzle[playerX][playerY] -= SokobanInterpreter.PLAYER;
      puzzle[playerXNew][playerYNew] += SokobanInterpreter.PLAYER;
      
      switch (direction) {
      
      case LEFT:
         
         playerX--;
         break;
         
      case UP:
         
         playerY--;
         break;
         
      case RIGHT:
         
         playerX++;
         break;
         
      case DOWN:
         
         playerY++;
         break;
         
      default:
      
      }
      
   }
   
   /*public void moveLeft() {
      
      if (puzzle[playerX - 1][playerY] == 1) {
         
         // add bump sound effect
         return;
         
      }
      
      else if (puzzle[playerX - 1][playerY] == 10 || puzzle[playerX - 1][playerY] == 26) {
         
         if (puzzle[playerX - 2][playerY] == 1 || puzzle[playerX - 2][playerY] == 10 || puzzle[playerX - 2][playerY] == 26) {
            
            // add bump sound effect
            return;   
            
         }
         

         puzzle[playerX - 1][playerY] -= 8;
         puzzle[playerX - 2][playerY] += 8;
         
      }
      
      puzzle[playerX][playerY] -= 4;
      puzzle[playerX - 1][playerY] += 4;
      playerX--;
      
   }

   public void moveRight() {

      if (puzzle[playerX + 1][playerY] == 1) {

         // add bump sound effect
         return;

      }

      else if (puzzle[playerX + 1][playerY] == 10 || puzzle[playerX + 1][playerY] == 26) {

         if (puzzle[playerX + 2][playerY] == 1 || puzzle[playerX + 2][playerY] == 10 || puzzle[playerX + 2][playerY] == 26) {

            // add bump sound effect
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

         // add bump sound effect
         return;

      }

      else if (puzzle[playerX][playerY - 1] == 10 || puzzle[playerX][playerY - 1] == 26) {

         if (puzzle[playerX][playerY - 2] == 1 || puzzle[playerX][playerY - 2] == 10 || puzzle[playerX][playerY - 2] == 26) {

            // add bump sound effect
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

         // add bump sound effect
         return;

      }

      else if (puzzle[playerX][playerY + 1] == 10 || puzzle[playerX][playerY + 1] == 26) {

         if (puzzle[playerX][playerY + 2] == 1 || puzzle[playerX][playerY + 2] == 10 || puzzle[playerX][playerY + 2] == 26) {

            // add bump sound effect
            return;

         }

         puzzle[playerX][playerY + 1] -= 8;
         puzzle[playerX][playerY + 2] += 8;

      }

      puzzle[playerX][playerY] -= 4;
      puzzle[playerX][playerY + 1] += 4;
      playerY++;
      
   }*/
   
}
