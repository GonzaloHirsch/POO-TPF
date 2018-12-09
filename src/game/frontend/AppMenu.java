package game.frontend;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;

//import javafx.scene.cont

import java.util.Optional;

public class AppMenu extends MenuBar {

    public AppMenu() {
        Menu file = new Menu("Options");
        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exit");
            alert.setHeaderText("Exit application");
            alert.setContentText("Are you sure you want to exit?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent()) {
                if (result.get() == ButtonType.OK) {
                    Platform.exit();
                }
            }
        });
        MenuItem mainMenuItem = new MenuItem("Return to main menu");
        mainMenuItem.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Main Menu");
            alert.setHeaderText("Return to Main Menu");
            alert.setContentText("Are you sure you want to return to the Main Menu?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent()) {
                if (result.get() == ButtonType.OK) {
                    AppMainMenu menu = new AppMainMenu(AppMainMenu.primaryStage);
                    Scene scene = new Scene(menu);
                    scene.getStylesheets().add("styles/stylesheet.css");
                    AppMainMenu.primaryStage.setResizable(false);
                    AppMainMenu.primaryStage.setScene(scene);
                    AppMainMenu.primaryStage.show();

                }
            }
        });
        file.getItems().addAll(mainMenuItem, exitMenuItem);
        Menu help = new Menu("Help");
        MenuItem aboutMenuItem = new MenuItem("About");
        aboutMenuItem.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("About");
            alert.setHeaderText("Candy TPE");
            alert.setContentText("Cátedra POO 2018.\n" +
                    "Original implementation: Laura Zabaleta (POO 2013).\n" +
                    "Actual implementation: \n\tIgnacio Villanueva - 59000\n\tGonzalo Hirsch - 59089");
            alert.showAndWait();
        });
        help.getItems().add(aboutMenuItem);
        getMenus().addAll(file, help);
    }

}
