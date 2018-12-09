package game.frontend;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class ScorePanel extends BorderPane {

	private Label scoreLabel;

	/**
	 *	We removed the initial string of "0" so it doesn't change from 0 to amount of moves and score
	 *	We changed the background color to match the style of the main menu
	 */
	public ScorePanel() {
		setStyle("-fx-background-color: #ffa3e8");
		scoreLabel = new Label("Click on each item you want to move, don't drag.");
		scoreLabel.setAlignment(Pos.CENTER);
		scoreLabel.setStyle("-fx-font-size: 24");
		setCenter(scoreLabel);
	}
	
	public void updateScore(String text) {
		scoreLabel.setText(text);
	}

}