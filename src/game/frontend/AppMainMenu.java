package game.frontend;

import game.backend.CandyGame;
import game.backend.FruitCandyGame;
import game.backend.GameListener;
import game.backend.cell.Cell;
import game.backend.element.Element;
import game.backend.level.Level1;
import game.backend.level.Level2;
import game.backend.level.Level3;
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
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
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
        this.setStyle("-fx-background-color: #f1ff");

        //VBox levels = new VBox();

        HBox topBox = new HBox();
        /*
        HBox level1Box = new HBox();
        HBox level2Box = new HBox();
        HBox level3Box = new HBox();





        level1Box.setAlignment(Pos.CENTER);
        level1Box.setStyle("-fx-background-color: #12ffff");

        level2Box.setAlignment(Pos.CENTER);
        level2Box.setStyle("-fx-background-color: #ffff22");

        level3Box.setAlignment(Pos.CENTER);
        level3Box.setStyle("-fx-background-color: #122222");

*/
        this.setSpacing(10);

        //topBox.setAlignment(Pos.TOP_CENTER);
        //topBox.setStyle("-fx-background-color: #12ff22");
        /*
        levels.setAlignment(Pos.CENTER);
        levels.setStyle("-fx-background-color: #f1ff");
*/
        this.setPrefSize(65 * 9, 65 * 9);
        this.setPadding(new Insets(5,5,5,5));

        //  So that all the children are center aligned
        this.setAlignment(Pos.CENTER);

        Label menuTitle = new Label("Candy Game");
        menuTitle.setAlignment(Pos.TOP_CENTER);
        menuTitle.setFont(new Font(75));
        menuTitle.setPrefSize(500,300);
        menuTitle.setPadding(new Insets(10,10,10,10));

        Label tipLabel = new Label("Si te paras sobre el botón del nivel, este muestra información sobre el mismo");
        tipLabel.setFont(new Font(12));

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
        level1Button.setTooltip(new Tooltip(Level1.LevelInfo()));

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
        level2Button.setTooltip(new Tooltip(Level2.LevelInfo()));

        Button level3Button = new Button("Level 3 - Cage");
        ButtonFormatting(level3Button);
        level3Button.setOnAction(event -> {
            CandyGame game = new CandyGame(Level3.class);
            LevelLoader(game, AppMainMenu.primaryStage);
        });
        //level3Button.setTooltip(new Tooltip(Level3.LevelInfo()));

        //  Button for information
        Button infoButton = new Button("Acerca De");
        infoButton.setTextAlignment(TextAlignment.CENTER);
        infoButton.setPrefSize(110,25);
        infoButton.setAlignment(Pos.CENTER);
        infoButton.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Acerca De");
            alert.setHeaderText("Candy TPE");
            alert.setContentText("Cátedra POO 2018.\n" +
                    "Implementación Original: Laura Zabaleta (POO 2013).\n" +
                    "Implementación Actual: \n\tIgnacio Villanueva - 59000\n\tGonzalo Hirsch - 59089");
            alert.showAndWait();
        });

        //  Button to quit application
        Button exitButton = new Button("Salir");
        exitButton.setTextAlignment(TextAlignment.CENTER);
        exitButton.setPrefSize(90,25);
        exitButton.setAlignment(Pos.CENTER);
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

        setIncets(level1Button);
        setIncets(level2Button);
        setIncets(level3Button);
        setIncets(infoButton);
        setIncets(exitButton);

        topBox.setSpacing(400);

        topBox.getChildren().addAll(infoButton, exitButton);
/*

        level1Box.getChildren().addAll(level1Button);
        level2Box.getChildren().addAll(level2Button);
        level3Box.getChildren().addAll(level3Button);

        this.getChildren().addAll(topBox, level1Box, level2Box, level3Box);
*/

        //levels.getChildren().addAll(level1Button, level2Button, level3Button);
/*
        this.getChildren().addAll(infoButton, menuTitle, exitButton, levels);
*/
        //this.getChildren().addAll(infoButton, menuTitle, exitButton, level1Button, level2Button, level3Button);
        this.getChildren().addAll(topBox, menuTitle, level1Button, level2Button, level3Button, tipLabel);
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

    private void setIncets(Button button){
        button.setPadding(new Insets(10,10,10,10));
    }
}
