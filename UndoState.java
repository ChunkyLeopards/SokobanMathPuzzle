import java.util.Arrays;

public class UndoState {
   
   public short state[][];
   public int x;
   public int y;
   public UndoState next = null;
   
   public UndoState(short undo[][], int px, int py) {
      
      state = Arrays.copyOf(undo, undo.length);
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
   
}
