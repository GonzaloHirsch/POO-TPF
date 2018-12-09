package game.backend.move;

import game.backend.Grid;
import game.backend.element.Bomb;
import game.backend.element.Candy;

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

		//	Cycles through the entire grid
		for(int i = 0; i < Grid.SIZE; i++) {
			for(int j = 0; j < Grid.SIZE; j++) {
				//	It compares the color of each candy with the original one
				if (candy.equals(get(i, j))) {
					clearContent(i, j);
				}
			}
		}
	}

}
