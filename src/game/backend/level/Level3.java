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
        return new Leve13State(MAX_MOVES);
    }

    @Override
    public void initialize(){
        super.initialize();
        setCagedCandy();
    }

    public static String LevelInfo(){
        return "You have " + MAX_MOVES + "to remove all the cages.";
    }

    @Override
    public boolean tryMove(int i1, int j1, int i2, int j2) {
        return super.tryMove(i1,j1,i2,j2);
    }

    private void setCagedCandy(){
        for(int i = 1; i < SIZE-1; i++) {
            convertToCaged(4, i);
        }
    }

    private void convertToCaged(int i, int j){
        if (g()[i][j].getContent() instanceof Candy)
            g()[i][j].setContent(new CagedCandy(((Candy) g()[i][j].getContent()).getColor()));
    }

    private class Leve13State extends GameState {
        private long cageCount = 0;
        private int maxMoves;

        private Leve13State(int maxMoves) {
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
