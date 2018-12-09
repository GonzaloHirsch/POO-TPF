package game.backend.move;

import game.backend.Grid;
import game.backend.element.*;

/*
	The move turns all color-matching candies into wrapped candies that then explode.
 */

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
		CandyColor color = candy.getColor();

		//	Destroys the bomb and the wrapped candy
		clearContent(i1, j1);
		clearContent(i2, j2);

		/*
		//	Replaces all candies with wrapped ones of the same color
		for(int i = 0; i < Grid.SIZE; i++) {
			for(int j = 0; j < Grid.SIZE; j++) {
				if (candy.equals(get(i, j))) {
					setContent(i, j, createWrapped(color));
				}
			}
		}
		*/
		//	Updates grid with the new content

		//	Clears all candy matching the color
		for(int i = 0; i < Grid.SIZE; i++) {
			for(int j = 0; j < Grid.SIZE; j++) {
				if (candy.equals(get(i, j))) {
					clearContent(i, j);
				}
			}
		}

		wasUpdated();

		int r = (int)(Math.random() * CandyColor.values().length);
		Candy c = new Candy(CandyColor.values()[r]);
/*
		for(int i = -1; i < 2; i++) {
			for(int j = -1; j < 2; j++) {
				if (i1 + i >= 0 && i1 + i < Grid.SIZE && j1 + j >= 0 && j1 + j < Grid.SIZE) {
					clearContent(i1 + i, j1 + j);
				}
			}
		}
		for(int i = -1; i < 2; i++) {
			for(int j = -1; j < 2; j++) {
				if (i2 + i >= 0 && i2 + i < Grid.SIZE && j2 + j >= 0 && j2 + j < Grid.SIZE) {
					clearContent(i2 + i, j2 + j);
				}
			}
		}
		*/

		//	Clears another random candy color
		for(int i = 0; i < Grid.SIZE; i++) {
			for(int j = 0; j < Grid.SIZE; j++) {
				if (c.equals(get(i, j))) {
					clearContent(i, j);
				}
			}
		}
	}

	/**
	 * Creates a new wrapped candy with the given color.
	 * @param color
	 * @return
	 */
	private Candy createWrapped(CandyColor color) {
		Candy c = new WrappedCandy();
		c.setColor(color);
		return c;
	}


}
