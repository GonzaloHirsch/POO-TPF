package game.frontend;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;

//import javafx.scene.cont

import java.util.Optional;

public class AppMenu extends MenuBar {

    public AppMenu() {
        Menu file = new Menu("Archivo");
        MenuItem exitMenuItem = new MenuItem("Salir");
        exitMenuItem.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Salir");
            alert.setHeaderText("Salir de la aplicación");
            alert.setContentText("¿Está seguro que desea salir de la aplicación?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent()) {
                if (result.get() == ButtonType.OK) {
                    Platform.exit();
                }
            }
        });
        MenuItem mainMenuItem = new MenuItem("Volver al Menu Principal");
        mainMenuItem.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Menu Principal");
            alert.setHeaderText("Volver al Menu Principal");
            alert.setContentText("¿Está seguro que desea volver al menu?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent()) {
                if (result.get() == ButtonType.OK) {
                    AppMainMenu menu = new AppMainMenu(AppMainMenu.primaryStage);
                    Scene scene = new Scene(menu);
                    AppMainMenu.primaryStage.setResizable(false);
                    AppMainMenu.primaryStage.setScene(scene);
                    AppMainMenu.primaryStage.show();

                }
            }
        });
        file.getItems().addAll(mainMenuItem, exitMenuItem);
        Menu help = new Menu("Ayuda");
        MenuItem aboutMenuItem = new MenuItem("Acerca De");
        aboutMenuItem.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Acerca De");
            alert.setHeaderText("Candy TPE");
            alert.setContentText("Cátedra POO 2018.\n" +
                    "Implementación Original: Laura Zabaleta (POO 2013).\n" +
                    "Implementación Actual: Ignacio Villanueva - 59000 y Gonzalo Hirsch - 59089");
            alert.showAndWait();
        });
        help.getItems().add(aboutMenuItem);
        getMenus().addAll(file, help);
    }

}
