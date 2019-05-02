import java.util.Arrays;

public class LaTeXInputRepresentation {
   
   private String highlightColor = "\\definecolor{lblue}{rgb}{0, 0.15, 1}";
   private String setHighlightStart = "\\fgcolor{white}{\\bgcolor{lblue}{";
   private boolean activeSelection;
   private int selectionIndex;
   private int cursorLocationIndex;
   private int possibleCursorLocations[];
   private String LaTeX;
   
   public LaTeXInputRepresentation() {
      
      activeSelection = false;
      selectionIndex = 0;
      cursorLocationIndex = 0;
      possibleCursorLocations = new int[1];
      possibleCursorLocations[0] = 0;
      LaTeX = "";
      
   }

   public LaTeXInputRepresentation(LaTeXInputRepresentation p) {
      
      activeSelection = false;
      selectionIndex = 0;
      cursorLocationIndex = 0;
      possibleCursorLocations = new int[1];
      possibleCursorLocations[0] = 0;
      LaTeX = "";
      
   }
   
   public void addFormula(String f) {
      
      StringBuilder newLaTeX = new StringBuilder();
      newLaTeX.append(LaTeX.substring(0, possibleCursorLocations[cursorLocationIndex]));
      switch(f) {
      case "Lowercase Eta":
         newLaTeX.append("\\eta ");
         newLaTeX.append(LaTeX.substring(possibleCursorLocations[cursorLocationIndex]));
         possibleCursorLocations = Arrays.copyOf(possibleCursorLocations, possibleCursorLocations.length + 1);
         for(int i = possibleCursorLocations.length - 1; i > cursorLocationIndex; i--) {
            possibleCursorLocations[i] = possibleCursorLocations[i - 1];
            possibleCursorLocations[i] += 5;
         }
         cursorLocationIndex++;
         possibleCursorLocations[cursorLocationIndex] = possibleCursorLocations[cursorLocationIndex - 1] + 5;
         break;
      case "^{}":
         System.out.println(LaTeX);
         newLaTeX.append(f);
         newLaTeX.append(LaTeX.substring(possibleCursorLocations[cursorLocationIndex]));
         possibleCursorLocations = Arrays.copyOf(possibleCursorLocations, possibleCursorLocations.length + 2);
         if(cursorLocationIndex != 0) {
            for(int i = possibleCursorLocations.length - 1; i > cursorLocationIndex; i--) {
               possibleCursorLocations[i] = possibleCursorLocations[i - 2];
               possibleCursorLocations[i] += f.length();
            }
         }
         cursorLocationIndex++;
         possibleCursorLocations[cursorLocationIndex] = possibleCursorLocations[cursorLocationIndex - 1] + f.length() - 1;
         possibleCursorLocations[cursorLocationIndex + 1] = possibleCursorLocations[cursorLocationIndex] + 1;
         System.out.println(LaTeX);
         break;
      case "\\~{}":
      case "\\@ ":
      case "\\$ ":
      case "\\% ":
      case "\\& ":
      case "\\_ ":
      case "\\{ ":
      case "\\} ":
      case "\\| ":
         newLaTeX.append(f);
         newLaTeX.append(LaTeX.substring(possibleCursorLocations[cursorLocationIndex]));
         possibleCursorLocations = Arrays.copyOf(possibleCursorLocations, possibleCursorLocations.length + 1);
         for(int i = possibleCursorLocations.length - 1; i > cursorLocationIndex; i--) {
            possibleCursorLocations[i] = possibleCursorLocations[i - 1];
            possibleCursorLocations[i] += f.length();
         }
         cursorLocationIndex++;
         possibleCursorLocations[cursorLocationIndex] = possibleCursorLocations[cursorLocationIndex - 1] + f.length();
         break;
      default:
         System.out.println(f);
      }
      LaTeX = newLaTeX.toString();
      
   }
   
