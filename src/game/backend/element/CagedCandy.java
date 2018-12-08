package game.backend.element;

public class CagedCandy extends Candy {

    public CagedCandy(){};

    public CagedCandy(CandyColor color){
        super(color);
    }

    @Override
    public boolean isMovable() {
        return false;
    }

    @Override
    public boolean isOverlay() {return true;}

    @Override
    public String getKey() {
        return "CAGED-CANDY";
    }

    @Override
    public long getScore() {
        return 100;
    }
}
