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
   String problem;
   
   public MathTemplateInterpreter() {
      
      template = listTemplates(new File("templates"));
      File rTemplate = template[7/*(int)(Math.random() * template.length)*/];
      getSections(rTemplate);
      getProblem();
      
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
      String nextChar;
      int delimCount = 0;
      
      try {
         
         while((nextLine = b.readLine()) != null) {
            
            section.append(nextLine.substring(0,  nextLine.indexOf(":")));
            temp = Arrays.copyOf(temp, temp.length + 1);
            temp[temp.length - 1] = section.toString();
            section.delete(0, section.length());
            nextLine = nextLine.substring(nextLine.indexOf(":") + 1);
            delimCount = 1;
            
            while(delimCount > 0) {
               
               if(nextLine.isEmpty()) {
                
                  nextLine = b.readLine();
                  
               } else {
                  
                  nextChar = nextLine.substring(0, 1);
                  nextLine = nextLine.substring(1, nextLine.length());
                  
                  switch(nextChar) {
                  case ":":
                     delimCount++;
                     section.append(nextChar);
                     break;
                  case ";":
                     delimCount--;
                     
                     if(delimCount != 0) {
                        
                        section.append(nextChar);
                     
                     }
                     break;
                  case " ":
                     break;
                  default:
                     section.append(nextChar);
                  }
                  
               }
               
            }
            
            temp = Arrays.copyOf(temp, temp.length + 1);
            temp[temp.length - 1] = section.toString();
            section.delete(0, section.length());
            
         }
         
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
      sections = temp;
      
   }
   
   public void getProblem() {
      
      int pIndex = 0;
      int rIndex = 0;
      String problem;
      String constraints;
      
      for(int index = 0; index < sections.length; index += 2) {
         
         if(sections[index].equals("Problem")) {
            
            pIndex = index + 1;
            
         }

         if(sections[index].equals("RandomConstraints")) {
            
            rIndex = index + 1;
            
         }
         
         if(pIndex != 0 && rIndex != 0) {
            
            break;
            
         }
         
      }
      
      problem = sections[pIndex];
      constraints = sections[rIndex];
      
   }
   
}
