import javax.swing.JDialog;
import javax.swing.JLabel;

public class SokobanEvents {

   private static JDialog event;
   private static JLabel text;

   public static void win() {

      event = new JDialog();
      event.setTitle("Winner!");
      text = new JLabel("You solved the puzzle. Congratulations!");
      event.add(text);
      event.pack();
      event.setVisible(true);

   }

   public static void stuck() {

      event = new JDialog();
      event.setTitle("Stuck?");
      text = new JLabel("You appear to be stuck. Undo?");
      event.add(text);
      event.pack();
      event.setVisible(true);

   }

}
