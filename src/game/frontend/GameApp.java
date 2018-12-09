package game.frontend;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameApp extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		//	Name on top of the window
		primaryStage.setTitle("Candy Game");

		/*
		//CandyGame game = new CandyGame(Level1.class);
		CandyGame game = new FruitCandyGame(Level2.class);
		CandyFrame frame = new CandyFrame(game);
		Scene scene = new Scene(frame);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();

*/
		//	Trying to build a main menu for the game

		AppMainMenu menu = new AppMainMenu(primaryStage);
		//Scene scene = new Scene(menu);
		//menu.getChildren().add(new AppMenu());

		Scene scene = new Scene(menu);
		scene.getStylesheets().add("styles/stylesheet.css");
		//((VBox)scene.getRoot()).getChildren().add(new AppMenu());
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	/*	IMPORTANT
		I found a bug when the user clicks the coordinates have an extra 0.5 in the x position.
		Don't know how to fucking correct it.
		The only solution I found is to compensate the error.
	 */

}
