import java.util.Arrays;

public class LaTeXInputRepresentation {

   private String highlightColor = "\\definecolor{lblue}{rgb}{0, 0.15, 1}";
   private String setHighlightStart = "\\fgcolor{white}{\\bgcolor{lblue}{";
   private boolean activeSelection;
   private int selectionIndex;
   private int cursorLocationIndex;
   private int possibleCursorLocations[];
   private int selectionSkipForward[];
   private int selectionSkipBackward[];
   private String LaTeX;

   public LaTeXInputRepresentation() {

      activeSelection = false;
      selectionIndex = 0;
      cursorLocationIndex = 0;
      possibleCursorLocations = new int[1];
      possibleCursorLocations[0] = 0;
      selectionSkipForward = new int[1];
      selectionSkipForward[0] = 0;
      selectionSkipBackward = new int[1];
      selectionSkipBackward[0] = Integer.MAX_VALUE;
      LaTeX = "";

   }

   public LaTeXInputRepresentation(LaTeXInputRepresentation p) {

      activeSelection = false;
      selectionIndex = 0;
      cursorLocationIndex = 0;
      possibleCursorLocations = new int[1];
      possibleCursorLocations[0] = 0;
      selectionSkipForward = new int[1];
      selectionSkipForward[0] = 0;
      selectionSkipBackward = new int[1];
      selectionSkipBackward[0] = Integer.MAX_VALUE;
      LaTeX = "";

   }

   public void addFormula(String f) {

      if (activeSelection) {
         remove(true);
      }
      StringBuilder newLaTeX = new StringBuilder();
      newLaTeX.append(LaTeX.substring(0, possibleCursorLocations[cursorLocationIndex]));
      switch (f) {
      case "Lowercase Eta":
         newLaTeX.append("\\eta ");
         newLaTeX.append(LaTeX.substring(possibleCursorLocations[cursorLocationIndex]));
         addToTrackerArrays(5, 0);
         break;
      case "^{}":
         newLaTeX.append(f);
         newLaTeX.append(LaTeX.substring(possibleCursorLocations[cursorLocationIndex]));
         addToTrackerArrays(1, 1);
         break;
      case "Fraction":
         newLaTeX.append("\\frac{}{}");
         newLaTeX.append(LaTeX.substring(possibleCursorLocations[cursorLocationIndex]));
         addToTrackerArrays(5, 2);
         break;
      case "\\frac{}{}":
         newLaTeX.append(f);
         newLaTeX.append(LaTeX.substring(possibleCursorLocations[cursorLocationIndex]));
         addToTrackerArrays(5, 2);
         break;
      case "\\~{}":
         newLaTeX.append(f);
         newLaTeX.append(LaTeX.substring(possibleCursorLocations[cursorLocationIndex]));
         addToTrackerArrays(2, 1);
         break;
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
         addToTrackerArrays(3, 0);
         break;
      default:
         System.out.println(f);
         newLaTeX.append(LaTeX.substring(possibleCursorLocations[cursorLocationIndex]));
      }
      LaTeX = newLaTeX.toString();

   }

   public void addChar(char c) {

      if (activeSelection) {
         remove(true);
      }
      StringBuilder newLaTeX = new StringBuilder();
      newLaTeX.append(LaTeX.substring(0, possibleCursorLocations[cursorLocationIndex]));
      newLaTeX.append(String.valueOf(c));
      newLaTeX.append(LaTeX.substring(possibleCursorLocations[cursorLocationIndex]));
      LaTeX = newLaTeX.toString();
      addToTrackerArrays(1, 0);

   }

