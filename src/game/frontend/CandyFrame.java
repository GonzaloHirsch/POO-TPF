package game.frontend;

import game.backend.FrontEndListener;
import game.backend.cell.Cell;
import game.backend.cell.FruitGeneratorCell;
import game.backend.element.*;

import game.backend.element.Element;
import game.backend.gametypes.CandyGame;
import game.backend.gametypes.FruitCandyGame;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Optional;

public class CandyFrame extends VBox {

	private static final int CELL_SIZE = 65;

	private BoardPanel boardPanel;
	private ScorePanel scorePanel;
	private ImageManager imageManager;
	private Point2D lastPoint;
	private CandyGame game;
	private Stage primaryStage;

	private String winMessage = "You Win!";
	private String loseMessage = "Loser!";
	private String endMessage;

	public CandyFrame(CandyGame game, Stage primaryStage, ImageManager imageManager) {
		this.game = game;
		this.imageManager = imageManager;
		this.primaryStage = primaryStage;

		getChildren().add(new AppMenu(primaryStage, imageManager));

		//	Board Panel
		boardPanel = new BoardPanel(game.getSize(), game.getSize(), CELL_SIZE);
		boardPanel.setBoardBackground(this.imageManager.getImage(new Nothing()));
		getChildren().add(boardPanel);

		//	Score Panel
		scorePanel = new ScorePanel();
		getChildren().add(scorePanel);

		game.initGame();
		FrontEndListener callbacks;
		game.addFrontEndCallbacks(callbacks = new FrontEndListener() {
			@Override
			public void gridUpdated() {
				Timeline timeLine = new Timeline();
				Duration frameGap = Duration.millis(75);
				Duration frameTime = Duration.ZERO;
				for (int i = game().getSize() - 1; i >= 0; i--) {
					for (int j = game().getSize() - 1; j >= 0; j--) {
						int finalI = i;
						int finalJ = j;
						Cell cell = CandyFrame.this.game.get(i, j);
						Element element = cell.getContent();
						Image image = CandyFrame.this.imageManager.getImage(element);
						Image overlay = CandyFrame.this.imageManager.getOverlay(element);
						timeLine.getKeyFrames().add(new KeyFrame(frameTime, e -> boardPanel.setImage(finalI, finalJ, null, null)));
						timeLine.getKeyFrames().add(new KeyFrame(frameTime, e -> boardPanel.setImage(finalI, finalJ, image, overlay)));
					}
					frameTime = frameTime.add(frameGap);
				}
				timeLine.play();
			}

			@Override
			public void swapElements(int i1, int j1, int i2, int j2) {
				boardPanel.swapCells(i1, j1, i2, j2);
			}

			@Override
			public void fallElements() {

			}

		});

		callbacks.gridUpdated();

		//	When the user clicks on a grid cell
		addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			//	Verification that stops the player from making moves if the game is over
			if (!game().isFinished()){
				if (lastPoint == null) {
					lastPoint = translateCoords(event.getX(), event.getY());

					//	Makes the chosen cell glow
					if (lastPoint != null)
						addGlow((int)lastPoint.getX(), (int)lastPoint.getY(), 0.5);
				} else {
					Point2D newPoint = translateCoords(event.getX(), event.getY());
					if (newPoint != null) {

						//	It removes the glow from the cell
						addGlow((int)lastPoint.getX(), (int)lastPoint.getY(), 0);

						//	Here it tries to make the swap
						game().tryMove((int)lastPoint.getX(), (int)lastPoint.getY(), (int)newPoint.getX(), (int)newPoint.getY());

						String message = game().toString();

						//	Checking if game is over
						if (game().isFinished()) {
							if (game().playerWon()) {
								this.endMessage = this.winMessage;
							} else {
								this.endMessage = this.loseMessage;
							}

							if (this.game instanceof FruitCandyGame)
								FruitGeneratorCell.initializeSpawnedFruits();

							//	Prompting the player to play again
							Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

							//	Changing buttons text
							((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Play Again");
							((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("Main Menu");

							alert.getDialogPane().getStylesheets().add("styles/stylesheet.css");
							alert.setTitle("Game Over");
							alert.setHeaderText(this.endMessage);
							alert.setContentText("Do you want to go back to the Main Menu or play again?");
							Optional<ButtonType> result = alert.showAndWait();
							if(result.isPresent()) {
								if (result.get() == ButtonType.OK) {
									AppMainMenu.LevelLoader(this.game, this.primaryStage, this.imageManager);
								} else {
									AppMainMenu menu = new AppMainMenu(this.primaryStage, this.imageManager);
									Scene scene = new Scene(menu);
									scene.getStylesheets().add("styles/stylesheet.css");
									this.primaryStage.setResizable(false);
									this.primaryStage.setScene(scene);
									this.primaryStage.show();
								}
							}
						}
						scorePanel.updateScore(message);
						lastPoint = null;
					}
				}
			}
		});
	}

	private CandyGame game() {
		return game;
	}


	private void addGlow(int i, int j, double value){
		Cell cell = CandyFrame.this.game.get(i, j);
		Element element = cell.getContent();

		Image image = imageManager.getImage(element);

		boardPanel.setGlowingImage(i, j, value, image);
	}

	/*
		We use sceneToLocal in order to avoid offsets from other elements such as the top menu bar.

		It switches the i and j coordinates to be consistent with the matrix notation.
		For example (matrix):			For example (coordinates):
			[(0,0)][(0,1)][(0,2)]			[(0,2)][(1,2)][(2,2)]
			[(1,0)][(1,1)][(1,2)]			[(0,1)][(1,1)][(2,1)]
			[(2,0)][(2,1)][(2,2)]			[(0,0)][(1,0)][(2,0)]
		Notice the switch between the coordinate systems
	 */
	private Point2D translateCoords(double x, double y) {

		double i = boardPanel.sceneToLocal(x,y).getX() / CELL_SIZE;
		double j = boardPanel.sceneToLocal(x,y).getY() / CELL_SIZE;

		return (i >= 0 && i < game.getSize() && j >= 0 && j < game.getSize()) ? new Point2D(j, i) : null;
	}

}
