package game.backend.move;

import game.backend.Grid;
import game.backend.element.Bomb;
import game.backend.element.Candy;
import game.backend.element.CandyColor;
import game.backend.element.HorizontalStripedCandy;
import game.backend.element.VerticalStripedCandy;

public class BombStrippedMove extends Move {

	public BombStrippedMove(Grid grid) {
		super(grid);
	}
	
	@Override
	public void removeElements() {
		Candy candy = (Candy) (get(i1, j1) instanceof Bomb ? get(i2, j2) : get(i1, j1));
		CandyColor color = candy.getColor();

		//	Replaces all the candies that have the same color as the matched candy with striped ones
		for(int i = 0; i < Grid.SIZE; i++) {
			for(int j = 0; j < Grid.SIZE; j++) {
				if (candy.equals(get(i, j))) {
					setContent(i, j, createStriped(color));
				}
			}
		}

		//	Updates grid with the new content
		wasUpdated();

		/*
			Clears the content for each striped candy of the same color as the matched candy.
			It uses clear content because it calls the explode method, so it explodes each striped color candy.
		 */
		for(int i = 0; i < Grid.SIZE; i++) {
			for(int j = 0; j < Grid.SIZE; j++) {
				if (candy.equals(get(i, j))) {
					clearContent(i, j);
				}
			}
		}
	}

	/*
		Creates striped candies of the given color in random directions
	 */
	private Candy createStriped(CandyColor color) {
		Candy c;
		if ((int)(Math.random() * 2) == 0) {
			c = new HorizontalStripedCandy();
		} else {
			c = new VerticalStripedCandy();
		}
		c.setColor(color);
		return c;
	}

}