   public void remove(boolean forward) {

      StringBuilder deletedString = new StringBuilder();

      if (!activeSelection) {

         if (forward) {

            if (cursorLocationIndex < possibleCursorLocations.length - 1) {

               if (selectionSkipForward[cursorLocationIndex] == 0) {
                  int diffLength = possibleCursorLocations[cursorLocationIndex + 1]
                        - possibleCursorLocations[cursorLocationIndex];
                  deletedString.append(LaTeX.substring(0, possibleCursorLocations[cursorLocationIndex]));
                  deletedString.append(LaTeX.substring(possibleCursorLocations[cursorLocationIndex + 1]));
                  LaTeX = deletedString.toString();
                  for (int i = cursorLocationIndex; i < possibleCursorLocations.length - 1; i++) {
                     possibleCursorLocations[i] = possibleCursorLocations[i + 1] - diffLength;
                     selectionSkipForward[i] = selectionSkipForward[i + 1];
                     selectionSkipBackward[i] = selectionSkipBackward[i + 1];
                  }
                  for (int i = 0; i < possibleCursorLocations.length; i++) {
                     if (selectionSkipForward[i] > cursorLocationIndex) {
                        selectionSkipForward[i]--;
                     }
                     if (selectionSkipBackward[i] > cursorLocationIndex
                           && selectionSkipBackward[i] != Integer.MAX_VALUE) {
                        selectionSkipBackward[i]--;
                     }
                  }
                  possibleCursorLocations = Arrays.copyOf(possibleCursorLocations, possibleCursorLocations.length - 1);
                  selectionSkipForward = Arrays.copyOf(selectionSkipForward, selectionSkipForward.length - 1);
                  selectionSkipBackward = Arrays.copyOf(selectionSkipBackward, selectionSkipBackward.length - 1);
               } else {
                  activeSelection = true;
                  selectionIndex = selectionSkipForward[cursorLocationIndex];
               }

            }

         } else {

            if (cursorLocationIndex > 0) {

               if (selectionSkipBackward[cursorLocationIndex] == Integer.MAX_VALUE) {
                  int diffLength = possibleCursorLocations[cursorLocationIndex]
                        - possibleCursorLocations[cursorLocationIndex - 1];
                  deletedString.append(LaTeX.substring(0, possibleCursorLocations[cursorLocationIndex - 1]));
                  deletedString.append(LaTeX.substring(possibleCursorLocations[cursorLocationIndex]));
                  LaTeX = deletedString.toString();
                  for (int i = cursorLocationIndex - 1; i < possibleCursorLocations.length - 1; i++) {
                     possibleCursorLocations[i] = possibleCursorLocations[i + 1] - diffLength;
                     selectionSkipForward[i] = selectionSkipForward[i + 1];
                     selectionSkipBackward[i] = selectionSkipBackward[i + 1];
                  }
                  for (int i = 0; i < possibleCursorLocations.length; i++) {
                     if (selectionSkipForward[i] > cursorLocationIndex - 1) {
                        selectionSkipForward[i]--;
                     }
                     if (selectionSkipBackward[i] > cursorLocationIndex - 1
                           && selectionSkipBackward[i] != Integer.MAX_VALUE) {
                        selectionSkipBackward[i]--;
                     }
                  }
                  cursorLocationIndex--;
                  possibleCursorLocations = Arrays.copyOf(possibleCursorLocations, possibleCursorLocations.length - 1);
                  selectionSkipForward = Arrays.copyOf(selectionSkipForward, selectionSkipForward.length - 1);
                  selectionSkipBackward = Arrays.copyOf(selectionSkipBackward, selectionSkipBackward.length - 1);
               } else {
                  activeSelection = true;
                  selectionIndex = selectionSkipBackward[cursorLocationIndex];
               }

            }

         }

      } else {

         if (cursorLocationIndex > selectionIndex) {

            int indexDiff = cursorLocationIndex - selectionIndex;
            int diffLength = possibleCursorLocations[cursorLocationIndex] - possibleCursorLocations[selectionIndex + 1];
            deletedString.append(LaTeX.substring(0, possibleCursorLocations[selectionIndex]));
            deletedString.append(LaTeX.substring(possibleCursorLocations[cursorLocationIndex]));
            LaTeX = deletedString.toString();
            for (int i = selectionIndex; i < possibleCursorLocations.length - indexDiff; i++) {
               possibleCursorLocations[i] = possibleCursorLocations[i + indexDiff] - diffLength - 1;
               selectionSkipForward[i] = selectionSkipForward[i + indexDiff];
               selectionSkipBackward[i] = selectionSkipBackward[i + indexDiff];
            }
            for (int i = 0; i < possibleCursorLocations.length; i++) {
               if (selectionSkipForward[i] > cursorLocationIndex) {
                  selectionSkipForward[i] -= indexDiff;
               }
               if (selectionSkipBackward[i] > cursorLocationIndex && selectionSkipBackward[i] != Integer.MAX_VALUE) {
                  selectionSkipBackward[i] -= indexDiff;
               }
            }
            possibleCursorLocations = Arrays.copyOf(possibleCursorLocations,
                  possibleCursorLocations.length - indexDiff);
            selectionSkipForward = Arrays.copyOf(selectionSkipForward, selectionSkipForward.length - indexDiff);
            selectionSkipBackward = Arrays.copyOf(selectionSkipBackward, selectionSkipBackward.length - indexDiff);
            cursorLocationIndex = selectionIndex;
            activeSelection = false;

         } else {

            int indexDiff = selectionIndex - cursorLocationIndex;
            int diffLength = possibleCursorLocations[selectionIndex] - possibleCursorLocations[cursorLocationIndex + 1];
            deletedString.append(LaTeX.substring(0, possibleCursorLocations[cursorLocationIndex]));
            deletedString.append(LaTeX.substring(possibleCursorLocations[selectionIndex]));
            LaTeX = deletedString.toString();
            for (int i = cursorLocationIndex; i < possibleCursorLocations.length - indexDiff; i++) {
               possibleCursorLocations[i] = possibleCursorLocations[i + indexDiff] - diffLength - 1;
               selectionSkipForward[i] = selectionSkipForward[i + indexDiff];
               selectionSkipBackward[i] = selectionSkipBackward[i + indexDiff];
            }
            for (int i = 0; i < possibleCursorLocations.length; i++) {
               if (selectionSkipForward[i] > cursorLocationIndex) {
                  selectionSkipForward[i] -= indexDiff;
               }
               if (selectionSkipBackward[i] > cursorLocationIndex && selectionSkipBackward[i] != Integer.MAX_VALUE) {
                  selectionSkipBackward[i] -= indexDiff;
               }
            }
            possibleCursorLocations = Arrays.copyOf(possibleCursorLocations,
                  possibleCursorLocations.length - indexDiff);
            selectionSkipForward = Arrays.copyOf(selectionSkipForward, selectionSkipForward.length - indexDiff);
            selectionSkipBackward = Arrays.copyOf(selectionSkipBackward, selectionSkipBackward.length - indexDiff);
            activeSelection = false;

         }

      }

   }

