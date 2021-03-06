package game.backend;

import game.backend.cell.Cell;
import game.backend.cell.FruitGeneratorCell;
import game.backend.element.Candy;
import game.backend.element.CandyColor;
import game.backend.element.Element;
import game.backend.element.Fruit;
import game.backend.move.Move;
import game.backend.move.MoveMaker;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Grid {

	//	Size of the square grid
	public static final int SIZE = 9;

	private Cell[][] g = new Cell[SIZE][SIZE];
	private Map<Cell, Point> gMap = new HashMap<>();
	private GameState state;
	private List<GameListener> listeners = new ArrayList<>();
	private List<FrontEndListener> frontListners = new ArrayList<>();
	private MoveMaker moveMaker;
	private FigureDetector figureDetector;
	
	protected abstract GameState newState();
	protected abstract void fillCells();
	
	protected Cell[][] g() {
		return g;
	}
	
	public GameState state(){
		return state;
	}
	
	public void initialize() {
		moveMaker = new MoveMaker(this);
		figureDetector = new FigureDetector(this);
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				g[i][j] = new Cell(this);
				gMap.put(g[i][j], new Point(i,j));
			}
		}
		fillCells();

		//	This call fills the board with candies
		fallElements();
	}	

	public Element get(int i, int j) {
		return g[i][j].getContent();
	}
	
	public Cell getCell(int i, int j) {
		return g[i][j];
	}

	public void fallElements() {
		int i = SIZE - 1;
		while (i >= 0) {
			int j = 0;
			while (j < SIZE) {
				//	In case a fruit is on the bottom of the board, it removes it
				if (g[SIZE - 1][j].getContent() instanceof Fruit) {

					/*
						If the state is null, it means it was not initialized yet, so that means the game didn't start yet.
						But if the game didn't start, if a fruit reaches the bottom and is eliminated it shouldn't count.
					 */
					if (state == null)
						FruitGeneratorCell.incrementSpawnedFruits(-1);

					this.clearContent(SIZE - 1, j);
				}
				if (g[i][j].isEmpty()) {
					if (g[i][j].fallUpperContent()) {
						i = SIZE;
						j = -1;
						break;
					} 
				}
				j++;
			}	
			i--;
		}
	}
	
	public void clearContent(int i, int j) {
		g[i][j].clearContent();
	}
	
	public void setContent(int i, int j, Element e) {
		g[i][j].setContent(e);
	}

	/*
		Gets the type of movement involved and makes the swap.
		If the move is not valid it swaps again, returning to the state before.
	 */
	public boolean tryMove(int i1, int j1, int i2, int j2) {
		//	Gets the type of movement made
		Move move = moveMaker.getMove(i1, j1, i2, j2);

		/*
			The swap has to be made before the move verification because it can't check
			If its moved from here, the game BREAKS
		 */
        swapContent(i1, j1, i2, j2);
		frontSwapElements(i1, j1, i2, j2);
		if (move != null && move.isValid()) {
			move.removeElements();
			fallElements();
			return true;
		} else {
			frontSwapElements(i1, j1, i2, j2);
			swapContent(i1, j1, i2, j2);
			return false;
		}
	}	

	/*
		Checks whether the piece in the new place creates a shape,
		and if it does, it removes it.
	 */
	public Figure tryRemove(Cell cell) {
		if (gMap.containsKey(cell)) {
			Point p = gMap.get(cell);
			Figure f = figureDetector.checkFigure(p.x, p.y);
			if (f != null) {
				removeFigure(p.x, p.y, f);
			}
			return f;
		}
		return null;
	}
	
	private void removeFigure(int i, int j, Figure f) {
		CandyColor color = ((Candy) get(i, j)).getColor();
		if (f.hasReplacement()) {
			setContent(i, j, f.generateReplacement(color));
		} else {
			clearContent(i, j);
		}
		for (Point p : f.getPoints()) {
			clearContent(i + p.x, j + p.y);
		}
	}

	public void swapContent(int i1, int j1, int i2, int j2) {
		Element e = g[i1][j1].getContent();
		g[i1][j1].setContent(g[i2][j2].getContent());
		g[i2][j2].setContent(e);
		wasUpdated();
	}
	
	public GameState createState() {
		this.state = newState();
		return this.state;
	}
	
	public void addListener(GameListener listener) {
		listeners.add(listener);
	}

	
	public void cellExplosion(Element e) {
		for (GameListener gl: listeners) {
			gl.cellExplosion(e);
		}
	}

	/**
	 * Adds Front-End Listeners which listen to certain Back-End events, and updates correspondingly
	 * @param listener
	 */
	public void addFrontEndListener(FrontEndListener listener){
		this.frontListners.add(listener);
	}

	public void wasUpdated(){
		if (frontListners.size() > 0) {
			for (FrontEndListener calls: frontListners) {
				calls.gridUpdated();
			}
		}
	}

	/**
	 * Event called when two elements get swapped
	 * @param i1
	 * @param j1
	 * @param i2
	 * @param j2
	 */
	public void frontSwapElements(int i1, int j1, int i2, int j2){
		if(g[i1][j1].isMovable() && g[i2][j2].isMovable()) {
			if (frontListners.size() > 0) {
				for (FrontEndListener calls : frontListners) {
					calls.swapElements(i1, j1, i2, j2);
				}
			}
		}
	}

}
