import java.util.Arrays;

public class LaTeXInputRepresentation {
   
   private String highlightColor = "\\definecolor{lblue}{rgb}{0.55, 0.78, 1}";
   private String setHighlightStart = "\\bgcolor{lblue}{";
   private boolean active;
   private boolean activeSelection;
   private int selectionStartIndex;
   private int selectionEndIndex;
   private int cursorLocationIndex;
   private int possibleCursorLocations[];
   private String LaTeX;
   
   public LaTeXInputRepresentation() {
      
      active = false;
      activeSelection = false;
      selectionStartIndex = 0;
      selectionEndIndex = 0;
      cursorLocationIndex = 0;
      possibleCursorLocations = new int[1];
      possibleCursorLocations[0] = 0;
      LaTeX = "";
      
   }

   public LaTeXInputRepresentation(LaTeXInputRepresentation p) {
      
      active = false;
      activeSelection = false;
      selectionStartIndex = 0;
      selectionEndIndex = 0;
      cursorLocationIndex = 0;
      possibleCursorLocations = new int[1];
      possibleCursorLocations[0] = 0;
      LaTeX = "";
      
   }
   
   public void addFormula(String f, int argumentCount) {
      
      System.out.println(f);
      
   }
   
   public void addChar(char c) {
      
      String tempLaTeX = LaTeX;
      LaTeX = tempLaTeX.substring(0, possibleCursorLocations[cursorLocationIndex]);
      LaTeX = LaTeX.concat(String.valueOf(c));
      LaTeX = LaTeX.concat(tempLaTeX.substring(possibleCursorLocations[cursorLocationIndex]));
      possibleCursorLocations = Arrays.copyOf(possibleCursorLocations, possibleCursorLocations.length + 1);
      for(int i = possibleCursorLocations.length - 1; i > cursorLocationIndex; i--) {
         possibleCursorLocations[i] = possibleCursorLocations[i - 1];
         possibleCursorLocations[i]++;
      }
      cursorLocationIndex++;
      possibleCursorLocations[cursorLocationIndex] = possibleCursorLocations[cursorLocationIndex - 1] + 1;
      
   }
   
   public void backspace() {
      
      if(!activeSelection) {
         
         LaTeX = LaTeX.substring(0, possibleCursorLocations[cursorLocationIndex - 1]).concat(LaTeX.substring(possibleCursorLocations[cursorLocationIndex]));
         for(int i = cursorLocationIndex - 1; i < possibleCursorLocations.length - 1; i++) {
            possibleCursorLocations[i] = possibleCursorLocations[i + 1];
         }
         cursorLocationIndex--;
         
      }
      else {
      }
      
   }
   
   public void removeChar() {
      
      
      
   }
   
   public void setActive() {
      
      active = true;
      
   }
   
   public void setInactive() {
      
      active = false;
      
   }
   
   public void setSelection() {
      
      selectionStartIndex = cursorLocationIndex;
      selectionEndIndex = cursorLocationIndex;
      
   }
   
   public void addToSelection() {
      
      
      
   }
   
   public void clearSelection() {
      
      
      
   }
   
   public String buildLaTeX() {
      String returnable = "";
      if(active) {
         if(activeSelection) {
         }
         else {
            returnable = LaTeX.substring(0, possibleCursorLocations[cursorLocationIndex]);
            returnable = returnable.concat("\\vert ");
            returnable = returnable.concat(LaTeX.substring(possibleCursorLocations[cursorLocationIndex]));
         }
      }
      else {
         returnable = LaTeX;
      }
      return returnable;
      
   }
   
   public boolean getActive() {
      
      return active;
      
   }
   
   public void moveCursorRight() {
      
      if(cursorLocationIndex == possibleCursorLocations.length - 1) {
         //do stuff
      }
      else {
         cursorLocationIndex++;
      }
      
   }
   
   public void moveCursorLeft() {
      
      if(cursorLocationIndex == 0) {
         //do stuff
      }
      else {
         cursorLocationIndex--;
      }
      
   }
   
   public void moveCursorDown() {
      
      
      
   }
   
   public void moveCursorUp() {
      
      
      
   }
}