   public void addToTrackerArrays(int strLen, int args) {

      int oldPossibleCursorLocations[] = Arrays.copyOf(possibleCursorLocations, possibleCursorLocations.length);
      int oldSelectionSkipForward[] = Arrays.copyOf(selectionSkipForward, selectionSkipForward.length);
      int oldSelectionSkipBackward[] = Arrays.copyOf(selectionSkipBackward, selectionSkipBackward.length);
      possibleCursorLocations = Arrays.copyOf(possibleCursorLocations, possibleCursorLocations.length + 1 + args);
      selectionSkipForward = Arrays.copyOf(selectionSkipForward, selectionSkipForward.length + 1 + args);
      selectionSkipBackward = Arrays.copyOf(selectionSkipBackward, selectionSkipBackward.length + 1 + args);
      for (int i = cursorLocationIndex; i < oldPossibleCursorLocations.length; i++) {
         possibleCursorLocations[i + 1 + args] = oldPossibleCursorLocations[i];
         possibleCursorLocations[i + 1 + args] += strLen + args * 2;
         selectionSkipForward[i + 1 + args] = oldSelectionSkipForward[i];
         selectionSkipBackward[i + 1 + args] = oldSelectionSkipBackward[i];
      }
      for (int i = 0; i < possibleCursorLocations.length; i++) {
         if (selectionSkipForward[i] > cursorLocationIndex) {
            selectionSkipForward[i] += args + 1;
         }
         if (selectionSkipBackward[i] >= cursorLocationIndex && selectionSkipBackward[i] != Integer.MAX_VALUE) {
            selectionSkipBackward[i] += args + 1;
         }
      }
      if (args != 0) {
         selectionSkipForward[cursorLocationIndex] = cursorLocationIndex + args + 1;
         selectionSkipBackward[cursorLocationIndex + args + 1] = cursorLocationIndex;
      } else {
         selectionSkipForward[cursorLocationIndex] = 0;
         selectionSkipBackward[cursorLocationIndex + args + 1] = Integer.MAX_VALUE;
      }
      for (int i = 0; i < args; i++) {
         selectionSkipForward[cursorLocationIndex + i + 1] = selectionSkipForward[cursorLocationIndex];
         selectionSkipBackward[cursorLocationIndex + i + 1] = selectionSkipBackward[cursorLocationIndex + args + 1];
         possibleCursorLocations[cursorLocationIndex + i + 1] = oldPossibleCursorLocations[cursorLocationIndex] + strLen
               + i * 2 + 1;
      }
      cursorLocationIndex++;

   }

