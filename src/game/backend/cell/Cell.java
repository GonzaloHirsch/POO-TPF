package game.backend.cell;

import game.backend.Grid;
import game.backend.element.Element;
import game.backend.element.Fruit;
import game.backend.element.Nothing;
import game.backend.element.Wall;
import game.backend.move.Direction;

public class Cell {
	
	private Grid grid;
	private Cell[] around = new Cell[Direction.values().length];
	private Element content;
	
	public Cell(Grid grid) {
		this.grid = grid;
		//	Initializes all cells with nothing
		this.content = new Nothing();
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
	
	public boolean isEmpty() {
		return !content.isSolid();
	}

	public Element getContent() {
		return content;
	}
	
	public void clearContent() {
		if (content.isMovable()) {
			Direction[] explosionCascade = content.explode();
			grid.cellExplosion(content);
			this.content = new Nothing();
			if (explosionCascade != null) {
				expandExplosion(explosionCascade); 
			}
			this.content = new Nothing();
		}
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

				//	If the element is a fruit and doesn't have a wall below
				if (this.getContent() instanceof Fruit && around[Direction.DOWN.ordinal()].isMovable())
					return true;

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
