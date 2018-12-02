package game.backend.cell;

import game.backend.Grid;
import game.backend.element.*;

public class CandyGeneratorCell extends Cell {
	
	public CandyGeneratorCell(Grid grid) {
		super(grid);
	}
	
	@Override
	public boolean isMovable(){
		return true;
	}
	
	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public Element getContent() {
		//TEST
		//return new Fruit(FruitType.values()[1]);

		int i = (int)(Math.random() * CandyColor.values().length);
		return new Candy(CandyColor.values()[i]);

	}
	
	@Override
	public Element getAndClearContent() {
		return getContent();
	}

	@Override
	public boolean fallUpperContent() {
		throw new IllegalStateException();
	}
	
	@Override
	public void setContent(Element content) {
		throw new IllegalStateException();
	}
	
	@Override
	public boolean equals(Object obj) {
		return false;
	}

}
