import java.io.*;
import java.util.Arrays;

/**
 * Methods for generating, storing and checking hashcodes for a puzzle
 * 
 * @author Cyrus Baker
 *
 */
public class HashPuzzle {

   /**
    * generate a string of the contents of a file
    * 
    * @param f
    * @return
    * @throws IOException
    */
   public static String genString(File f) {
      BufferedReader reader = null;
      try {
         reader = new BufferedReader(new FileReader(f));
      } catch (FileNotFoundException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      }
      String line = null;
      StringBuilder sb = new StringBuilder();

      sb.append(f.getName());

      try {
         while ((line = reader.readLine()) != null) {
            if (!line.startsWith("Hash:")) {
               sb.append(line);
            }
         }
         return sb.toString();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         try {
            reader.close();
         } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      return "";
   }

   /**
    * Generates a hash code from a file as a string
    * 
    * 
    * @param f
    * @return
    * @throws IOException
    */
   public static int genHash(File f) {
      // default no file, hash set to -1
      int v = -1;
      boolean bool = false;

      bool = f.exists();

      if (bool) {
         
         // turn file contents to string
         String hashStr = genString(f);
         v = hashStr.hashCode();

      }
      else {

         System.err.println("File not found");
         
      }
      
      return v;
      
   }

   public static int genHash(String s) {
      return s.hashCode();
   }

   /**
    * Reads the hash from a line in a puzzle file
    * 
    * @param f
    * @return
    * @throws IOException
    */
   public static int retrieveHashFromFile(File f) {
      BufferedReader reader = null;
      try {
         reader = new BufferedReader(new FileReader(f));
      } catch (FileNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      String line = null;
      StringBuilder sb = new StringBuilder();

      String v;

      try {
         try {
            while ((line = reader.readLine()) != null) {
               if (line.startsWith("Hash:")) {
                  sb.append(line);

               }
            }
         } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
         if (sb.length() > 5) {
            v = sb.toString().substring(5);

            return Integer.parseInt(v);
         }
      } finally {
         try {
            reader.close();
         } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      return -1;

   }
   
   public static void main(String args[]) {
      
      String test1 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890-=!@#$%^&*()_+;:,.?";
      int  test1hash = genHash(test1);
      File f = new File("puzzles/TestingHashAgain.spsf");
      File f2 = new File("puzzles/TestHash2");
      System.out.println("Test 1: Java string input");
      System.out.println("\tString is: " + test1);
      System.out.println("\tHash code is: " + test1hash);
      System.out.println("\tByte values are: " + Arrays.toString(test1.getBytes()) + "\n");
      System.out.println("Test 2: Shared file");
      System.out.println("\tString retrieved from file is: " + genString(f));
      System.out.println("\tHash code is: " + genHash(f));
      System.out.println("\tByte values are: " + Arrays.toString(genString(f).getBytes()) + "\n");
      System.out.println("Test 2: Remade file");
      System.out.println("\tString retrieved from file is: " + genString(f2));
      System.out.println("\tHash code is: " + genHash(f2));
      System.out.println("\tByte values are: " + Arrays.toString(genString(f2).getBytes()));
      
   }
}
