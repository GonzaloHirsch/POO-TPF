package game.backend.level;

//  Cage/Jaula Level

import game.backend.GameState;
import game.backend.Grid;
import game.backend.cell.CandyGeneratorCell;
import game.backend.cell.Cell;
import game.backend.cell.FruitGeneratorCell;
import game.backend.element.*;

import java.io.Console;

public class Level3 extends Level1 {


    @Override
    public  void fillCells(){
        super.fillCells();
    }

    @Override
    public void initialize(){
        super.initialize();
        setCagedCandy();
    }

    @Override
    public boolean tryMove(int i1, int j1, int i2, int j2) {
        System.out.println(g()[i1][j1].getContent().getKey());
        return super.tryMove(i1,j1,i2,j2);
    }

    private void setCagedCandy(){
        g()[3][3].setContent(new CagedCandy(new Candy(CandyColor.BLUE)));
    }
}
