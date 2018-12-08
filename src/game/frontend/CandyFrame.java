package game.frontend;

import game.backend.CandyGame;
import game.backend.GameListener;
import game.backend.cell.Cell;
import game.backend.element.CagedCandy;
import game.backend.element.Candy;
import game.backend.element.CandyColor;
import game.backend.element.Element;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class CandyFrame extends VBox {

	private static final int CELL_SIZE = 65;

	private BoardPanel boardPanel;
	private ScorePanel scorePanel;
	private ImageManager images;
	private Point2D lastPoint;
	private CandyGame game;

	public CandyFrame(CandyGame game) {
		this.game = game;
		getChildren().add(new AppMenu());
		images = new ImageManager();

		//	Board Panel
		boardPanel = new BoardPanel(game.getSize(), game.getSize(), CELL_SIZE);
		getChildren().add(boardPanel);

		//	Score Panel
		scorePanel = new ScorePanel();
		getChildren().add(scorePanel);

		game.initGame();
		GameListener listener;
		game.addGameListener(listener = new GameListener() {
			@Override
			public void gridUpdated() {
				Timeline timeLine = new Timeline();
				Duration frameGap = Duration.millis(100);
				Duration frameTime = Duration.ZERO;
				for (int i = game().getSize() - 1; i >= 0; i--) {
					for (int j = game().getSize() - 1; j >= 0; j--) {
						int finalI = i;
						int finalJ = j;
						Cell cell = CandyFrame.this.game.get(i, j);
						Element element = cell.getContent();
						Image image = images.getImage(element);
						Image overlay = images.getOverlay(element);
						timeLine.getKeyFrames().add(new KeyFrame(frameTime, e -> boardPanel.setImage(finalI, finalJ, null, null)));
						timeLine.getKeyFrames().add(new KeyFrame(frameTime, e -> boardPanel.setImage(finalI, finalJ, image, overlay)));
					}
					frameTime = frameTime.add(frameGap);
				}
				timeLine.play();
			}
			@Override
			public void cellExplosion(Element e) {
				//
			}
		});

		listener.gridUpdated();

		//	When the user clicks on a grid cell
		addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			//	Verification that stops the player from making moves if the game is over
			if (!game().isFinished()){
				if (lastPoint == null) {
					lastPoint = translateCoords(event.getX() /*- 0.5*/, event.getY());
					//if (lastPoint != null)
					//	lastPoint = new Point2D(lastPoint.getX() - 0.5, lastPoint.getY());
					System.out.println("Get first = " +  lastPoint);
				} else {
					Point2D newPoint = translateCoords(event.getX() /*- 0.5*/, event.getY());
					if (newPoint != null) {
						//newPoint = new Point2D(newPoint.getX() - 0.5, newPoint.getY());
						System.out.println("Get second = " +  newPoint);

						//HERE WE CAN SHOW MOVES
						//	Here it tries to make the swap
						game().tryMove((int)lastPoint.getX(), (int)lastPoint.getY(), (int)newPoint.getX(), (int)newPoint.getY());

						String message;
						//	Message showing the score of the player
						String scoreMessage = ((Long)game().getScore()).toString() + " Points";
						String movesMessage = game().getMovesLeft() + " Moves Left";
						message = movesMessage + " - " + scoreMessage;

						//	Checking if game is over
						if (game().isFinished()) {
							if (game().playerWon()) {
								message = message + " - Finished - You Win!";
							} else {
								message = message + " - Finished - Loser !";
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

	/*
		I added (y - 32.5) to compensate for the error when the mouse position is picked up.
		There is an error of about CELL_SIZE/2, which is in the field of y.
		Without the compensation, the game misreads the coordinates and fails to make some moves.

		It switches the i and j coordinates to be consistent with the matrix notation.
		For example (matrix):			For example (coordinates):
			[(0,0)][(0,1)][(0,2)]			[(0,2)][(1,2)][(2,2)]
			[(1,0)][(1,1)][(1,2)]			[(0,1)][(1,1)][(2,1)]
			[(2,0)][(2,1)][(2,2)]			[(0,0)][(1,0)][(2,0)]
		Notice the switch between the coordinate systems
	 */
	private Point2D translateCoords(double x, double y) {
		double i = x / CELL_SIZE;
		//double j = y / CELL_SIZE;
		double j = (y - 32.5) / CELL_SIZE;
		return (i >= 0 && i < game.getSize() && j >= 0 && j < game.getSize()) ? new Point2D(j, i) : null;
	}

}