   public String buildLaTeX(boolean forAnswer) {
      
      if(forAnswer) {
         return LaTeX;
      }

      StringBuilder returnable = new StringBuilder();
      if (activeSelection) {
         if (cursorLocationIndex > selectionIndex) {

            returnable.append(highlightColor);
            returnable.append(LaTeX.substring(0, possibleCursorLocations[selectionIndex]));
            returnable.append(setHighlightStart);
            returnable.append(LaTeX.substring(possibleCursorLocations[selectionIndex],
                  possibleCursorLocations[cursorLocationIndex]));
            returnable.append("\\hspace{-2}\\vert\\hspace{-2}}}");
            returnable = returnable.append(LaTeX.substring(possibleCursorLocations[cursorLocationIndex]));

         } else if (cursorLocationIndex < selectionIndex) {

            returnable.append(highlightColor);
            returnable.append(LaTeX.substring(0, possibleCursorLocations[cursorLocationIndex]));
            returnable.append(setHighlightStart);
            returnable.append("\\hspace{-2}\\vert\\hspace{-2}");
            returnable.append(LaTeX.substring(possibleCursorLocations[cursorLocationIndex],
                  possibleCursorLocations[selectionIndex]));
            returnable.append("}}");
            returnable = returnable.append(LaTeX.substring(possibleCursorLocations[selectionIndex]));

         } else {

            returnable = returnable.append(LaTeX.substring(0, possibleCursorLocations[cursorLocationIndex]));
            returnable = returnable.append("\\hspace{-2}\\vert\\hspace{-2}");
            returnable = returnable.append(LaTeX.substring(possibleCursorLocations[cursorLocationIndex]));

         }
      } else {

         returnable = returnable.append(LaTeX.substring(0, possibleCursorLocations[cursorLocationIndex]));
         returnable = returnable.append("\\hspace{-2}\\vert\\hspace{-2}");
         returnable = returnable.append(LaTeX.substring(possibleCursorLocations[cursorLocationIndex]));

      }

      return returnable.toString();

   }

   public void moveCursorRight(boolean shift) {

      if (shift && !activeSelection) {
         selectionIndex = cursorLocationIndex;
         activeSelection = true;
      }
      if (!shift) {
         activeSelection = false;
      }
      if (cursorLocationIndex != possibleCursorLocations.length - 1 && !shift) {
         cursorLocationIndex++;
      }
      if (shift) {
         if (cursorLocationIndex >= selectionIndex) {
            if (selectionSkipForward[cursorLocationIndex] != 0) {
               cursorLocationIndex = selectionSkipForward[cursorLocationIndex];
               if (selectionSkipBackward[cursorLocationIndex] < selectionIndex) {
                  selectionIndex = selectionSkipBackward[cursorLocationIndex];
               }
            } else if (cursorLocationIndex < possibleCursorLocations.length - 1) {
               cursorLocationIndex++;
            }
         } else {
            if (selectionSkipForward[cursorLocationIndex] != 0) {
               cursorLocationIndex = selectionSkipForward[cursorLocationIndex];
            } else if (cursorLocationIndex < possibleCursorLocations.length - 1) {
               cursorLocationIndex++;
            }
         }
      }

   }

   public void moveCursorLeft(boolean shift) {

      if (shift && !activeSelection) {
         selectionIndex = cursorLocationIndex;
         activeSelection = true;
      }
      if (!shift) {
         activeSelection = false;
      }
      if (cursorLocationIndex != 0 && !shift) {
         cursorLocationIndex--;
      }
      if (shift) {
         if (cursorLocationIndex <= selectionIndex) {
            if (selectionSkipBackward[cursorLocationIndex] != Integer.MAX_VALUE) {
               cursorLocationIndex = selectionSkipBackward[cursorLocationIndex];
               if (selectionSkipForward[cursorLocationIndex] > selectionIndex) {
                  selectionIndex = selectionSkipForward[cursorLocationIndex];
               }
            } else if (cursorLocationIndex > 0) {
               cursorLocationIndex--;
            }
         } else {
            if (selectionSkipBackward[cursorLocationIndex] != Integer.MAX_VALUE) {
               cursorLocationIndex = selectionSkipBackward[cursorLocationIndex];
            } else if (cursorLocationIndex > 0) {
               cursorLocationIndex--;
            }
         }
      }

   }

   public void moveHome(boolean shift) {

      if (shift && !activeSelection) {
         selectionIndex = cursorLocationIndex;
         activeSelection = true;
      }
      if (!shift) {
         activeSelection = false;
      }
      cursorLocationIndex = 0;
      if (shift) {
         for (int i = 0; i < selectionIndex; i++) {
            if (selectionSkipForward[i] > selectionIndex) {
               selectionIndex = selectionSkipForward[i];
            }
         }
      }

   }

   public void moveEnd(boolean shift) {

      if (shift && !activeSelection) {
         selectionIndex = cursorLocationIndex;
         activeSelection = true;
      }
      if (!shift) {
         activeSelection = false;
      }
      cursorLocationIndex = possibleCursorLocations.length - 1;
      if (shift) {
         for (int i = possibleCursorLocations.length - 1; i > selectionIndex; i--) {
            if (selectionSkipBackward[i] < selectionIndex) {
               selectionIndex = selectionSkipBackward[i];
            }
         }
      }
   }
}
