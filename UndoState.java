import java.util.Arrays;

public class UndoState {

   public short state[][];
   public int x;
   public int y;
   public UndoState next = null;

   public UndoState(short undo[][], int px, int py) {
      state = new short[undo.length][undo[0].length];

      for (int i = 0; i < undo.length; i++) {

         state[i] = Arrays.copyOf(undo[i], undo[i].length);

      }

      x = px;
      y = py;

   }

   public void setNext(UndoState n) {

      next = n;

   }

   public UndoState getNext() {

      return next;

   }

   public short[][] getState() {

      return state;

   }

   public int getX() {

      return x;

   }

   public int getY() {

      return y;

   }

   public String toString() {

      return "The player was at " + x + ", " + y;

   }

}
