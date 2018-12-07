package game.frontend;

import game.backend.CandyGame;
import game.backend.FruitCandyGame;
import game.backend.GameListener;
import game.backend.cell.Cell;
import game.backend.element.Element;
import game.backend.level.Level1;
import game.backend.level.Level2;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Optional;

public class AppMainMenu extends VBox {

    //It shouldnt be public, but it doesnt work the app menu if its private, i have to think a better way
    public static Stage primaryStage;

    public AppMainMenu(Stage primaryStage) {

        //getChildren().add(new AppMenu());

        AppMainMenu.primaryStage = primaryStage;

        this.setSpacing(10);

        this.setPrefSize(65 * 9, 65 * 9);

        //  So that all the children are center aligned
        this.setAlignment(Pos.CENTER);

        Label menuTitle = new Label("Candy Game");
        menuTitle.setAlignment(Pos.TOP_CENTER);
        menuTitle.setFont(new Font(50));
        menuTitle.setPrefSize(500,300);
        menuTitle.setPadding(new Insets(10,10,10,10));

        //this.getChildren().add(menuTitle);

        //  A button to go to each level
        Button level1Button = new Button("Level 1 - Normal");
        ButtonFormatting(level1Button);
        level1Button.setOnAction(event -> {
            CandyGame game = new CandyGame(Level1.class);
            /*
            CandyFrame frame = new CandyFrame(game);
            Scene scene = new Scene(frame);
            AppMainMenu.this.primaryStage.setResizable(false);
            AppMainMenu.this.primaryStage.setScene(scene);
            AppMainMenu.this.primaryStage.show();
            */
            LevelLoader(game, AppMainMenu.primaryStage);
        });

        Button level2Button = new Button("Level 2 - Fruits");
        ButtonFormatting(level2Button);
        level2Button.setOnAction(event -> {
            CandyGame game = new FruitCandyGame(Level2.class);
            /*
            CandyFrame frame = new CandyFrame(game);
            Scene scene = new Scene(frame);
            AppMainMenu.this.primaryStage.setResizable(false);
            AppMainMenu.this.primaryStage.setScene(scene);
            AppMainMenu.this.primaryStage.show();
            */
            LevelLoader(game, AppMainMenu.primaryStage);
        });

        Button level3Button = new Button("Level 3 - Jelly");
        ButtonFormatting(level3Button);
        level3Button.setOnAction(event -> {
            /*
            CandyGame game = new CandyGame(Level3.class);
            LevelLoader(game);
            */
        });

        //  Button for information
        Button infoButton = new Button("Acerca De");
        infoButton.setTextAlignment(TextAlignment.CENTER);
        infoButton.setPrefSize(100,25);
        infoButton.setAlignment(Pos.BASELINE_RIGHT);
        infoButton.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Acerca De");
            alert.setHeaderText("Candy TPE");
            alert.setContentText("Cátedra POO 2018.\n" +
                    "Implementación Original: Laura Zabaleta (POO 2013).\n" +
                    "Implementación Actual: Ignacio Villanueva - 59000 y Gonzalo Hirsch - 59089");
            alert.showAndWait();
        });

        //  Button to quit application
        Button exitButton = new Button("Salir");
        exitButton.setTextAlignment(TextAlignment.CENTER);
        exitButton.setPrefSize(100,25);
        exitButton.setAlignment(Pos.TOP_RIGHT);
        exitButton.setOnAction(event -> {
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

        level1Button.setPadding(new Insets(10,10,10,10));
        level2Button.setPadding(new Insets(10,10,10,10));
        level3Button.setPadding(new Insets(10,10,10,10));
        infoButton.setPadding(new Insets(10,10,10,10));
        exitButton.setPadding(new Insets(10,10,10,10));

        this.getChildren().addAll(menuTitle, level1Button, level2Button, level3Button, infoButton, exitButton);
    }

    private void LevelLoader(CandyGame candyGame, Stage primaryStage){
        CandyFrame frame = new CandyFrame(candyGame);
        Scene scene = new Scene(frame);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void ButtonFormatting(Button button){
        button.setTextAlignment(TextAlignment.CENTER);
        button.setPrefSize(150,50);
        button.setAlignment(Pos.CENTER);
    }
}