   public void addChar(char c) {
      
      StringBuilder newLaTeX = new StringBuilder();
      newLaTeX.append(LaTeX.substring(0, possibleCursorLocations[cursorLocationIndex]));
      newLaTeX.append(String.valueOf(c));
      newLaTeX.append(LaTeX.substring(possibleCursorLocations[cursorLocationIndex]));
      LaTeX = newLaTeX.toString();
      possibleCursorLocations = Arrays.copyOf(possibleCursorLocations, possibleCursorLocations.length + 1);
      for(int i = possibleCursorLocations.length - 1; i > cursorLocationIndex; i--) {
         possibleCursorLocations[i] = possibleCursorLocations[i - 1];
         possibleCursorLocations[i]++;
      }
      cursorLocationIndex++;
      //possibleCursorLocations[cursorLocationIndex] = possibleCursorLocations[cursorLocationIndex - 1] + 1;
      
   }
   
   public void remove(boolean forward) {
      
      StringBuilder deletedString = new StringBuilder();
      
      System.out.println(Arrays.toString(possibleCursorLocations));
      System.out.println(LaTeX);
      System.out.println(cursorLocationIndex);
      
      if(!activeSelection) {
         
         if(forward) {
            
            if(cursorLocationIndex < possibleCursorLocations.length - 1) {
               
               System.out.println("in delete");
               
               int diffLength = possibleCursorLocations[cursorLocationIndex] - possibleCursorLocations[cursorLocationIndex - 1];               
               deletedString.append(LaTeX.substring(0, possibleCursorLocations[cursorLocationIndex]));
               deletedString.append(LaTeX.substring(possibleCursorLocations[cursorLocationIndex + 1]));
               LaTeX = deletedString.toString();
               for(int i = cursorLocationIndex; i < possibleCursorLocations.length - 1; i++) {
                  possibleCursorLocations[i] = possibleCursorLocations[i + 1] - diffLength;
               }
               possibleCursorLocations = Arrays.copyOf(possibleCursorLocations, possibleCursorLocations.length - 1);
               
            }
            
         }
         else {
            
            if(cursorLocationIndex > 0) {
               
               System.out.println("in backspace");
               
               int diffLength = possibleCursorLocations[cursorLocationIndex] - possibleCursorLocations[cursorLocationIndex - 1];
               deletedString.append(LaTeX.substring(0, possibleCursorLocations[cursorLocationIndex - 1]));
               deletedString.append(LaTeX.substring(possibleCursorLocations[cursorLocationIndex]));
               LaTeX = deletedString.toString();
               for(int i = cursorLocationIndex - 1; i < possibleCursorLocations.length - 1; i++) {
                  possibleCursorLocations[i] = possibleCursorLocations[i + 1] - diffLength;
               }
               cursorLocationIndex--;
               possibleCursorLocations = Arrays.copyOf(possibleCursorLocations, possibleCursorLocations.length - 1);
               
            }
            
         }
         
      }
      else {
         
         if(cursorLocationIndex > selectionIndex) {
            
            int indexDiff = cursorLocationIndex - selectionIndex - 1;
            int diffLength = possibleCursorLocations[cursorLocationIndex] - possibleCursorLocations[selectionIndex + 1];
            System.out.println(diffLength);
            deletedString.append(LaTeX.substring(0, possibleCursorLocations[selectionIndex]));
            deletedString.append(LaTeX.substring(possibleCursorLocations[cursorLocationIndex], possibleCursorLocations.length - 1));
            LaTeX = deletedString.toString();
            for(int i = selectionIndex; i < possibleCursorLocations.length - indexDiff; i++) {
               possibleCursorLocations[i] = possibleCursorLocations[i + indexDiff] - diffLength;
            }
            possibleCursorLocations = Arrays.copyOf(possibleCursorLocations, possibleCursorLocations.length - indexDiff - 1);
            cursorLocationIndex = selectionIndex;
            activeSelection = false;
            
         }
         else {
            
            int indexDiff = selectionIndex - cursorLocationIndex - 1;
            int diffLength = possibleCursorLocations[selectionIndex] - possibleCursorLocations[cursorLocationIndex + 1];
            System.out.println(diffLength);
            deletedString.append(LaTeX.substring(0, possibleCursorLocations[cursorLocationIndex]));
            deletedString.append(LaTeX.substring(possibleCursorLocations[selectionIndex], possibleCursorLocations.length - 1));
            LaTeX = deletedString.toString();
            for(int i = cursorLocationIndex; i < possibleCursorLocations.length - indexDiff; i++) {
               possibleCursorLocations[i] = possibleCursorLocations[i + indexDiff] - diffLength;
            }
            possibleCursorLocations = Arrays.copyOf(possibleCursorLocations, possibleCursorLocations.length - indexDiff - 1);
            activeSelection = false;
            
         }
         
      }
      
      System.out.println(Arrays.toString(possibleCursorLocations));
      System.out.println(LaTeX);
      System.out.println(cursorLocationIndex);
      
   }
   
