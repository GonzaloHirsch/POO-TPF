package game.backend.move;

import game.backend.CandyGame;
import game.backend.element.CagedCandy;
import game.backend.element.Element;

public class CageCandyGame extends CandyGame {
    public CageCandyGame(Class<?> clazz) {
        super(clazz);
    }

    @Override
    public void cellExplosion(Element e) {
        if(e.getClass() == CagedCandy.class)

            this.state.addScore(1);
    }

    @Override
    public String toString(){
        return getMovesLeft() + " Moves Left - " + getScore() + " Cages";
    }
}
