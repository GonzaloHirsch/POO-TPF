package game.frontend;

import game.backend.element.Element;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class BoardPanel extends TilePane {

	private Group[][] cells;

	public BoardPanel(final int rows, final int columns, final int cellSize) {
		setPrefRows(rows);
		setPrefColumns(columns);
		setPrefTileHeight(cellSize);
		setPrefTileWidth(cellSize);
        Group group = new Group();

		this.cells = new Group[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				ImageView mainImage = new ImageView();
				mainImage.setFitHeight(65);			// To ensure that the image size doesn't exceed the cell size
				mainImage.setFitWidth(65);
				ImageView overlay = new ImageView();
				overlay.setFitHeight(65);
				overlay.setFitWidth(65);
			    cells[i][j] = new Group(mainImage, overlay);
				getChildren().add(cells[i][j]);
			}
		}
	}
	
	public void setImage(int row, int column, Image image, Image overlay) {
		ImageView mainImage = (ImageView) cells[row][column].getChildren().get(0);
		mainImage.setImage(image);
        ImageView overlayImage = (ImageView) cells[row][column].getChildren().get(1);
        overlayImage.setImage(overlay);
	}

	public void setGlowingImage(int row, int column, double value, Image image){
		ImageView mainImage = (ImageView) cells[row][column].getChildren().get(0);
		mainImage.setImage(image);
		mainImage.setEffect(new Glow(value));
	}

}