package game.backend.move;

import game.backend.Grid;
import game.backend.element.*;

public class BombWrappedMove extends Move {

	public BombWrappedMove(Grid grid) {
		super(grid);	
	}

	/**
	 * 	The original code was changed because it was not making the right removal of elements.
	 * 	It should destroy all candies matching the color of the wrapped one, and also destroy all
	 * 	candies of another random color, we verified that the correct move was the described above with the
	 * 	original Candy Crush game.
	 * 	The original code is kept for comparison.
	 * 	It cycles the grid 2 times.
	 */
	@Override
	public void removeElements() {
		//	It needs the candy for the color comparison
		Candy candy = (Candy) (get(i1, j1) instanceof Bomb ? get(i2, j2) : get(i1, j1));
		//	It needs the color to create the new candy with it

		//	Destroys the bomb and the wrapped candy
		clearContent(i1, j1);
		clearContent(i2, j2);

		//	Clears all candy matching the color
		this.candyColorRemover(candy);

		wasUpdated();

		//	Creates a new candy of a random color, can be the same as before
		int r = (int)(Math.random() * CandyColor.values().length);
		Candy c = new Candy(CandyColor.values()[r]);

		//	Clears another random candy color
		this.candyColorRemover(c);
	}
}
