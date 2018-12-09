package game.backend.move;

import game.backend.Grid;

public class TwoWrappedMove extends Move {

	public TwoWrappedMove(Grid grid) {
		super(grid);
	}

	/**
	 * 	It removes a rectangle around both wrapped candies.
	 */
	@Override
	public void removeElements() {
		int currI, currJ;

		//	Destroys both candies
		clearContent(i1, j1);
		clearContent(i2, j2);

		if (i1 == i2) {
			if (j1 < j2) {
				currI = i1;
				currJ = j1;
			} else {
				currI = i2;
				currJ = j2;
			}
			SideRemover(currI, currJ);
		} else {
			if (i1 < i2) {
				currI = i1;
				currJ = j1;
			} else {
				currI = i2;
				currJ = j2;
			}
			SideRemover(currI, currJ);
		}
	}

	/**
	 * Removes the sides of the two wrapped candies.
	 * Verifications are needed not to go over the size of the array.
	 * This was happening in the original implementation.
	 * This solves the issue.
	 * @param i
	 * @param j
	 */
	private void SideRemover(int i, int j){
		if (j-1 < Grid.SIZE && 0 <= j-1)
			clearContent(i,j-1);
		if (j + 2 < Grid.SIZE && 0 <= j+2)
			clearContent(i, j + 2);
		for(int n = -1; n < 3; n++) {
			if (j + n < Grid.SIZE && 0 <= j + n){
				if (i-1 < Grid.SIZE && 0 <= i-1)
					clearContent(i - 1, j + n);
				if (i+1 < Grid.SIZE && 0 <= i+1)
					clearContent(i + 1, j + n);
			}
		}
	}

}
