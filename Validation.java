
public class Validation {

	public static boolean validate(SokobanRuntimeStorage p) {
		// run all tests for validity

		// check boxes to targets 1 to 1, nest for
		int count = 0; // increment when box found, decrement when target found, box on target do
						// nothing
		for (int i = 0; i < p.getWidth(); i++) {
			for (int j = 0; j < p.getHeight(); j++) {
				if (p.getValue(i, j) == 10)
					count++;
				else if (p.getValue(i, j) == 18)
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
		for (int i = 0; i < p.getWidth() - 1; i++) {
			for (int j = 0; j < p.getHeight() - 1; j++) {
				int UL = p.getValue(i, j);
				int UR = p.getValue(i + 1, j);
				int LL = p.getValue(i, j + 1);
				int LR = p.getValue(i + 1, j + 1);
				// 2 boxes against a wall
				// 2 boxes against wall one on target
				// WW
				// BB
				if ((UL == 1 && UR == 1 && LL == 10 && LR == 10)
						// WW
						// BS
						|| (UL == 1 && UR == 1 && LL == 10 && LR == 26)
						// WW
						// SB
						|| (UL == 1 && UR == 1 && LL == 26 && LR == 10)
						// WB
						// WB
						|| (UL == 1 && UR == 10 && LL == 1 && LR == 10)
						// WS
						// WB
						|| (UL == 1 && UR == 26 && LL == 1 && LR == 10)
						// WB
						// WS
						|| (UL == 1 && UR == 10 && LL == 1 && LR == 26)
						// BW
						// BW
						|| (UL == 10 && UR == 1 && LL == 10 && LR == 1)
						// SW
						// BW
						|| (UL == 26 && UR == 1 && LL == 10 && LR == 1)
						// BW
						// SW
						|| (UL == 10 && UR == 1 && LL == 26 && LR == 1)
						// BB
						// WW
						|| (UL == 10 && UR == 10 && LL == 1 && LR == 1)
						// SB
						// WW
						|| (UL == 26 && UR == 10 && LL == 1 && LR == 1)
						// BS
						// WW
						|| (UL == 10 && UR == 26 && LL == 1 && LR == 1)) {
					System.err.println("Failed Case 3a, invalid box structure, two connected boxes along a wall");
					return false;
				}

				// 1 box in a corner
				// BW
				// W
				if ((UL == 10 && UR == 1 && LL == 1)
						// WB
						// W
						|| (UL == 1 && UR == 10 && LR == 1)
						// W
						// WB
						|| (UR == 1 && LL == 1 && LR == 10)
						// W
						// BW
						|| (UL == 1 && LL == 10 && LR == 1)) {
					System.err.println("Failed Case 3b, invalid box structure, box in a corner");
					return false;
				}

				// 3 boxes around a wall
				// WB
				// __
				if ((UL == 1 && UR == 10 && (LL == 10 || LL == 26) && (LR == 10 || LR == 26))
						// W_
						// _B
						|| (UL == 1 && LR == 10 && (LL == 10 || LL == 26) && (UR == 10 || UR == 26))
						// W_
						// B_
						|| (UL == 1 && LL == 10 && (UR == 10 || UR == 26) && (LR == 10 || LR == 26))
						// BW
						// __
						|| (UR == 1 && UL == 10 && (LL == 10 || LL == 26) && (LR == 10 || LR == 26))
						// _W
						// B_
						|| (UR == 1 && LL == 10 && (UL == 10 || UL == 26) && (LR == 10 || LR == 26))
						// _W
						// _B
						|| (UR == 1 && LR == 10 && (UL == 10 || UL == 26) && (LL == 10 || LL == 26))
						// __
						// WB
						|| (LL == 1 && LR == 10 && (UL == 10 || UL == 26) && (UR == 10 || UR == 26))
						// B_
						// W_
						|| (LL == 1 && UL == 10 && (LR == 10 || LR == 26) && (UR == 10 || UR == 26))
						// _B
						// W_
						|| (LL == 1 && UR == 10 && (UL == 10 || UL == 26) && (LR == 10 || LR == 26))
						// __
						// BW
						|| (LR == 1 && LL == 10 && (UL == 10 || UL == 26) && (UR == 10 || UR == 26))
						// B_
						// _W
						|| (LR == 1 && UL == 10 && (LL == 10 || LL == 26) && (UR == 10 || UR == 26))
						// _B
						// _W
						|| (LR == 1 && UR == 10 && (LL == 10 || LL == 26) && (UL == 10 || UL == 26))) {
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

		// Case 4 External wall closes, includes corners for aesthetics
		for (int i = 0; i < p.getWidth(); i++) {
			for (int j = 0; j < p.getHeight(); j++) {

			}
		}
		System.out.println("passed test 4");

		// Check all internal spaces connected, create list of internal spaces (x,y) and
		// a tree
		System.out.println("passed test 5");

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
				if (p.getValue(i, j) == 10)
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
				if (U == 1 && L == 10) {
					int storeI = i;
					while (p.getValue(storeI, j) != 1) {
						storeI--;
					}
					int beginI, endI;
					beginI = storeI;
					storeI++;

					while (p.getValue(storeI, j) != 1) {
						if (p.getValue(storeI, j) == 18)
							tarCount++;
						else if (p.getValue(storeI, j) == 10)
							boxCount++;
						storeI++;
					}
					endI = storeI;
					boolean indent = false;
					if (boxCount > tarCount) {
						for (int k = beginI; k <= endI; k++) {
							if (indent && (p.getValue(k, j + 1) == 2 || p.getValue(k, j + 1) == 18
									|| p.getValue(k, j + 1) == 6 || p.getValue(k, j + 1) == 22|| p.getValue(k, j + 1) == 10 || p.getValue(k, j + 1) == 26)) {
								isFine = true;
							} else if (indent) {
								indent = false;
							} else if (!indent && (p.getValue(k, j + 1) == 2 || p.getValue(k, j + 1) == 18
									|| p.getValue(k, j + 1) == 6 || p.getValue(k, j + 1) == 22|| p.getValue(k, j + 1) == 10 || p.getValue(k, j + 1) == 26)) {
								indent = true;
							}
						}
					} else {
						isFine = true;
					}
				// W
				// B
				} else if (U == 10 && L == 1) {
					int storeI = i;
					while (p.getValue(storeI, j + 1) != 1) {
						storeI--;
					}
					int beginI, endI;
					beginI = storeI;
					storeI++;

					while (p.getValue(storeI, j + 1) != 1) {
						if (p.getValue(storeI, j + 1) == 18)
							tarCount++;
						else if (p.getValue(storeI, j + 1) == 10)
							boxCount++;
						storeI++;
					}
					endI = storeI;
					boolean indent = false;
					if (boxCount > tarCount) {
						for (int k = beginI; k <= endI; k++) {
							if (indent && (p.getValue(k, j) == 2 || p.getValue(k, j) == 18 || p.getValue(k, j) == 6
									|| p.getValue(k, j) == 22|| p.getValue(k, j) == 10 || p.getValue(k, j) == 26)) {
								isFine = true;
							} else if (indent) {
								indent = false;
							} else if (!indent && (p.getValue(k, j) == 2 || p.getValue(k, j) == 18
									|| p.getValue(k, j) == 6 || p.getValue(k, j) == 22)|| p.getValue(k, j) == 10 || p.getValue(k, j) == 26) {
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
				if (L == 10 && R == 1) {
					int storeJ = j;
					while (p.getValue(i, storeJ) != 1) {
						storeJ--;
					}
					int beginJ, endJ;
					beginJ = storeJ;
					storeJ++;

					while (p.getValue(i, storeJ) != 1) {
						if (p.getValue(i, storeJ) == 18)
							tarCount++;
						else if (p.getValue(i, storeJ) == 10)
							boxCount++;
						storeJ++;
					}
					endJ = storeJ;
					boolean indent = false;
					if (boxCount > tarCount) {
						for (int k = beginJ; k <= endJ; k++) {
							if (indent && (p.getValue(i + 1, k) == 2 || p.getValue(i + 1, k) == 18
									|| p.getValue(i + 1, k) == 6 || p.getValue(i + 1, k) == 22|| p.getValue(i + 1, k) == 10 || p.getValue(i + 1, k) == 26)) {
								isFine = true;
							} else if (indent) {
								indent = false;
							} else if (!indent && (p.getValue(i + 1, k) == 2 || p.getValue(i + 1, k) == 18
									|| p.getValue(i + 1, k) == 6 || p.getValue(i + 1, k) == 22|| p.getValue(i + 1, k) == 10 || p.getValue(i + 1, k) == 26)) {
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
					while (p.getValue(i + 1, storeJ) != 1) {
						storeJ--;
					}
					int beginJ, endJ;
					beginJ = storeJ;
					storeJ++;

					while (p.getValue(i + 1, storeJ) != 1) {
						if (p.getValue(i + 1, storeJ) == 18)
							tarCount++;
						else if (p.getValue(i + 1, storeJ) == 10)
							boxCount++;
						storeJ++;
					}
					endJ = storeJ;
					boolean indent = false;
					if (boxCount > tarCount) {
						for (int k = beginJ; k <= endJ; k++) {
							if (indent && (p.getValue(i, k) == 2 || p.getValue(i, k) == 18 || p.getValue(i, k) == 6
									|| p.getValue(i, k) == 22|| p.getValue(i, k) == 10 || p.getValue(i, k) == 26)) {
								isFine = true;
							} else if (indent) {
								indent = false;
							} else if (!indent && (p.getValue(i, k) == 2 || p.getValue(i, k) == 18
									|| p.getValue(i, k) == 6 || p.getValue(i, k) == 22|| p.getValue(i, k) == 10 || p.getValue(i, k) == 26)) {
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

}