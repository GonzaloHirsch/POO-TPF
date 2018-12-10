package game.backend.level;

//  Cage/Jaula Level

import game.backend.GameState;
import game.backend.cell.Cell;
import game.backend.element.*;

public class Level3 extends Level1 {
    private static int MAX_MOVES= 30;

    @Override
    public  void fillCells(){
        super.fillCells();
    }


    @Override
    protected GameState newState() {
        return new Level3State(MAX_MOVES);
    }

    @Override
    public void initialize(){
        super.initialize();
        setCagedCandy();
    }

    /**
     * 	Method to get the level information in order to be displayed in the tooltips
     * @return	A string containing all important level info
     */
    public static String LevelInfo(){
        return "You have " + MAX_MOVES + " moves to remove all the cages.";
    }

    @Override
    public boolean tryMove(int i1, int j1, int i2, int j2) {
        return super.tryMove(i1,j1,i2,j2);
    }

    private void setCagedCandy(){
        for(int j = 1; j < SIZE-1; j++) {
            convertToCaged(4, j);
        }
    }

    private void convertToCaged(int i, int j){
        if (g()[i][j].getContent() instanceof Candy)
            g()[i][j].setContent(new CagedCandy(((Candy) g()[i][j].getContent()).getColor()));
    }

    private class Level3State extends GameState {
        private long cageCount = 0;
        private int maxMoves;

        private Level3State(int maxMoves) {
            this.maxMoves = maxMoves;
            for(Cell[] i : g()){
                for(Cell c : i){
                    if(c.getContent().getClass() == CagedCandy.class)
                        cageCount++;
                }
            }
        }

        public int getMaxMoves() { return this.maxMoves; };

        @Override
        public boolean gameOver() {
            return playerWon() || getMoves() >= maxMoves;
        }

        @Override
        public boolean playerWon() {
            return getScore() >= cageCount;
        }
    }
}
