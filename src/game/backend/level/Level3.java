package game.backend.level;

//  Cage/Jaula Level

import game.backend.GameState;
import game.backend.element.*;

public class Level3 extends Level1 {
    private static int MAX_MOVES= 30;
    private int cageCount = 0;

    @Override
    public  void fillCells(){
        super.fillCells();
    }


    @Override
    protected GameState newState() {
        return new Leve13State(cageCount, MAX_MOVES);
    }

    @Override
    public void initialize(){
        super.initialize();
        setCagedCandy();
        System.out.println(cageCount);
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
            g()[4][i].setContent(new CagedCandy(((Candy) g()[4][i].getContent()).getColor()));

            cageCount++;
        }
    }

    private class Leve13State extends GameState {
        private long cageCount;
        private int maxMoves;

        public Leve13State(long cageCount, int maxMoves) {
            this.cageCount = cageCount;
            this.maxMoves = maxMoves;
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
