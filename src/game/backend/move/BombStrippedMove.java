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

	/**
	 * 	It destroys all color-matching candies by turning them into striped candies and
	 * 	then exploding them all.
	 * 	Cycles entire grid twice.
	 */
	@Override
	public void removeElements() {
		//	It needs to get the candy for the color comparison
		Candy candy = (Candy) (get(i1, j1) instanceof Bomb ? get(i2, j2) : get(i1, j1));
		//	Takes the color to then create the new candy of the same color
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

	/**
	 * Creates a striped candy with the given color.
	 * The direction is random.
	 * @param color
	 * @return
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
