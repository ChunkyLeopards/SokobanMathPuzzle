
public class MathInputSolver {
   
   public static void solveMath(String input, String answer) {
      
      
      
   }
   
   public static int gcd(int x, int y) {
      
      if(x == y) {
         
         return x;
         
      }
      
      if(x > y) {
         
         return gcd(x - y, y);
         
      }
      
      return gcd(y - x, x);
      
   }
   
   public static void main(String args[]) {
      
      
      
   }
   
}
