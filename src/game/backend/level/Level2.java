package game.backend.level;

import game.backend.GameState;
import game.backend.Grid;
import game.backend.cell.CandyGeneratorCell;
import game.backend.cell.Cell;
import game.backend.cell.FruitGeneratorCell;
import game.backend.element.Wall;

/**
    FRUIT LEVEL
    The player has to move the fruits to the bottom of the grid.
    Win Condition: Clear all fruits
 */
public class Level2 extends Grid {

    private static int FRUIT_AMOUNT = 6;
    private static int MAX_MOVES = 30;

    /**
        This number (between 0 and 1) represents the chances of a fruit appearing.
        Suggested values:
            All 6 fruits in the beginning --> FRUIT_CHANCE = 0.2
            About 4 fruits in the beginning --> FRUIT_CHANCE = 0.09
            About 2 fruits in the beginning --> FRUIT_CHANCE = 0.05
        I found out around 0.05 works best, spawning maybe 2 or 3 at the start.
     */
    private static double FRUIT_CHANCE = 0.05;

    private Cell wallCell;
    private Cell fruitGenCell;

    @Override
    protected GameState newState() {
        return new Level2State(Level2.FRUIT_AMOUNT, Level2.MAX_MOVES);
    }

    public static String LevelInfo(){
        return "You have " + MAX_MOVES + " moves to clear " + FRUIT_AMOUNT + " fruits to win.";
    }

    public int getMaxMoves(){  return MAX_MOVES;}

    @Override
    protected void fillCells() {

        wallCell = new Cell(this);
        wallCell.setContent(new Wall());
        fruitGenCell = new FruitGeneratorCell(this, Level2.FRUIT_AMOUNT, Level2.FRUIT_CHANCE, new CandyGeneratorCell(this));

        //corners
        g()[0][0].setAround(fruitGenCell, g()[1][0], wallCell, g()[0][1]);
        g()[0][SIZE-1].setAround(fruitGenCell, g()[1][SIZE-1], g()[0][SIZE-2], wallCell);
        g()[SIZE-1][0].setAround(g()[SIZE-2][0], wallCell, wallCell, g()[SIZE-1][1]);
        g()[SIZE-1][SIZE-1].setAround(g()[SIZE-2][SIZE-1], wallCell, g()[SIZE-1][SIZE-2], wallCell);

        //upper line cells
        for (int j = 1; j < SIZE-1; j++) {
            g()[0][j].setAround(fruitGenCell,g()[1][j],g()[0][j-1],g()[0][j+1]);
        }
        //bottom line cells
        for (int j = 1; j < SIZE-1; j++) {
            g()[SIZE-1][j].setAround(g()[SIZE-2][j], wallCell, g()[SIZE-1][j-1],g()[SIZE-1][j+1]);
        }
        //left line cells
        for (int i = 1; i < SIZE-1; i++) {
            g()[i][0].setAround(g()[i-1][0],g()[i+1][0], wallCell ,g()[i][1]);
        }
        //right line cells
        for (int i = 1; i < SIZE-1; i++) {
            g()[i][SIZE-1].setAround(g()[i-1][SIZE-1],g()[i+1][SIZE-1], g()[i][SIZE-2], wallCell);
        }
        //central cells
        for (int i = 1; i < SIZE-1; i++) {
            for (int j = 1; j < SIZE-1; j++) {
                g()[i][j].setAround(g()[i-1][j],g()[i+1][j],g()[i][j-1],g()[i][j+1]);
            }
        }
    }

    @Override
    public boolean tryMove(int i1, int j1, int i2, int j2) {
        boolean ret;
        if (ret = super.tryMove(i1, j1, i2, j2)) {
            state().addMove();
        }
        return ret;
    }

    private class Level2State extends GameState {
        private int fruitAmount;
        private int maxMoves;

        public Level2State(int fruitAmount, int maxMoves) {
            this.fruitAmount = fruitAmount;
            this.maxMoves = maxMoves;
        }

        public int getMaxMoves() { return this.maxMoves; };

        @Override
        public boolean gameOver() {
            return playerWon() || getMoves() >= this.maxMoves;
        }

        //  The player wins when all the fruits are cleared
        @Override
        public boolean playerWon() {
            return getScore() >= this.fruitAmount;
        }
    }

}
