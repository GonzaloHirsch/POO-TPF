package game.frontend;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class BoardPanel extends Pane {

	private enum Layout {
		MAIN_IMAGE(0), OVERLAY(1);

		private final int index;

		Layout(int i){
			index = i;
		}
		public int getIndex(){
			return  index;
		}

	}

	private Group[][] cells;

	public BoardPanel(final int rows, final int columns, final int cellSize) {
		setPrefWidth(columns*cellSize);
		setPrefHeight(rows * cellSize);

        Group group = new Group();

		this.cells = new Group[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
			    cells[i][j] = new Group();
			    for(Layout l: Layout.values()){
			    	ImageView image = new ImageView();
			    	image.setFitWidth(cellSize);
			    	image.setFitHeight(cellSize);
			    	cells[i][j].getChildren().add(image);
				}


			    cells[i][j].setTranslateX(j * cellSize);
				cells[i][j].setTranslateY(i * cellSize);
				getChildren().add(cells[i][j]);
			}
		}
	}

	public void setBoardBackground(Image backgroundImage){
		BackgroundImage myBI= new BackgroundImage(backgroundImage,
				BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);

		this.setBackground(new Background(myBI));
	}
	
	public void setImage(int row, int column, Image image, Image overlay) {
		ImageView mainImage = (ImageView) cells[row][column].getChildren().get(Layout.MAIN_IMAGE.getIndex());
		mainImage.setImage(image);
        ImageView overlayImage = (ImageView) cells[row][column].getChildren().get(Layout.OVERLAY.getIndex());
        overlayImage.setImage(overlay);
	}

	public void setGlowingImage(int row, int column, double value, Image image){
		ImageView mainImage = (ImageView) cells[row][column].getChildren().get(Layout.MAIN_IMAGE.getIndex());
		mainImage.setImage(image);
		mainImage.setEffect(new Glow(value));
	}

	public void swapCells(int i1, int j1, int i2, int j2){
		Animations.SwapElements(cells[i1][j1].getChildren().get(Layout.MAIN_IMAGE.getIndex()), cells[i2][j2].getChildren().get(Layout.MAIN_IMAGE.getIndex()), 100);
		Group aux = cells[i1][j1];
		cells[i1][j1] = cells[i2][j2];
		cells[i2][j2] = aux;
	}

}