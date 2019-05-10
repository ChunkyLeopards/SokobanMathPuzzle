
public class UndoStack {

   private UndoState head;

   public UndoStack(short undo[][], int x, int y) {

      head = new UndoState(undo, x, y);

   }

   public UndoState pop() {

      UndoState temp = head;

      if (head.getNext() != null) {

         head = head.getNext();

      }

      return temp;

   }

   public void push(UndoState undo) {

      undo.setNext(head);
      head = undo;

   }

   public int size() {

      int count = 0;
      UndoState temp = head;

      while (temp != null) {

         temp = temp.getNext();
         count++;

      }

      return count;

   }

}
