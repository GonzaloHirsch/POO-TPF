package game.backend.cell;

import game.backend.Grid;
import game.backend.element.*;

/*
    This cells have the ability to spawn a fruit, given a number representing the chance of a fruit falling.
    It can spawn either a fruit or a candy
 */

public class FruitGeneratorCell extends Cell {
    private static int maxFruitAmount;
    private static int spawnedFruitAmount;
    private static double fruitChance;
    private static CandyGeneratorCell candyGenCell;

    public FruitGeneratorCell(Grid grid, int maxFruitAmount, double fruitChance, CandyGeneratorCell candyGenCell) {
        super(grid);
        FruitGeneratorCell.maxFruitAmount = maxFruitAmount;
        FruitGeneratorCell.spawnedFruitAmount = 0;
        FruitGeneratorCell.fruitChance = fruitChance;
        FruitGeneratorCell.candyGenCell = candyGenCell;
    }

    @Override
    public boolean isMovable(){
        return true;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    /*
        If there are fruits available to be generated and the random number is in range, it generates a fruit.
        Else, it generates a candy.
     */
    @Override
    public Element getContent() {
        double r = Math.random();

        if (FruitGeneratorCell.spawnedFruitAmount < FruitGeneratorCell.maxFruitAmount && r < FruitGeneratorCell.fruitChance){
            int i = (int)(Math.random() * FruitType.values().length);
            FruitGeneratorCell.spawnedFruitAmount++;
            return new Fruit(FruitType.values()[i]);
        }

        return FruitGeneratorCell.candyGenCell.getContent();
    }

    @Override
    public Element getAndClearContent() {
        return getContent();
    }

    @Override
    public boolean fallUpperContent() {
        throw new IllegalStateException();
    }

    @Override
    public void setContent(Element content) {
        throw new IllegalStateException();
    }

    public static void incrementSpawnedFruits(int value){
        spawnedFruitAmount += value;
    }

    public static void initializeSpawnedFruits(){
        spawnedFruitAmount = 0;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

}
