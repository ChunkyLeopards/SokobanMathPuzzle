import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class TestInput {

   private static JFrame window;
   private static Input in;

   public static void main(String[] args) {

      window = new JFrame();
      in = new Input();
      window.add(in.getPanel());
      window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      window.pack();
      window.setSize(1000, 100);
      window.setVisible(true);

   }

}
