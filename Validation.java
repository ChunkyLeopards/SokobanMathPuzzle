
public class Validation {

   public static boolean validate(SokobanRuntimeStorage p) {
      // run all tests for validity

      // check boxes to targets 1 to 1, nest for
      int count = 0; // increment when box found, decrement when target found, box on target do
      // nothing
      for (int i = 0; i < p.getWidth(); i++) {
         for (int j = 0; j < p.getHeight(); j++) {
            if (p.getValue(i, j) % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_BOX)
               count++;
            else if (p.getValue(i, j) % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_TARGET)
               count--;
         }
      }

      if (count != 0) {
         System.err.println("Failed case 1 - box and target count do not match");
         return false; // # of boxes and targets do not match
      }

      if (checkSolved(p)) {
         System.err.println("Failed case 2 - puzzle already solved");
         return false;
      }

      // check for invalid structure in 2x2, nested for
      // ex) square of boxes, all walls and boxes
      if (!checkStructure(p)) {

         System.err.println("Failed case 3 - invalid element structure.");
         return false;

      }

      // Case 4 External wall closes, includes corners for aesthetics
      for (int i = 0; i < p.getWidth(); i++) {
         for (int j = 0; j < p.getHeight(); j++) {

         }
      }
      // System.out.println("passed test 4");

      // Check all internal spaces connected, create list of internal spaces (x,y) and
      // a tree
      // System.out.println("passed test 5");

      // Boxes on wall with no target along wall requires indentation
      if (!checkIndentVert(p)) {
         System.err.println("Failed case 6a, improper indentation vertical");
         return false;
      }
      if (!checkIndentHor(p)) {
         System.err.println("Failed case 6b, improper indentation horizontal");
         return false;
      }

      return true;

   }

   public static boolean checkSolved(SokobanRuntimeStorage p) {
      // check not already solved, at least one box not on target
      int count = 0;
      for (int i = 0; i < p.getWidth(); i++) {
         for (int j = 0; j < p.getHeight(); j++) {
            if (p.getValue(i, j) % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_BOX)
               count++;
         }
      }
      if (count == 0) {
         return true; // all boxes already on targets
      }
      return false;
   }

   public static boolean checkBorder(SokobanRuntimeStorage p) {
      // make sure puzzle closes border and corners filled
      return true;

   }

   public static boolean checkIndentVert(SokobanRuntimeStorage p) {

      for (int i = 0; i < p.getWidth(); i++) {
         for (int j = 0; j < p.getHeight() - 1; j++) {
            int L = p.getValue(i, j);
            int U = p.getValue(i, j + 1);
            int boxCount = 0;
            int tarCount = 0;
            boolean isFine = false;
            // B
            // W
            if (U == SokobanInterpreter.WALL && L == SokobanInterpreter.INTERNAL_BOX) {
               int storeI = i;
               while (p.getValue(storeI, j) != SokobanInterpreter.WALL) {
                  storeI--;
               }
               int beginI, endI;
               beginI = storeI;
               storeI++;

               while (p.getValue(storeI, j) != SokobanInterpreter.WALL) {
                  if (p.getValue(storeI, j) % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_TARGET)
                     tarCount++;
                  else if (p.getValue(storeI, j)
                        % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_BOX)
                     boxCount++;
                  storeI++;
               }
               endI = storeI;
               boolean indent = false;
               if (boxCount > tarCount) {
                  for (int k = beginI; k <= endI; k++) {
                     if (indent && (p.getValue(k, j + 1)
                           % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL
                           || p.getValue(k, j + 1)
                                 % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_TARGET
                           || p.getValue(k, j + 1)
                                 % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_PLAYER
                           || p.getValue(k, j + 1)
                                 % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_PLAYER_TARGET
                           || p.getValue(k, j + 1)
                                 % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_BOX
                           || p.getValue(k, j + 1)
                                 % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_BOX_TARGET)) {
                        isFine = true;
                     } else if (indent) {
                        indent = false;
                     } else if (!indent && (p.getValue(k, j + 1)
                           % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL
                           || p.getValue(k, j + 1)
                                 % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_TARGET
                           || p.getValue(k, j + 1)
                                 % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_PLAYER
                           || p.getValue(k, j + 1)
                                 % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_PLAYER_TARGET
                           || p.getValue(k, j + 1)
                                 % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_BOX
                           || p.getValue(k, j + 1)
                                 % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_BOX_TARGET)) {
                        indent = true;
                     }
                  }
               } else {
                  isFine = true;
               }
               // W
               // B
            } else if (U == SokobanInterpreter.INTERNAL_BOX && L == SokobanInterpreter.WALL) {
               int storeI = i;
               while (p.getValue(storeI, j + 1) != SokobanInterpreter.WALL) {
                  storeI--;
               }
               int beginI, endI;
               beginI = storeI;
               storeI++;

               while (p.getValue(storeI, j + 1) != SokobanInterpreter.WALL) {
                  if (p.getValue(storeI, j + 1)
                        % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_TARGET)
                     tarCount++;
                  else if (p.getValue(storeI, j + 1)
                        % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_BOX)
                     boxCount++;
                  storeI++;
               }
               endI = storeI;
               boolean indent = false;
               if (boxCount > tarCount) {
                  for (int k = beginI; k <= endI; k++) {
                     if (indent && (p.getValue(k, j)
                           % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL
                           || p.getValue(k, j)
                                 % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_TARGET
                           || p.getValue(k, j)
                                 % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_PLAYER
                           || p.getValue(k, j)
                                 % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_PLAYER_TARGET
                           || p.getValue(k, j) % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_BOX
                           || p.getValue(k, j)
                                 % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_BOX_TARGET)) {
                        isFine = true;
                     } else if (indent) {
                        indent = false;
                     } else if (!indent && (p.getValue(k, j)
                           % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL
                           || p.getValue(k, j)
                                 % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_TARGET
                           || p.getValue(k, j)
                                 % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_PLAYER
                           || p.getValue(k, j)
                                 % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_PLAYER_TARGET)
                           || p.getValue(k, j) % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_BOX
                           || p.getValue(k, j)
                                 % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_BOX_TARGET) {
                        indent = true;
                     }
                  }
               } else {
                  isFine = true;
               }
            } else {
               isFine = true;
            }
            if (!isFine) {
               return false;
            }

         }
      }
      return true;

   }

