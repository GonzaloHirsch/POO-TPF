package game.frontend;

import game.backend.CandyGame;
import game.backend.FruitCandyGame;
import game.backend.level.Level1;
import game.backend.level.Level2;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameApp extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		//CandyGame game = new CandyGame(Level1.class);
		CandyGame game = new FruitCandyGame(Level2.class);
		CandyFrame frame = new CandyFrame(game);
		Scene scene = new Scene(frame);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();


		//	Trying to build a main menu for the game
		/*
		AppMainMenu menu = new AppMainMenu(primaryStage);
		Scene scene = new Scene(menu, 65 * 9, 65 * 9);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
		*/
	}

}
