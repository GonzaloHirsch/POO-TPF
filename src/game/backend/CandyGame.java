package game.backend;

import game.backend.cell.Cell;
import game.backend.element.Element;
import game.backend.element.Fruit;
import game.backend.element.FruitType;

public class CandyGame implements GameListener {
	
	private Class<?> levelClass;
	private Grid grid;
	private GameState state;
	
	public CandyGame(Class<?> clazz) {
		this.levelClass = clazz;
	}
	
	public void initGame() {
		try {
			grid = (Grid)levelClass.newInstance();
		} catch(IllegalAccessException | InstantiationException e) {
			System.out.println("ERROR AL INICIAR");
		}
		state = grid.createState();
		grid.initialize();
		addGameListener(this);
	}
	
	public int getSize() {
		return Grid.SIZE;
	}
	
	public boolean tryMove(int i1, int j1, int i2, int j2){
		// If any of the elements chosen to move is a Fruit, it returns false because they are not selectable
		if (grid.g()[i1][j1].getContent() instanceof Fruit || grid.g()[i2][j2].getContent() instanceof Fruit)
				return false;
		return grid.tryMove(i1, j1, i2, j2);
	}
	
	public Cell get(int i, int j){
		return grid.getCell(i, j);
	}
	
	public void addGameListener(GameListener listener) {
		grid.addListener(listener);
	}
	
	public long getScore() {
		return state.getScore();
	}
	
	public boolean isFinished() {
		return state.gameOver();
	}
	
	public boolean playerWon() {
		return state.playerWon();
	}
	
	@Override
	public void cellExplosion(Element e) {
		state.addScore(e.getScore());
	}
	
	@Override
	public void gridUpdated() {
		//
	}

}