   public static boolean checkIndentHor(SokobanRuntimeStorage p) {

      for (int i = 0; i < p.getWidth() - 1; i++) {
         for (int j = 0; j < p.getHeight(); j++) {
            int L = p.getValue(i, j);
            int R = p.getValue(i + 1, j);
            int boxCount = 0;
            int tarCount = 0;
            boolean isFine = false;
            // BW
            if (L == SokobanInterpreter.INTERNAL_BOX && R == SokobanInterpreter.WALL) {
               int storeJ = j;
               while (p.getValue(i, storeJ) != SokobanInterpreter.WALL) {
                  storeJ--;
               }
               int beginJ, endJ;
               beginJ = storeJ;
               storeJ++;

               while (p.getValue(i, storeJ) != SokobanInterpreter.WALL) {
                  if (p.getValue(i, storeJ) % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_TARGET)
                     tarCount++;
                  else if (p.getValue(i, storeJ)
                        % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_BOX)
                     boxCount++;
                  storeJ++;
               }
               endJ = storeJ;
               boolean indent = false;
               if (boxCount > tarCount) {
                  for (int k = beginJ; k <= endJ; k++) {
                     if (indent && (p.getValue(i + 1, k)
                           % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL
                           || p.getValue(i + 1, k)
                                 % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_TARGET
                           || p.getValue(i + 1, k)
                                 % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_PLAYER
                           || p.getValue(i + 1, k)
                                 % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_PLAYER_TARGET
                           || p.getValue(i + 1, k)
                                 % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_BOX
                           || p.getValue(i + 1, k)
                                 % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_BOX_TARGET)) {
                        isFine = true;
                     } else if (indent) {
                        indent = false;
                     } else if (!indent && (p.getValue(i + 1, k)
                           % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL
                           || p.getValue(i + 1, k)
                                 % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_TARGET
                           || p.getValue(i + 1, k)
                                 % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_PLAYER
                           || p.getValue(i + 1, k)
                                 % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_PLAYER_TARGET
                           || p.getValue(i + 1, k)
                                 % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_BOX
                           || p.getValue(i + 1, k)
                                 % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_BOX_TARGET)) {
                        indent = true;
                     }
                  }
               } else {
                  isFine = true;
               }
            }
            // WB
            else if (L == 1 && R == 10) {
               int storeJ = j;
               while (p.getValue(i + 1, storeJ) != SokobanInterpreter.WALL) {
                  storeJ--;
               }
               int beginJ, endJ;
               beginJ = storeJ;
               storeJ++;

               while (p.getValue(i + 1, storeJ) != SokobanInterpreter.WALL) {
                  if (p.getValue(i + 1, storeJ)
                        % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_TARGET
                        || p.getValue(i + 1, storeJ)
                              % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_PLAYER_TARGET)
                     tarCount++;
                  else if (p.getValue(i + 1, storeJ)
                        % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_BOX)
                     boxCount++;
                  storeJ++;
               }
               endJ = storeJ;
               boolean indent = false;
               if (boxCount > tarCount) {
                  for (int k = beginJ; k <= endJ; k++) {
                     if (indent && (p.getValue(i, k)
                           % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL
                           || p.getValue(i, k)
                                 % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_TARGET
                           || p.getValue(i, k) % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.PLAYER
                           || p.getValue(i, k)
                                 % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_PLAYER_TARGET
                           || p.getValue(i, k) % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_BOX
                           || p.getValue(i, k)
                                 % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_BOX_TARGET)) {
                        isFine = true;
                     } else if (indent) {
                        indent = false;
                     } else if (!indent && (p.getValue(i, k)
                           % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL
                           || p.getValue(i, k)
                                 % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_TARGET
                           || p.getValue(i, k) % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.PLAYER
                           || p.getValue(i, k)
                                 % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_PLAYER_TARGET
                           || p.getValue(i, k) % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_BOX
                           || p.getValue(i, k)
                                 % SokobanInterpreter.BOX_TRACK_OFFSET == SokobanInterpreter.INTERNAL_BOX_TARGET)) {
                        indent = true;
                     }
                  }
               } else {
                  isFine = true;
               }
            } else {
               isFine = true;
            }
            if (!isFine) {
               return false;
            }

         }
      }
      return true;

   }

