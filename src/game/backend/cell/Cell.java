package game.backend.cell;

import game.backend.Grid;
import game.backend.element.*;
import game.backend.move.BombMove;
import game.backend.move.Direction;

import java.awt.geom.Point2D;
import java.util.Vector;

public class Cell {
	private Grid grid;
	private Cell[] around = new Cell[Direction.values().length];
	private Element content;
	
	public Cell(Grid grid) {
		this.grid = grid;
		//	Initializes all cells with nothing
		this.content = new Nothing();
	}

	protected Grid getGrid(){
		return this.grid;
	}

	/*
		Fills the around array with the cells adyacent to the actual one
	 */
	public void setAround(Cell up, Cell down, Cell left, Cell right) {
		this.around[Direction.UP.ordinal()] = up;
		this.around[Direction.DOWN.ordinal()] = down;
		this.around[Direction.LEFT.ordinal()] = left;
		this.around[Direction.RIGHT.ordinal()] = right;
	}

	public boolean hasFloor() {
		return !around[Direction.DOWN.ordinal()].isEmpty();
	}
	
	public boolean isMovable(){
		return content.isMovable();
	}

	//	All elements are solid except for Nothing
	public boolean isEmpty() {
		return !content.isSolid();
	}

	public Element getContent() {
		return content;
	}
	
	public void clearContent() {
		if (content.isMovable()) {
			/*
				If a bomb is cleared by another fruit, this should explode.
				This piece of code makes sure of that.
			 */
			if (content instanceof Bomb){

				BombMove bombMove = new BombMove(grid);

				//	Candy with a random color
				int r = (int)(Math.random() * CandyColor.values().length);

				//	Bomb explosion
				bombMove.removeElements(new Candy(CandyColor.values()[r]));

				//	Removes the bomb
				this.content = new Nothing();
			} else if (content instanceof Fruit){
				if (!this.around[Direction.DOWN.ordinal()].isMovable()) {
				/*
					In case a fruit is tried to be exploded.
					It checks if there is a wall bellow it, in which case it explodes.
					If there isn't a wall, the fruit is not exploded by the effect of other combos
				 */
					grid.cellExplosion(content);
					this.content = new Nothing();
				}
			} else {
				Direction[] explosionCascade = content.explode();
				grid.cellExplosion(content);
				this.content = new Nothing();
				if (explosionCascade != null) {
					expandExplosion(explosionCascade);
				}

				this.content = new Nothing();
			}
		} else {
			if(content instanceof CagedCandy){
				grid.cellExplosion(content);
				CagedCandy cagedCandy = (CagedCandy) content;   // If it's a caged candy, I replace it with a class candy of it's color
                setContent(new Candy(cagedCandy.getColor()));
            }

		}
		grid.wasUpdated();		// Update the grid when clearing content
	}
	
	private void expandExplosion(Direction[] explosion) {
		for(Direction d: explosion) {
			this.around[d.ordinal()].explode(d);
		}
	}
	
	private void explode(Direction d) {
		clearContent();
		if (this.around[d.ordinal()] != null)
			this.around[d.ordinal()].explode(d);
	}
	
	public Element getAndClearContent() {
		if (content.isMovable()) {
			Element ret = content;
			this.content = new Nothing();
			return ret;
		} else {
			if(content instanceof CagedCandy){
				CagedCandy cagedCandy = (CagedCandy) content;   // If it's a caged candy, I replace it with a class candy of it's color
				setContent(new Candy(cagedCandy.getColor()));
			}
		}
		return null;
	}

	/*
		Moves the content recursively from top to bottom, until it hits something "solid"
	 */
	public boolean fallUpperContent() {
		Cell up = around[Direction.UP.ordinal()];
		if ((this.isEmpty() || this.getContent() instanceof Fruit) && !up.isEmpty() && up.isMovable()) {

			//	Retrieves the content from the cell above itself and sets it to itself
			this.content = up.getAndClearContent();
			grid.wasUpdated();
			if (this.hasFloor()) {

				//	If the element is a fruit
				if (this.getContent() instanceof Fruit){
					if (around[Direction.DOWN.ordinal()].isMovable())
						return true;
					/*
						If the state is null, it means it was not initialized yet, so that means the game didn't start yet.
						But if the game didn't start, if a fruit reaches the bottom and is eliminated it shouldn't count.
					 */
					if (grid.state() == null)
						FruitGeneratorCell.incrementSpawnedFruits(-1);
				}


				//	If the element is a candy or a fruit with a wall below
				grid.tryRemove(this);
				return true;
			} else {
				Cell down = around[Direction.DOWN.ordinal()];
				return down.fallUpperContent();
			}
		} 
		return false;
	}
	
	public void setContent(Element content) {
		this.content = content;
	}

}
