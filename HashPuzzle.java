import java.io.*;

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
   
}
