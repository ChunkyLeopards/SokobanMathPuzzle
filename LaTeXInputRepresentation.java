import java.util.Arrays;

public class LaTeXInputRepresentation {
   
   private String highlightColor = "\\definecolor{lblue}{rgb}{0.55, 0.78, 1}";
   private String setHighlightStart = "\\bgcolor{lblue}{";
   private boolean active;
   private LaTeXInputRepresentation parent;
   private LaTeXInputRepresentation children[];
   private int childLocations[];
   private boolean activeSelection;
   private int selectionStartIndex;
   private int selectionEndIndex;
   private int cursorLocationIndex;
   private int possibleCursorLocations[];
   private String LaTeX;
   
   public LaTeXInputRepresentation() {
      
      parent = null;
      children = new LaTeXInputRepresentation[0];
      childLocations = new int[0];
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
      
      parent = p;
      children = new LaTeXInputRepresentation[0];
      childLocations = new int[0];
      active = false;
      activeSelection = false;
      selectionStartIndex = 0;
      selectionEndIndex = 0;
      cursorLocationIndex = 0;
      possibleCursorLocations = new int[1];
      possibleCursorLocations[0] = 0;
      LaTeX = "";
      
   }
   
   public void addChar(char c) {
      
      String tempLaTeX = LaTeX;
      LaTeX = tempLaTeX.substring(0, possibleCursorLocations[cursorLocationIndex]);
      LaTeX = LaTeX.concat(String.valueOf(c));
      LaTeX = LaTeX.concat(tempLaTeX.substring(possibleCursorLocations[cursorLocationIndex]));
      possibleCursorLocations = Arrays.copyOf(possibleCursorLocations, possibleCursorLocations.length + 1);
      cursorLocationIndex++;
      possibleCursorLocations[cursorLocationIndex] = possibleCursorLocations[cursorLocationIndex - 1] + 1;
      
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
            if(children.length == 0) {
               
            }
            else {
               
            }
         }
         else {
            if(children.length == 0) {
               returnable = LaTeX.substring(0, possibleCursorLocations[cursorLocationIndex]);
               returnable = returnable.concat("\\vert ");
               returnable = returnable.concat(LaTeX.substring(possibleCursorLocations[cursorLocationIndex]));
               System.out.println(returnable);
            }
            else {
               
            }
         }
      }
      else {
         if(children.length == 0) {
            returnable = LaTeX;
         }
         else {
            
         }
      }
      return returnable;
      
   }
   
   public boolean getActive() {
      
      return active;
      
   }
   
   public LaTeXInputRepresentation[] getChildren() {
      
      return children;
      
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
