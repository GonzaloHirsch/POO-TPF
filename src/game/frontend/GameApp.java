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

		ImageManager imageManager = new ImageManager();

		//  A new instance of the main menu
		AppMainMenu menu = new AppMainMenu(primaryStage, imageManager);

		Scene scene = new Scene(menu);

		//  Applying the CSS stylesheet
		scene.getStylesheets().add("styles/stylesheet.css");

		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
