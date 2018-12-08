package game.backend;

import game.backend.element.Element;
import game.backend.element.Fruit;

public class FruitCandyGame extends CandyGame {
    public FruitCandyGame(Class<?> clazz) {
        super(clazz);
    }

    public int getMovesLeft() { return state.getMaxMoves() - state.getMoves();};

    /*
        This method is for counting the score as fruits already cleared
     */
    @Override
    public void cellExplosion(Element e) {
        if (e instanceof Fruit)
            this.state.addScore(e.getScore());
    }
}
