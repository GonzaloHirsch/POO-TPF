package game.backend.gametypes;

import game.backend.element.Element;
import game.backend.element.Fruit;

public class FruitCandyGame extends CandyGame {
    public FruitCandyGame(Class<?> clazz) {
        super(clazz);
    }

    /*
        This method is for counting the score as fruits already cleared
     */
    @Override
    public void cellExplosion(Element e) {
        if (e instanceof Fruit)
            this.state.addScore(e.getScore());
    }

    @Override
    public String toString() {
        return getMovesLeft() + " Moves Left - " + getScore() + " Fruits";
    }
}
