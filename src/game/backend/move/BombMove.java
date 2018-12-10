package game.backend.move;

import game.backend.Grid;
import game.backend.cell.Cell;
import game.backend.element.Bomb;
import game.backend.element.Candy;
import game.backend.element.Element;

public class BombMove extends Move {
	
	public BombMove(Grid grid) {
		super(grid);
	}

	/**
		It destroys all the candies matching the color of the matched candy.
	 	Cycles the entire grid.
	 */
	@Override
	public void removeElements() {
		//	It needs to get the candy for the color comparison
		Candy candy = (Candy) (get(i1, j1) instanceof Bomb ? get(i2, j2) : get(i1, j1));

		//	Clears candy and bomb
		clearContent(i1, j1);
		clearContent(i2, j2);

		//	Remove all candies matching the color of the given one
		removeElements(candy);
	}

	/**
	 * 	Method to remove all candies that match the color of the passed candy
	 * 	It's used when a bomb is exploded by another candy.
	 * @param candy		candy used as reference to compare colors
	 */
	public void removeElements(Candy candy) {
		this.candyColorRemover(candy);
	}

}
