import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class MathTemplateInterpreter {

   private File template[];
   private String sections[];
   private String problem;
   private RandomConstraint rNum[];
   private float randFloats[];
   private int randInts[];

   public MathTemplateInterpreter() {

      template = listTemplates(new File("templates"));
      File rTemplate = template[7/* (int)(Math.random() * template.length) */];
      getSections(rTemplate);
      genProblem();

   }

   public File[] listTemplates(File directory) {

      File subFiles[] = directory.listFiles();
      File allSubFiles[] = new File[0];
      int index = 0;

      for (int i = 0; i < subFiles.length; i++) {

         if (subFiles[i].isDirectory()) {

            File temp[] = listTemplates(subFiles[i]);
            allSubFiles = Arrays.copyOf(allSubFiles, allSubFiles.length + temp.length);

            for (int j = 0; j < temp.length; j++, index++) {

               allSubFiles[index] = temp[j];

            }

         } else {

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

         while ((nextLine = b.readLine()) != null) {

            section.append(nextLine.substring(0, nextLine.indexOf(":")));
            temp = Arrays.copyOf(temp, temp.length + 1);
            temp[temp.length - 1] = section.toString();
            section.delete(0, section.length());
            nextLine = nextLine.substring(nextLine.indexOf(":") + 1);
            delimCount = 1;

            while (delimCount > 0) {

               if (nextLine.isEmpty()) {

                  nextLine = b.readLine();

               } else {

                  nextChar = nextLine.substring(0, 1);
                  nextLine = nextLine.substring(1, nextLine.length());

                  switch (nextChar) {
                  case ":":
                     delimCount++;
                     section.append(nextChar);
                     break;
                  case ";":
                     delimCount--;

                     if (delimCount != 0) {

                        section.append(nextChar);

                     }

                     break;
                  case " ":
                     if (temp[temp.length - 1].equals("Description")) {
                        section.append(" ");
                     }
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

   public void genProblem() {

      int pIndex = 0;
      int rIndex = 0;
      String preProblem = "";
      StringBuilder randomProblem = new StringBuilder();
      String constraints = "";
      rNum = new RandomConstraint[0];

      for (int index = 0; index < sections.length; index += 2) {

         if (sections[index].equals("Problem")) {

            pIndex = index + 1;

         }

         if (sections[index].equals("RandomConstraints")) {

            rIndex = index + 1;

         }

         if (pIndex != 0 && rIndex != 0) {

            break;

         }

      }

      if (pIndex == 0) {

         System.err.println("Problem does not exist.");

      }

      else {

         preProblem = sections[pIndex];

      }

      if (rIndex != 0) {

         constraints = sections[rIndex];

      }

      while (!constraints.isEmpty()) {

         rNum = Arrays.copyOf(rNum, rNum.length + 1);
         RandomConstraint r = new RandomConstraint();
         r.setName(constraints.substring(0, constraints.indexOf(':')));
         constraints = constraints.substring(constraints.indexOf("type:"));
         String type = constraints.substring(constraints.indexOf(':') + 1, constraints.indexOf(';'));
         constraints = constraints.substring(constraints.indexOf(';') + 1);

         if (type.equals("i")) {

            r.setType(RandomConstraint.INT);

         } else {

            r.setType(RandomConstraint.FLOAT);

         }

         int indexEnd = findEnd(constraints);
         String rel = constraints.substring(0, indexEnd - 1);
         constraints = constraints.substring(indexEnd);
         String range = rel.substring(rel.indexOf(':') + 1);
         indexEnd = findEnd(range);
         rel = range.substring(indexEnd);
         range = range.substring(0, indexEnd - 1);
         String rangeMin = range.substring(0, range.indexOf(';'));
         String rangeMax = range.substring(range.indexOf("max:"), range.length() - 1);
         rangeMin = rangeMin.substring(rangeMin.indexOf(':') + 1, rangeMin.length());
         rangeMax = rangeMax.substring(rangeMax.indexOf(':') + 1, rangeMax.length());
         r.setMinRange(Float.parseFloat(rangeMin));
         r.setMaxRange(Float.parseFloat(rangeMax));
         String neq = rel.substring(rel.indexOf(':') + 1);
         indexEnd = findEnd(neq);
         rel = neq.substring(indexEnd);
         neq = neq.substring(0, indexEnd - 1);

         if (r.getType() == RandomConstraint.FLOAT) {

            String precision = "";
            precision = rel.substring(rel.indexOf("precision"));
            rel = rel.substring(0, rel.indexOf(";") + 1);
            r.setPrecision(Float.parseFloat(precision.substring(precision.indexOf(':') + 1, precision.indexOf(';'))));

         }

         while (!neq.isEmpty()) {

            String nextConstraint = "";

            if (neq.indexOf(',') < 0) {

               nextConstraint = neq.substring(0, neq.length());
               neq = "";

            } else {

               nextConstraint = neq.substring(0, neq.indexOf(','));
               neq = neq.substring(neq.indexOf(',') + 1);

            }

            r.addExcludedValue(Float.parseFloat(nextConstraint));

         }

         rNum[rNum.length - 1] = r;

      }

      randFloats = new float[rNum.length];
      randInts = new int[rNum.length];

      for (int i = 0; i < rNum.length; i++) {

         if (rNum[i].getType() == RandomConstraint.INT) {

            randInts[i] = rNum[i].generateRandInt();

         } else {

            randFloats[i] = rNum[i].generateRandFloat();

         }

      }

      while (!preProblem.isEmpty()) {

         if (preProblem.indexOf('\"') < 0) {

            randomProblem.append(preProblem);
            preProblem = "";

         } else {

            randomProblem.append(preProblem.substring(0, preProblem.indexOf('\"')));
            preProblem = preProblem.substring(preProblem.indexOf('\"') + 1);

            for (int i = 0; i < rNum.length; i++) {

               if (rNum[i].getName().equals(preProblem.substring(0, preProblem.indexOf('\"')))) {

                  if (rNum[i].getType() == RandomConstraint.INT) {

                     randomProblem.append(randInts[i]);

                  } else {

                     randomProblem.append(randFloats[i]);

                  }

               }

            }

            preProblem = preProblem.substring(preProblem.indexOf('\"') + 1);

         }

      }

      problem = randomProblem.toString();

   }

   public int findEnd(String s) {

      int index = 0;
      int deLim = 1;
      while (deLim > 0) {

         switch (s.charAt(index)) {

         case ':':
            deLim++;
            break;
         case ';':
            deLim--;
            break;

         }

         index++;

      }

      return index;

   }

   public String getProblem() {

      return problem;

   }

   public String getDescription() {

      String description = "";

      for (int index = 0; index < sections.length; index += 2) {

         if (sections[index].equals("Description")) {

            description = sections[index + 1];

         }

      }

      return description;

   }

}