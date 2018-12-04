package game.backend;

import game.backend.element.Element;
import game.backend.element.Fruit;

public class FruitCandyGame extends CandyGame {
    public FruitCandyGame(Class<?> clazz) {
        super(clazz);
    }

    @Override
    public void cellExplosion(Element e) {
        if (e instanceof Fruit)
            this.state.addScore(e.getScore());
    }
}
