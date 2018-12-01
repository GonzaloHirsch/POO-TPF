package game.backend.element;

public class Fruit extends Element {

    private FruitType type;
    private boolean isCleared = false;

    public Fruit(){}

    public Fruit(FruitType type) { this.type = type; }

    public FruitType getType() { return this.type; }

    public void setType(FruitType type) { this.type = type; }

    @Override
    public int hashCode() { return ((this.type == null) ? 0 : type.hashCode()); }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Fruit))
            return false;
        Fruit other = (Fruit) obj;
        if (this.type != other.type)
            return false;
        return true;
    }

    @Override
    public long getScore(){ return 1; }

    @Override
    public boolean isMovable() { return true; }

    @Override
    public String getKey() { return "FRUIT"; }

    @Override
    public String getFullKey() { return type.toString() + "-FRUIT";}
}
