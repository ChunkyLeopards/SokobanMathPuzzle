import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class MathTemplateInterpreter {
   
   File template[];
   int weight[];
   String sections[];
   
   public MathTemplateInterpreter() {
      
      template = listTemplates(new File("templates"));
      File rTemplate = template[(int)(Math.random() * template.length)];
      getSections(rTemplate);
      
   }
   
   public File[] listTemplates(File directory) {
      
      File subFiles[] = directory.listFiles();
      File allSubFiles[] = new File[0];
      int index = 0;
      
      for(int i = 0; i < subFiles.length; i++) {
         
         if(subFiles[i].isDirectory()) {
            
            File temp[] = listTemplates(subFiles[i]);
            allSubFiles = Arrays.copyOf(allSubFiles, allSubFiles.length + temp.length);
            
            for(int j = 0; j < temp.length; j++, index++) {
                              
               allSubFiles[index] = temp[j];
               
            }
            
         }
         else {
            
            allSubFiles = Arrays.copyOf(allSubFiles, allSubFiles.length + 1);
            allSubFiles[index] = subFiles[i];
            index++;
            
         }
         
      }
      
      return allSubFiles;
      
   }
   
   public void getSections(File template) {
      
      BufferedReader b = null;
      try {
         b = new BufferedReader(new FileReader(template));
      } catch (FileNotFoundException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      }      
      String temp[] = new String[0]; 
      StringBuilder section = new StringBuilder();
      String nextLine;
      char nextChar;
      int delimCount = 0;
      try {
         while((nextLine = b.readLine()) != null) {
            
            while(delimCount > 0) {
               
            }
         }
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      sections = temp;
      
   }
   
}
