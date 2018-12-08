package game.backend.element;

public class CagedCandy extends Element {

    private Candy candy;

    public CagedCandy(){};

    public CagedCandy(Candy candy) {
        this.candy = candy;
    }

    public void setCandy(Candy candy){
        this.candy = candy;
    }

    public Candy getCandy(){
        return candy;
    }

    @Override
    public boolean isMovable() {
        return false;
    }

    @Override
    public boolean isOverlay() {return true;}

    /*
        It hashes by candy
     */
    @Override
    public int hashCode() {
        return candy.hashCode();
    }

    /*
        It compares by candy caged
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof CagedCandy))
            return false;
        CagedCandy other = (CagedCandy) obj;
        return candy.equals(other.getCandy());
    }

    @Override
    public String getKey() {
        return "CAGED-CANDY";
    }

    @Override
    public String getFullKey() {
        return candy.getFullKey();
    }

    @Override
    public long getScore() {
        return 100;
    }
}
