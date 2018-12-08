package game.backend.move;

import game.backend.Figure;
import game.backend.FigureDetector;
import game.backend.Grid;
import game.backend.element.Fruit;
import game.backend.element.Nothing;

public class CandyMove extends Move {

	private Figure f1;
	private Figure f2;
	
	private FigureDetector detector;
	private Grid grid;
	
	public CandyMove(Grid grid) {
		super(grid);
		this.grid = grid;
	}
	
	@Override
	public boolean internalValidation() {
		this.detector = new FigureDetector(grid);

		if (this.grid.getCell(i1,j1).getContent() instanceof Fruit || this.grid.getCell(i1,j1).getContent() instanceof Nothing)
			f1 = null;
		else
			f1 = detector.checkFigure(i1, j1);
		if (this.grid.getCell(i2,j2).getContent() instanceof Fruit || this.grid.getCell(i1,j1).getContent() instanceof Nothing)
			f2 = null;
		else
			f2 = detector.checkFigure(i2, j2);
		return f1 != null || f2 != null;
	}	

	@Override
	public void removeElements() {
		if (f1 != null) {
			detector.removeFigure(i1, j1, f1);
		}
		if (f2 != null) {
			detector.removeFigure(i2, j2, f2);
		}
	}

}