   public String buildLaTeX() {
      
      StringBuilder returnable = new StringBuilder();
      if(activeSelection) {
         if(cursorLocationIndex > selectionIndex) {
            
            returnable.append(highlightColor);
            returnable.append(LaTeX.substring(0, possibleCursorLocations[selectionIndex]));
            returnable.append(setHighlightStart);
            returnable.append(LaTeX.substring(possibleCursorLocations[selectionIndex], possibleCursorLocations[cursorLocationIndex]));
            returnable.append("\\hspace{-2}\\vert\\hspace{-2}}}");
            returnable = returnable.append(LaTeX.substring(possibleCursorLocations[cursorLocationIndex]));
            
         }
         else if(cursorLocationIndex < selectionIndex) {
            
            returnable.append(highlightColor);
            returnable.append(LaTeX.substring(0, possibleCursorLocations[cursorLocationIndex]));
            returnable.append(setHighlightStart);
            returnable.append("\\hspace{-2}\\vert\\hspace{-2}");
            returnable.append(LaTeX.substring(possibleCursorLocations[cursorLocationIndex], possibleCursorLocations[selectionIndex]));
            returnable.append("}}");
            returnable = returnable.append(LaTeX.substring(possibleCursorLocations[selectionIndex]));
            
         }
         else {
            
            returnable = returnable.append(LaTeX.substring(0, possibleCursorLocations[cursorLocationIndex]));
            returnable = returnable.append("\\hspace{-2}\\vert\\hspace{-2}");
            returnable = returnable.append(LaTeX.substring(possibleCursorLocations[cursorLocationIndex]));
            
         }
      }
      else {
         
         returnable = returnable.append(LaTeX.substring(0, possibleCursorLocations[cursorLocationIndex]));
         returnable = returnable.append("\\hspace{-2}\\vert\\hspace{-2}");
         returnable = returnable.append(LaTeX.substring(possibleCursorLocations[cursorLocationIndex]));
         
      }
      return returnable.toString();
      
   }
   
   public void moveCursorRight(boolean shift) {

      if(shift && !activeSelection) {
         selectionIndex = cursorLocationIndex;
         activeSelection = true;
      }
      if(!shift) {
         activeSelection = false;
      }
      if(cursorLocationIndex != possibleCursorLocations.length - 1) {
         cursorLocationIndex++;
      }
      
   }
   
   public void moveCursorLeft(boolean shift) {
      
      if(shift && !activeSelection) {
         selectionIndex = cursorLocationIndex;
         activeSelection = true;
      }
      if(!shift) {
         activeSelection = false;
      }
      if(cursorLocationIndex != 0) {
         cursorLocationIndex--;
      }
      
   }
   
   public void moveHome(boolean shift) {
      
      if(shift && !activeSelection) {
         selectionIndex = cursorLocationIndex;
         activeSelection = true;
      }
      if(!shift) {
         activeSelection = false;
      }
      cursorLocationIndex = 0;
      
   }
   
   public void moveEnd(boolean shift) {
      
      if(shift && !activeSelection) {
         selectionIndex = cursorLocationIndex;
         activeSelection = true;
      }
      if(!shift) {
         activeSelection = false;
      }
      cursorLocationIndex = possibleCursorLocations.length - 1;
      
   }
}
