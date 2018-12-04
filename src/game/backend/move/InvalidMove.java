package game.backend.move;

import game.backend.Grid;

/*
    This move is used to represent all the moves that are not valid.
    Such as:
        Bomb - Fruit
        Fruit - Fruit
 */
public class InvalidMove extends Move {

    public InvalidMove(Grid grid) {
        super(grid);
    }

    @Override
    public boolean internalValidation(){ return false; }

    @Override
    public void removeElements() { /* It cannot happen this move*/ }
}
