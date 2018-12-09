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

		int i = (int)(Math.random() * CandyColor.values().length);
		return new Candy(CandyColor.values()[i]);


		//	Only wrapped testing
		/*
		WrappedCandy ret = new WrappedCandy();

		ret.setColor(CandyColor.values()[i]);
		return ret;
		*/

		//	All bomb and 2 colors testing
		/*
		if (Math.random() > 0.4)
			return new Candy(CandyColor.BLUE);
		else if (Math.random() > 0.4)
			return new Candy(CandyColor.GREEN);
		Bomb bomb = new Bomb();
		return bomb;
		*/
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