   public static boolean checkAccessibility(SokobanRuntimeStorage p) {

      return true;

   }

   public static boolean checkBoxTarget(SokobanRuntimeStorage p) {
      // boxes and targets 1 to 1
      return true;

   }

   public static boolean checkStructure(SokobanRuntimeStorage p) {

      for (int i = 0; i < p.getWidth() - 1; i++) {
         for (int j = 0; j < p.getHeight() - 1; j++) {
            int UL = p.getValue(i, j) % SokobanInterpreter.BOX_TRACK_OFFSET;
            int UR = p.getValue(i + 1, j) % SokobanInterpreter.BOX_TRACK_OFFSET;
            int LL = p.getValue(i, j + 1) % SokobanInterpreter.BOX_TRACK_OFFSET;
            int LR = p.getValue(i + 1, j + 1) % SokobanInterpreter.BOX_TRACK_OFFSET;
            // 2 boxes against a wall
            // 2 boxes against wall one on target
            // WW
            // BB
            if ((UL == SokobanInterpreter.WALL && UR == SokobanInterpreter.WALL && LL == SokobanInterpreter.INTERNAL_BOX
                  && LR == SokobanInterpreter.INTERNAL_BOX)
                  // WW
                  // BS
                  || (UL == SokobanInterpreter.WALL && UR == SokobanInterpreter.WALL
                        && LL == SokobanInterpreter.INTERNAL_BOX && LR == SokobanInterpreter.INTERNAL_BOX_TARGET)
                  // WW
                  // SB
                  || (UL == SokobanInterpreter.WALL && UR == SokobanInterpreter.WALL
                        && LL == SokobanInterpreter.INTERNAL_BOX_TARGET && LR == SokobanInterpreter.INTERNAL_BOX)
                  // WB
                  // WB
                  || (UL == SokobanInterpreter.WALL && UR == SokobanInterpreter.INTERNAL_BOX
                        && LL == SokobanInterpreter.WALL && LR == SokobanInterpreter.INTERNAL_BOX)
                  // WS
                  // WB
                  || (UL == SokobanInterpreter.WALL && UR == SokobanInterpreter.INTERNAL_BOX_TARGET
                        && LL == SokobanInterpreter.WALL && LR == SokobanInterpreter.INTERNAL_BOX)
                  // WB
                  // WS
                  || (UL == SokobanInterpreter.WALL && UR == SokobanInterpreter.INTERNAL_BOX
                        && LL == SokobanInterpreter.WALL && LR == SokobanInterpreter.INTERNAL_BOX_TARGET)
                  // BW
                  // BW
                  || (UL == SokobanInterpreter.INTERNAL_BOX && UR == SokobanInterpreter.WALL
                        && LL == SokobanInterpreter.INTERNAL_BOX && LR == SokobanInterpreter.WALL)
                  // SW
                  // BW
                  || (UL == SokobanInterpreter.INTERNAL_BOX_TARGET && UR == SokobanInterpreter.WALL
                        && LL == SokobanInterpreter.INTERNAL_BOX && LR == SokobanInterpreter.WALL)
                  // BW
                  // SW
                  || (UL == SokobanInterpreter.INTERNAL_BOX && UR == SokobanInterpreter.WALL
                        && LL == SokobanInterpreter.INTERNAL_BOX_TARGET && LR == SokobanInterpreter.WALL)
                  // BB
                  // WW
                  || (UL == SokobanInterpreter.INTERNAL_BOX && UR == SokobanInterpreter.INTERNAL_BOX
                        && LL == SokobanInterpreter.WALL && LR == SokobanInterpreter.WALL)
                  // SB
                  // WW
                  || (UL == SokobanInterpreter.INTERNAL_BOX_TARGET && UR == SokobanInterpreter.INTERNAL_BOX
                        && LL == SokobanInterpreter.WALL && LR == SokobanInterpreter.WALL)
                  // BS
                  // WW
                  || (UL == SokobanInterpreter.INTERNAL_BOX && UR == SokobanInterpreter.INTERNAL_BOX_TARGET
                        && LL == SokobanInterpreter.WALL && LR == SokobanInterpreter.WALL)) {
               System.err.println("Failed Case 3a, invalid box structure, two connected boxes along a wall");
               return false;
            }

            // 1 box in a corner
            // BW
            // W
            if ((UL == SokobanInterpreter.INTERNAL_BOX && UR == SokobanInterpreter.WALL
                  && LL == SokobanInterpreter.WALL)
                  // WB
                  // W
                  || (UL == SokobanInterpreter.WALL && UR == SokobanInterpreter.INTERNAL_BOX
                        && LR == SokobanInterpreter.WALL)
                  // W
                  // WB
                  || (UR == SokobanInterpreter.WALL && LL == SokobanInterpreter.WALL
                        && LR == SokobanInterpreter.INTERNAL_BOX)
                  // W
                  // BW
                  || (UL == SokobanInterpreter.WALL && LL == SokobanInterpreter.INTERNAL_BOX
                        && LR == SokobanInterpreter.WALL)) {
               System.err.println("Failed Case 3b, invalid box structure, box in a corner");
               return false;
            }

            // 3 boxes around a wall
            // WB
            // __
            if ((UL == SokobanInterpreter.WALL && UR == SokobanInterpreter.INTERNAL_BOX
                  && (LL == SokobanInterpreter.INTERNAL_BOX || LL == SokobanInterpreter.INTERNAL_BOX_TARGET)
                  && (LR == SokobanInterpreter.INTERNAL_BOX || LR == SokobanInterpreter.INTERNAL_BOX_TARGET))
                  // W_
                  // _B
                  || (UL == SokobanInterpreter.WALL && LR == SokobanInterpreter.INTERNAL_BOX
                        && (LL == SokobanInterpreter.INTERNAL_BOX || LL == SokobanInterpreter.INTERNAL_BOX_TARGET)
                        && (UR == SokobanInterpreter.INTERNAL_BOX || UR == SokobanInterpreter.INTERNAL_BOX_TARGET))
                  // W_
                  // B_
                  || (UL == SokobanInterpreter.WALL && LL == SokobanInterpreter.INTERNAL_BOX
                        && (UR == SokobanInterpreter.INTERNAL_BOX || UR == SokobanInterpreter.INTERNAL_BOX_TARGET)
                        && (LR == SokobanInterpreter.INTERNAL_BOX || LR == SokobanInterpreter.INTERNAL_BOX_TARGET))
                  // BW
                  // __
                  || (UR == SokobanInterpreter.WALL && UL == SokobanInterpreter.INTERNAL_BOX
                        && (LL == SokobanInterpreter.INTERNAL_BOX || LL == SokobanInterpreter.INTERNAL_BOX_TARGET)
                        && (LR == SokobanInterpreter.INTERNAL_BOX || LR == SokobanInterpreter.INTERNAL_BOX_TARGET))
                  // _W
                  // B_
                  || (UR == SokobanInterpreter.WALL && LL == SokobanInterpreter.INTERNAL_BOX
                        && (UL == SokobanInterpreter.INTERNAL_BOX || UL == SokobanInterpreter.INTERNAL_BOX_TARGET)
                        && (LR == SokobanInterpreter.INTERNAL_BOX || LR == SokobanInterpreter.INTERNAL_BOX_TARGET))
                  // _W
                  // _B
                  || (UR == SokobanInterpreter.WALL && LR == SokobanInterpreter.INTERNAL_BOX
                        && (UL == SokobanInterpreter.INTERNAL_BOX || UL == SokobanInterpreter.INTERNAL_BOX_TARGET)
                        && (LL == SokobanInterpreter.INTERNAL_BOX || LL == SokobanInterpreter.INTERNAL_BOX_TARGET))
                  // __
                  // WB
                  || (LL == SokobanInterpreter.WALL && LR == SokobanInterpreter.INTERNAL_BOX
                        && (UL == SokobanInterpreter.INTERNAL_BOX || UL == SokobanInterpreter.INTERNAL_BOX_TARGET)
                        && (UR == SokobanInterpreter.INTERNAL_BOX || UR == SokobanInterpreter.INTERNAL_BOX_TARGET))
                  // B_
                  // W_
                  || (LL == SokobanInterpreter.WALL && UL == SokobanInterpreter.INTERNAL_BOX
                        && (LR == SokobanInterpreter.INTERNAL_BOX || LR == SokobanInterpreter.INTERNAL_BOX_TARGET)
                        && (UR == SokobanInterpreter.INTERNAL_BOX || UR == SokobanInterpreter.INTERNAL_BOX_TARGET))
                  // _B
                  // W_
                  || (LL == SokobanInterpreter.WALL && UR == SokobanInterpreter.INTERNAL_BOX
                        && (UL == SokobanInterpreter.INTERNAL_BOX || UL == SokobanInterpreter.INTERNAL_BOX_TARGET)
                        && (LR == SokobanInterpreter.INTERNAL_BOX || LR == SokobanInterpreter.INTERNAL_BOX_TARGET))
                  // __
                  // BW
                  || (LR == SokobanInterpreter.WALL && LL == SokobanInterpreter.INTERNAL_BOX
                        && (UL == SokobanInterpreter.INTERNAL_BOX || UL == SokobanInterpreter.INTERNAL_BOX_TARGET)
                        && (UR == SokobanInterpreter.INTERNAL_BOX || UR == SokobanInterpreter.INTERNAL_BOX_TARGET))
                  // B_
                  // _W
                  || (LR == SokobanInterpreter.WALL && UL == SokobanInterpreter.INTERNAL_BOX
                        && (LL == SokobanInterpreter.INTERNAL_BOX || LL == SokobanInterpreter.INTERNAL_BOX_TARGET)
                        && (UR == SokobanInterpreter.INTERNAL_BOX || UR == SokobanInterpreter.INTERNAL_BOX_TARGET))
                  // _B
                  // _W
                  || (LR == SokobanInterpreter.WALL && UR == SokobanInterpreter.INTERNAL_BOX
                        && (LL == SokobanInterpreter.INTERNAL_BOX || LL == SokobanInterpreter.INTERNAL_BOX_TARGET)
                        && (UL == SokobanInterpreter.INTERNAL_BOX || UL == SokobanInterpreter.INTERNAL_BOX_TARGET))) {
               System.err.println("Failed Case 3c, invalid box structure, 3 boxes around one wall");
               return false;
            }

            // 4 boxes
            if ((UL == 10 && (UR == 10 || UR == 26) && (LL == 10 || LL == 26) && (LR == 10 || LR == 26))
                  || (UR == 10 && (UL == 10 || UL == 26) && (LL == 10 || LL == 26) && (LR == 10 || LR == 26))
                  || (LL == 10 && (UL == 10 || UL == 26) && (UR == 10 || UR == 26) && (LR == 10 || LR == 26))
                  || (LR == 10 && (UL == 10 || UL == 26) && (LL == 10 || LL == 26) && (UR == 10 || UR == 26))) {
               System.err.println("Failed Case 3d, invalid box structure, 4 boxes in a 2 by 2 space");
               return false;
            }

         }
      }
      return true;
   }

}